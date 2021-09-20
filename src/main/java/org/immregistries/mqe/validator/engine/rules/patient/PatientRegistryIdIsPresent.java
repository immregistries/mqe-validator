package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Patient)
public class PatientRegistryIdIsPresent extends ValidationRule<MqePatient> {

  public PatientRegistryIdIsPresent() {
    this.addRuleDetection(Detection.PatientRegistryIdIsMissing);
    this.addRuleDetection(Detection.PatientRegistryIdIsPresent);
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    String regNum = target.getIdRegistryNumber();
    if (StringUtils.isEmpty(regNum)) {
      issues.add(Detection.PatientRegistryIdIsMissing.build(target));
    } else {
      issues.add(Detection.PatientRegistryIdIsPresent.build(target));
    }
    boolean passed = issues.size() == 0;
    return buildResults(issues, passed);
  }

}
