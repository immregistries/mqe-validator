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

public class VaccinationSourceIsAdministered extends ValidationRule<MqeVaccination> {

  AdministeredLikelihood confidenceCalculator = AdministeredLikelihood.INSTANCE;

  public VaccinationSourceIsAdministered() {
    this.addRuleDocumentation(Arrays.asList(
        Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical,
        Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered));
    this.addImplementationMessage(Detection.VaccinationInformationSourceIsAdministeredButAppearsToHistorical, "Vaccination information source is reported as administered but based on our scoring calculation (how recently shot was given and how much data is known about the shot) the shot seems to be historical.");
    this.addImplementationMessage(Detection.VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered, "Vaccination information source is reported as historical but based on our scoring calculation (how recently shot was given and how much data is known about the shot) the shot seems to be administered.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String sourceCd = target.getInformationSourceCode();
    boolean administered;
    
    switch (sourceCd) {
	    case MqeVaccination.INFO_SOURCE_ADMIN:
	    	administered = true;
	      break;
	    default:
	    	administered = false;
	      break;
    }
    
    int administeredScore = confidenceCalculator.administeredLikelihoodScore(target, m);

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
