package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationFinancialEligibilityCodeIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationFinancialEligibilityCodeIsValid() {
    this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsDeprecated);
    this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsInvalid);
    this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsMissing);
    this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsPresent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String financialEligibilityCode = target.getFinancialEligibilityCode();
    issues.addAll(codr.handleCodeOrMissing(financialEligibilityCode,
        VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, target));

    passed = verifyPassed(issues);
    return buildResults(issues, passed);

  }
}
