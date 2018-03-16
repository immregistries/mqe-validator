package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allison on 5/9/2017.
 */
public class PatientClassIsValid extends ValidationRule<DqaPatient> {
	
	public PatientClassIsValid() {
		ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_CLASS));
	}
	
    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        issues.addAll(codr.handleCode(target.getPatientClass(), VxuField.PATIENT_CLASS));

        passed = (issues.size() == 0);

        return buildResults(issues, passed);
    }
}
