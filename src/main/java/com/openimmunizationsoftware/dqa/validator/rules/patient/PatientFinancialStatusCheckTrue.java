package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class PatientFinancialStatusCheckTrue extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
//	    if (!ksm.getKeyedValueBoolean(KeyedSetting.VALIDATE_PATIENT_FINANCIAL_STATUS_IGNORE, true))
		
		//TODO:  figure out how to make this aware of the system settings. 
		//for now, it will always be true;
		
		return buildResults(issues, passed);
	}
	
}
