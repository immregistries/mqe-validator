package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.List;

public class PatientProtectionIndicatorIsValid extends ValidationRule<DqaPatient> {

    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        String protectionCode = target.getProtectionCode();

        //TODO: QUESTION:  Should "Missing" be handled here, or in the codeHandler?
        //I'm thinking...  here.  No compelling reason yet.  just a feeling.
        // TODO: need to create test for protection indicator

        issues.addAll(this.codr.handleCode(protectionCode, VxuField.PATIENT_PROTECTION_INDICATOR));

        if ("Y".equals(protectionCode)) {
            issues.add(Detection.PatientProtectionIndicatorIsValuedAsYes.build(protectionCode));
        } else if ("N".equals(protectionCode)) {
            issues.add(Detection.PatientProtectionIndicatorIsValuedAsNo.build(protectionCode));
        }

        passed = (issues.size() == 0);

        return buildResults(issues, passed);
    }

}
