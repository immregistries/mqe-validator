package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientDeathDateIsValid extends ValidationRule<Patient> {

//	if (notEmpty(patient.getDeathIndicator(), pi.PatientDeathIndicatorIsMissing))
//    {
//      if (patient.getDeathIndicator().equals("Y"))
//      {
//        if (notEmpty(patient.getDeathDate(), pi.PatientDeathDateIsMissing))
//        {
//          if (patient.getBirthDate() != null && patient.getDeathDate().before(trunc(patient.getBirthDate())))
//          {
//            registerIssue(pi.PatientDeathDateIsBeforeBirth);
//          }
//          if (message.getReceivedDate().before(trunc(patient.getDeathDate())))
//          {
//            registerIssue(pi.PatientDeathDateIsInFuture);
//          }
//        }
//      } else
//      {
//        if (patient.getDeathDate() != null)
//        {
//          registerIssue(pi.PatientDeathIndicatorIsInconsistent);
//        }
//      }
//    }
	
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class, PatientDeathIndicatorIsValid.class, PatientBirthDateIsValid.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived message) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		if (target.getDeathDate() != null) {
			if (target.getBirthDate() != null && target.getDeathDate().before(target.getBirthDate())) {
				issues.add(PotentialIssue.PatientDeathDateIsBeforeBirth.build(target.getDeathDate().toString()));
				passed = false;
			}
			
			 if (message.getReceivedDate().before(target.getDeathDate())) {
			 	issues.add(PotentialIssue.PatientDeathDateIsInFuture.build(target.getDeathDate().toString()));
			 	passed = false;
		     }
		} 

		return buildResults(issues, passed);
	}
	
}
