package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VaccinationVIS;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationVisCvxIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationVisIsPresent.class, VaccinationIsAdministered.class};
  }

  public VaccinationVisCvxIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_VIS_CVX_CODE));
  }


  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    VaccinationVIS vis = target.getVaccinationVis();
    String visCvx = vis.getCvxCode();

    issues.addAll(codr.handleCode(visCvx, VxuField.VACCINATION_VIS_CVX_CODE, target));

    passed = issues.isEmpty();
    return buildResults(issues, passed);
  }
}
