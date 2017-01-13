package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;
import org.immregistries.dqa.validator.model.hl7types.Id;

public class PatientPrimaryPhysicianNameIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		Id physician = target.getPhysician();
		
		if (physician != null) {
			if (physician.getName() == null || common.isEmpty(physician.getName())) {
				issues.add(PotentialIssue.PatientPrimaryPhysicianNameIsMissing.build());
				passed = false;
			}
		}
	 
		return buildResults(issues, passed);
	}
	
}
