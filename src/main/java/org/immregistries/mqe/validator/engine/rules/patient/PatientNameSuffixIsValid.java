package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientNameSuffixIsValid extends ValidationRule<MqePatient> {

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String sfx = target.getNameSuffix();

    // You know what... there aren't even any potential issues for the suffix.

    // Heres the code from the validator. Nothing gets validated. just transformed.

    // if (!isEmpty(patient.getNameSuffix()))
    // {
    // if (patient.getNameSuffix().equalsIgnoreCase("11") ||
    // patient.getNameSuffix().equalsIgnoreCase("2nd"))
    // {
    // patient.setNameSuffix("II");
    // } else if (patient.getNameSuffix().equalsIgnoreCase("111") ||
    // patient.getNameSuffix().equalsIgnoreCase("3rd"))
    // {
    // patient.setNameSuffix("III");
    // } else if (patient.getNameSuffix().equalsIgnoreCase("4th"))
    // {
    // patient.setNameSuffix("IV");
    // }
    // boolean isValid = false;
    // for (String valid : VALID_SUFFIX)
    // {
    // if (patient.getNameSuffix().equalsIgnoreCase(valid))
    // {
    // isValid = true;
    // }
    // }
    // if (!isValid)
    // {
    // // TODO suffix is invalid
    // patient.setNameSuffix("");
    // }
    // }

    return buildResults(issues, passed);
  }

}
