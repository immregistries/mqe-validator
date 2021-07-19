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
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCvxCodeAndCptCodeAreInconsistent);
      id.setImplementationDescription(
          "The Vaccination CPT code given is expecting a different vaccine group than the vaccine group from the CVX given.");
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

    passed = verifyPassed(issues);

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
