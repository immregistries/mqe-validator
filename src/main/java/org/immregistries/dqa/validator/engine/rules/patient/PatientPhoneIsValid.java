package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.PhoneValidator;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.DqaPhoneNumber;
import org.immregistries.dqa.vxu.VxuField;

public class PatientPhoneIsValid extends ValidationRule<DqaPatient> {

  private PhoneValidator phValr = PhoneValidator.INSTANCE;

  public PatientPhoneIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE));
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE_TEL_USE_CODE));
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE_TEL_EQUIP_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    DqaPhoneNumber phone = target.getPhone();

    issues.addAll(phValr.validatePhone(phone, VxuField.PATIENT_PHONE,
        VxuField.PATIENT_PHONE_TEL_USE_CODE, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, target));

    if (issues.size() > 0) {
      passed = false;
    }

    return buildResults(issues, passed);
  }

}