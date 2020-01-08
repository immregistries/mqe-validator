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

public class VaccinationNdcIsValid extends ValidationRule<MqeVaccination> {

  public VaccinationNdcIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationNDCCodeIsMissing);
      id.setHowToFix("The vaccination was submitted without an NDC code. Please review how vaccines are coded in your system and or ask your vendor to ensure that an NDC is included for all administered vaccinations. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. NDC provides the best information about the type of vaccination administered. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationNDCCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination was submitted with an NDC code that was not recognized. Please review how vaccines are coded in your system and or ask your vendor to ensure that an NDC is included for all administered vaccinations. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. NDC provides the best information about the type of vaccination administered. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<>(this.codr.handleCodeOrMissing(target.getAdminNdc(), VxuField.VACCINATION_NDC_CODE, target));

    LOGGER.info("issues: " + issues);
    boolean passed = issues.isEmpty();
    return buildResults(issues, passed);
  }
}
