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

public class VaccinationActionCodeIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		
		issues.addAll(this.codr.handleCode(target.getAction(), IssueField.VACCINATION_ACTION_CODE));

		if (issues.size() > 0) {
			passed = false;
		} else {
			String actionCode = target.getActionCode();
			if (target.isActionAdd()) {
				issues.add(MessageAttribute.VaccinationActionCodeIsValuedAsAdd.build(actionCode));
				issues.add(MessageAttribute.VaccinationActionCodeIsValuedAsAddOrUpdate.build(actionCode));
			} else if (target.isActionUpdate()) {
				issues.add(MessageAttribute.VaccinationActionCodeIsValuedAsUpdate.build(actionCode));
				issues.add(MessageAttribute.VaccinationActionCodeIsValuedAsAddOrUpdate.build(actionCode));
			} else if (target.isActionDelete()) {
				issues.add(MessageAttribute.VaccinationActionCodeIsValuedAsDelete.build(actionCode));
			}
		}
		
		return buildResults(issues, passed);
		
	}
}
