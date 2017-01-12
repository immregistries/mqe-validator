package com.openimmunizationsoftware.dqa.validator.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientIsUnderage;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientNameIsValid;

public class RespPartyNameIsNotSameAsPatient extends ValidationRule<NextOfKin> {

	@Override
	protected final Class[] getDependencies() {

		return new Class[] { 
				PatientIsUnderage.class, 
				PatientNameIsValid.class,
				NextOfKinNameIsValid.class };
	}

	@Override
	protected ValidationRuleResult executeRule(NextOfKin target, MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		Patient patient = m.getPatient();

		if (target.isResponsibleParty()
				&& areEqual(target.getNameLast(),   patient.getNameLast())
				&& areEqual(target.getNameFirst(),  patient.getNameFirst())
				&& areEqual(target.getNameMiddle(), patient.getNameMiddle())
				&& areEqual(target.getNameSuffix(), patient.getNameMiddle())
				&& areEqual(target.getNameSuffix(), patient.getNameSuffix())) {

			issues.add(PotentialIssue.PatientGuardianNameIsSameAsUnderagePatient.build());
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