package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientProtectionIndicatorIsValid extends ValidationRule<MqePatient> {

  public PatientProtectionIndicatorIsValid() {
    this.addRuleDetection(Detection.PatientProtectionIndicatorIsDeprecated);
    this.addRuleDetection(Detection.PatientProtectionIndicatorIsInvalid);
    this.addRuleDetection(Detection.PatientProtectionIndicatorIsMissing);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsValuedAsYes);
      id.setImplementationDescription("Patient Protection Indicator value is 'Y'.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsValuedAsNo);
      id.setImplementationDescription("Patient Protection Indicator value is 'N'.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    // TODO: need to create test for protection indicator
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    String protectionCode = target.getProtectionCode();

    if (StringUtils.isEmpty(protectionCode)) {
      issues.add(Detection.PatientProtectionIndicatorIsMissing.build(target));
    } else {

      issues.addAll(
          this.codr.handleCode(protectionCode, VxuField.PATIENT_PROTECTION_INDICATOR, target));

      if ("Y".equals(protectionCode)) {
        issues
            .add(Detection.PatientProtectionIndicatorIsValuedAsYes.build((protectionCode), target));
      } else if ("N".equals(protectionCode)) {
        issues
            .add(Detection.PatientProtectionIndicatorIsValuedAsNo.build((protectionCode), target));
      }
    }

    passed = verifyPassed(issues);

    return buildResults(issues, passed);
  }

}
