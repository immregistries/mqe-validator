package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientBirthDateIsValid extends ValidationRule<Patient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class};
	}

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived message) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String birthDateString = target.getBirthDateString();
		
		if (this.common.isEmpty(birthDateString)) {
			issues.add(PotentialIssue.PatientBirthDateIsMissing.build(birthDateString));
			passed = false;
		}
		
		if (!this.common.isValidDate(birthDateString)) {
			issues.add(PotentialIssue.PatientBirthDateIsInvalid.build(birthDateString));
			passed = false;
		} else {
			Date birthDate = this.common.parseDateFrom(birthDateString);
		
			//in the original validator, the "future" was determined based
			//on when the message is validated...  we might need to keep that. 
			Date receivedDate = message.getReceivedDate();
			if (receivedDate != null && receivedDate.before(birthDate)) { 
				issues.add(PotentialIssue.PatientBirthDateIsInFuture.build(birthDateString));
				passed = false;
			}
			
			Date messageDate = message.getMessageHeader().getMessageDate();
			if (messageDate != null && messageDate.before(birthDate)) {
				issues.add(PotentialIssue.PatientBirthDateIsAfterSubmission.build(birthDateString));
				passed = false;
			}
		}
		
		return buildResults(issues, passed);
	}
	
}
