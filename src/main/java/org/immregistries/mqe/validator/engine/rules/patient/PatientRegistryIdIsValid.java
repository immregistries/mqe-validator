package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientRegistryIdIsValid extends ValidationRule<MqePatient> {

  public PatientRegistryIdIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_REGISTRY_ID));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed = true;

    String regNum = target.getIdRegistryNumber();
    issues.addAll(codr.handleCode(regNum, VxuField.PATIENT_REGISTRY_ID, target));

    // if (this.common.isEmpty(regNum)) {
    // issues.add(MessageAttribute.get(VxuField.PATIENT_REGISTRY_ID,
    // IssueType.MISSING).build(target));
    // }

    // TODO PatientRegistryIdIsUnrecognized--can we use codr instead? I can't get it to work

    passed = issues.size() == 0;
    return buildResults(issues, passed);
  }

}
