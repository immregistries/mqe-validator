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

public class PatientSsnIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String ssn = target.getIdSsnNumber();
		
		if (common.isEmpty(ssn)) {
			issues.add(PotentialIssue.get(IssueField.PATIENT_SSN,  IssueType.MISSING).build());
			passed = false;
		} else if (!isSsnPattern(ssn)) {
			issues.add(PotentialIssue.get(IssueField.PATIENT_SSN,  IssueType.INVALID).build());
			passed = false;
		}
		
		return buildResults(issues, passed);
	}
	
	protected boolean isSsnPattern(String val) {
		if ((val.indexOf("000") == 0) 
		|| (val.indexOf("00", 3) == 3)
		|| (!val.matches("[0-9]{9}")) || (val.equals("123456789"))
		|| val.equals("987654321")
		|| (common.hasTooManyConsecutiveChars(val, 6))) {
			return false;
		}

		return true;
	}
	
}
