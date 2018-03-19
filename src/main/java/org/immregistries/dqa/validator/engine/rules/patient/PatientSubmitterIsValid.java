package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientSubmitterIsValid extends ValidationRule<DqaPatient> {

	public PatientSubmitterIsValid() {
		ruleDetections.addAll(
				Arrays.asList(
						Detection.get(VxuField.PATIENT_SUBMITTER_ID, IssueType.MISSING),
						Detection.get(VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, IssueType.MISSING),
						Detection.get(VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, IssueType.MISSING)
				)
		);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String submitterNumStr = target.getIdSubmitterNumber();
		String assignAuthCodeStr = target.getIdSubmitterAssigningAuthorityCode();
		String submitterTypeCdStr = target.getIdSubmitterTypeCode();
		
		if (this.common.isEmpty(submitterNumStr)) {
			
			issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID, IssueType.MISSING).build(target));
			passed = false;
			
			if (this.common.isEmpty(assignAuthCodeStr)) {
				issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, IssueType.MISSING).build(target));
			}
			
			if (this.common.isEmpty(submitterTypeCdStr)) {
				issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, IssueType.MISSING).build(target));
			}
	    }
		
		return buildResults(issues, passed);
	}
	
}
