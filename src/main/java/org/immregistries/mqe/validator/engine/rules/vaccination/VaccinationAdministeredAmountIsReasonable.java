package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdministeredAmountIsReasonable extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredAmtIsValid.class, VaccinationIsAdministered.class};
  }

  public VaccinationAdministeredAmountIsReasonable() {
    this.addRuleDocumentation(Detection.VaccinationAdministeredAmountIsInvalid);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;
    String amount = target.getAmount();
    Double d = Double.parseDouble(amount);
    if (d > 999 || d < 0) {
      issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(amount, target));
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
