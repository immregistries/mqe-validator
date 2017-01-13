package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationCvxAdminAgeIsValid extends ValidationRule<DqaVaccination> {


	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationCvxIsValid.class };
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String cvxCode = target.getAdminCvxCode();
		Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
		
		if (vaccineCode != null && target.getAdminDate() != null) {
			if (m.getPatient().getBirthDate() != null) {
				codr.handleAgeDate(vaccineCode
						, target.getAdminDate()
						, m.getPatient().getBirthDate()
						, IssueField.VACCINATION_ADMIN_DATE);
			}
			
			passed = (issues.size() == 0);
		}

		return buildResults(issues, passed);
	}
}
