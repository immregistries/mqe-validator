package org.immregistries.dqa.validator.engine.rules.patient;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientHasResponsibleParty extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class};
	}
	
	public PatientHasResponsibleParty() {
		this.ruleDetections.addAll(Arrays.asList(Detection.PatientGuardianResponsiblePartyIsMissing));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived mr) {
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		if (target.getResponsibleParty() == null
			|| StringUtils.isBlank(target.getResponsibleParty().getRelationshipCode())) {
			issues.add(Detection.PatientGuardianResponsiblePartyIsMissing.build(target));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}
}
