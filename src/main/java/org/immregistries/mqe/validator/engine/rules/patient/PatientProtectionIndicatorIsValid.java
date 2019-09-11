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
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsDeprecated);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsInvalid);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsMissing);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsUnrecognized);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsValuedAsYes);
      id.setImplementationDescription("Patient Protection Indicator value is 'Y'.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsValuedAsNo);
      id.setImplementationDescription("Patient Protection Indicator value is 'N'.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
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

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
