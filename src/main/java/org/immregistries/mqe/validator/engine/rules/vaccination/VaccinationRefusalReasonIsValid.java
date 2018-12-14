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

public class VaccinationRefusalReasonIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class};
  }

  public VaccinationRefusalReasonIsValid() {
    this.addRuleDocumentation(Arrays.asList(
        Detection.VaccinationRefusalReasonConflictsCompletionStatus,
        Detection.VaccinationRefusalReasonIsMissing));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.VACCINATION_REFUSAL_REASON));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();

    boolean passed = true;

    if (target.isCompletionCompleted() && !this.common.isEmpty(target.getRefusalCode())) {
      issues.add(Detection.VaccinationRefusalReasonConflictsCompletionStatus.build(target));
    }

    if (target.isCompletionRefused()) {
      if (this.common.isEmpty(target.getRefusalCode())) {
        issues.add(Detection.VaccinationRefusalReasonIsMissing.build(target));
      } else {
        issues.addAll(codr.handleCode(target.getRefusal(), VxuField.VACCINATION_REFUSAL_REASON,
            target));
      }
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);

  }
}
