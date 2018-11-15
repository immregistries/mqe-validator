package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.DetectionType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientSsnIsValid extends ValidationRule<MqePatient> {

  public PatientSsnIsValid() {
    this.addRuleDocumentation(Arrays.asList(Detection.get(VxuField.PATIENT_SSN, DetectionType.MISSING),
        Detection.get(VxuField.PATIENT_SSN, DetectionType.INVALID)));
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
