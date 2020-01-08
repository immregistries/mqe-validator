package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.codebase.client.reference.CvxConceptType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationCvxUseIsValid extends ValidationRule<MqeVaccination> {

  private static final Logger logger = LoggerFactory.getLogger(VaccinationCvxUseIsValid.class);

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationCvxIsValid.class};
  }

  public VaccinationCvxUseIsValid() {
    {
      ImplementationDetail id = this
          .addRuleDetection(Detection.VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange);
      id.setImplementationDescription(
          "Vaccination Administered Date is outside of expected vaccine date range.");
      id.setHowToFix("The vaccination was administered before or after when it was available for general use. Please review the vaccine administered and the date it was administered on to ensure that the event has been recorded properly. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange);
      id.setImplementationDescription(
          "Vaccination Administered Date is outside of licensed vaccine date range.");
      id.setHowToFix("The vaccination was administered before or after when it was licensed for use. Please review the vaccine administered and the date it was administered on to ensure that the event has been recorded properly. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String cvxCode = target.getAdminCvxCode();

    Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);

    String CvxConceptTypeString = vaccineCode.getConceptType();
    CvxConceptType concept = CvxConceptType.getBy(CvxConceptTypeString);

    if (vaccineCode != null && target.getAdminDate() != null) {
      if (CvxConceptType.FOREIGN_VACCINE == concept
          || CvxConceptType.UNSPECIFIED == concept && !target.isAdministered()) {
        logger.info(
            "Not evaluating date because the concept type indicates an UNSPECIFIED or FOREIGN_VACCINE, and it's not administered");
      } else {
        issues.addAll(codr.handleUseDate(vaccineCode, target.getAdminDateString(),
            VxuField.VACCINATION_ADMIN_DATE, target));
      }
      passed = (issues.size() == 0);
    }

    return buildResults(issues, passed);
  }
}
