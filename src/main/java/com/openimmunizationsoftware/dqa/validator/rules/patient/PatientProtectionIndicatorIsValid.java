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

public class PatientProtectionIndicatorIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		String protectionCode = target.getProtectionCode();

		//TODO: QUESTION:  Should "Missing" be handled here, or in the codeHandler?
		//I'm thinking...  here.  No compelling reason yet.  just a feeling. 
		
		if (common.isEmpty(protectionCode)) {
			issues.add(PotentialIssue.buildIssue(IssueField.PATIENT_PROTECTION_INDICATOR, IssueType.MISSING));
			passed = false;
		} else if ("Y".equals(protectionCode)) {
		    issues.add(PotentialIssue.PatientProtectionIndicatorIsValuedAsYes.build());
		    passed = false;
		} else if ("N".equals(protectionCode)) {
			issues.add(PotentialIssue.PatientProtectionIndicatorIsValuedAsNo.build());
			passed = false;
	    }
	 
		return buildResults(issues, passed);
	}
	
}
