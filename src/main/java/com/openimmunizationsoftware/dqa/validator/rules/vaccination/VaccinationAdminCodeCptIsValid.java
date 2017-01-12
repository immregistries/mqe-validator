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

public class VaccinationAdminCodeCptIsValid extends ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationUseCptInsteadOfCvx.class};
	}

	@Override
	protected ValidationRuleResult executeRule(Vaccination v, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		CodedEntity cpt = v.getAdminCpt();

		issues.addAll(codr.handleCode(cpt, IssueField.VACCINATION_ADMIN_CODE));
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
	}

}
