package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.code.NokRelationship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NextOfKinRelationshipIsValidForUnderagedPatient extends ValidationRule<DqaNextOfKin> {

    @Override
    protected final Class[] getDependencies() {
        // TODO: needs to be tested from the frontend once PatientIsUnderage is working
        return new Class[]{PatientIsUnderage.class};
    }

	public NextOfKinRelationshipIsValidForUnderagedPatient() {
		this.ruleDetections.addAll(Arrays.asList(Detection.NextOfKinRelationshipIsUnexpected,
				Detection.NextOfKinRelationshipIsUnrecognized,
				Detection.NextOfKinRelationshipIsNotResponsibleParty,
				Detection.NextOfKinRelationshipIsMissing));
	}
	
    @Override
    protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        String relationship = target.getRelationshipCode();

        if (StringUtils.isNotEmpty(relationship)) {
            if (!target.isResponsibleRelationship()) {
                if (target.isChildRelationship()) {
                    // In most situations, an underage patient would not have a child, so this is unexpected (and
                    // probably indicates the relationship was entered backwards). However, it's not impossible, so
                    // it's just a warning.
                    issues.add(Detection.NextOfKinRelationshipIsUnexpected.build(relationship));
                } else if (NokRelationship.get(relationship) == NokRelationship.UNKNOWN) {
                    issues.add(Detection.NextOfKinRelationshipIsUnrecognized.build(relationship));
                } else {
                    issues.add(Detection.NextOfKinRelationshipIsNotResponsibleParty.build(relationship));
                }
            }
        } else {
            issues.add(Detection.NextOfKinRelationshipIsMissing.build(relationship));
        }

        passed = issues.size() == 0;

        return buildResults(issues, passed);
    }
}
