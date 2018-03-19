package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

public class PatientFinancialStatusCheckTrue extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
//	    if (!ksm.getKeyedValueBoolean(KeyedSetting.VALIDATE_PATIENT_FINANCIAL_STATUS_IGNORE, true))
		
		//TODO:  figure out how to make this aware of the system settings. 
		//for now, it will always be true;
		
		return buildResults(issues, passed);
	}
	
}
