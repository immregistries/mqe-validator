package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.AdministeredLikelihood;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationIsForeign extends ValidationRule<DqaVaccination> {

	AdministeredLikelihood confidenceCalculator = AdministeredLikelihood.INSTANCE;

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;
		
		String cvxCode 		 = target.getAdminCvxCode();
		boolean administered = target.isAdministered();
		
		Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
		CvxConceptType concept = CvxConceptType.getBy(vaccineCode.getConceptType());
		
		if (CvxConceptType.FOREIGN_VACCINE == concept) {
			if (administered) {
				issues.add(PotentialIssue.VaccinationAdministeredCodeIsForiegn.build());
			} else {
				issues.add(PotentialIssue.VaccinationHistoricalCodeIsForeign.build());
			}
		
			passed = true;
		}

		return buildResults(issues, passed);

	}
}
