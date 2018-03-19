package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PatientIsUnderage extends ValidationRule<DqaPatient> {
 
	private static final Logger logger = LoggerFactory
		.getLogger(PatientIsUnderage.class);

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
			PatientBirthDateIsValid.class
		};
	}
	
	public PatientIsUnderage() {
		this.ruleDetections.add(Detection.PatientBirthDateIsUnderage);
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
			
			DateTime eighteenYearsBeforeToday = (new DateTime()).minusYears(18);
			DateTime birthDate = new DateTime(target.getBirthDate());
			
			boolean underage = birthDate.isAfter(eighteenYearsBeforeToday);
			logger.info("Eighteen years before submission: " + datr.toString(eighteenYearsBeforeToday));
			logger.info("patient birth date: " + datr.toString(birthDate));
			
			if (/* patient is underage */underage) {
				issues.add(Detection.PatientBirthDateIsUnderage.build(datr.toString(birthDate), target));
				passed = true;
			}
		}
		
		return buildResults(issues, passed);
	}
}
