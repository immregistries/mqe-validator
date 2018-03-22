package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PatientFinancialStatusDateIsValid extends ValidationRule<DqaPatient> {
    @Override
    protected final Class[] getDependencies() {
        return new Class[]{
                PatientFinancialStatusCheckTrue.class,
                PatientBirthDateIsValid.class};
    }
    
    public PatientFinancialStatusDateIsValid() {
    	this.ruleDetections.addAll(Arrays.asList(
    			Detection.PatientVfcEffectiveDateIsBeforeBirth,
    			Detection.PatientVfcEffectiveDateIsInFuture,
    			Detection.PatientVfcEffectiveDateIsMissing
    	));
	}

    @Override
    protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
        List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
        boolean passed = true;

        Date finEligDte = target.getFinancialEligibilityDate();
        Date birthDate = target.getBirthDate();
        Date recDate = m.getReceivedDate();

        if (finEligDte != null) {
            if (this.datr.isBeforeDate(finEligDte, birthDate)) {
                issues.add(Detection.PatientVfcEffectiveDateIsBeforeBirth.build(target));
                passed = false;
            }

            if (this.datr.isBeforeDate(recDate, finEligDte)) {
                issues.add(Detection.PatientVfcEffectiveDateIsInFuture.build(target));
                passed = false;
            }
        } else {
            issues.add(Detection.PatientVfcEffectiveDateIsMissing.build(target));
            passed = false;
        }

        return buildResults(issues, passed);
    }

}
