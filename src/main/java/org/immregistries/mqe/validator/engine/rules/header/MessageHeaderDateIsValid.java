package org.immregistries.mqe.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.Arrays;
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
    return new Class[] {
    // PatientExists.class,
    };
  }

  public MessageHeaderDateIsValid() {
    this.ruleDetections.addAll(Arrays.asList(Detection.MessageMessageDateIsMissing,
        Detection.MessageMessageDateIsInFuture, Detection.MessageMessageDateIsMissingTimezone));
  }


  @Override
  protected ValidationRuleResult executeRule(MqeMessageHeader target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String messageDateString = target.getMessageDateString();
    if (this.common.isEmpty(messageDateString)) {
      issues.add(Detection.MessageMessageDateIsMissing.build(target));
    } else {
      LOGGER.info("messageDate: " + target.getMessageDate());
      LOGGER.info("receivedDate: " + mr.getReceivedDate());
      if (datr.isAfterDate(target.getMessageDate(), mr.getReceivedDate())) {
        issues.add(Detection.MessageMessageDateIsInFuture.build((messageDateString), target));
      }

      // Need to do the timezone validation.
      if (!datr.hasTimezone(messageDateString)) {
        issues
            .add(Detection.MessageMessageDateIsMissingTimezone.build((messageDateString), target));
      }
    }

    passed = issues.isEmpty();

    return buildResults(issues, passed);
  }

}
