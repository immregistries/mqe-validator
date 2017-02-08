package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;
import org.immregistries.dqa.validator.model.hl7types.PatientImmunity;

public class PatientImmunityIsValid extends ValidationRule<DqaPatient> {
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
//				PatientBirthDateIsValid.class
				};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		for (PatientImmunity imm : target.getPatientImmunityList()) {
			String immCode = imm.getImmunityCode();
			issues.addAll(codr.handleCode(immCode, IssueField.PATIENT_IMMUNITY_CODE));
		}

		passed = issues.isEmpty();
		
		return buildResults(issues, passed);
	}

}
