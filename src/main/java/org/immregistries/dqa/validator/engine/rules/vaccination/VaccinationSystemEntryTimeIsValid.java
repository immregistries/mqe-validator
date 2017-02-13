package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationSystemEntryTimeIsValid extends
		ValidationRule<DqaVaccination> {

	// dependency: VaccinationIsAdministered

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		if (target.getSystemEntryDate() == null) {
			issues.add(MessageAttribute.VaccinationSystemEntryTimeIsMissing.build());
		} else if (datr.isBeforeDate(m.getReceivedDate(),target.getSystemEntryDate())) {
			issues.add(MessageAttribute.VaccinationSystemEntryTimeIsInFuture.build());
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
