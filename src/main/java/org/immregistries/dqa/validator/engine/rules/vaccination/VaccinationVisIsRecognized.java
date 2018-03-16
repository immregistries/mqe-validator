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

public class VaccinationVisIsRecognized extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationVisIsPresent.class, VaccinationIsAdministered.class};
	}

	public VaccinationVisIsRecognized() {
		ruleDetections.add(Detection.VaccinationVisIsMissing);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		VaccinationVIS vis = target.getVaccinationVis();
		String visCvx = vis.getCvxCode();
			
		if (common.isEmpty(vis.getDocumentCode()) && (common.isEmpty(visCvx) && vis.getPublishedDate() == null)) {
				issues.add(Detection.VaccinationVisIsMissing.build());
		}
		
		passed = issues.isEmpty();
		return buildResults(issues, passed);
	}
}
