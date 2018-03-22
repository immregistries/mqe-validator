package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinationCompletionStatusIsValid extends ValidationRule<DqaVaccination> {

    public VaccinationCompletionStatusIsValid() {
        ruleDetections.addAll(Arrays.asList(
            Detection.VaccinationCompletionStatusIsValuedAsCompleted,
            Detection.VaccinationCompletionStatusIsValuedAsRefused,
            Detection.VaccinationCompletionStatusIsValuedAsNotAdministered,
            Detection.VaccinationCompletionStatusIsValuedAsPartiallyAdministered,
            Detection.VaccinationCompletionStatusIsUnrecognized));
        ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_COMPLETION_STATUS));
    }

    @Override protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

        List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
        boolean passed = true;

        // If vaccination is not actually administered then this is a waiver. Need
        // to check that now, here to see if we need to enforce a value in RXA-9 to
        // indicate that the vaccination is historical or administered.
        // By default we assume that the vaccination was completed.

        String completion = target.getCompletion();

        issues.addAll(this.codr.handleCode(completion, VxuField.VACCINATION_COMPLETION_STATUS, target));
        if (issues.size() > 0) {
            passed = false;
        }
        String completionCode = target.getCompletionCode();
        Detection detection;
        switch (completionCode) {
            case DqaVaccination.COMPLETION_COMPLETED:
                detection = Detection.VaccinationCompletionStatusIsValuedAsCompleted;
                break;
            case DqaVaccination.COMPLETION_REFUSED:
                detection = Detection.VaccinationCompletionStatusIsValuedAsRefused;
                break;
            case DqaVaccination.COMPLETION_NOT_ADMINISTERED:
                detection = Detection.VaccinationCompletionStatusIsValuedAsNotAdministered;
                break;
            case DqaVaccination.COMPLETION_PARTIALLY_ADMINISTERED:
                detection = Detection.VaccinationCompletionStatusIsValuedAsPartiallyAdministered;
                break;
            default:
                detection = Detection.VaccinationCompletionStatusIsUnrecognized;
        }
        issues.add(detection.build(completionCode, target));
        return buildResults(issues, passed);

    }
}
