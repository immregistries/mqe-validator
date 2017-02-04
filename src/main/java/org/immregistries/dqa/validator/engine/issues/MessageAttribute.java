package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.immregistries.dqa.hl7util.SeverityLevel;

public enum MessageAttribute {
	GeneralAuthorizationException(IssueObject.GENERAL, IssueType.EXCEPTION, IssueField.AUTHORIZATION, "", SeverityLevel.ACCEPT, "207", "DQA0002", ""),
	GeneralConfigurationException(IssueObject.GENERAL, IssueType.EXCEPTION, IssueField.CONFIGURATION, "", SeverityLevel.ACCEPT, "207", "DQA0003", ""),
	GeneralParseException(IssueObject.GENERAL, IssueType.EXCEPTION, IssueField.PARSE, "", SeverityLevel.ACCEPT, "207", "DQA0004", ""),
	GeneralProcessingException(IssueObject.GENERAL, IssueType.EXCEPTION, IssueField.PROCESSING, "", SeverityLevel.ACCEPT, "207", "DQA0005", ""),

	NextOfKinAddressIsDifferentFromPatientAddress(IssueObject.NEXT_OF_KIN, IssueType.DIFFERENT_FROM_PATIENT_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS, "", SeverityLevel.ACCEPT, "102", "DQA0056", "NK1-4"),
	NextOfKinAddressIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS, "", SeverityLevel.ACCEPT, "101", "DQA0057", "NK1-4"),
	NextOfKinAddressCityIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_CITY, "", SeverityLevel.WARN, "102", "DQA0058", "NK1-4.3"),
	NextOfKinAddressCityIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", "DQA0059", "NK1-4.3"),
	NextOfKinAddressCountryIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", "DQA0060", "NK1-4.6"),
	NextOfKinAddressCountryIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.INFO, "102", "DQA0061", "NK1-4.6"),
	NextOfKinAddressCountryIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", "DQA0062", "NK1-4.6"),
	NextOfKinAddressCountryIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.ACCEPT, "101", "DQA0063", "NK1-4.6"),
	NextOfKinAddressCountryIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "103", "DQA0064", "NK1-4.6"),
	NextOfKinAddressCountyIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", "DQA0065", "NK1-4.9"),
	NextOfKinAddressCountyIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.INFO, "102", "DQA0066", "NK1-4.9"),
	NextOfKinAddressCountyIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", "DQA0067", "NK1-4.9"),
	NextOfKinAddressCountyIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "101", "DQA0068", "NK1-4.9"),
	NextOfKinAddressCountyIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "103", "DQA0069", "NK1-4.9"),
	NextOfKinAddressStateIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, IssueField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "102", "DQA0070", "NK1-4.4"),
	NextOfKinAddressStateIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.INFO, "102", "DQA0071", "NK1-4.4"),
	NextOfKinAddressStateIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "102", "DQA0072", "NK1-4.4"),
	NextOfKinAddressStateIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", "DQA0073", "NK1-4.4"),
	NextOfKinAddressStateIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "103", "DQA0074", "NK1-4.4"),
	NextOfKinAddressStreetIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", "DQA0075", "NK1-4.1"),
	NextOfKinAddressStreet2IsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET2, "", SeverityLevel.ACCEPT, "101", "DQA0076", "NK1-4.2"),
	NextOfKinAddressTypeIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", "DQA0395", "NK1-4.7"),
	NextOfKinAddressTypeIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", "DQA0396", "NK1-4.7"),
	NextOfKinAddressTypeIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", "DQA0397", "NK1-4.7"),
	NextOfKinAddressTypeIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "101", "DQA0398", "NK1-4.7"),
	NextOfKinAddressTypeIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "103", "DQA0399", "NK1-4.7"),
	NextOfKinAddressTypeIsValuedBadAddress(IssueObject.NEXT_OF_KIN, IssueType.VALUED_BAD_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", "DQA0522", "NK1-4.7"),
	NextOfKinAddressZipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_ZIP, "", SeverityLevel.WARN, "102", "DQA0077", "NK1-4.5"),
	NextOfKinAddressZipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", "DQA0078", "NK1-4.5"),
	NextOfKinNameIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME, "", SeverityLevel.WARN, "101", "DQA0079", "NK1-2"),
	NextOfKinNameFirstIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_FIRST, "", SeverityLevel.WARN, "101", "DQA0080", "NK1-2.1"),
	NextOfKinNameLastIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_LAST, "", SeverityLevel.WARN, "101", "DQA0081", "NK1-2.2"),
	NextOfKinPhoneNumberIsIncomplete(IssueObject.NEXT_OF_KIN, IssueType.INCOMPLETE, IssueField.NEXT_OF_KIN_PHONE_NUMBER, "", SeverityLevel.WARN, "102", "DQA0082", "NK1-5"),
	NextOfKinPhoneNumberIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_PHONE_NUMBER, "", SeverityLevel.WARN, "102", "DQA0083", "NK1-5"),
	NextOfKinPhoneNumberIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_PHONE_NUMBER, "", SeverityLevel.ACCEPT, "101", "DQA0084", "NK1-5"),
	NextOfKinRelationshipIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, IssueField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", "DQA0085", "NK1-3"),
	NextOfKinRelationshipIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.INFO, "102", "DQA0086", "NK1-3"),
	NextOfKinRelationshipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", "DQA0087", "NK1-3"),
	NextOfKinRelationshipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "101", "DQA0088", "NK1-3"),
	NextOfKinRelationshipIsNotResponsibleParty(IssueObject.NEXT_OF_KIN, IssueType.NOT_RESPONSIBLE_PARTY, IssueField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.ACCEPT, "102", "DQA0089", "NK1-3"),
	NextOfKinRelationshipIsUnexpected(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTED, IssueField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", "DQA0485", "NK1-3"),
	NextOfKinRelationshipIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "103", "DQA0090", "NK1-3"),
	NextOfKinSsnIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_SSN, "", SeverityLevel.ACCEPT, "101", "DQA0091", "NK1-33"),
	ObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE,"", SeverityLevel.WARN, "102", "DQA999", "OBX"),
	ObservationValueTypeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATED, IssueField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "102", "DQA0470", "OBX-2"),
	ObservationValueTypeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.INFO, "102", "DQA0471", "OBX-2"),
	ObservationValueTypeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "102", "DQA0472", "OBX-2"),
	ObservationValueTypeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "101", "DQA0473", "OBX-2"),
	ObservationValueTypeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "103", "DQA0474", "OBX-2"),
	ObservationObservationIdentifierCodeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATED, IssueField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "102", "DQA0475", "OBX-3"),
	ObservationObservationIdentifierCodeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.INFO, "102", "DQA0476", "OBX-3"),
	ObservationObservationIdentifierCodeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "102", "DQA0477", "OBX-3"),
	ObservationObservationIdentifierCodeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "101", "DQA0478", "OBX-3"),
	ObservationObservationIdentifierCodeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "103", "DQA0479", "OBX-3"),
	ObservationObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE, "", SeverityLevel.WARN, "101", "DQA0480", "OBX-5"),
	ObservationDateTimeOfObservationIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION, "", SeverityLevel.WARN, "101", "DQA0481", "OBX-14"),
	ObservationDateTimeOfObservationIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION, "", SeverityLevel.WARN, "102", "DQA0482", "OBX-14"),
	PatientObjectIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.NONE,"", SeverityLevel.ACCEPT, "101", "DQA0092", "PID-11"),
	PatientAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS, "", SeverityLevel.ACCEPT, "101", "DQA0092", "PID-11"),
	PatientAddressCityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "102", "DQA0093", "PID-11.3"),
	PatientAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", "DQA0094", "PID-11.3"),
	PatientAddressCountryIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", "DQA0095", "PID-11.6"),
	PatientAddressCountryIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.INFO, "102", "DQA0096", "PID-11.6"),
	PatientAddressCountryIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", "DQA0097", "PID-11.6"),
	PatientAddressCountryIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.ACCEPT, "101", "DQA0098", "PID-11.6"),
	PatientAddressCountryIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "103", "DQA0099", "PID-11.6"),
	PatientAddressCountyIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", "DQA0100", "PID-11.9"),
	PatientAddressCountyIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.INFO, "102", "DQA0101", "PID-11.9"),
	PatientAddressCountyIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", "DQA0102", "PID-11.9"),
	PatientAddressCountyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "101", "DQA0103", "PID-11.9"),
	PatientAddressCountyIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "103", "DQA0104", "PID-11.9"),
	PatientAddressStateIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_ADDRESS_STATE, "", SeverityLevel.WARN, "102", "DQA0105", "PID-11.4"),
	PatientAddressStateIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_STATE, "", SeverityLevel.INFO, "102", "DQA0106", "PID-11.4"),
	PatientAddressStateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "102", "DQA0107", "PID-11.4"),
	PatientAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", "DQA0108", "PID-11.4"),
	PatientAddressStateIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "103", "DQA0109", "PID-11.4"),
	PatientAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", "DQA0110", "PID-11.1"),
	PatientAddressStreet2IsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET2, "", SeverityLevel.ACCEPT, "101", "DQA0111", "PID-11.2"),
	PatientAddressTypeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "101", "DQA0451", "PID-11.7"),
	PatientAddressTypeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", "DQA0517", "PID-11.7"),
	PatientAddressTypeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", "DQA0518", "PID-11.7"),
	PatientAddressTypeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "102", "DQA0519", "PID-11.7"),
	PatientAddressTypeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.WARN, "103", "DQA0520", "PID-11.7"),
	PatientAddressTypeIsValuedBadAddress(IssueObject.PATIENT, IssueType.VALUED_BAD_ADDRESS, IssueField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", "DQA0521", "PID-11.7"),
	PatientAddressZipIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_ZIP, "", SeverityLevel.WARN, "102", "DQA0112", "PID-11.5"),
	PatientAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", "DQA0113", "PID-11.5"),
	PatientAliasIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ALIAS, "", SeverityLevel.ACCEPT, "101", "DQA0114", "PID-5"),
	PatientBirthDateIsAfterSubmission(IssueObject.PATIENT, IssueType.AFTER_SUBMISSION, IssueField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", "DQA0115", "PID-7"),
	PatientBirthDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", "DQA0116", "PID-7"),
	PatientBirthDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", "DQA0117", "PID-7"),
	PatientBirthDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "101", "DQA0118", "PID-7"),
	PatientBirthDateIsUnderage(IssueObject.PATIENT, IssueType.UNDERAGE, IssueField.PATIENT_BIRTH_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0119", "PID-7"),
	PatientBirthDateIsVeryLongAgo(IssueObject.PATIENT, IssueType.VERY_LONG_AGO, IssueField.PATIENT_BIRTH_DATE, "", SeverityLevel.WARN, "102", "DQA0120", "PID-7"),
	PatientBirthIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_INDICATOR, "", SeverityLevel.WARN, "102", "DQA0121", "PID-24"),
	PatientBirthIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_INDICATOR, "", SeverityLevel.ACCEPT, "101", "DQA0122", "PID-24"),
	PatientBirthOrderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_ORDER, "", SeverityLevel.WARN, "102", "DQA0123", "PID-25"),
	PatientBirthOrderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_ORDER, "", SeverityLevel.ACCEPT, "101", "DQA0124", "PID-25"),
	PatientBirthOrderIsMissingAndMultipleBirthIndicated(IssueObject.PATIENT, IssueType.MISSING_AND_MULTIPLE_BIRTH_INDICATED, IssueField.PATIENT_BIRTH_ORDER, "", SeverityLevel.WARN, "102", "DQA0125", "PID-25"),
	PatientBirthPlaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_PLACE, "", SeverityLevel.ACCEPT, "101", "DQA0126", "PID-23"),
	PatientBirthRegistryIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_REGISTRY_ID, "", SeverityLevel.WARN, "102", "DQA0127", "PID-3"),
	PatientBirthRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_REGISTRY_ID, "", SeverityLevel.ACCEPT, "101", "DQA0128", "PID-3"),
	PatientClassIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_CLASS, "", SeverityLevel.WARN, "102", "DQA0374", "PV1-2"),
	PatientClassIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_CLASS, "", SeverityLevel.INFO, "102", "DQA0375", "PV1-2"),
	PatientClassIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_CLASS, "", SeverityLevel.ACCEPT, "102", "DQA0376", "PV1-2"),
	PatientClassIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_CLASS, "", SeverityLevel.WARN, "101", "DQA0377", "PV1-2"),
	PatientClassIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_CLASS, "", SeverityLevel.ACCEPT, "103", "DQA0378", "PV1-2"),
	PatientDeathDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0129", "PID-29"),
	PatientDeathDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0130", "PID-29"),
	PatientDeathDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0131", "PID-29"),
	PatientDeathDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_DATE, "", SeverityLevel.WARN, "101", "DQA0132", "PID-29"),
	PatientDeathIndicatorIsInconsistent(IssueObject.PATIENT, IssueType.INCONSISTENT, IssueField.PATIENT_DEATH_INDICATOR, "", SeverityLevel.WARN, "102", "DQA0133", "PID-30"),
	PatientDeathIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_INDICATOR, "", SeverityLevel.ACCEPT, "101", "DQA0134", "PID-30"),
	PatientEthnicityIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "102", "DQA0135", "PID-22"),
	PatientEthnicityIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ETHNICITY, "", SeverityLevel.INFO, "102", "DQA0136", "PID-22"),
	PatientEthnicityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "102", "DQA0137", "PID-22"),
	PatientEthnicityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ETHNICITY, "", SeverityLevel.ACCEPT, "101", "DQA0138", "PID-22"),
	PatientEthnicityIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "103", "DQA0139", "PID-22"),
	PatientGenderIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_GENDER, "", SeverityLevel.WARN, "102", "DQA0143", "PID-8"),
	PatientGenderIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_GENDER, "", SeverityLevel.INFO, "102", "DQA0144", "PID-8"),
	PatientGenderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "102", "DQA0145", "PID-8"),
	PatientGenderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "101", "DQA0146", "PID-8"),
	PatientGenderIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "103", "DQA0147", "PID-8"),
	PatientGuardianAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS, "", SeverityLevel.ACCEPT, "101", "DQA0148", "NK1-4"),
	PatientGuardianAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", "DQA0149", "NK1-4.3"),
	PatientGuardianAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", "DQA0150", "NK1-4.4"),
	PatientGuardianAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", "DQA0151", "NK1-4.1"),
	PatientGuardianAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", "DQA0152", "NK1-4.5"),
	PatientGuardianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME, "", SeverityLevel.WARN, "101", "DQA0155", "NK1-2"),
	PatientGuardianNameIsSameAsUnderagePatient(IssueObject.PATIENT, IssueType.SAME_AS_UNDERAGE_PATIENT, IssueField.PATIENT_GUARDIAN_NAME, "", SeverityLevel.ACCEPT, "102", "DQA0156", "NK1-2"),
	PatientGuardianNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_FIRST, "", SeverityLevel.WARN, "101", "DQA0153", "NK1-2.2"),
	PatientGuardianNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_LAST, "", SeverityLevel.WARN, "101", "DQA0154", "NK1-2.1"),
	PatientGuardianResponsiblePartyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY, "", SeverityLevel.WARN, "101", "DQA0157", "NK1"),
	PatientGuardianPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_PHONE, "", SeverityLevel.ACCEPT, "101", "DQA0158", "NK1-5"),
	PatientGuardianRelationshipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RELATIONSHIP, "", SeverityLevel.WARN, "101", "DQA0159", "NK1-3"),
	PatientImmunizationRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", "DQA0160", "PD1-16"),
	PatientImmunizationRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.INFO, "102", "DQA0161", "PD1-16"),
	PatientImmunizationRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", "DQA0162", "PD1-16"),
	PatientImmunizationRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.ACCEPT, "101", "DQA0163", "PD1-16"),
	PatientImmunizationRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "103", "DQA0164", "PD1-16"),
	PatientMedicaidNumberIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MEDICAID_NUMBER, "", SeverityLevel.WARN, "102", "DQA0167", "PID-3"),
	PatientMedicaidNumberIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MEDICAID_NUMBER, "", SeverityLevel.ACCEPT, "101", "DQA0168", "PID-3"),
	PatientMiddleNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MIDDLE_NAME, "", SeverityLevel.ACCEPT, "101", "DQA0169", "PID-5.3"),
	PatientMiddleNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ACCEPT, "101", "DQA0169", "PID-5.3"),
	PatientMiddleNameMayBeInitial(IssueObject.PATIENT, IssueType.MAY_BE_AN_INITIAL, IssueField.PATIENT_MIDDLE_NAME, "", SeverityLevel.ACCEPT, "102", "DQA0170", "PID-5.3"),
	//Some of these weren't represented in the XLS file. 
	PatientMotherSMaidenNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", "DQA0171", "PID-6.1"),
	PatientMothersMaidenNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", "DQA0171", "PID-6.1"), 
	PatientMotherSMaidenNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT, "101", "DQA0171", "PID-6.1"),
	PatientMotherSMaidenNameHasInvalidPrefixes(IssueObject.PATIENT, IssueType.INVALID_PREFIXES, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT, "101", "DQA0171", "PID-6.1"),
	PatientMotherSMaidenNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT, "101", "DQA0171", "PID-6.1"),
	
	PatientNameMayBeTemporaryNewbornName(IssueObject.PATIENT, IssueType.MAY_BE_TEMPORARY_NEWBORN_NAME, IssueField.PATIENT_NAME, "", SeverityLevel.ACCEPT, "102", "DQA0172", "PID-5"),
	PatientNameMayBeTestName(IssueObject.PATIENT, IssueType.MAY_BE_TEST_NAME, IssueField.PATIENT_NAME, "", SeverityLevel.ACCEPT, "102", "DQA0173", "PID-5"),
	PatientNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.WARN, "102", "DQA0173", "PID-5"), 
	PatientNameFirstIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_FIRST, "", SeverityLevel.ERROR, "102", "DQA0140", "PID-5.2"),
	PatientNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_FIRST, "", SeverityLevel.ERROR, "101", "DQA0141", "PID-5.2"),
	PatientNameFirstMayIncludeMiddleInitial(IssueObject.PATIENT, IssueType.MAY_INCLUDE_MIDDLE_INITIAL, IssueField.PATIENT_NAME_FIRST, "", SeverityLevel.ACCEPT, "102", "DQA0142", "PID-5.2"),
	PatientNameLastIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_LAST, "", SeverityLevel.ERROR, "102", "DQA0165", "PID-5.1"),
	PatientNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_LAST, "", SeverityLevel.ERROR, "101", "DQA0166", "PID-5.1"),
	PatientNameMiddleIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MIDDLE_NAME, "", SeverityLevel.ERROR, "", "DQA0528", ""),
	PatientNameMiddleIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MIDDLE_NAME, "", SeverityLevel.ERROR, "", "DQA0529", ""),
	PatientNameTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "102", "DQA0405", "PID-5.7"),
	PatientNameTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.INFO, "102", "DQA0406", "PID-5.7"),
	PatientNameTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "102", "DQA0407", "PID-5.7"),
	PatientNameTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0408", "PID-5.7"),
	PatientNameTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "103", "DQA0409", "PID-5.7"),
	PatientNameTypeCodeIsNotValuedLegal(IssueObject.PATIENT, IssueType.NOT_VALUED_LEGAL, IssueField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "", "DQA0516", ""),
	PatientPhoneIsIncomplete(IssueObject.PATIENT, IssueType.INCOMPLETE, IssueField.PATIENT_PHONE, "", SeverityLevel.WARN, "102", "DQA0174", "PID-13"),
	PatientPhoneIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE, "", SeverityLevel.WARN, "102", "DQA0175", "PID-13"),
	PatientPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE, "", SeverityLevel.ACCEPT, "101", "DQA0176", "PID-13"),
	PatientPhoneTelUseCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "102", "DQA0453", "PID-13.2"),
	PatientPhoneTelUseCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.INFO, "102", "DQA0454", "PID-13.2"),
	PatientPhoneTelUseCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "102", "DQA0455", "PID-13.2"),
	PatientPhoneTelUseCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0456", "PID-13.2"),
	PatientPhoneTelUseCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "103", "DQA0457", "PID-13.2"),
	PatientPhoneTelEquipCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "102", "DQA0458", "PID-13.3"),
	PatientPhoneTelEquipCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.INFO, "102", "DQA0459", "PID-13.3"),
	PatientPhoneTelEquipCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "102", "DQA0460", "PID-13.3"),
	PatientPhoneTelEquipCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0461", "PID-13.3"),
	PatientPhoneTelEquipCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "103", "DQA0462", "PID-13.3"),
	PatientPrimaryFacilityIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.WARN, "102", "DQA0177", "PD1-3.3"),
	PatientPrimaryFacilityIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.INFO, "102", "DQA0178", "PD1-3.3"),
	PatientPrimaryFacilityIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", "DQA0179", "PD1-3.3"),
	PatientPrimaryFacilityIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", "DQA0180", "PD1-3.3"),
	PatientPrimaryFacilityIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.WARN, "103", "DQA0181", "PD1-3.3"),
	PatientPrimaryFacilityNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", "DQA0182", "PD1-3.1"),
	PatientPrimaryLanguageIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PRIMARY_LANGUAGE, "", SeverityLevel.ACCEPT, "102", "DQA0183", "PID-15"),
	PatientPrimaryLanguageIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PRIMARY_LANGUAGE, "", SeverityLevel.INFO, "102", "DQA0184", "PID-15"),
	PatientPrimaryLanguageIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PRIMARY_LANGUAGE, "", SeverityLevel.WARN, "102", "DQA0185", "PID-15"),
	PatientPrimaryLanguageIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PRIMARY_LANGUAGE, "", SeverityLevel.ACCEPT, "101", "DQA0186", "PID-15"),
	PatientPrimaryLanguageIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PRIMARY_LANGUAGE, "", SeverityLevel.WARN, "103", "DQA0187", "PID-15"),
	PatientPrimaryPhysicianIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "102", "DQA0188", "PD1-4.1"),
	PatientPrimaryPhysicianIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.INFO, "102", "DQA0189", "PD1-4.1"),
	PatientPrimaryPhysicianIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "102", "DQA0190", "PD1-4.1"),
	PatientPrimaryPhysicianIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.ACCEPT, "101", "DQA0191", "PD1-4.1"),
	PatientPrimaryPhysicianIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "103", "DQA0192", "PD1-4.1"),
	PatientPrimaryPhysicianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_NAME, "", SeverityLevel.ACCEPT, "101", "DQA0193", "PD1-4.2"),
	PatientProtectionIndicatorIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "102", "DQA0194", "PD1-12"),
	PatientProtectionIndicatorIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.INFO, "102", "DQA0195", "PD1-12"),
	PatientProtectionIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "102", "DQA0196", "PD1-12"),
	PatientProtectionIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.ACCEPT, "101", "DQA0197", "PD1-12"),
	PatientProtectionIndicatorIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "103", "DQA0198", "PD1-12"),
	PatientProtectionIndicatorIsValuedAsNo(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "no", SeverityLevel.ACCEPT, "102", "DQA0199", "PD1-12"),
	PatientProtectionIndicatorIsValuedAsYes(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "yes", SeverityLevel.WARN, "102", "DQA0200", "PD1-12"),
	PatientPublicityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "102", "DQA0201", "PD1-11"),
	PatientPublicityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.INFO, "102", "DQA0202", "PD1-11"),
	PatientPublicityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "102", "DQA0203", "PD1-11"),
	PatientPublicityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0204", "PD1-11"),
	PatientPublicityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "103", "DQA0205", "PD1-11"),
	PatientRaceIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_RACE, "", SeverityLevel.WARN, "102", "DQA0206", "PID-10"),
	PatientRaceIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_RACE, "", SeverityLevel.INFO, "102", "DQA0207", "PID-10"),
	PatientRaceIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_RACE, "", SeverityLevel.WARN, "102", "DQA0208", "PID-10"),
	PatientRaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_RACE, "", SeverityLevel.ACCEPT, "101", "DQA0209", "PID-10"),
	PatientRaceIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_RACE, "", SeverityLevel.WARN, "103", "DQA0210", "PID-10"),
	PatientRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_ID, "", SeverityLevel.ACCEPT, "101", "DQA0211", "PID-3"),
	PatientRegistryIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_ID, "", SeverityLevel.ACCEPT, "103", "DQA0212", "PID-3"),
	PatientRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", "DQA0213", "PD1-16"),
	PatientRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.INFO, "102", "DQA0214", "PD1-16"),
	PatientRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", "DQA0215", "PD1-16"),
	PatientRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.ACCEPT, "101", "DQA0216", "PD1-16"),
	PatientRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "103", "DQA0217", "PD1-16"),
	PatientSsnIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SSN, "", SeverityLevel.WARN, "102", "DQA0218", "PID-3"),
	PatientSsnIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SSN, "", SeverityLevel.ACCEPT, "101", "DQA0219", "PID-3"),
	PatientSubmitterIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID, "", SeverityLevel.ERROR, "101", "DQA0220", "PID-3"),
	PatientSubmitterIdAuthorityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_AUTHORITY, "", SeverityLevel.WARN, "101", "DQA0393", "PID-3.4"),
	PatientSubmitterIdTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.ERROR, "101", "DQA0394", "PID-3.5"),
	PatientSubmitterIdTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.WARN, "", "DQA0512", ""),
	PatientSubmitterIdTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.ERROR, "", "DQA0513", ""),
	PatientSubmitterIdTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.WARN, "", "DQA0514", ""),
	PatientSubmitterIdTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.INFO, "", "DQA0515", ""),
	PatientSystemCreationDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SYSTEM_CREATION_DATE, "", SeverityLevel.ACCEPT, "", "DQA0530", ""),
	PatientSystemCreationDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR, "", "DQA0530", ""), 
	PatientSystemCreationDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR, "DQA9999", ""), 
	PatientVfcEffectiveDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0221", "PV1-20.2"),
	PatientVfcEffectiveDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0222", "PV1-20.2"),
	PatientVfcEffectiveDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0223", "PV1-20.2"),
	PatientVfcEffectiveDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "101", "DQA0224", "PV1-20.2"),
	PatientVfcStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, IssueField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "102", "DQA0225", "PV1-20.1"),
	PatientVfcStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_VFC_STATUS, "", SeverityLevel.INFO, "102", "DQA0226", "PV1-20.1"),
	PatientVfcStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "102", "DQA0227", "PV1-20.1"),
	PatientVfcStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_STATUS, "", SeverityLevel.ACCEPT, "101", "DQA0228", "PV1-20.1"),
	PatientVfcStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "103", "DQA0229", "PV1-20.1"),
	PatientWicIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_WIC_ID, "", SeverityLevel.ACCEPT, "102", "DQA0230", "PID-3"),
	PatientWicIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_WIC_ID, "", SeverityLevel.ACCEPT, "101", "DQA0231", "PID-3"),
	VaccinationActionCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_ACTION_CODE, "", SeverityLevel.WARN, "102", "DQA0232", "RXA-21"),
	VaccinationActionCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ACTION_CODE, "", SeverityLevel.INFO, "102", "DQA0233", "RXA-21"),
	VaccinationActionCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ACTION_CODE, "", SeverityLevel.ACCEPT, "102", "DQA0234", "RXA-21"),
	VaccinationActionCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ACTION_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0235", "RXA-21"),
	VaccinationActionCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ACTION_CODE, "", SeverityLevel.WARN, "103", "DQA0236", "RXA-21"),
	VaccinationActionCodeIsValuedAsAdd(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add", SeverityLevel.ACCEPT, "102", "DQA0237", "RXA-21"),
	VaccinationActionCodeIsValuedAsAddOrUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add or update", SeverityLevel.ACCEPT, "102", "DQA0238", "RXA-21"),
	VaccinationActionCodeIsValuedAsDelete(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "delete", SeverityLevel.ACCEPT, "102", "DQA0239", "RXA-21"),
	VaccinationActionCodeIsValuedAsUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "update", SeverityLevel.ACCEPT, "102", "DQA0240", "RXA-21"),
	VaccinationAdminCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", "DQA0241", "RXA-5"),
	VaccinationAdminCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.INFO, "102", "DQA0242", "RXA-5"),
	VaccinationAdminCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "102", "DQA0243", "RXA-5"),
	VaccinationAdminCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "102", "DQA0491", "RXA-5"),
	VaccinationAdminCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "101", "DQA0244", "RXA-5"),
	VaccinationAdminCodeIsNotSpecific(IssueObject.VACCINATION, IssueType.NOT_SPECIFIC, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", "DQA0245", "RXA-5"),
	VaccinationAdminCodeIsNotVaccine(IssueObject.VACCINATION, IssueType.NOT_VACCINE, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ACCEPT, "102", "DQA0246", "RXA-5"),
	VaccinationAdminCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", "DQA0490", "RXA-5"),
	VaccinationAdminCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "103", "DQA0247", "RXA-5"),
	VaccinationAdminCodeIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "not administered", SeverityLevel.ACCEPT, "102", "DQA0248", "RXA-5"),
	VaccinationAdminCodeIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "unknown", SeverityLevel.WARN, "102", "DQA0249", "RXA-5"),
	VaccinationAdminCodeTableIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE_TABLE, "", SeverityLevel.ERROR, "101", "DQA0483", "RXA-5"),
	VaccinationAdminCodeTableIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE_TABLE, "", SeverityLevel.ERROR, "102", "DQA0484", "RXA-5"),
	VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(IssueObject.VACCINATION, IssueType.MAY_BE_PREVIOUSLY_REPORTED, IssueField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", "DQA0250", "RXA-5"),
	VaccinationAdminDateIsAfterLotExpirationDate(IssueObject.VACCINATION, IssueType.AFTER_LOT_EXPIRATION, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", "DQA0251", "RXA-3"),
	VaccinationAdminDateIsAfterMessageSubmitted(IssueObject.VACCINATION, IssueType.AFTER_MESSAGE_SUBMITTED, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0252", "RXA-3"),
	VaccinationAdminDateIsAfterPatientDeathDate(IssueObject.VACCINATION, IssueType.AFTER_PATIENT_DEATH_DATE, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", "DQA0253", "RXA-3"),
	VaccinationAdminDateIsAfterSystemEntryDate(IssueObject.VACCINATION, IssueType.AFTER_SYSTEM_ENTRY_DATE, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", "DQA0254", "RXA-3"),
	VaccinationAdminDateIsBeforeBirth(IssueObject.VACCINATION, IssueType.BEFORE_BIRTH, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", "DQA0255", "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", "DQA0256", "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", "DQA0257", "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", "DQA0258", "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", "DQA0259", "RXA-3"),
	VaccinationAdminDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", "DQA0260", "RXA-3"),
	VaccinationAdminDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "101", "DQA0261", "RXA-3"),
	VaccinationAdminDateIsOn15ThDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIFTEENTH_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0262", "RXA-3"),
	VaccinationAdminDateIsOnFirstDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIRST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0263", "RXA-3"),
	VaccinationAdminDateIsOnLastDayOfMonth(IssueObject.VACCINATION, IssueType.ON_LAST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0264", "RXA-3"),
	VaccinationAdminDateIsReportedLate(IssueObject.VACCINATION, IssueType.REPORTED_LATE, IssueField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", "DQA0265", "RXA-3"),
	VaccinationAdminDateEndIsDifferentFromStartDate(IssueObject.VACCINATION, IssueType.DIFF_FROM_START, IssueField.VACCINATION_ADMIN_DATE_END, "", SeverityLevel.WARN, "102", "DQA0266", "RXA-3"),
	VaccinationAdminDateEndIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE_END, "", SeverityLevel.ACCEPT, "101", "DQA0267", "RXA-3"),
	VaccinationAdministeredCodeIsForiegn(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ACCEPT, "102", "DQA0268", "?"),
	VaccinationHistoricalCodeIsForeign(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ACCEPT,  "102", "DQA0268", "?"),
	VaccinationAdministeredAmountIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "", SeverityLevel.WARN, "102", "DQA0268", "RXA-6"),
	VaccinationAdministeredAmountIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "", SeverityLevel.ACCEPT, "101", "DQA0269", "RXA-6"),
	VaccinationAdministeredAmountIsValuedAsZero(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "zero", SeverityLevel.ACCEPT, "102", "DQA0270", "RXA-6"),
	VaccinationAdministeredAmountIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "unknown", SeverityLevel.ACCEPT, "102", "DQA0271", "RXA-6"),
	VaccinationAdministeredUnitIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "102", "DQA0447", "RXA-7"),
	VaccinationAdministeredUnitIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.INFO, "102", "DQA0448", "RXA-7"),
	VaccinationAdministeredUnitIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "102", "DQA0449", "RXA-7"),
	VaccinationAdministeredUnitIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.ACCEPT, "101", "DQA0272", "RXA-7"),
	VaccinationAdministeredUnitIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "103", "DQA0450", "RXA-7"),
	VaccinationBodyRouteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", "DQA0273", "RXR-1"),
	VaccinationBodyRouteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_ROUTE, "", SeverityLevel.INFO, "102", "DQA0274", "RXR-1"),
	VaccinationBodyRouteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", "DQA0275", "RXR-1"),
	VaccinationBodyRouteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", "DQA0276", "RXR-1"),
	VaccinationBodyRouteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_ROUTE, "", SeverityLevel.ACCEPT, "101", "DQA0277", "RXR-1"),
	VaccinationBodyRouteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "103", "DQA0278", "RXR-1"),
	VaccinationBodySiteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", "DQA0279", "RXR-2"),
	VaccinationBodySiteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_SITE, "", SeverityLevel.INFO, "102", "DQA0280", "RXR-2"),
	VaccinationBodySiteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", "DQA0281", "RXR-2"),
	VaccinationBodySiteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", "DQA0282", "RXR-2"),
	VaccinationBodySiteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_SITE, "", SeverityLevel.ACCEPT, "101", "DQA0283", "RXR-2"),
	VaccinationBodySiteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "103", "DQA0284", "RXR-2"),
	VaccinationCompletionStatusIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.WARN, "102", "DQA0285", "RXA-20"),
	VaccinationCompletionStatusIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.INFO, "102", "DQA0286", "RXA-20"),
	VaccinationCompletionStatusIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "102", "DQA0287", "RXA-20"),
	VaccinationCompletionStatusIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "101", "DQA0288", "RXA-20"),
	VaccinationCompletionStatusIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "103", "DQA0289", "RXA-20"),
	VaccinationCompletionStatusIsValuedAsCompleted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "completed", SeverityLevel.ACCEPT, "102", "DQA0290", "RXA-20"),
	VaccinationCompletionStatusIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "not administered", SeverityLevel.INFO, "102", "DQA0291", "RXA-20"),
	VaccinationCompletionStatusIsValuedAsPartiallyAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "partially administered", SeverityLevel.INFO, "102", "DQA0292", "RXA-20"),
	VaccinationCompletionStatusIsValuedAsRefused(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "refused", SeverityLevel.INFO, "102", "DQA0293", "RXA-20"),
	VaccinationConfidentialityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "102", "DQA0294", "ORC-28"),
	VaccinationConfidentialityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.INFO, "102", "DQA0295", "ORC-28"),
	VaccinationConfidentialityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "102", "DQA0296", "ORC-28"),
	VaccinationConfidentialityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0297", "ORC-28"),
	VaccinationConfidentialityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "103", "DQA0298", "ORC-28"),
	VaccinationConfidentialityCodeIsValuedAsRestricted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "restricted", SeverityLevel.WARN, "102", "DQA0299", "ORC-28"),
	VaccinationCptCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", "DQA0300", "RXA-5"),
	VaccinationCptCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CPT_CODE, "", SeverityLevel.INFO, "102", "DQA0301", "RXA-5"),
	VaccinationCptCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", "DQA0302", "RXA-5"),
	VaccinationCptCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", "DQA0489", "RXA-5"),
	VaccinationCptCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CPT_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0303", "RXA-5"),
	VaccinationCptCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE, "", SeverityLevel.ACCEPT, "102", "DQA0488", "RXA-5"),
	VaccinationCptCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "103", "DQA0304", "RXA-5"),
	VaccinationCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", "DQA0305", "RXA-5"),
	VaccinationCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CVX_CODE, "", SeverityLevel.INFO, "102", "DQA0306", "RXA-5"),
	VaccinationCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", "DQA0307", "RXA-5"),
	VaccinationCvxCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", "DQA0487", "RXA-5"),
	VaccinationCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CVX_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0308", "RXA-5"),
	VaccinationCvxCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE, "", SeverityLevel.ACCEPT, "102", "DQA0486", "RXA-5"),
	VaccinationCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "103", "DQA0309", "RXA-5"),
	VaccinationCvxCodeAndCptCodeAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_CVX_CODE_AND_CPT_CODE, "", SeverityLevel.WARN, "102", "DQA0310", "RXA-5"),
	VaccinationFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_FACILITY_ID, "", SeverityLevel.WARN, "102", "DQA0311", "RXA-11.4"),
	VaccinationFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_ID, "", SeverityLevel.INFO, "102", "DQA0312", "RXA-11.4"),
	VaccinationFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", "DQA0313", "RXA-11.4"),
	VaccinationFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", "DQA0314", "RXA-11.4"),
	VaccinationFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "103", "DQA0315", "RXA-11.4"),
	VaccinationFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", "DQA0316", "RXA-11.4"),
	VaccinationFillerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", "DQA0379", "ORC-3"),
	VaccinationFillerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.INFO, "102", "DQA0380", "ORC-3"),
	VaccinationFillerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", "DQA0381", "ORC-3"),
	VaccinationFillerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.ACCEPT, "101", "DQA0382", "ORC-3"),
	VaccinationFillerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "103", "DQA0383", "ORC-3"),
	VaccinationFinancialEligibilityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.WARN, "102", "DQA0465", "OBX-5"),
	VaccinationFinancialEligibilityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.INFO, "102", "DQA0466", "OBX-5"),
	VaccinationFinancialEligibilityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "102", "DQA0467", "OBX-5"),
	VaccinationFinancialEligibilityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0468", "OBX-5"),
	VaccinationFinancialEligibilityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "103", "DQA0469", "OBX-5"),
	VaccinationGivenByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "102", "DQA0317", "RXA-10"),
	VaccinationGivenByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_GIVEN_BY, "", SeverityLevel.INFO, "102", "DQA0318", "RXA-10"),
	VaccinationGivenByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "102", "DQA0319", "RXA-10"),
	VaccinationGivenByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_GIVEN_BY, "", SeverityLevel.ACCEPT, "101", "DQA0320", "RXA-10"),
	VaccinationGivenByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "103", "DQA0321", "RXA-10"),
	VaccinationIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID, "", SeverityLevel.ACCEPT, "101", "DQA0322", "ORC-3"),
	VaccinationIdOfReceiverIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_RECEIVER, "", SeverityLevel.ACCEPT, "101", "DQA0323", "ORC-2"),
	VaccinationIdOfReceiverIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_RECEIVER, "", SeverityLevel.WARN, "103", "DQA0324", "ORC-2"),
	VaccinationIdOfSenderIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_SENDER, "", SeverityLevel.ACCEPT, "101", "DQA0325", "ORC-3"),
	VaccinationIdOfSenderIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_SENDER, "", SeverityLevel.WARN, "103", "DQA0326", "ORC-3"),
	VaccinationInformationSourceIsAdministeredButAppearsToHistorical(IssueObject.VACCINATION, IssueType.ADMINISTERED_BUT_APPEARS_TO_HISTORICAL, IssueField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", "DQA0327", "RXA-9"),
	VaccinationInformationSourceIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", "DQA0328", "RXA-9"),
	VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(IssueObject.VACCINATION, IssueType.HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED, IssueField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", "DQA0329", "RXA-9"),
	VaccinationInformationSourceIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.INFO, "102", "DQA0330", "RXA-9"),
	VaccinationInformationSourceIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "102", "DQA0331", "RXA-9"),
	VaccinationInformationSourceIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "101", "DQA0332", "RXA-9"),
	VaccinationInformationSourceIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "103", "DQA0333", "RXA-9"),
	VaccinationInformationSourceIsValuedAsAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "administered", SeverityLevel.ACCEPT, "102", "DQA0334", "RXA-9"),
	VaccinationInformationSourceIsValuedAsHistorical(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "historical", SeverityLevel.ACCEPT, "102", "DQA0335", "RXA-9"),
	VaccinationVisDocumentTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", "DQA0496", ""),
	VaccinationVisDocumentTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.INFO, "", "DQA0497", ""),
	VaccinationVisDocumentTypeIsIncorrect(IssueObject.VACCINATION, IssueType.INCORRECT, IssueField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", "DQA0498", ""),
	VaccinationVisDocumentTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", "DQA0499", ""),
	VaccinationVisDocumentTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", "DQA0500", ""),
	VaccinationVisDocumentTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", "DQA0501", ""),
	VaccinationVisDocumentTypeIsOutOfDate(IssueObject.VACCINATION, IssueType.OUT_OF_DATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", "DQA0502", ""),
	VaccinationVisVersionDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", "DQA0503", ""),
	VaccinationVisVersionDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", "DQA0504", ""),
	VaccinationVisVersionDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", "DQA0505", ""),
	VaccinationVisVersionDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", "DQA0506", ""),
	VaccinationVisDeliveryDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", "DQA0507", ""),
	VaccinationVisDeliveryDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", "DQA0508", ""),
	VaccinationVisDeliveryDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, IssueField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", "DQA0509", ""),
	VaccinationVisDeliveryDateIsBeforeVersionDate(IssueObject.VACCINATION, IssueType.BEFORE_VERSION_DATE, IssueField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", "DQA0510", ""),
	VaccinationVisDeliveryDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, IssueField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", "DQA0511", ""),
	
	//More that were missing. 
	VaccinationVisPublishedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPublishedDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPublishedDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPresentedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPresentedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPresentedDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPresentedDateIsBeforePublishedDate(IssueObject.VACCINATION, IssueType.BEFORE_PUBLISHED_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPresentedDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS,"", SeverityLevel.WARN, "", "DQA9999", ""),
	VaccinationVisPublishedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", "DQA9999", ""),
	
	
	VaccinationLotExpirationDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_EXPIRATION_DATE, "", SeverityLevel.WARN, "102", "DQA0336", "RXA-16"),
	VaccinationLotExpirationDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_EXPIRATION_DATE, "", SeverityLevel.ACCEPT, "101", "DQA0337", "RXA-16"),
	VaccinationLotNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_NUMBER, "", SeverityLevel.WARN, "102", "DQA0338", "RXA-15"),
	VaccinationLotNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_NUMBER, "", SeverityLevel.WARN, "101", "DQA0339", "RXA-15"),
	VaccinationManufacturerCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", "DQA0340", "RXA-17"),
	VaccinationManufacturerCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.INFO, "102", "DQA0341", "RXA-17"),
	VaccinationManufacturerCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", "DQA0342", "RXA-17"),
	VaccinationManufacturerCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", "DQA0495", "RXA-17"),
	VaccinationManufacturerCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "101", "DQA0343", "RXA-17"),
	VaccinationManufacturerCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", "DQA0494", "RXA-17"),
	VaccinationManufacturerCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "103", "DQA0344", "RXA-17"),
	VaccinationOrderControlCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "102", "DQA0373", "ORC-1"),
	VaccinationOrderControlCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.INFO, "102", "DQA0369", "ORC-1"),
	VaccinationOrderControlCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "102", "DQA0370", "ORC-1"),
	VaccinationOrderControlCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.ACCEPT, "101", "DQA0371", "ORC-1"),
	VaccinationOrderControlCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "103", "DQA0372", "ORC-1"),
	VaccinationOrderFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", "DQA0442", "ORC-21"),
	VaccinationOrderFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.INFO, "102", "DQA0443", "ORC-21"),
	VaccinationOrderFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.WARN, "102", "DQA0444", "ORC-21"),
	VaccinationOrderFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", "DQA0445", "ORC-21"),
	VaccinationOrderFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.WARN, "103", "DQA0446", "ORC-21"),
	VaccinationOrderFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", "DQA0441", "ORC-21"),
	VaccinationOrderedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "102", "DQA0345", "XCN-12"),
	VaccinationOrderedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDERED_BY, "", SeverityLevel.INFO, "102", "DQA0346", "XCN-12"),
	VaccinationOrderedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "102", "DQA0347", "XCN-12"),
	VaccinationOrderedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDERED_BY, "", SeverityLevel.ACCEPT, "101", "DQA0348", "XCN-12"),
	VaccinationOrderedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "103", "DQA0349", "XCN-12"),
	VaccinationPlacerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", "DQA0384", "ORC-2"),
	VaccinationPlacerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.INFO, "102", "DQA0385", "ORC-2"),
	VaccinationPlacerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", "DQA0386", "ORC-2"),
	VaccinationPlacerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.ACCEPT, "101", "DQA0387", "ORC-2"),
	VaccinationPlacerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "103", "DQA0388", "ORC-2"),
	VaccinationProductIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_PRODUCT, "", SeverityLevel.WARN, "102", "DQA0350", "RXA-5","RXA-17"),
	VaccinationProductIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", "DQA0351", "RXA-5","RXA-17"),
	VaccinationProductIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", "DQA0493", "RXA-5","RXA-17"),
	VaccinationProductIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "101", "DQA0352", "RXA-5 or RXA-17"),
	VaccinationProductIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", "DQA0492", "RXA-5","RXA-17"),
	VaccinationProductIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PRODUCT, "", SeverityLevel.WARN, "103", "DQA0353", "RXA-5","RXA-17"),
	VaccinationRecordedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "102", "DQA0354", "ORC-10"),
	VaccinationRecordedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_RECORDED_BY, "", SeverityLevel.INFO, "102", "DQA0355", "ORC-10"),
	VaccinationRecordedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "102", "DQA0356", "ORC-10"),
	VaccinationRecordedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_RECORDED_BY, "", SeverityLevel.ACCEPT, "101", "DQA0357", "ORC-10"),
	VaccinationRecordedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "103", "DQA0358", "ORC-10"),
	VaccinationRefusalReasonConflictsCompletionStatus(IssueObject.VACCINATION, IssueType.CONFLICTS_COMPLETION_STATUS, IssueField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "102", "DQA0359", "RXA-18"),
	VaccinationRefusalReasonIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, IssueField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.WARN, "102", "DQA0360", "RXA-18"),
	VaccinationRefusalReasonIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.INFO, "102", "DQA0361", "RXA-18"),
	VaccinationRefusalReasonIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "102", "DQA0362", "RXA-18"),
	VaccinationRefusalReasonIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "101", "DQA0363", "RXA-18"),
	VaccinationRefusalReasonIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "103", "DQA0364", "RXA-18"),
	VaccinationSystemEntryTimeIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "102", "DQA0365", "RXA-22"),
	VaccinationSystemEntryTimeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "102", "DQA0366", "RXA-22"),
	VaccinationSystemEntryTimeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "101", "DQA0367", "RXA-22"),
	UnknownValidationIssue(IssueObject.GENERAL, IssueType.UNRECOGNIZED, IssueField.CONFIGURATION, "", SeverityLevel.ERROR, "101", "DQA0000", "")
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
	    private SeverityLevel severity;
	    private String[] hl7Locations;
	    private String dqaErrorCode;
	    private String hl7ErrorCode;
	    
	private MessageAttribute(IssueObject entity, IssueType type,
			IssueField fieldRef, String valuation, SeverityLevel severity,
			String hl7ErrCode, String dqaErrorCode, String... hl7Location) {
	
		this.fieldValue = valuation;
		this.targetObject = entity;
		this.issueType = type;
		this.fieldRef = fieldRef;
		this.severity = severity;
		
		this.hl7Locations = hl7Location;
		this.dqaErrorCode = dqaErrorCode;
		this.hl7ErrorCode = hl7ErrCode;
		
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
		return found;
  }
  
  public ValidationIssue build() {
		ValidationIssue found = new ValidationIssue();
		found.setMessageAttribute(this);
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

public SeverityLevel getSeverity() {
	return severity;
}

public String[] getHl7Locations() {
	return hl7Locations;
}

public String getDqaErrorCode() {
	return dqaErrorCode;
}

public String getHl7ErrorCode() {
	return hl7ErrorCode;
}


}
