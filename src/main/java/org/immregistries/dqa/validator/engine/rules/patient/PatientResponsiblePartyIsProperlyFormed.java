package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.IssueField;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientResponsiblePartyIsProperlyFormed extends ValidationRule<DqaNextOfKin> {
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {MessageHasResponsibleParty.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived mr) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		DqaPatient patient = mr.getPatient();
		
		if (patient.getResponsibleParty() != null) {
			
			String pFirst = patient.getNameFirst();
			String pLast  = patient.getNameLast();
			String tFirst = target.getNameFirst();
			String tLast  = target.getNameLast();
		        
	    	this.util.addToListIfEmpty(target.getAddress().getStateCode(), 	IssueField.PATIENT_GUARDIAN_ADDRESS_STATE, issues);
	    	this.util.addToListIfEmpty(target.getAddress().getCity(), 		IssueField.PATIENT_GUARDIAN_ADDRESS_CITY, issues);
	    	this.util.addToListIfEmpty(target.getAddress().getZip(), 			IssueField.PATIENT_GUARDIAN_ADDRESS_ZIP, issues);
	    	this.util.addToListIfEmpty(target.getNameFirst(),  MessageAttribute.PatientGuardianNameFirstIsMissing, issues);
	    	this.util.addToListIfEmpty(target.getNameLast(),   MessageAttribute.PatientGuardianNameLastIsMissing, issues);
	    	
			if (StringUtils.isEmpty(tFirst) || StringUtils.isEmpty(tLast)) {
				issues.add(MessageAttribute.PatientGuardianNameIsMissing.build());
			}

			if (pFirst != null && !pFirst.equals("") && pLast != null && !pLast.equals("")) {
				if (pFirst.equals(tFirst) && pLast.equals(tLast)) {
					issues.add(MessageAttribute.PatientGuardianNameIsSameAsUnderagePatient.build());
				}
			}

			this.util.addToListIfEmpty(target.getPhoneNumber(), MessageAttribute.PatientGuardianPhoneIsMissing, issues);
			this.util.addToListIfEmpty(target.getRelationshipCode(), MessageAttribute.PatientGuardianRelationshipIsMissing, issues);
			
			passed = (issues.size() == 0);

		} else {
			// Shouldn't the responsible party be present???  This didn't raise any issues in the original code.  I'm not sure why.
			issues.add(MessageAttribute.PatientGuardianResponsiblePartyIsMissing.build());
			passed = false;
		}

		return buildResults(issues, passed);
	}

}
