package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdminCodeCptIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationUseCptInsteadOfCvx.class};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination v, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String cpt = v.getAdminCptCode();

		issues.addAll(codr.handleCode(cpt, VxuField.VACCINATION_ADMIN_CODE));
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
	}

}
