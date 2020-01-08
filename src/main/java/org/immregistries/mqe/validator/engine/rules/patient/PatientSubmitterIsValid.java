package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.DetectionType;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientSubmitterIsValid extends ValidationRule<MqePatient> {

  public PatientSubmitterIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientSubmitterIdIsMissing);
      id.setHowToFix("The patient submitter ID (or medical record number) was not valued. Please contact "
          + "your software vendor and request that they populate all messages with the medical record number. ");
      id.setWhyToFix("The patient submitter ID or medical record number is a unique id assigned to a patient "
          + "by the submitting system. This id must not be reassigned to other patients althought it might be retired "
          + "in favor of other codes when records are merged. The IIS needs this to track the identity of records from "
          + "submitters. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientSubmitterIdAuthorityIsMissing);
      id.setHowToFix("The patient submitter ID or medical record number is not encoded correctly in the message. "
          + "Please contact your software vendor and ask them to insure that all submitted ids have an authority "
          + "indicated. ");
      id.setWhyToFix("The assigning authority may be used by the IIS to distinguish between different EHR systems that "
          + "assign medical record numbers.  ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientSubmitterIdTypeCodeIsMissing);
      id.setHowToFix("The patient submitter ID or medical record number is not encoded correctly in the message. "
          + "Please contact your software vendor and ask them to insure that all submitted ids have an id type indicated. ");
      id.setWhyToFix("The id type is used by IIS to properly identify the medical record number. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String submitterNumStr = target.getIdSubmitterNumber();
    String assignAuthCodeStr = target.getIdSubmitterAssigningAuthorityCode();
    String submitterTypeCdStr = target.getIdSubmitterTypeCode();

    if (this.common.isEmpty(submitterNumStr)) {

      issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID, DetectionType.MISSING).build(target));
      passed = false;

      if (this.common.isEmpty(assignAuthCodeStr)) {
        issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, DetectionType.MISSING)
            .build(target));
      }

      if (this.common.isEmpty(submitterTypeCdStr)) {
        issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, DetectionType.MISSING)
            .build(target));
      }
    }

    return buildResults(issues, passed);
  }

}
