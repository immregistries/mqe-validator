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

@ValidationRuleEntry(TargetType.Patient)
public class PatientExists extends ValidationRule<MqePatient> {

  public PatientExists() {
    this.addRuleDetection(Detection.PatientObjectIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientObjectIsMissing);
      id.setImplementationDescription("Verifies that the patient object was created and patient detections can be executed");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientObjectIsPresent);
      id.setImplementationDescription("Verifies that the patient object was created and patient detections can be executed");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target == null) {
      issues.add(Detection.PatientObjectIsMissing.build(target));
      passed = false;
    } else {
      issues.add(Detection.PatientObjectIsPresent.build(target));
    }

    return buildResults(issues, passed);
  }


}
