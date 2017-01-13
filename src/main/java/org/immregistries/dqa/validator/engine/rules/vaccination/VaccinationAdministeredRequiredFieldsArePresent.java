package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationAdministeredRequiredFieldsArePresent extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationIsAdministered.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
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
