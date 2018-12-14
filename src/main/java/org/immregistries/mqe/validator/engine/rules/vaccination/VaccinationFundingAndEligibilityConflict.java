package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.generated.LinkTo;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationFundingAndEligibilityConflict extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationFundingSourceCodeIsValid.class,
        VaccinationFinancialEligibilityCodeIsValid.class};
  }

  public VaccinationFundingAndEligibilityConflict() {
    this.addRuleDocumentation(Detection.VaccinationFundingSourceCodeIsUnexpectedForFinancialEligibility);
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
