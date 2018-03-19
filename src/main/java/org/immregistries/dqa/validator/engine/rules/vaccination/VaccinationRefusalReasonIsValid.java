package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinationRefusalReasonIsValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}
	
	public VaccinationRefusalReasonIsValid() {
		ruleDetections.addAll(
				Arrays.asList(
						Detection.VaccinationRefusalReasonConflictsCompletionStatus,
						Detection.VaccinationRefusalReasonIsMissing
				)
		);
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_REFUSAL_REASON));
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		
		boolean passed = true;
		
		if (target.isCompletionCompleted() && !this.common.isEmpty(target.getRefusalCode())) {
			issues.add(Detection.VaccinationRefusalReasonConflictsCompletionStatus.build(target));
		}

		if (target.isCompletionRefused()) {
			if (this.common.isEmpty(target.getRefusalCode())) {
				issues.add(Detection.VaccinationRefusalReasonIsMissing.build(target));
			} else {
				issues.addAll(codr.handleCode(target.getRefusal(), VxuField.VACCINATION_REFUSAL_REASON, target));
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);

	}
}
