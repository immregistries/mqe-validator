package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.hl7.CodedEntity;

public class VaccinationFinancialEligibilityCodeIsValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		String financialEligibilityCode = target.getFinancialEligibilityCode();
		
		if (target.isAdministered()) {
		   if (common.isEmpty(financialEligibilityCode))
		      {
		        issues.add(Detection.VaccinationFinancialEligibilityCodeIsMissing.build());
		      } else {
		      issues.addAll(codr.handleCode(financialEligibilityCode, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE));
		    }
		}

		passed = (issues.size() == 0);
		return buildResults(issues, passed);

	}
}
