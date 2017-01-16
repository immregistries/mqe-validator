package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

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
			Date birthDate = this.common.parseDateFrom(birthDateString);
		
			//in the original validator, the "future" was determined based
			//on when the message is validated...  we might need to keep that. 
			Date receivedDate = message.getReceivedDate();
			if (receivedDate != null && receivedDate.before(birthDate)) { 
				issues.add(MessageAttribute.PatientBirthDateIsInFuture.build(birthDateString));
				passed = false;
			}
			
			Date messageDate = message.getMessageHeader().getMessageDate();
			if (messageDate != null && messageDate.before(birthDate)) {
				issues.add(MessageAttribute.PatientBirthDateIsAfterSubmission.build(birthDateString));
				passed = false;
			}
		}
		
		return buildResults(issues, passed);
	}
	
}
