package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;

public class VaccinationCreationDateIsValid extends ValidationRule<MqeVaccination> {



  public VaccinationCreationDateIsValid() {
    super();
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationSystemEntryDateIsInvalid);
      id.setImplementationDescription(
          "Vaccination System Entry date cannot be translated to a date.");
    }
    this.addRuleDetection(Detection.VaccinationSystemEntryDateIsMissing);
    this.addRuleDetection(Detection.VaccinationSystemEntryDateIsPresent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationSystemEntryDateIsInTheFuture);
      id.setImplementationDescription(
          "Vaccination System Entry date is in the future or is after message recieved date.");
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed = false;

    if (target.getSystemEntryDateString() == null || target.getSystemEntryDateString().isEmpty()) {
      issues.add(Detection.VaccinationSystemEntryDateIsMissing.build(target));
    } else {
      issues.add(Detection.VaccinationSystemEntryDateIsPresent.build(target));
      if (this.common.isValidDate(target.getSystemEntryDateString())) {
        DateTime systemEntryDate = this.common.parseDateTimeFrom(target.getSystemEntryDateString());

        if (systemEntryDate.isAfterNow()
            || datr.isBeforeDate(m.getReceivedDate(), systemEntryDate.toDate())) {
          issues.add(Detection.VaccinationSystemEntryDateIsInTheFuture
              .build(target.getSystemEntryDateString(), target));
        } else {
          if (target.getSystemEntryDate() == null) {
            target.setSystemEntryDate(systemEntryDate.toDate());
          }
          passed = true;
        }
      } else {
        issues.add(Detection.VaccinationSystemEntryDateIsInvalid
            .build(target.getSystemEntryDateString(), target));
      }
    }

    return buildResults(issues, passed);
  }

}
