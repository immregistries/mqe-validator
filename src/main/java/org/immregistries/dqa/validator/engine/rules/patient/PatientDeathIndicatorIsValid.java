package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientDeathIndicatorIsValid extends ValidationRule<DqaPatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[]{PatientExists.class};
  }

  public PatientDeathIndicatorIsValid() {
    ruleDetections.addAll(Arrays.asList(Detection.PatientDeathIndicatorIsMissing,
        Detection.PatientDeathIndicatorIsInconsistent));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String dInd = target.getDeathIndicator();
    String deathDate = target.getDeathDateString();

    if (!this.common.isEmpty(deathDate) && this.common.isEmpty(dInd)) {
      issues.add(Detection.PatientDeathIndicatorIsMissing.build(target));
      passed = false; // should this be a pass???
    } else if (!"Y".equals(dInd)) {
      if (this.common.isNotEmpty(target.getDeathDateString())) {
        issues.add(Detection.PatientDeathIndicatorIsInconsistent.build(dInd, target));
        passed = false;
      }
    }
    // what's considered a pass here?
    // 1. not inconsistent... that's a non-passing problem. but it might be a passing issue on the
    // message as a whole.
    return buildResults(issues, passed);
  }
}
