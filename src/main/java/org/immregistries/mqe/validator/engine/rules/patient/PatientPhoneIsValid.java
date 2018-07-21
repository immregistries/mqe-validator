package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
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
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE));
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE_TEL_USE_CODE));
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE_TEL_EQUIP_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    MqePhoneNumber phone = target.getPhone();

    issues.addAll(phValr.validatePhone(
                    phone,
                    VxuField.PATIENT_PHONE,
                    VxuField.PATIENT_PHONE_TEL_USE_CODE,
                    VxuField.PATIENT_PHONE_TEL_EQUIP_CODE,
                    target));

    if (issues.size() > 0) {
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
