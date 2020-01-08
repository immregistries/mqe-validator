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

/**
 * Created by Allison on 5/9/2017.
 */
public class PatientClassIsValid extends ValidationRule<MqePatient> {

  public PatientClassIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientClassIsMissing);
      id.setHowToFix("Ask your software vendor to please indicate that patient class as recurring patient. ");
      id.setWhyToFix("Messaging standards may require the patient class be set. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientClassIsUnrecognized);
      id.setHowToFix("Ask your software vendor to please ensure that patient class is properly encoded. ");
      id.setWhyToFix("Messaging standards require the patient class be set with a recognized value. ");
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    issues
        .addAll(codr.handleCodeOrMissing(target.getPatientClass(), VxuField.PATIENT_CLASS, target));

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }
}
