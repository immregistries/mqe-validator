package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VaccinationVIS;

public class VaccinationVisIsPresent extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }


  public VaccinationVisIsPresent() {
    this.addRuleDetection(Detection.VaccinationVisIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisIsMissing);
      id.setImplementationDescription(
          "Administered Vaccine is missing VIS or VIS is missing a document code, CVX and Published Date.");
      id.setHowToFix("Please review the codes used to report VIS statements presented and correct. ");
      id.setWhyToFix("Properly recording the Vaccine Information Statement in the originating medical system is important to qualify for coverage under the Vaccine Compensation Program. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    VaccinationVIS vis = target.getVaccinationVis();
    if (vis == null || (this.common.isEmpty(vis.getDocumentCode())
        && (this.common.isEmpty(vis.getCvxCode()) && vis.getPublishedDate() == null))) {
      issues.add(Detection.VaccinationVisIsMissing.build(target));
    } else {
      passed = true;
    }

    return buildResults(issues, passed);
  }
}
