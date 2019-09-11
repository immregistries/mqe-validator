package org.immregistries.mqe.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;

public class MessageHeaderDateIsValid extends ValidationRule<MqeMessageHeader> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
        // PatientExists.class,
    };
  }

  public MessageHeaderDateIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.MessageMessageDateIsUnexpectedFormat);
      id.setImplementationDescription("Message Header date cannot be translated to a date");
      id.setHowToFix(
          "The problem is in the format of the HL7 message being sent. Please contact your software vendor to fix this issue. ");
      id.setWhyToFix(
          "The message date indicates the date and time when this message was created and is needed to provide proper context to all other dates in this message. Not knowing the date, time and timezone used for this message can cause other dates to be read improperly. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageMessageDateIsInFuture);
      id.setImplementationDescription("Message Header date is over 2 hours into the future.");
      id.setHowToFix(
          "The system creating or sending the HL7 message is not reporting the right date and time. The date and time is being reported in the future. Please check the sending system and ensure that it has the time set correctly. The sending system may send in any time zone but the time must be set correctly for the time zone. ");
      id.setWhyToFix(
          "It is important to ensure that vaccinations are reported and recorded on the right date. Incorrect time settings can affect not only the contents of the message but the recording of critical immunization data in other places. Ensuring that all systems involved in handling immunization data are set to the same time is critical to ensure correct communication of dates and times. ");
    }
  }


  @Override
  protected ValidationRuleResult executeRule(MqeMessageHeader target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String messageDateString = target.getMessageDateString();
    if (this.common.isEmpty(messageDateString)) {
      issues.add(Detection.MessageMessageDateIsMissing.build(target));
      passed = false;
    } else {
      LOGGER.info("messageDate: " + target.getMessageDate());
      LOGGER.info("messageDateString: " + target.getMessageDateString());
      LOGGER.info("receivedDate: " + mr.getReceivedDate());
      if (target.getMessageDate() == null) {
        //must have failed parsing, so add a detection saying its not valid.
        issues
            .add(Detection.MessageMessageDateIsUnexpectedFormat.build((messageDateString), target));
        passed = false;
      } else {
        Date t = target.getMessageDate();
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(t); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 2); // adds one hour to account for system time
        Date modifiedMessageDate = cal.getTime();
        if (datr.isAfterDate(modifiedMessageDate, mr.getReceivedDate())) {
          issues.add(Detection.MessageMessageDateIsInFuture.build((messageDateString), target));
          passed = false;
        }

        // Need to do the timezone validation.
        if (!datr.hasTimezone(messageDateString)) {
          issues.add(
              Detection.MessageMessageDateIsMissingTimezone.build((messageDateString), target));
          //doesn't fail. we can still use it.
        }
      }
    }

    return buildResults(issues, passed);
  }

}
