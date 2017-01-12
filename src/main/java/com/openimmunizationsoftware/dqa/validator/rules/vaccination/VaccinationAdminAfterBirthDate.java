package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientBirthDateIsValid;

public class VaccinationAdminAfterBirthDate extends ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientBirthDateIsValid.class, VaccinationAdminDateIsValid.class};
	}

	@Override
	protected ValidationRuleResult executeRule(Vaccination v, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		Date adminDate = v.getAdminDate();
		Date birthDate = m.getPatient().getBirthDate();
		
		if (adminDate.before(birthDate)) {
			issues.add(this.util.createIssue(PotentialIssue.VaccinationAdminDateIsBeforeBirth, adminDate.toString()));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}

}
