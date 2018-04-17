package org.immregistries.dqa.validator.transform;

import java.util.List;
import org.immregistries.dqa.core.util.DateUtility;
import org.immregistries.dqa.validator.address.AddressCleanser;
import org.immregistries.dqa.validator.address.AddressCleanserDefault;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.PatientImmunity;
import org.immregistries.dqa.vxu.hl7.Observation;

public class MessageTransformerMCIR {

  private DateUtility du = DateUtility.INSTANCE;
  private AddressCleanser ac = AddressCleanserDefault.INSTANCE;


  // given an HL7 object model, put it into the VXU business object model.
  public void transform(DqaMessageReceived mr) {

    // Make sure to get the stuff from this class: VaccinationObservationsAreValidFIXTHIS
    transformHeaderData(mr.getMessageHeader());
  }

  public void transformVaccinations(List<DqaVaccination> vaccs) {

  }

  protected void transformHeaderData(DqaMessageHeader mh) {}

  protected void transformObservations(DqaMessageReceived mr) {
    // for (Vaccination v : mr.getVaccinations()) {
    // List<Observation> obxList = v.getObservations();
    // for (Observation o : obxList) {
    //
    // }
    // }

  }

  protected void transformPatient(DqaMessageReceived mr) {
    DqaPatient p = mr.getPatient();
    // transformAddress(p.getAddress());
    //
    // //Need to pick one as the official "guardian"
    // for (NextOfKin kin : mr.getNextOfKins()) {
    // if (p.getResponsibleParty() == null) {
    // if (kin.isResponsibleRelationship()) {
    // p.setResponsibleParty(kin);
    // break;
    // }
    // }
    // }

    // if ("".equals(p.getIdMedicaidNumber())) {
    // // If MRN was not found then try one more time looking for it under PT or
    // // PI
    // for (String[] comps : compList) {
    // String idType = read(5, comps);
    // if (idType.equals(def.getParam(HL7Param.PATIENT_ID_TYPE_PT)) ||
    // idType.equals(def.getParam(HL7Param.PATIENT_ID_TYPE_PI))) {
    // patient.getId().setMr(comps[0]);
    // break;
    // }
    // }
    // }

  }

  protected void transformNextOfKinData(DqaMessageReceived mr) {
    // List<NextOfKin> kinList = mr.getNextOfKins();
    // for (NextOfKin kin : kinList) {
    // // 1. Clean the address
    // transformAddress(kin.getAddress());
    // }

  }

  protected void transformAddress(DqaAddress... a) {
    ac.cleanThese(a);
  }

  /*
   * I need to create a set of methods that will transform an observation into a business object.
   */
  // if (observation.getObservationIdentifierCode().equals(OBX_DISEASE_WITH_PRESUMED_IMMUNITY))
  public PatientImmunity toPatientImmunity(Observation ob) {

    PatientImmunity patientImmunity = new PatientImmunity();
    patientImmunity.setImmunityCode(ob.getValue());
    // patient.getPatientImmunityList().add(patientImmunity);
    // handleCodeReceived(patientImmunity.getImmunity(),
    // PotentialIssues.Field.PATIENT_IMMUNITY_CODE);

    return new PatientImmunity();

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
