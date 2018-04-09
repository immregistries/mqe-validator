package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class VaccinationAdminDateIsValid extends ValidationRule<DqaVaccination> {

  public VaccinationAdminDateIsValid() {
    this.ruleDetections.addAll(Arrays.asList(Detection.VaccinationAdminDateIsMissing,
        Detection.VaccinationAdminDateIsInvalid,
        Detection.VaccinationAdminDateIsAfterMessageSubmitted,
        Detection.VaccinationAdminDateIsOnFirstDayOfMonth,
        Detection.VaccinationAdminDateIsOn15ThDayOfMonth,
        Detection.VaccinationAdminDateIsOnLastDayOfMonth,
        Detection.VaccinationAdminDateIsAfterLotExpirationDate,
        Detection.VaccinationAdminDateIsAfterPatientDeathDate,
        Detection.VaccinationAdminDateIsBeforeBirth,
        Detection.VaccinationAdminDateIsAfterSystemEntryDate,
        Detection.VaccinationAdminDateEndIsMissing,
        Detection.VaccinationAdminDateEndIsDifferentFromStartDate));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    String dateString = target.getAdminDateString();

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (this.common.isEmpty(dateString)) {
      issues.add(Detection.VaccinationAdminDateIsMissing.build(target));
      passed = false;

      return buildResults(issues, passed);
    }

    if (!this.common.isValidDate(dateString)) {
      LOGGER.info("Date is not valid: " + dateString);
      issues.add(Detection.VaccinationAdminDateIsInvalid.build((dateString), target));
      passed = false;
      return buildResults(issues, passed);
    };

    DateTime adminDate = this.datr.parseDateTime(dateString);

    if (this.datr.isAfterDate(adminDate.toDate(), m.getMessageHeader().getMessageDate())) {
      LOGGER.info("Date is not valid: " + dateString);
      issues.add(Detection.VaccinationAdminDateIsAfterMessageSubmitted.build((dateString), target));
      passed = false;
    }

    // After this, we have a date.
    int dayOfMonth = adminDate.getDayOfMonth();

    LocalDate lastDayOfMonth = adminDate.toLocalDate().dayOfMonth().withMaximumValue();

    int lastDay = lastDayOfMonth.getDayOfMonth();

    if (dayOfMonth == 1) {
      issues.add(Detection.VaccinationAdminDateIsOnFirstDayOfMonth.build((dateString), target));
    } else if (dayOfMonth == 15) {
      issues.add(Detection.VaccinationAdminDateIsOn15ThDayOfMonth.build((dateString), target));
    } else if (dayOfMonth == lastDay) {
      issues.add(Detection.VaccinationAdminDateIsOnLastDayOfMonth.build((dateString), target));
    }

    if (target.isAdministered()) {
      if (target.getExpirationDate() != null) {
        if (datr.isAfterDate(target.getAdminDate(), target.getExpirationDate())) {
          issues.add(Detection.VaccinationAdminDateIsAfterLotExpirationDate.build(target));
        }
      }
    }

    if (datr.isAfterDate(target.getAdminDate(), m.getReceivedDate())) {
      issues.add(Detection.VaccinationAdminDateIsAfterMessageSubmitted.build(target));
    }

    if (m.getPatient().getDeathDate() != null) {
      if (datr.isAfterDate(target.getAdminDate(), m.getPatient().getDeathDate())) {
        issues.add(Detection.VaccinationAdminDateIsAfterPatientDeathDate.build(target));
      }
    }

    if (m.getPatient().getBirthDate() != null) {
      if (datr.isBeforeDate(target.getAdminDate(), m.getPatient().getBirthDate())) {
        issues.add(Detection.VaccinationAdminDateIsBeforeBirth.build(target));
      }
    }

    if (target.getSystemEntryDate() != null) {
      if (datr.isAfterDate(target.getAdminDate(), target.getSystemEntryDate())) {
        issues.add(Detection.VaccinationAdminDateIsAfterSystemEntryDate.build(target));
      }
    }

    if (target.getAdminDateEnd() == null) {
      issues.add(Detection.VaccinationAdminDateEndIsMissing.build(target));
    } else {
      if (!target.getAdminDateEnd().equals(target.getAdminDate())) {
        issues.add(Detection.VaccinationAdminDateEndIsDifferentFromStartDate.build(target));
      }
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);

  }
}
