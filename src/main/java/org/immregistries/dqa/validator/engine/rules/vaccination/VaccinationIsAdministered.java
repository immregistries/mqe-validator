package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.AdministeredLikelihood;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationIsAdministered extends ValidationRule<DqaVaccination> {

  AdministeredLikelihood confidenceCalculator = AdministeredLikelihood.INSTANCE;

  public VaccinationIsAdministered() {
    ruleDetections.addAll(Arrays.asList(
        Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical,
        Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

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
