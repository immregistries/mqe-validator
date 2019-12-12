package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.codes.AdministeredLikelihood;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationSourceIsAdministeredButAppearsHistorical
    extends ValidationRule<MqeVaccination> {

  AdministeredLikelihood confidenceCalculator = AdministeredLikelihood.INSTANCE;

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsHistorical.class};
  }

  public VaccinationSourceIsAdministeredButAppearsHistorical() {
    {
      ImplementationDetail id = this.addRuleDetection(
          Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered);
      id.setImplementationDescription(
          "Vaccination information source is reported as historical but based on our scoring calculation (how recently shot was given and how much data is known about the shot) the shot seems to be administered.");
      id.setHowToFix("The vaccination is reported as historical but looks more like an administered vaccinatin. Please review the vaccination event and ensure that it is properly recorded as historical or administered. ");
      id.setWhyToFix("Reporting information as historical means the vaccination will not be decremented from inventory and will be given a lower priority in representing the vaccination event if it matches with other reports of the same vaccination. It is important to report administered vaccinations as administered. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    int administeredScore = confidenceCalculator.administeredLikelihoodScore(target, m);

    if (administeredScore >= 10) {
      issues.add(Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered
          .build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }
}
