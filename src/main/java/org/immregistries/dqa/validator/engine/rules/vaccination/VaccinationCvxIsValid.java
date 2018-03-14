package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationCvxIsValid extends ValidationRule<DqaVaccination> {

	public VaccinationCvxIsValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_CVX_CODE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		issues.addAll(this.codr.handleCode(target.getAdminCvxCode(), VxuField.VACCINATION_CVX_CODE));
		LOGGER.info("issues: " + issues);
		
		boolean passed = issues.isEmpty();

		return buildResults(issues, passed);
	}
}
