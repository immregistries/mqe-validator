package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientSystemCreationDateIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
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
