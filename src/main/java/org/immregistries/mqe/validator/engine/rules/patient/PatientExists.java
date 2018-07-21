package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientExists extends ValidationRule<MqePatient> {

  public PatientExists() {
    ruleDetections.add(Detection.PatientObjectIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target == null) {
      issues.add(Detection.PatientObjectIsMissing.build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }


}
