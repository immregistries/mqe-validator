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

public class VaccinationFundingSourceCodeIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class};
  }

  public VaccinationFundingSourceCodeIsValid() {
    this.addRuleDocumentation(codr
        .getDetectionsForField(VxuField.VACCINATION_FUNDING_SOURCE_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String fundingSourceCode = target.getFundingSourceCode();

    if (target.isAdministered()) {
      if (this.common.isEmpty(fundingSourceCode)) {
        issues.add(Detection.VaccinationFundingSourceCodeIsMissing.build(target));
      } else {
        issues.addAll(codr.handleCode(fundingSourceCode,
            VxuField.VACCINATION_FUNDING_SOURCE_CODE, target));
      }
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
