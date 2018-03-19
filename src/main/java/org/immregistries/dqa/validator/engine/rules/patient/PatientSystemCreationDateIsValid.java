package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PatientSystemCreationDateIsValid extends ValidationRule<DqaPatient> {

	public PatientSystemCreationDateIsValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.PatientSystemCreationDateIsMissing,
				Detection.PatientSystemCreationDateIsBeforeBirth,
				Detection.PatientSystemCreationDateIsInFuture
		));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		Date sysCreationDate = target.getSystemCreationDate();

		if (sysCreationDate == null) {
			issues.add(Detection.PatientSystemCreationDateIsMissing
					.build(target));
			passed = false;
		} else {
			Date birthDate = target.getBirthDate();
			if (birthDate != null && this.datr.isBeforeDate(sysCreationDate, birthDate)) {
					issues.add(Detection.PatientSystemCreationDateIsBeforeBirth.build(target));
					passed = false;
			}
			
			Date msgReceivedDate = m.getReceivedDate();
			if (msgReceivedDate != null && this.datr.isBeforeDate(sysCreationDate, msgReceivedDate)) {
				issues.add(Detection.PatientSystemCreationDateIsInFuture.build(target));
				passed = false;
			}
		}

		return buildResults(issues, passed);
	}

}
