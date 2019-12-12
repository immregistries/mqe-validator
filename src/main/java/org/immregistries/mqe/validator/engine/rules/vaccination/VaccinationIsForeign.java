package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
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

public class VaccinationIsForeign extends ValidationRule<MqeVaccination> {

  public VaccinationIsForeign() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationAdminCodeIsForeign);
      id.setImplementationDescription("Administered Vaccination has a foreign CVX vaccine code.");
      id.setHowToFix("The administered vaccination is indicated as being a foreign vaccine, one that is not normally administered in the US. Please review the vaccination codes you are sending with this vaccination and ensure that the information is being recorded correctly. Normally would not expect a foriegn vaccine to be reported as administered in the US. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationHistoricalCodeIsForeign);
      id.setImplementationDescription("Historical Vaccination has a foreign CVX vaccine code.");
      id.setHowToFix("The vaccination on the patient history is indicated as being a foreign vaccine, one that is not normally administered in the US. Review the vaccination record to ensure this is correct. It is possible for foriegn vaccines to be record on the records of patients in the US. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String cvxCode = target.getAdminCvxCode();
    boolean administered = target.isAdministered();

    Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
    if (vaccineCode != null) {
      CvxConceptType concept = CvxConceptType.getBy(vaccineCode.getConceptType());

      if (CvxConceptType.FOREIGN_VACCINE == concept) {
        if (administered) {
          issues.add(Detection.VaccinationAdminCodeIsForeign.build(target));
        } else {
          issues.add(Detection.VaccinationHistoricalCodeIsForeign.build(target));
        }
      }
      passed = true;
    }

    return buildResults(issues, passed);

  }
}
