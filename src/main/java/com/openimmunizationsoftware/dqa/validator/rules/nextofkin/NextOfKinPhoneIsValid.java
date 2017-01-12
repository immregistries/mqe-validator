package com.openimmunizationsoftware.dqa.validator.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.common.PhoneValidator;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class NextOfKinPhoneIsValid extends ValidationRule<NextOfKin> {

	private PhoneValidator phoneValidator = PhoneValidator.INSTANCE;
	
	@Override
	protected ValidationRuleResult executeRule(NextOfKin target, MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		issues.addAll(
				phoneValidator.validatePhone(target.getPhone(), IssueField.NEXT_OF_KIN_PHONE_NUMBER)
		);
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
	}

}
