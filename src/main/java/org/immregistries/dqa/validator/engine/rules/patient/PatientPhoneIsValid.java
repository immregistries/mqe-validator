package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.PhoneValidator;
import org.immregistries.dqa.validator.issue.IssueField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.hl7.PhoneNumber;

public class PatientPhoneIsValid extends ValidationRule<DqaPatient> {

	private PhoneValidator phValr = PhoneValidator.INSTANCE;
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		PhoneNumber phone = target.getPhone();
		
		List<ValidationIssue> phIssues = phValr.validatePhone(phone, IssueField.PATIENT_PHONE, IssueField.PATIENT_PHONE_TEL_USE_CODE, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE);
		issues.addAll(phIssues);
		
		if (issues != null && issues.size() > 0) {
			passed = false;
		}
		 
		return buildResults(issues, passed);
	}
	
}
