package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.codes.VaccineMvx;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationMfrIsValid extends ValidationRule<Vaccination> {

	// dependency: VaccinationIsAdministered

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		if (target.isAdministered()) {
			issues.addAll(codr.handleCode(target.getManufacturer(), IssueField.VACCINATION_MANUFACTURER_CODE));
			passed = (issues.size() == 0);
			
		}

		VaccineMvx vaccineMvx = repo.getMfrForCode(target.getManufacturerCode());

		if (target.isAdministered()) {
			if (vaccineMvx != null && !common.isEmpty(vaccineMvx.getMvxCode())
					&& target.getAdminDate() != null) {
				if (datr.isAfterDate(vaccineMvx.getValidStartDate(),
						target.getAdminDate())
						|| datr.isAfterDate(target.getAdminDate(),
								vaccineMvx.getValidEndDate())) {
					issues.add(PotentialIssue.VaccinationManufacturerCodeIsInvalidForDateAdministered
							.build(target.getManufacturer().getCodeReceived()));
					passed = false;
				} else if (vaccineMvx.getUseStartDate().after(
						target.getAdminDate())
						|| target.getAdminDate().after(
								vaccineMvx.getUseEndDate())) {
					issues.add(PotentialIssue.VaccinationManufacturerCodeIsUnexpectedForDateAdministered
							.build(target.getManufacturer().getCodeReceived()));
				}
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
