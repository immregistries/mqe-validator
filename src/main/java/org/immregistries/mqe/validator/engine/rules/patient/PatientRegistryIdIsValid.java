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

public class PatientRegistryIdIsValid extends ValidationRule<MqePatient> {
  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientRegistryIdIsPresent.class, PatientExists.class};
  }

  public PatientRegistryIdIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRegistryIdIsMissing);
      id.setHowToFix(
          "The IIS registry ID was not recognized. Please contact your software vendor and the IIS to verify the need for sending "
              + "the IIS registry ID. ");
      id.setWhyToFix(
          "The IIS ID is used for identifying the specific patient that is to be updated or queried in the IIS. "
              + "This is normally used in queries and is not expected on updates. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRegistryIdIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix(
          "The IIS registry ID was not recognized. Please contact your software vendor to verify the need for sending "
              + "the IIS registry ID. ");
      id.setWhyToFix(
          "The IIS ID is used for identifying the specific patient that is to be updated or queried in the IIS. "
              + "This is normally used in queries and is not expected on updates. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    String regNum = target.getIdRegistryNumber();
    List<ValidationReport> issues =
        new ArrayList<>(codr.handleCode(regNum, VxuField.PATIENT_REGISTRY_ID, target));
    // TODO PatientRegistryIdIsUnrecognized--can we use codr instead? I can't get it to work
    boolean passed = issues.size() == 0;
    return buildResults(issues, passed);
  }

}
