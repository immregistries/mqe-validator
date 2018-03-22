package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

public class PatientExists extends ValidationRule<DqaPatient> {

	public PatientExists() {
		ruleDetections.add(Detection.PatientObjectIsMissing);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived mr) {
		
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		if (target == null) {
			issues.add(Detection.PatientObjectIsMissing.build(target));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}


}
