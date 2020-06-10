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
      id.setHowToFix("Review the vaccination administration date and or lot expiration date to ensure the information is correct. If the patient was given expired vaccine, the dose should be repeated.  ");
      id.setWhyToFix("The administration date is used both to identify a unique vaccination event and to establish the event in the correct place on the vaccination history. The primary function of an immunization history is to provide specific information about when vaccinations were given so that clinical decisions can be made as to what vaccinations are due next and if the patient is properly protected from disease. ");
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
