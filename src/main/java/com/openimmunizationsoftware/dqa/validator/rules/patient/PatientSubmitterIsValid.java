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

public class PatientSubmitterIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String submitterNumStr = target.getIdSubmitterNumber();
		String assignAuthCodeStr = target.getIdSubmitterAssigningAuthorityCode();
		String submitterTypeCdStr = target.getIdSubmitterTypeCode();
		
		if (common.isEmpty(submitterNumStr)) {
			
			issues.add(PotentialIssue.get(IssueField.PATIENT_SUBMITTER_ID, IssueType.MISSING).build());
			passed = false;
			
			if (common.isEmpty(assignAuthCodeStr)) {
				issues.add(PotentialIssue.get(IssueField.PATIENT_SUBMITTER_ID_AUTHORITY, IssueType.MISSING).build());
			}
			
			if (common.isEmpty(submitterTypeCdStr)) {
				issues.add(PotentialIssue.get(IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE, IssueType.MISSING).build());
			}
	    }
		
		return buildResults(issues, passed);
	}
	
}
