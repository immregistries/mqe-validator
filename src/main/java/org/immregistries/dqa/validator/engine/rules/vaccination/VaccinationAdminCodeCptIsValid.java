package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;

public class VaccinationAdminCodeCptIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationUseCptInsteadOfCvx.class};
	}

	public VaccinationAdminCodeCptIsValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_ADMIN_CODE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String cpt = target.getAdminCptCode();

		issues.addAll(codr.handleCode(cpt, VxuField.VACCINATION_ADMIN_CODE, target));
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
	}

}
