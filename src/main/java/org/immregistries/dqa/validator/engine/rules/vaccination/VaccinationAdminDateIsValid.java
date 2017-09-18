package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class VaccinationAdminDateIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		String dateString = target.getAdminDateString();

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		if (this.common.isEmpty(dateString)) {
			issues.add(Detection.VaccinationAdminDateIsMissing.build());
			passed = false;

			return buildResults(issues, passed);
		}

		if (!this.common.isValidDate(dateString)) {
			LOGGER.info("Date is not valid: " + dateString);
			issues.add(Detection.VaccinationAdminDateIsInvalid.build(dateString));
			passed = false;
			return buildResults(issues, passed);
		}
		;

		DateTime adminDate = this.datr.parseDateTime(dateString);

		if (this.datr.isAfterDate(adminDate.toDate(), m.getMessageHeader().getMessageDate())) {
			LOGGER.info("Date is not valid: " + dateString);
			issues.add(Detection.VaccinationAdminDateIsAfterMessageSubmitted.build(dateString));
			passed = false;
		}

		// After this, we have a date.
		int dayOfMonth = adminDate.getDayOfMonth();
		
		LocalDate lastDayOfMonth = adminDate.toLocalDate().dayOfMonth().withMaximumValue();
		
		int lastDay = lastDayOfMonth.getDayOfMonth();
		
		if (dayOfMonth == 1) {
			issues.add(Detection.VaccinationAdminDateIsOnFirstDayOfMonth.build(dateString));
		} else if (dayOfMonth == 15) {
			issues.add(Detection.VaccinationAdminDateIsOn15ThDayOfMonth.build(dateString));
		} else if (dayOfMonth == lastDay) {
			issues.add(Detection.VaccinationAdminDateIsOnLastDayOfMonth.build(dateString));
		}

		if (target.isAdministered()) {
			if (target.getExpirationDate() != null) {
				if (datr.isAfterDate(target.getAdminDate(), target.getExpirationDate())) {
					issues.add(Detection.VaccinationAdminDateIsAfterLotExpirationDate.build());
				}
			}
		}

		if (datr.isAfterDate(target.getAdminDate(), m.getReceivedDate())) {
			issues.add(Detection.VaccinationAdminDateIsAfterMessageSubmitted.build());
		}

		if (m.getPatient().getDeathDate() != null) {
			if (datr.isAfterDate(target.getAdminDate(), m.getPatient().getDeathDate())) {
				issues.add(Detection.VaccinationAdminDateIsAfterPatientDeathDate.build());
			}
		}

		if (m.getPatient().getBirthDate() != null) {
			if (datr.isBeforeDate(target.getAdminDate(), m.getPatient().getBirthDate())) {
				issues.add(Detection.VaccinationAdminDateIsBeforeBirth.build());
			}
		}

		if (target.getSystemEntryDate() != null) {
			if (datr.isAfterDate(target.getAdminDate(), target.getSystemEntryDate())) {
				issues.add(Detection.VaccinationAdminDateIsAfterSystemEntryDate.build());
			}
		}

		if (target.getAdminDateEnd() == null) {
			issues.add(Detection.VaccinationAdminDateEndIsMissing.build());
		} else {
			if (!target.getAdminDateEnd().equals(target.getAdminDate())) {
				issues.add(Detection.VaccinationAdminDateEndIsDifferentFromStartDate.build());
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);

	}
}
