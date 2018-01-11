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
        } else if (!"Y".equals(dInd)) {
            if (target.getDeathDate() != null || target.getDeathDateString() != null) {
                String deathDate = target.getDeathDate() != null ? target.getDeathDate().toString() : target.getDeathDateString();
                ValidationIssue issue = this.util.createIssue(Detection.PatientDeathIndicatorIsInconsistent, deathDate);
                issues.add(issue);
                passed = false;
            }
        }
        //what's considered a pass here?
//			1. not inconsistent...  that's a non-passing problem. but it might be a passing issue on the message as a whole.  
        return buildResults(issues, passed);
    }
}
