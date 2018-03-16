package org.immregistries.dqa.validator.engine.rules.header;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageHeaderDateIsValid extends ValidationRule<DqaMessageHeader> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
		// PatientExists.class,
		};
	}
	
	public MessageHeaderDateIsValid() {
		this.ruleDetections.addAll(Arrays.asList(Detection.MessageMessageDateIsMissing,
				Detection.MessageMessageDateIsInFuture,
				Detection.MessageMessageDateIsMissingTimezone));
	}


	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target,
			DqaMessageReceived mr) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String messageDateString = target.getMessageDateString();
		if (this.common.isEmpty(messageDateString)) {
			issues.add(Detection.MessageMessageDateIsMissing.build(target));
		} else {
			LOGGER.info("messageDate: " + target.getMessageDate());
			LOGGER.info("receivedDate: " + mr.getReceivedDate());
			if (datr.isAfterDate(target.getMessageDate(), mr.getReceivedDate())) {
				issues.add(Detection.MessageMessageDateIsInFuture.build((messageDateString), target));
			}
			
			//Need to do the timezone validation. 
			if (!datr.hasTimezone(messageDateString)) {
				issues.add(Detection.MessageMessageDateIsMissingTimezone.build((messageDateString), target));
			}
		}
		
		passed = issues.isEmpty();

		return buildResults(issues, passed);
	}

}
