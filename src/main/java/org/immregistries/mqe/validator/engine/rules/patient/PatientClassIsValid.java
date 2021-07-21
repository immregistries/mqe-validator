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

/**
 * Created by Allison on 5/9/2017.
 */
@ValidationRuleEntry(TargetType.Patient)
public class PatientClassIsValid extends ValidationRule<MqePatient> {

  public PatientClassIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientClassIsMissing);
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientClassIsUnrecognized);
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    issues
        .addAll(codr.handleCodeOrMissing(target.getPatientClass(), VxuField.PATIENT_CLASS, target));

    passed = verifyNoIssuesExceptPresent(issues);

    return buildResults(issues, passed);
  }
}
