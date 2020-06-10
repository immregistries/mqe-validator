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

public class VaccinationAdministeredAmountIsReasonable extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationAdministeredAmtIsValid.class,
        VaccinationSourceIsAdministered.class};
  }

  public VaccinationAdministeredAmountIsReasonable() {
    this.addRuleDetection(Detection.VaccinationAdministeredAmountIsInvalid);

    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredAmountIsInvalid);
      id.setImplementationDescription(
          "Vaccination Administered amount is expected to be a number between 0 and 999.");
      id.setHowToFix("The amount of vaccination administered is invalid. Please review the vaccination event and record the correct administered amount. ");
      id.setWhyToFix("While in most cases the amount of vaccination administered is known and can be assumed based on the type of vaccination given, there are some cases where the amount of vaccination given can vary between adult and child doses. Indicating the right dose amount can help in these situations and can also help to build a complete and proper vaccination history. Submitting an invalid amount of vaccination given is confusing and may lead to the registry not understanding correctly what really happened. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;
    String amount = target.getAmount();
    Double d = Double.parseDouble(amount);
    if (d > 999 || d < 0) {
      issues.add(Detection.VaccinationAdministeredAmountIsInvalid.build(amount, target));
    }

    passed = (issues.size() == 0);
    return buildResults(issues, passed);

  }
}
