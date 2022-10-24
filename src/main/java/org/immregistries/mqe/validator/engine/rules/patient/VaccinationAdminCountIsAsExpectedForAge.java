package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;
import org.joda.time.DateTime;

@ValidationRuleEntry(TargetType.Patient)
public class VaccinationAdminCountIsAsExpectedForAge extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientBirthDateIsValid.class};
  }

  public VaccinationAdminCountIsAsExpectedForAge() {
    super();
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.AdministeredVaccinationsCountIsLargerThanExpected);
      id.setImplementationDescription(
          "Expecting less than 20 vaccinations given before 6 months of age. Expecting less than 30 vaccinations given before 2 years of age.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.AdministeredVaccinationsCountIsTwoVaccinationEventsBySixYears);
      id.setImplementationDescription(
          "Patient received vaccinations on two different dates by the age of six. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    int count = 0;
    int count_of_baby_vaccinations = 0;
    int count_of_toddler_vaccinations = 0;
    int count_of_child_vaccinations = 0;

    DateTime birthDate = new DateTime(target.getBirthDate());
    DateTime _6months = birthDate.plusMonths(6);
    DateTime _2years = birthDate.plusYears(2);
    DateTime _6years = birthDate.plusYears(6);
    
    for (MqeVaccination v : m.getVaccinations()) {
      if (this.common.isValidDate(v.getAdminDateString())) {
        DateTime adminDate = this.datr.parseDateTime(v.getAdminDateString());

        count++;
        if (adminDate.isBefore(_6months)) {
          count_of_baby_vaccinations++;
        }
        if (adminDate.isBefore(_2years)) {
          count_of_toddler_vaccinations++;
        }
        if (adminDate.isBefore(_6years)) {
          count_of_child_vaccinations++;
        }
      }

    }

    if (count_of_baby_vaccinations >= 20 || count_of_toddler_vaccinations >= 30) {
      issues.add(Detection.AdministeredVaccinationsCountIsLargerThanExpected.build(target));
      passed = false;
    }
    
    if (count_of_toddler_vaccinations < 15) {
      issues.add(Detection.AdministeredVaccinationsCountIsLessThanFifteenByTwentyFourMonths.build(target));
    }
    
    if (count == 0)
    {
      issues.add(Detection.AdministeredVaccinationsCountIsZero.build(target));
    }
    
    if (count_of_child_vaccinations >= 2)
    {
      issues.add(Detection.AdministeredVaccinationsCountIsTwoVaccinationEventsBySixYears.build(target));
    }
    
    return buildResults(issues, passed);
  }

}
