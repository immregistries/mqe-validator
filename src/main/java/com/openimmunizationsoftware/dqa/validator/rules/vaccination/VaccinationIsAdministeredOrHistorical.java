package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class VaccinationIsAdministeredOrHistorical extends
		ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		passed = (target.getCompletion().isEmpty() 
				  || target.isCompletionCompletedOrPartiallyAdministered())
			  && (target.getAdminCvxCode() != null 
		  	  	  && !target.getAdminCvxCode().equals("998"));

		return buildResults(issues, passed);

	}
}
