package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationRefusalReasonIsValid extends
		ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		
		if (target.isCompletionCompleted()
				&& !common.isEmpty(target.getRefusalCode())) {
			issues.add(PotentialIssue.VaccinationRefusalReasonConflictsCompletionStatus.build());
		}

		if (target.isCompletionRefused()) {
			if (common.isEmpty(target.getRefusalCode())) {
				issues.add(PotentialIssue.VaccinationRefusalReasonIsMissing.build());
			} else {
				issues.addAll(codr.handleCode(target.getRefusal(), IssueField.VACCINATION_REFUSAL_REASON));
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);

	}
}
