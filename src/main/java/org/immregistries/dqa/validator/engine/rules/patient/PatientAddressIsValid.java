package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.AddressFields;
import org.immregistries.dqa.validator.engine.common.AddressValidator;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;
import org.immregistries.dqa.validator.model.hl7types.Address;

public class PatientAddressIsValid extends ValidationRule<DqaPatient> {

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
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		Address a = target.getAddress();

		ValidationRuleResult result = addressValidator.getAddressIssuesFor(fields, a);
		result.setRuleClass(this.getClass());
		
		return result;
	}

}
