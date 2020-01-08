package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdminCodeIsPresent extends ValidationRule<MqeVaccination> {

  public VaccinationAdminCodeIsPresent() {
    this.addRuleDetection(Detection.VaccinationAdminCodeIsMissing);
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminCodeIsMissing);
      id.setHowToFix("The vaccine admin code is missing. Please review how vaccines are coded in your system and select a CVX or NDC code that represents the vaccination given.");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();

    String adminCvx = target.getAdminCvxCode();
    String adminNdc = target.getAdminNdc();
    String adminCpt = target.getAdminCptCode();


    if (StringUtils.isBlank(adminCpt) && StringUtils.isBlank(adminCvx)
        && StringUtils.isBlank(adminNdc)) {
      issues.add(Detection.VaccinationAdminCodeIsMissing.build(target));
    }
    LOGGER.info("issues: " + issues);

    boolean passed = issues.isEmpty();

    return buildResults(issues, passed);
  }
}
