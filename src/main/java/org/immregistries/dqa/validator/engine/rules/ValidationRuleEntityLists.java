package org.immregistries.dqa.validator.engine.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.rules.header.MessageHeaderCodesAreValid;
import org.immregistries.dqa.validator.engine.rules.header.MessageHeaderDateIsExpectedFormat;
import org.immregistries.dqa.validator.engine.rules.header.MessageHeaderDateIsValid;
import org.immregistries.dqa.validator.engine.rules.header.MessageHeaderFieldsArePresent;
import org.immregistries.dqa.validator.engine.rules.header.MessageVersionIs25;
import org.immregistries.dqa.validator.engine.rules.header.MessageVersionIsValid;
import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinAddressIsSameAsPatientAddress;
import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinAddressIsValid;
import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinIsPresent;
import org.immregistries.dqa.validator.engine.rules.patient.PatientAddressIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientAliasIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientBirthDateIsReasonable;
import org.immregistries.dqa.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientDeathDateIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientDeathIndicatorIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientEthnicityIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientExists;
import org.immregistries.dqa.validator.engine.rules.patient.PatientFinancialStatusCheckTrue;
import org.immregistries.dqa.validator.engine.rules.patient.PatientFinancialStatusDateIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientGenderIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientHasResponsibleParty;
import org.immregistries.dqa.validator.engine.rules.patient.PatientImmunityIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.dqa.validator.engine.rules.patient.PatientMedicaidNumberIsValid;
import org.immregistries.dqa.validator.engine.rules.patient.PatientMiddleNameIsPresent;
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
import org.immregistries.dqa.validator.engine.rules.patient.PatientResponsiblePartyIsProperlyFormed;
import org.immregistries.dqa.validator.engine.rules.patient.PatientSubmitterIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationActionCodeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminAfterBirthDate;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminCodeCptIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminCodeIsUsable;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminCodeIsPresent;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminCodeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminDateIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdminDateIsValidForPatientAge;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdministeredAmtIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdministeredRequiredFieldsArePresent;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationAdministeredUnitIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationBodyRouteAndSiteAreValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCodeGroupsMatch;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCompletionStatusIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationConfidentialityCodeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCptIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCvxIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationCvxUseIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationFinancialEligibilityCodeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationInformationSourceIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsAdministered;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsAdministeredOrHistorical;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsForeign;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsPresent;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationMfrIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationNdcIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationOrdererIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationProductIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationRefusalReasonIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationSystemEntryTimeIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationUseCptInsteadOfCvx;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationUseCvx;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationVisCvxIsValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationVisDatesAreValid;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationVisIsPresent;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationVisIsRecognized;

public enum ValidationRuleEntityLists {
  //@formatter:off
  PATIENT_RULES(
      new PatientAddressIsValid(),
      new PatientAliasIsValid(),
      new PatientBirthDateIsValid(),
      new PatientBirthDateIsReasonable(),
      new PatientDeathDateIsValid(),
      new PatientDeathIndicatorIsValid(),
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
      new VaccinationInformationSourceIsValid(),
      new VaccinationIsAdministered(),
      new VaccinationIsAdministeredOrHistorical(),
      new VaccinationIsForeign(),
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