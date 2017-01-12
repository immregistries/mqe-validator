package com.openimmunizationsoftware.dqa.validator.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.types.Address;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.common.AddressFields;
import com.openimmunizationsoftware.dqa.validator.common.AddressValidator;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class NextOfKinAddressIsValid extends ValidationRule<NextOfKin> {

	private AddressFields fields = new AddressFields(
			IssueField.NEXT_OF_KIN_ADDRESS,
			IssueField.NEXT_OF_KIN_ADDRESS_STREET,
			IssueField.NEXT_OF_KIN_ADDRESS_STREET2,
			IssueField.NEXT_OF_KIN_ADDRESS_CITY,
			IssueField.NEXT_OF_KIN_ADDRESS_STATE,
			IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,
			IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,
			IssueField.NEXT_OF_KIN_ADDRESS_ZIP,
			IssueField.NEXT_OF_KIN_ADDRESS_TYPE);

	private AddressValidator addressValidator = AddressValidator.INSTANCE;

	@Override
	protected ValidationRuleResult executeRule(NextOfKin target,
			MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		Address nokAddress = target.getAddress();

		ValidationRuleResult addrResult = addressValidator.getAddressIssuesFor(
				fields, target.getAddress());

		passed = addrResult.isRulePassed();
		issues.addAll(addrResult.getIssues());

		Address p = m.getPatient().getAddress();

		if (nokAddress != null && !nokAddress.equals(p)) {
			issues.add(PotentialIssue.NextOfKinAddressIsDifferentFromPatientAddress.build());
		}

		passed = issues.size() == 0;

		return buildResults(issues, passed);
	}

}
