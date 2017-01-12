package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationSystemEntryTimeIsValid extends
		ValidationRule<Vaccination> {

	// dependency: VaccinationIsAdministered

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
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
