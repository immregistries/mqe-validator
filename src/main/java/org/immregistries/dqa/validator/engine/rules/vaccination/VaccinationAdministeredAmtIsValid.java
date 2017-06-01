package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdministeredAmtIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		String administeredAmount = target.getAmount();

		if (target.isAdministered()) {
			if (common.isEmpty(administeredAmount) || "999".equals(administeredAmount)) {
				issues.add(MessageAttribute.VaccinationAdministeredAmountIsMissing.build());
				issues.add(MessageAttribute.VaccinationAdministeredAmountIsValuedAsUnknown.build());
			} else {
				try {
					float amount = Float.parseFloat(target.getAmount());
					if (amount == 0) {
						issues.add(MessageAttribute.VaccinationAdministeredAmountIsValuedAsZero.build());
					} else if (amount > 999) {
						issues.add(MessageAttribute.VaccinationAdministeredAmountIsInvalid.build(target.getAmount()));
					} else {
						passed = true;
					}
				} catch (NumberFormatException nfe) {
					issues.add(MessageAttribute.VaccinationAdministeredAmountIsInvalid.build(target.getAmount()));
				}
			}
		}

		return buildResults(issues, passed);
	}
}