package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

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
        return new Class[]{PatientExists.class};
    }

    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
        List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
        boolean passed = true;

        String dInd = target.getDeathIndicator();

        if (this.common.isEmpty(dInd)) {
            issues.add(this.util.createIssue(Detection.PatientDeathIndicatorIsMissing, dInd));
            passed = false; //should this be a pass???
        }

        if (!"Y".equals(dInd)) {
            if (target.getDeathDate() != null || target.getDeathDateString() != null) {
                issues.add(this.util.createIssue(Detection.PatientDeathIndicatorIsInconsistent, target.getDeathDate().toString()));
                passed = false;
            }
        }

        //what's considered a pass here?
//			1. not inconsistent...  that's a non-passing problem. but it might be a passing issue on the message as a whole.  
        return buildResults(issues, passed);
    }
}
