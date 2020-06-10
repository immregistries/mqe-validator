package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.common.PhoneValidator;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.immregistries.mqe.vxu.VxuField;

public class PatientPhoneIsValid extends ValidationRule<MqePatient> {

  private PhoneValidator phValr = PhoneValidator.INSTANCE;

  public PatientPhoneIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneIsIncomplete);
      id.setHowToFix("The phone number is missing required information. Please verify the phone number in the medical record "
          + "is complete. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneIsInvalid);
      id.setImplementationDescription(
          "Only validating North American Phone Numbers. Area code must be 3 valid digits (First digit can't be 0 or 1. Can't end with '11'). Local number must be 7 valid digits (First digit can't be 0 or 1. First 3 digits can't be '555'. 2nd and 3rd digits can't both be '1'.).");
      id.setHowToFix("The phone number does not look like a valid phone number. Please verify the phone number in the medical record. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneIsMissing);
      id.setHowToFix("The phone number is not valued. Please verify that the phone number is on the medical record or please "
          + "contact your software vendor to ask that the phone number be sent in all messages when available. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsDeprecated);
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsInvalid);
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsMissing);
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsDeprecated);
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsInvalid);
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsMissing);
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("Please contact your software vendor and ask them to properly encode the phone number. ");
      id.setWhyToFix("The phone number is used for patient matching and for contacting patients during reminder/recall activities. "
          + "Sending the wrong code could confuse the IIS. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    MqePhoneNumber phone = target.getPhone();

    issues.addAll(phValr.validatePhone(phone, VxuField.PATIENT_PHONE,
        VxuField.PATIENT_PHONE_TEL_USE_CODE, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, target));

    if (issues.size() > 0) {
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
