package org.immregistries.mqe.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.VxuField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHeaderCodesAreValid extends ValidationRule<MqeMessageHeader> {

  private static final Logger logger = LoggerFactory.getLogger(MessageHeaderCodesAreValid.class);

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
    // PatientExists.class,
    };
  }

  @Override
  protected ValidationRuleResult executeRule(MqeMessageHeader target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

     String ackTypeAccept = target.getAckTypeAcceptCode();
     logger.info("getAckTypeAcceptCode:"+ackTypeAccept);
     issues.addAll(codr.handleCode(ackTypeAccept, VxuField.MESSAGE_ACCEPT_ACK_TYPE, target));

     String ackTypeApp = target.getAckTypeApplicationCode();
     logger.info("getAckTypeApplicationCode:"+ackTypeApp);
     issues.addAll(codr.handleCode(ackTypeApp, VxuField.MESSAGE_APP_ACK_TYPE, target));

    passed = issues.size() == 0;
    return buildResults(issues, passed);
  }


}
