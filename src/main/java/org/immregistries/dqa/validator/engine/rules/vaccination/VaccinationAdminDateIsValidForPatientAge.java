package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdminDateIsValidForPatientAge extends ValidationRule<DqaVaccination> {


	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationCvxIsValid.class };
	}
	
	public VaccinationAdminDateIsValidForPatientAge() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_ADMIN_DATE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {
		LOGGER.info("VaccinationAdminDateIsValidForPatientAge - evaluating");
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String cvxCode = target.getAdminCvxCode();
		Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
		LOGGER.info("VaccinationAdminDateIsValidForPatientAge - CVX: " + cvxCode);
		LOGGER.info("VaccinationAdminDateIsValidForPatientAge - Admin Date " + target.getAdminDateString());
		LOGGER.info("VaccinationAdminDateIsValidForPatientAge - Birth Date " + m.getPatient().getBirthDateString());
		if (vaccineCode != null && !StringUtils.isBlank(target.getAdminDateString())) {
			
			Date adminDate = datr.parseDate(target.getAdminDateString());
			Date birthDate = datr.parseDate(m.getPatient().getBirthDateString());
			
			if (birthDate != null) {
				issues.addAll(codr.handleAgeDate(
						vaccineCode
						, adminDate
						, birthDate
						, VxuField.VACCINATION_ADMIN_DATE));
			}
			
			passed = (issues.size() == 0);
		}

		return buildResults(issues, passed);
	}
}
