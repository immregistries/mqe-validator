package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdminAfterBirthDate extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
	  return new Class[] {PatientBirthDateIsValid.class, VaccinationAdminDateIsValid.class};
  }

  public VaccinationAdminAfterBirthDate() {
    ruleDetections.add(Detection.VaccinationAdminDateIsBeforeBirth);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination v, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    Date adminDate = v.getAdminDate();
    Date birthDate = m.getPatient().getBirthDate();

    if (adminDate.before(birthDate)) {
      issues.add(Detection.VaccinationAdminDateIsBeforeBirth.build(adminDate.toString(), v));
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
