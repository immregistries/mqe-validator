package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodeStatusValue;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationUseCptInsteadOfCvx extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String adminCvx = target.getAdminCvxCode();
		String adminCpt = target.getAdminCptCode();
		
		Code cvxCode = repo.getCodeFromValue(adminCvx, CodesetType.VACCINATION_CVX_CODE);
		Code cptCode = repo.getCodeFromValue(adminCpt, CodesetType.VACCINATION_CPT_CODE);

		boolean cvxIsUsable = false;
		boolean cptIsUsable = false;
		
		if (cvxCode != null) {
			CodeStatusValue cvxStatus = CodeStatusValue.getBy(cvxCode.getCodeStatus());
			cvxIsUsable = !(CodeStatusValue.INVALID == cvxStatus || CodeStatusValue.IGNORED == cvxStatus);
		}
		
		if (cptCode != null) {
			CodeStatusValue cptStatus = CodeStatusValue.getBy(cptCode.getCodeStatus());
			cptIsUsable = !(CodeStatusValue.INVALID == cptStatus || CodeStatusValue.IGNORED == cptStatus);
		}
		
	    boolean useCptInsteadOfCvx = !cvxIsUsable && cptIsUsable;

	    passed = useCptInsteadOfCvx;

		return buildResults(issues, passed);
	}
}
