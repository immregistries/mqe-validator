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

public class PatientGenderIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientGenderIsValid() {
	  this.addRuleDetections(codr.getDetectionsForField(VxuField.PATIENT_GENDER));
	  
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsDeprecated);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsInvalid);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsMissing);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsUnrecognized);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsDeprecated);
        // TODO Complete ImplementationDescription
        id.setImplementationDescription("");
        // TODO Complete HowToFix
        id.setHowToFix("");
        // TODO Complete WhyToFix
        id.setWhyToFix("");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsDeprecated);
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
	    String patientGenderString = target.getSex();
	    
    	issues.addAll(codr.handleCodeOrMissing(patientGenderString, VxuField.PATIENT_GENDER, target));
    	passed = issues.isEmpty();
	    
	    return buildResults(issues, passed);
  }
}
