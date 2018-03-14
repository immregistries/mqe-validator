package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientNameTypeIsValid extends ValidationRule<DqaPatient> {
	
	public PatientNameTypeIsValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.PatientNameTypeCodeIsMissing
		));
	}

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (target != null && target.getName() != null) {
			String type = target.getName().getType();
			if (common.isEmpty(type)) {
				issues.add(Detection.PatientNameTypeCodeIsMissing.build());
			}
			
			//TODO: code received stuff. 
//	    	handleCodeReceived(target.getName().getType(), PotentialIssues.Field.PATIENT_NAME_TYPE_CODE);
		}
		
		return buildResults(issues, passed);
	}
	
}
