package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodeStatusValue;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationUseCvx extends ValidationRule<MqeVaccination> {

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String cvxString = target.getAdminCvxCode();

    Code cvxCode = repo.getCodeFromValue(cvxString, CodesetType.VACCINATION_CVX_CODE);

    boolean useCvx = true;

    if (cvxCode == null) {
      useCvx = false;
    } else {
      CodeStatusValue cvxStatus = CodeStatusValue.getBy(cvxCode.getCodeStatus());

      if (cvxStatus == null || cvxCode == null) {
        useCvx = false;
      }

      if (useCvx) {
        switch (cvxStatus) {
          case INVALID:
          case IGNORED:
            useCvx = true;
            break;
          default:
            break;
        }
      }
      passed = useCvx;
    }

    return buildResults(issues, passed);
  }
}
