package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class VaccinationCodesAreValid extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination vaccination, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		if (vaccination == null) {
			return buildResults(issues, false);
		}

		if (vaccination.isAdministered()) {
			issues.addAll(codr.handleCode(vaccination.getBodyRoute(), IssueField.VACCINATION_BODY_ROUTE));
			issues.addAll(codr.handleCode(vaccination.getBodySite(), IssueField.VACCINATION_BODY_SITE));
			issues.addAll(codr.handleCode(vaccination.getOrderedBy(), IssueField.VACCINATION_ORDERED_BY));
		}
	    
	    issues.addAll(codr.handleCode(vaccination.getEnteredBy(), IssueField.VACCINATION_RECORDED_BY));

	    // TODO VaccinationBodyRouteIsInvalidForVaccineIndicated
	    // TODO VaccinationBodySiteIsInvalidForVaccineIndicated

		// mark passed if there's no issues.
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}

}
