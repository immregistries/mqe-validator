package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodeStatusValue;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationUseNdc extends ValidationRule<DqaVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class};
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String ndcString = target.getAdminNdc();

    Code ndcCode = repo.getCodeFromValue(ndcString, CodesetType.VACCINATION_NDC_CODE);

    boolean useNdc = true;

    if (ndcCode != null) {
      CodeStatusValue ndcStatus = CodeStatusValue.getBy(ndcCode.getCodeStatus());

      if (ndcStatus == null) {
        useNdc = false;
      } else {

        switch (ndcStatus) {
          case VALID:
            useNdc = true;
            break;
          default:
            useNdc = false;
            break;
        }
      }
      passed = useNdc;
    }

    return buildResults(issues, passed);
  }
}
