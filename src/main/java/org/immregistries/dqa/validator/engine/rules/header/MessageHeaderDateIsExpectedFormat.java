package org.immregistries.dqa.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class MessageHeaderDateIsExpectedFormat extends ValidationRule<DqaMessageHeader> {

  private final String expected = "yyyyMMddHHmmssZ";
  private final DateTimeFormatter expectedFormat = DateTimeFormat.forPattern(expected);

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
    // PatientExists.class,
    };
  }

  public MessageHeaderDateIsExpectedFormat() {
    this.ruleDetections.addAll(Arrays.asList(Detection.MessageMessageDateIsUnexpectedFormat));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaMessageHeader target, DqaMessageReceived mr) {

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
