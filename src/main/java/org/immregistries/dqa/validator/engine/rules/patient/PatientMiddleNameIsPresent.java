package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientMiddleNameIsPresent extends ValidationRule<DqaPatient> {

	public PatientMiddleNameIsPresent() {
		ruleDetections.add(Detection.PatientMiddleNameIsMissing);
	}
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String middleName = target.getNameMiddle();
		  
		if (common.isEmpty(middleName)) {
			issues.add(Detection.PatientMiddleNameIsMissing.build(target));
			passed = false;
		}
		return buildResults(issues, passed);
	}
	
	

}
