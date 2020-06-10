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

public class VaccinationFundingSourceCodeIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationFundingSourceCodeIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsDeprecated);
      id.setHowToFix("The vaccination funding source code is not a good value. Please update your funding source codes you use or contact your vendor to have the correct code submitted with vaccination records. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsInvalid);
      id.setHowToFix("The vaccination funding source code is not a good value. Please update your funding source codes you use or contact your vendor to have the correct code submitted with vaccination records. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsMissing);
      id.setHowToFix("The vaccination funding source code is not indicated. Please ensure that the funding source is indicated or contact your vendor to have the it submitted with vaccination records. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationFundingSourceCodeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination funding source code is not a good value. Please update your funding source codes you use or contact your vendor to have the correct code submitted with vaccination records. ");
      id.setWhyToFix("The use of publicly supplied vaccinations must be accounted for. Reporting this information can provide the information needed to comply with program requirements and ensure continued access to publicly funded vaccines. It can also be used to help resolve issues when public or private vaccines are borrowed to meet immediate needs. ");
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
