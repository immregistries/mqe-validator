package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.CodeReceived;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvx;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationCvxIsValid extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		issues.addAll(this.codr.handleCode(target.getAdminCvx(), IssueField.VACCINATION_CVX_CODE));

		String cvxCode = target.getAdminCvxCode();

		if (!common.isEmpty(cvxCode)) {
			 
//			VaccineCvx vaccineCvx= repo.getCvxForCode(cvxCode);
			VaccineCvx vaccineCvx = target.getVaccineCvx();
			
			if (vaccineCvx != null && target.getAdminDate() != null) {
				CodeReceived vaccineCr = target.getAdminCvx().getCodeReceived();
				
				if (datr.isAfterDate(vaccineCvx.getValidStartDate(), target.getAdminDate())
				 || datr.isAfterDate(target.getAdminDate(), vaccineCvx.getValidEndDate())) {
				
					if (VaccineCvx.CONCEPT_TYPE_FOREIGN_VACCINE.equals(vaccineCvx.getConceptType())
					 || VaccineCvx.CONCEPT_TYPE_UNSPECIFIED.equals(vaccineCvx.getConceptType())) {
						if (target.isAdministered()) {
							issues.add(PotentialIssue.VaccinationCvxCodeIsInvalidForDateAdministered.build(vaccineCr));
						}
					} else {
						issues.add(PotentialIssue.VaccinationCvxCodeIsInvalidForDateAdministered.build(vaccineCr));
					}
				} else if (datr.isAfterDate(vaccineCvx.getUseStartDate(), target.getAdminDate())
						|| datr.isAfterDate(target.getAdminDate(), vaccineCvx.getUseEndDate())) {
					if (VaccineCvx.CONCEPT_TYPE_FOREIGN_VACCINE.equals(vaccineCvx.getConceptType())
					 || VaccineCvx.CONCEPT_TYPE_UNSPECIFIED.equals(vaccineCvx.getConceptType())) {
						if (target.isAdministered()) {
							issues.add(PotentialIssue.VaccinationCvxCodeIsUnexpectedForDateAdministered.build(vaccineCr));
						}
					} else {
						issues.add(PotentialIssue.VaccinationCvxCodeIsUnexpectedForDateAdministered.build(vaccineCr));
					}
				}
			}
		}
		
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
}
