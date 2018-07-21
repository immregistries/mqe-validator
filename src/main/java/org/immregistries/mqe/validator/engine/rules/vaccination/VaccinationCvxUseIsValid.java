package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.codebase.client.generated.Code;
import org.immregistries.codebase.client.reference.CodesetType;
import org.immregistries.codebase.client.reference.CvxConceptType;
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
      if (CvxConceptType.FOREIGN_VACCINE == concept || CvxConceptType.UNSPECIFIED == concept
          && !target.isAdministered()) {
        logger
            .info("Not evaluating date because the concept type indicates an UNSPECIFIED or FOREIGN_VACCINE, and it's not administered");
      } else {
        codr.handleUseDate(vaccineCode, target.getAdminDateString(),
            VxuField.VACCINATION_ADMIN_DATE, target);
      }

      passed = (issues.size() == 0);
    }

    return buildResults(issues, passed);
  }
}
