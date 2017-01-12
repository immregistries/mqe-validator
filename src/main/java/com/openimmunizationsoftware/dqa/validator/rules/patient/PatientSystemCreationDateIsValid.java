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

public class PatientSystemCreationDateIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		Date sysCreationDate = target.getSystemCreationDate();

		if (sysCreationDate == null) {
			issues.add(PotentialIssue.PatientSystemCreationDateIsMissing
					.build());
			passed = false;
		} else {
			Date birthDate = target.getBirthDate();
			if (birthDate != null && this.datr.isBeforeDate(sysCreationDate, birthDate)) {
					issues.add(PotentialIssue.PatientSystemCreationDateIsBeforeBirth.build());
					passed = false;
			}
			
			Date msgReceivedDate = m.getReceivedDate();
			if (msgReceivedDate != null && this.datr.isBeforeDate(sysCreationDate, msgReceivedDate)) {
				issues.add(PotentialIssue.PatientSystemCreationDateIsInFuture.build());
				passed = false;
			}
		}

		return buildResults(issues, passed);
	}

}
