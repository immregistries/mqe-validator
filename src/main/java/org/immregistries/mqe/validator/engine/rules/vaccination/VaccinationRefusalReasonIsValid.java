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
import org.immregistries.mqe.vxu.VxuField;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationRefusalReasonIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationRefusalReasonIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationRefusalReasonConflictsCompletionStatus);
      id.setImplementationDescription(
          "Vaccination is marked as completed but refusal code was given.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationRefusalReasonIsMissing);
      id.setImplementationDescription(
          "Vaccination completion was refused but refusal code is missing. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();

    boolean passed = true;

    if (target.isCompletionCompleted() && !this.common.isEmpty(target.getRefusalCode())) {
      issues.add(Detection.VaccinationRefusalReasonConflictsCompletionStatus.build(target));
    }

    if (target.isCompletionRefused()) {
      issues.addAll(codr.handleCodeOrMissing(target.getRefusal(),
          VxuField.VACCINATION_REFUSAL_REASON, target));
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);

  }
}
