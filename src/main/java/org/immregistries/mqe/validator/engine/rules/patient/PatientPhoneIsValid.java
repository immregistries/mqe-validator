package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.common.PhoneValidator;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.*;

@ValidationRuleEntry(TargetType.Patient)
public class PatientPhoneIsValid extends ValidationRule<MqePatient> {

  private PhoneValidator phValr = PhoneValidator.INSTANCE;

  public PatientPhoneIsValid() {
    this.addRuleDetection(Detection.PatientPhoneIsIncomplete);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPhoneIsInvalid);
      id.setImplementationDescription(
          "Only validating North American Phone Numbers. Area code must be 3 valid digits (First digit can't be 0 or 1. Can't end with '11'). Local number must be 7 valid digits (First digit can't be 0 or 1. First 3 digits can't be '555'. 2nd and 3rd digits can't both be '1'.).");
    }
    this.addRuleDetection(Detection.PatientPhoneIsMissing);
    this.addRuleDetection(Detection.PatientPhoneIsPresent);
    this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsDeprecated);
    this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsInvalid);
    this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsMissing);
    this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsPresent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientPhoneTelUseCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsDeprecated);
    this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsInvalid);
    this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsMissing);
    this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsPresent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientPhoneTelEquipCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
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
