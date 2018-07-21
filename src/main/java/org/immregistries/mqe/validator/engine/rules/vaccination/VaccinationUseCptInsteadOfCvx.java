package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodeStatusValue;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationUseCptInsteadOfCvx extends ValidationRule<MqeVaccination> {

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String adminCvx = target.getAdminCvxCode();
    String adminCpt = target.getAdminCptCode();

    Code cvxCode = repo.getCodeFromValue(adminCvx, CodesetType.VACCINATION_CVX_CODE);
    Code cptCode = repo.getCodeFromValue(adminCpt, CodesetType.VACCINATION_CPT_CODE);

    boolean cvxIsUsable = false;
    boolean cptIsUsable = false;

    if (cvxCode != null) {
      CodeStatusValue cvxStatus = CodeStatusValue.getBy(cvxCode.getCodeStatus());
      cvxIsUsable = !(CodeStatusValue.INVALID == cvxStatus || CodeStatusValue.IGNORED == cvxStatus);
    }

    if (cptCode != null) {
      CodeStatusValue cptStatus = CodeStatusValue.getBy(cptCode.getCodeStatus());
      cptIsUsable = !(CodeStatusValue.INVALID == cptStatus || CodeStatusValue.IGNORED == cptStatus);
    }

    boolean useCptInsteadOfCvx = !cvxIsUsable && cptIsUsable;

    passed = useCptInsteadOfCvx;

    return buildResults(issues, passed);
  }
}
