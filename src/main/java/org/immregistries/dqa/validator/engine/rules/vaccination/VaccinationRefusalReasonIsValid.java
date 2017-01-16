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

public class VaccinationRefusalReasonIsValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		
		boolean passed = true;
		
		if (target.isCompletionCompleted() && !common.isEmpty(target.getRefusalCode())) {
			issues.add(MessageAttribute.VaccinationRefusalReasonConflictsCompletionStatus.build());
		}

		if (target.isCompletionRefused()) {
			if (common.isEmpty(target.getRefusalCode())) {
				issues.add(MessageAttribute.VaccinationRefusalReasonIsMissing.build());
			} else {
				issues.addAll(codr.handleCode(target.getRefusal(), IssueField.VACCINATION_REFUSAL_REASON));
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);

	}
}
