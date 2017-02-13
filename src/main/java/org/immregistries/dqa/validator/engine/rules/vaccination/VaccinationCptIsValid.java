package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.IssueField;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationCptIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String cptCode = target.getAdminCptCode();
		Code cpt = repo.getCodeFromValue(cptCode,  CodesetType.VACCINATION_CPT_CODE);
		
		// so what I need to do... is check the CPT code stuff...
		if (!common.isEmpty(cptCode)) {

			issues.addAll(this.codr.handleCode(cptCode, IssueField.VACCINATION_CPT_CODE));

			if (issues.size() > 0) {
				passed = false;
			}
			
			//TODO:  figure out if this is the right place for use dates and start dates. 
			if (cpt != null && target.getAdminDate() != null) {
					codr.handleUseDate(cpt, target.getAdminDateString(), IssueField.VACCINATION_CPT_CODE);
					issues.add(MessageAttribute.VaccinationCptCodeIsInvalidForDateAdministered.build(target.getAdminCptCode()));
					passed = false;
			} 
		}

		return buildResults(issues, passed);
	}
}
