package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientDeathIndicatorIsValid extends ValidationRule<Patient> {

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
		return new Class[] {PatientExists.class};
	}
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived message) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String dInd = target.getDeathIndicator();
		boolean missing = this.common.isEmpty(dInd);
		
		if (missing) {
			issues.add(this.util.createIssue(PotentialIssue.PatientDeathIndicatorIsMissing, dInd));
			passed = false; //should this be a pass???
		}
		
		if (!"Y".equals(dInd)) {
			if (target.getDeathDate() != null) {
				issues.add(this.util.createIssue(PotentialIssue.PatientDeathIndicatorIsInconsistent, target.getDeathDate().toString()));
				passed = false;
			}
		}

		//what's considered a pass here?  
//			1. not inconsistent...  that's a non-passing problem. but it might be a passing issue on the message as a whole.  
		return buildResults(issues, passed);
	}
	
}
