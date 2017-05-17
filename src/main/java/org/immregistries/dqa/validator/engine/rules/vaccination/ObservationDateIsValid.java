package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.hl7.Observation;

import java.util.ArrayList;
import java.util.List;

/**
 * Currently only checks if the date is present or missing. Created by Allison on 5/9/2017.
 */
public class ObservationDateIsValid extends ValidationRule<DqaVaccination> {
    @Override
    protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        for (Observation o : target.getObservations()) {
            String observationDateString = o.getObservationDateString();
            if (StringUtils.isEmpty(observationDateString)) {
                issues.add(MessageAttribute.ObservationDateTimeOfObservationIsMissing.build(observationDateString));
            }
        }

        passed = issues.size() == 0;

        return buildResults(issues, passed);
    }
}
