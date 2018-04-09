package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.joda.time.DateTime;

public class PatientDeathDateIsValid extends ValidationRule<DqaPatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientDeathDateIsValid() {
    ruleDetections.addAll(Arrays.asList(Detection.PatientDeathDateIsMissing,
        Detection.PatientDeathDateIsInvalid, Detection.PatientDeathDateIsInFuture,
        Detection.PatientDeathDateIsBeforeBirth));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
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
