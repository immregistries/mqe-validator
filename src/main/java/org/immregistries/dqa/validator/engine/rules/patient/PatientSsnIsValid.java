package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.IssueType;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientSsnIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String ssn = target.getIdSsnNumber();
		
		if (common.isEmpty(ssn)) {
			issues.add(MessageAttribute.get(IssueField.PATIENT_SSN,  IssueType.MISSING).build());
			passed = false;
		} else if (!isSsnPattern(ssn)) {
			issues.add(MessageAttribute.get(IssueField.PATIENT_SSN,  IssueType.INVALID).build());
			passed = false;
		}
		
		return buildResults(issues, passed);
	}
	
	protected boolean isSsnPattern(String val) {
		if ((val.indexOf("000") == 0) 
		|| (val.indexOf("00", 3) == 3)
		|| (!val.matches("[0-9]{9}")) || (val.equals("123456789"))
		|| val.equals("987654321")
		|| (common.hasTooManyConsecutiveChars(val, 6))) {
			return false;
		}

		return true;
	}
	
}
