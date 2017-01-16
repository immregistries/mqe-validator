package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationValuedAmtIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		String administeredAmount = target.getAmount();

		if (common.isEmpty(administeredAmount)
				|| "999".equals(administeredAmount)) {
			if (target.isAdministered()) {
				issues.add(MessageAttribute.VaccinationAdministeredAmountIsMissing
						.build());
				issues.add(MessageAttribute.VaccinationAdministeredAmountIsValuedAsUnknown
						.build());
			}
		} else {
			try {
				float amount = Float.parseFloat(target.getAmount());
				if (amount == 0) {
					if (target.isAdministered()) {
						issues.add(MessageAttribute.VaccinationAdministeredAmountIsValuedAsZero
								.build());
					}
				} else {
					passed = true;
				}
			} catch (NumberFormatException nfe) {
				if (target.isAdministered()) {
					issues.add(MessageAttribute.VaccinationAdministeredAmountIsInvalid
							.build(target.getAmount()));
				}
			}
		}

		return buildResults(issues, passed);

	}
}
