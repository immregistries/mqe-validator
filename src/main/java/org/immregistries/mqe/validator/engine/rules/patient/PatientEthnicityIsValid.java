package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientEthnicityIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class,};
  }

  /*
   * This is the money:
   */

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    // TODO: need to figure out what to do with this.
    // handleCodeReceived(patient.getEthnicity(), PotentialIssues.Field.PATIENT_ETHNICITY);
    // Does it make sense to keep all of these in the CodesAreValid class?

    return buildResults(issues, passed);
  }
}
