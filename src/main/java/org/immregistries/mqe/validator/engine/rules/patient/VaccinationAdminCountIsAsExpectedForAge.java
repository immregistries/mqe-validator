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
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;

public class VaccinationAdminCountIsAsExpectedForAge extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientBirthDateIsValid.class};
  }

  public VaccinationAdminCountIsAsExpectedForAge() {
    super();
    this.addRuleDetection(Detection.AdministeredVaccinationsCountIsLargerThanExpected);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.AdministeredVaccinationsCountIsLargerThanExpected);
      id.setImplementationDescription(
          "Expecting less than 20 vaccinations given before 6 months of age. Expecting less than 30 vaccinations given before 2 years of age.");
      id.setHowToFix("This record has so many vaccinations that it is assumed to be either a test record or multiple improperly-merged patient records. This record should be reviewed to determine if the data is correct. If it is test information, then this should not be submitted to a production system. If this is an improperly merged record then it should be fixed before submission to an immunization system. ");
      id.setWhyToFix("Production immunization systems depend on accurate data free of test data and badly merged records. Immunization systems actively merge data together and depend on all submitted data accurately reflect what actually happened. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    int count_of_baby_vaccinations = 0;
    int count_of_toddler_vaccinations = 0;

    DateTime birthDate = new DateTime(target.getBirthDate());
    DateTime _6months = birthDate.plusMonths(6);
    DateTime _2years = birthDate.plusYears(2);

    for (MqeVaccination v : m.getVaccinations()) {
      if (this.common.isValidDate(v.getAdminDateString())) {
        DateTime adminDate = this.datr.parseDateTime(v.getAdminDateString());

        if (adminDate.isBefore(_6months)) {
          count_of_baby_vaccinations++;
        }
        if (adminDate.isBefore(_2years)) {
          count_of_toddler_vaccinations++;
        }
      }

    }

    if (count_of_baby_vaccinations > 20 || count_of_toddler_vaccinations > 30) {
      issues.add(Detection.AdministeredVaccinationsCountIsLargerThanExpected.build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
