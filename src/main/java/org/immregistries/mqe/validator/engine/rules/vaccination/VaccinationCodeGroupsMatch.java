package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationCodeGroupsMatch extends ValidationRule<MqeVaccination> {

  public VaccinationCodeGroupsMatch() {
    this.addRuleDetection(Detection.VaccinationCvxCodeAndCptCodeAreInconsistent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCvxCodeAndCptCodeAreInconsistent);
      id.setImplementationDescription(
          "The Vaccination CPT code given is expecting a different vaccine group than the vaccine group from the CVX given.");
      id.setHowToFix("The vaccination is being reported with both a CVX and a CPT code but they do not agree on which vaccination was given. Please check your vaccine codes and ensure that the cvx and cpt codes are recorded properly for the vaccination being reported. ");
      id.setWhyToFix("Creating a complete vaccination history requires an accurate list of vaccinations administered. Conflicting information means that the wrong information may be recorded and transmitted to other systems. Great care must be taken to ensure that vaccinations are reported using the correct codes. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String vaccineCpt = target.getAdminCptCode();

    String vaccineCvx = target.getAdminCvxCode();

    // Code cptCode = repo.getCodeFromValue(target.getAdminCptCode(),
    // CodesetType.VACCINATION_CPT_CODE);

    Code cptCvxCode = repo.getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_CPT_CODE,
        vaccineCpt, CodesetType.VACCINATION_CVX_CODE);

    if (cptCvxCode != null && cptCvxCode.getValue() != null && vaccineCvx != null) {
      if (!checkGroupMatch(vaccineCvx, cptCvxCode.getValue())) {
        issues.add(Detection.VaccinationCvxCodeAndCptCodeAreInconsistent.build(target));
      }
    }

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

  private boolean checkGroupMatch(String cvxCode, String cptCvxCode) {
    if (cvxCode == null || cptCvxCode == null) {
      return false;
    }

    if (cvxCode.equals(cptCvxCode)) {
      return true;
    }

    List<Code> cvxVaccineGroups = repo.getRelatedCodesForCodeIn(CodesetType.VACCINATION_CVX_CODE,
        cvxCode, CodesetType.VACCINE_GROUP);

    List<Code> cptVaccineGroups = repo.getRelatedCodesForCodeIn(CodesetType.VACCINATION_CPT_CODE,
        cptCvxCode, CodesetType.VACCINE_GROUP);

    if (cptVaccineGroups != null && cvxVaccineGroups != null) {
      for (Code cvxGroup : cvxVaccineGroups) {
        for (Code cptGroup : cptVaccineGroups) {
          if (repo.codeEquals(cvxGroup, cptGroup)) {
            return true;
          }
        }
      }
    }

    return false;
    // CPT doesn't map to CVX, so need to check if it's in the same family
    // Has to have at least one matching.
  }

}
