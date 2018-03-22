package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

public class PatientNameSuffixIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
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
