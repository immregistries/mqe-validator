package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientMultipleBirthsValid extends ValidationRule<Patient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { PatientExists.class };
	}

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived message) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		String multipleBirthInd = target.getBirthMultiple();

		if (common.isEmpty(multipleBirthInd)) {
			issues.add(PotentialIssue.PatientBirthIndicatorIsMissing.build());
			passed = false;
		} else {
			
			String birthOrder = target.getBirthOrderCode();
			
			if ("Y".equals(multipleBirthInd)) {
				
				// handleCodeReceived(patient.getBirthOrder(), PotentialIssues.Field.PATIENT_BIRTH_ORDER);
				// gotta handle those codes received...
				
				if (common.isEmpty(birthOrder)) {
					issues.add(PotentialIssue.PatientBirthOrderIsMissingAndMultipleBirthIndicated.build());
					passed = false;
				}
				
			} else if ("N".equals(multipleBirthInd)) {
				
				if (!common.isEmpty(birthOrder) && !"1".equals(birthOrder)) {
					issues.add(PotentialIssue.PatientBirthOrderIsInvalid.build());
					passed = false;
				}
				
			} else {
				
				issues.add(PotentialIssue.PatientBirthIndicatorIsInvalid.build());
				passed = false;
				
			}
		}

		return buildResults(issues, passed);
	}

}
