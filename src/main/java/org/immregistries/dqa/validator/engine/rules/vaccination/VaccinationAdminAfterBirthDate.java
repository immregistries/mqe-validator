package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdminAfterBirthDate extends ValidationRule<DqaVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientBirthDateIsValid.class, VaccinationAdminDateIsValid.class};
  }

  public VaccinationAdminAfterBirthDate() {
    ruleDetections.add(Detection.VaccinationAdminDateIsBeforeBirth);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination v, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    Date adminDate = v.getAdminDate();
    Date birthDate = m.getPatient().getBirthDate();

    if (adminDate.before(birthDate)) {
      issues.add(this.util.createIssue(Detection.VaccinationAdminDateIsBeforeBirth,
          adminDate.toString()));
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
