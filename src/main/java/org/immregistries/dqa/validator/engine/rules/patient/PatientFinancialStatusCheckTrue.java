package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientFinancialStatusCheckTrue extends ValidationRule<DqaPatient> {

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    // if (!ksm.getKeyedValueBoolean(KeyedSetting.VALIDATE_PATIENT_FINANCIAL_STATUS_IGNORE, true))

    // TODO: figure out how to make this aware of the system settings.
    // for now, it will always be true;

    return buildResults(issues, passed);
  }

}
