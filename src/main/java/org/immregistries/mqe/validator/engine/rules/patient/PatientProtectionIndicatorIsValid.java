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
      id.setHowToFix("The protection indicator is a value that should no longer be sent. "
          + "Please contact your software vendor to update the values sent. ");
      id.setWhyToFix(
          "The protection indicator or consent status indicates how the IIS should retain and share this record. "
              + "The exact meaning of this protection is defined by the IIS. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsInvalid);
      id.setHowToFix("The protection indicator is a value that should not be sent. "
          + "Please contact your software vendor to update the values sent. ");
      id.setWhyToFix(
          "The protection indicator or consent status indicates how the IIS should retain and share this record. "
              + "The exact meaning of this protection is defined by the IIS. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsMissing);
      id.setHowToFix("The protection indicator was not indicated. "
          + "Please update the medical record to indicate the correct protection status or contact your software vendor to request "
          + "that the protection indicator be sent when known for all messages. ");
      id.setWhyToFix(
          "The protection indicator or consent status indicates how the IIS should retain and share this record. "
              + "The exact meaning of this protection is defined by the IIS. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The protection indicator is a value that is unknown. "
          + "Please contact your software vendor to update the values sent. ");
      id.setWhyToFix(
          "The protection indicator or consent status indicates how the IIS should retain and share this record. "
              + "The exact meaning of this protection is defined by the IIS. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsValuedAsYes);
      id.setImplementationDescription("Patient Protection Indicator value is 'Y'.");
      id.setHowToFix(
          "The protection indicator is yes, which means the record should be protected. This does not indicate a problem "
              + "with the record. Nothing to fix. ");
      id.setWhyToFix(
          "The protection indicator or consent status indicates how the IIS should retain and share this record. "
              + "The exact meaning of this protection is defined by the IIS. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientProtectionIndicatorIsValuedAsNo);
      id.setImplementationDescription("Patient Protection Indicator value is 'N'.");
      id.setHowToFix(
          "The protection indicator is no, which means the record does not require special protection. "
              + "This does not indicate a problem with the record. Nothing to fix. ");
      id.setWhyToFix(
          "The protection indicator or consent status indicates how the IIS should retain and share this record. "
              + "The exact meaning of this protection is defined by the IIS. ");
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
