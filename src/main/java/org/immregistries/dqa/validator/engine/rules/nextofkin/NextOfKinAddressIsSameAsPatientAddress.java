package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.patient.PatientExists;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaAddress;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextOfKinAddressIsSameAsPatientAddress extends ValidationRule<DqaNextOfKin> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { PatientExists.class, NextOfKinAddressIsValid.class };
	}

	public NextOfKinAddressIsSameAsPatientAddress() {
		this.ruleDetections.addAll(Arrays.asList(Detection.NextOfKinAddressIsDifferentFromPatientAddress));
	}
	
	
	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived message) {
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;

		DqaAddress p = message.getPatient().getPatientAddress();
		DqaAddress n = target.getAddress();
		if (!p.getCity().equals(n.getCity())
				|| !p.getState().equals(n.getState())
				|| !p.getStreet().equals(n.getStreet())
				|| !p.getStreet2().equals(p.getStreet2())) {
			
			issues.add(Detection.NextOfKinAddressIsDifferentFromPatientAddress.build(n.toString(), target));
			passed = false;
		}

		return buildResults(issues, passed);
	}
}
