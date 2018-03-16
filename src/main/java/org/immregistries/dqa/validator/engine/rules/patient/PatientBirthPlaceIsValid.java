package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientBirthPlaceIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
		};
	}
	
	public PatientBirthPlaceIsValid() {
		this.ruleDetections.addAll(Arrays.asList(Detection.PatientBirthPlaceIsMissing));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived mr) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String place = target.getBirthPlace();
		
		if (common.isEmpty(place)) {
			issues.add(Detection.PatientBirthPlaceIsMissing.build());
		}
		
		return buildResults(issues, passed);
	}


}
