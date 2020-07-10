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

public class PatientSsnIsValid extends ValidationRule<MqePatient> {

  public PatientSsnIsValid() {
    this.addRuleDetection(Detection.PatientSsnIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientSsnIsInvalid);
      id.setImplementationDescription(
          "Patient SSN cannot start with 000 or have 00 in the middle section. It must be comprised of exactly 9 digits. It cannot be equal to '123456789' or '987654321'. It cannot have 6 consective digits that are the same.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String ssn = target.getIdSsnNumber();

    if (this.common.isEmpty(ssn)) {
      issues.add(Detection.get(VxuField.PATIENT_SSN, DetectionType.MISSING).build(target));
      passed = false;
    } else if (!isSsnPattern(ssn)) {
      issues.add(Detection.get(VxuField.PATIENT_SSN, DetectionType.INVALID).build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }

  protected boolean isSsnPattern(String val) {
    if ((val.indexOf("000") == 0) || (val.indexOf("00", 3) == 3) || (!val.matches("[0-9]{9}"))
        || (val.equals("123456789")) || val.equals("987654321")
        || (common.hasTooManyConsecutiveChars(val, 6))) {
      return false;
    }

    return true;
  }

}
