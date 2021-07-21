package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.TargetType;
import org.immregistries.mqe.vxu.hl7.Id;

@ValidationRuleEntry(TargetType.Patient)
public class PatientPrimaryPhysicianNameIsValid extends ValidationRule<MqePatient> {


  public PatientPrimaryPhysicianNameIsValid() {
    this.addRuleDetection(Detection.PatientPrimaryPhysicianNameIsMissing);
    this.addRuleDetection(Detection.PatientPrimaryPhysicianNameIsPresent);
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    Id physician = target.getPhysician();

    if (physician != null) {
      if (physician.getName() == null || this.common.isEmpty(physician.getName())) {
        issues.add(Detection.PatientPrimaryPhysicianNameIsMissing.build(target));
        passed = false;
      } else {
        issues.add(Detection.PatientPrimaryPhysicianNameIsPresent.build(target));
      }
    }

    return buildResults(issues, passed);
  }

}
