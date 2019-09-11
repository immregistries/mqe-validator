package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.generated.UseDate;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationProductIsValid extends ValidationRule<MqeVaccination> {

  // dependency: VaccinationIsAdministered
  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class, VaccinationAdminCodeIsValid.class};
  }

  public VaccinationProductIsValid() {
    this.addRuleDetections(codr.getDetectionsForField(VxuField.VACCINATION_PRODUCT));
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationProductIsInvalidForDateAdministered);
      id.setImplementationDescription(
          "Vaccination product was used outside of the valid date range defined for this product. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationProductIsUnexpectedForDateAdministered);
      id.setImplementationDescription(
          "Vaccination product was used outside of the expected date range defined for this product. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    String product = target.getProduct();

    if (product != null) {
      Code productCode = this.repo.getCodeFromValue(product, CodesetType.VACCINE_PRODUCT);
      issues.addAll(codr.handleCode(productCode, VxuField.VACCINATION_PRODUCT, product, target));

      if (productCode != null) {
        UseDate ud = productCode.getUseDate();

        if (ud != null && target.getAdminDate() != null) {
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

            issues.add(
                Detection.VaccinationProductIsInvalidForDateAdministered.build(product, target));
            passed = false;

          } else if (datr.isAfterDate(target.getAdminDate(), notExpectedAfterDate)
              || datr.isBeforeDate(target.getAdminDate(), notExpectedBeforeDate)) {
            issues.add(
                Detection.VaccinationProductIsUnexpectedForDateAdministered.build(product, target));
          }
        }
      }
    } else {
      issues.add(Detection.VaccinationProductIsMissing.build(target));
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }
}
