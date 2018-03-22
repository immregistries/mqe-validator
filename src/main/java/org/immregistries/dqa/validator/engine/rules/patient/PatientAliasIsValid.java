package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

public class PatientAliasIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
		};
	}
	
	public PatientAliasIsValid() {
		this.ruleDetections.add(
			Detection.PatientAliasIsMissing
		);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = false;
		
		String aliasFirst = target.getAliasFirst();
		String aliasLast = target.getAliasLast();
		
		if (this.common.isEmpty(aliasLast + aliasFirst)) {
			issues.add(Detection.PatientAliasIsMissing.build(target));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}
}
