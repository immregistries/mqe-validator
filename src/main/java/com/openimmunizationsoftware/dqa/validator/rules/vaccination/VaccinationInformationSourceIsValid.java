package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationInformationSourceIsValid extends ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			VaccinationIsAdministeredOrHistorical.class
		};
	}
	
	/*
	 * This is the money: 
	 */
	
	@Override
	protected ValidationRuleResult executeRule(Vaccination target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String sourceCd = target.getInformationSourceCode();
		
		if (common.isEmpty(sourceCd)) {
			issues.add(PotentialIssue.VaccinationInformationSourceIsMissing.build());
			passed = false;
		} else {
			
	        issues.addAll(this.codr.handleCode(target.getInformationSource(), IssueField.VACCINATION_INFORMATION_SOURCE));
	        
	        if (issues.size() > 0) {
	        	passed = false;
	        }
	        
			switch (sourceCd) {
				case Vaccination.INFO_SOURCE_ADMIN:
					issues.add(PotentialIssue.VaccinationInformationSourceIsValuedAsAdministered
							.build(sourceCd));
					break;
				case Vaccination.INFO_SOURCE_HIST:
					issues.add(PotentialIssue.VaccinationInformationSourceIsValuedAsHistorical
							.build(sourceCd));
					break;
			}
	      }
		
		return buildResults(issues, passed);
	}
}
