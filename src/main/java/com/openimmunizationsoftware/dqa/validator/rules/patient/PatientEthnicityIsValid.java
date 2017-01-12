package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class PatientEthnicityIsValid extends ValidationRule<Patient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
		};
	}
	
	/*
	 * This is the money: 
	 */
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		//TODO: need to figure out what to do with this. 
//	    handleCodeReceived(patient.getEthnicity(), PotentialIssues.Field.PATIENT_ETHNICITY);
//		Does it make sense to keep all of these in the CodesAreValid class?
		
		return buildResults(issues, passed);
	}
}
