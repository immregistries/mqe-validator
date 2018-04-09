package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdminCodeIsUsable extends ValidationRule<DqaVaccination> {


  @Override
  protected final Class[] getDependencies() {
    return new Class[]{VaccinationAdminCodeIsPresent.class};
  }

  public VaccinationAdminCodeIsUsable() {
    ruleDetections.add(Detection.VaccinationAdminCodeIsNotUsable);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();

    boolean passed = true;

    String cvxCode = target.getAdminCvxCode();
    String ndcCode = target.getAdminNdc();

    if (StringUtils.isBlank(cvxCode) && StringUtils.isBlank(ndcCode)) {
      return buildResults(issues, false);
    }

    Code ndcDerivedAdminCode = null;
    //Get NDC code data, if it's not blank.
    if (StringUtils.isNotBlank(ndcCode)) {
      ndcDerivedAdminCode = this.repo
          .getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_NDC_CODE, ndcCode,
              CodesetType.VACCINATION_CVX_CODE);
    }

    Code cvxDerivedadminCode = null;
    //If you didn't find anything for NDC code data, look up CVX code data.
    if (StringUtils.isNotBlank(cvxCode)) {
      cvxDerivedadminCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
    }

    //If you found something, evaluate it.
    if (cvxDerivedadminCode != null || ndcDerivedAdminCode != null) {
      passed = true;
    } else {
      issues.add(Detection.VaccinationAdminCodeIsNotUsable.build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }
}
