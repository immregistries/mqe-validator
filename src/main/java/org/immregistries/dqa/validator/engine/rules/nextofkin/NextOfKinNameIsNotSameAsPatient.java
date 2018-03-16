package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.dqa.validator.engine.rules.patient.PatientNameIsValid;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPatient;

public class NextOfKinNameIsNotSameAsPatient extends ValidationRule<DqaNextOfKin> {

	@Override
	protected final Class[] getDependencies() {

		return new Class[] { 
				PatientIsUnderage.class, 
				PatientNameIsValid.class,
				NextOfKinNameIsValid.class };
	}
	
	public NextOfKinNameIsNotSameAsPatient() {
		this.ruleDetections.addAll(Arrays.asList
				(Detection.PatientGuardianNameIsSameAsUnderagePatient));
	}

	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		DqaPatient patient = m.getPatient();

		if (target.isResponsibleRelationship()
				&& areEqual(target.getNameLast(),   patient.getNameLast())
				&& areEqual(target.getNameFirst(),  patient.getNameFirst())
				&& areEqual(target.getNameMiddle(), patient.getNameMiddle())
				&& areEqual(target.getNameSuffix(), patient.getNameMiddle())
				&& areEqual(target.getNameSuffix(), patient.getNameSuffix())) {

			issues.add(Detection.PatientGuardianNameIsSameAsUnderagePatient.build());
			passed = false;
		}
		
		return buildResults(issues, passed);
	}

	/**
	 * Just a little hack. 
	 * @param one
	 * @param two
	 * @return
	 */
	protected boolean areEqual(String one, String two) {
		return one != null && one.equals(two);
	}

}