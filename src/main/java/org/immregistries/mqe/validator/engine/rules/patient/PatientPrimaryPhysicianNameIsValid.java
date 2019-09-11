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
import org.immregistries.mqe.vxu.hl7.Id;

public class PatientPrimaryPhysicianNameIsValid extends ValidationRule<MqePatient> {


  public PatientPrimaryPhysicianNameIsValid() {
    this.addRuleDetection(Detection.PatientPrimaryPhysicianNameIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPrimaryPhysicianIdIsMissing);
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
    Id physician = target.getPhysician();

    if (physician != null) {
      if (physician.getName() == null || this.common.isEmpty(physician.getName())) {
        issues.add(Detection.PatientPrimaryPhysicianNameIsMissing.build(target));
        passed = false;
      }
    }

    return buildResults(issues, passed);
  }

}
