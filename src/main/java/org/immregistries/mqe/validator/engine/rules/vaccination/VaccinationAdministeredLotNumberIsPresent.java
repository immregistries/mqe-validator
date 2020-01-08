package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdministeredLotNumberIsPresent extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationAdministeredLotNumberIsPresent() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationLotNumberIsMissing);
      id.setHowToFix("The vaccination lot number was not indicated for an administered vaccination. If the vaccination was administered then the person giving the vaccination should know the lot number of the vaccine administered. This should be entered as part of the complete record. Please verify that the lot number is being entered. If the lot number is not available because this was administered at a different location or by a different organization then this vaccination should be recorded as a historical dose. ");
      id.setWhyToFix("The Lot Number is used for several critical IIS functions including: Vaccine lot inventory decrementing, vaccination matching, and vaccination product recall. Reporting the vaccine lot number helps to create a complete and accurate vaccination history. If at all possible, please report the lot number. ");
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

    if (this.common.isEmpty(target.getLotNumber())) {
      issues.add(Detection.VaccinationLotNumberIsMissing.build(target));
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);

  }
}
