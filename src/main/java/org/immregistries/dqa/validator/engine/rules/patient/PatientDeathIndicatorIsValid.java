package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientDeathIndicatorIsValid extends ValidationRule<DqaPatient> {

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
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String dInd = target.getDeathIndicator();
		boolean missing = this.common.isEmpty(dInd);
		
		if (missing) {
			issues.add(this.util.createIssue(MessageAttribute.PatientDeathIndicatorIsMissing, dInd));
			passed = false; //should this be a pass???
		}
		
		if (!"Y".equals(dInd)) {
			if (target.getDeathDate() != null) {
				issues.add(this.util.createIssue(MessageAttribute.PatientDeathIndicatorIsInconsistent, target.getDeathDate().toString()));
				passed = false;
			}
		}

		//what's considered a pass here?  
//			1. not inconsistent...  that's a non-passing problem. but it might be a passing issue on the message as a whole.  
		return buildResults(issues, passed);
	}
	
}
