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
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsDeprecated);
      id.setHowToFix("The vaccination completion status, which indicates if the vaccination was given completely or not, is using an old code that should no longer be used. Please contact your vendor and request that they update the codes they use for reporting vaccination completeness. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. The patient may refuse a vaccination or it may not be completely administered properly. These may be reported to the IIS but need to be properly marked to indicate that they were not completed and so should not count towards the patient's immunization status. The completion status needs to be reported correctly so that the information can be properly recorded. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsInvalid);
      id.setHowToFix("The vaccination completion status, which indicates if the vaccination was given completely or not, is using an old code that should no longer be used. Please contact your vendor and request that they update the codes they use for reporting vaccination completeness. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. The patient may refuse a vaccination or it may not be completely administered properly. These may be reported to the IIS but need to be properly marked to indicate that they were not completed and so should not count towards the patient's immunization status. The completion status needs to be reported correctly so that the information can be properly recorded. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsMissing);
      id.setHowToFix("The vaccination completion status, which indicates if the vaccination was given completely or not, was not indicated. Please contact your vendor to always indicate the completeness status when it is known. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. The patient may refuse a vaccination or it may not be completely administered properly. These may be reported to the IIS but need to be properly marked to indicate that they were not completed and so should not count towards the patient's immunization status. The completion status needs to be reported correctly so that the information can be properly recorded. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The vaccination completion status, which indicates if the vaccination was given completely or not, is using an old code that is not understood. Please contact your vendor and request that they update the codes they use for reporting vaccination completeness. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. The patient may refuse a vaccination or it may not be completely administered properly. These may be reported to the IIS but need to be properly marked to indicate that they were not completed and so should not count towards the patient's immunization status. The completion status needs to be reported correctly so that the information can be properly recorded. ");
    }
    String baseMessage = "Vaccination Completion Code has value of ";
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsCompleted);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.COMPLETION_COMPLETED));
      id.setHowToFix("The vaccination completion status is indicating that this vaccination was administered completely. If this is correct then there is nothing to fix. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. It is important to track to ensure that information is being recorded properly. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsRefused);
      id.setImplementationDescription(baseMessage.concat(MqeVaccination.COMPLETION_REFUSED));
      id.setHowToFix("The vaccination completion status is indicating that this vaccination was refused. If this is correct then there is nothing to fix. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. It is important to track to ensure that information is being recorded properly. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsNotAdministered);
      id.setImplementationDescription(
          baseMessage.concat(MqeVaccination.COMPLETION_NOT_ADMINISTERED));
      id.setHowToFix("The vaccination completion status is indicating that this vaccination was not administered. If this is correct then there is nothing to fix. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. It is important to track to ensure that information is being recorded properly. ");
    }
    {
      ImplementationDetail id = this
          .addRuleDetection(Detection.VaccinationCompletionStatusIsValuedAsPartiallyAdministered);
      id.setImplementationDescription(
          baseMessage.concat(MqeVaccination.COMPLETION_PARTIALLY_ADMINISTERED));
      id.setHowToFix("The vaccination completion status is indicating that this vaccination was partially administered. If this is correct then there is nothing to fix. ");
      id.setWhyToFix("The completion status indicates the final status of the immunization being reported. It is important to track to ensure that information is being recorded properly. ");
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
