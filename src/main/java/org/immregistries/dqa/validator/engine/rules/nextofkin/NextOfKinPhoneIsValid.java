package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.PhoneValidator;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.VxuField;

public class NextOfKinPhoneIsValid extends ValidationRule<DqaNextOfKin> {

  private PhoneValidator phoneValidator = PhoneValidator.INSTANCE;

  public NextOfKinPhoneIsValid() {
    this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_PHONE));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    issues.addAll(phoneValidator.validatePhone(target.getPhone(), VxuField.NEXT_OF_KIN_PHONE,
        target));

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
