package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationFinancialEligibilityCodeIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class};
  }

  public VaccinationFinancialEligibilityCodeIsValid() {
    ruleDetections.add(Detection.VaccinationFinancialEligibilityCodeIsMissing);
    ruleDetections.addAll(codr
        .getDetectionsForField(VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String financialEligibilityCode = target.getFinancialEligibilityCode();

    if (target.isAdministered()) {
      if (this.common.isEmpty(financialEligibilityCode)) {
        issues.add(Detection.VaccinationFinancialEligibilityCodeIsMissing.build(target));
      } else {
        issues.addAll(codr.handleCode(financialEligibilityCode,
            VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, target));
      }
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
