package org.immregistries.dqa.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;

public class MessageHeaderDateIsValid extends ValidationRule<DqaMessageHeader> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
		// PatientExists.class,
		};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target,
			DqaMessageReceived mr) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String messageDateString = target.getMessageDateString();
		if (common.isEmpty(messageDateString)) {
			issues.add(MessageAttribute.MessageMessageDateIsMissing.build());
		} else {
			LOGGER.info("messageDate: " + target.getMessageDate());
			LOGGER.info("receivedDate: " + mr.getReceivedDate());
			if (datr.isAfterDate(target.getMessageDate(), mr.getReceivedDate())) {
				issues.add(MessageAttribute.MessageMessageDateIsInFuture.build(messageDateString));
			}
			
			//Need to do the timezone validation. 
			if (!datr.hasTimezone(messageDateString)) {
				issues.add(MessageAttribute.MessageMessageDateIsMissingTimezone.build(messageDateString));
			}
		}
		
		passed = issues.isEmpty();

		return buildResults(issues, passed);
	}

}
