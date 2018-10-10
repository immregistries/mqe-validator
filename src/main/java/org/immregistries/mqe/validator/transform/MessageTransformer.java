package org.immregistries.mqe.validator.transform;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.mqe.core.util.DateUtility;
import org.immregistries.mqe.validator.address.AddressCleanser;
import org.immregistries.mqe.validator.address.AddressCleanserFactory;
import org.immregistries.mqe.validator.engine.codes.CodeRepository;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.PatientImmunity;
import org.immregistries.mqe.vxu.VaccinationVIS;
import org.immregistries.mqe.vxu.hl7.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// NOTE: When you do things like change a FLU vaccine - do that in the business layer
// that should happen after the validation...
//
// In my business layer, I might add an extra piece that says
// "Your codes got dinged, but I'm going to use it, and change it to this."

// We need to do our reporting based on what came in, not what we actually did with it.
//
/*
 * //General principle:
 *
 * PRE-transform, we want to count up all the codes they sent in. This will be cool. We'll actually
 * go through the object model and list out all the stuff they sent. Then we can save that at the
 * end if we want.
 *
 * Transform step , we interpret what's there , and pick certain pieces out , but we generally don't
 * change anything, or add anything.
 *
 *
 * DO we need to be able to preserve what came in v.s. what we changed it to in the transform?
 *
 * We don't want to say "your cvx is not right for your mvx" if we derived the cvx
 *
 * We want to be able to report "We decided to make this change to your message" We Do want to say
 * "You put this crazy CVX, and we opted to use this other one" How do I do that?
 *
 *
 * So I think perhaps I should use the same pattern here, where I can report what's happening. call
 * it a "Reportable" or something.
 *
 * every method should report what it did. Picked the first address only? Say it. pick a guardian
 * out of the list of next of kins? yup. put it on a reportable.
 *
 * there really shouldn't be a lot of stuff in the MCIR specific one.
 */

public enum MessageTransformer {
                                INSTANCE;

  private CodeRepository repo = CodeRepository.INSTANCE;

  private static final Logger logger = LoggerFactory.getLogger(MessageTransformer.class);

  private DateUtility datr = DateUtility.INSTANCE;

  // private AddressCleanser ac = AddressCleanserDefault.INSTANCE;

  // given an HL7 object model, put it into the VXU business object model.
  public MqeMessageReceived transform(MqeMessageReceived mr) {
    // Make sure to get the stuff from this class: VaccinationObservationsAreValidFIXTHIS
    transformHeaderData(mr.getMessageHeader());
    transformPatient(mr);
    transformObservations(mr);
    transformVaccinations(mr.getVaccinations());
    transformAddesses(mr);

    return mr;
  }

  protected void transformAddesses(MqeMessageReceived mr) {
    if (mr == null) {
      return;
    }

    //  Build a list of addresses to clean.
    List<MqeAddress> list = new ArrayList<>();
    MqePatient p = mr.getPatient();

    if (p != null) {
      list.addAll(p.getPatientAddressList());
      if (p.getResponsibleParty() != null) {
        list.add(p.getResponsibleParty().getAddress());
      }
    }

    if (mr.getNextOfKins() != null) {
      for (MqeNextOfKin dq : mr.getNextOfKins()) {
        list.add(dq.getAddress());
      }
    }

    if (logger.isInfoEnabled()) {
      logger.info("Starting address cleansing request");
    }

    //Then clean them. Get the cleanser every time.
    AddressCleanser ac = AddressCleanserFactory.INSTANCE.getAddressCleanser();
    Map<MqeAddress, MqeAddress> cleanMap = ac.cleanThese(list.toArray(new MqeAddress[] {}));
    if (logger.isInfoEnabled()) {
      logger.info("Finished address cleansing request");
    }

    //Then set them back to their original spots.
    List<MqeAddress> cleanAddresses = new ArrayList<>();

    if (p != null) {

      for (MqeAddress a : p.getPatientAddressList()) {
        MqeAddress aClean = cleanMap.get(a);
        cleanAddresses.add(aClean);
      }

      p.getPatientAddressList().clear();
      p.getPatientAddressList().addAll(cleanAddresses);
      if (logger.isInfoEnabled()) {
        logger.info("Patient Address: " + p.getPatientAddress());
      }
      if (p.getPatientAddress() != null && !p.getPatientAddress().isClean()) {
        logger.warn("Patient Address not clean: " + p.getPatientAddress().getCleansingResultCode());
      }
      if (p.getResponsibleParty() != null) {
        MqeAddress aClean = cleanMap.get(p.getResponsibleParty().getAddress());
        p.getResponsibleParty().setAddress(aClean);
      }
    }

    if (mr.getNextOfKins() != null) {
      for (MqeNextOfKin dq : mr.getNextOfKins()) {
        MqeAddress a = dq.getAddress();
        MqeAddress clean = cleanMap.get(a);
        dq.setAddress(clean);
      }
    }
  }

