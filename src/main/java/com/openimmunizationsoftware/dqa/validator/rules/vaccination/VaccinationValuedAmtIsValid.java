package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationValuedAmtIsValid extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = false;

		String administeredAmount = target.getAmount();

		if (common.isEmpty(administeredAmount)
				|| "999".equals(administeredAmount)) {
			if (target.isAdministered()) {
				issues.add(PotentialIssue.VaccinationAdministeredAmountIsMissing
						.build());
				issues.add(PotentialIssue.VaccinationAdministeredAmountIsValuedAsUnknown
						.build());
			}
		} else {
			try {
				float amount = Float.parseFloat(target.getAmount());
				if (amount == 0) {
					if (target.isAdministered()) {
						issues.add(PotentialIssue.VaccinationAdministeredAmountIsValuedAsZero
								.build());
					}
				} else {
					passed = true;
				}
			} catch (NumberFormatException nfe) {
				if (target.isAdministered()) {
					issues.add(PotentialIssue.VaccinationAdministeredAmountIsInvalid
							.build(target.getAmount()));
				}
			}
		}

		return buildResults(issues, passed);

	}
}
