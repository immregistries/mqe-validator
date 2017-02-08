package org.immregistries.dqa.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageHeader;
import org.immregistries.dqa.validator.model.DqaMessageReceived;

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

		if (common.isEmpty(target.getMessageDateString())) {
			issues.add(MessageAttribute.MessageMessageDateIsMissing.build());
		} else {
			if (datr.isAfterDate(target.getMessageDate(), mr.getReceivedDate())) {
				issues.add(MessageAttribute.MessageMessageDateIsInFuture.build(target.getMessageDateString()));
			}
			
			//Need to do the timezone validation. 
			if (!datr.hasTimezone(target.getMessageDateString())) {
				issues.add(MessageAttribute.MessageMessageDateIsMissingTimezone.build(target.getMessageDateString()));
			}
		}
		
		passed = issues.isEmpty();

		return buildResults(issues, passed);
	}

}
