package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;
import org.immregistries.dqa.validator.model.codes.VaccineMvx;

public class VaccinationMfrIsValid extends ValidationRule<DqaVaccination> {

	// dependency: VaccinationIsAdministered

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
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
					issues.add(MessageAttribute.VaccinationManufacturerCodeIsInvalidForDateAdministered
							.build(target.getManufacturer()));
					passed = false;
				} else if (vaccineMvx.getUseStartDate().after(
						target.getAdminDate())
						|| target.getAdminDate().after(
								vaccineMvx.getUseEndDate())) {
					issues.add(MessageAttribute.VaccinationManufacturerCodeIsUnexpectedForDateAdministered
							.build(target.getManufacturer()));
				}
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
