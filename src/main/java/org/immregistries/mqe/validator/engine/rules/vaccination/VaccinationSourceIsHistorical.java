package org.immregistries.mqe.validator.engine.rules.vaccination;

import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

import java.util.ArrayList;
import java.util.List;

public class VaccinationSourceIsHistorical extends ValidationRule<MqeVaccination> {

    @Override
    protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
        List<ValidationReport> issues = new ArrayList<>();
        boolean passed;

        String sourceCd = target.getInformationSourceCode();

        switch (sourceCd) {
            case MqeVaccination.INFO_SOURCE_HIST:
                passed = true;
                break;
            default:
                passed = false;
                break;
        }

        return buildResults(issues, passed);
    }
}