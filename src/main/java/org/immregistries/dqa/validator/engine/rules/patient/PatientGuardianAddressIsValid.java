package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

/**
 * Created by Allison on 5/9/2017.
 */
public class PatientGuardianAddressIsValid extends ValidationRule<DqaPatient> {

  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    // TODO: finish this and test it--is guardian the responsible party? if so, this should already
    // be addressed by the next-of-kin address validation

    passed = (issues.size() == 0);
    return buildResults(issues, passed);
  }
}
