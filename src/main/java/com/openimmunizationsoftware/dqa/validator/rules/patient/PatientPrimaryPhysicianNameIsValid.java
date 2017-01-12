package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.types.Id;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientPrimaryPhysicianNameIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		Id physician = target.getPhysician();
		
		if (physician != null) {
			if (physician.getName() == null || common.isEmpty(physician.getName())) {
				issues.add(PotentialIssue.PatientPrimaryPhysicianNameIsMissing.build());
				passed = false;
			}
		}
	 
		return buildResults(issues, passed);
	}
	
}
