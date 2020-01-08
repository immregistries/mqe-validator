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

public class VaccinationAdminCodeCptIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationUseCptInsteadOfCvx.class};
  }

  public VaccinationAdminCodeCptIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCptCodeIsDeprecated);
      id.setHowToFix("The vaccination was submitted with a CPT code that should no longer be used. Please review how vaccines are coded in your system and select a CPT code that better represents the vaccination given. Because the use of CPT is now deprecated in favor of reporting vaccines with CVX or NDC you should also consider reporting this vaccination information as a CVX or NDC instead of as a CPT.");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCptCodeIsInvalid);
      id.setHowToFix("The vaccination was submitted with a CPT code that should no longer be used. Please review how vaccines are coded in your system and select a CPT code that better represents the vaccination given. Because the use of CPT is now deprecated in favor of reporting vaccines with CVX or NDC you should also consider reporting this vaccination information as a CVX or NDC instead of as a CPT.");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCptCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination was submitted with a CPT code that is not recognized. Please review how vaccines are coded in your system and select a CPT code that better represents the vaccination given. Because the use of CPT is now deprecated in favor of reporting vaccines with CVX or NDC you should also consider reporting this vaccination information as a CVX or NDC instead of as a CPT.");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.VaccinationCptCodeIsMissing);
      id.setHowToFix("The vaccination was submitted without indicating a CPT code. For most interfaces this is not a problem as vaccinations should be reported using CVX or NDC. If you are required to submit using CPT then please review how vaccines are being coded in your system to ensure this vaccination is assigned a CPT code. ");
      id.setWhyToFix("Correctly understanding what type of vaccination was administered is critical for building a complete vaccination history. Clinical Decision Support Systems depend on having access to a complete and accurate vaccination history. Without this the recommendations for a patient will be incorrect. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String cpt = target.getAdminCptCode();

    issues.addAll(codr.handleCode(cpt, VxuField.VACCINATION_CPT_CODE, target));

    passed = (issues.size() == 0);

    return buildResults(issues, passed);
  }

}
