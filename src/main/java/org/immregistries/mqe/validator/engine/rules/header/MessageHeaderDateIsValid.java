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
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.MessageHeader)
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
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageMessageDateIsInFuture);
      id.setImplementationDescription("Message Header date is over 2 hours into the future.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageMessageDateIsMissing);
      id.setImplementationDescription("Message Header date was not indicated.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageMessageDateIsPresent);
      id.setImplementationDescription("Message Header date was indicated.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageMessageDateTimezoneIsMissing);
      id.setImplementationDescription("Message Header date timezone was not indicated.");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageMessageDateTimezoneIsPresent);
      id.setImplementationDescription("Message Header date timezone was indicated.");
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
      issues.add(Detection.MessageMessageDateIsPresent.build(target));
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
        if (datr.hasTimezone(messageDateString)) {
          issues.add(
              Detection.MessageMessageDateTimezoneIsPresent.build((messageDateString), target));
        } else {
          issues.add(
              Detection.MessageMessageDateTimezoneIsMissing.build((messageDateString), target));
          //doesn't fail. we can still use it.
        }
      }
    }

    return buildResults(issues, passed);
  }

}
