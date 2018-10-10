package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationInformationSourceIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministeredOrHistorical.class};
  }

  public VaccinationInformationSourceIsValid() {
    ruleDetections.addAll(Arrays.asList(Detection.VaccinationInformationSourceIsMissing,
        Detection.VaccinationInformationSourceIsValuedAsAdministered,
        Detection.VaccinationInformationSourceIsValuedAsHistorical));

    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_INFORMATION_SOURCE));
  }

  /*
   * This is the money:
   */

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String sourceCd = target.getInformationSourceCode();

    if (this.common.isEmpty(sourceCd)) {
      issues.add(Detection.VaccinationInformationSourceIsMissing.build(target));
      passed = false;
    } else {

      issues.addAll(this.codr.handleCode(target.getInformationSource(),
          VxuField.VACCINATION_INFORMATION_SOURCE, target));

      if (issues.size() > 0) {
        passed = false;
      }

      switch (sourceCd) {
        case MqeVaccination.INFO_SOURCE_ADMIN:
          issues.add(Detection.VaccinationInformationSourceIsValuedAsAdministered.build((sourceCd),
              target));
          break;
        default:
          issues.add(Detection.VaccinationInformationSourceIsValuedAsHistorical.build((sourceCd),
              target));
          break;
      }
    }

    return buildResults(issues, passed);
  }
}
