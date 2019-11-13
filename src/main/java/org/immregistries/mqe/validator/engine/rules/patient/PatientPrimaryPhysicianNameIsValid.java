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
      id.setHowToFix("Please ask your software vendor to ensure that the patient's primary care physician is encoded in the message. ");
      id.setWhyToFix("The primary physician may be used by the IIS to determine the medical home for this record or to help "
          + "document the source of the data. ");
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