  protected void transformVaccinations(List<MqeVaccination> vaccinations) {
    for (MqeVaccination v : vaccinations) {
      // transform information source into boolean for administered:
      v.setAdministered("00".equals(v.getInformationSource()));

      // calculate the derived CVX:
      String cvxDerived = "";
      if (StringUtils.isNotBlank(v.getAdminNdc())) {
        String ndc = v.getAdminNdc();
        // lookup CVX:
        cvxDerived = repo.getRelatedCodes().getCvxValueFromNdcString(ndc);
      } else if (StringUtils.isNotBlank(v.getAdminCvxCode())) {
        cvxDerived = v.getAdminCvxCode();
      } else if (StringUtils.isNotBlank(v.getAdminCptCode())) {
        // lookup CVX:
        cvxDerived = repo.getRelatedCodes().getCvxFromCptString(v.getAdminCptCode());
      }
      v.setCvxDerived(cvxDerived);
      // calculate the vaccine groups for this vaccine based on the derived CVX:
      v.setVaccineGroupsDerived(repo.getRelatedCodes().getVaccineGroupLabelsFromCvx(cvxDerived));

      String mvxCode = v.getManufacturerCode();
      String cvxCode = v.getCvxDerived();
      String adminDate = v.getAdminDateString();

      Code product = this.repo.getVaccineProduct(cvxCode, mvxCode, adminDate);

      if (product != null) {
        String productCode = product.getValue();
        v.setProduct(productCode);
      }
    }
  }

  protected void transformHeaderData(MqeMessageHeader mh) {
    if (mh != null && StringUtils.isNotEmpty(mh.getMessageDateString())) {
      Date d = datr.parseDate(mh.getMessageDateString());
      mh.setMessageDate(d);
    }
  }

  private static final String OBX_VACCINE_FUNDING = "64994-7";
  private static final String OBX_VACCINE_TYPE = "30956-7";
  private static final String OBX_VIS_PUBLISHED = "29768-9";
  private static final String OBX_VIS_PRESENTED = "29769-7";
  private static final String OBX_DISEASE_WITH_PRESUMED_IMMUNITY = "59784-9";
  private static final String OBX_SERIOLOGICAL_EVIDENCE_OF_IMMUNITY = "75505-8";

