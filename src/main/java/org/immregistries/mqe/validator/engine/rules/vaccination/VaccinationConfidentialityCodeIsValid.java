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

public class VaccinationConfidentialityCodeIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredAmtIsValid.class};
  }

  public VaccinationConfidentialityCodeIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsDeprecated);
      id.setHowToFix("The confidentiality code for this vaccination is being reported with an old concept code. Please contact your vendor and request that they use the correct concept code. ");
      id.setWhyToFix("The confidentiality code may be used to protect this specific vaccination from being shared in specific situations. Please contact the receiving system to understand in which ways confidentiality for vaccinations is supported. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsInvalid);
      id.setHowToFix("The confidentiality code for this vaccination is being reported with an old concept code. Please contact your vendor and request that they use the correct concept code. ");
      id.setWhyToFix("The confidentiality code may be used to protect this specific vaccination from being shared in specific situations. Please contact the receiving system to understand in which ways confidentiality for vaccinations is supported. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsMissing);
      id.setHowToFix("The confidentiality for this vaccination is not indicated. If you have indicated the confidentiality for this vaccination then please contact your vendor to request that they report the confidentiality code with this vaccination. ");
      id.setWhyToFix("The confidentiality code may be used to protect this specific vaccination from being shared in specific situations. Please contact the receiving system to understand in which ways confidentiality for vaccinations is supported. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The confidentiality code for this vaccination is being reported with an unrecognized concept code. Please contact your vendor and request that they use the correct concept code. ");
      id.setWhyToFix("The confidentiality code may be used to protect this specific vaccination from being shared in specific situations. Please contact the receiving system to understand in which ways confidentiality for vaccinations is supported. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsValuedAsRestricted);
      id.setImplementationDescription("Vaccination Confidentiality Code has value of 'R' or 'V'.");
      id.setHowToFix("The confidentiality for this vaccination is indicated as restricted. If the confidentiality for this vaccination is not restricted please contact your vendor to ensure that confidentiality is being reported properly. ");
      id.setWhyToFix("The confidentiality code may be used to protect this specific vaccination from being shared in specific situations. Please contact the receiving system to understand in which ways confidentiality for vaccinations is supported. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String confCode = target.getConfidentialityCode();

    issues.addAll(
        codr.handleCodeOrMissing(confCode, VxuField.VACCINATION_CONFIDENTIALITY_CODE, target));
    passed = (issues.size() == 0);

    if ("R".equals(confCode) || "V".equals(confCode)) {
      issues.add(Detection.VaccinationConfidentialityCodeIsValuedAsRestricted.build(target));
    }

    return buildResults(issues, passed);

  }
}
