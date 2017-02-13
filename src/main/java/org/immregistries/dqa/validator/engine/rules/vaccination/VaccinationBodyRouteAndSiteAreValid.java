package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.IssueField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationBodyRouteAndSiteAreValid extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationIsAdministered.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination vaccination, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (vaccination != null) {//No null pointers!
			String bodySite = vaccination.getBodySite();
			String bodyRoute = vaccination.getBodyRoute();
			
			issues.addAll(codr.handleCode(bodyRoute, IssueField.VACCINATION_BODY_ROUTE));
			issues.addAll(codr.handleCode(bodySite, IssueField.VACCINATION_BODY_SITE));
		}
	    
		//These were not implemented in DQA 1.0
	    // TODO VaccinationBodyRouteIsInvalidForVaccineIndicated
	    // TODO VaccinationBodySiteIsInvalidForVaccineIndicated
		
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}

}
