package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
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
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsVeryLongAgo);
      id.setImplementationDescription("Patient is over 120 years old.");
      id.setHowToFix("The patient is much older than expected. The assumption is the patient date-of-birth has been recorded "
          + "incorrectly or this does not represent a real person. Please verify the patient's birth date is recorded correctly. "
          + "If it is, and the patient was born in years expected for someone to still be alive, then please contact your "
          + "software vendor to ensure that the date is being encoded properly in the message. If the patient is a test "
          + "patient and you are sending this to a test IIS then please update the test patient to indicate a clinically appropriate "
          + "age and resubmit. If this is being sent to a production IIS interface then please take steps to ensure this data "
          + "is not submitted to the IIS and if it has to submit a request to the IIS to remove this test data from the repository. ");
      id.setWhyToFix("The patient birth date is needed to generate an accurate immunization evaluation and forecast, "
          + "and to match patient records. Incorrect birthdates cause major problems for the quality of data in the IIS. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsAfterSubmission);
      id.setImplementationDescription(
          "Patient birth date is over 2 hours after the message header date.");
      id.setHowToFix("The birth date is indicated as happening after this message was created. "
          + "Please verify the birth date for this patient and the sequence of message creation and submission to the "
          + "IIS in relation to this data of birth. The IIS does not expect to receive advanced notice of a patient's birth. "
          + "There may also be a problem with the system clock that causes the message date to be set to an invalid past date, "
          + "which would cause the birth date to appear to be reported in the future. "
          + "(Please note that the sending system and the receiving IIS do not have to be in the same time zone, the message header date "
          + "does include the time zone so that the IIS can correctly calculate the time in the IIS time zone. As long as the "
          + "sending system has the time zone set correctly and the time setting correctly for the time zone the receiving IIS"
          + "will can adjust the date and time to the IIS time zone. ) ");
      id.setWhyToFix("The IIS only records immunizations that have been given for patients that have already been born. "
          + "The IIS does not record information for patients yet-to-be-born. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientBirthDateIsOnFirstDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the first day of the month.");
      id.setHowToFix("Does not necessarily indicate there is a problem to fix, but sometimes patients are registered "
          + "that have no birth date. This can happen when a birth date is not received or the patient comes from a country "
          + "or culture where exact birth dates are not recorded. In these cases the birth date might be set to the first of "
          + "a given month. This check is done to verify the frequency, which if too high might indicate this is being done. ");
      id.setWhyToFix("The IIS matches patients by birth date and uses it to calculate when vaccinations are due. "
          + "It is critical that the correct birth date is always submitted with patient records. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsOn15ThDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the 15th day of the month.");
      id.setHowToFix("Does not necessarily indicate there is a problem to fix, but sometimes patients are registered "
          + "that have no birth date. This can happen when a birth date is not received or the patient comes from a country "
          + "or culture where exact birth dates are not recorded. In these cases the birth date might be set to the 15th day of "
          + "a given month. This check is done to verify the frequency, which if too high might indicate this is being done. ");
      id.setWhyToFix("The IIS matches patients by birth date and uses it to calculate when vaccinations are due. "
          + "It is critical that the correct birth date is always submitted with patient records. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsOnLastDayOfMonth);
      id.setImplementationDescription("Patient birth date is on the last day of the month.");
      id.setHowToFix("Does not necessarily indicate there is a problem to fix, but sometimes patients are registered "
          + "that have no birth date. This can happen when a birth date is not received or the patient comes from a country "
          + "or culture where exact birth dates are not recorded. In these cases the birth date might be set to the last day of "
          + "a given month. This check is done to verify the frequency, which if too high might indicate this is being done. ");
      id.setWhyToFix("The IIS matches patients by birth date and uses it to calculate when vaccinations are due. "
          + "It is critical that the correct birth date is always submitted with patient records. ");
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
