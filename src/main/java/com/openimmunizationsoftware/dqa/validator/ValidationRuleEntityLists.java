package com.openimmunizationsoftware.dqa.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.openimmunizationsoftware.dqa.validator.rules.nextofkin.NextOfKinAddressIsSameAsPatientAddress;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientAddressIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientAliasIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientBirthDateIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientDeathDateIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientDeathIndicatorIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientEthnicityIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientExists;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientFinancialStatusCheckTrue;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientFinancialStatusDateIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientGenderIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientIsUnderage;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientMedicaidNumberIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientMiddleNameIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientMothersMaidenNameIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientMultipleBirthsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientNameIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientNameSuffixIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientNameTypeIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientPhoneIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientPrimaryPhysicianNameIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientProtectionIndicatorIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientRegistryIdIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientSsnIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientSubmitterIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientSystemCreationDateIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationActionCodeIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationAdminAfterBirthDate;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationAdminCodeCptIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationAdminCodeCvxIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationAdminDateIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationAdministeredRequiredFieldsArePresent;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationAdministeredUnitIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationCodeGroupsMatch;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationCodesAreValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationCompletionStatusIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationConfidentialityCodeIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationCptIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationCvxUseIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationFinancialEligibilityCodeIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationInformationSourceIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationIsAdministered;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationIsAdministeredOrHistorical;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationMfrIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationObservationsAreValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationProductIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationRefusalReasonIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationSystemEntryTimeIsValid;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationUseCptInsteadOfCvx;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationUseCvx;
import com.openimmunizationsoftware.dqa.validator.rules.vaccination.VaccinationValuedAmtIsValid;

public enum ValidationRuleEntityLists {
	PATIENT_RULES(
			  new PatientAddressIsValid() 
			, new PatientAliasIsValid() 
			, new PatientBirthDateIsValid()
			, new PatientDeathDateIsValid()
			, new PatientDeathIndicatorIsValid()
			, new PatientEthnicityIsValid()
			, new PatientExists()
			, new PatientFinancialStatusCheckTrue()
			, new PatientFinancialStatusDateIsValid()
			, new PatientGenderIsValid()
			, new PatientIsUnderage()
			, new PatientMedicaidNumberIsValid()
			, new PatientMiddleNameIsValid()
			, new PatientMothersMaidenNameIsValid()
			, new PatientMultipleBirthsValid()
			, new PatientNameIsValid()
			, new PatientNameSuffixIsValid()
			, new PatientNameTypeIsValid()
			, new PatientPhoneIsValid()
			, new PatientPrimaryPhysicianNameIsValid()
			, new PatientProtectionIndicatorIsValid()
			, new PatientRegistryIdIsValid()
			, new PatientSsnIsValid() 
			, new PatientSubmitterIsValid()
			, new PatientSystemCreationDateIsValid()
			), 
	VACCINATION_RULES(
				new VaccinationCodesAreValid()
			  , new VaccinationActionCodeIsValid()
			  , new VaccinationAdminAfterBirthDate()
			  , new VaccinationAdminCodeCptIsValid()
			  , new VaccinationAdminCodeCvxIsValid()
			  , new VaccinationAdminDateIsValid()
			  , new VaccinationAdministeredRequiredFieldsArePresent()
			  , new VaccinationAdministeredUnitIsValid()
			  , new VaccinationCodeGroupsMatch()
			  , new VaccinationCompletionStatusIsValid()
			  , new VaccinationConfidentialityCodeIsValid()
			  , new VaccinationCptIsValid()
			  , new VaccinationCvxUseIsValid()
			  , new VaccinationFinancialEligibilityCodeIsValid()
			  , new VaccinationInformationSourceIsValid()
			  , new VaccinationIsAdministered()
			  , new VaccinationIsAdministeredOrHistorical()
			  , new VaccinationMfrIsValid()
			  , new VaccinationObservationsAreValid()
			  , new VaccinationProductIsValid()
			  , new VaccinationRefusalReasonIsValid()
			  , new VaccinationSystemEntryTimeIsValid()
			  , new VaccinationUseCptInsteadOfCvx()
			  , new VaccinationUseCvx()
			  , new VaccinationValuedAmtIsValid()
			  , new VaccinationAdminDateIsValid()
			  , new VaccinationActionCodeIsValid()
			  , new VaccinationAdminAfterBirthDate() 
			  , new VaccinationIsAdministeredOrHistorical()
			  , new VaccinationInformationSourceIsValid()
			  , new VaccinationCptIsValid()
			),
	MESSAGE_RULES(),
	NEXT_OF_KIN_RULES(
			new NextOfKinAddressIsSameAsPatientAddress()
			),
	;
	
	private final List<ValidationRule> rules;
	private ValidationRuleEntityLists(ValidationRule...rulesIn) {
		this.rules = Arrays.asList(rulesIn);
	}
	
	public List<ValidationRule> getRules() {
		List<ValidationRule> rulesCopy = new ArrayList<ValidationRule>();
		rulesCopy.addAll(this.rules);
		return rulesCopy;
	}
}
