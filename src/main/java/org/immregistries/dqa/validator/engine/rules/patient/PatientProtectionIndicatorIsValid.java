package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.IssueType;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientProtectionIndicatorIsValid extends ValidationRule<DqaPatient> {

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String protectionCode = target.getProtectionCode();

		//TODO: QUESTION:  Should "Missing" be handled here, or in the codeHandler?
		//I'm thinking...  here.  No compelling reason yet.  just a feeling. 
		
		if (common.isEmpty(protectionCode)) {
			issues.add(PotentialIssue.buildIssue(IssueField.PATIENT_PROTECTION_INDICATOR, IssueType.MISSING));
			passed = false;
		} else if ("Y".equals(protectionCode)) {
		    issues.add(PotentialIssue.PatientProtectionIndicatorIsValuedAsYes.build());
		    passed = false;
		} else if ("N".equals(protectionCode)) {
			issues.add(PotentialIssue.PatientProtectionIndicatorIsValuedAsNo.build());
			passed = false;
	    }
	 
		return buildResults(issues, passed);
	}
	
}
