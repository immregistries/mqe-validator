package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.generated.UseDate;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationMfrIsValid extends ValidationRule<MqeVaccination> {

  public VaccinationMfrIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationManufacturerCodeIsDeprecated);
      id.setHowToFix("The vaccine manufacturer is indicated with a bad code that should not longer be used. Please review your manufacturer codes or contact your vendor to ensure that the latest and best manufacturer codes are being reported. ");
      id.setWhyToFix("The vaccine manufacturer code allows for identification of the specific vaccine administered and gives context for the lot number indicated. This helps to create a full and complete record, helps with vaccination matching and ensure that the good data is being stored. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationManufacturerCodeIsInvalid);
      id.setHowToFix("The vaccine manufacturer is indicated with a bad code that should not longer be used. Please review your manufacturer codes or contact your vendor to ensure that the latest and best manufacturer codes are being reported. ");
      id.setWhyToFix("The vaccine manufacturer code allows for identification of the specific vaccine administered and gives context for the lot number indicated. This helps to create a full and complete record, helps with vaccination matching and ensure that the good data is being stored. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationManufacturerCodeIsMissing);
      id.setHowToFix("The vaccine manufacturer is not indicated. Please indicate the manufacturer for this vaccination. ");
      id.setWhyToFix("The vaccine manufacturer code allows for identification of the specific vaccine administered and gives context for the lot number indicated. This helps to create a full and complete record, helps with vaccination matching and ensure that the good data is being stored. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationManufacturerCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccine manufacturer is indicated with a bad code that should not longer be used. Please review your manufacturer codes or contact your vendor to ensure that the latest and best manufacturer codes are being reported. ");
      id.setWhyToFix("The vaccine manufacturer code allows for identification of the specific vaccine administered and gives context for the lot number indicated. This helps to create a full and complete record, helps with vaccination matching and ensure that the good data is being stored. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationManufacturerCodeIsInvalidForDateAdministered);
      id.setImplementationDescription(
          "Vaccination Manufacturer code was used outside of the valid date range defined for this code. ");
      id.setHowToFix("The vaccine manufacturer was not expected to be creating vaccines during the time that this vaccination was administered. Please review the manufacture indicated for this vaccination. ");
      id.setWhyToFix("The vaccine manufacturer code allows for identification of the specific vaccine administered and gives context for the lot number indicated. This helps to create a full and complete record, helps with vaccination matching and ensure that the good data is being stored. ");
    }
    {
      ImplementationDetail id = this
          .addRuleDetection(Detection.VaccinationManufacturerCodeIsUnexpectedForDateAdministered);
      id.setImplementationDescription(
          "Vaccination Manufacturer code was used outside of the expected date range defined for this code. ");
      id.setHowToFix("The vaccine manufacturer was not expected to be creating vaccines during the time that this vaccination was administered. Please review the manufacture indicated for this vaccination. ");
      id.setWhyToFix("The vaccine manufacturer code allows for identification of the specific vaccine administered and gives context for the lot number indicated. This helps to create a full and complete record, helps with vaccination matching and ensure that the good data is being stored. ");
    }
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

          issues.add(Detection.VaccinationManufacturerCodeIsInvalidForDateAdministered
              .build(target.getManufacturer(), target));
          passed = false;

        } else if (datr.isAfterDate(target.getAdminDate(), notExpectedAfterDate)
            || datr.isBeforeDate(target.getAdminDate(), notExpectedBeforeDate)) {
          issues.add(Detection.VaccinationManufacturerCodeIsUnexpectedForDateAdministered
              .build(target.getManufacturer(), target));
        }
      }
    }
    passed = issues.isEmpty();

    return buildResults(issues, passed);
  }
}
