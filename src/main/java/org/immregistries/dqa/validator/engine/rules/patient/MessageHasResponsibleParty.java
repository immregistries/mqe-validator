package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPatient;

public class MessageHasResponsibleParty extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class};
	}
	
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived mr) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		List<DqaNextOfKin> kinList = mr.getNextOfKins();
		
		boolean hasRespParty = false;
		
		for (DqaNextOfKin kin : kinList) {
			if (kin.isResponsibleRelationship()) {
				hasRespParty = true;
				break;
			}
		}
		
		if (!hasRespParty) {
			issues.add(this.util.createIssue(Detection.PatientGuardianResponsiblePartyIsMissing, ""));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}
}
