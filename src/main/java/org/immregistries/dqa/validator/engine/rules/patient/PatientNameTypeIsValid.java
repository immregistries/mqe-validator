package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientNameTypeIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (target != null && target.getName() != null) {
			String type = target.getName().getType();
			if (common.isEmpty(type)) {
				issues.add(PotentialIssue.PatientNameTypeCodeIsMissing.build());
			}
			
			//TODO: code received stuff. 
//	    	handleCodeReceived(target.getName().getType(), PotentialIssues.Field.PATIENT_NAME_TYPE_CODE);
		}
		
		return buildResults(issues, passed);
	}
	
}
