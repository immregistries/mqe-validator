package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.AddressFields;
import org.immregistries.dqa.validator.engine.common.AddressValidator;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaNextOfKin;
import org.immregistries.dqa.validator.model.hl7types.Address;

public class NextOfKinAddressIsValid extends ValidationRule<DqaNextOfKin> {

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
	protected ValidationRuleResult executeRule(DqaNextOfKin target,
			DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		Address nokAddress = target.getAddress();

		ValidationRuleResult addrResult = addressValidator.getAddressIssuesFor(
				fields, target.getAddress());

		passed = addrResult.isRulePassed();
		issues.addAll(addrResult.getIssues());

		Address p = m.getPatient().getAddress();

		if (nokAddress != null && !nokAddress.equals(p)) {
			issues.add(MessageAttribute.NextOfKinAddressIsDifferentFromPatientAddress.build());
		}

		passed = issues.size() == 0;

		return buildResults(issues, passed);
	}

}
