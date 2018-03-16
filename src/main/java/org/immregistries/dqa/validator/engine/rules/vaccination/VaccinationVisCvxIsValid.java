package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VaccinationVIS;

public class VaccinationVisCvxIsValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationVisIsPresent.class, VaccinationIsAdministered.class };
	}

	public VaccinationVisCvxIsValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_VIS_CVX_CODE));
	}
	
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		VaccinationVIS vis = target.getVaccinationVis();
		String visCvx = vis.getCvxCode();
			
		issues.addAll(codr.handleCode(visCvx, VxuField.VACCINATION_VIS_CVX_CODE));

		passed = issues.isEmpty();
		return buildResults(issues, passed);
	}
}
