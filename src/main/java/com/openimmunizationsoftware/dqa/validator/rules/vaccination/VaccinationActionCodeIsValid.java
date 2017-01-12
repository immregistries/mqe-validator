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

public class VaccinationActionCodeIsValid extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		
		issues.addAll(this.codr.handleCode(target.getAction(), IssueField.VACCINATION_ACTION_CODE));

		if (issues.size() > 0) {
			passed = false;
		} else {
			String actionCode = target.getActionCode();
			if (target.isActionAdd()) {
				issues.add(PotentialIssue.VaccinationActionCodeIsValuedAsAdd.build(actionCode));
				issues.add(PotentialIssue.VaccinationActionCodeIsValuedAsAddOrUpdate.build(actionCode));
			} else if (target.isActionUpdate()) {
				issues.add(PotentialIssue.VaccinationActionCodeIsValuedAsUpdate.build(actionCode));
				issues.add(PotentialIssue.VaccinationActionCodeIsValuedAsAddOrUpdate.build(actionCode));
			} else if (target.isActionDelete()) {
				issues.add(PotentialIssue.VaccinationActionCodeIsValuedAsDelete.build(actionCode));
			}
		}
		
		return buildResults(issues, passed);
		
	}
}
