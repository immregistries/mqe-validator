package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.VxuField;

public class PatientRegistryIdIsValid extends ValidationRule<DqaPatient> {

  public PatientRegistryIdIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_REGISTRY_ID));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
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
