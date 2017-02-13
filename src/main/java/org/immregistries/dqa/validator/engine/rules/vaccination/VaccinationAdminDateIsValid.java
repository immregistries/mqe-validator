package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
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
			issues.add(MessageAttribute.VaccinationAdminDateIsMissing.build());
			passed = false;

			return buildResults(issues, passed);
		}

		if (!this.common.isValidDate(dateString)) {
			LOGGER.info("Date is not valid: " + dateString);
			issues.add(MessageAttribute.VaccinationAdminDateIsInvalid.build(dateString));
			passed = false;
			return buildResults(issues, passed);
		}
		;

		DateTime adminDate = this.datr.parseDateTime(dateString);

		if (this.datr.isAfterDate(adminDate.toDate(), m.getMessageHeader().getMessageDate())) {
			LOGGER.info("Date is not valid: " + dateString);
			issues.add(MessageAttribute.VaccinationAdminDateIsAfterMessageSubmitted.build(dateString));
			passed = false;
		}

		// After this, we have a date.
		int month = adminDate.getDayOfMonth();
		
		LocalDate lastDayOfMonth = adminDate.toLocalDate().dayOfMonth().withMaximumValue();
		
		int lastDay = lastDayOfMonth.getDayOfMonth();
		
		if (month == 1) {
			issues.add(MessageAttribute.VaccinationAdminDateIsOnFirstDayOfMonth.build(dateString));
		} else if (month == 15) {
			issues.add(MessageAttribute.VaccinationAdminDateIsOn15ThDayOfMonth.build(dateString));
		} else if (month == lastDay) {
			issues.add(MessageAttribute.VaccinationAdminDateIsOnLastDayOfMonth.build(dateString));
		}

		if (target.isAdministered()) {
			if (target.getExpirationDate() != null) {
				if (datr.isAfterDate(target.getAdminDate(), target.getExpirationDate())) {
					issues.add(MessageAttribute.VaccinationAdminDateIsAfterLotExpirationDate.build());
				}
			}
		}

		if (datr.isAfterDate(target.getAdminDate(), m.getReceivedDate())) {
			issues.add(MessageAttribute.VaccinationAdminDateIsAfterMessageSubmitted.build());
		}

		if (m.getPatient().getDeathDate() != null) {
			if (datr.isAfterDate(target.getAdminDate(), m.getPatient().getDeathDate())) {
				issues.add(MessageAttribute.VaccinationAdminDateIsAfterPatientDeathDate.build());
			}
		}

		if (m.getPatient().getBirthDate() != null) {
			if (datr.isBeforeDate(target.getAdminDate(), m.getPatient().getBirthDate())) {
				issues.add(MessageAttribute.VaccinationAdminDateIsBeforeBirth.build());
			}
		}

		if (target.getSystemEntryDate() != null) {
			if (datr.isAfterDate(target.getAdminDate(), target.getSystemEntryDate())) {
				issues.add(MessageAttribute.VaccinationAdminDateIsAfterSystemEntryDate.build());
			}
		}

		if (target.getAdminDateEnd() == null) {
			issues.add(MessageAttribute.VaccinationAdminDateEndIsMissing.build());
		} else {
			if (!target.getAdminDateEnd().equals(target.getAdminDate())) {
				issues.add(MessageAttribute.VaccinationAdminDateEndIsDifferentFromStartDate.build());
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);

	}
}
