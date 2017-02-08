package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;
import org.immregistries.dqa.validator.model.VaccinationVIS;

public class VaccinationVisIsRecognized extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationVisIsPresent.class};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		VaccinationVIS vis = target.getVaccinationVis();
		String visCvx = vis.getCvxCode();
			
		if (common.isEmpty(vis.getDocumentCode()) && (common.isEmpty(visCvx) && vis.getPublishedDate() == null)) {
				issues.add(MessageAttribute.VaccinationVisIsMissing.build());
		}
		
		passed = issues.isEmpty();
		return buildResults(issues, passed);
	}
}
