package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.IssueType;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientProtectionIndicatorIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String protectionCode = target.getProtectionCode();

		//TODO: QUESTION:  Should "Missing" be handled here, or in the codeHandler?
		//I'm thinking...  here.  No compelling reason yet.  just a feeling. 
		
		if (common.isEmpty(protectionCode)) {
			issues.add(MessageAttribute.buildIssue(VxuField.PATIENT_PROTECTION_INDICATOR, IssueType.MISSING));
			passed = false;
		} else if ("Y".equals(protectionCode)) {
		    issues.add(MessageAttribute.PatientProtectionIndicatorIsValuedAsYes.build(protectionCode));
		    passed = false;
		} else if ("N".equals(protectionCode)) {
			issues.add(MessageAttribute.PatientProtectionIndicatorIsValuedAsNo.build(protectionCode));
			passed = false;
	    }
	 
		return buildResults(issues, passed);
	}
	
}
