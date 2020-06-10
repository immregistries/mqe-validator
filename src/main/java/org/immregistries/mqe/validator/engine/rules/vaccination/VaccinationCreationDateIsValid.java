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
import org.joda.time.DateTime;

public class VaccinationCreationDateIsValid extends ValidationRule<MqeVaccination> {



  public VaccinationCreationDateIsValid() {
    super();
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationSystemEntryDateIsInvalid);
      id.setImplementationDescription(
          "Vaccination System Entry date cannot be translated to a date.");
      id.setHowToFix("The date that this vaccination was entered into the recording system could not be understood because it is formatted wrong. Please contact your vendor and request that they review the system entry date and ensure that it is always encoded properly for reporting. ");
      id.setWhyToFix("The system entry date can be used to help provide context and information that can aid in ensuring the record has been properly recorded and in matching and merging into the official vaccination record. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationSystemEntryDateIsMissing);
      id.setHowToFix("The date that this vaccination was entered into the recording system is not indicated. Please contact your vendor and request that they include the system entry date with all submissions. ");
      id.setWhyToFix("The system entry date can be used to help provide context and information that can aid in ensuring the record has been properly recorded and in matching and merging into the official vaccination record. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.VaccinationSystemEntryDateIsInTheFuture);
      id.setImplementationDescription(
          "Vaccination System Entry date is in the future or is after message recieved date.");
      id.setHowToFix("The date that this vaccination was entered into the recording system is set in the future. This could be because your recording or submitting system has the current date and time set incorrectly or because the sending system is not working properly. Please contact your system administrator or vendor to review and correct. ");
      id.setWhyToFix("The system entry date can be used to help provide context and information that can aid in ensuring the record has been properly recorded and in matching and merging into the official vaccination record. ");
    }

  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed = false;

    if (target.getSystemEntryDateString() == null || target.getSystemEntryDateString().isEmpty()) {
      issues.add(Detection.VaccinationSystemEntryDateIsMissing.build(target));
    } else {
      if (this.common.isValidDate(target.getSystemEntryDateString())) {
        DateTime systemEntryDate = this.common.parseDateTimeFrom(target.getSystemEntryDateString());

        if (systemEntryDate.isAfterNow()
            || datr.isBeforeDate(m.getReceivedDate(), systemEntryDate.toDate())) {
          issues.add(Detection.VaccinationSystemEntryDateIsInTheFuture
              .build(target.getSystemEntryDateString(), target));
        } else {
          if (target.getSystemEntryDate() == null) {
            target.setSystemEntryDate(systemEntryDate.toDate());
          }
          passed = true;
        }
      } else {
        issues.add(Detection.VaccinationSystemEntryDateIsInvalid
            .build(target.getSystemEntryDateString(), target));
      }
    }

    return buildResults(issues, passed);
  }

}
