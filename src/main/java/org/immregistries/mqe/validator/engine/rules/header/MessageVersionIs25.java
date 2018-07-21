package org.immregistries.mqe.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;

public class MessageVersionIs25 extends ValidationRule<MqeMessageHeader> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {MessageVersionIsValid.class};
  }

  @Override
  protected ValidationRuleResult executeRule(MqeMessageHeader target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    String version = target.getMessageVersion();

    boolean passed = version != null && version.startsWith("2.5");

    return buildResults(issues, passed);
  }

}
