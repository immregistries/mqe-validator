package org.immregistries.mqe.validator.engine.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.naming.ConfigurationException;
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
import org.immregistries.mqe.validator.engine.rules.patient.PatientAddressIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientAliasIsPresent;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateCharacteristic;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsReasonable;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientBirthPlaceIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientClassIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientCodesAreValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientCreationDateIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientCreationTimeliness;
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
import org.immregistries.mqe.validator.engine.rules.patient.PatientSsnIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.PatientSubmitterIsValid;
import org.immregistries.mqe.validator.engine.rules.patient.VaccinationAdminCountIsAsExpectedForAge;
import org.immregistries.mqe.validator.engine.rules.vaccination.ObservationDateIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.ObservationValueTypeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationActionCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeCptIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeIsPresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeIsUsable;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminDateIsBeforeLotExpirationDate;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminDateIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdminDateIsValidForPatientAge;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationAdministeredAmountIsReasonable;
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
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCreationDateIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCreationTimeliness;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCvxIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationCvxUseIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationFinancialEligibilityCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationFundingAndEligibilityConflict;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationFundingSourceCodeIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationInformationSourceIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationIsAdministeredOrHistorical;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationIsForeign;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationIsPresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationMfrIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationNdcIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationOrdererIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationProductIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationRefusalReasonIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationSourceIsAdministered;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationSourceIsHistoricalButAppearsAdministered;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationUseCptInsteadOfCvx;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationUseCvx;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationUseNdc;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisCvxIsValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisDatesAreValid;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisIsPresent;
import org.immregistries.mqe.validator.engine.rules.vaccination.VaccinationVisIsRecognized;
import org.immregistries.mqe.vxu.TargetType;
import org.reflections.Reflections;
@SuppressWarnings({"unchecked", "rawtypes"})
public enum ValidationRuleEntityLists {
  INSTANCE;
//  //@formatter:off
//  PATIENT_RULES(
//      new PatientAddressIsValid(),
//      new PatientAliasIsPresent(),
//      new PatientBirthDateIsValid(),
//      new PatientBirthDateIsReasonable(),
//      new PatientBirthDateCharacteristic(),
//      new PatientBirthPlaceIsValid(),
//      new PatientClassIsValid(),
//      new PatientCodesAreValid(),
//      new PatientCreationDateIsValid(),
//      new PatientCreationTimeliness(),
//      new PatientDeathDateIsValid(),
//      new PatientDeathIndicatorIsValid(),
//      new PatientEmailIsPresent(),
//      new PatientEthnicityIsValid(),
//      new PatientExists(),
//      new PatientFinancialStatusCheckTrue(),
//      new PatientFinancialStatusDateIsValid(),
//      new PatientGenderIsValid(),
//      new PatientHasResponsibleParty(),
//      new PatientImmunityIsValid(),
//      new PatientIsUnderage(),
//      new PatientMedicaidNumberIsValid(),
//      new PatientMothersMaidenNameIsValid(),
//      new PatientMultipleBirthsValid(),
//      new PatientNameIsValid(),
//      new PatientNameSuffixIsValid(),
//      new PatientNameTypeIsValid(),
//      new PatientPhoneIsValid(),
//      new PatientPrimaryPhysicianNameIsValid(),
//      new PatientProtectionIndicatorIsValid(),
//      new PatientRegistryIdIsValid(),
//      new PatientRegistryIdIsPresent(),
//      new PatientResponsiblePartyIsProperlyFormed(),
//      new PatientSsnIsValid(),
//      new PatientSubmitterIsValid(),
//      new VaccinationAdminCountIsAsExpectedForAge()),
//  VACCINATION_RULES(
//	  new ObservationDateIsValid(),
//	  new ObservationValueTypeIsValid(),
//      new VaccinationActionCodeIsValid(),
//      new VaccinationAdminCodeCptIsValid(),
//      new VaccinationAdminCodeIsPresent(),
//      new VaccinationAdminCodeIsUsable(),
//      new VaccinationAdminCodeIsValid(),
//      new VaccinationAdminDateIsBeforeLotExpirationDate(),
//      new VaccinationAdminDateIsValid(),
//      new VaccinationAdminDateIsValidForPatientAge(),
//      new VaccinationAdministeredAmountIsReasonable(),
//	  new VaccinationSourceIsHistoricalButAppearsAdministered(),
//      new VaccinationAdministeredAmtIsValid(),
//      new VaccinationAdministeredLotNumberIsValid(),
//      new VaccinationAdministeredLotNumberIsPresent(),
//      new VaccinationAdministeredRequiredFieldsArePresent(),
//      new VaccinationAdministeredUnitIsValid(),
//      new VaccinationBodyRouteAndSiteAreValid(),
//      new VaccinationCodeGroupsMatch(),
//      new VaccinationCompletionStatusIsValid(),
//      new VaccinationConfidentialityCodeIsValid(),
//      new VaccinationCptIsValid(),
//      new VaccinationCreationTimeliness(),
//      new VaccinationCreationDateIsValid(),
//      new VaccinationCvxIsValid(),
//      new VaccinationCvxUseIsValid(),
//      new VaccinationFinancialEligibilityCodeIsValid(),
//      new VaccinationFundingAndEligibilityConflict(),
//      new VaccinationFundingSourceCodeIsValid(),
//      new VaccinationInformationSourceIsValid(),
//      new VaccinationIsAdministeredOrHistorical(),
//      new VaccinationIsForeign(),
//      new VaccinationIsPresent(),
//      new VaccinationMfrIsValid(),
//      new VaccinationNdcIsValid(),
//      new VaccinationOrdererIsValid(),
//      new VaccinationProductIsValid(),
//      new VaccinationRefusalReasonIsValid(),
//      new VaccinationSourceIsAdministered(),
//      new VaccinationUseCptInsteadOfCvx(),
//      new VaccinationUseCvx(),
//      new VaccinationUseNdc(),
//      new VaccinationAdministeredAmtIsValid(),
//      new VaccinationVisCvxIsValid(),
//      new VaccinationVisDatesAreValid(),
//      new VaccinationVisIsPresent(),
//      new VaccinationVisIsRecognized()),
//  MESSAGE_HEADER_RULES(
//      new MessageHeaderCodesAreValid(),
//      new MessageHeaderFieldsArePresent(),
//      new MessageHeaderDateIsValid(),
//      new MessageVersionIsValid(),
//      new MessageVersionIs25()),
//  NEXT_OF_KIN_RULES(
//      new NextOfKinIsPresent(),
//      new NextOfKinNameIsValid(),
//      new NextOfKinAddressIsSameAsPatientAddress(),
//      new NextOfKinAddressIsValid(),
//      new NextOfKinPhoneIsValid(),
//      new NextOfKinRelationshipIsValidForUnderagedPatient(),
//      new NextOfKinNameIsNotSameAsPatient(),
//      new NextOfKinGuardianAddressIsValid()
//  //@formatter:on
//                                       );

