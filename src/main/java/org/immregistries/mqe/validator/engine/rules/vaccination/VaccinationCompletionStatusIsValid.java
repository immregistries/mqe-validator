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

public class VaccinationCompletionStatusIsValid extends ValidationRule<MqeVaccination> {

  public VaccinationCompletionStatusIsValid() {
    this.addRuleDetection(Detection.VaccinationCompletionStatusIsDeprecated);
    this.addRuleDetection(Detection.VaccinationCompletionStatusIsInvalid);
    this.addRuleDetection(Detection.VaccinationCompletionStatusIsMissing);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    String baseMessage = "Vaccination Completion Code has value of ";
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsCompleted);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.COMPLETION_COMPLETED));
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsRefused);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.COMPLETION_REFUSED));
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsNotAdministered);
      id.setImplementationDescription(
          baseMessage.concat(MqeVaccination.COMPLETION_NOT_ADMINISTERED));
    }
    {
      ImplementationDetail id = this
          .addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsPartiallyAdministered);
      id.setImplementationDescription(
          baseMessage.concat(MqeVaccination.COMPLETION_PARTIALLY_ADMINISTERED));
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    // If vaccination is not actually administered then this is a waiver. Need
    // to check that now, here to see if we need to enforce a value in RXA-9 to
    // indicate that the vaccination is historical or administered.
    // By default we assume that the vaccination was completed.

    String completion = target.getCompletion();

    if (this.common.isEmpty(completion)) {
      passed = false;
      issues.add(Detection.VaccinationCompletionStatusIsMissing.build(target));
    } else {

      issues
          .addAll(this.codr.handleCode(completion, VxuField.VACCINATION_COMPLETION_STATUS, target));
      if (issues.size() > 0) {
        passed = false;
      }
      String completionCode = target.getCompletionCode();
      Detection detection;
      switch (completionCode) {
        case MqeVaccination.COMPLETION_COMPLETED:
          detection = Detection.VaccinationCompletionStatusIsValuedAsCompleted;
          break;
        case MqeVaccination.COMPLETION_REFUSED:
          detection = Detection.VaccinationCompletionStatusIsValuedAsRefused;
          break;
        case MqeVaccination.COMPLETION_NOT_ADMINISTERED:
          detection = Detection.VaccinationCompletionStatusIsValuedAsNotAdministered;
          break;
        case MqeVaccination.COMPLETION_PARTIALLY_ADMINISTERED:
          detection = Detection.VaccinationCompletionStatusIsValuedAsPartiallyAdministered;
          break;
        default:
          detection = Detection.VaccinationCompletionStatusIsUnrecognized;
      }
      issues.add(detection.build(completionCode, target));
    }
    return buildResults(issues, passed);

  }
}
