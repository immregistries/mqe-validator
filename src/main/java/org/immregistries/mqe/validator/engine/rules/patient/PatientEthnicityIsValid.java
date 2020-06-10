package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

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
      id.setHowToFix("The patient ethnicity is indicated with a code that is not recognized. "
          + "Please contact your software vendor and request that the ethnicity codes be updated to the current ones. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating ethnicity helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. Sending the correct ethnicity code will ensure the IIS properly receives this information. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientEthnicityIsInvalid);
      id.setHowToFix(
          "The patient ethnicity is indicated with a code that should no longer be used. "
              + "Please contact your software vendor and request that the ethnicity codes be updated to the current ones. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating ethnicity helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. Sending the correct ethnicity code will ensure the IIS properly receives this information. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientEthnicityIsDeprecated);
      id.setHowToFix("The patient ethnicity is indicated with an old code. "
          + "Please contact your software vendor and request that the ethnicity codes be updated to the current ones. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating ethnicity helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. Sending the correct ethnicity code will ensure the IIS properly receives this information. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientEthnicityIsMissing);
      id.setHowToFix(
          "The patient ethnicity is not indicated. Please verify that this is coded in the medical record. "
              + "If it is, then contact your software vendor and request that it be coded in the message. ");
      id.setWhyToFix("Vaccine programs must reach the entire population and all communities. "
          + "Indicating ethnicity helps immunization programs ensure that the entire community is being reached and "
          + "protected from disease. ");
    }

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
