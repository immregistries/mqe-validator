package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdministeredAmountIsReasonable extends ValidationRule<DqaVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredAmtIsValid.class, VaccinationIsAdministered.class};
  }

  public VaccinationAdministeredAmountIsReasonable() {
    ruleDetections.add(Detection.VaccinationAdministeredAmountIsInvalid);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;
    String amount = target.getAmount();
    Double d = Double.parseDouble(amount);
    if (d > 999) {
      issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(amount, target));
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
