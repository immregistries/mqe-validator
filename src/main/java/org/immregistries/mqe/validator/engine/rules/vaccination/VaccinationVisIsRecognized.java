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

public class VaccinationVisIsRecognized extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationVisIsPresent.class, VaccinationSourceIsAdministered.class};
  }

  public VaccinationVisIsRecognized() {
    this.addRuleDetection(Detection.VaccinationVisIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisIsMissing);
      id.setImplementationDescription(
          "Vaccination Vis is missing a document code, CVX and Published Date.");
      id.setHowToFix("Please ensure that Vaccine Information Statement was recorded properly on the patient record. ");
      id.setWhyToFix("Properly recording the Vaccine Information Statement in the originating medical system is important to qualify for coverage under the Vaccine Compensation Program. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    VaccinationVIS vis = target.getVaccinationVis();
    String visCvx = vis.getCvxCode();

    if (this.common.isEmpty(vis.getDocumentCode())
        && (this.common.isEmpty(visCvx) && vis.getPublishedDate() == null)) {
      issues.add(Detection.VaccinationVisIsMissing.build(target));
    }

    passed = issues.isEmpty();
    return buildResults(issues, passed);
  }
}
