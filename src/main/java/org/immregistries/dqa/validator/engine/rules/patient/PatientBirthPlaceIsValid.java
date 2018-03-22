package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		String place = target.getBirthPlace();
		
		if (this.common.isEmpty(place)) {
			issues.add(Detection.PatientBirthPlaceIsMissing.build(target));
		}
		
		return buildResults(issues, passed);
	}


}
