package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.generated.UseDate;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationMfrIsValid extends ValidationRule<MqeVaccination> {

  // dependency: VaccinationIsAdministered

  public VaccinationMfrIsValid() {
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.VACCINATION_MANUFACTURER_CODE));
    this.addImplementationMessage(Detection.VaccinationManufacturerCodeIsInvalidForDateAdministered, "Vaccination Manufacturer code was used outside of the valid date range defined for this code. ");
    this.addImplementationMessage(Detection.VaccinationManufacturerCodeIsUnexpectedForDateAdministered, "Vaccination Manufacturer code was used outside of the expected date range defined for this code. ");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target.isAdministered()) {
      issues.addAll(codr.handleCode(target.getManufacturer(),
          VxuField.VACCINATION_MANUFACTURER_CODE, target));
      passed = (issues.size() == 0);
    }

    Code vaccineMvx = repo.getMfrForCode(target.getManufacturerCode());

    if (vaccineMvx != null) {
      UseDate ud = vaccineMvx.getUseDate();

      if (target.isAdministered() && ud != null && target.getAdminDate() != null) {
        String notBeforeString = ud.getNotBefore();
        String notAfterString = ud.getNotAfter();

        Date notBeforeDate = datr.parseDate(notBeforeString);
        Date notAfterDate = datr.parseDate(notAfterString);

        String notExpectedBeforeString = ud.getNotExpectedBefore();
        String notExpectedAfterString = ud.getNotExpectedAfter();

        Date notExpectedBeforeDate = datr.parseDate(notExpectedBeforeString);
        Date notExpectedAfterDate = datr.parseDate(notExpectedAfterString);

        if (datr.isAfterDate(target.getAdminDate(), notAfterDate)
            || datr.isBeforeDate(target.getAdminDate(), notBeforeDate)) {

          issues.add(Detection.VaccinationManufacturerCodeIsInvalidForDateAdministered.build(
              target.getManufacturer(), target));
          passed = false;

        } else if (datr.isAfterDate(target.getAdminDate(), notExpectedAfterDate)
            || datr.isBeforeDate(target.getAdminDate(), notExpectedBeforeDate)) {
          issues.add(Detection.VaccinationManufacturerCodeIsUnexpectedForDateAdministered.build(
              target.getManufacturer(), target));
        }
      }
    }
    passed = issues.isEmpty();

    return buildResults(issues, passed);
  }
}
