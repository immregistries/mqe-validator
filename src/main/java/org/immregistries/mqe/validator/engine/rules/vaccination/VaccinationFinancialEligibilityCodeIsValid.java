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
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsDeprecated);
      id.setHowToFix("The vaccination funding eligibility code is old. Please update your funding eligibility codes you use or contact your vendor to have the correct code submitted with vaccination records. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsInvalid);
      id.setHowToFix("The vaccination funding eligibility code is invalid and should not to be used. Please update funding eligibility codes you use or contact your vendor to have the correct code submitted with vaccination records. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsMissing);
      id.setHowToFix("The vaccination funding eligibility code is not reported. Please ensure you are indicating the funding source or contact your vendor to request that the funding source be submitted with all administered vaccinations. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFinancialEligibilityCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination funding eligibility code is not recognized or understood. Please update your funding eligibility codes you use or contact your vendor to have the correct code submitted with vaccination records. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String financialEligibilityCode = target.getFinancialEligibilityCode();

    if (target.isAdministered()) {
      issues.addAll(codr.handleCodeOrMissing(financialEligibilityCode,
          VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, target));
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
