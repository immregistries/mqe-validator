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
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsDeprecated);
        id.setHowToFix("The patient sex is set with an older code that should no longer be used. "
            + "Please contact your software vendor and request that correct sex codes be used. ");
        id.setWhyToFix("Patient sex may be used for patient matching purposes or to ensure the correct vaccination is recommended. ");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsInvalid);
        id.setHowToFix("The patient sex is set with an invalid code that should no longer be used. "
            + "Please contact your software vendor and request that correct sex codes be used. ");
        id.setWhyToFix("Patient sex may be used for patient matching purposes or to ensure the correct vaccination is recommended. ");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsMissing);
        id.setHowToFix("The patient sex is not coded. "
            + "Please verify that this information is recorded in the medical record. ");
        id.setWhyToFix("Patient sex may be used for patient matching purposes or to ensure the correct vaccination is recommended. ");
      }
      {
        ImplementationDetail id =
            this.addRuleDetection(Detection.PatientGenderIsUnrecognized);
        id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
        id.setHowToFix("The patient sex is set with an code that is not understood. "
            + "Please contact your software vendor and request that correct sex codes be used. ");
        id.setWhyToFix("Patient sex may be used for patient matching purposes or to ensure the correct vaccination is recommended. ");
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