  protected void transformObservations(MqeMessageReceived mr) {
    for (MqeVaccination v : mr.getVaccinations()) {
      List<Observation> obxList = v.getObservations();
      List<String> subIdOrderedList = new ArrayList<String>();
      Map<String, List<Observation>> obxMap = new HashMap<String, List<Observation>>();

      for (Observation o : obxList) {

        String subId = o.getSubId();
        List<Observation> obxSubList = obxMap.get(subId);

        if (obxSubList == null) {
          obxSubList = new ArrayList<Observation>();
          obxMap.put(subId, obxSubList);
          subIdOrderedList.add(subId);// This keeps the order of the OBX in the message.
        }

        obxSubList.add(o);
      }

      for (String subId : subIdOrderedList) {

        List<Observation> obsSet = obxMap.get(subId);
        // what kind of set is this?
        boolean isVIS = false;
        boolean isImmunity = false;
        boolean isVfc = false;

        for (Observation o : obsSet) {
          if (o != null && o.getIdentifierCode() != null) {
            switch (o.getIdentifierCode()) {
              case OBX_VACCINE_FUNDING:
                isVfc = true;
                break;
              case OBX_VIS_PUBLISHED:
              case OBX_VIS_PRESENTED:
              case OBX_VACCINE_TYPE:
                isVIS = true;
                break;
              case OBX_DISEASE_WITH_PRESUMED_IMMUNITY:
                isImmunity = true;
                break;
            }
          }
        }

        if (isVIS) {
          // make a VIS object from this set, add to the list for the vaccine.
          VaccinationVIS vis = createVISFromObxSet(obsSet);
          // add to vaccine's list.
          v.setVaccinationVis(vis);
        }

        if (isImmunity) {
          // make immunity object
          PatientImmunity pi = createPatientImmunityFromObxSet(obsSet);
          // add to patient's list.
          mr.getPatient().getPatientImmunityList().add(pi);
        }

        if (isVfc) {
          // make VFC
          String vfcCode = getVfcCodeFromObxSet(obsSet);
          // set in the vaccine.
          v.setFinancialEligibilityCode(vfcCode);
        }
      }
    }
  }

  //  protected boolean isSingleSubIdList(List<Observation> obxList) {
  //    String subId = (obxList != null && obxList.size() > 0 ? obxList.get(0).getSubId() : "");
  //    String nextSubId = subId;
  //    for (Observation o : obxList) {
  //      subId = o.getSubId();
  //      if (!subId.equals(nextSubId)) {
  //        return false;
  //      } else {
  //        nextSubId = subId;
  //      }
  //    }
  //    return true;
  //  }

  protected String getVfcCodeFromObxSet(List<Observation> obxList) {

    if (obxList == null) {
      return null;
    }

    for (Observation o : obxList) {
      // Transform them into their stuff!!! Make them into objects they represent.
      // We really only accept a few kinds of observations:
      switch (o.getIdentifierCode()) {
        case OBX_VACCINE_FUNDING:
          return o.getValue();
        default:
          // don't use it... it's not a VFC observation.
          break;
      }
    }
    return null;
  }

  protected PatientImmunity createPatientImmunityFromObxSet(List<Observation> obxList) {

    if (obxList == null) {
      return null;
    }

    PatientImmunity pi = new PatientImmunity();

    for (Observation o : obxList) {
      switch (o.getIdentifierCode()) {
        case OBX_DISEASE_WITH_PRESUMED_IMMUNITY:
        case OBX_SERIOLOGICAL_EVIDENCE_OF_IMMUNITY:
          pi.setCode(o.getValue());
          pi.setType(o.getIdentifierCode());
      }
    }

    return pi;
  }

  protected VaccinationVIS createVISFromObxSet(List<Observation> obxList) {
    if (obxList == null) {
      return null;
    }

    // This method assumes a single set of VIS OBX's. These should
    // all be from the same sub-id.
    VaccinationVIS vis = new VaccinationVIS();
    // We should probably enforce that it's all the same sub id being sent in.
    for (Observation o : obxList) {
      switch (o.getIdentifierCode()) {
        case OBX_VACCINE_TYPE:
          vis.setCvxCode(o.getValue());
          break;
        case OBX_VIS_PUBLISHED:
          String publishedDateString = o.getValue();
          vis.setPublishedDateString(publishedDateString);
          break;
        case OBX_VIS_PRESENTED:
          String presentedDateString = o.getValue();
          vis.setPresentedDateString(presentedDateString);
          break;
        default:
          // not a vis part.
          break;
      }
    }

    logger.info("VIS object built from observations: " + vis);
    return vis;
  }

