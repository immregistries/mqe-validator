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

public class PatientMultipleBirthsValid extends ValidationRule<DqaPatient> {

    @Override
    protected final Class[] getDependencies() {
        return new Class[]{PatientExists.class};
    }

    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        String multipleBirthInd = target.getBirthMultipleInd();

        if (common.isEmpty(multipleBirthInd)) {
            issues.add(Detection.PatientBirthIndicatorIsMissing.build());
        } else {
            String birthOrder = target.getBirthOrderNumber();

            if ("Y".equals(multipleBirthInd)) {
                //TODO: birth order codes aren't working for some reason
                issues.addAll(codr.handleCode(target.getBirthOrder(), VxuField.PATIENT_BIRTH_ORDER));

                if (common.isEmpty(birthOrder)) {
                    issues.add(Detection.PatientBirthOrderIsMissingAndMultipleBirthIndicated.build());
                }
            } else if ("N".equals(multipleBirthInd)) {
                if (!common.isEmpty(birthOrder) && !"1".equals(birthOrder)) {
                    issues.add(Detection.PatientBirthOrderIsInvalid.build());
                }
            } else {
                issues.add(Detection.PatientBirthIndicatorIsInvalid.build());
            }
        }

        passed = issues.size() == 0;

        return buildResults(issues, passed);
    }
}
