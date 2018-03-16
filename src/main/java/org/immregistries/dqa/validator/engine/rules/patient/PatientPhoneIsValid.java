package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.PhoneValidator;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.DqaPhoneNumber;

public class PatientPhoneIsValid extends ValidationRule<DqaPatient> {

	private PhoneValidator phValr = PhoneValidator.INSTANCE;
	
	public PatientPhoneIsValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE));
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE_TEL_USE_CODE));
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_PHONE_TEL_EQUIP_CODE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		DqaPhoneNumber phone = target.getPhone();
		
		List<ValidationIssue> phIssues = phValr.validatePhone(phone, VxuField.PATIENT_PHONE, VxuField.PATIENT_PHONE_TEL_USE_CODE, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE);
		issues.addAll(phIssues);
		
		if (issues != null && issues.size() > 0) {
			passed = false;
		}
		 
		return buildResults(issues, passed);
	}
	
}
