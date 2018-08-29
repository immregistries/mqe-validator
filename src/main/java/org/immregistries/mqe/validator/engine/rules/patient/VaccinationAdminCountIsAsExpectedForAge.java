package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;

public class VaccinationAdminCountIsAsExpectedForAge extends ValidationRule<MqePatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { PatientBirthDateIsValid.class };
	}

	public VaccinationAdminCountIsAsExpectedForAge() {
		super();
		this.ruleDetections.add(Detection.AdministredVaccinationsCountIsLargerThanExpected);
	}

	@Override
	protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
		List<ValidationReport> issues = new ArrayList<ValidationReport>();
		boolean passed = true;

		int count_of_baby_vaccinations = 0;
		int count_of_toddler_vaccinations = 0;

		DateTime birthDate = new DateTime(target.getBirthDate());
		DateTime _6months = birthDate.plusMonths(6);
		DateTime _2years = birthDate.plusYears(2);

		for (MqeVaccination v : m.getVaccinations()) {
			if (this.common.isValidDate(v.getAdminDateString())) {
				DateTime adminDate = this.datr.parseDateTime(v.getAdminDateString());
				
				if (adminDate.isBefore(_6months)) {
					count_of_baby_vaccinations++;
				}
				if (adminDate.isBefore(_2years)) {
					count_of_toddler_vaccinations++;
				}
			}

		}

		if (count_of_baby_vaccinations > 20 || count_of_toddler_vaccinations > 30) {
			issues.add(Detection.AdministredVaccinationsCountIsLargerThanExpected.build(target));
			passed = false;
		}

		return buildResults(issues, passed);
	}

}
