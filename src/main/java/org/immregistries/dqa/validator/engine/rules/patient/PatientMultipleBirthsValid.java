package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

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
			issues.add(MessageAttribute.PatientBirthIndicatorIsMissing.build());
			passed = false;
		} else {
			
			String birthOrder = target.getBirthOrderNumber();
			
			if ("Y".equals(multipleBirthInd)) {
				
				// handleCodeReceived(patient.getBirthOrder(), PotentialIssues.Field.PATIENT_BIRTH_ORDER);
				// gotta handle those codes received...
				
				if (common.isEmpty(birthOrder)) {
					issues.add(MessageAttribute.PatientBirthOrderIsMissingAndMultipleBirthIndicated.build());
					passed = false;
				}
				
			} else if ("N".equals(multipleBirthInd)) {
				
				if (!common.isEmpty(birthOrder) && !"1".equals(birthOrder)) {
					issues.add(MessageAttribute.PatientBirthOrderIsInvalid.build());
					passed = false;
				}
				
			} else {
				
				issues.add(MessageAttribute.PatientBirthIndicatorIsInvalid.build());
				passed = false;
				
			}
		}

		return buildResults(issues, passed);
	}

}
