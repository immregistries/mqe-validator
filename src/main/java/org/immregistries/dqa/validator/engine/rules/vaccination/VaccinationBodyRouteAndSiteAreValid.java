package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;

public class VaccinationBodyRouteAndSiteAreValid extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationIsAdministered.class};
	}
	
	public VaccinationBodyRouteAndSiteAreValid() {
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_BODY_ROUTE));
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_BODY_SITE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (target != null) {//No null pointers!
			String bodySite = target.getBodySite();
			String bodyRoute = target.getBodyRoute();
			
			issues.addAll(codr.handleCode(bodyRoute, VxuField.VACCINATION_BODY_ROUTE, target));
			issues.addAll(codr.handleCode(bodySite, VxuField.VACCINATION_BODY_SITE, target));
		}
	    
		//These were not implemented in DQA 1.0
	    // TODO VaccinationBodyRouteIsInvalidForVaccineIndicated
	    // TODO VaccinationBodySiteIsInvalidForVaccineIndicated
		
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}

}
