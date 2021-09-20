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
      id.setImplementationDescription("Vaccine administered date is after Lot expiration date. ");
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
