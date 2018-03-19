package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.AdministeredLikelihood;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinationIsForeign extends ValidationRule<DqaVaccination> {

	public VaccinationIsForeign() {
		ruleDetections.addAll(
				Arrays.asList(
						Detection.VaccinationAdministeredCodeIsForiegn,
						Detection.VaccinationHistoricalCodeIsForeign
				)
		);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;
		
		String cvxCode 		 = target.getAdminCvxCode();
		boolean administered = target.isAdministered();
		
		Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
		if (vaccineCode != null) {
		CvxConceptType concept = CvxConceptType.getBy(vaccineCode.getConceptType());
		
		if (CvxConceptType.FOREIGN_VACCINE == concept) {
			if (administered) {
				issues.add(Detection.VaccinationAdministeredCodeIsForiegn.build(target));
			} else {
				issues.add(Detection.VaccinationHistoricalCodeIsForeign.build(target));
			}
		}
			passed = true;
		}

		return buildResults(issues, passed);

	}
}
