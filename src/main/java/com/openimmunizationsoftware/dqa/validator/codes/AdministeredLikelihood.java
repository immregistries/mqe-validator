package com.openimmunizationsoftware.dqa.validator.codes;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.common.DateUtility;

public enum AdministeredLikelihood {
	INSTANCE;
	
	private DateUtility datr = DateUtility.INSTANCE;
	
	public int administeredLiklihoodScore(Vaccination vaccination, MessageReceived message) {

	      // Created rough scoring system that gives a point to other attributes
	      // that suggest a vaccination
	      // was administered or not. The idea is that if the sender knows a lot
	      // about the vaccination and it
	      // was given recently then it is probably administered. Otherwise it must
	      // be historical.
	      int administeredScore = 0;
	      if (vaccination.getAdminDate() != null)
	      {
	    	int elapsed = datr.monthsBetween(vaccination.getAdminDate(), message.getReceivedDate());
	    	
	        if (elapsed < 1)
	        {
	          administeredScore += 5;
	        }
	      }
	      if (!vaccination.getLotNumber().equals(""))
	      {
	        administeredScore += 2;
	      }
	      if (vaccination.getExpirationDate() != null)
	      {
	        administeredScore += 2;
	      }
	      if (!vaccination.getManufacturerCode().equals(""))
	      {
	        administeredScore += 2;
	      }
	      if (!vaccination.getFinancialEligibilityCode().equals(""))
	      {
	        administeredScore += 2;
	      }
	      if (!vaccination.getBodyRouteCode().equals(""))
	      {
	        administeredScore += 1;
	      }
	      if (!vaccination.getBodySiteCode().equals(""))
	      {
	        administeredScore += 1;
	      }
	      if (!vaccination.getAmount().equals("") && !vaccination.getAmount().equals("999") && !vaccination.getAmount().equals("0"))
	      {
	        administeredScore += 3;
	      }
	      if (!vaccination.getFacilityIdNumber().equals("") || !vaccination.getFacilityName().equals(""))
	      {
	        administeredScore += 4;
	      }
	      if (!vaccination.getGivenBy().equals("") || !vaccination.getGivenByNameFirst().equals("") || !vaccination.getGivenByNameLast().equals(""))
	      {
	        administeredScore += 4;
	      }

	      return administeredScore;
	    
	}
}
