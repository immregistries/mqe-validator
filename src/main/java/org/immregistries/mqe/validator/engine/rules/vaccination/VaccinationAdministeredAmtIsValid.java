package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class VaccinationAdministeredAmtIsValid extends ValidationRule<MqeVaccination> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {VaccinationSourceIsAdministered.class};
  }

  public VaccinationAdministeredAmtIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredAmountIsMissing);
      id.setImplementationDescription(
          "Vaccination Administered Amount is missing or equal to 999.");
      id.setHowToFix("The amount of vaccination given was not indicated. Please ensure that the amount of vaccination given is recorded. ");
      id.setWhyToFix("While in most cases the amount of vaccination administered is known and can be assumed based on the type of vaccination given, there are some cases where the amount of vaccination given can vary between adult and child doses. Indicating the right dose amount can help in these situations and can also help to build a complete and proper vaccination history. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredAmountIsValuedAsUnknown);
      id.setImplementationDescription(
          "Vaccination Administered Amount is missing or equal to 999.");
      id.setHowToFix("The amount of vaccination given was not indicated. Please ensure that the amount of vaccination given is recorded. ");
      id.setWhyToFix("While in most cases the amount of vaccination administered is known and can be assumed based on the type of vaccination given, there are some cases where the amount of vaccination given can vary between adult and child doses. Indicating the right dose amount can help in these situations and can also help to build a complete and proper vaccination history. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredAmountIsValuedAsZero);
      id.setImplementationDescription("Vaccination Administered Amount is 0.");
      id.setHowToFix("The amount of vaccination administered is set to zero, which indicates that no vaccination was given. If this is the case then no immunization should have been reported. If the vaccination was properly and completely administered, but the amount was not known then it should be reported without indicating the amount. If the vaccination was not properly given then it should either not be reported, or if it was previously reported then a delete should be sent to remove the record. Please contact your vendor and request that immunizations not be reported with zero amount of vaccination. ");
      id.setWhyToFix("While in most cases the amount of vaccination administered is known and can be assumed based on the type of vaccination given, there are some cases where the amount of vaccination given can vary between adult and child doses. Indicating the right dose amount can help in these situations and can also help to build a complete and proper vaccination history. Submitting a zero amount of vaccination given is confusing and may lead to the registry not understanding correctly what really happened. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationAdministeredAmountIsInvalid);
      id.setImplementationDescription(
          "Vaccination Administered Amount could not be converted into a number.");
      id.setHowToFix("The amount of vaccination given was not correctly formatted and therefore is not understood. Please contact your vendor and ask that they only submit administration amounts that are correctly formatted numbers between 0 and 999. ");
      id.setWhyToFix("While in most cases the amount of vaccination administered is known and can be assumed based on the type of vaccination given, there are some cases where the amount of vaccination given can vary between adult and child doses. Indicating the right dose amount can help in these situations and can also help to build a complete and proper vaccination history. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String administeredAmount = target.getAmount();

    if (target.isAdministered()) {
      if (this.common.isEmpty(administeredAmount) || "999".equals(administeredAmount)) {
        issues.add(Detection.VaccinationAdministeredAmountIsMissing.build(target));
        issues.add(Detection.VaccinationAdministeredAmountIsValuedAsUnknown.build(target));
      } else {
        try {
          float amount = Float.parseFloat(target.getAmount());
          if (amount == 0) {
            issues.add(Detection.VaccinationAdministeredAmountIsValuedAsZero.build(target));
          } else {
            passed = true;
          }
        } catch (NumberFormatException nfe) {
          issues.add(
              Detection.VaccinationAdministeredAmountIsInvalid.build(target.getAmount(), target));
        }
      }
    }

    return buildResults(issues, passed);
  }
}
