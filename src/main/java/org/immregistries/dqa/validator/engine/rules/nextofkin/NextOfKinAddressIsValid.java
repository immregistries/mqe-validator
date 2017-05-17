package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.AddressFields;
import org.immregistries.dqa.validator.engine.common.AddressValidator;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.hl7.Address;

import java.util.ArrayList;
import java.util.List;

public class NextOfKinAddressIsValid extends ValidationRule<DqaNextOfKin> {

    private AddressFields fields = new AddressFields(
            VxuField.NEXT_OF_KIN_ADDRESS,
            VxuField.NEXT_OF_KIN_ADDRESS_STREET,
            VxuField.NEXT_OF_KIN_ADDRESS_STREET2,
            VxuField.NEXT_OF_KIN_ADDRESS_CITY,
            VxuField.NEXT_OF_KIN_ADDRESS_STATE,
            VxuField.NEXT_OF_KIN_ADDRESS_COUNTY,
            VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY,
            VxuField.NEXT_OF_KIN_ADDRESS_ZIP,
            VxuField.NEXT_OF_KIN_ADDRESS_TYPE);

    private AddressValidator addressValidator = AddressValidator.INSTANCE;

    @Override
    protected ValidationRuleResult executeRule(DqaNextOfKin target,
                                               DqaMessageReceived m) {
        List<ValidationIssue> issues = new ArrayList<>();
        boolean passed;

        Address nokAddress = target.getAddress();
        Address p = m.getPatient().getAddress();

        ValidationRuleResult addrResult = addressValidator.getAddressIssuesFor(fields, nokAddress);
        issues.addAll(addrResult.getIssues());

        if (nokAddress != null) {
            if (!nokAddress.equals(p)) {
                //TODO this functionality is also in NextOfKinAddressIsSameAsPatientAddress, which should we use?
                issues.add(MessageAttribute.NextOfKinAddressIsDifferentFromPatientAddress.build(nokAddress.toString()));
            }

            if (nokAddress.getTypeCode() != null && "BA".equals(nokAddress.getTypeCode())) {
                issues.add(MessageAttribute.NextOfKinAddressTypeIsValuedBadAddress.build(nokAddress.toString()));
            }

            issues.addAll(this.codr.handleCode(nokAddress.getTypeCode(), VxuField.NEXT_OF_KIN_ADDRESS_TYPE));
        }

        passed = issues.size() == 0;

        return buildResults(issues, passed);
    }

}
