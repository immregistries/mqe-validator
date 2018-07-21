package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientFinancialStatusCheckTrue extends ValidationRule<MqePatient> {

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    // if (!ksm.getKeyedValueBoolean(KeyedSetting.VALIDATE_PATIENT_FINANCIAL_STATUS_IGNORE, true))

    // TODO: figure out how to make this aware of the system settings.
    // for now, it will always be true;

    return buildResults(issues, passed);
  }

}
