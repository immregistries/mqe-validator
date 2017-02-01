package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.immregistries.dqa.hl7util.SeverityLevel;

public enum MessageAttribute {
	  GeneralProcessingException(	  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_PROCESSING,"", SeverityLevel.ERROR)
	, NextOfKinAddressIsDifferentFromPatientAddress(IssueObject.NEXT_OF_KIN, IssueType.DIFFERENT_FROM_PATIENT_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCityIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.WARN)
	, NextOfKinAddressCityIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCityIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCityIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCityIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCityIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountryIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountryIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountryIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountryIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountryIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountyIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountyIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountyIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountyIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressCountyIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressStateIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressStateIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressStateIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressStateIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressStateIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressStreetIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressStreet2IsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET2,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressTypeIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressTypeIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressTypeIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressTypeIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressTypeIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressTypeIsValuedBadAddress(IssueObject.NEXT_OF_KIN, IssueType.VALUED_BAD_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressZipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", SeverityLevel.ACCEPT)
	, NextOfKinAddressZipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", SeverityLevel.ACCEPT)
	, NextOfKinNameIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME,"", SeverityLevel.ACCEPT)
	, NextOfKinNameFirstIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameFirstIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameFirstIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameFirstIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameFirstIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameLastIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameLastIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameLastIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameLastIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ACCEPT)
	, NextOfKinNameLastIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ACCEPT)
	, NextOfKinPhoneNumberIsIncomplete(IssueObject.NEXT_OF_KIN, IssueType.INCOMPLETE, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", SeverityLevel.ACCEPT)
	, NextOfKinPhoneNumberIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", SeverityLevel.ACCEPT)
	, NextOfKinPhoneNumberIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", SeverityLevel.ACCEPT)
	, NextOfKinRelationshipIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, NextOfKinRelationshipIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, NextOfKinRelationshipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, NextOfKinRelationshipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, NextOfKinRelationshipIsNotResponsibleParty(IssueObject.NEXT_OF_KIN, IssueType.NOT_RESPONSIBLE_PARTY, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, NextOfKinRelationshipIsUnexpected(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, NextOfKinRelationshipIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, NextOfKinSsnIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_SSN,"", SeverityLevel.ACCEPT)
	, ObservationValueTypeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ACCEPT)
	, ObservationValueTypeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ACCEPT)
	, ObservationValueTypeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ACCEPT)
	, ObservationValueTypeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ACCEPT)
	, ObservationValueTypeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ACCEPT)
	, ObservationIdentifierCodeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ACCEPT)
	, ObservationIdentifierCodeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ACCEPT)
	, ObservationIdentifierCodeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ACCEPT)
	, ObservationIdentifierCodeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ACCEPT)
	, ObservationIdentifierCodeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ACCEPT)
	, ObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE,"", SeverityLevel.ACCEPT)
	, ObservationDateTimeOfObservationIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", SeverityLevel.ACCEPT)
	, ObservationDateTimeOfObservationIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", SeverityLevel.ACCEPT)
	, PatientObjectIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.NONE,"", SeverityLevel.ACCEPT)
	, PatientAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS,"", SeverityLevel.ACCEPT)
	, PatientAddressCityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, PatientAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, PatientAddressCityIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, PatientAddressCityIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, PatientAddressCityIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, PatientAddressCityIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountryIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountryIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountryIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountryIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountryIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountyIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountyIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountyIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, PatientAddressCountyIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ACCEPT)
	, PatientAddressStateIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, PatientAddressStateIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, PatientAddressStateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, PatientAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, PatientAddressStateIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, PatientAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET,"", SeverityLevel.ACCEPT)
	, PatientAddressStreet2IsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET2,"", SeverityLevel.ACCEPT)
	, PatientAddressTypeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, PatientAddressTypeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, PatientAddressTypeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, PatientAddressTypeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, PatientAddressTypeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, PatientAddressTypeIsValuedBadAddress(IssueObject.PATIENT, IssueType.VALUED_BAD_ADDRESS, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ACCEPT)
	, PatientAddressZipIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_ZIP,"", SeverityLevel.ACCEPT)
	, PatientAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_ZIP,"", SeverityLevel.ACCEPT)
	, PatientAliasIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ALIAS,"", SeverityLevel.ACCEPT)
	, PatientBirthDateIsAfterSubmission(IssueObject.PATIENT, IssueType.AFTER_SUBMISSION, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ACCEPT)
	, PatientBirthDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ACCEPT)
	, PatientBirthDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ACCEPT)
	, PatientBirthDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ACCEPT)
	, PatientBirthDateIsUnderage(IssueObject.PATIENT, IssueType.UNDERAGE, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ACCEPT)
	, PatientBirthDateIsVeryLongAgo(IssueObject.PATIENT, IssueType.VERY_LONG_AGO, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ACCEPT)
	, PatientBirthIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientBirthIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientBirthOrderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_ORDER,"", SeverityLevel.ACCEPT)
	, PatientBirthOrderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_ORDER,"", SeverityLevel.ACCEPT)
	, PatientBirthOrderIsMissingAndMultipleBirthIndicated(IssueObject.PATIENT, IssueType.MISSING_AND_MULTIPLE_BIRTH_INDICATED, IssueField.PATIENT_BIRTH_ORDER,"", SeverityLevel.ACCEPT)
	, PatientBirthPlaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ACCEPT)
	, PatientBirthPlaceIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ACCEPT)
	, PatientBirthPlaceIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ACCEPT)
	, PatientBirthPlaceIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ACCEPT)
	, PatientBirthPlaceIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ACCEPT)
	, PatientBirthRegistryIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", SeverityLevel.ACCEPT)
	, PatientBirthRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", SeverityLevel.ACCEPT)
	, PatientClassIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_CLASS,"", SeverityLevel.ACCEPT)
	, PatientClassIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_CLASS,"", SeverityLevel.ACCEPT)
	, PatientClassIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_CLASS,"", SeverityLevel.ACCEPT)
	, PatientClassIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_CLASS,"", SeverityLevel.ACCEPT)
	, PatientClassIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_CLASS,"", SeverityLevel.ACCEPT)
	, PatientDeathDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ACCEPT)
	, PatientDeathDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ACCEPT)
	, PatientDeathDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ACCEPT)
	, PatientDeathDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ACCEPT)
	, PatientDeathIndicatorIsInconsistent(IssueObject.PATIENT, IssueType.INCONSISTENT, IssueField.PATIENT_DEATH_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientDeathIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientEthnicityIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ACCEPT)
	, PatientEthnicityIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ACCEPT)
	, PatientEthnicityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ACCEPT)
	, PatientEthnicityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ACCEPT)
	, PatientEthnicityIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ACCEPT)
	, PatientGenderIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_GENDER,"", SeverityLevel.ACCEPT)
	, PatientGenderIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_GENDER,"", SeverityLevel.ACCEPT)
	, PatientGenderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_GENDER,"", SeverityLevel.ACCEPT)
	, PatientGenderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GENDER,"", SeverityLevel.ACCEPT)
	, PatientGenderIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_GENDER,"", SeverityLevel.ACCEPT)
	, PatientGuardianAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS,"", SeverityLevel.ACCEPT)
	, PatientGuardianAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_CITY,"", SeverityLevel.ACCEPT)
	, PatientGuardianAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STATE,"", SeverityLevel.ACCEPT)
	, PatientGuardianAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STREET,"", SeverityLevel.ACCEPT)
	, PatientGuardianAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_ZIP,"", SeverityLevel.ACCEPT)
	, PatientGuardianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME,"", SeverityLevel.ACCEPT)
	, PatientGuardianNameIsSameAsUnderagePatient(IssueObject.PATIENT, IssueType.SAME_AS_UNDERAGE_PATIENT, IssueField.PATIENT_GUARDIAN_NAME,"", SeverityLevel.ACCEPT)
	, PatientGuardianNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_GUARDIAN_NAME,"", SeverityLevel.ACCEPT)
	, PatientGuardianNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientGuardianNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_LAST,"", SeverityLevel.ACCEPT)
	, PatientGuardianResponsiblePartyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY,"", SeverityLevel.ACCEPT)
	, PatientGuardianPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_PHONE,"", SeverityLevel.ACCEPT)
	, PatientGuardianRelationshipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RELATIONSHIP,"", SeverityLevel.ACCEPT)
	, PatientImmunityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientImmunityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientImmunityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientImmunityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientImmunityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientImmunizationRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientImmunizationRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientImmunizationRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientImmunizationRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientImmunizationRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientMedicaidNumberIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MEDICAID_NUMBER,"", SeverityLevel.WARN)
	, PatientMedicaidNumberIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MEDICAID_NUMBER,"", SeverityLevel.ACCEPT)
	, PatientMiddleNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ACCEPT)
	, PatientMiddleNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.WARN)
	, PatientMiddleNameMayBeInitial(IssueObject.PATIENT, IssueType.MAY_BE_AN_INITIAL, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ACCEPT)
	, PatientMiddleNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ACCEPT)
	, PatientMiddleNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ACCEPT)
	, PatientMiddleNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ACCEPT)
	, PatientMiddleNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ACCEPT)
	, PatientMothersMaidenNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientMotherSMaidenNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientMotherSMaidenNameHasInvalidPrefixes(IssueObject.PATIENT, IssueType.INVALID_PREFIXES, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientMotherSMaidenNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientMotherSMaidenNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientMotherSMaidenNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientMotherSMaidenNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientMotherSMaidenNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT)
	, PatientNameMayBeTemporaryNewbornName(IssueObject.PATIENT, IssueType.MAY_BE_TEMPORARY_NEWBORN_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ACCEPT)
	, PatientNameMayBeTestName(IssueObject.PATIENT, IssueType.MAY_BE_TEST_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ACCEPT)
	, PatientNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ACCEPT)
	, PatientNameIsAKnownTestName(IssueObject.PATIENT, IssueType.KNOWN_TEST_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ACCEPT)
	, PatientNameFirstIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientNameFirstIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientNameFirstIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientNameFirstIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientNameFirstIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientNameFirstMayIncludeMiddleInitial(IssueObject.PATIENT, IssueType.MAY_INCLUDE_MIDDLE_INITIAL, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ACCEPT)
	, PatientNameLastIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ACCEPT)
	, PatientNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ACCEPT)
	, PatientNameLastIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ACCEPT)
	, PatientNameLastIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ACCEPT)
	, PatientNameLastIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ACCEPT)
	, PatientNameLastIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ACCEPT)
	, PatientNameTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientNameTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientNameTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientNameTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientNameTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientNameTypeCodeIsNotValuedLegal(IssueObject.PATIENT, IssueType.NOT_VALUED_LEGAL, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneIsIncomplete(IssueObject.PATIENT, IssueType.INCOMPLETE, IssueField.PATIENT_PHONE,"", SeverityLevel.ACCEPT)
	, PatientPhoneIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE,"", SeverityLevel.ACCEPT)
	, PatientPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelUseCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelUseCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelUseCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelUseCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelUseCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelEquipCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelEquipCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelEquipCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelEquipCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ACCEPT)
	, PatientPhoneTelEquipCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ACCEPT)
	, PatientPrimaryFacilityIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryFacilityIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryFacilityIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryFacilityIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryFacilityIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryFacilityNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_NAME,"", SeverityLevel.ACCEPT)
	, PatientPrimaryLanguageIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ACCEPT)
	, PatientPrimaryLanguageIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ACCEPT)
	, PatientPrimaryLanguageIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ACCEPT)
	, PatientPrimaryLanguageIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ACCEPT)
	, PatientPrimaryLanguageIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ACCEPT)
	, PatientPrimaryPhysicianIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryPhysicianIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryPhysicianIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryPhysicianIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryPhysicianIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ACCEPT)
	, PatientPrimaryPhysicianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_NAME,"", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsValuedAsNo(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "no", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsValuedAsYes(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "yes", SeverityLevel.ACCEPT)
	, PatientPublicityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientPublicityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientPublicityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientPublicityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientPublicityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ACCEPT)
	, PatientRaceIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_RACE,"", SeverityLevel.ACCEPT)
	, PatientRaceIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_RACE,"", SeverityLevel.ACCEPT)
	, PatientRaceIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_RACE,"", SeverityLevel.ACCEPT)
	, PatientRaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_RACE,"", SeverityLevel.ACCEPT)
	, PatientRaceIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_RACE,"", SeverityLevel.ACCEPT)
	, PatientRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_ID,"", SeverityLevel.ACCEPT)
	, PatientRegistryIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_ID,"", SeverityLevel.ACCEPT)
	, PatientRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ACCEPT)
	, PatientSsnIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SSN,"", SeverityLevel.ACCEPT)
	, PatientSsnIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SSN,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdAuthorityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_AUTHORITY,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ACCEPT)
	, PatientSystemCreationDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ACCEPT)
	, PatientSystemCreationDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ACCEPT)
	, PatientSystemCreationDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ACCEPT)
	, PatientSystemCreationDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ACCEPT)
	, PatientVfcEffectiveDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ACCEPT)
	, PatientVfcEffectiveDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ACCEPT)
	, PatientVfcEffectiveDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ACCEPT)
	, PatientVfcEffectiveDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ACCEPT)
	, PatientVfcStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ACCEPT)
	, PatientVfcStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ACCEPT)
	, PatientVfcStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ACCEPT)
	, PatientVfcStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ACCEPT)
	, PatientVfcStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ACCEPT)
	, PatientWicIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_WIC_ID,"", SeverityLevel.ACCEPT)
	, PatientWicIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_WIC_ID,"", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsAdd(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsAddOrUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add or update", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsDelete(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "delete", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "update", SeverityLevel.ACCEPT)
	, VaccinationAdministeredCodeIsForiegn(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ACCEPT)
	, VaccinationHistoricalCodeIsForeign(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.WARN)
	, VaccinationAdminCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsNotSpecific(IssueObject.VACCINATION, IssueType.NOT_SPECIFIC, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsNotVaccine(IssueObject.VACCINATION, IssueType.NOT_VACCINE, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "not administered", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "unknown", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(IssueObject.VACCINATION, IssueType.MAY_BE_PREVIOUSLY_REPORTED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeTableIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeTableIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsAfterLotExpirationDate(IssueObject.VACCINATION, IssueType.AFTER_LOT_EXPIRATION, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsAfterMessageSubmitted(IssueObject.VACCINATION, IssueType.AFTER_MESSAGE_SUBMITTED, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsAfterPatientDeathDate(IssueObject.VACCINATION, IssueType.AFTER_PATIENT_DEATH_DATE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsAfterSystemEntryDate(IssueObject.VACCINATION, IssueType.AFTER_SYSTEM_ENTRY_DATE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsBeforeBirth(IssueObject.VACCINATION, IssueType.BEFORE_BIRTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsOn15ThDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIFTEENTH_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsOnFirstDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIRST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsOnLastDayOfMonth(IssueObject.VACCINATION, IssueType.ON_LAST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateIsReportedLate(IssueObject.VACCINATION, IssueType.REPORTED_LATE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateEndIsDifferentFromStartDate(IssueObject.VACCINATION, IssueType.DIFF_FROM_START, IssueField.VACCINATION_ADMIN_DATE_END,"", SeverityLevel.ACCEPT)
	, VaccinationAdminDateEndIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE_END,"", SeverityLevel.ACCEPT)
	, VaccinationAdministeredAmountIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", SeverityLevel.ACCEPT)
	, VaccinationAdministeredAmountIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", SeverityLevel.ACCEPT)
	, VaccinationAdministeredAmountIsValuedAsZero(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "zero", SeverityLevel.ACCEPT)
	, VaccinationAdministeredAmountIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "unknown", SeverityLevel.ACCEPT)
	, VaccinationAdministeredUnitIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ACCEPT)
	, VaccinationAdministeredUnitIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ACCEPT)
	, VaccinationAdministeredUnitIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ACCEPT)
	, VaccinationAdministeredUnitIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ACCEPT)
	, VaccinationAdministeredUnitIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ACCEPT)
	, VaccinationBodyRouteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ACCEPT)
	, VaccinationBodyRouteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ACCEPT)
	, VaccinationBodyRouteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ACCEPT)
	, VaccinationBodyRouteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ACCEPT)
	, VaccinationBodyRouteIsInvalidForBodySiteIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_BODY_SITE, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ACCEPT)
	, VaccinationBodyRouteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ACCEPT)
	, VaccinationBodyRouteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ACCEPT)
	, VaccinationBodySiteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ACCEPT)
	, VaccinationBodySiteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ACCEPT)
	, VaccinationBodySiteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ACCEPT)
	, VaccinationBodySiteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ACCEPT)
	, VaccinationBodySiteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ACCEPT)
	, VaccinationBodySiteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsCompleted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "completed", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "not administered", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsPartiallyAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "partially administered", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsRefused(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "refused", SeverityLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsValuedAsRestricted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "restricted", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationCvxCodeAndCptCodeAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_CVX_CODE_AND_CPT_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_NAME,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsValuedAsPublic(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "public", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsValuedAsPrivate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "private", SeverityLevel.ACCEPT)
	, VaccinationFillerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationFillerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationFillerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationFillerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationFillerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationFinancialEligibilityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationFinancialEligibilityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationFinancialEligibilityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationFinancialEligibilityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationFinancialEligibilityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationGivenByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ACCEPT)
	, VaccinationGivenByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ACCEPT)
	, VaccinationGivenByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ACCEPT)
	, VaccinationGivenByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ACCEPT)
	, VaccinationGivenByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ACCEPT)
	, VaccinationIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID,"", SeverityLevel.ACCEPT)
	, VaccinationIdOfReceiverIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_RECEIVER,"", SeverityLevel.ACCEPT)
	, VaccinationIdOfReceiverIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_RECEIVER,"", SeverityLevel.ACCEPT)
	, VaccinationIdOfSenderIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_SENDER,"", SeverityLevel.ACCEPT)
	, VaccinationIdOfSenderIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_SENDER,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsAdministeredButAppearsToHistorical(IssueObject.VACCINATION, IssueType.ADMIN_BUT_APPEARS_HISTORICAL, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(IssueObject.VACCINATION, IssueType.HISTORICAL_BUT_APPEARS_ADMIN, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsValuedAsAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "administered", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsValuedAsHistorical(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "historical", SeverityLevel.ACCEPT)
	, VaccinationVisIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS,"", SeverityLevel.ACCEPT)
	, VaccinationVisIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS,"", SeverityLevel.ACCEPT)
	, VaccinationVisIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS,"", SeverityLevel.ACCEPT)
	, VaccinationVisCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationVisCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationVisCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationVisCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationVisCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationVisDocumentTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationVisDocumentTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationVisDocumentTypeIsIncorrect(IssueObject.VACCINATION, IssueType.INCORRECT, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationVisDocumentTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationVisDocumentTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationVisDocumentTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationVisDocumentTypeIsOutOfDate(IssueObject.VACCINATION, IssueType.OUT_OF_DATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPublishedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPublishedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPublishedDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPublishedDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPresentedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPresentedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPresentedDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPresentedDateIsBeforePublishedDate(IssueObject.VACCINATION, IssueType.BEFORE_PUBLISHED_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationVisPresentedDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationLotExpirationDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationLotExpirationDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", SeverityLevel.ACCEPT)
	, VaccinationLotNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationLotNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationManufacturerCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationManufacturerCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationManufacturerCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationManufacturerCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationManufacturerCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationManufacturerCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationManufacturerCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationOrderControlCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationOrderControlCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationOrderControlCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationOrderControlCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationOrderControlCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationOrderFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationOrderFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationOrderFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationOrderFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationOrderFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ACCEPT)
	, VaccinationOrderFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_NAME,"", SeverityLevel.ACCEPT)
	, VaccinationOrderedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationOrderedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationOrderedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationOrderedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationOrderedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationPlacerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationPlacerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationPlacerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationPlacerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationPlacerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ACCEPT)
	, VaccinationProductIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ACCEPT)
	, VaccinationProductIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ACCEPT)
	, VaccinationProductIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ACCEPT)
	, VaccinationProductIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ACCEPT)
	, VaccinationProductIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ACCEPT)
	, VaccinationProductIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ACCEPT)
	, VaccinationRecordedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationRecordedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationRecordedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationRecordedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationRecordedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ACCEPT)
	, VaccinationRefusalReasonConflictsCompletionStatus(IssueObject.VACCINATION, IssueType.CONFLICTS_WITH_COMPLETION_STATUS, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ACCEPT)
	, VaccinationRefusalReasonIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ACCEPT)
	, VaccinationRefusalReasonIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ACCEPT)
	, VaccinationRefusalReasonIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ACCEPT)
	, VaccinationRefusalReasonIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ACCEPT)
	, VaccinationRefusalReasonIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ACCEPT)
	, VaccinationSystemEntryTimeIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", SeverityLevel.ACCEPT)
	, VaccinationSystemEntryTimeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", SeverityLevel.ACCEPT)
	, VaccinationSystemEntryTimeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", SeverityLevel.ACCEPT)
	, VaccinationTradeNameIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ACCEPT)
	, VaccinationTradeNameIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ACCEPT)
	, VaccinationTradeNameIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ACCEPT)
	, VaccinationTradeNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ACCEPT)
	, VaccinationTradeNameIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ACCEPT)
	, VaccinationTradeNameAndVaccineAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_VACCINE,"", SeverityLevel.ACCEPT)
	, VaccinationTradeNameAndManufacturerAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_MANUFACTURER,"", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsValuedAsValid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "valid", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsValuedAsInvalid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "invalid", SeverityLevel.ACCEPT)
	, UnknownValidationIssue(IssueObject.GENERAL, IssueType.MISSING, IssueField.GENERAL_PROCESSING,"", SeverityLevel.ACCEPT)
