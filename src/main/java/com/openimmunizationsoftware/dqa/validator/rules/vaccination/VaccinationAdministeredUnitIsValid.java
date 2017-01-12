package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class VaccinationAdministeredUnitIsValid extends ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationValuedAmtIsValid.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = false;

		if (target.isAdministered()) {
			issues.addAll(codr.handleCode(target.getAmountUnit(), IssueField.VACCINATION_ADMINISTERED_UNIT));
		}

		passed = (issues.size() == 0);
		return buildResults(issues, passed);

	}
}
