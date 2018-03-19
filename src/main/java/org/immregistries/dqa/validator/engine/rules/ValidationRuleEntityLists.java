package org.immregistries.dqa.validator.engine.rules;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.rules.header.*;
import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinAddressIsSameAsPatientAddress;
import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinIsPresent;
import org.immregistries.dqa.validator.engine.rules.patient.*;
import org.immregistries.dqa.validator.engine.rules.vaccination.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("rawtypes")
public enum ValidationRuleEntityLists {
	PATIENT_RULES(
			  new PatientAddressIsValid()
			, new PatientAliasIsValid() 
			, new PatientBirthDateIsValid()
			, new PatientBirthDateIsReasonable()
			, new PatientDeathDateIsValid()
			, new PatientDeathIndicatorIsValid()
			, new PatientEthnicityIsValid()
			, new PatientExists()
			, new PatientFinancialStatusCheckTrue()
			, new PatientFinancialStatusDateIsValid()
			, new PatientGenderIsValid()
			, new PatientIsUnderage()
			, new PatientMedicaidNumberIsValid()
			, new PatientMiddleNameIsPresent()
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
//			, new PatientSsnIsValid() 
			, new PatientSubmitterIsValid()
			, new PatientSystemCreationDateIsValid()
			, new PatientImmunityIsValid()
			, new PatientHasResponsibleParty()
			, new PatientResponsiblePartyIsProperlyFormed()
			), 
	VACCINATION_RULES(
			    new VaccinationIsPresent()
			  , new VaccinationActionCodeIsValid()
			  , new VaccinationAdminAfterBirthDate()
			  , new VaccinationAdminCodeCptIsValid()
			  , new VaccinationAdminCodeCvxIsValid()
			  , new VaccinationAdminCodeIsValid()
			  , new VaccinationAdminDateIsValid()
			  , new VaccinationAdminDateIsValidForPatientAge()
			  , new VaccinationAdministeredRequiredFieldsArePresent()
			  , new VaccinationAdministeredUnitIsValid()
			  , new VaccinationBodyRouteAndSiteAreValid()
			  , new VaccinationCodeGroupsMatch()
			  , new VaccinationCompletionStatusIsValid()
			  , new VaccinationConfidentialityCodeIsValid()
			  , new VaccinationCptIsValid()
			  , new VaccinationCvxIsValid()
			  , new VaccinationCvxUseIsValid()
			  , new VaccinationFinancialEligibilityCodeIsValid()
			  , new VaccinationInformationSourceIsValid()
			  , new VaccinationIsAdministered()
			  , new VaccinationIsAdministeredOrHistorical()
			  , new VaccinationIsForeign()
			  , new VaccinationMfrIsValid()
			  ,	new VaccinationOrdererIsValid()
			  , new VaccinationProductIsValid()
			  , new VaccinationRefusalReasonIsValid()
			  , new VaccinationSystemEntryTimeIsValid()
			  , new VaccinationUseCptInsteadOfCvx()
			  , new VaccinationUseCvx()
			  , new VaccinationAdministeredAmtIsValid()
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
			, new MessageVersionIs25()
			),
	NEXT_OF_KIN_RULES(
			  new NextOfKinIsPresent()
			, new NextOfKinAddressIsSameAsPatientAddress()

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
