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
      id.setImplementationDescription("Next of Kin First Name is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameFirstIsPresent);
      id.setImplementationDescription("Next of Kin First Name is indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameLastIsMissing);
      id.setImplementationDescription("Next of Kin Last Name is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameLastIsPresent);
      id.setImplementationDescription("Next of Kin Last Name is indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameIsMissing);
      id.setImplementationDescription("No value found for both first and last name, no data sent, nothing to analyze. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameIsMissing);
      id.setImplementationDescription("Value found for first and/or last name ");
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
      issues.add(Detection.NextOfKinNameIsPresent.build(first + " " + last, target));
      if (this.common.isEmpty(first)) {
        issues.add(Detection.NextOfKinNameFirstIsMissing.build((first), target));
      } else {
        issues.add(Detection.NextOfKinNameFirstIsPresent.build((first), target));
      }

      if (this.common.isEmpty(last)) {
        issues.add(Detection.NextOfKinNameLastIsMissing.build((last), target));
      } else {
        issues.add(Detection.NextOfKinNameLastIsPresent.build((last), target));
      }
    }

    passed = verifyNoIssuesExceptPresent(issues);

    return buildResults(issues, passed);
  }

}
