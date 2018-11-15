package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.common.PhoneValidator;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.VxuField;

public class NextOfKinPhoneIsValid extends ValidationRule<MqeNextOfKin> {

  private PhoneValidator phoneValidator = PhoneValidator.INSTANCE;

  public NextOfKinPhoneIsValid() {
    this.addRuleDocumentation(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_PHONE));
    this.addImplementationMessage(Detection.NextOfKinPhoneNumberIsIncomplete, "Next of kin phone number is missing area code or local number.");
    this.addImplementationMessage(Detection.NextOfKinPhoneNumberIsInvalid, "Phone number is invalid according to the North American Numbering Plan (NANP).");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    issues.addAll(phoneValidator.validatePhone(target.getPhone(), VxuField.NEXT_OF_KIN_PHONE,
        target));

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
