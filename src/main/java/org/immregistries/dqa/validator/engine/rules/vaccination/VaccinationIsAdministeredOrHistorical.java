package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationIsAdministeredOrHistorical extends
		ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		passed = (target.getCompletion() != null && (target.getCompletion().isEmpty() 
				  || target.isCompletionCompletedOrPartiallyAdministered())
			  && (target.getAdminCvxCode() != null 
		  	  	  && !target.getAdminCvxCode().equals("998")));

		return buildResults(issues, passed);

	}
}
