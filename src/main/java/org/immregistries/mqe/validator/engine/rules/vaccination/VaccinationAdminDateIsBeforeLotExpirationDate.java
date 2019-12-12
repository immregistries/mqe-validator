package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdminDateIsBeforeLotExpirationDate extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdminDateIsValid.class};
  }

  public VaccinationAdminDateIsBeforeLotExpirationDate() {
    this.addRuleDetection(Detection.VaccinationAdminDateIsAfterLotExpirationDate);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsAfterLotExpirationDate);
      id.setImplementationDescription("Vaccine administered date is after Lot expiration date. ");
      id.setHowToFix("Please review the record and determine if the lot expiration date and vaccination date are correct. If they are correct then no action should be taken, otherwise the record should be corrected and resubmitted. ");
      id.setWhyToFix("It is not correct practice, but it is clinically possible to administer a vaccination after it expires. If this happens it is important to report the event accurately. However, this issue may indicate a problem with how the lot expiration date or administered date were recorded. ");
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
