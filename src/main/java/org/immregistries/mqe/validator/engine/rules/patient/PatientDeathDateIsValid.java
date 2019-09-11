package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
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
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientDeathDateIsInvalid);
      id.setImplementationDescription("The death date cannot be translated to a date.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientDeathDateIsInFuture);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientDeathDateIsBeforeBirth);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
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
