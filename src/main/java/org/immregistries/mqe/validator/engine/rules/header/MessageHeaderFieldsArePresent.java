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

public class MessageHeaderFieldsArePresent extends ValidationRule<MqeMessageHeader> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
        // PatientExists.class,
    };
  }

  public MessageHeaderFieldsArePresent() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.MessageReceivingApplicationIsMissing);
      id.setImplementationDescription("Message Receiving Application is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageReceivingFacilityIsMissing);
      id.setImplementationDescription("Message Receiving Facility is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageSendingApplicationIsMissing);
      id.setImplementationDescription("Message Sending Application is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageMessageControlIdIsMissing);
      id.setImplementationDescription("Message Control Id is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageAcceptAckTypeIsMissing);
      id.setImplementationDescription("Message Accept Acknowledgment Type is missing");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.MessageAppAckTypeIsMissing);
      id.setImplementationDescription("Message Application Acknowledgment Type is missing");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeMessageHeader target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (this.common.isEmpty(target.getReceivingApplication())) {
      issues.add(Detection.MessageReceivingApplicationIsMissing.build(target));
    }
    if (this.common.isEmpty(target.getReceivingFacility())) {
      issues.add(Detection.MessageReceivingFacilityIsMissing.build(target));
    }
    if (this.common.isEmpty(target.getSendingApplication())) {
      issues.add(Detection.MessageSendingApplicationIsMissing.build(target));
    }
    if (this.common.isEmpty(target.getMessageControl())) {
      issues.add(Detection.MessageMessageControlIdIsMissing.build(target));
    }

    if (this.common.isEmpty(target.getAckTypeAcceptCode())) {
      issues.add(Detection.MessageAcceptAckTypeIsMissing.build(target));
    }

    if (this.common.isEmpty(target.getAckTypeApplicationCode())) {
      issues.add(Detection.MessageAppAckTypeIsMissing.build(target));
    }
    // if (this.common.isEmpty(target.getSendingRespOrg())) {
    // issues.add(MessageAttribute.MessageSendingResponsibleOrganizationIsMissing.build(target));
    // }

    passed = issues.isEmpty();
    return buildResults(issues, passed);
  }


}
