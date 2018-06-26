package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientMiddleNameIsPresent extends ValidationRule<MqePatient> {

  public PatientMiddleNameIsPresent() {
    ruleDetections.add(Detection.PatientMiddleNameIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String middleName = target.getNameMiddle();

    if (common.isEmpty(middleName)) {
      issues.add(Detection.PatientMiddleNameIsMissing.build(target));
      passed = false;
    }
    return buildResults(issues, passed);
  }


}
