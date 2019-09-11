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
	  this.addRuleDetections(codr.getDetectionsForField(VxuField.PATIENT_ETHNICITY));
	  
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientEthnicityIsUnrecognized);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientEthnicityIsInvalid);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientEthnicityIsDeprecated);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientEthnicityIsMissing);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }

  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    String patientEthnicityString = target.getEthnicity();
    
	issues.addAll(codr.handleCodeOrMissing(patientEthnicityString, VxuField.PATIENT_ETHNICITY, target));
	passed = issues.isEmpty();
    
    return buildResults(issues, passed);
  }
}
