package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.DetectionType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationNdcIsValid extends ValidationRule<MqeVaccination> {

  public VaccinationNdcIsValid() {
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_NDC_CODE));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<>(this.codr.handleCodeOrMissing(target.getAdminNdc(), VxuField.VACCINATION_NDC_CODE, target));

    LOGGER.info("issues: " + issues);
    boolean passed = issues.isEmpty();
    return buildResults(issues, passed);
  }
}
