package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.codes.AdministeredLikelihood;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationIsAdministered extends ValidationRule<MqeVaccination> {

  AdministeredLikelihood confidenceCalculator = AdministeredLikelihood.INSTANCE;

  public VaccinationIsAdministered() {
    ruleDetections.addAll(Arrays.asList(
        Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical,
        Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    boolean administered = target.isAdministered();

    int administeredScore = confidenceCalculator.administeredLiklihoodScore(target, m);

    if (administered && administeredScore < 10) {
      issues.add(Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical
          .build(target));
    }
    if (!administered && administeredScore >= 10) {
      issues.add(Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered
          .build(target));
    }

    passed = administered;

    return buildResults(issues, passed);

  }
}
