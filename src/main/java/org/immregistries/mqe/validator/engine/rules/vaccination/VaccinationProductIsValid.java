package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationProductIsValid extends ValidationRule<MqeVaccination> {

  // dependency: VaccinationIsAdministered
  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class,VaccinationAdminCodeIsValid.class};
  }

  public VaccinationProductIsValid() {
    ruleDetections.add(Detection.VaccinationProductIsMissing);
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_PRODUCT));
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    String product = target.getProduct();

    if (product != null) {
      Code productCode = this.repo.getCodeFromValue(product, CodesetType.VACCINE_PRODUCT);
      String adminDate = target.getAdminDateString();
      issues.addAll(codr.handleCode(productCode, VxuField.VACCINATION_PRODUCT, product, target));
      codr.handleUseDate(productCode, adminDate, VxuField.VACCINATION_PRODUCT, target);
    } else {
      issues.add(Detection.VaccinationProductIsMissing.build(target));
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }
}
