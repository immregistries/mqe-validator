package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationSystemEntryTimeIsValid extends
		ValidationRule<DqaVaccination> {

	// dependency: VaccinationIsAdministered

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		if (target.getSystemEntryDate() == null) {
			issues.add(PotentialIssue.VaccinationSystemEntryTimeIsMissing.build());
		} else if (datr.isBeforeDate(m.getReceivedDate(),target.getSystemEntryDate())) {
			issues.add(PotentialIssue.VaccinationSystemEntryTimeIsInFuture.build());
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
