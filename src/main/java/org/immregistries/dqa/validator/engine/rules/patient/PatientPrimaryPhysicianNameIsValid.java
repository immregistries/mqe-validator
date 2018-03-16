package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.hl7.Id;

public class PatientPrimaryPhysicianNameIsValid extends ValidationRule<DqaPatient> {

	
	public PatientPrimaryPhysicianNameIsValid() {
		ruleDetections.add(Detection.PatientPrimaryPhysicianNameIsMissing);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		Id physician = target.getPhysician();
		
		if (physician != null) {
			if (physician.getName() == null || common.isEmpty(physician.getName())) {
				issues.add(Detection.PatientPrimaryPhysicianNameIsMissing.build());
				passed = false;
			}
		}
	 
		return buildResults(issues, passed);
	}
	
}
