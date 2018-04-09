package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientGenderIsValid extends ValidationRule<DqaPatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class,};
  }

  /*
   * This is the money:
   */

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    // TODO: code recieved stuff.
    // handleCodeReceived(patient.getSex(), PotentialIssues.Field.PATIENT_GENDER);

    return buildResults(issues, passed);
  }
}
