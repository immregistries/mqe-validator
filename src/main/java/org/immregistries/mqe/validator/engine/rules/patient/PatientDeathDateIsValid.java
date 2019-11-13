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
import org.joda.time.DateTime;

public class PatientDeathDateIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientDeathDateIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientDeathDateIsMissing);
      id.setImplementationDescription(
          "The death indicator is marked as dead but there is no death date.");
      id.setHowToFix("The patient is indicated as deceased but no death date is indicated. "
          + "Please verify if the medical record includes a death date and if so contact your software vendor "
          + "and request that the death date be encoded in all messages when known. ");
      id.setWhyToFix(
          "It is important for the IIS to know about patients who are now deceased so that reminder/recall "
              + "activities are not attempted and to help the IIS understand the true vaccination rates in a given area "
              + "or population. A death date helps provide assurance of the death status and provide the complete picture "
              + "when verifying data quality. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientDeathDateIsInvalid);
      id.setImplementationDescription("The death date cannot be translated to a date.");
      id.setHowToFix(
          "The patient is indicated as deceased but the death date is not coded properly. "
              + "Please contact your software vendor and request that the death date be encoded properly when known. ");
      id.setWhyToFix(
          "It is important for the IIS to know about patients who are now deceased so that reminder/recall "
              + "activities are not attempted and to help the IIS understand the true vaccination rates in a given area "
              + "or population. A death date helps provide assurance of the death status and provide the complete picture "
              + "when verifying data quality. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientDeathDateIsInFuture);
      id.setHowToFix(
          "The patient is indicated as deceased but the death date is set in the future. "
              + "Please verify if the death date is set correctly and contact your software vendor and request that "
              + "future dates can not be recorded in the medical record nor sent onwards to other systems such as the IIS. ");
      id.setWhyToFix(
          "It is important for the IIS to know about patients who are now deceased so that reminder/recall "
              + "activities are not attempted and to help the IIS understand the true vaccination rates in a given area "
              + "or population. A death date helps provide assurance of the death status and provide the complete picture "
              + "when verifying data quality. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientDeathDateIsBeforeBirth);
      id.setHowToFix(
          "The patient is indicated as deceased but the death date is before the patient was born. "
              + "Please verify if the death date is set correctly and contact your software vendor and request that "
              + "death dates can not be recorded in the medical record as happening before a patient was born. ");
      id.setWhyToFix(
          "It is important for the IIS to know about patients who are now deceased so that reminder/recall "
              + "activities are not attempted and to help the IIS understand the true vaccination rates in a given area "
              + "or population. A death date helps provide assurance of the death status and provide the complete picture "
              + "when verifying data quality. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<>();

    String deathDateString = target.getDeathDateString();

    // if the death date is empty, but the indicator says "Y", that means the date is missing.
    if ("Y".equals(target.getDeathIndicator()) && this.common.isEmpty(deathDateString)) {
      issues.add(Detection.PatientDeathDateIsMissing.build((deathDateString), target));
    } else if (!this.common.isEmpty(deathDateString)) {
      if (!this.common.isValidDate(deathDateString)) {
        // Invalid date string.
        issues.add(Detection.PatientDeathDateIsInvalid.build((deathDateString), target));
      } else {
        // make sure it's before the message date, and after the birth date.
        DateTime deathDate = this.common.parseDateTimeFrom(deathDateString);
        DateTime receivedDate = new DateTime(message.getReceivedDate());
        DateTime birthDate = this.common.parseDateTimeFrom(target.getBirthDateString());

        // if the death date is after the date the message was received
        if (receivedDate.isBefore(deathDate)) {
          issues.add(Detection.PatientDeathDateIsInFuture.build((deathDateString), target));
        }

        // if the death date is before the birth date
        if (deathDate.isBefore(birthDate)) {
          issues.add(Detection.PatientDeathDateIsBeforeBirth.build((deathDateString), target));
        }
      }
    }

    boolean passed = issues.size() <= 0;

    return buildResults(issues, passed);
  }
}
