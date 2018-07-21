package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VaccinationVIS;

public class VaccinationVisIsPresent extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {

    };
  }

  public VaccinationVisIsPresent() {
    ruleDetections.add(Detection.VaccinationVisIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    if (target.isAdministered()) {
      VaccinationVIS vis = target.getVaccinationVis();
      if (vis == null
          || (this.common.isEmpty(vis.getDocumentCode()) && (this.common.isEmpty(vis.getCvxCode()) && vis
              .getPublishedDate() == null))) {
        issues.add(Detection.VaccinationVisIsMissing.build(target));
      } else {
        passed = true;
      }
    }

    return buildResults(issues, passed);
  }
}
