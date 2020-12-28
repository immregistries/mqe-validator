package org.immregistries.mqe.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageVersionIsValid extends ValidationRule<MqeMessageHeader> {

  private static final Logger logger = LoggerFactory.getLogger(MessageVersionIsValid.class);

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
        // PatientExists.class,
    };
  }

  public MessageVersionIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageVersionIsMissing);
      id.setImplementationDescription("Message Version is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageVersionIsUnrecognized);
      id.setImplementationDescription("Message version is not a version of 2.3, 2.4, or 2.5 ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeMessageHeader target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    String version = target.getMessageVersion();

    if (this.common.isEmpty(version)) {
      issues.add(Detection.MessageVersionIsMissing.build(target));
    } else {
      // We want to evaluate the starting three characters... 2.5.1 should evaluate as 2.5, etc.
      String evalVersion = version;
      if (evalVersion.length() > 3) {
        evalVersion = evalVersion.substring(0, 3);
        // logger.info("eval version: " + evalVersion);
      }
      switch (evalVersion) {
        case "2.5":
        case "2.4":
        case "2.3":
          passed = true;
          break;
        default:
          issues.add(Detection.MessageVersionIsUnrecognized.build(version, target));
      }
    }

    return buildResults(issues, passed);
  }

}
