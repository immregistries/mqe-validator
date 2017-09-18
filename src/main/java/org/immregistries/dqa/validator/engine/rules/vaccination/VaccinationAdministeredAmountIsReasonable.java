package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdministeredAmountIsReasonable extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationAdministeredAmtIsValid.class, VaccinationIsAdministered.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		Double d = Double.parseDouble(target.getAmount()); 
		if (d > 999) {
			issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(target.getAmount()));
		}

		passed = (issues.size() == 0);
		return buildResults(issues, passed);

	}
}
