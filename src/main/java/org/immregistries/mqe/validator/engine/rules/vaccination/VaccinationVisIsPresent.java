package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;
import org.immregistries.mqe.vxu.VaccinationVIS;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationVisIsPresent extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }


  public VaccinationVisIsPresent() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisIsMissing);
      id.setImplementationDescription(
          "Administered Vaccine is missing VIS or VIS is missing a document code, CVX and Published Date.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationVisIsPresent);
      id.setImplementationDescription(
          "Administered Vaccine has VIS document code, CVX and Published Date.");
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
      issues.add(Detection.VaccinationVisIsPresent.build(target));
      passed = true;
    }

    return buildResults(issues, passed);
  }
}
