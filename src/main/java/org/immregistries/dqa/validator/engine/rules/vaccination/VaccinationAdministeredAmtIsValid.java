package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			if (this.common.isEmpty(administeredAmount) || "999".equals(administeredAmount)) {
				issues.add(Detection.VaccinationAdministeredAmountIsMissing.build(target));
				issues.add(Detection.VaccinationAdministeredAmountIsValuedAsUnknown.build(target));
			} else {
				try {
					float amount = Float.parseFloat(target.getAmount());
					if (amount == 0) {
						issues.add(Detection.VaccinationAdministeredAmountIsValuedAsZero.build(target));
					} else if (amount > 999) {
						issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(target.getAmount(), target));
					} else {
						passed = true;
					}
				} catch (NumberFormatException nfe) {
					issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(target.getAmount(), target));
				}
			}
		}

		return buildResults(issues, passed);
	}
}