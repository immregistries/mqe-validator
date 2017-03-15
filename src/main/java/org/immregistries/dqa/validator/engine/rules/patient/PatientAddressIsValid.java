package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.AddressFields;
import org.immregistries.dqa.validator.engine.common.AddressValidator;
import org.immregistries.dqa.validator.issue.VxuField;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.hl7.Address;

public class PatientAddressIsValid extends ValidationRule<DqaPatient> {

	private AddressFields fields = new AddressFields(
			 	VxuField.PATIENT_ADDRESS,
			 	VxuField.PATIENT_ADDRESS_STREET,
			 	VxuField.PATIENT_ADDRESS_STREET2,
			    VxuField.PATIENT_ADDRESS_CITY,
			    VxuField.PATIENT_ADDRESS_STATE,
			    VxuField.PATIENT_ADDRESS_COUNTY,
			    VxuField.PATIENT_ADDRESS_COUNTRY,
			    VxuField.PATIENT_ADDRESS_ZIP,
			    VxuField.PATIENT_ADDRESS_TYPE);

	private AddressValidator addressValidator = AddressValidator.INSTANCE;

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
		};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		Address a = target.getAddress();

		ValidationRuleResult result = addressValidator.getAddressIssuesFor(fields, a);
		result.setRuleClass(this.getClass());
		
		return result;
	}

}
