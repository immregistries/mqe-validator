package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;

import java.util.ArrayList;
import java.util.List;

public class NextOfKinIsPresent extends ValidationRule<DqaNextOfKin> {

	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = target != null;
		
		return buildResults(issues, passed);
	}

}
