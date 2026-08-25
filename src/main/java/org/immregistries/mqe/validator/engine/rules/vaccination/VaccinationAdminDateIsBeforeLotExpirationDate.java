package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationAdminDateIsBeforeLotExpirationDate extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdminDateIsValid.class};
  }

  public VaccinationAdminDateIsBeforeLotExpirationDate() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsAfterLotExpirationDate);
      id.setImplementationDescription(
          "Compares the vaccination's admin date to the lot's expiration date on the same vaccination record; "
              + "fails when admin date is on or after the expiration date.");
      id.setWhyToFix(
          "A dose given from expired vaccine stock may not have provided the intended immunity, "
              + "which can affect clinical decisions (e.g. whether the dose counts toward the series) "
              + "and may indicate a data entry error (wrong lot or wrong date) rather than an actual expired-dose administration.");
      id.setHowToFix(
          "Confirm the lot number and admin date on the source record. If either was mistyped, correct and resubmit. "
              + "If the dose truly was administered from expired stock, no correction is needed here, "
              + "but the provider organization should be made aware of the expired-stock usage.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    Date adminDate = target.getAdminDate();
    Date lotExpirationDate = target.getExpirationDate();

    if (lotExpirationDate != null && !adminDate.before(lotExpirationDate)) {
      issues.add(Detection.VaccinationAdminDateIsAfterLotExpirationDate.build(adminDate.toString(),
          target));
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
