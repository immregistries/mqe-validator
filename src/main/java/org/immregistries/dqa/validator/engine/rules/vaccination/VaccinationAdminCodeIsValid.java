package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.codebase.client.reference.CvxSpecialValues;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdminCodeIsValid extends ValidationRule<DqaVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[]{VaccinationAdminCodeIsPresent.class};
  }

  public VaccinationAdminCodeIsValid() {
    ruleDetections.addAll(Arrays
        .asList(
            Detection.VaccinationAdminCodeIsNotSpecific,
            Detection.VaccinationAdminCodeIsValuedAsNotAdministered,
            Detection.VaccinationAdminCodeIsValuedAsUnknown,
            Detection.VaccinationAdminCodeIsNotVaccine));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();

    boolean passed = true;

    String cvxCode = target.getAdminCvxCode();
    String ndcCode = target.getAdminNdc();
    String cptCode = target.getAdminCptCode();

    if (StringUtils.isBlank(cvxCode)
        && StringUtils.isBlank(ndcCode)
        && StringUtils.isBlank(cptCode)
        ) {
      return buildResults(issues, false);
    }

    boolean useNdc = true;
    Code adminCode = null;

    //Get NDC code data, if it's not blank.
    if (StringUtils.isNotBlank(ndcCode)) {
      adminCode = this.repo
          .getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_NDC_CODE, ndcCode,
              CodesetType.VACCINATION_CVX_CODE);
    }

    //If you didn't find anything for NDC code data, look up CVX code data.
    if (adminCode == null) {
      if (StringUtils.isNotBlank(cvxCode)) {
        adminCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
        useNdc = false;
      }
    }

    //If you didn't find anything for NDC code data, look up CVX code data.
    if (adminCode == null) {
      if (StringUtils.isNotBlank(cptCode)) {
        adminCode = this.repo
            .getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_CPT_CODE, cptCode,
                CodesetType.VACCINATION_CVX_CODE);
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
          case FOREIGN_VACCINE:
            issues.add(Detection.VaccinationAdminCodeIsForiegn.build(adminValue, target));
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
