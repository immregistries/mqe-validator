package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientNameTypeIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		if (target != null && target.getName() != null) {
			CodedEntity type = target.getName().getType();
			if (type == null || common.isEmpty(type.getCode())) {
				issues.add(PotentialIssue.PatientNameTypeCodeIsMissing.build());
			}
			
			//TODO: code received stuff. 
//	    	handleCodeReceived(target.getName().getType(), PotentialIssues.Field.PATIENT_NAME_TYPE_CODE);
		}
		
		return buildResults(issues, passed);
	}
	
}
