package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.codes.AdministeredLikelihood;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationIsAdministered extends ValidationRule<Vaccination> {

	AdministeredLikelihood al = AdministeredLikelihood.INSTANCE;

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		boolean administered = target.isAdministered();

		int administeredScore = al.administeredLiklihoodScore(target, m);

		if (administered && administeredScore < 10) {
			issues.add(PotentialIssue.VaccinationInformationSourceIsAdministeredButAppearsToHistorical.build());
		}
		if (!administered && administeredScore >= 10) {
			issues.add(PotentialIssue.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered.build());
		}
		
		passed = administered;

		return buildResults(issues, passed);

	}
}
