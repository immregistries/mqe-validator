package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientIsUnderage extends ValidationRule<Patient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
			PatientBirthDateIsValid.class
		};
	}
	
	/*
	 * This is the money: 
	 */
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = false;
		
		if (/* protect first */target != null && target.getBirthDate() != null
				&& m.getMessageHeader() != null
				&& m.getMessageHeader().getMessageDate() != null) {
			
			DateTime eighteenYearsFromSubmission = new DateTime(m.getMessageHeader().getMessageDate().getTime()).minusYears(18);
			
			if (/* patient is underage */eighteenYearsFromSubmission.isBefore(target.getBirthDate().getTime())) {
				issues.add(PotentialIssue.PatientBirthDateIsUnderage.build(target.getBirthDate().toString()));
				passed = true;
			}
		}
		
		return buildResults(issues, passed);
	}
}
