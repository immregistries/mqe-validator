package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.hl7.Observation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allison on 5/9/2017.
 */
public class ObservationValueTypeIsValid extends ValidationRule<DqaVaccination> {
    @Override
    protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        for (Observation o : target.getObservations()) {
            issues.addAll(codr.handleCode(o.getValueTypeCode(), VxuField.OBSERVATION_VALUE_TYPE));
        }

        passed = issues.size() == 0;

        return buildResults(issues, passed);
    }
}
