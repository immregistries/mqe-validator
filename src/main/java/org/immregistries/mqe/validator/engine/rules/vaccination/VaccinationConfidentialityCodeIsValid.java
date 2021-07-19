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
    this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsDeprecated);
    this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsInvalid);
    this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsMissing);
    this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsPresent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationConfidentialityCodeIsValuedAsRestricted);
      id.setImplementationDescription("Vaccination Confidentiality Code has value of 'R' or 'V'.");
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
