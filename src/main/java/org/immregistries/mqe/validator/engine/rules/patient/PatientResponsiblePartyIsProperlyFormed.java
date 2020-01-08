package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.address.SmartyStreetResponse;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientResponsiblePartyIsProperlyFormed extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientHasResponsibleParty.class};
  }

  public PatientResponsiblePartyIsProperlyFormed() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressStateIsMissing);
      id.setHowToFix("The state of the guardian/parent address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianAddressCityIsMissing);
      id.setHowToFix("The city of the guardian/parent address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressZipIsMissing);
      id.setHowToFix("The zip code of the guardian/parent address is missing. Please review the address for all persons connected with this patient and either remove it or update it.");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianNameFirstIsMissing);
      id.setHowToFix("The guardian/parent first name is missing. Please review the patient guardian/parent and ensure that they have their first name "
          + "or please ask your software vendor to ensure that the name of the guardian/parent responsible for the patient is sent properly in the message. ");
      id.setWhyToFix("The name of the guardian/parent can be used for patient matching and as a contact for reminder/recall activities. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianNameLastIsMissing);
      id.setHowToFix("The guardian/parent last name is missing. Please review the patient guardian/parent and ensure that they have their first name "
          + "or please ask your software vendor to ensure that the name of the guardian/parent responsible for the patient is sent properly in the message. ");
      id.setWhyToFix("The name of the guardian/parent can be used for patient matching and as a contact for reminder/recall activities. ");
    }

    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianNameIsSameAsUnderagePatient);
      id.setImplementationDescription(
          "Patient first and last name match the guardian first and last name.");
      id.setHowToFix("It appears that the minor (child) patient has themselves indicated as their own guardian/parent. Verify that this is the case in your system. If the information is recorded correctly then contact your software vendor and ask that they do not submit the patient as their own guardian/parent. ");
      id.setWhyToFix("The guardian/parent information is used for patient deduplication. Resending the patient information as the guardian does not help improve matching and may cause confusion and duplicated information that is not properly kept up-to-date in the IIS. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianPhoneIsMissing);
      id.setHowToFix("Indicate the phone for the guardian/parent. ");
      id.setWhyToFix("Most IIS do not read the guardian/parent phone, but some do look for a phone here. ");
    }


    if (props.isAddressCleanserEnabled()) {
      this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
      ImplementationDetail id = this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
      id.setImplementationDescription(
          "Patient Guardian Address is invalid according to Smarty Streets.");
      id.setHowToFix("The address for the guardian/parent is not considered a valid address.  Please review the address for all persons connected with this patient and either remove it or update it. ");
      id.setWhyToFix("Guardian/parent address may be used for Reminder/Recall purposes. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived mr) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target.getResponsibleParty() != null) {
      MqeNextOfKin guardian = target.getResponsibleParty();

      String tFirst = guardian.getNameFirst();
      String tLast = guardian.getNameLast();
      String pFirst = target.getNameFirst();
      String pLast = target.getNameLast();

      if (props.isAddressCleanserEnabled()) {
        if (guardian.getAddress() != null && !guardian.getAddress().isClean()) {
          ValidationReport r = Detection.PatientGuardianAddressIsInvalid.build(target);
          List<SmartyStreetResponse> rList =
              SmartyStreetResponse.codesFromDpv(guardian.getAddress().getCleansingResultCode());
          if (rList.size() > 0) {
            StringBuilder b = new StringBuilder(":");
            for (SmartyStreetResponse rz : rList) {
              b.append(" ").append(rz.title);
            }
            r.setAdditionalMessage(b.toString());
          }
          issues.add(r);
        }
      }
      if (guardian.getAddress() != null) {
        if (this.common.isEmpty(guardian.getAddress().getStateCode())) {
          issues.add(Detection.PatientGuardianAddressStateIsMissing.build(target));
        }
        if (this.common.isEmpty(guardian.getAddress().getCity())) {
          issues.add(Detection.PatientGuardianAddressCityIsMissing.build(target));
        }
        if (this.common.isEmpty(guardian.getAddress().getZip())) {
          issues.add(Detection.PatientGuardianAddressZipIsMissing.build(target));
        }
      } else {
        issues.add(Detection.PatientGuardianAddressIsMissing.build(target));
      }

      if (this.common.isEmpty(tFirst)) {
        issues.add(Detection.PatientGuardianNameFirstIsMissing.build(target));
      }
      if (this.common.isEmpty(tLast)) {
        issues.add(Detection.PatientGuardianNameLastIsMissing.build(target));
      }

      if (StringUtils.isNotBlank(pFirst) && StringUtils.isNotBlank(pLast) && pFirst.equals(tFirst)
          && pLast.equals(tLast)) {
        issues.add(Detection.PatientGuardianNameIsSameAsUnderagePatient.build(target));
      }

      if (this.common.isEmpty(guardian.getPhoneNumber())) {
        issues.add(Detection.PatientGuardianPhoneIsMissing.build(target));
      }

      passed = (issues.size() == 0);

    }

    return buildResults(issues, passed);
  }

}
