package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

public class PatientMultipleBirthsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientMultipleBirthsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientBirthIndicatorIsMissing);
      id.setHowToFix("The multiple birth indicator is not coded. Please verify in the medical record "
          + "if the multiple status is recorded or contact your software vendor to ensure that "
          + "it is being placed in the message. This status should only be indicated (either yes or no) when "
          + "the status is known, otherwise leave it blank. ");
      id.setWhyToFix("Indicating to an IIS that a patient is part of a multiple birth can give "
          + "a clue to not merge records that are very similar. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientBirthOrderIsMissingAndMultipleBirthIndicated);
      id.setImplementationDescription(
          "Multiple birth indicator was sent as Yes but birth order was not.");
      id.setHowToFix("The patient has been flagged as being part of a multiple, birth but with no indication of the birth order. "
          + "Please verify that the birth order is set in the medical record or contact your software vendor to "
          + "code the birth order when it is set. Ideally, a medical record system should prompt the user to indicate "
          + "the birth order when there is a multiple birth. ");
      id.setWhyToFix("Indicating to an IIS that a patient is part of a multiple birth can give "
          + "a clue to not merge records that are very similar. "
          + "Birth order gives even more detail and could even be used to match the right records automatically. ");

    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthOrderIsInvalid);
      id.setImplementationDescription(
          "Multiple birth indicator was sent as No but birth order was sent with value > 1.");
      id.setHowToFix("The message indicates that this patient is definitely not in a multiple birth, but also that the "
          + "birth order is greater than 1. This is inconsistent. Please contact your software vendor and request that "
          + "they ensure that the data in the message is consistent. It should indicate a birth order of 1, indicate "
          + "the patient is part of a multiple birth, or not indicate the multiple birth status. ");
      id.setWhyToFix("Indicating to an IIS that a patient is part of a multiple birth and the birth order can give "
          + "a clue to not merge records that are very similar. But sending conflicting information could confuse "
          + "the IIS and lead to unintended outcomes in matching. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthIndicatorIsInvalid);
      id.setImplementationDescription("Birth indicator is something other than 'Y' or 'N'.");
      id.setHowToFix("The multiple birth indicator is not coded with a proper value. Please contact your software vendor to ensure that "
          + "it is being placed in the message properly. This status should only be indicated (either yes or no) when "
          + "the status is known, otherwise leave it blank. ");
      id.setWhyToFix("Indicating to an IIS that a patient is part of a multiple birth can give "
          + "a clue to not merge records that are very similar. ");

    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    String multipleBirthInd = target.getBirthMultipleInd();

    if (this.common.isEmpty(multipleBirthInd)) {
      issues.add(Detection.PatientBirthIndicatorIsMissing.build(target));
    } else {
      String birthOrder = target.getBirthOrder();

      if ("Y".equals(multipleBirthInd)) {
        // TODO: birth order codes aren't working for some reason
        issues.addAll(
            codr.handleCodeOrMissing(target.getBirthOrder(), VxuField.PATIENT_BIRTH_ORDER, target));

        if (this.common.isEmpty(birthOrder)) {
          issues.add(Detection.PatientBirthOrderIsMissingAndMultipleBirthIndicated.build(target));
        }
      } else if ("N".equals(multipleBirthInd)) {
        if (!this.common.isEmpty(birthOrder) && !"1".equals(birthOrder)) {
          issues.add(Detection.PatientBirthOrderIsInvalid.build(target));
        }
      } else {
        issues.add(Detection.PatientBirthIndicatorIsInvalid.build(target));
      }
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }
}
