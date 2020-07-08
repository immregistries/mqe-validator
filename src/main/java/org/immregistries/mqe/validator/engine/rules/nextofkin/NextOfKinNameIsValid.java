package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;

public class NextOfKinNameIsValid extends ValidationRule<MqeNextOfKin> {

  public NextOfKinNameIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameFirstIsMissing);
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameLastIsMissing);
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameIsMissing);
      id.setImplementationDescription("No value found for both first and last name, no data sent, nothing to analyze. ");
    }
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
