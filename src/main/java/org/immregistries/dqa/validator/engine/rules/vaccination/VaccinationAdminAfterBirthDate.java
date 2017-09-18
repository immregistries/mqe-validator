package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdminAfterBirthDate extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientBirthDateIsValid.class, VaccinationAdminDateIsValid.class};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination v, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		Date adminDate = v.getAdminDate();
		Date birthDate = m.getPatient().getBirthDate();
		
		if (adminDate.before(birthDate)) {
			issues.add(this.util.createIssue(Detection.VaccinationAdminDateIsBeforeBirth, adminDate.toString()));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}

}
