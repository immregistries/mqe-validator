package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.VxuField;

/**
 * Created by Allison on 5/9/2017.
 */
public class PatientClassIsValid extends ValidationRule<DqaPatient> {

  public PatientClassIsValid() {
    ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.PATIENT_CLASS));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    issues.addAll(codr.handleCode(target.getPatientClass(), VxuField.PATIENT_CLASS, target));

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }
}
