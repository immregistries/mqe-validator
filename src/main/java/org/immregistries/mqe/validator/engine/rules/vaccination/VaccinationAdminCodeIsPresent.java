package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationAdminCodeIsPresent extends ValidationRule<MqeVaccination> {

  public VaccinationAdminCodeIsPresent() {
    this.addRuleDocumentation(Detection.VaccinationAdminCodeIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();

    String adminCvx = target.getAdminCvxCode();
    String adminNdc = target.getAdminNdc();
    String adminCpt = target.getAdminCptCode();


    if (StringUtils.isBlank(adminCpt)
        && StringUtils.isBlank(adminCvx)
        && StringUtils.isBlank(adminNdc)) {
      issues.add(Detection.VaccinationAdminCodeIsMissing.build(target));
    }
    LOGGER.info("issues: " + issues);

    boolean passed = issues.isEmpty();

    return buildResults(issues, passed);
  }
}