  private Map<TargetType, List<ValidationRule>> rules = new HashMap<>();
//
//  ValidationRuleEntityLists(ValidationRule... rulesIn) {
//    this.rules = new HashSet<>(Arrays.asList(rulesIn));
//  }

  ValidationRuleEntityLists() {
      Reflections reflections = new Reflections("org.immregistries.mqe.validator");
      Set<Class<?>> ruleClasses = reflections.getTypesAnnotatedWith(ValidationRuleEntry.class);
      for (Class c : ruleClasses) {
        try {
          if (!c.isAssignableFrom(ValidationRule.class)) {
            throw new RuntimeException("You have a class annotated with ValidateRuleEntry that is not a Validation Rule: " + c);
          }
          ValidationRule rule = (ValidationRule)c.newInstance();
          ValidationRuleEntry annotation = (ValidationRuleEntry) c.getAnnotation(ValidationRuleEntry.class);
          TargetType type = annotation.value();
          List<ValidationRule> ruleList = rules.computeIfAbsent(type, k -> new ArrayList<>());
          ruleList.add(rule);
        } catch (InstantiationException | IllegalAccessException e) {
          throw new RuntimeException("Something really bad happened while instantiating a class annotated with ValidationRuleEntry. Class["+ c+"]", e);
        }
      }
  }

  public Set<ValidationRule> allRules() {
    //This collects all of the values from the lists in each of the type entries for the map.
    //This could have been a lot longer, but this is pretty, and cool, right?
    return rules.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
  }

  public Set<Detection> activeDetections() {
    return null;
//    return this.allRules().stream().flatMap((r) -> r.getRuleDetections().stream().map((d) -> (Detection) d))
//        .collect(Collectors.toSet())
//        ;
  }

  public Set<ImplementationDetail> getImplementationDocumentations() {
    Set<ImplementationDetail> dets = new HashSet<ImplementationDetail>();
    for (ValidationRule rule : allRules()) {
      dets.addAll(rule.getImplementationDocumentation());
    }
    return dets;
  }


  public Set<ValidationRule> getRules() {
    return this.rules;
    // It needs to be a new list. then if it's modified, the enum's version doesn't change.
  }


}
