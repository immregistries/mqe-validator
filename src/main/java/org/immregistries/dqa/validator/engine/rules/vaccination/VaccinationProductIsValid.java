package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

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
			issues.addAll(codr.handleCode(target.getProduct(), IssueField.VACCINATION_PRODUCT));
			codr.handleUseDate(product, adminDate, IssueField.VACCINATION_PRODUCT);
		} else {
			issues.add(MessageAttribute.VaccinationProductIsMissing.build());
		}
//		}

		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
