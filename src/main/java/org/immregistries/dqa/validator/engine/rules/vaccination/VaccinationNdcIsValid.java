package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

public class VaccinationNdcIsValid extends ValidationRule<DqaVaccination> {

  public VaccinationNdcIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_NDC_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>(this.codr.handleCode(target.getAdminNdc(), VxuField.VACCINATION_NDC_CODE, target));

    LOGGER.info("issues: " + issues);
    boolean passed = issues.isEmpty();
    return buildResults(issues, passed);
  }
}
