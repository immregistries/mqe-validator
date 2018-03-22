package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientAliasIsValid extends ValidationRule<DqaPatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class,};
  }

  public PatientAliasIsValid() {
    this.ruleDetections.add(Detection.PatientAliasIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {

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
