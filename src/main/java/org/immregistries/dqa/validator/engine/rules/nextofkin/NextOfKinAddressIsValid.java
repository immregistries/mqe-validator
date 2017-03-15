package org.immregistries.dqa.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.AddressFields;
import org.immregistries.dqa.validator.engine.common.AddressValidator;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.hl7.Address;

public class NextOfKinAddressIsValid extends ValidationRule<DqaNextOfKin> {

	private AddressFields fields = new AddressFields(
			VxuField.NEXT_OF_KIN_ADDRESS,
			VxuField.NEXT_OF_KIN_ADDRESS_STREET,
			VxuField.NEXT_OF_KIN_ADDRESS_STREET2,
			VxuField.NEXT_OF_KIN_ADDRESS_CITY,
			VxuField.NEXT_OF_KIN_ADDRESS_STATE,
			VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY,
			VxuField.NEXT_OF_KIN_ADDRESS_COUNTY,
			VxuField.NEXT_OF_KIN_ADDRESS_ZIP,
			VxuField.NEXT_OF_KIN_ADDRESS_TYPE);

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
			issues.add(MessageAttribute.NextOfKinAddressIsDifferentFromPatientAddress.build(nokAddress.toString()));
		}

		passed = issues.size() == 0;

		return buildResults(issues, passed);
	}

}
