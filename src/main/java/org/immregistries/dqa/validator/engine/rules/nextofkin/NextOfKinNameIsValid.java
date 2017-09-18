package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;

import java.util.ArrayList;
import java.util.List;

public class NextOfKinNameIsValid extends ValidationRule<DqaNextOfKin> {

    @Override
    protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

        List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
        boolean passed;

        String first = target.getNameFirst();
        String last = target.getNameLast();

        if (StringUtils.isEmpty(first) && StringUtils.isEmpty(last)) {
            issues.add(Detection.NextOfKinNameIsMissing.build(first + " " + last));
        } else {
            if (StringUtils.isEmpty(first)) {
                issues.add(Detection.NextOfKinNameFirstIsMissing.build(first));
            }

            if (StringUtils.isEmpty(last)) {
                issues.add(Detection.NextOfKinNameLastIsMissing.build(last));
            }
        }

        passed = (issues.size() == 0);

        return buildResults(issues, passed);
    }

}
