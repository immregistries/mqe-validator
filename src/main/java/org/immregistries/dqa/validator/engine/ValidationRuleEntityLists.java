package org.immregistries.dqa.validator.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinAddressIsSameAsPatientAddress;
import org.immregistries.dqa.validator.engine.rules.patient.MessageHasResponsibleParty;
import org.immregistries.dqa.validator.engine.rules.patient.PatientAddressIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientAliasIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientDeathDateIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientDeathIndicatorIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientEthnicityIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientExists;
import org.immregistries.dqa.validator.engine.rules.patient.PatientFinancialStatusCheckTrue;
import org.immregistries.dqa.validator.engine.rules.patient.PatientFinancialStatusDateIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientGenderIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.dqa.validator.engine.rules.patient.PatientMedicaidNumberIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientMiddleNameIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientMothersMaidenNameIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientMultipleBirthsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientNameIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientNameSuffixIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientNameTypeIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientPhoneIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientPrimaryPhysicianNameIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientProtectionIndicatorIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientRegistryIdIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientSsnIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientSubmitterIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientSystemCreationDateIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationActionCodeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminAfterBirthDate;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminCodeCptIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminCodeCvxIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminDateIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdministeredRequiredFieldsArePresent;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdministeredUnitIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCodeGroupsMatch;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationOrdererIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCompletionStatusIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationConfidentialityCodeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCptIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCvxUseIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationFinancialEligibilityCodeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationInformationSourceIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsAdministered;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsAdministeredOrHistorical;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationMfrIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationObservationsAreValidFIXTHIS;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationProductIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationRefusalReasonIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationSystemEntryTimeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationUseCptInsteadOfCvx;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationUseCvx;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationValuedAmtIsValid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
			  , new VaccinationObservationsAreValidFIXTHIS()
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
