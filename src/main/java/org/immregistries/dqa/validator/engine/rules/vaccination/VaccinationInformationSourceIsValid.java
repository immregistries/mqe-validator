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

public class VaccinationInformationSourceIsValid extends ValidationRule<DqaVaccination> {

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
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
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
				case DqaVaccination.INFO_SOURCE_ADMIN:
					issues.add(PotentialIssue.VaccinationInformationSourceIsValuedAsAdministered
							.build(sourceCd));
					break;
				case DqaVaccination.INFO_SOURCE_HIST:
					issues.add(PotentialIssue.VaccinationInformationSourceIsValuedAsHistorical
							.build(sourceCd));
					break;
			}
	      }
		
		return buildResults(issues, passed);
	}
}
