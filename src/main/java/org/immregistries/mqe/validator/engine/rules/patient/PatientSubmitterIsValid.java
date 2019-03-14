package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.DetectionType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientSubmitterIsValid extends ValidationRule<MqePatient> {

  public PatientSubmitterIsValid() {
    this.addRuleDocumentation(Arrays.asList(
        Detection.PatientSubmitterIdIsMissing,
        Detection.PatientSubmitterIdAuthorityIsMissing,
        Detection.PatientSubmitterIdTypeCodeIsMissing));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String submitterNumStr = target.getIdSubmitterNumber();
    String assignAuthCodeStr = target.getIdSubmitterAssigningAuthorityCode();
    String submitterTypeCdStr = target.getIdSubmitterTypeCode();

    if (this.common.isEmpty(submitterNumStr)) {

      issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID, DetectionType.MISSING).build(target));
      passed = false;

      if (this.common.isEmpty(assignAuthCodeStr)) {
        issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, DetectionType.MISSING)
            .build(target));
      }

      if (this.common.isEmpty(submitterTypeCdStr)) {
        issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, DetectionType.MISSING)
            .build(target));
      }
    }

    return buildResults(issues, passed);
  }

}
