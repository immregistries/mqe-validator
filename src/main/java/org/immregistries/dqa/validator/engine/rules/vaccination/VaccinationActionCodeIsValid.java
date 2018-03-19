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
		
		
		issues.addAll(this.codr.handleCode(target.getAction(), VxuField.VACCINATION_ACTION_CODE, target));

		if (issues.size() > 0) {
			passed = false;
		} else {
			String actionCode = target.getActionCode();
			if (target.isActionAdd()) {
				issues.add(Detection.VaccinationActionCodeIsValuedAsAdd.build((actionCode), target));
				issues.add(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate.build((actionCode), target));
			} else if (target.isActionUpdate()) {
				issues.add(Detection.VaccinationActionCodeIsValuedAsUpdate.build((actionCode), target));
				issues.add(Detection.VaccinationActionCodeIsValuedAsAddOrUpdate.build((actionCode), target));
			} else if (target.isActionDelete()) {
				issues.add(Detection.VaccinationActionCodeIsValuedAsDelete.build((actionCode), target));
			}
		}
		
		return buildResults(issues, passed);
		
	}
}
