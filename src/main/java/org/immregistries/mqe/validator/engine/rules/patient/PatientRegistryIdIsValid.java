package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientRegistryIdIsValid extends ValidationRule<MqePatient> {
  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientRegistryIdIsPresent.class, PatientExists.class};
  }

  public PatientRegistryIdIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_REGISTRY_ID));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    String regNum = target.getIdRegistryNumber();
    List<ValidationReport> issues = new ArrayList<>(
        codr.handleCode(regNum, VxuField.PATIENT_REGISTRY_ID, target));
    // TODO PatientRegistryIdIsUnrecognized--can we use codr instead? I can't get it to work
    boolean passed = issues.size() == 0;
    return buildResults(issues, passed);
  }

}
