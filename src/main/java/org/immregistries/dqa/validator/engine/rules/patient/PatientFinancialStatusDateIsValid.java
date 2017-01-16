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

public class PatientFinancialStatusDateIsValid extends ValidationRule<DqaPatient> {
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
				PatientFinancialStatusCheckTrue.class,
				PatientBirthDateIsValid.class};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		Date finEligDte = target.getFinancialEligibilityDate();
		Date birthDate = target.getBirthDate();
		Date recDate = m.getReceivedDate();
		
		if (finEligDte != null) {

			if (this.datr.isBeforeDate(finEligDte, birthDate)) // finEligDte.before(trunc(birthDate)))
			{
				issues.add(MessageAttribute.PatientVfcEffectiveDateIsBeforeBirth.build());
				passed = false;
			}

			if (this.datr.isBeforeDate(recDate,  finEligDte)) {
				issues.add(MessageAttribute.PatientVfcEffectiveDateIsInFuture.build());
				passed = false;
			}
		}

		return buildResults(issues, passed);
	}

}
