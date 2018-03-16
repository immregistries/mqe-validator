package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationActionCodeIsValid extends ValidationRule<DqaVaccination> {

	public VaccinationActionCodeIsValid() {
		ruleDetections.addAll(
				Arrays.asList(
						Detection.VaccinationActionCodeIsValuedAsAdd,
						Detection.VaccinationActionCodeIsValuedAsAddOrUpdate,
						Detection.VaccinationActionCodeIsValuedAsUpdate,
						Detection.VaccinationActionCodeIsValuedAsAddOrUpdate,
						Detection.VaccinationActionCodeIsValuedAsDelete
				)
		);
		
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_ACTION_CODE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		
		issues.addAll(this.codr.handleCode(target.getAction(), VxuField.VACCINATION_ACTION_CODE));

		if (issues.size() > 0) {
			passed = false;
		} else {
			String actionCode = target.getActionCode();
			if (target.isActionAdd()) {
				issues.add(Detection.VaccinationActionCodeIsValuedAsAdd.build(actionCode));
				issues.add(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate.build(actionCode));
			} else if (target.isActionUpdate()) {
				issues.add(Detection.VaccinationActionCodeIsValuedAsUpdate.build(actionCode));
				issues.add(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate.build(actionCode));
			} else if (target.isActionDelete()) {
				issues.add(Detection.VaccinationActionCodeIsValuedAsDelete.build(actionCode));
			}
		}
		
		return buildResults(issues, passed);
		
	}
}
