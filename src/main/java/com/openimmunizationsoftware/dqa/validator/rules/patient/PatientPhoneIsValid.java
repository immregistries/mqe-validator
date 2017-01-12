package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.types.PatientPhone;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.common.PhoneValidator;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class PatientPhoneIsValid extends ValidationRule<Patient> {

	private PhoneValidator phValr = PhoneValidator.INSTANCE;
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		PatientPhone phone = target.getPhone();
		
		List<IssueFound> phIssues = phValr.validatePhone(phone, IssueField.PATIENT_PHONE, IssueField.PATIENT_PHONE_TEL_USE_CODE, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE);
		issues.addAll(phIssues);
		
		if (issues != null && issues.size() > 0) {
			passed = false;
		}
		 
		return buildResults(issues, passed);
	}
	
}
