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

public class PatientNameTypeIsValid extends ValidationRule<MqePatient> {

  public PatientNameTypeIsValid() {
    this.addRuleDetection(Detection.PatientNameTypeCodeIsMissing);
    this.addRuleDetection(Detection.PatientNameTypeCodeIsPresent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientNameTypeCodeIsNotValuedLegal);
      id.setImplementationDescription("Patient Name Type is not 'L' for legal.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target != null && target.getName() != null) {
      String type = target.getName().getType();

      issues.addAll(this.codr.handleCodeOrMissing(type, VxuField.PATIENT_NAME_TYPE_CODE, target));

      // name code is supposed to be L for legal
      if (!"L".equals(target.getNameTypeCode())) {
        issues.add(Detection.PatientNameTypeCodeIsNotValuedLegal.build(target));
      }
    }

    return buildResults(issues, passed);
  }

}
