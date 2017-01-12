package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.IssueType;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientAliasIsValid extends ValidationRule<Patient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
		};
	}
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = false;
		
		String aliasFirst = target.getAliasFirst();
		String aliasLast = target.getAliasLast();
		
		if (common.isEmpty(aliasLast + aliasFirst)) {
			issues.add(PotentialIssue.buildIssue(IssueField.PATIENT_ALIAS, IssueType.MISSING));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}
}
