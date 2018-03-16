package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodeStatusValue;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

import java.util.ArrayList;
import java.util.List;

public class VaccinationUseCvx extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String cvxString = target.getAdminCvxCode();
		
		Code cvxCode = repo.getCodeFromValue(cvxString, CodesetType.VACCINATION_CVX_CODE);
		
		boolean useCvx = true;
		
		if (cvxCode == null) {
			useCvx = false;
		} else {
			CodeStatusValue cvxStatus = CodeStatusValue.getBy(cvxCode.getCodeStatus());
			
			
			if (cvxStatus == null || cvxCode == null) {
				useCvx = false;
			}
			
			if (useCvx) {
				switch (cvxStatus) {
				case INVALID:
				case IGNORED:
					useCvx = true;
					break;
				default:
					break;
				}
			}
			passed = useCvx;
		}

		return buildResults(issues, passed);
	}
}
