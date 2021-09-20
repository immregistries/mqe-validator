package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationAdministeredLotNumberIsPresent extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationAdministeredLotNumberIsPresent() {
    this.addRuleDetection(Detection.VaccinationLotNumberIsMissing);
    this.addRuleDetection(Detection.VaccinationLotNumberIsPresent);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    // If vaccination is not actually administered then this is a waiver. Need
    // to check that now, here to see if we need to enforce a value in RXA-9 to
    // indicate that the vaccination is historical or administered.
    // By default we assume that the vaccination was completed.

    if (this.common.isEmpty(target.getLotNumber())) {
      passed = false;
      issues.add(Detection.VaccinationLotNumberIsMissing.build(target));
    } else {
      issues.add(Detection.VaccinationLotNumberIsPresent.build(target));
    }

    passed = verifyNoIssuesExceptPresent(issues);

    return buildResults(issues, passed);

  }
}
