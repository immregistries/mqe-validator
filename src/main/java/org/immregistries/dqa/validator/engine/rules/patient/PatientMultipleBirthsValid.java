package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientMultipleBirthsValid extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { PatientExists.class };
	}

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String multipleBirthInd = target.getBirthMultipleInd();

		if (common.isEmpty(multipleBirthInd)) {
			issues.add(PotentialIssue.PatientBirthIndicatorIsMissing.build());
			passed = false;
		} else {
			
			String birthOrder = target.getBirthOrderNumber();
			
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
