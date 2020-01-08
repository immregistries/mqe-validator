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
import org.joda.time.DateTime;

public class PatientCreationDateIsValid extends ValidationRule<MqePatient> {



  public PatientCreationDateIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientSystemEntryDateIsInvalid);
      id.setImplementationDescription("Patient system entry date cannot be translated to a date.");
      id.setHowToFix(
          "The patient system entry date, which indicates when the patient information sent to the IIS was entered into "
              + "the sending system, is incorrectly formatted. "
              + "Please contact your system vendor and request that the format of this date be encoded properly or omitted from the message. ");
      id.setWhyToFix(
          "The system entry date may help provide context for information, especially when trying to determine which reported "
              + "patient record is the most recently updated. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientSystemEntryDateIsMissing);
      id.setHowToFix(
          "The patient system entry date, which indicates when the patient information sent to the IIS was entered into "
              + "the sending system, was not sent. "
              + "Please contact your system vendor and request that this date be sent in every message. ");
      id.setWhyToFix(
          "The system entry date may help provide context for information, especially when trying to determine which reported "
              + "patient record is the most recently updated. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientSystemEntryDateIsInTheFuture);
      id.setHowToFix(
          "The patient system entry date, which indicates when the patient information sent to the IIS was entered into "
              + "the sending system, is in the future. "
              + "Please ask your system administrator to verify that the system has the date set properly and that the data in your "
              + "medical record system is encoded with the right dates. ");
      id.setWhyToFix(
          "It is important for all software systems to know and operate on the right date and time for their time zone and place in the world. "
              + "Systems that are operated on the wrong date and time will make errors when recording and reporting information. Fixing "
              + "this problem will ensure that other dates and times in the same message are being transmitted and understood correctly. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed = false;

    if (target.getSystemEntryDateString() == null || target.getSystemEntryDateString().isEmpty()) {
      issues.add(Detection.PatientSystemEntryDateIsMissing.build(target));
    } else {
      if (this.common.isValidDate(target.getSystemEntryDateString())) {
        DateTime systemEntryDate = this.common.parseDateTimeFrom(target.getSystemEntryDateString());
        if (systemEntryDate.isAfterNow()) {
          issues.add(Detection.PatientSystemEntryDateIsInTheFuture
              .build(target.getSystemEntryDateString(), target));
        } else {
          if (target.getSystemEntryDate() == null) {
            target.setSystemEntryDate(systemEntryDate.toDate());
          }
          passed = true;
        }
      } else {
        issues.add(Detection.PatientSystemEntryDateIsInvalid
            .build(target.getSystemEntryDateString(), target));
      }
    }

    return buildResults(issues, passed);
  }

}
