package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientMultipleBirthsValid extends ValidationRule<DqaPatient> {

    @Override
    protected final Class[] getDependencies() {
        return new Class[]{PatientExists.class};
    }

    public PatientMultipleBirthsValid() {
    	ruleDetections.addAll(Arrays.asList(
    			Detection.PatientBirthIndicatorIsMissing,
    			Detection.PatientBirthOrderIsMissingAndMultipleBirthIndicated,
    			Detection.PatientBirthOrderIsInvalid,
    			Detection.PatientBirthIndicatorIsInvalid
    	));
    	ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_BIRTH_ORDER));
	}
    
    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
        List<ValidationDetection> issues = new ArrayList<>();
        boolean passed;

        String multipleBirthInd = target.getBirthMultipleInd();

        if (this.common.isEmpty(multipleBirthInd)) {
            issues.add(Detection.PatientBirthIndicatorIsMissing.build(target));
        } else {
            String birthOrder = target.getBirthOrderNumber();

            if ("Y".equals(multipleBirthInd)) {
                //TODO: birth order codes aren't working for some reason
                issues.addAll(codr.handleCode(target.getBirthOrder(), VxuField.PATIENT_BIRTH_ORDER, target));

                if (this.common.isEmpty(birthOrder)) {
                    issues.add(Detection.PatientBirthOrderIsMissingAndMultipleBirthIndicated.build(target));
                }
            } else if ("N".equals(multipleBirthInd)) {
                if (!this.common.isEmpty(birthOrder) && !"1".equals(birthOrder)) {
                    issues.add(Detection.PatientBirthOrderIsInvalid.build(target));
                }
            } else {
                issues.add(Detection.PatientBirthIndicatorIsInvalid.build(target));
            }
        }

        passed = issues.size() == 0;

        return buildResults(issues, passed);
    }
}
