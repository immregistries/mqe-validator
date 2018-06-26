package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

/**
 * Created by Allison on 5/9/2017.
 */
public class PatientGuardianAddressIsValid extends ValidationRule<MqePatient> {

  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    // TODO: finish this and test it--is guardian the responsible party? if so, this should already
    // be addressed by the next-of-kin address validation

    passed = (issues.size() == 0);
    return buildResults(issues, passed);
  }
}
