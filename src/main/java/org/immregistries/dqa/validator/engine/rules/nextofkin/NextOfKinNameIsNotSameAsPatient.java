package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.dqa.validator.engine.rules.patient.PatientNameIsValid;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaNextOfKin;
import org.immregistries.dqa.validator.model.DqaPatient;

public class NextOfKinNameIsNotSameAsPatient extends ValidationRule<DqaNextOfKin> {

	@Override
	protected final Class[] getDependencies() {

		return new Class[] { 
				PatientIsUnderage.class, 
				PatientNameIsValid.class,
				NextOfKinNameIsValid.class };
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

			issues.add(MessageAttribute.PatientGuardianNameIsSameAsUnderagePatient.build());
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