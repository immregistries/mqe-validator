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
public class VaccinationAdministeredRequiredFieldsArePresent
    extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredLotNumberIsPresent.class};
  }

  public VaccinationAdministeredRequiredFieldsArePresent() {
    this.addRuleDetection(Detection.VaccinationFacilityNameIsMissing);
    this.addRuleDetection(Detection.VaccinationLotExpirationDateIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    // If vaccination is not actually administered then this is a waiver. Need
    // to check that now, here to see if we need to enforce a value in RXA-9 to
    // indicate that the vaccination is historical or administered.
    // By default we assume that the vaccination was completed.

    if (this.common.isEmpty(target.getFacilityName())) {
      issues.add(Detection.VaccinationFacilityNameIsMissing.build(target));
    }

    if (target.getExpirationDate() == null) {
      issues.add(Detection.VaccinationLotExpirationDateIsMissing.build(target));
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);

  }
}
