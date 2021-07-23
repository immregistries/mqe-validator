package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.TargetType;
import org.immregistries.mqe.vxu.VxuField;

@ValidationRuleEntry(TargetType.Patient)
public class PatientEthnicityIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientEthnicityIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientEthnicityIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    this.addRuleDetection(Detection.PatientEthnicityIsInvalid);
    this.addRuleDetection(Detection.PatientEthnicityIsDeprecated);
    this.addRuleDetection(Detection.PatientEthnicityIsMissing);
    this.addRuleDetection(Detection.PatientEthnicityIsPresent);
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    String patientEthnicityString = target.getEthnicity();

    issues.addAll(
        codr.handleCodeOrMissing(patientEthnicityString, VxuField.PATIENT_ETHNICITY, target));
    passed = issues.isEmpty();

    return buildResults(issues, passed);
  }
}

