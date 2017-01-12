package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.types.Id;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class PatientCodesAreValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		if (target == null) {
			return buildResults(issues, false);
		}

		issues.addAll(this.codr.handleCode(target.getBirthOrder(),
				IssueField.PATIENT_BIRTH_ORDER));

		issues.addAll(this.codr.handleCode(target.getEthnicity(),
				IssueField.PATIENT_ETHNICITY));

		issues.addAll(this.codr.handleCode(target.getSex(),
				IssueField.PATIENT_GENDER));

		issues.addAll(this.codr.handleCode(target.getName().getType(),
				IssueField.PATIENT_NAME_TYPE_CODE));

		if (target.getFacility() != null) {
			Id facility = target.getFacility().getId();
			issues.addAll(this.codr.handleCode(facility,
					IssueField.PATIENT_PRIMARY_FACILITY_ID));
		}
		
		issues.addAll(this.codr.handleCode(target.getPrimaryLanguage(),
				IssueField.PATIENT_PRIMARY_LANGUAGE));
		
		issues.addAll(this.codr.handleCode(target.getPhysician(),
				IssueField.PATIENT_PRIMARY_PHYSICIAN_ID));

		issues.addAll(this.codr.handleCode(target.getProtection(),
				IssueField.PATIENT_PROTECTION_INDICATOR));

		issues.addAll(this.codr.handleCode(target.getPublicity(),
				IssueField.PATIENT_PUBLICITY_CODE));
		
		issues.addAll(this.codr.handleCode(target.getRace(),
				IssueField.PATIENT_RACE));

		issues.addAll(this.codr.handleCode(target.getFinancialEligibility(),
				IssueField.PATIENT_VFC_STATUS));

		// mark passed if there's no issues.
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}

}
