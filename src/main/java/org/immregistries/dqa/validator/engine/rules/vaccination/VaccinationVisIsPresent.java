package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VaccinationVIS;

public class VaccinationVisIsPresent extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
				
		};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		if (target.isAdministered()) {
		  VaccinationVIS vis = target.getVaccinationVis();
		if (vis == null ||(common.isEmpty(vis.getDocumentCode()) && (common.isEmpty(vis.getCvxCode()) && vis.getPublishedDate() == null))) {
				issues.add(MessageAttribute.VaccinationVisIsMissing.build());
		} else {
		  passed = true;
			}
		}
		
		return buildResults(issues, passed);
	}
}
