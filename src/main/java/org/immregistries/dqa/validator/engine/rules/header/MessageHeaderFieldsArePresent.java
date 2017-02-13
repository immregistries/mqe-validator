package org.immregistries.dqa.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
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
	
	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target, DqaMessageReceived mr) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		if (common.isEmpty(target.getReceivingApplication())) {
			issues.add(MessageAttribute.MessageReceivingApplicationIsMissing.build());
		}
		if (common.isEmpty(target.getReceivingFacility())) {
			issues.add(MessageAttribute.MessageReceivingFacilityIsMissing.build());
		}
		if (common.isEmpty(target.getSendingApplication())) {
			issues.add(MessageAttribute.MessageSendingApplicationIsMissing.build());
		}
		if (common.isEmpty(target.getMessageControl())) {
			issues.add(MessageAttribute.MessageMessageControlIdIsMissing.build());
		}
		
		if (common.isEmpty(target.getAckTypeAcceptCode())) {
			issues.add(MessageAttribute.MessageAcceptAckTypeIsMissing.build());
		}

		if (common.isEmpty(target.getAckTypeApplicationCode())) {
			issues.add(MessageAttribute.MessageAppAckTypeIsMissing.build());
		}


		passed = issues.isEmpty();
		return buildResults(issues, passed);
	}


}
