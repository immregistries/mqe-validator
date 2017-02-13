package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.IssueField;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientSubmitterIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String submitterNumStr = target.getIdSubmitterNumber();
		String assignAuthCodeStr = target.getIdSubmitterAssigningAuthorityCode();
		String submitterTypeCdStr = target.getIdSubmitterTypeCode();
		
		if (common.isEmpty(submitterNumStr)) {
			
			issues.add(MessageAttribute.get(IssueField.PATIENT_SUBMITTER_ID, IssueType.MISSING).build());
			passed = false;
			
			if (common.isEmpty(assignAuthCodeStr)) {
				issues.add(MessageAttribute.get(IssueField.PATIENT_SUBMITTER_ID_AUTHORITY, IssueType.MISSING).build());
			}
			
			if (common.isEmpty(submitterTypeCdStr)) {
				issues.add(MessageAttribute.get(IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE, IssueType.MISSING).build());
			}
	    }
		
		return buildResults(issues, passed);
	}
	
}
