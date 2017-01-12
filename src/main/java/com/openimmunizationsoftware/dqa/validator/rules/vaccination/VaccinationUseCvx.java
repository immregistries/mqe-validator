package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCpt;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvx;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class VaccinationUseCvx extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		//This is a copy of what's in VaccinationUseCptInsteadOfCpt... Hmm...  not ideal. 
		VaccineCvx vaccineCvx = target.getVaccineCvx();
		CodedEntity cvxCe = target.getAdminCvx();
		VaccineCpt vaccineCpt = repo.getCptForCode(target.getAdminCptCode());
	    boolean useCptInsteadOfCvx = (vaccineCvx == null || cvxCe.isInvalid() || cvxCe.isIgnored()) && vaccineCpt != null;
	    
	    passed = !useCptInsteadOfCvx;

		return buildResults(issues, passed);
	}
}
