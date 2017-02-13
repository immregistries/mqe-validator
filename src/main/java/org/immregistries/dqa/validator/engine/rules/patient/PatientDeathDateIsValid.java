package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientDeathDateIsValid extends ValidationRule<DqaPatient> {

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
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		if (target.getDeathDate() != null) {
			if (target.getBirthDate() != null && target.getDeathDate().before(target.getBirthDate())) {
				issues.add(MessageAttribute.PatientDeathDateIsBeforeBirth.build(target.getDeathDate().toString()));
				passed = false;
			}
			
			 if (message.getReceivedDate().before(target.getDeathDate())) {
			 	issues.add(MessageAttribute.PatientDeathDateIsInFuture.build(target.getDeathDate().toString()));
			 	passed = false;
		     }
		} 

		return buildResults(issues, passed);
	}
	
}
