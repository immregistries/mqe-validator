package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.engine.rules.patient.PatientExists;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaNextOfKin;
import org.immregistries.dqa.validator.model.hl7types.Address;

public class NextOfKinAddressIsSameAsPatientAddress extends ValidationRule<DqaNextOfKin> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { PatientExists.class, NextOfKinAddressIsValid.class };
	}

	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived message) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		Address p = message.getPatient().getAddress();
		Address n = target.getAddress();
		if (!p.getCity().equals(n.getCity())
				|| !p.getState().equals(n.getState())
				|| !p.getStreet().equals(n.getStreet())
				|| !p.getStreet2().equals(p.getStreet2())) {
			
			issues.add(PotentialIssue.NextOfKinAddressIsDifferentFromPatientAddress.build(n.toString()));
			passed = false;
		}

		return buildResults(issues, passed);
	}
}
