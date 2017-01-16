package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;
import org.immregistries.dqa.validator.model.hl7types.CodedEntity;

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
		        issues.add(MessageAttribute.VaccinationFinancialEligibilityCodeIsMissing.build());
		      } else {
		      issues.addAll(codr.handleCode(financialEligibilityCode, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE));
		    }
		}

		passed = (issues.size() == 0);
		return buildResults(issues, passed);

	}
}
