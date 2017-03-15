package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationProductIsValid extends ValidationRule<DqaVaccination> {

	// dependency: VaccinationIsAdministered
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			VaccinationIsAdministered.class, 
		};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String mvxCode = target.getManufacturerCode();
		String cvxCode = target.getAdminCvxCode();
		String adminDate = target.getAdminDateString();

		Code product = this.repo.getVaccineProduct(cvxCode, mvxCode, adminDate);

//		if (target.isAdministered()) { //This check is not necessary because of the class dependency. 
		if (product != null) {
			issues.addAll(codr.handleCode(target.getProduct(), VxuField.VACCINATION_PRODUCT));
			codr.handleUseDate(product, adminDate, VxuField.VACCINATION_PRODUCT);
		} else {
			issues.add(MessageAttribute.VaccinationProductIsMissing.build());
		}
//		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
