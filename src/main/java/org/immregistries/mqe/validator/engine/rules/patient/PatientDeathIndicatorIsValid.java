package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientDeathIndicatorIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientDeathIndicatorIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientDeathIndicatorIsMissing);
      id.setImplementationDescription(
          "Patient death date was given but death indicator is missing.");
      id.setHowToFix(
          "The death indicator is not set yet, there is a date of death indicated. Please verify the data in the medical "
              + "record and contact your software vendor and request that when sending death information to never indicate a death "
              + "date without also indicating that the patient is deceased. ");
      id.setWhyToFix(
          "It is important for the IIS to know about patients who are now deceased so that reminder/recall "
              + "activities are not attempted and to help the IIS understand the true vaccination rates in a given area "
              + "or population. Inconsistent messaging may confuse the IIS. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientDeathIndicatorIsInconsistent);
      id.setImplementationDescription(
          "Patient death indicator says not dead but death date is populated.");
      id.setHowToFix(
          "The death indicator is specifically set to NO, yet there is a date of death indicated. "
              + "Please verify the data in the medical "
              + "record and contact your software vendor and request that when sending death information to never indicate a death "
              + "date without also indicating that the patient is deceased. ");
      id.setWhyToFix(
          "It is important for the IIS to know about patients who are now deceased so that reminder/recall "
              + "activities are not attempted and to help the IIS understand the true vaccination rates in a given area "
              + "or population. Inconsistent messaging may confuse the IIS. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String dInd = target.getDeathIndicator();
    String deathDate = target.getDeathDateString();

    if (!this.common.isEmpty(deathDate) && this.common.isEmpty(dInd)) {
      issues.add(Detection.PatientDeathIndicatorIsMissing.build(target));
      passed = false; // should this be a pass???
    } else if (!"Y".equals(dInd)) {
      if (this.common.isNotEmpty(target.getDeathDateString())) {
        issues.add(Detection.PatientDeathIndicatorIsInconsistent.build(dInd, target));
        passed = false;
      }
    }
    // what's considered a pass here?
    // 1. not inconsistent... that's a non-passing problem. but it might be a passing issue on the
    // message as a whole.
    return buildResults(issues, passed);
  }
}
