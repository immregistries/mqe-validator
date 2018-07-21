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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class MessageHeaderDateIsExpectedFormat extends ValidationRule<MqeMessageHeader> {

  private final DateTimeFormatter expectedFormat = DateTimeFormat.forPattern("yyyyMMddHHmmssZ");

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
    // PatientExists.class,
    };
  }

  public MessageHeaderDateIsExpectedFormat() {
    this.ruleDetections.add(Detection.MessageMessageDateIsUnexpectedFormat);
  }

  @Override
  protected ValidationRuleResult executeRule(MqeMessageHeader target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (!this.common.isEmpty(target.getMessageDateString())) {
      if (!datr.isExpectedDateFormat(target.getMessageDateString(), expectedFormat)) {
        issues.add(Detection.MessageMessageDateIsUnexpectedFormat.build(
            target.getMessageDateString(), target));
      }
    }

    passed = issues.isEmpty();

    return buildResults(issues, passed);
  }

}
