package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientMultipleBirthsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientMultipleBirthsValid() {
    ruleDetections.addAll(Arrays.asList(
        Detection.PatientBirthIndicatorIsMissing,
        Detection.PatientBirthOrderIsMissingAndMultipleBirthIndicated,
        Detection.PatientBirthOrderIsInvalid,
        Detection.PatientBirthIndicatorIsInvalid));
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_BIRTH_ORDER));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    String multipleBirthInd = target.getBirthMultipleInd();

    if (this.common.isEmpty(multipleBirthInd)) {
      issues.add(Detection.PatientBirthIndicatorIsMissing.build(target));
    } else {
      String birthOrder = target.getBirthOrder();

      if ("Y".equals(multipleBirthInd)) {
        // TODO: birth order codes aren't working for some reason
        issues
            .addAll(codr.handleCodeOrMissing(target.getBirthOrder(), VxuField.PATIENT_BIRTH_ORDER, target));

        if (this.common.isEmpty(birthOrder)) {
          issues.add(Detection.PatientBirthOrderIsMissingAndMultipleBirthIndicated.build(target));
        }
      } else if ("N".equals(multipleBirthInd)) {
        if (!this.common.isEmpty(birthOrder) && !"1".equals(birthOrder)) {
          issues.add(Detection.PatientBirthOrderIsInvalid.build(target));
        }
      } else {
        issues.add(Detection.PatientBirthIndicatorIsInvalid.build(target));
      }
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }
}
