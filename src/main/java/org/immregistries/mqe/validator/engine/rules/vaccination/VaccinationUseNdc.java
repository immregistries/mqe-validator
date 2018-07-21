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

public class VaccinationUseNdc extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationIsAdministered.class};
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

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
