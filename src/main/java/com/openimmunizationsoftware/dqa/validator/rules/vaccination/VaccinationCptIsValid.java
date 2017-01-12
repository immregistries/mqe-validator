package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCpt;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationCptIsValid extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		String cptCode = target.getAdminCptCode();

		// so what I need to do... is check the CPT code stuff...
		if (!common.isEmpty(cptCode)) {
			CodedEntity cpt = target.getAdminCpt();

			issues.addAll(this.codr.handleCode(cpt, IssueField.VACCINATION_CPT_CODE));

			if (issues.size() > 0) {
				passed = false;
			}
			
			VaccineCpt vaccineCpt = repo.getCptForCode(cptCode);

			if (vaccineCpt != null && target.getAdminDate() != null) {
				if (this.datr.isAfterDate(vaccineCpt.getValidStartDate(),
						target.getAdminDate())
						|| this.datr.isAfterDate(target.getAdminDate(),
								vaccineCpt.getValidEndDate())) {
					issues.add(PotentialIssue.VaccinationCptCodeIsInvalidForDateAdministered.build(target.getAdminCpt().getCodeReceived()));
					passed = false;
				} else if (this.datr.isAfterDate(vaccineCpt.getUseStartDate(),
						target.getAdminDate())
						|| this.datr.isAfterDate(target.getAdminDate(),
								vaccineCpt.getUseEndDate())) {
					issues.add(PotentialIssue.VaccinationCptCodeIsUnexpectedForDateAdministered.build(target.getAdminCpt().getCodeReceived()));
					passed = false;
				}
			}
		}

		return buildResults(issues, passed);
	}
}
