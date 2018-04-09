package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationIsAdministeredOrHistorical extends ValidationRule<DqaVaccination> {

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    passed =
        (target.getCompletion() != null
            && (target.getCompletion().isEmpty() || target
                .isCompletionCompletedOrPartiallyAdministered()) && (target.getAdminCvxCode() != null && !target
            .getAdminCvxCode().equals("998")));

    return buildResults(issues, passed);
  }
}
