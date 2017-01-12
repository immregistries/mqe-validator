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

public class VaccinationConfidentialityCodeIsValid extends
		ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationValuedAmtIsValid.class };
	}

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = false;

		CodedEntity conf = target.getConfidentiality();
		String confCode = target.getConfidentialityCode();

		issues.addAll(codr.handleCode(conf,IssueField.VACCINATION_CONFIDENTIALITY_CODE));
		passed = (issues.size() == 0);

		if ("R".equals(confCode) || "V".equals(confCode)) {
			issues.add(PotentialIssue.VaccinationConfidentialityCodeIsValuedAsRestricted.build());
			//this is not a fail condition. 
		}

		return buildResults(issues, passed);

	}
}
