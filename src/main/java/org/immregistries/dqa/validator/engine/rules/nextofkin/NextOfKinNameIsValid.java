package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;

public class NextOfKinNameIsValid extends ValidationRule<DqaNextOfKin> {

  public NextOfKinNameIsValid() {
    this.ruleDetections.addAll(Arrays.asList(Detection.NextOfKinNameIsMissing,
        Detection.NextOfKinNameFirstIsMissing, Detection.NextOfKinNameLastIsMissing));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed;

    String first = target.getNameFirst();
    String last = target.getNameLast();

    if (this.common.isEmpty(first) && this.common.isEmpty(last)) {
      issues.add(Detection.NextOfKinNameIsMissing.build(first + " " + last, target));
    } else {
      if (this.common.isEmpty(first)) {
        issues.add(Detection.NextOfKinNameFirstIsMissing.build((first), target));
      }

      if (this.common.isEmpty(last)) {
        issues.add(Detection.NextOfKinNameLastIsMissing.build((last), target));
      }
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
