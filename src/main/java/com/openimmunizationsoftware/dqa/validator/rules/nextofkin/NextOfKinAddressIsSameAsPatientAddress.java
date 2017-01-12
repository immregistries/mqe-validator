package com.openimmunizationsoftware.dqa.validator.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.types.Address;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientExists;

public class NextOfKinAddressIsSameAsPatientAddress extends ValidationRule<NextOfKin> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { PatientExists.class, NextOfKinAddressIsValid.class };
	}

	@Override
	protected ValidationRuleResult executeRule(NextOfKin target, MessageReceived message) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
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
