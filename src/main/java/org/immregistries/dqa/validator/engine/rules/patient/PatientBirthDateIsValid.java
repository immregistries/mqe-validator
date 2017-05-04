package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.joda.time.DateTime;

public class PatientBirthDateIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String birthDateString = target.getBirthDateString();
		
		if (this.common.isEmpty(birthDateString)) {
			issues.add(MessageAttribute.PatientBirthDateIsMissing.build(birthDateString));
			passed = false;
		}
		
		if (!this.common.isValidDate(birthDateString)) {
			issues.add(MessageAttribute.PatientBirthDateIsInvalid.build(birthDateString));
			passed = false;
		} else {
			DateTime birthDate = this.common.parseDateTimeFrom(birthDateString);
		
			//in the original validator, the "future" was determined based
			//on when the message is validated...  we might need to keep that. 
			DateTime receivedDate = new DateTime(message.getReceivedDate());
			
			if (receivedDate != null && receivedDate.isBefore(birthDate)) { 
				issues.add(MessageAttribute.PatientBirthDateIsInFuture.build(birthDateString));
				passed = false;
			}
			
			DateTime messageDate = new DateTime(message.getMessageHeader().getMessageDate());
			
			if (messageDate != null && messageDate.isBefore(birthDate)) {
				issues.add(MessageAttribute.PatientBirthDateIsAfterSubmission.build(birthDateString));
				passed = false;
			}

		}
		
		return buildResults(issues, passed);
	}
	
}
