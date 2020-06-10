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
      id.setHowToFix("The next-of-kin is being indicated with a first name. Please check to see if all next-of-kin associated with the patient are completed properly or contact your software vendor and request that next-of-kin records without a full name be excluded from being sent to the IIS. ");
      id.setWhyToFix("The IIS uses the next-of-kin name for patient matching and reminder/recall activities. An incomplete name can cause confusion and matching mistakes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameLastIsMissing);
      id.setHowToFix("The next-of-kin is being indicated with a last name. Please check to see if all next-of-kin associated with the patient are completed properly or contact your software vendor and request that next-of-kin records without a full name be excluded from being sent to the IIS. ");
      id.setWhyToFix("The IIS uses the next-of-kin name for patient matching and reminder/recall activities. An incomplete name can cause confusion and matching mistakes. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinNameIsMissing);
      id.setImplementationDescription("No value found for both first and last name, no data sent, nothing to analyze. ");
      id.setHowToFix("The next-of-kin is being indicated with a name. Please check to see if all next-of-kin associated with the patient are completed properly or contact your software vendor and request that next-of-kin records without a full name be excluded from being sent to the IIS. ");
      id.setWhyToFix("The IIS uses the next-of-kin name for patient matching and reminder/recall activities. An incomplete name can cause confusion and matching mistakes. ");
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