  /**
   * Parse birth/death dates, etc.
   *
   * @param mr Message to be processed.
   */
  protected void transformPatient(MqeMessageReceived mr) {
    MqePatient p = mr.getPatient();
    // transformAddress(p.getAddress());

    // TODO: should we be parsing the birth dates here, or in the validation classes?
    // birth date transformations
    String birthDateString = p.getBirthDateString();
    if (!StringUtils.isBlank(birthDateString)) {
      Date birthDateObject = datr.parseDate(birthDateString);

      if (birthDateObject != null) {
        p.setBirthDate(birthDateObject);
        // is this an adult???
        p.setUnderAged(datr.isAdult(birthDateObject));
      }
    }

    // death date transformations
    String deathDateString = p.getDeathDateString();
    if (!StringUtils.isBlank(deathDateString)) {
      Date deathDateObject = datr.parseDate(deathDateString);

      if (deathDateObject != null) {
        p.setDeathDate(deathDateObject);
      }
    }

  }

  // From Validation.java
  // for (Observation observation : vaccination.getObservations())
  // {
  // skippableItem = observation;
  // handleCodeReceived(observation.getValueType(), PotentialIssues.Field.OBSERVATION_VALUE_TYPE);
  // handleCodeReceived(observation.getObservationIdentifier(),
  // PotentialIssues.Field.OBSERVATION_IDENTIFIER_CODE);
  // if (!observation.isSkipped())
  // {
  // if (financialEligibilityCode == null &&
  // observation.getObservationIdentifierCode().equals(OBX_VACCINE_FUNDING))
  // {
  // if (notEmpty(observation.getObservationValue(), pi.ObservationValueIsMissing))
  // {
  // financialEligibilityCode = observation.getObservationValue();
  // }
  // } else if (observation.getObservationIdentifierCode().equals(OBX_VACCINE_TYPE)
  // || observation.getObservationIdentifierCode().equals(OBX_VIS_PRESENTED)
  // || observation.getObservationIdentifierCode().equals(OBX_VIS_PUBLISHED))
  // {
  // String key = observation.getObservationSubId();
  // VaccinationVIS vaccinationVIS = vaccinationVISMap.get(key);
  // if (vaccinationVIS == null)
  // {
  // vaccinationVIS = new VaccinationVIS();
  // vaccinationVIS.setVaccination(vaccination);
  // vaccinationVISMap.put(key, vaccinationVIS);
  // vaccination.getVaccinationVisList().add(vaccinationVIS);
  // }
  // if (observation.getObservationIdentifierCode().equals(OBX_VACCINE_TYPE))
  // {
  // vaccinationVIS.setCvxCode(observation.getObservationValue());
  // } else if (observation.getObservationIdentifierCode().equals(OBX_VIS_PRESENTED))
  // {
  // if (!observation.getObservationValue().equals(""))
  // {
  // vaccinationVIS.setPresentedDate(createDate(pi.VaccinationVisPresentedDateIsInvalid,
  // observation.getObservationValue()));
  // }
  // } else if (observation.getObservationIdentifierCode().equals(OBX_VIS_PUBLISHED))
  // {
  //
  // if (!observation.getObservationValue().equals(""))
  // {
  // vaccinationVIS.setPublishedDate(createDate(pi.VaccinationVisPublishedDateIsInvalid,
  // observation.getObservationValue()));
  // }
  // }
  // } else if
  // (observation.getObservationIdentifierCode().equals(OBX_DISEASE_WITH_PRESUMED_IMMUNITY))
  // {
  //
  // PatientImmunity patientImmunity = new PatientImmunity();
  // patientImmunity.setPatient(patient);
  // patientImmunity.setImmunityCode(observation.getObservationValue());
  // patient.getPatientImmunityList().add(patientImmunity);
  // handleCodeReceived(patientImmunity.getImmunity(), PotentialIssues.Field.PATIENT_IMMUNITY_CODE);
  // }
  // }
  // }
}
