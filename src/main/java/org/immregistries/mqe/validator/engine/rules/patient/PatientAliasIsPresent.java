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

public class PatientAliasIsPresent extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class,};
  }

  public PatientAliasIsPresent() {
    this.addRuleDetection(Detection.PatientAliasIsMissing);
    ImplementationDetail id = this.addRuleDetection(Detection.PatientAliasIsMissing);id.setImplementationDescription("Patient is missing values for both first and last alias names.");
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
    }

    return buildResults(issues, passed);
  }
}
