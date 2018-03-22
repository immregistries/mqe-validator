package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;

public class VaccinationCvxIsValid extends ValidationRule<DqaVaccination> {

	public VaccinationCvxIsValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_CVX_CODE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		issues.addAll(this.codr.handleCode(target.getAdminCvxCode(), VxuField.VACCINATION_CVX_CODE, target));
		LOGGER.info("issues: " + issues);
		
		boolean passed = issues.isEmpty();

		return buildResults(issues, passed);
	}
}
