package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdministeredAmtIsValid extends ValidationRule<DqaVaccination> {

	public VaccinationAdministeredAmtIsValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.VaccinationAdministeredAmountIsMissing,
				Detection.VaccinationAdministeredAmountIsValuedAsUnknown,
				Detection.VaccinationAdministeredAmountIsValuedAsZero,
				Detection.VaccinationAdministeredAmountIsInvalid
		));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		String administeredAmount = target.getAmount();

		if (target.isAdministered()) {
			if (common.isEmpty(administeredAmount) || "999".equals(administeredAmount)) {
				issues.add(Detection.VaccinationAdministeredAmountIsMissing.build());
				issues.add(Detection.VaccinationAdministeredAmountIsValuedAsUnknown.build());
			} else {
				try {
					float amount = Float.parseFloat(target.getAmount());
					if (amount == 0) {
						issues.add(Detection.VaccinationAdministeredAmountIsValuedAsZero.build());
					} else if (amount > 999) {
						issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(target.getAmount()));
					} else {
						passed = true;
					}
				} catch (NumberFormatException nfe) {
					issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(target.getAmount()));
				}
			}
		}

		return buildResults(issues, passed);
	}
}