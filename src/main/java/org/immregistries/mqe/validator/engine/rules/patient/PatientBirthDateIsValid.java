package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class PatientBirthDateIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientBirthDateIsValid() {
    this.ruleDetections.addAll(Arrays.asList(Detection.PatientBirthDateIsMissing,
        Detection.PatientBirthDateIsInvalid, Detection.PatientBirthDateIsInFuture,
        Detection.PatientBirthDateIsOn15ThDayOfMonth, Detection.PatientBirthDateIsOnFirstDayOfMonth,
        Detection.PatientBirthDateIsOnLastDayOfMonth,
        Detection.PatientBirthDateIsAfterSubmission));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String birthDateString = target.getBirthDateString();

    if (this.common.isEmpty(birthDateString)) {
      issues.add(Detection.PatientBirthDateIsMissing.build((birthDateString), target));
      passed = false;
    } else if (!this.common.isValidDate(birthDateString)) {
      issues.add(Detection.PatientBirthDateIsInvalid.build((birthDateString), target));
      passed = false;
    } else {
      DateTime birthDate = this.common.parseDateTimeFrom(birthDateString);

      // in the original validator, the "future" was determined based
      // on when the message is validated... we might need to keep that.
      DateTime receivedDate = new DateTime(message.getReceivedDate());

      if (receivedDate != null && receivedDate.isBefore(birthDate)) {
        issues.add(Detection.PatientBirthDateIsInFuture.build((birthDateString), target));
        passed = false;
      }

      DateTime messageDate = new DateTime(message.getMessageHeader().getMessageDate());

      if (messageDate != null && messageDate.isBefore(birthDate)) {
        issues.add(Detection.PatientBirthDateIsAfterSubmission.build((birthDateString), target));
        passed = false;
      }
      
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

    }

    return buildResults(issues, passed);
  }

}
