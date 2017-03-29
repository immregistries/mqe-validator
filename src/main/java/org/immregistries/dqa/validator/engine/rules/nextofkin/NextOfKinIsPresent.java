package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;

public class NextOfKinIsPresent extends ValidationRule<DqaNextOfKin> {

	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = target != null;
		
		return buildResults(issues, passed);
	}

}
