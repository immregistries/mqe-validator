package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;

public class NextOfKinNameIsValid extends ValidationRule<MqeNextOfKin> {

  public NextOfKinNameIsValid() {
    this.addRuleDocumentation(Arrays.asList(Detection.NextOfKinNameIsMissing,
        Detection.NextOfKinNameFirstIsMissing, Detection.NextOfKinNameLastIsMissing));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {

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
