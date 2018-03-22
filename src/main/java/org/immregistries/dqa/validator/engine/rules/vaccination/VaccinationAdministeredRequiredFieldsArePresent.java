package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinationAdministeredRequiredFieldsArePresent extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {VaccinationIsAdministered.class};
	}
	
	public VaccinationAdministeredRequiredFieldsArePresent() {
		ruleDetections.addAll(Arrays.asList(
				Detection.VaccinationFacilityNameIsMissing,
				Detection.VaccinationLotExpirationDateIsMissing,
				Detection.VaccinationLotNumberIsMissing,
				Detection.VaccinationLotNumberIsInvalid
		));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		
	    // If vaccination is not actually administered then this is a waiver. Need
	    // to check that now, here to see if we need to enforce a value in RXA-9 to
	    // indicate that the vaccination is historical or administered.
	    // By default we assume that the vaccination was completed.

	      if (this.common.isEmpty(target.getFacilityName())) {
	    	  issues.add(Detection.VaccinationFacilityNameIsMissing.build(target));
	      }
	    	  
	      if (target.getExpirationDate() == null) {
	    	  issues.add(Detection.VaccinationLotExpirationDateIsMissing.build(target));
	      }
	      
	      if (this.common.isEmpty(target.getLotNumber())) {
	    	  issues.add(Detection.VaccinationLotNumberIsMissing.build(target));
	      } else {
	        String lotNumber = target.getLotNumber();
	        if (lotNumber.startsWith("LOT") || lotNumber.length() <= 4)
	        {
	          issues.add(Detection.VaccinationLotNumberIsInvalid.build(target));
	        }
	      }
	    
	    passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
		
	}
}
