package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationFinancialEligibilityCodeIsValid extends
		ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = false;

		CodedEntity financialElig = target.getFinancialEligibility();
		String financialEligibilityCode = target.getFinancialEligibilityCode();
		
		if (target.isAdministered()) {
		   if (common.isEmpty(financialEligibilityCode))
		      {
		        issues.add(PotentialIssue.VaccinationFinancialEligibilityCodeIsMissing.build());
		      } else {
		      issues.addAll(codr.handleCode(financialElig, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE));
		    }
		}

		passed = (issues.size() == 0);
		return buildResults(issues, passed);

	}
}
