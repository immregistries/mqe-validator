package org.immregistries.mqe.validator.engine.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderCodesAreValid;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderDateIsExpectedFormat;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderDateIsValid;
import org.immregistries.mqe.validator.engine.rules.header.MessageHeaderFieldsArePresent;
import org.immregistries.mqe.validator.engine.rules.header.MessageVersionIs25;
import org.immregistries.mqe.validator.engine.rules.header.MessageVersionIsValid;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinAddressIsSameAsPatientAddress;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinAddressIsValid;
import org.immregistries.mqe.validator.engine.rules.nextofkin.NextOfKinIsPresent;
import org.immregistries.mqe.validator.engine.rules.patient.PatientAddressIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientAliasIsPresent;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsReasonable;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientDeathDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientDeathIndicatorIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientEmailIsPresent;
import org.immregistries.mqe.validator.engine.rules.patient.PatientEthnicityIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientExists;
import org.immregistries.mqe.validator.engine.rules.patient.PatientFinancialStatusCheckTrue;
import org.immregistries.mqe.validator.engine.rules.patient.PatientFinancialStatusDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientGenderIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientHasResponsibleParty;
import org.immregistries.mqe.validator.engine.rules.patient.PatientImmunityIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.mqe.validator.engine.rules.patient.PatientMedicaidNumberIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientMiddleNameIsPresent;
import org.immregistries.mqe.validator.engine.rules.patient.PatientMiddleNameIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientMothersMaidenNameIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientMultipleBirthsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientNameIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientNameSuffixIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientNameTypeIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientPhoneIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientPrimaryPhysicianNameIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientProtectionIndicatorIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientRegistryIdIsPresent;
import org.immregistries.mqe.validator.engine.rules.patient.PatientRegistryIdIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientResponsiblePartyIsProperlyFormed;
import org.immregistries.mqe.validator.engine.rules.patient.PatientSubmitterIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationActionCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminAfterBirthDate;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeCptIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeIsPresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeIsUsable;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminDateIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminDateIsValidForPatientAge;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdministeredAmtIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdministeredLotNumberIsPresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdministeredLotNumberIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdministeredRequiredFieldsArePresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdministeredUnitIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationBodyRouteAndSiteAreValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCodeGroupsMatch;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCompletionStatusIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationConfidentialityCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCptIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCvxIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCvxUseIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationFinancialEligibilityCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationFundingAndEligibilityConflict;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationFundingSourceCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationInformationSourceIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationIsAdministered;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationIsAdministeredOrHistorical;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationIsForeign;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationIsPresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationMfrIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationNdcIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationOrdererIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationProductIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationRefusalReasonIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationSystemEntryTimeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationUseCptInsteadOfCvx;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationUseCvx;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisCvxIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisDatesAreValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisIsPresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisIsRecognized;

public enum ValidationRuleEntityLists {
  //@formatter:off
  PATIENT_RULES(
      new PatientAddressIsValid(),
      new PatientAliasIsPresent(),
      new PatientBirthDateIsValid(),
      new PatientBirthDateIsReasonable(),
      new PatientDeathDateIsValid(),
      new PatientDeathIndicatorIsValid(),
      new PatientEmailIsPresent(),
      new PatientEthnicityIsValid(),
      new PatientExists(),
      new PatientFinancialStatusCheckTrue(),
      new PatientFinancialStatusDateIsValid(),
      new PatientGenderIsValid(),
      new PatientIsUnderage(),
      new PatientMedicaidNumberIsValid(),
      new PatientMiddleNameIsPresent(),
      new PatientMiddleNameIsValid(),
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
      new PatientSubmitterIsValid(),
      new PatientImmunityIsValid(),
      new PatientHasResponsibleParty(),
      new PatientResponsiblePartyIsProperlyFormed()),
  VACCINATION_RULES(
      new VaccinationIsPresent(),
      new VaccinationActionCodeIsValid(),
      new VaccinationAdminAfterBirthDate(),
      new VaccinationAdminCodeCptIsValid(),
      new VaccinationAdminCodeIsValid(),
      new VaccinationAdminDateIsValid(),
      new VaccinationAdminDateIsValidForPatientAge(),
      new VaccinationAdministeredRequiredFieldsArePresent(),
      new VaccinationAdministeredUnitIsValid(),
      new VaccinationBodyRouteAndSiteAreValid(),
      new VaccinationCodeGroupsMatch(),
      new VaccinationCompletionStatusIsValid(),
      new VaccinationConfidentialityCodeIsValid(),
      new VaccinationCptIsValid(),
      new VaccinationCvxIsValid(),
      new VaccinationNdcIsValid(),
      new VaccinationCvxUseIsValid(),
      new VaccinationFinancialEligibilityCodeIsValid(),
      new VaccinationFundingSourceCodeIsValid(),
      new VaccinationFundingAndEligibilityConflict(),
      new VaccinationInformationSourceIsValid(),
      new VaccinationIsAdministered(),
      new VaccinationIsAdministeredOrHistorical(),
      new VaccinationIsForeign(),
      new VaccinationAdministeredLotNumberIsValid(),
      new VaccinationAdministeredLotNumberIsPresent(),
      new VaccinationMfrIsValid(),
      new VaccinationOrdererIsValid(),
      new VaccinationProductIsValid(),
      new VaccinationRefusalReasonIsValid(),
      new VaccinationSystemEntryTimeIsValid(),
      new VaccinationUseCptInsteadOfCvx(),
      new VaccinationUseCvx(),
      new VaccinationAdministeredAmtIsValid(),
      new VaccinationVisCvxIsValid(),
      new VaccinationVisDatesAreValid(),
      new VaccinationVisIsPresent(),
      new VaccinationAdminCodeIsPresent(),
      new VaccinationAdminCodeIsUsable(),
      new VaccinationVisIsRecognized()),
  MESSAGE_HEADER_RULES(
      new MessageHeaderCodesAreValid(),
      new MessageHeaderFieldsArePresent(),
      new MessageHeaderDateIsValid(),
      new MessageHeaderDateIsExpectedFormat(),
      new MessageVersionIsValid(),
      new MessageVersionIs25()),
  NEXT_OF_KIN_RULES(
      new NextOfKinIsPresent(),
      new NextOfKinAddressIsSameAsPatientAddress(),
      new NextOfKinAddressIsValid()
  //@formatter:on
  );

  private final List<ValidationRule> rules;

  ValidationRuleEntityLists(ValidationRule... rulesIn) {
    this.rules = Arrays.asList(rulesIn);
  }

  public List<ValidationRule> getRules() {
    return new ArrayList<>(this.rules);
    // It needs to be a new list. then if it's modified, the enum's version doesn't change.
  }
}
