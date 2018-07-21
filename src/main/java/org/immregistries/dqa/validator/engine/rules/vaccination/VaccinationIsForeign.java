package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationIsForeign extends ValidationRule<DqaVaccination> {

  public VaccinationIsForeign() {
    ruleDetections.addAll(Arrays.asList(Detection.VaccinationAdminCodeIsForiegn,
        Detection.VaccinationHistoricalCodeIsForeign));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String cvxCode = target.getAdminCvxCode();
    boolean administered = target.isAdministered();

    Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
    if (vaccineCode != null) {
      CvxConceptType concept = CvxConceptType.getBy(vaccineCode.getConceptType());

      if (CvxConceptType.FOREIGN_VACCINE == concept) {
        if (administered) {
          issues.add(Detection.VaccinationAdminCodeIsForiegn.build(target));
        } else {
          issues.add(Detection.VaccinationHistoricalCodeIsForeign.build(target));
        }
      }
      passed = true;
    }

    return buildResults(issues, passed);

  }
}