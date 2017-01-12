package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientBirthDateIsValid;

public class VaccinationAdministeredRequiredFieldsArePresent extends ValidationRule<Vaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationIsAdministered.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(Vaccination target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		
	    // If vaccination is not actually administered then this is a waiver. Need
	    // to check that now, here to see if we need to enforce a value in RXA-9 to
	    // indicate that the vaccination is historical or administered.
	    // By default we assume that the vaccination was completed.

	      if (common.isEmpty(target.getFacilityName())) {
	    	  issues.add(PotentialIssue.VaccinationFacilityNameIsMissing.build());
	      }
	    	  
	      if (target.getExpirationDate() == null) {
	    	  issues.add(PotentialIssue.VaccinationLotExpirationDateIsMissing.build());
	      }
	      
	      if (common.isEmpty(target.getLotNumber())) {
	    	  issues.add(PotentialIssue.VaccinationLotNumberIsMissing.build());
	      } else {
	        String lotNumber = target.getLotNumber();
	        if (lotNumber.startsWith("LOT") || lotNumber.length() <= 4)
	        {
	          issues.add(PotentialIssue.VaccinationLotNumberIsInvalid.build());
	        }
	      }
	    
	    passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
		
	}
}
