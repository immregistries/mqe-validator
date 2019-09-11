package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationAdminDateIsValidForPatientAge extends ValidationRule<MqeVaccination> {


  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientBirthDateIsValid.class, VaccinationAdminDateIsValid.class, VaccinationCvxIsValid.class};
  }

  public VaccinationAdminDateIsValidForPatientAge() {
    ImplementationDetail id =
        this.addRuleDetection(Detection.VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge);
        id.setImplementationDescription("CodeBase specifies the expected age range for this vaccination.  We compare the patients age at administration to this age range.");
        id.setHowToFix("Vaccinations given outside of the expected age range may not be effective. ");
        id.setWhyToFix("Consider reviewing your medical practices.");
    ImplementationDetail id2 =
    this.addRuleDetection(Detection.VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge);
        id2.setImplementationDescription("CodeBase specifies the valid age range for this vaccination.  We compare the patients age at administration to this age range.");
        id2.setHowToFix("Vaccinations given outside of the valid age range are not effective, and do not support desired public health outcomes.  It's a waste of money");
        id2.setWhyToFix("Make sure your administration date is accurate.  If it is, evaluate why you are administering doses outside of the valid age range.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    LOGGER.info("VaccinationAdminDateIsValidForPatientAge - evaluating");
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String cvxCode = target.getAdminCvxCode();
    Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
    LOGGER.info("VaccinationAdminDateIsValidForPatientAge - CVX: " + cvxCode);
    LOGGER.info("VaccinationAdminDateIsValidForPatientAge - Admin Date "
        + target.getAdminDateString());
    LOGGER.info("VaccinationAdminDateIsValidForPatientAge - Birth Date "
        + m.getPatient().getBirthDateString());
    if (vaccineCode != null && !this.common.isEmpty(target.getAdminDateString())) {

      Date adminDate = datr.parseDate(target.getAdminDateString());
      Date birthDate = datr.parseDate(m.getPatient().getBirthDateString());

      if (birthDate != null) {
        issues.addAll(codr.handleAgeDate(CodesetType.VACCINATION_CVX_CODE, vaccineCode, adminDate, birthDate, VxuField.VACCINATION_ADMIN_DATE, target));
      }

      passed = (issues.size() == 0);
    }

    return buildResults(issues, passed);
  }
}
