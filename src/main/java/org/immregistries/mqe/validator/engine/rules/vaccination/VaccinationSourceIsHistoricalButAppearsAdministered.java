package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.codes.AdministeredLikelihood;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationSourceIsHistoricalButAppearsAdministered
    extends ValidationRule<MqeVaccination> {

  AdministeredLikelihood confidenceCalculator = AdministeredLikelihood.INSTANCE;

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationSourceIsHistoricalButAppearsAdministered() {
    {
      ImplementationDetail id = this.addRuleDetection(
          Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical);
      id.setImplementationDescription(
          "Vaccination information source is reported as administered, but based on our scoring calculation (how recently shot was given and how much data is known about the shot) the shot seems to be historical.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    int administeredScore = confidenceCalculator.administeredLikelihoodScore(target, m);

    if (administeredScore < 10) {
      issues.add(
          Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical.build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }
}
