package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VaccinationVIS;

import java.util.ArrayList;
import java.util.List;

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
			
		if (this.common.isEmpty(vis.getDocumentCode()) && (this.common.isEmpty(visCvx) && vis.getPublishedDate() == null)) {
				issues.add(Detection.VaccinationVisIsMissing.build(target));
		}
		
		passed = issues.isEmpty();
		return buildResults(issues, passed);
	}
}
