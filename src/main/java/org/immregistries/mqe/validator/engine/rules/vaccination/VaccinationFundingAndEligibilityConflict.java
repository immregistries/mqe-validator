package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.generated.LinkTo;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationFundingAndEligibilityConflict extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationFundingSourceCodeIsValid.class,
        VaccinationFinancialEligibilityCodeIsValid.class};
  }

  public VaccinationFundingAndEligibilityConflict() {
    this.addRuleDetection(
        Detection.VaccinationFundingSourceCodeIsUnexpectedForFinancialEligibility);
    {
      ImplementationDetail id = this.addRuleDetection(
          Detection.VaccinationFundingSourceCodeIsUnexpectedForFinancialEligibility);
      id.setImplementationDescription(
          "The financial funding source given is unexpected for the financial eligibility given.");
      id.setHowToFix("The funding source does not match with the funding eligibility for this patient and this vaccination. Please review the funding source and eligibility and ensure that they are correctly indicated. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    if (StringUtils.isBlank(target.getFinancialEligibilityCode())
        || StringUtils.isBlank(target.getFundingSourceCode())) {
      return buildResults(issues, false);
    }
    Code financialEligibility = repo.getCodeMap()
        .getCodeForCodeset(CodesetType.FINANCIAL_STATUS_CODE, target.getFinancialEligibilityCode());
    Code fundingSource = repo.getCodeMap().getCodeForCodeset(CodesetType.VACCINATION_FUNDING_SOURCE,
        target.getFundingSourceCode());
    if (financialEligibility == null || fundingSource == null) {
      return buildResults(issues, false);
    }

    if (financialEligibility.getReference() != null
        && financialEligibility.getReference().getLinkTo() != null) {
      boolean found = false;
      boolean matches = false;
      for (LinkTo linkTo : financialEligibility.getReference().getLinkTo()) {
        if (linkTo.getCodeset().equals(CodesetType.VACCINATION_FUNDING_SOURCE.getType())) {
          found = true;
          if (fundingSource.getValue().equals(linkTo.getValue())) {
            matches = true;
            break;
          }
        }
      }
      if (found && !matches) {
        issues.add(Detection.VaccinationFundingSourceCodeIsUnexpectedForFinancialEligibility
            .build(target));
      }
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
