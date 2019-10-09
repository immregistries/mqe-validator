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
          + "Please verify that the patient email is indicated on the medical record. "
          + "If so, please contact your software vendor and request that email be send in every message when available.  ");
      id.setWhyToFix(
          "The email address may be used by IIS to improve patient matching or for contacting during reminder/recall activities. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientEmailIsInvalid);
      id.setImplementationDescription(
          "Email address is invalid and must contain letters, numbers, '@' and '.'");
      id.setHowToFix("The patient email is not a valid email address. "
          + "Please verify that the patient email is indicated properly on the medical record. ");
      id.setWhyToFix(
          "The email address may be used by IIS to improve patient matching or for contacting during reminder/recall activities. ");
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
