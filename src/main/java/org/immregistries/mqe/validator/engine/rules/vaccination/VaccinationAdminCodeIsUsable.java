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
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

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
      id.setHowToFix("The submitted code could not be recognized as a usable code. Please review how vaccines are coded in your system and select a CVX or NDC code that better represents the vaccination given.");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
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
