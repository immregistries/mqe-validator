package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

public class PatientBirthDateIsReasonable extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class, PatientBirthDateIsValid.class,
        MessageHeaderDateIsValid.class};
  }

  public PatientBirthDateIsReasonable() {
    this.addRuleDetections(Arrays.asList(Detection.PatientBirthDateIsVeryLongAgo,
        Detection.PatientBirthDateIsOn15ThDayOfMonth, Detection.PatientBirthDateIsOnFirstDayOfMonth,
        Detection.PatientBirthDateIsOnLastDayOfMonth, Detection.PatientBirthDateIsAfterSubmission));
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsVeryLongAgo);
      id.setImplementationDescription("Patient is over 120 years old.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsAfterSubmission);
      id.setImplementationDescription(
          "Patient birth date is over 2 hours after the message header date.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientBirthDateIsOnFirstDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the first day of the month.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsOn15ThDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the 15th day of the month.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsOnLastDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the last day of the month.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed = true;

    String birthDateString = target.getBirthDateString();
    DateTime birthDate = new DateTime(target.getBirthDate());

    // in the original validator, the "future" was determined based
    // on when the message is validated... we might need to keep that.
    DateTime messageDate = new DateTime(message.getMessageHeader().getMessageDate());
    messageDate = messageDate.plusHours(2);//This avoids system time clock issues.


    // This is not an error condition... the birth date can still be valid. it's just weird.
    int age = this.datr.getAge(birthDate);
    if (age > 120) {
      issues.add(Detection.PatientBirthDateIsVeryLongAgo.build((birthDateString), target));
      passed = false;
    } else if (birthDate.isAfter(messageDate)) {
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

    return buildResults(issues, passed);
  }
}
