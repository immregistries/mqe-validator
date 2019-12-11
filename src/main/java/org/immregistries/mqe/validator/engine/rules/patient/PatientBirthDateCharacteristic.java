package org.immregistries.mqe.validator.engine.rules.patient;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderDateIsValid;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientBirthDateCharacteristic extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class, PatientBirthDateIsValid.class};
  }

  public PatientBirthDateCharacteristic() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientBirthDateIsOnFirstDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the first day of the month.");
      id.setHowToFix(
          "There is nothing to fix if the patient was born on the first day of they month.");
      id.setWhyToFix(
          "Sometimes when the exact patient date of birth is not known the first day of the month is choosen. It is important to use the correct date of birth, both for matching purposes and determining the right time for vaccinations.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsOn15ThDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the 15th day of the month.");
      id.setHowToFix(
          "There is nothing to fix if the patient was born on the fifteenth day of they month.");
      id.setWhyToFix(
          "Sometimes when the exact patient date of birth is not known the fifteenth day of the month is choosen. It is important to use the correct date of birth, both for matching purposes and determining the right time for vaccinations.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsOnLastDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the last day of the month.");
      id.setHowToFix(
          "There is nothing to fix if the patient was born on the last day of they month.");
      id.setWhyToFix(
          "Sometimes when the exact patient date of birth is not known the last day of the month is choosen. It is important to use the correct date of birth, both for matching purposes and determining the right time for vaccinations.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed = true;

    String birthDateString = target.getBirthDateString();
    DateTime birthDate = new DateTime(target.getBirthDate());

    // After this, we have a date.
    int dayOfMonth = birthDate.getDayOfMonth();

    LocalDate lastDayOfMonth = birthDate.toLocalDate().dayOfMonth().withMaximumValue();

    int lastDay = lastDayOfMonth.getDayOfMonth();

    if (dayOfMonth == 1) {
      issues.add(Detection.PatientBirthDateIsOnFirstDayOfMonth.build((birthDateString), target));
    } else if (dayOfMonth == 15) {
      issues.add(Detection.PatientBirthDateIsOn15ThDayOfMonth.build((birthDateString), target));
    } else if (dayOfMonth == lastDay) {
      issues.add(Detection.PatientBirthDateIsOnLastDayOfMonth.build((birthDateString), target));
    }

    return buildResults(issues, passed);
  }
}
