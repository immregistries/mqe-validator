package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientSsnIsValid extends ValidationRule<DqaPatient> {

	public PatientSsnIsValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.get(VxuField.PATIENT_SSN,  IssueType.MISSING),
				Detection.get(VxuField.PATIENT_SSN,  IssueType.INVALID)
		));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String ssn = target.getIdSsnNumber();
		
		if (common.isEmpty(ssn)) {
			issues.add(Detection.get(VxuField.PATIENT_SSN,  IssueType.MISSING).build());
			passed = false;
		} else if (!isSsnPattern(ssn)) {
			issues.add(Detection.get(VxuField.PATIENT_SSN,  IssueType.INVALID).build());
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
