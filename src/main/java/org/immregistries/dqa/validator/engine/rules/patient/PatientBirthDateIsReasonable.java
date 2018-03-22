package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.joda.time.DateTime;

public class PatientBirthDateIsReasonable extends ValidationRule<DqaPatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class, PatientBirthDateIsValid.class};
  }

  public PatientBirthDateIsReasonable() {
    this.ruleDetections.add(Detection.PatientBirthDateIsVeryLongAgo);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String birthDateString = target.getBirthDateString();
    DateTime birthDate = this.common.parseDateTimeFrom(birthDateString);

    // This is not an error condition... the birthdate can still be valid. it's just wierd.
    int age = this.datr.getAge(birthDate);
    if (age > 120) {
      issues.add(Detection.PatientBirthDateIsVeryLongAgo.build((birthDateString), target));
    }

    return buildResults(issues, passed);
  }
}
