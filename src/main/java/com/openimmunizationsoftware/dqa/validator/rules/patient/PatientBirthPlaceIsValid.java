package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientBirthPlaceIsValid extends ValidationRule<Patient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
		};
	}
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived mr) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String place = target.getBirthPlace();
		
		if (common.isEmpty(place)) {
			issues.add(PotentialIssue.PatientBirthPlaceIsMissing.build());
		}
		
		return buildResults(issues, passed);
	}


}
