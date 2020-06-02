package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

/**
 * Covers several cases. Looks like a catchall for codes that don't have a separate rule
 */
public class PatientCodesAreValid extends ValidationRule<MqePatient> {

  public PatientCodesAreValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPrimaryLanguageIsMissing);
      id.setHowToFix("Verify that the patient has a primary or contact language indicated. "
          + "This is the preferred language that should be used when contacting the patient or the patient's guardian/parent. "
          + "If this is correctly indicated on the patient medical record then please contact your software vendor and request "
          + "that this be designated in the HL7 message. ");
      id.setWhyToFix(
          "IIS or IIS users may contact patients and guardian/parents to remind them about immunizations due. "
              + "For accessibility regulations it is important to ensure that this information is sent in a language "
              + "that can be understood. Indicating that language can help immunization outreach work more effectively. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientPrimaryLanguageIsUnrecognized);
      id.setHowToFix(
          "Please contact your software vendor and request that they ensure that patient languages are being sent "
              + "with the correct code. ");
      id.setWhyToFix(
          "IIS or IIS users may contact patients and guardian/parents to remind them about immunizations due. "
              + "For accessibility regulations it is important to ensure that this information is sent in a language "
              + "that can be understood. Indicating that language can help immunization outreach work more effectively. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPublicityCodeIsMissing);
      id.setHowToFix(
          "The publicity code, which indicates what types of reminder/recall should be attempted, is not indicated. "
              + "Please verify this is set in the medical record and if it is contact your software vendor to request that it be "
              + "included in messages about this patient. ");
      id.setWhyToFix(
          "Reminder/recall helps patients keep up-to-date on immunizations and reduce missed opportunities. "
              + "Indicating the publicity code will help the IIS and IIS users know what types of contacts are authorized to be made. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPublicityCodeIsUnrecognized);
      id.setHowToFix(
          "The publicity code, which indicates what types of reminder/recall should be attempted, is not coded properly. "
              + "Please contact your software vendor to request that the publicity code be "
              + "coded properly.");
      id.setWhyToFix(
          "Reminder/recall helps patients keep up-to-date on immunizations and reduce missed opportunities. "
              + "Indicating the publicity code will help the IIS and IIS users know what types of contacts are authorized to be made. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPublicityCodeIsInvalid);
      id.setHowToFix(
          "The publicity code, which indicates what types of reminder/recall should be attempted, is not coded properly. "
              + "Please contact your software vendor to request that the publicity code be "
              + "coded properly.");
      id.setWhyToFix(
          "Reminder/recall helps patients keep up-to-date on immunizations and reduce missed opportunities. "
              + "Indicating the publicity code will help the IIS and IIS users know what types of contacts are authorized to be made. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsMissing);
      id.setHowToFix(
          "Patient race is not indicated. Please verify that this is coded in the medical record. "
              + "If it is, then contact your software vendor and request that it be coded in the message. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating race helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsDeprecated);
      id.setHowToFix("Patient race is indicated with an old code. "
          + "Please contact your software vendor and request that the race codes be updated to the current ones. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating race helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. Sending the correct race code will ensure the IIS properly receives this information. ");
    }

    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsUnrecognized);
      id.setHowToFix("Patient race is indicated with a code that is not recognized. "
          + "Please contact your software vendor and request that the race codes be updated to the current ones. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating race helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. Sending the correct race code will ensure the IIS properly receives this information. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsInvalid);
      id.setHowToFix("Patient race is indicated with a code that should no longer be used. "
          + "Please contact your software vendor and request that the race codes be updated to the current ones. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating race helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. Sending the correct race code will ensure the IIS properly receives this information. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsDeprecated);
      id.setHowToFix("The funding eligibility code at the patient level is a code that should no longer be used. "
          + "Please contact your software vendor and request that correct funding eligibility codes are sent for the patient. ");
      id.setWhyToFix("Vaccine programs must know the funding eligibility of patients and their vaccinations in order to "
          + "ensure that vaccination programs receive continued funding. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsInvalid);
      id.setHowToFix("The funding eligibility code at the patient level is a code that should not be used. "
          + "Please contact your software vendor and request that correct funding eligibility codes are sent for the patient.  ");
      id.setWhyToFix("Vaccine programs must know the funding eligibility of patients and their vaccinations in order to "
          + "ensure that vaccination programs receive continued funding. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsMissing);
      id.setHowToFix("The funding eligibility code at the patient level was not specified.  ");
      id.setWhyToFix("Vaccine programs must know the funding eligibility of patients and their vaccinations in order to "
          + "ensure that vaccination programs receive continued funding. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsUnrecognized);
      id.setHowToFix("The funding eligibility code at the patient level is a code that is not understood. "
          + "Please contact your software vendor and request that correct funding eligibility codes are sent for the patient.  ");
      id.setWhyToFix("Vaccine programs must know the funding eligibility of patients and their vaccinations in order to "
          + "ensure that vaccination programs receive continued funding. ");
    }

    ///TODO ADD HowToFix and WhyToFix
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPrimaryFacilityIdIsMissing);
    }

    ///TODO ADD HowToFix and WhyToFix
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPrimaryFacilityNameIsMissing);
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    if (target == null) {
      return buildResults(issues, false);
    }

    // facility
    String facilityId = target.getFacilityIdNumber();
    String facilityName = target.getFacilityName();

    if (this.common.isEmpty(facilityId)) {
      issues.add(Detection.PatientPrimaryFacilityIdIsMissing.build((facilityId), target));
    }

    if (this.common.isEmpty(facilityName)) {
      issues.add(Detection.PatientPrimaryFacilityNameIsMissing.build((facilityName), target));
    }

    // language
    issues.addAll(this.codr.handleCodeOrMissing(target.getPrimaryLanguage(),
        VxuField.PATIENT_PRIMARY_LANGUAGE, target));

    // physician
    // TODO: primary physician isn't in the list of validation rules at all...
    // issues.addAll(this.codr.handleCode(target.getPhysician().getFormattedNumber(),
    // VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, target));

    // publicity code
    issues.addAll(this.codr.handleCodeOrMissing(target.getPublicity(),
        VxuField.PATIENT_PUBLICITY_CODE, target));

    // race
    issues.addAll(this.codr.handleCodeOrMissing(target.getRace(), VxuField.PATIENT_RACE, target));

    // VFC/financial eligibility status
    issues.addAll(this.codr.handleCodeOrMissing(target.getFinancialEligibility(),
        VxuField.PATIENT_VFC_STATUS, target));

    // mark passed if there's no issues
    passed = (issues.size() == 0);
    return buildResults(issues, passed);
  }

}
