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
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientNameTypeCodeIsMissing);
      id.setHowToFix("The patient name type is not valued. Please contact your software vendor and ask them to "
          + "ensure that all names sent in the message include a name type code. ");
      id.setWhyToFix("The IIS uses the name for patient matching. Indicating the type of name being sent will help "
          + "the IIS match it correctly to other names that have been received.  ");
    }

    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientNameTypeCodeIsNotValuedLegal);
      id.setImplementationDescription("Patient Name Type is not 'L' for legal.");
      id.setHowToFix("The first name sent was not the legal name. Please contact your software vendor and ask them "
          + "to ensure that the first name sent is always the legal name and that it is designated as such. ");
      id.setWhyToFix("The IIS uses the name for patient matching. The legal name is generally stable and is routinely submitted "
          + "by other systems. ");
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
