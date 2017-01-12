package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientFinancialStatusDateIsValid extends ValidationRule<Patient> {
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
				PatientFinancialStatusCheckTrue.class,
				PatientBirthDateIsValid.class};
	}

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		Date finEligDte = target.getFinancialEligibilityDate();
		Date birthDate = target.getBirthDate();
		Date recDate = m.getReceivedDate();
		
		if (finEligDte != null) {

			if (this.datr.isBeforeDate(finEligDte, birthDate)) // finEligDte.before(trunc(birthDate)))
			{
				issues.add(PotentialIssue.PatientVfcEffectiveDateIsBeforeBirth.build());
				passed = false;
			}

			if (this.datr.isBeforeDate(recDate,  finEligDte)) {
				issues.add(PotentialIssue.PatientVfcEffectiveDateIsInFuture.build());
				passed = false;
			}
		}

		return buildResults(issues, passed);
	}

}
