package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Patient)
public class PatientAliasIsPresent extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class,};
  }

  public PatientAliasIsPresent() {
    this.addRuleDetection(Detection.PatientAliasIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAliasIsMissing);
      id.setImplementationDescription(
          "Patient is missing values for both first and last alias names.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientAliasIsPresent);
      id.setImplementationDescription(
          "Patient has values for first and/or last alias names.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String aliasFirst = target.getAliasFirst();
    String aliasLast = target.getAliasLast();

    if (this.common.isEmpty(aliasLast + aliasFirst)) {
      issues.add(Detection.PatientAliasIsMissing.build(target));
      passed = false;
    } else {
      issues.add(Detection.PatientAliasIsPresent.build(target));
    }

    return buildResults(issues, passed);
  }
}
