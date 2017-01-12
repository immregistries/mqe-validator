package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class PatientNameSuffixIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String sfx = target.getNameSuffix();

		//You know what...  there aren't even any potential issues for the suffix.
		
//Heres the code from the validator.   Nothing gets validated.  just transformed. 

//		   if (!isEmpty(patient.getNameSuffix()))
//		    {
//		      if (patient.getNameSuffix().equalsIgnoreCase("11") || patient.getNameSuffix().equalsIgnoreCase("2nd"))
//		      {
//		        patient.setNameSuffix("II");
//		      } else if (patient.getNameSuffix().equalsIgnoreCase("111") || patient.getNameSuffix().equalsIgnoreCase("3rd"))
//		      {
//		        patient.setNameSuffix("III");
//		      } else if (patient.getNameSuffix().equalsIgnoreCase("4th"))
//		      {
//		        patient.setNameSuffix("IV");
//		      }
//		      boolean isValid = false;
//		      for (String valid : VALID_SUFFIX)
//		      {
//		        if (patient.getNameSuffix().equalsIgnoreCase(valid))
//		        {
//		          isValid = true;
//		        }
//		      }
//		      if (!isValid)
//		      {
//		        // TODO suffix is invalid
//		        patient.setNameSuffix("");
//		      }
//		    }
		 
		 
		return buildResults(issues, passed);
	}
	
}
