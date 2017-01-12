package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvx;
import com.openimmunizationsoftware.dqa.model.codes.VaccineMvx;
import com.openimmunizationsoftware.dqa.model.codes.VaccineProduct;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationProductIsValid extends ValidationRule<Vaccination> {

	// dependency: VaccinationIsAdministered

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		VaccineMvx vaccineMvx = repo.getMfrForCode(target.getManufacturerCode());
		VaccineCvx vaccineCvx = target.getVaccineCvx();

		if (target.isAdministered()) {
			if (vaccineMvx != null
					&& !vaccineMvx.getMvxCode().equals("")
					&& vaccineCvx != null
					&& !vaccineCvx.getCvxCode().equals("")
					&& !vaccineCvx.getCvxCode().equals("998")
					&& !vaccineCvx.getCvxCode().equals("999")
					&& (target.getManufacturer().isValid() || target
							.getManufacturer().isDeprecated())) {

				issues.addAll(codr.handleCode(target.getProduct(), IssueField.VACCINATION_PRODUCT));

				VaccineProduct vp = repo.getVaccineProduct(vaccineCvx, vaccineMvx, target.getAdminDate());

				if (!vp.getValidStartDate().after(target.getAdminDate())
						&& !target.getAdminDate().after(vp.getValidEndDate())) {
					issues.add(PotentialIssue.VaccinationProductIsInvalidForDateAdministered.build(vp.getProductCode()));
				}

				if (!vp.getUseStartDate().after(target.getAdminDate())
						&& !target.getAdminDate().after(vp.getUseEndDate()))
					issues.add(PotentialIssue.VaccinationProductIsUnexpectedForDateAdministered.build(vp.getProductCode()));
			} else {
				issues.add(PotentialIssue.VaccinationProductIsMissing.build());
			}
		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
