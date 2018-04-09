package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.PatientImmunity;
import org.immregistries.dqa.vxu.VxuField;

public class PatientImmunityIsValid extends ValidationRule<DqaPatient> {

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
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
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
