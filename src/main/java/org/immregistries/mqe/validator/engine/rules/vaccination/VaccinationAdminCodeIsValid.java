package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.codebase.client.reference.CvxConceptType;
import org.immregistries.codebase.client.reference.CvxSpecialValues;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdminCodeIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class, VaccinationAdminCodeIsPresent.class};
  }

  public VaccinationAdminCodeIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminCodeIsNotSpecific);
      id.setImplementationDescription(
          "Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has an unspecified value type.");
      id.setHowToFix("Please update the vaccination recorded as administered with a vaccination that is specific to what was given. If a specific one is selected, then verify the CVX code that is being reported to ensure it is the correct match for what is in the recorded vaccination history. ");
      id.setWhyToFix("Non-specific vaccination codes are reserved for reporting historical vaccines where information about which specific vaccination was given may not be available. It is better to record something of the vaccination history rather than have that information missing. But these non-specific codes should not be used in reporting administered vaccines where the reporter should know and thus be able to record the specific vaccine given. Without this specific vaccine information, the patient record will be incomplete, lot decrementing may not work properly, and recommendations may not take into account vaccine specific recommendations. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminCodeIsNotVaccine);
      id.setImplementationDescription(
          "Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has a non-vaccine value type.");
      id.setHowToFix("Please ask your system or vendor to not submit events that are not vaccines. ");
      id.setWhyToFix("The current list of CVX codes contains concepts that, strictly speaking, are not vaccines. Some of these are accepted by immunization systems while others may not be. Some immunization systems may even be barred from receiving such information. Care should be taken to only submit this type of information to systems that are prepared and willing to recieve it. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The submitted code could not be recognized as a usable code. Please review how vaccines are coded in your system and select a CVX or NDC code that better represents the vaccination given.");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminCodeIsValuedAsNotAdministered);
      id.setImplementationDescription(
          "Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has a value of 998.");
      id.setHowToFix("Please contact your vendor and request that they do not send an administered vaccination that indicates that no vaccine was administered. ");
      id.setWhyToFix("There is a CVX code value that indicates that a vaccine was not administered. While this code exists it should not be sent in messages to indicate a vaccination was given as this would cause confusion to the receiving system. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdminCodeIsValuedAsUnknown);
      id.setImplementationDescription(
          "Vaccination Administered Code (CVX derived from given NDC, CVX, CPT. Derivation stops on first success.) has a value of 999.");
      id.setHowToFix("Please contact your vendor and request that they do not send an administered vaccination without knowing what type of vaccination was administered. ");
      id.setWhyToFix("The type of vaccination administered must be indicated to record a vaccination. The sender should not report that an unknown vaccination was given. This cannot be added to an official vaccination history. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();

    boolean passed = true;

    String cvxCode = target.getAdminCvxCode();
    String ndcCode = target.getAdminNdc();
    String cptCode = target.getAdminCptCode();

    if (StringUtils.isBlank(cvxCode) && StringUtils.isBlank(ndcCode)
        && StringUtils.isBlank(cptCode)) {
      return buildResults(issues, false);
    }

    boolean useNdc = true;
    Code adminCode = null;

    //Get NDC code data, if it's not blank.
    if (StringUtils.isNotBlank(ndcCode)) {
      adminCode = this.repo.getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_NDC_CODE, ndcCode,
          CodesetType.VACCINATION_CVX_CODE);
    }

    //If you didn't find anything for NDC code data, look up CVX code data.
    if (adminCode == null) {
      if (StringUtils.isNotBlank(cvxCode)) {
        adminCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
        useNdc = false;
      }
    }

    //If you didn't find anything for CVX code data, look up CPT code data.
    if (adminCode == null) {
      if (StringUtils.isNotBlank(cptCode)) {
        adminCode = this.repo.getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_CPT_CODE,
            cptCode, CodesetType.VACCINATION_CVX_CODE);
        useNdc = false;
      }
    }

    //If you found something, evaluate it.
    if (adminCode != null) {
      String adminValue = adminCode.getValue();

      if (target.isAdministered()) {
        CvxConceptType concept = CvxConceptType.getBy(adminCode.getConceptType());
        switch (concept) {
          case UNSPECIFIED:
            issues.add(Detection.VaccinationAdminCodeIsNotSpecific.build(adminValue, target));
            break;
          case NON_VACCINE:
            issues.add(Detection.VaccinationAdminCodeIsNotVaccine.build(adminValue, target));
            break;
        }
      }

      CvxSpecialValues cvxSpecial = CvxSpecialValues.getBy(adminValue);
      switch (cvxSpecial) {
        case NO_VACCINE_ADMINISTERED:
          issues.add(
              Detection.VaccinationAdminCodeIsValuedAsNotAdministered.build(adminValue, target));
          break;
        case UNKNOWN:
          issues.add(Detection.VaccinationAdminCodeIsValuedAsUnknown.build(adminValue, target));
          break;
      }
      passed = (issues.size() == 0);
    } else {
      String bestCode = useNdc ? ndcCode : cvxCode;
      issues.add(Detection.VaccinationAdminCodeIsUnrecognized.build(bestCode, target));
      passed = false;
    }

    return buildResults(issues, passed);
  }
}
