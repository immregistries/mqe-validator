package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientEmailIsPresent extends ValidationRule<MqePatient> {

  public PatientEmailIsPresent() {
    this.addRuleDetection(Detection.PatientEmailIsMissing);
    this.addRuleDetection(Detection.PatientEmailIsInvalid);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientEmailIsMissing);
      id.setHowToFix("The patient email is missing. "
          + "Add the patient’s valid email address if known. "
          + "If so, please contact your software vendor and request that email be send in every message when available.  ");
      id.setWhyToFix(
          "Submitting a valid email addresses is important and can be used for various purposes, "
              + "including verification for IIS patient portal user requests.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientEmailIsInvalid);
      id.setImplementationDescription(
          "Email address is invalid and must contain letters, numbers, '@' and '.'");
      id.setHowToFix(
          "Review and correct the patient’s email address ensuring it meets email format standards. ");
      id.setWhyToFix(
          "Submitting a valid email addresses is important and can be used for various purposes, "
              + "including verification for IIS patient portal user requests. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String patientEmail = target.getEmail();

    if (this.common.isEmpty(patientEmail)) {
      issues.add(Detection.PatientEmailIsMissing.build(target));
    } else {
      String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@"
          + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

      Pattern pat = Pattern.compile(emailRegex);
      if (!pat.matcher(patientEmail).matches()) {
        issues.add(Detection.PatientEmailIsInvalid.build(target));
      }
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);
  }

}
