package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;

public class VaccinationFinancialEligibilityCodeIsValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}
	
	public VaccinationFinancialEligibilityCodeIsValid() {
		ruleDetections.add(Detection.VaccinationFinancialEligibilityCodeIsMissing);
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE));
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		String financialEligibilityCode = target.getFinancialEligibilityCode();
		
		if (target.isAdministered()) {
		   if (this.common.isEmpty(financialEligibilityCode))
		      {
		        issues.add(Detection.VaccinationFinancialEligibilityCodeIsMissing.build(target));
		      } else {
		      issues.addAll(codr.handleCode(financialEligibilityCode, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, target));
		    }
		}

		passed = (issues.size() == 0);
		return buildResults(issues, passed);

	}
}
