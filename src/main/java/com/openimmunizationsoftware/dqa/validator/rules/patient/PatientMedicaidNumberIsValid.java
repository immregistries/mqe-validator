package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.types.PatientIdNumber;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientMedicaidNumberIsValid extends ValidationRule<Patient> {

	//Because of this, we'll skip this rule if there is no patient object. 
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class};
	}
	
	
	/*
	 * This is the money: 
	 */
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		//what should I be doing when the target is empty...  no issues, and no pass for this guy I guess...
		//Could just bypass this by requiring the patient to exist. 
		if (target == null) {
			return buildResults(issues, false);
		}
		
		PatientIdNumber id = target.getIdMedicaid();
		
	    if (id == null || common.isEmpty(id.getNumber())) {
	    	issues.add(PotentialIssue.PatientMedicaidNumberIsMissing.build());
	    	passed = false;
	    } else if (!common.isValidIdentifier(id.getNumber(), 9)) {
	      issues.add(PotentialIssue.PatientMedicaidNumberIsInvalid.build(id.getNumber()));
	      passed = false;
	    }

		return buildResults(issues, passed);
	}
}
