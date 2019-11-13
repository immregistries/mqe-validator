package org.immregistries.mqe.validator.engine.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderCodesAreValid;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderDateIsValid;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderFieldsArePresent;
import org.immregistries.mqe.validator.engine.rules.header.MessageVersionIs25;
import org.immregistries.mqe.validator.engine.rules.header.MessageVersionIsValid;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinAddressIsSameAsPatientAddress;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinAddressIsValid;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinGuardianAddressIsValid;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinIsPresent;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinNameIsNotSameAsPatient;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinNameIsValid;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinPhoneIsValid;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinRelationshipIsValidForUnderagedPatient;
import org.immregistries.mqe.validator.engine.rules.patient.*;
import org.immregistries.mqe.validator.engine.rules.vaccination.*;

public enum ValidationRuleEntityLists {
  //@formatter:off
  PATIENT_RULES(
      new PatientAddressIsValid(),
      new PatientAliasIsPresent(),
      new PatientBirthDateIsValid(),
      new PatientBirthDateIsReasonable(),
      new PatientBirthDateCharacteristic(),
      new PatientBirthPlaceIsValid(),
      new PatientClassIsValid(),
      new PatientCodesAreValid(),
      new PatientCreationDateIsValid(),
      new PatientCreationTimeliness(),
      new PatientDeathDateIsValid(),
      new PatientDeathIndicatorIsValid(),
      new PatientEmailIsPresent(),
      new PatientEthnicityIsValid(),
      new PatientExists(),
      new PatientFinancialStatusCheckTrue(),
      new PatientFinancialStatusDateIsValid(),
      new PatientGenderIsValid(),
      new PatientHasResponsibleParty(),
      new PatientImmunityIsValid(),
      new PatientIsUnderage(),
      new PatientMedicaidNumberIsValid(),
      new PatientMothersMaidenNameIsValid(),
      new PatientMultipleBirthsValid(),
      new PatientNameIsValid(),
      new PatientNameSuffixIsValid(),
      new PatientNameTypeIsValid(),
      new PatientPhoneIsValid(),
      new PatientPrimaryPhysicianNameIsValid(),
      new PatientProtectionIndicatorIsValid(),
      new PatientRegistryIdIsValid(),
      new PatientRegistryIdIsPresent(),
      new PatientResponsiblePartyIsProperlyFormed(),
      new PatientSsnIsValid(),
      new PatientSubmitterIsValid(),
      new VaccinationAdminCountIsAsExpectedForAge()),
  VACCINATION_RULES(
	  new ObservationDateIsValid(),
	  new ObservationValueTypeIsValid(),
      new VaccinationActionCodeIsValid(),
      new VaccinationAdminCodeCptIsValid(),
      new VaccinationAdminCodeIsPresent(),
      new VaccinationAdminCodeIsUsable(),
      new VaccinationAdminCodeIsValid(),
      new VaccinationAdminDateIsBeforeLotExpirationDate(),
      new VaccinationAdminDateIsValid(),
      new VaccinationAdminDateIsValidForPatientAge(),
      new VaccinationAdministeredAmountIsReasonable(),
	  new VaccinationSourceIsHistoricalButAppearsAdministered(),
      new VaccinationAdministeredAmtIsValid(),
      new VaccinationAdministeredLotNumberIsValid(),
      new VaccinationAdministeredLotNumberIsPresent(),
      new VaccinationAdministeredRequiredFieldsArePresent(),
      new VaccinationAdministeredUnitIsValid(),
      new VaccinationBodyRouteAndSiteAreValid(),
      new VaccinationCodeGroupsMatch(),
      new VaccinationCompletionStatusIsValid(),
      new VaccinationConfidentialityCodeIsValid(),
      new VaccinationCptIsValid(),
      new VaccinationCreationTimeliness(),
      new VaccinationCreationDateIsValid(),
      new VaccinationCvxIsValid(),
      new VaccinationCvxUseIsValid(),
      new VaccinationFinancialEligibilityCodeIsValid(),
      new VaccinationFundingAndEligibilityConflict(),
      new VaccinationFundingSourceCodeIsValid(),
      new VaccinationInformationSourceIsValid(),
      new VaccinationIsAdministeredOrHistorical(),
      new VaccinationIsForeign(),
      new VaccinationIsPresent(),
      new VaccinationMfrIsValid(),
      new VaccinationNdcIsValid(),
      new VaccinationOrdererIsValid(),
      new VaccinationProductIsValid(),
      new VaccinationRefusalReasonIsValid(),
      new VaccinationSourceIsAdministered(),
      new VaccinationUseCptInsteadOfCvx(),
      new VaccinationUseCvx(),
      new VaccinationUseNdc(),
      new VaccinationAdministeredAmtIsValid(),
      new VaccinationVisCvxIsValid(),
      new VaccinationVisDatesAreValid(),
      new VaccinationVisIsPresent(),
      new VaccinationVisIsRecognized()),
  MESSAGE_HEADER_RULES(
      new MessageHeaderCodesAreValid(),
      new MessageHeaderFieldsArePresent(),
      new MessageHeaderDateIsValid(),
      new MessageVersionIsValid(),
      new MessageVersionIs25()),
  NEXT_OF_KIN_RULES(
      new NextOfKinIsPresent(),
      new NextOfKinNameIsValid(),
      new NextOfKinAddressIsSameAsPatientAddress(),
      new NextOfKinAddressIsValid(),
      new NextOfKinPhoneIsValid(),
      new NextOfKinRelationshipIsValidForUnderagedPatient(),
      new NextOfKinNameIsNotSameAsPatient(),
      new NextOfKinGuardianAddressIsValid()
  //@formatter:on
  );

  private final List<ValidationRule> rules;

  ValidationRuleEntityLists(ValidationRule... rulesIn) {
    this.rules = Arrays.asList(rulesIn);
  }
  
	public static Set<Detection> activeDetections(){
		Set<Detection> dets = new HashSet<Detection>();
    	for(ValidationRule rule : ValidationRuleEntityLists.PATIENT_RULES.getRules()){
    		dets.addAll(rule.getRuleDetections());
    	}
    	for(ValidationRule rule : ValidationRuleEntityLists.VACCINATION_RULES.getRules()){
    		dets.addAll(rule.getRuleDetections());
    	}
    	for(ValidationRule rule : ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules()){
    		dets.addAll(rule.getRuleDetections());
    	}
    	return dets;
	}
	
	public static Set<ImplementationDetail> getImplementationDocumentations(){
		Set<ImplementationDetail> dets = new HashSet<ImplementationDetail>();
    	for(ValidationRule rule : ValidationRuleEntityLists.PATIENT_RULES.getRules()){
    		dets.addAll(rule.getImplementationDocumentation());
    	}
    	for(ValidationRule rule : ValidationRuleEntityLists.VACCINATION_RULES.getRules()){
    		dets.addAll(rule.getImplementationDocumentation());
    	}
    	for(ValidationRule rule : ValidationRuleEntityLists.MESSAGE_HEADER_RULES.getRules()){
    		dets.addAll(rule.getImplementationDocumentation());
    	}
    	for(ValidationRule rule : ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules()){
    		dets.addAll(rule.getImplementationDocumentation());
    	}
    	return dets;
	}
	

  public List<ValidationRule> getRules() {
    return new ArrayList<>(this.rules);
    // It needs to be a new list. then if it's modified, the enum's version doesn't change.
  }
}
