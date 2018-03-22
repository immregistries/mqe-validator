package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

public class VaccinationProductIsValid extends ValidationRule<DqaVaccination> {

  // dependency: VaccinationIsAdministered
  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class,};
  }

  public VaccinationProductIsValid() {
    ruleDetections.add(Detection.VaccinationProductIsMissing);
    ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_PRODUCT));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

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
