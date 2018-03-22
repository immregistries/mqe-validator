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

public class MessageHeaderFieldsArePresent extends ValidationRule<DqaMessageHeader> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
    // PatientExists.class,
    };
  }

  public MessageHeaderFieldsArePresent() {
    this.ruleDetections.addAll(Arrays.asList(Detection.MessageReceivingApplicationIsMissing,
        Detection.MessageReceivingFacilityIsMissing, Detection.MessageSendingApplicationIsMissing,
        Detection.MessageMessageControlIdIsMissing, Detection.MessageAcceptAckTypeIsMissing,
        Detection.MessageAppAckTypeIsMissing));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaMessageHeader target, DqaMessageReceived mr) {

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
