package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.AdministeredLikelihood;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationIsAdministered extends ValidationRule<DqaVaccination> {

	AdministeredLikelihood confidenceCalculator = AdministeredLikelihood.INSTANCE;

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		boolean administered = target.isAdministered();

		int administeredScore = confidenceCalculator.administeredLiklihoodScore(target, m);

		if (administered && administeredScore < 10) {
			issues.add(Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical.build());
		}
		if (!administered && administeredScore >= 10) {
			issues.add(Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered.build());
		}
		
		passed = administered;

		return buildResults(issues, passed);

	}
}
