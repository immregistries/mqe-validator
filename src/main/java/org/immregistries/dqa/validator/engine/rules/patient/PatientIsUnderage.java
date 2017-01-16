package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;
import org.joda.time.DateTime;

public class PatientIsUnderage extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
			PatientBirthDateIsValid.class
		};
	}
	
	/*
	 * This is the money: 
	 */
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;
		
		if (/* protect first */target != null && target.getBirthDate() != null
				&& m.getMessageHeader() != null
				&& m.getMessageHeader().getMessageDate() != null) {
			
			DateTime eighteenYearsFromSubmission = new DateTime(m.getMessageHeader().getMessageDate().getTime()).minusYears(18);
			
			if (/* patient is underage */eighteenYearsFromSubmission.isBefore(target.getBirthDate().getTime())) {
				issues.add(MessageAttribute.PatientBirthDateIsUnderage.build(target.getBirthDate().toString()));
				passed = true;
			}
		}
		
		return buildResults(issues, passed);
	}
}
