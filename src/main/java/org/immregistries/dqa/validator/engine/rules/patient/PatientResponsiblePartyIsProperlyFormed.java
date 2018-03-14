package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientResponsiblePartyIsProperlyFormed extends ValidationRule<DqaNextOfKin> {
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {MessageHasResponsibleParty.class};
	}
	
	public PatientResponsiblePartyIsProperlyFormed() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_GUARDIAN_ADDRESS_STATE));
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_GUARDIAN_ADDRESS_CITY));
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_GUARDIAN_ADDRESS_ZIP));
		
		ruleDetections.addAll(Arrays.asList(
				Detection.PatientGuardianNameFirstIsMissing,
				Detection.PatientGuardianNameLastIsMissing,
				Detection.PatientGuardianNameIsMissing,
				Detection.PatientGuardianNameIsSameAsUnderagePatient,
				Detection.PatientGuardianPhoneIsMissing,
				Detection.PatientGuardianRelationshipIsMissing,
				Detection.PatientGuardianResponsiblePartyIsMissing
		));
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
		        
	    	this.util.addToListIfEmpty(target.getAddress().getStateCode(), 	VxuField.PATIENT_GUARDIAN_ADDRESS_STATE, issues);
	    	this.util.addToListIfEmpty(target.getAddress().getCity(), 		VxuField.PATIENT_GUARDIAN_ADDRESS_CITY, issues);
	    	this.util.addToListIfEmpty(target.getAddress().getZip(), 			VxuField.PATIENT_GUARDIAN_ADDRESS_ZIP, issues);
	    	this.util.addToListIfEmpty(target.getNameFirst(),  Detection.PatientGuardianNameFirstIsMissing, issues);
	    	this.util.addToListIfEmpty(target.getNameLast(),   Detection.PatientGuardianNameLastIsMissing, issues);
	    	
			if (StringUtils.isEmpty(tFirst) || StringUtils.isEmpty(tLast)) {
				issues.add(Detection.PatientGuardianNameIsMissing.build());
			}

			if (pFirst != null && !pFirst.equals("") && pLast != null && !pLast.equals("")) {
				if (pFirst.equals(tFirst) && pLast.equals(tLast)) {
					issues.add(Detection.PatientGuardianNameIsSameAsUnderagePatient.build());
				}
			}

			this.util.addToListIfEmpty(target.getPhoneNumber(), Detection.PatientGuardianPhoneIsMissing, issues);
			this.util.addToListIfEmpty(target.getRelationshipCode(), Detection.PatientGuardianRelationshipIsMissing, issues);
			
			passed = (issues.size() == 0);

		} else {
			// Shouldn't the responsible party be present???  This didn't raise any issues in the original code.  I'm not sure why.
			issues.add(Detection.PatientGuardianResponsiblePartyIsMissing.build());
			passed = false;
		}

		return buildResults(issues, passed);
	}

}
