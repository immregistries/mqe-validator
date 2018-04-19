package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientMiddleNameIsPresent extends ValidationRule<DqaPatient> {

  public PatientMiddleNameIsPresent() {
    ruleDetections.add(Detection.PatientMiddleNameIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
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