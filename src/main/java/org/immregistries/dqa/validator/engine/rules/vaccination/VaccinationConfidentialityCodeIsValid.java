package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;
import org.immregistries.dqa.validator.model.hl7types.CodedEntity;

public class VaccinationConfidentialityCodeIsValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationValuedAmtIsValid.class };
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = false;

		String confCode = target.getConfidentialityCode();

		issues.addAll(codr.handleCode(confCode,IssueField.VACCINATION_CONFIDENTIALITY_CODE));
		passed = (issues.size() == 0);

		if ("R".equals(confCode) || "V".equals(confCode)) {
			issues.add(PotentialIssue.VaccinationConfidentialityCodeIsValuedAsRestricted.build());
		}

		return buildResults(issues, passed);

	}
}
