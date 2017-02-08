package org.immregistries.dqa.validator.engine.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.rules.header.*;
import org.immregistries.dqa.validator.engine.rules.nextofkin.*;
import org.immregistries.dqa.validator.engine.rules.patient.*;
import org.immregistries.dqa.validator.engine.rules.vaccination.*;

@SuppressWarnings("rawtypes")
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
			, new PatientImmunityIsValid()
			, new MessageHasResponsibleParty()
			), 
	VACCINATION_RULES(
				new VaccinationOrdererIsValid()
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
			  , new VaccinationVisCvxIsValid()
			  , new VaccinationVisDatesAreValid()
			  , new VaccinationVisIsPresent()
			  , new VaccinationVisIsRecognized()
			),
	MESSAGE_HEADER_RULES(
			  new MessageHeaderCodesAreValid()
			, new MessageHeaderFieldsArePresent()
			, new MessageHeaderDateIsValid()
			, new MessageHeaderDateIsExpectedFormat()
			, new MessageVersionIsValid()
			, new MessageVersionIs23()
			, new MessageVersionIs24()
			, new MessageVersionIs25()
			),
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
