package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.PhoneValidator;
import org.immregistries.dqa.validator.issue.IssueField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;

public class NextOfKinPhoneIsValid extends ValidationRule<DqaNextOfKin> {

	private PhoneValidator phoneValidator = PhoneValidator.INSTANCE;
	
	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		issues.addAll(
				phoneValidator.validatePhone(target.getPhone(), IssueField.NEXT_OF_KIN_PHONE_NUMBER)
		);
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
	}

}