;

	  private static final Map<IssueField, Map<IssueType, MessageAttribute>> fieldIssueMaps = new HashMap<IssueField, Map<IssueType, MessageAttribute>>();
	  
	  private static final Map<IssueField, List<MessageAttribute>> objectIssueListMap = new HashMap<IssueField, List<MessageAttribute>>();
	  
	  private static final Map<String, MessageAttribute> displayTextMap = new HashMap<String, MessageAttribute>();
	  static {
		  for (MessageAttribute issue : MessageAttribute.values()) {
			  Map<IssueType, MessageAttribute> map = fieldIssueMaps.get(issue.getTargetField());
			  if (map == null) {
				  map = new HashMap<IssueType, MessageAttribute>();
				  fieldIssueMaps.put(issue.getTargetField(), map);
			  }
			  
			  map.put(issue.getIssueType(), issue);
			  
			  List<MessageAttribute> objectIssues = objectIssueListMap.get(issue.getTargetObject());
			  if (objectIssues == null) {
				  objectIssues = new ArrayList<MessageAttribute>();
			  }
			  objectIssues.add(issue);
			  
			  displayTextMap.put(issue.getDisplayText() + issue.getIssueType().getText(), issue);
		  }
	  }
	  /**
	   * This method doesn't have a purpose yet. 
	   * I was just thinking...  using this I could create a list of issues to check
	   * for any given object.  But I think the list is too exhaustive. 
	   * Maybe I should put it in the map based on an "eligible" switch
	   * or something. 
	   * @param object
	   * @return
	   */
	  public static List<MessageAttribute> getIssuesFor(IssueObject object) {
		  return objectIssueListMap.get(object);
	  }
	  
	  /**
	   * This replaces the whole PotentialIssues.java class
	   * and all the mapping that was done there. 
	   * @param field
	   * @param type
	   * @return
	   */
	  public static MessageAttribute get(IssueField field, IssueType type) {
		  Map<IssueType, MessageAttribute> fieldIssues = fieldIssueMaps.get(field);
		  if (fieldIssues != null) {
			  return fieldIssues.get(type);
		  }
		  return MessageAttribute.UnknownValidationIssue;
	  }
	  
	    public static final String CHANGE_PRIORITY_BLOCKED = "Blocked";
	    public static final String CHANGE_PRIORITY_CAN = "Can";
	    public static final String CHANGE_PRIORITY_MAY = "May";
	    public static final String CHANGE_PRIORITY_MUST = "Must";
	    public static final String CHANGE_PRIORITY_SHOULD = "Should";

	    private String fieldValue = "";
	    private IssueType issueType;
	    private IssueObject targetObject;
	    private IssueField fieldRef;
	    private SeverityLevel defaultAction;
	    
    private MessageAttribute(IssueObject entity, IssueType type, IssueField fieldRef, String valuation, SeverityLevel ia) {
    	this.fieldValue = valuation;
    	this.targetObject = entity;
    	this.issueType = type;
    	this.fieldRef = fieldRef;
    	this.defaultAction = ia;
    }
	    

  public String getDisplayText()
  {
    StringBuilder displayText = new StringBuilder();

    displayText.append(targetObject.getDescription() + " " + fieldRef.getFieldDescription() + " " + issueType);
    if (fieldValue != null && !fieldValue.equals(""))
    {
      displayText.append(" " + fieldValue);
    }
    return displayText.toString();
  }

  public String getFieldValue()
  {
    return fieldValue;
  }

  public IssueType getIssueType()
  {
    return issueType;
  }
  

  public IssueType getIssueTypeEnum()
  {
    return issueType;
  }

  public IssueField getTargetField()
  {
    return fieldRef;
  }

  public IssueObject getTargetObject()
  {
    return targetObject;
  }

  public ValidationIssue build(String value) {
	  	ValidationIssue found = build();
	  	found.setCodeReceived(value);
	  	found.setHl7Reference(this.getTargetField().getHl7Field());
		return found;
  }
  
  public ValidationIssue build() {
		ValidationIssue found = new ValidationIssue();
		found.setMessageAttribute(this);
		//This needs to be equipped to be naunced. Need to make an option to override this in a profile. 
		found.setSeverityLevel(this.getDefaultAction());
		return found;
  }
  
  public static ValidationIssue buildIssue(IssueField field, IssueType type) {
	  MessageAttribute issue = get(field, type);
	  return issue.build();
  }
  
  public static ValidationIssue buildIssue(IssueField field, IssueType type, String value) {
	  MessageAttribute issue = get(field, type);
	  return issue.build(value);
  }

  public static MessageAttribute getPotentialIssueByDisplayText(String num, IssueType missing) {

		return getPotentialIssueByDisplayText(num + missing.getText());
	}
  
  public static MessageAttribute getPotentialIssueByDisplayText(String txt) {
		MessageAttribute issue = displayTextMap.get(txt);
		if (issue == null) {
			return UnknownValidationIssue;
		} 
		return issue;
	}

public SeverityLevel getDefaultAction() {
	return defaultAction;
}

}
