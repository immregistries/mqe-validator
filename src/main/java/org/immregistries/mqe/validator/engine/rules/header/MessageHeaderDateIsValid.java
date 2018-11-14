package org.immregistries.mqe.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;

public class MessageHeaderDateIsValid extends ValidationRule<MqeMessageHeader> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[]{
        // PatientExists.class,
    };
  }

  public MessageHeaderDateIsValid() {
    this.addRuleDocumentation(Arrays.asList(
        Detection.MessageMessageDateIsUnexpectedFormat,
        Detection.MessageMessageDateIsMissing,
        Detection.MessageMessageDateIsInFuture,
        Detection.MessageMessageDateIsMissingTimezone));
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
        issues.add(Detection.MessageMessageDateIsUnexpectedFormat.build((messageDateString), target));
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
