package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientNameTypeIsValid extends ValidationRule<MqePatient> {

  public PatientNameTypeIsValid() {
    ruleDetections.addAll(Arrays.asList(Detection.PatientNameTypeCodeIsMissing));
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target != null && target.getName() != null) {
      String type = target.getName().getType();
      if (this.common.isEmpty(type)) {
        issues.add(Detection.PatientNameTypeCodeIsMissing.build(target));
      }

      // TODO: code received stuff.
      // handleCodeReceived(target.getName().getType(),
      // PotentialIssues.Field.PATIENT_NAME_TYPE_CODE);
    }

    return buildResults(issues, passed);
  }

}
