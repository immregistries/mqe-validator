package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

public class VaccinationAdminCodeIsPresent extends ValidationRule<DqaVaccination> {

  public VaccinationAdminCodeIsPresent() {
    ruleDetections.add(Detection.VaccinationAdminCodeIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
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
