package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdministeredRequiredFieldsArePresent
    extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredLotNumberIsPresent.class};
  }

  public VaccinationAdministeredRequiredFieldsArePresent() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFacilityNameIsMissing);
      id.setHowToFix("The vaccination facility is not indicated. Please contact your vendor to ensure that when vaccinations are administered the facility where they were administered at is reported. ");
      id.setWhyToFix("The facility name might be used to indicate where a vaccination was given. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationLotExpirationDateIsMissing);
      id.setHowToFix("The lot expiration date was not indicated. Please verify if the lot expiration date has been entered or check with your vendor to ensure that when entered the lot number is submitted. ");
      id.setWhyToFix("The registry may use this information to verify that the vaccination was given before the expiration date. ");
    }
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
