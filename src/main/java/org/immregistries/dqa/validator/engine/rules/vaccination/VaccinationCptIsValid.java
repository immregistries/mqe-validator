package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;

public class VaccinationCptIsValid extends ValidationRule<DqaVaccination> {

	
	public VaccinationCptIsValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_CPT_CODE));
		ruleDetections.add(Detection.VaccinationCptCodeIsInvalidForDateAdministered);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String cptCode = target.getAdminCptCode();
		Code cpt = repo.getCodeFromValue(cptCode,  CodesetType.VACCINATION_CPT_CODE);
		
		// so what I need to do... is check the CPT code stuff...
		if (!this.common.isEmpty(cptCode)) {

			issues.addAll(this.codr.handleCode(cptCode, VxuField.VACCINATION_CPT_CODE, target));

			if (issues.size() > 0) {
				passed = false;
			}
			
			//TODO:  figure out if this is the right place for use dates and start dates. 
			if (cpt != null && target.getAdminDate() != null) {
					codr.handleUseDate(cpt, target.getAdminDateString(), VxuField.VACCINATION_CPT_CODE, target);
					issues.add(Detection.VaccinationCptCodeIsInvalidForDateAdministered.build(target.getAdminCptCode(), target));
					passed = false;
			} 
		}

		return buildResults(issues, passed);
	}
}
