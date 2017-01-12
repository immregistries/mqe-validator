package com.openimmunizationsoftware.dqa.validator.rules.respparty;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class ResponsiblePartyIsProperlyFormed extends ValidationRule<NextOfKin> {
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {ResponsiblePartyExists.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(NextOfKin target, MessageReceived mr) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		Patient patient = mr.getPatient();
		
		if (patient.getResponsibleParty() != null) {
			
			String pFirst = patient.getNameFirst();
			String pLast  = patient.getNameLast();
			String tFirst = target.getNameFirst();
			String tLast  = target.getNameLast();
		        
	    	this.util.addToListIfEmpty(target.getAddress().getStateCode(), 	IssueField.PATIENT_GUARDIAN_ADDRESS_STATE, issues);
	    	this.util.addToListIfEmpty(target.getAddress().getCity(), 		IssueField.PATIENT_GUARDIAN_ADDRESS_CITY, issues);
	    	this.util.addToListIfEmpty(target.getAddress().getZip(), 			IssueField.PATIENT_GUARDIAN_ADDRESS_ZIP, issues);
	    	this.util.addToListIfEmpty(target.getNameFirst(),  PotentialIssue.PatientGuardianNameFirstIsMissing, issues);
	    	this.util.addToListIfEmpty(target.getNameLast(),   PotentialIssue.PatientGuardianNameLastIsMissing, issues);
	    	
			if (StringUtils.isEmpty(tFirst) || StringUtils.isEmpty(tLast)) {
				issues.add(PotentialIssue.PatientGuardianNameIsMissing.build());
			}

			if (pFirst != null && !pFirst.equals("") && pLast != null && !pLast.equals("")) {
				if (pFirst.equals(tFirst) && pLast.equals(tLast)) {
					issues.add(PotentialIssue.PatientGuardianNameIsSameAsUnderagePatient.build());
				}
			}

			this.util.addToListIfEmpty(target.getPhoneNumber(), PotentialIssue.PatientGuardianPhoneIsMissing, issues);
			this.util.addToListIfEmpty(target.getRelationshipCode(), PotentialIssue.PatientGuardianRelationshipIsMissing, issues);
			
			passed = (issues.size() == 0);

		} else {
			// Shouldn't the responsible party be present???  This didn't raise any issues in the original code.  I'm not sure why.
			issues.add(PotentialIssue.PatientGuardianResponsiblePartyIsMissing.build());
			passed = false;
		}

		return buildResults(issues, passed);
	}

}
