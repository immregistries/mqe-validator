package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientFinancialStatusDateIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientFinancialStatusCheckTrue.class, PatientBirthDateIsValid.class};
  }

  public PatientFinancialStatusDateIsValid() {
    this.ruleDetections.addAll(Arrays.asList(Detection.PatientVfcEffectiveDateIsBeforeBirth,
        Detection.PatientVfcEffectiveDateIsInFuture, Detection.PatientVfcEffectiveDateIsMissing));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    Date finEligDte = target.getFinancialEligibilityDate();
    Date birthDate = target.getBirthDate();
    Date recDate = m.getReceivedDate();

    if (finEligDte != null) {
      if (this.datr.isBeforeDate(finEligDte, birthDate)) {
        issues.add(Detection.PatientVfcEffectiveDateIsBeforeBirth.build(target));
        passed = false;
      }

      if (this.datr.isBeforeDate(recDate, finEligDte)) {
        issues.add(Detection.PatientVfcEffectiveDateIsInFuture.build(target));
        passed = false;
      }
    } else {
      issues.add(Detection.PatientVfcEffectiveDateIsMissing.build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
