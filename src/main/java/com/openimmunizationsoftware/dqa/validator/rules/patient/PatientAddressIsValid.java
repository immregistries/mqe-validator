package com.openimmunizationsoftware.dqa.validator.rules.patient;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.types.Address;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.common.AddressFields;
import com.openimmunizationsoftware.dqa.validator.common.AddressValidator;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;

public class PatientAddressIsValid extends ValidationRule<Patient> {

	private AddressFields fields = new AddressFields(
			 	IssueField.PATIENT_ADDRESS,
			 	IssueField.PATIENT_ADDRESS_STREET,
			 	IssueField.PATIENT_ADDRESS_STREET2,
			    IssueField.PATIENT_ADDRESS_CITY,
			    IssueField.PATIENT_ADDRESS_STATE,
			    IssueField.PATIENT_ADDRESS_COUNTY,
			    IssueField.PATIENT_ADDRESS_COUNTRY,
			    IssueField.PATIENT_ADDRESS_ZIP,
			    IssueField.PATIENT_ADDRESS_TYPE);

	private AddressValidator addressValidator = AddressValidator.INSTANCE;

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			PatientExists.class, 
		};
	}
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		Address a = target.getAddress();

		ValidationRuleResult result = addressValidator.getAddressIssuesFor(fields, a);
		result.setRuleClass(this.getClass());
		
		return result;
	}

}
