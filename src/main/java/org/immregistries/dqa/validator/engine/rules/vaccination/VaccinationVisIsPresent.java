package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

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

		if (target.getVaccinationVis() == null) {
			if (target.isAdministered()) {
				issues.add(MessageAttribute.VaccinationVisIsMissing.build());
			}
		} else {
			passed = true;
		}
		
		return buildResults(issues, passed);
	}
}
