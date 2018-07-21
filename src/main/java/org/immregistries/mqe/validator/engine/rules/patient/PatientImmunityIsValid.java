package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.PatientImmunity;
import org.immregistries.mqe.vxu.VxuField;

public class PatientImmunityIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
    // PatientBirthDateIsValid.class
    };
  }

  public PatientImmunityIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.PATIENT_IMMUNITY_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    for (PatientImmunity imm : target.getPatientImmunityList()) {
      String immCode = imm.getImmunityCode();
      issues.addAll(codr.handleCode(immCode, VxuField.PATIENT_IMMUNITY_CODE, target));
    }

    passed = issues.isEmpty();

    return buildResults(issues, passed);
  }

}
