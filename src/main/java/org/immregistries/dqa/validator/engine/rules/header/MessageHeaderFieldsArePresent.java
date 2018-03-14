package org.immregistries.dqa.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;

public class MessageHeaderFieldsArePresent extends ValidationRule<DqaMessageHeader> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
//			PatientExists.class, 
		};
	}
	
	public MessageHeaderFieldsArePresent() {
		this.ruleDetections.addAll(Arrays.asList(Detection.MessageReceivingApplicationIsMissing,
				Detection.MessageReceivingFacilityIsMissing,
				Detection.MessageSendingApplicationIsMissing,
				Detection.MessageMessageControlIdIsMissing,
				Detection.MessageAcceptAckTypeIsMissing,
				Detection.MessageAppAckTypeIsMissing));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target, DqaMessageReceived mr) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (common.isEmpty(target.getReceivingApplication())) {
			issues.add(Detection.MessageReceivingApplicationIsMissing.build());
		}
		if (common.isEmpty(target.getReceivingFacility())) {
			issues.add(Detection.MessageReceivingFacilityIsMissing.build());
		}
		if (common.isEmpty(target.getSendingApplication())) {
			issues.add(Detection.MessageSendingApplicationIsMissing.build());
		}
		if (common.isEmpty(target.getMessageControl())) {
			issues.add(Detection.MessageMessageControlIdIsMissing.build());
		}
		
		if (common.isEmpty(target.getAckTypeAcceptCode())) {
			issues.add(Detection.MessageAcceptAckTypeIsMissing.build());
		}

		if (common.isEmpty(target.getAckTypeApplicationCode())) {
			issues.add(Detection.MessageAppAckTypeIsMissing.build());
		}
//		if (common.isEmpty(target.getSendingRespOrg())) {
//			issues.add(MessageAttribute.MessageSendingResponsibleOrganizationIsMissing.build());
//		}
		
		passed = issues.isEmpty();
		return buildResults(issues, passed);
	}


}
