package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.TargetType;
import org.immregistries.mqe.vxu.VxuField;

@ValidationRuleEntry(TargetType.Vaccination)
public class VaccinationFundingSourceCodeIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationFundingSourceCodeIsValid() {
    this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsDeprecated);
    this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsInvalid);
    this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsMissing);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String fundingSourceCode = target.getFundingSourceCode();

    if (target.isAdministered()) {
      issues.addAll(codr.handleCodeOrMissing(fundingSourceCode,
          VxuField.VACCINATION_FUNDING_SOURCE_CODE, target));
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
