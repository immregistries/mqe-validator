package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.VxuField;

public class VaccinationCvxIsValid extends ValidationRule<MqeVaccination> {

  public VaccinationCvxIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCvxCodeIsDeprecated);
      id.setHowToFix("The vaccination was submitted with a CVX code that should no longer be used. Please review how the vaccines are coded in your system and select a CVX code that better represents the vaccination given. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCvxCodeIsInvalid);
      id.setHowToFix("The vaccination was submitted with a CVX code that should no longer be used. Please review how the vaccines are coded in your system and select a CVX code that better represents the vaccination given. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCvxCodeIsMissing);
      id.setHowToFix("The vaccination was submitted but with no CVX code. Please review how the vaccines are coded in your system or contact your vendor to ensure that CVX codes are being submitted. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCvxCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination was submitted with a CVX code that is not recognized. Please review how the vaccines are coded in your system and select a CVX code that better represents the vaccination given. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>(this.codr
        .handleCodeOrMissing(target.getAdminCvxCode(), VxuField.VACCINATION_CVX_CODE, target));
    LOGGER.info("issues: " + issues);

    boolean passed = issues.isEmpty();

    return buildResults(issues, passed);
  }
}
