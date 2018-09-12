package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationAdminCodeCptIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationUseCptInsteadOfCvx.class};
  }

  public VaccinationAdminCodeCptIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_CPT_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String cpt = target.getAdminCptCode();

    issues.addAll(codr.handleCode(cpt, VxuField.VACCINATION_CPT_CODE, target));

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
