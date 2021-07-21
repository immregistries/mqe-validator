package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationAdminCodeIsUsable extends ValidationRule<MqeVaccination> {


  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdminCodeIsPresent.class};
  }

  public VaccinationAdminCodeIsUsable() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminCodeIsNotUsable);
      id.setImplementationDescription(
          "NDC, CVX, or CPT must be given in order to have a Vaccination Administered Code.");
    } 
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

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
      ndcDerivedAdminCode = this.repo.getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_NDC_CODE,
          ndcCode, CodesetType.VACCINATION_CVX_CODE);
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
