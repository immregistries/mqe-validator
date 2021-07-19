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

public class VaccinationAdministeredUnitIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredAmtIsValid.class,
        VaccinationSourceIsAdministered.class};
  }

  public VaccinationAdministeredUnitIsValid() {
    this.addRuleDetection(Detection.VaccinationAdministeredUnitIsDeprecated);
    this.addRuleDetection(Detection.VaccinationAdministeredUnitIsInvalid);
    this.addRuleDetection(Detection.VaccinationAdministeredUnitIsMissing);
    this.addRuleDetection(Detection.VaccinationAdministeredUnitIsPresent);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredUnitIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    issues.addAll(codr.handleCodeOrMissing(target.getAmountUnit(),
        VxuField.VACCINATION_ADMINISTERED_UNIT, target));

    passed = verifyPassed(issues);
    return buildResults(issues, passed);

  }
}
