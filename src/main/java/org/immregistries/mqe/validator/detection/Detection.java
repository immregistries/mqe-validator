package org.immregistries.mqe.validator.detection;

import static org.immregistries.mqe.hl7util.SeverityLevel.ACCEPT;
import static org.immregistries.mqe.hl7util.SeverityLevel.ERROR;
import static org.immregistries.mqe.hl7util.SeverityLevel.INFO;
import static org.immregistries.mqe.hl7util.SeverityLevel.WARN;
import static org.immregistries.mqe.validator.detection.DetectionType.ADMINISTERED_BUT_APPEARS_TO_HISTORICAL;
import static org.immregistries.mqe.validator.detection.DetectionType.AFTER_ADMIN_DATE;
import static org.immregistries.mqe.validator.detection.DetectionType.AFTER_LOT_EXPIRATION;
import static org.immregistries.mqe.validator.detection.DetectionType.AFTER_MESSAGE_SUBMITTED;
import static org.immregistries.mqe.validator.detection.DetectionType.AFTER_PATIENT_DEATH_DATE;
import static org.immregistries.mqe.validator.detection.DetectionType.AFTER_SUBMISSION;
import static org.immregistries.mqe.validator.detection.DetectionType.AFTER_SYSTEM_ENTRY_DATE;
import static org.immregistries.mqe.validator.detection.DetectionType.ARE_INCONSISTENT;
import static org.immregistries.mqe.validator.detection.DetectionType.BEFORE_BIRTH;
import static org.immregistries.mqe.validator.detection.DetectionType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE;
import static org.immregistries.mqe.validator.detection.DetectionType.BEFORE_OR_AFTER_EXPECTED_DATE_RANGE;
import static org.immregistries.mqe.validator.detection.DetectionType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE;
import static org.immregistries.mqe.validator.detection.DetectionType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE;
import static org.immregistries.mqe.validator.detection.DetectionType.BEFORE_PUBLISHED_DATE;
import static org.immregistries.mqe.validator.detection.DetectionType.BEFORE_VERSION_DATE;
import static org.immregistries.mqe.validator.detection.DetectionType.CONFLICTS_COMPLETION_STATUS;
import static org.immregistries.mqe.validator.detection.DetectionType.DEPRECATED;
import static org.immregistries.mqe.validator.detection.DetectionType.DIFFERENT_FROM_PATIENT_ADDRESS;
import static org.immregistries.mqe.validator.detection.DetectionType.DIFF_FROM_START;
import static org.immregistries.mqe.validator.detection.DetectionType.EXCEPTION;
import static org.immregistries.mqe.validator.detection.DetectionType.HAS_JUNK_NAME;
import static org.immregistries.mqe.validator.detection.DetectionType.HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED;
import static org.immregistries.mqe.validator.detection.DetectionType.IGNORED;
import static org.immregistries.mqe.validator.detection.DetectionType.INCOMPLETE;
import static org.immregistries.mqe.validator.detection.DetectionType.INCONSISTENT;
import static org.immregistries.mqe.validator.detection.DetectionType.INCORRECT;
import static org.immregistries.mqe.validator.detection.DetectionType.INVALID;
import static org.immregistries.mqe.validator.detection.DetectionType.INVALID_FOR_DATE_ADMINISTERED;
import static org.immregistries.mqe.validator.detection.DetectionType.INVALID_FOR_VACCINE;
import static org.immregistries.mqe.validator.detection.DetectionType.INVALID_INFIXES;
import static org.immregistries.mqe.validator.detection.DetectionType.INVALID_PREFIXES;
import static org.immregistries.mqe.validator.detection.DetectionType.INVALID_SUFFIXES;
import static org.immregistries.mqe.validator.detection.DetectionType.IN_FUTURE;
import static org.immregistries.mqe.validator.detection.DetectionType.IS_LATE;
import static org.immregistries.mqe.validator.detection.DetectionType.IS_ON_TIME;
import static org.immregistries.mqe.validator.detection.DetectionType.IS_TOO_LATE;
import static org.immregistries.mqe.validator.detection.DetectionType.IS_VERY_LATE;
import static org.immregistries.mqe.validator.detection.DetectionType.MAY_BE_AN_INITIAL;
import static org.immregistries.mqe.validator.detection.DetectionType.MAY_BE_PREVIOUSLY_REPORTED;
import static org.immregistries.mqe.validator.detection.DetectionType.MAY_BE_TEMPORARY_NEWBORN_NAME;
import static org.immregistries.mqe.validator.detection.DetectionType.MAY_BE_TEST_NAME;
import static org.immregistries.mqe.validator.detection.DetectionType.MAY_INCLUDE_MIDDLE_INITIAL;
import static org.immregistries.mqe.validator.detection.DetectionType.MISSING;
import static org.immregistries.mqe.validator.detection.DetectionType.MISSING_AND_MULTIPLE_BIRTH_INDICATED;
import static org.immregistries.mqe.validator.detection.DetectionType.MISSING_TIMEZONE;
import static org.immregistries.mqe.validator.detection.DetectionType.MUTLIPLES;
import static org.immregistries.mqe.validator.detection.DetectionType.NOT_PRECISE;
import static org.immregistries.mqe.validator.detection.DetectionType.NOT_RESPONSIBLE_PARTY;
import static org.immregistries.mqe.validator.detection.DetectionType.NOT_SAME_AS_ADMIN_DATE;
import static org.immregistries.mqe.validator.detection.DetectionType.NOT_SPECIFIC;
import static org.immregistries.mqe.validator.detection.DetectionType.NOT_USABLE;
import static org.immregistries.mqe.validator.detection.DetectionType.NOT_VACCINE;
import static org.immregistries.mqe.validator.detection.DetectionType.NOT_VALUED_LEGAL;
import static org.immregistries.mqe.validator.detection.DetectionType.ON_FIFTEENTH_DAY_OF_MONTH;
import static org.immregistries.mqe.validator.detection.DetectionType.ON_FIRST_DAY_OF_MONTH;
import static org.immregistries.mqe.validator.detection.DetectionType.ON_LAST_DAY_OF_MONTH;
import static org.immregistries.mqe.validator.detection.DetectionType.OUT_OF_DATE;
import static org.immregistries.mqe.validator.detection.DetectionType.PRESENT;
import static org.immregistries.mqe.validator.detection.DetectionType.REPORTED_LATE;
import static org.immregistries.mqe.validator.detection.DetectionType.SAME_AS_UNDERAGE_PATIENT;
import static org.immregistries.mqe.validator.detection.DetectionType.TOO_SHORT;
import static org.immregistries.mqe.validator.detection.DetectionType.UNDERAGE;
import static org.immregistries.mqe.validator.detection.DetectionType.UNEXPECTED;
import static org.immregistries.mqe.validator.detection.DetectionType.UNEXPECTED_FORMAT;
import static org.immregistries.mqe.validator.detection.DetectionType.UNEXPECTED_FOR_DATE_ADMINISTERED;
import static org.immregistries.mqe.validator.detection.DetectionType.UNEXPECTED_FOR_FINANCIAL_ELIGIBILITY;
import static org.immregistries.mqe.validator.detection.DetectionType.UNRECOGNIZED;
import static org.immregistries.mqe.validator.detection.DetectionType.VACCINATION_COUNT_EXCEEDS_EXPECTATIONS;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_ADD;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_ADD_OR_UPDATE;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_ADMINISTERED;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_COMPLETED;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_DELETE;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_FOREIGN;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_HISTORICAL;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_NO;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_NOT_ADMINISTERED;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_PARTIALLY_ADMINISTERED;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_REFUSED;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_RESTRICTED;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_UNKNOWN;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_UPDATE;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_YES;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_AS_ZERO;
import static org.immregistries.mqe.validator.detection.DetectionType.VALUED_BAD_ADDRESS;
import static org.immregistries.mqe.validator.detection.DetectionType.VERY_LONG_AGO;
import static org.immregistries.mqe.validator.detection.MqeCode.*;
import static org.immregistries.mqe.vxu.VxuField.*;
import java.util.HashMap;
import java.util.Map;
import org.immregistries.mqe.hl7util.ApplicationErrorCode;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.hl7util.builder.AckERRCode;
import org.immregistries.mqe.hl7util.model.Hl7Location;
import org.immregistries.mqe.hl7util.model.MetaFieldInfo;
import org.immregistries.mqe.util.validation.MqeDetection;
import org.immregistries.mqe.vxu.MetaFieldInfoData;
import org.immregistries.mqe.vxu.VxuField;
import org.immregistries.mqe.vxu.VxuObject;

public enum Detection implements MqeDetection {
  // @formatter:off
  GeneralAuthorizationException(AUTHORIZATION, EXCEPTION, ACCEPT, MQE0002),
  GeneralConfigurationException(CONFIGURATION, EXCEPTION, ACCEPT, MQE0003),
  GeneralParseException(PARSE, EXCEPTION, ACCEPT, MQE0004),
  GeneralProcessingException(PROCESSING, EXCEPTION, ACCEPT, MQE0005),
  UnknownDetection(PROCESSING, EXCEPTION, ACCEPT, MQE0558),

  //These might be validated from NIST validations.
  //Maybe we have an entry for Hl7StructureWarning, Hl7StructureError...
  //We need to figure this out...
  //MQE should not be looking at the ACK type...
  //accept ack type

  MessageAcceptAckTypeIsMissing(MESSAGE_ACCEPT_ACK_TYPE, MISSING, ACCEPT, MQE0006),
  MessageAppAckTypeIsMissing(MESSAGE_APP_ACK_TYPE, MISSING, ACCEPT, MQE0410),
  MessageMessageControlIdIsMissing(MESSAGE_CONTROL_ID, MISSING, WARN, MQE0014),

  //This we validate against other stuff.
  MessageMessageDateIsInFuture(MESSAGE_DATE, IN_FUTURE, ERROR, MQE0015),
//  MessageMessageDateIsInvalid(MESSAGE_DATE, INVALID, ACCEPT, MQE0016),  /* Decided this is covered by more specific detections. NB and JH */
  MessageMessageDateIsMissing(MESSAGE_DATE, MISSING, ERROR, MQE0017),
  MessageMessageDateIsNotPrecise(MESSAGE_DATE, NOT_PRECISE, WARN, MQE0526),
  MessageMessageDateTimezoneIsMissing(MESSAGE_DATE, MISSING_TIMEZONE, WARN, MQE0527),
  MessageMessageDateIsUnexpectedFormat(MESSAGE_DATE, UNEXPECTED_FORMAT, ERROR, MQE0531),

  MessageMessageProfileIdIsMissing(MESSAGE_PROFILE_ID, MISSING, ACCEPT, MQE0439),
  MessageMessageTriggerIsMissing(MESSAGE_TRIGGER, MISSING, WARN, MQE0018),
  MessageMessageTypeIsMissing(MESSAGE_TYPE, MISSING, WARN, MQE0020),
  MessageProcessingIdIsMissing(MESSAGE_PROCESSING_ID, MISSING, ACCEPT, MQE0023),
  MessageReceivingApplicationIsMissing(MESSAGE_RECEIVING_APPLICATION, MISSING, ACCEPT, MQE0030),
  MessageReceivingFacilityIsMissing(MESSAGE_RECEIVING_FACILITY, MISSING, ACCEPT, MQE0032),
  MessageSendingApplicationIsMissing(MESSAGE_SENDING_APPLICATION, MISSING, ACCEPT, MQE0035),
  MessageSendingFacilityIsMissing(MESSAGE_SENDING_FACILITY, MISSING, WARN, MQE0037),
  MessageSendingResponsibleOrganizationIsMissing(MESSAGE_SENDING_RESPONSIBLE_ORGANIZATION, MISSING, WARN, MQE0556),

  MessageVersionIsMissing(MESSAGE_VERSION, MISSING, ACCEPT, MQE0038),
  MessageVersionIsUnrecognized(MESSAGE_VERSION, UNRECOGNIZED, WARN, MQE0039),
  MessageVersionIsInvalid(MESSAGE_VERSION, INVALID, WARN, MQE0523),

  NextOfKinAddressIsDifferentFromPatientAddress(NEXT_OF_KIN_ADDRESS, DIFFERENT_FROM_PATIENT_ADDRESS, ACCEPT, MQE0056),
  NextOfKinAddressIsMissing(NEXT_OF_KIN_ADDRESS, MISSING, ACCEPT, MQE0057),
  NextOfKinAddressIsInvalid(NEXT_OF_KIN_ADDRESS, INVALID, ACCEPT, MQE0564),
  NextOfKinAddressCityIsInvalid(NEXT_OF_KIN_ADDRESS_CITY, INVALID, WARN, MQE0058),
  NextOfKinAddressCityIsMissing(NEXT_OF_KIN_ADDRESS_CITY, MISSING, ACCEPT, MQE0059),
  NextOfKinAddressCountryIsDeprecated(NEXT_OF_KIN_ADDRESS_COUNTRY, DEPRECATED, WARN, MQE0060),
  NextOfKinAddressCountryIsIgnored(NEXT_OF_KIN_ADDRESS_COUNTRY, IGNORED, INFO, MQE0061),
  NextOfKinAddressCountryIsInvalid(NEXT_OF_KIN_ADDRESS_COUNTRY, INVALID, WARN, MQE0062),
  NextOfKinAddressCountryIsMissing(NEXT_OF_KIN_ADDRESS_COUNTRY, MISSING, ACCEPT, MQE0063),
  NextOfKinAddressCountryIsUnrecognized(NEXT_OF_KIN_ADDRESS_COUNTRY, UNRECOGNIZED, WARN, MQE0064),
  NextOfKinAddressCountyIsDeprecated(NEXT_OF_KIN_ADDRESS_COUNTY, DEPRECATED, WARN, MQE0065),
  NextOfKinAddressCountyIsIgnored(NEXT_OF_KIN_ADDRESS_COUNTY, IGNORED, INFO, MQE0066),
  NextOfKinAddressCountyIsInvalid(NEXT_OF_KIN_ADDRESS_COUNTY, INVALID, WARN, MQE0067),
  NextOfKinAddressCountyIsMissing(NEXT_OF_KIN_ADDRESS_COUNTY, MISSING, ACCEPT, MQE0068),
  NextOfKinAddressCountyIsUnrecognized(NEXT_OF_KIN_ADDRESS_COUNTY, UNRECOGNIZED, ACCEPT, MQE0069),
  NextOfKinAddressStateIsDeprecated(NEXT_OF_KIN_ADDRESS_STATE, DEPRECATED, WARN, MQE0070),
  NextOfKinAddressStateIsIgnored(NEXT_OF_KIN_ADDRESS_STATE, IGNORED, INFO, MQE0071),
  NextOfKinAddressStateIsInvalid(NEXT_OF_KIN_ADDRESS_STATE, INVALID, WARN, MQE0072),
  NextOfKinAddressStateIsMissing(NEXT_OF_KIN_ADDRESS_STATE, MISSING, ACCEPT, MQE0073),
  NextOfKinAddressStateIsUnrecognized(NEXT_OF_KIN_ADDRESS_STATE, UNRECOGNIZED, WARN, MQE0074),
  NextOfKinAddressStreetIsMissing(NEXT_OF_KIN_ADDRESS_STREET, MISSING, ACCEPT, MQE0075),
  NextOfKinAddressStreet2IsMissing(NEXT_OF_KIN_ADDRESS_STREET2, MISSING, ACCEPT, MQE0076),
  NextOfKinAddressTypeIsDeprecated(NEXT_OF_KIN_ADDRESS_TYPE, DEPRECATED, WARN, MQE0395),
  NextOfKinAddressTypeIsIgnored(NEXT_OF_KIN_ADDRESS_TYPE, IGNORED, INFO, MQE0396),
  NextOfKinAddressTypeIsInvalid(NEXT_OF_KIN_ADDRESS_TYPE, INVALID, WARN, MQE0397),
  NextOfKinAddressTypeIsMissing(NEXT_OF_KIN_ADDRESS_TYPE, MISSING, ACCEPT, MQE0398),
  NextOfKinAddressTypeIsUnrecognized(NEXT_OF_KIN_ADDRESS_TYPE, UNRECOGNIZED, WARN, MQE0399),
  NextOfKinAddressTypeIsValuedBadAddress(NEXT_OF_KIN_ADDRESS_TYPE, VALUED_BAD_ADDRESS, INFO, MQE0522),
  NextOfKinAddressZipIsInvalid(NEXT_OF_KIN_ADDRESS_ZIP, INVALID, WARN, MQE0077),
  NextOfKinAddressZipIsMissing(NEXT_OF_KIN_ADDRESS_ZIP, MISSING, ACCEPT, MQE0078),
  NextOfKinNameIsMissing(NEXT_OF_KIN_NAME, MISSING, WARN, MQE0079),
  NextOfKinNameFirstIsMissing(NEXT_OF_KIN_NAME_FIRST, MISSING, WARN, MQE0080),
  NextOfKinNameLastIsMissing(NEXT_OF_KIN_NAME_LAST, MISSING, WARN, MQE0081),
  NextOfKinPhoneNumberIsIncomplete(NEXT_OF_KIN_PHONE, INCOMPLETE, WARN, MQE0082),
  NextOfKinPhoneNumberIsInvalid(NEXT_OF_KIN_PHONE, INVALID, WARN, MQE0083),
  NextOfKinPhoneNumberIsMissing(NEXT_OF_KIN_PHONE, MISSING, ACCEPT, MQE0084),
  NextOfKinRelationshipIsDeprecated(NEXT_OF_KIN_RELATIONSHIP, DEPRECATED, WARN, MQE0085),
  NextOfKinRelationshipIsIgnored(NEXT_OF_KIN_RELATIONSHIP, IGNORED, INFO, MQE0086),
  NextOfKinRelationshipIsInvalid(NEXT_OF_KIN_RELATIONSHIP, INVALID, WARN, MQE0087),
  NextOfKinRelationshipIsMissing(NEXT_OF_KIN_RELATIONSHIP, MISSING, WARN, MQE0088),
  NextOfKinRelationshipIsNotResponsibleParty(NEXT_OF_KIN_RELATIONSHIP, NOT_RESPONSIBLE_PARTY, ACCEPT, MQE0089),
  NextOfKinRelationshipIsUnexpected(NEXT_OF_KIN_RELATIONSHIP, UNEXPECTED, WARN, MQE0485),
  NextOfKinRelationshipIsUnrecognized(NEXT_OF_KIN_RELATIONSHIP, UNRECOGNIZED, WARN, MQE0090),
  NextOfKinSsnIsMissing(NEXT_OF_KIN_SSN, MISSING, ACCEPT, MQE0091),
  ObservationValueIsMissing(OBSERVATION_VALUE, MISSING, WARN, MQE0532),
  ObservationValueTypeIsDeprecated(OBSERVATION_VALUE_TYPE, DEPRECATED, WARN, MQE0470),
  ObservationValueTypeIsIgnored(OBSERVATION_VALUE_TYPE, IGNORED, INFO, MQE0471),
  ObservationValueTypeIsInvalid(OBSERVATION_VALUE_TYPE, INVALID, WARN, MQE0472),
  ObservationValueTypeIsMissing(OBSERVATION_VALUE_TYPE, MISSING, WARN, MQE0473),
  ObservationValueTypeIsUnrecognized(OBSERVATION_VALUE_TYPE, UNRECOGNIZED, WARN, MQE0474),
  ObservationObservationIdentifierCodeIsDeprecated(OBSERVATION_IDENTIFIER_CODE, DEPRECATED, WARN, MQE0475),
  ObservationObservationIdentifierCodeIsIgnored(OBSERVATION_IDENTIFIER_CODE, IGNORED, INFO, MQE0476),
  ObservationObservationIdentifierCodeIsInvalid(OBSERVATION_IDENTIFIER_CODE, INVALID, WARN, MQE0477),
  ObservationObservationIdentifierCodeIsMissing(OBSERVATION_IDENTIFIER_CODE, MISSING, WARN, MQE0478),
  ObservationObservationIdentifierCodeIsUnrecognized(OBSERVATION_IDENTIFIER_CODE, UNRECOGNIZED, WARN, MQE0479),
  ObservationObservationValueIsMissing(OBSERVATION_VALUE, MISSING, WARN, MQE0480),
  ObservationDateTimeOfObservationIsMissing(OBSERVATION_DATE_TIME_OF_OBSERVATION, MISSING, INFO, MQE0481),
  ObservationDateTimeOfObservationIsInvalid(OBSERVATION_DATE_TIME_OF_OBSERVATION, INVALID, INFO, MQE0482),
  PatientObjectIsMissing(NONE, MISSING, ACCEPT, MQE0545),
  PatientAddressIsMissing(PATIENT_ADDRESS, MISSING, ACCEPT, MQE0092),
  PatientAddressIsInvalid(PATIENT_ADDRESS, INVALID, WARN, MQE0562),
  PatientAddressCityIsInvalid(PATIENT_ADDRESS_CITY, INVALID, ACCEPT, MQE0093),
  PatientAddressCityIsMissing(PATIENT_ADDRESS_CITY, MISSING, ACCEPT, MQE0094),
  PatientAddressCountryIsDeprecated(PATIENT_ADDRESS_COUNTRY, DEPRECATED, WARN, MQE0095),
  PatientAddressCountryIsIgnored(PATIENT_ADDRESS_COUNTRY, IGNORED, INFO, MQE0096),
  PatientAddressCountryIsInvalid(PATIENT_ADDRESS_COUNTRY, INVALID, WARN, MQE0097),
  PatientAddressCountryIsMissing(PATIENT_ADDRESS_COUNTRY, MISSING, ACCEPT, MQE0098),
  PatientAddressCountryIsUnrecognized(PATIENT_ADDRESS_COUNTRY, UNRECOGNIZED, WARN, MQE0099),
  PatientAddressCountyIsDeprecated(PATIENT_ADDRESS_COUNTY, DEPRECATED, WARN, MQE0100),
  PatientAddressCountyIsIgnored(PATIENT_ADDRESS_COUNTY, IGNORED, INFO, MQE0101),
  PatientAddressCountyIsInvalid(PATIENT_ADDRESS_COUNTY, INVALID, WARN, MQE0102),
  PatientAddressCountyIsMissing(PATIENT_ADDRESS_COUNTY, MISSING, ACCEPT, MQE0103),
  PatientAddressCountyIsUnrecognized(PATIENT_ADDRESS_COUNTY, UNRECOGNIZED, ACCEPT, MQE0104),
  PatientAddressStateIsDeprecated(PATIENT_ADDRESS_STATE, DEPRECATED, WARN, MQE0105),
  PatientAddressStateIsIgnored(PATIENT_ADDRESS_STATE, IGNORED, INFO, MQE0106),
  PatientAddressStateIsInvalid(PATIENT_ADDRESS_STATE, INVALID, ACCEPT, MQE0107),
  PatientAddressStateIsMissing(PATIENT_ADDRESS_STATE, MISSING, ACCEPT, MQE0108),
  PatientAddressStateIsUnrecognized(PATIENT_ADDRESS_STATE, UNRECOGNIZED, ACCEPT, MQE0109),
  PatientAddressStreetIsMissing(PATIENT_ADDRESS_STREET, MISSING, ACCEPT, MQE0110),
  PatientAddressStreet2IsMissing(PATIENT_ADDRESS_STREET2, MISSING, ACCEPT, MQE0111),
  PatientAddressTypeIsMissing(PATIENT_ADDRESS_TYPE, MISSING, ACCEPT, MQE0451),
  PatientAddressTypeIsDeprecated(PATIENT_ADDRESS_TYPE, DEPRECATED, WARN, MQE0517),
  PatientAddressTypeIsIgnored(PATIENT_ADDRESS_TYPE, IGNORED, INFO, MQE0518),
  PatientAddressTypeIsInvalid(PATIENT_ADDRESS_TYPE, INVALID, ACCEPT, MQE0519),
  PatientAddressTypeIsUnrecognized(PATIENT_ADDRESS_TYPE, UNRECOGNIZED, WARN, MQE0520),
  PatientAddressTypeIsValuedBadAddress(PATIENT_ADDRESS_TYPE, VALUED_BAD_ADDRESS, INFO, MQE0521),
  PatientAddressZipIsInvalid(PATIENT_ADDRESS_ZIP, INVALID, WARN, MQE0112),
  PatientAddressZipIsMissing(PATIENT_ADDRESS_ZIP, MISSING, ACCEPT, MQE0113),
  PatientAliasIsMissing(PATIENT_ALIAS, MISSING, ACCEPT, MQE0114),
  PatientBirthDateIsAfterSubmission(PATIENT_BIRTH_DATE, AFTER_SUBMISSION, ERROR, MQE0115),
  PatientBirthDateIsOn15ThDayOfMonth(PATIENT_BIRTH_DATE, ON_FIFTEENTH_DAY_OF_MONTH, ACCEPT, MQE0565),
  PatientBirthDateIsOnFirstDayOfMonth(PATIENT_BIRTH_DATE, ON_FIRST_DAY_OF_MONTH, ACCEPT, MQE0566),
  PatientBirthDateIsOnLastDayOfMonth(PATIENT_BIRTH_DATE, ON_LAST_DAY_OF_MONTH, ACCEPT, MQE0567),
  PatientBirthDateIsInvalid(PATIENT_BIRTH_DATE, INVALID, ERROR, MQE0117),
  PatientBirthDateIsMissing(PATIENT_BIRTH_DATE, MISSING, ERROR, MQE0118),
  PatientBirthDateIsUnderage(PATIENT_BIRTH_DATE, UNDERAGE, ACCEPT, MQE0119),
  PatientBirthDateIsVeryLongAgo(PATIENT_BIRTH_DATE, VERY_LONG_AGO, ERROR, MQE0120),
  PatientBirthIndicatorIsInvalid(PATIENT_BIRTH_INDICATOR, INVALID, WARN, MQE0121),
  PatientBirthIndicatorIsMissing(PATIENT_BIRTH_INDICATOR, MISSING, ACCEPT, MQE0122),
  PatientBirthOrderIsUnknown(PATIENT_BIRTH_ORDER, VALUED_AS_UNKNOWN, WARN, MQE0557),
  PatientBirthOrderIsInvalid(PATIENT_BIRTH_ORDER, INVALID, WARN, MQE0123),
  PatientBirthOrderIsMissing(PATIENT_BIRTH_ORDER, MISSING, ACCEPT, MQE0124),
  PatientBirthOrderIsMultipleAndMultipleBirthIndicatedIsMissing(PATIENT_BIRTH_ORDER, MISSING_AND_MULTIPLE_BIRTH_INDICATED, WARN, MQE0125),
  PatientBirthPlaceIsMissing(PATIENT_BIRTH_PLACE, MISSING, ACCEPT, MQE0126),
  PatientBirthRegistryIdIsInvalid(PATIENT_BIRTH_REGISTRY_ID, INVALID, WARN, MQE0127),
  PatientBirthRegistryIdIsMissing(PATIENT_BIRTH_REGISTRY_ID, MISSING, ACCEPT, MQE0128),
  PatientClassIsDeprecated(PATIENT_CLASS, DEPRECATED, INFO, MQE0374),
  PatientClassIsIgnored(PATIENT_CLASS, IGNORED, INFO, MQE0375),
  PatientClassIsInvalid(PATIENT_CLASS, INVALID, ACCEPT, MQE0376),
  PatientClassIsMissing(PATIENT_CLASS, MISSING, ACCEPT, MQE0377),
  PatientClassIsUnrecognized(PATIENT_CLASS, UNRECOGNIZED, ACCEPT, MQE0378),
  PatientDeathDateIsBeforeBirth(PATIENT_DEATH_DATE, BEFORE_BIRTH, ACCEPT, MQE0129),
  PatientDeathDateIsInFuture(PATIENT_DEATH_DATE, IN_FUTURE, ACCEPT, MQE0130),
  PatientDeathDateIsInvalid(PATIENT_DEATH_DATE, INVALID, ACCEPT, MQE0131),
  PatientDeathDateIsMissing(PATIENT_DEATH_DATE, MISSING, WARN, MQE0132),
  PatientDeathIndicatorIsInconsistent(PATIENT_DEATH_INDICATOR, INCONSISTENT, WARN, MQE0133),
  PatientDeathIndicatorIsMissing(PATIENT_DEATH_INDICATOR, MISSING, ACCEPT, MQE0134),
  PatientEmailIsInvalid(PATIENT_EMAIL, INVALID, ACCEPT, MQE0588),
  PatientEmailIsMissing(PATIENT_EMAIL, MISSING, ACCEPT, MQE0589),
  PatientEthnicityIsDeprecated(PATIENT_ETHNICITY, DEPRECATED, WARN, MQE0135),
  PatientEthnicityIsIgnored(PATIENT_ETHNICITY, IGNORED, INFO, MQE0136),
  PatientEthnicityIsInvalid(PATIENT_ETHNICITY, INVALID, WARN, MQE0137),
  PatientEthnicityIsMissing(PATIENT_ETHNICITY, MISSING, ACCEPT, MQE0138),
  PatientEthnicityIsUnrecognized(PATIENT_ETHNICITY, UNRECOGNIZED, WARN, MQE0139),
  PatientGenderIsDeprecated(PATIENT_GENDER, DEPRECATED, WARN, MQE0143),
  PatientGenderIsIgnored(PATIENT_GENDER, IGNORED, INFO, MQE0144),
  PatientGenderIsInvalid(PATIENT_GENDER, INVALID, ACCEPT, MQE0145),
  PatientGenderIsMissing(PATIENT_GENDER, MISSING, ACCEPT, MQE0146),
  PatientGenderIsUnrecognized(PATIENT_GENDER, UNRECOGNIZED, ACCEPT, MQE0147),
  PatientGuardianAddressIsMissing(PATIENT_GUARDIAN_ADDRESS, MISSING, ACCEPT, MQE0148),
  PatientGuardianAddressIsInvalid(PATIENT_GUARDIAN_ADDRESS, INVALID, ACCEPT, MQE0563),
  PatientGuardianAddressCityIsMissing(PATIENT_GUARDIAN_ADDRESS_CITY, MISSING, ACCEPT, MQE0149),
  PatientGuardianAddressStateIsMissing(PATIENT_GUARDIAN_ADDRESS_STATE, MISSING, ACCEPT, MQE0150),
  PatientGuardianAddressStreetIsMissing(PATIENT_GUARDIAN_ADDRESS_STREET, MISSING, ACCEPT, MQE0151),
  PatientGuardianAddressZipIsMissing(PATIENT_GUARDIAN_ADDRESS_ZIP, MISSING, ACCEPT, MQE0152),
  PatientGuardianNameIsMissing(PATIENT_GUARDIAN_NAME, MISSING, WARN, MQE0155),
  PatientGuardianNameIsSameAsUnderagePatient(PATIENT_GUARDIAN_NAME, SAME_AS_UNDERAGE_PATIENT, ACCEPT, MQE0156),
  PatientGuardianNameFirstIsMissing(PATIENT_GUARDIAN_NAME_FIRST, MISSING, WARN, MQE0153),
  PatientGuardianNameLastIsMissing(PATIENT_GUARDIAN_NAME_LAST, MISSING, WARN, MQE0154),
  PatientGuardianResponsiblePartyIsMissing(PATIENT_GUARDIAN_RESPONSIBLE_PARTY, MISSING, WARN, MQE0157),
  PatientGuardianPhoneIsMissing(PATIENT_GUARDIAN_PHONE, MISSING, ACCEPT, MQE0158),
  PatientGuardianRelationshipIsMissing(PATIENT_GUARDIAN_RELATIONSHIP, MISSING, WARN, MQE0159),
  PatientGuardianAddressTypeIsValuedBadAddress(PATIENT_GUARDIAN_ADDRESS_TYPE, VALUED_BAD_ADDRESS, INFO, MQE0597),
  PatientGuardianAddressTypeIsMissing(PATIENT_GUARDIAN_ADDRESS_TYPE, MISSING, ACCEPT, MQE0598),
  PatientGuardianAddressCountyIsMissing(PATIENT_GUARDIAN_ADDRESS_COUNTY, MISSING, ACCEPT, MQE0608),
  PatientGuardianAddressCountryIsMissing(PATIENT_GUARDIAN_ADDRESS_COUNTRY, MISSING, ACCEPT, MQE0599),
  PatientGuardianAddressTypeIsUnrecognized(PATIENT_GUARDIAN_ADDRESS_TYPE, UNRECOGNIZED, WARN, MQE0600),  
  PatientImmunityCodeIsDeprecated(PATIENT_IMMUNITY_CODE, DEPRECATED, WARN, MQE0606),
  PatientImmunityCodeIsUnrecognized(PATIENT_IMMUNITY_CODE, UNRECOGNIZED, WARN, MQE0607),
  PatientImmunizationRegistryStatusIsDeprecated(PATIENT_IMMUNIZATION_REGISTRY_STATUS, DEPRECATED, WARN, MQE0160),
  PatientImmunizationRegistryStatusIsIgnored(PATIENT_IMMUNIZATION_REGISTRY_STATUS, IGNORED, INFO, MQE0161),
  PatientImmunizationRegistryStatusIsInvalid(PATIENT_IMMUNIZATION_REGISTRY_STATUS, INVALID, WARN, MQE0162),
  PatientImmunizationRegistryStatusIsMissing(PATIENT_IMMUNIZATION_REGISTRY_STATUS, MISSING, ACCEPT, MQE0163),
  PatientImmunizationRegistryStatusIsUnrecognized(PATIENT_IMMUNIZATION_REGISTRY_STATUS, UNRECOGNIZED, WARN, MQE0164),
  PatientMedicaidNumberIsInvalid(PATIENT_MEDICAID_NUMBER, INVALID, WARN, MQE0167),
  PatientMedicaidNumberIsMissing(PATIENT_MEDICAID_NUMBER, MISSING, ACCEPT, MQE0168),
  PatientMiddleNameMayBeInitial(PATIENT_NAME_MIDDLE, MAY_BE_AN_INITIAL, ACCEPT, MQE0170),
  //Some of these weren't represented in the XLS file.
  PatientMotherSMaidenNameIsMissing(PATIENT_MOTHERS_MAIDEN_NAME, MISSING, ACCEPT, MQE0171),
  PatientMothersMaidenNameIsInvalid(PATIENT_MOTHERS_MAIDEN_NAME, INVALID, ACCEPT, MQE0547),
  PatientMotherSMaidenNameHasJunkName(PATIENT_MOTHERS_MAIDEN_NAME, HAS_JUNK_NAME, ACCEPT, MQE0548),
  PatientMotherSMaidenNameHasInvalidPrefixes(PATIENT_MOTHERS_MAIDEN_NAME, INVALID_PREFIXES, ACCEPT, MQE0549),
  PatientMotherSMaidenNameIsTooShort(PATIENT_MOTHERS_MAIDEN_NAME, TOO_SHORT, ACCEPT, MQE0550),

  PatientNameMayBeTemporaryNewbornName(PATIENT_NAME, MAY_BE_TEMPORARY_NEWBORN_NAME, ACCEPT, MQE0172),
  PatientNameMayBeTestName(PATIENT_NAME, MAY_BE_TEST_NAME, ACCEPT, MQE0551),
  PatientNameHasJunkName(PATIENT_NAME, HAS_JUNK_NAME, WARN, MQE0173),
  PatientNameFirstIsInvalid(PATIENT_NAME_FIRST, INVALID, ERROR, MQE0140),
  PatientNameFirstIsMissing(PATIENT_NAME_FIRST, MISSING, ERROR, MQE0141),
  PatientNameFirstMayIncludeMiddleInitial(PATIENT_NAME_FIRST, MAY_INCLUDE_MIDDLE_INITIAL, ACCEPT, MQE0142),
  PatientNameLastIsInvalid(PATIENT_NAME_LAST, INVALID, ERROR, MQE0165),
  PatientNameLastIsMissing(PATIENT_NAME_LAST, MISSING, ERROR, MQE0166),
  PatientNameMiddleIsInvalid(PATIENT_NAME_MIDDLE, INVALID, WARN, MQE0528),
  PatientNameMiddleIsMissing(PATIENT_NAME_MIDDLE, MISSING, WARN, MQE0529),
  PatientNameTypeCodeIsDeprecated(PATIENT_NAME_TYPE_CODE, DEPRECATED, WARN, MQE0405),
  PatientNameTypeCodeIsIgnored(PATIENT_NAME_TYPE_CODE, IGNORED, INFO, MQE0406),
  PatientNameTypeCodeIsInvalid(PATIENT_NAME_TYPE_CODE, INVALID, WARN, MQE0407),
  PatientNameTypeCodeIsMissing(PATIENT_NAME_TYPE_CODE, MISSING, ACCEPT, MQE0408),
  PatientNameTypeCodeIsUnrecognized(PATIENT_NAME_TYPE_CODE, UNRECOGNIZED, WARN, MQE0409),
  PatientNameTypeCodeIsNotValuedLegal(PATIENT_NAME_TYPE_CODE, NOT_VALUED_LEGAL, WARN, MQE0516),
  PatientPhoneIsIncomplete(PATIENT_PHONE, INCOMPLETE, WARN, MQE0174),
  PatientPhoneIsInvalid(PATIENT_PHONE, INVALID, WARN, MQE0175),
  PatientPhoneIsMissing(PATIENT_PHONE, MISSING, ACCEPT, MQE0176),
  PatientPhoneTelUseCodeIsDeprecated(PATIENT_PHONE_TEL_USE_CODE, DEPRECATED, WARN, MQE0453),
  PatientPhoneTelUseCodeIsIgnored(PATIENT_PHONE_TEL_USE_CODE, IGNORED, INFO, MQE0454),
  PatientPhoneTelUseCodeIsInvalid(PATIENT_PHONE_TEL_USE_CODE, INVALID, WARN, MQE0455),
  PatientPhoneTelUseCodeIsMissing(PATIENT_PHONE_TEL_USE_CODE, MISSING, ACCEPT, MQE0456),
  PatientPhoneTelUseCodeIsUnrecognized(PATIENT_PHONE_TEL_USE_CODE, UNRECOGNIZED, WARN, MQE0457),
  PatientPhoneTelEquipCodeIsDeprecated(PATIENT_PHONE_TEL_EQUIP_CODE, DEPRECATED, WARN, MQE0458),
  PatientPhoneTelEquipCodeIsIgnored(PATIENT_PHONE_TEL_EQUIP_CODE, IGNORED, INFO, MQE0459),
  PatientPhoneTelEquipCodeIsInvalid(PATIENT_PHONE_TEL_EQUIP_CODE, INVALID, WARN, MQE0460),
  PatientPhoneTelEquipCodeIsMissing(PATIENT_PHONE_TEL_EQUIP_CODE, MISSING, ACCEPT, MQE0461),
  PatientPhoneTelEquipCodeIsUnrecognized(PATIENT_PHONE_TEL_EQUIP_CODE, UNRECOGNIZED, WARN, MQE0462),
  PatientPrimaryFacilityIdIsDeprecated(PATIENT_PRIMARY_FACILITY_ID, DEPRECATED, WARN, MQE0177),
  PatientPrimaryFacilityIdIsIgnored(PATIENT_PRIMARY_FACILITY_ID, IGNORED, INFO, MQE0178),
  PatientPrimaryFacilityIdIsInvalid(PATIENT_PRIMARY_FACILITY_ID, INVALID, ACCEPT, MQE0179),
  PatientPrimaryFacilityIdIsMissing(PATIENT_PRIMARY_FACILITY_ID, MISSING, ACCEPT, MQE0180),
  PatientPrimaryFacilityIdIsUnrecognized(PATIENT_PRIMARY_FACILITY_ID, UNRECOGNIZED, WARN, MQE0181),
  PatientPrimaryFacilityNameIsMissing(PATIENT_PRIMARY_FACILITY_NAME, MISSING, ACCEPT, MQE0182),
  PatientPrimaryLanguageIsDeprecated(PATIENT_PRIMARY_LANGUAGE, DEPRECATED, ACCEPT, MQE0183),
  PatientPrimaryLanguageIsIgnored(PATIENT_PRIMARY_LANGUAGE, IGNORED, INFO, MQE0184),
  PatientPrimaryLanguageIsInvalid(PATIENT_PRIMARY_LANGUAGE, INVALID, WARN, MQE0185),
  PatientPrimaryLanguageIsMissing(PATIENT_PRIMARY_LANGUAGE, MISSING, ACCEPT, MQE0186),
  PatientPrimaryLanguageIsUnrecognized(PATIENT_PRIMARY_LANGUAGE, UNRECOGNIZED, WARN, MQE0187),
  PatientPrimaryPhysicianIdIsDeprecated(PATIENT_PRIMARY_PHYSICIAN_ID, DEPRECATED, WARN, MQE0188),
  PatientPrimaryPhysicianIdIsIgnored(PATIENT_PRIMARY_PHYSICIAN_ID, IGNORED, INFO, MQE0189),
  PatientPrimaryPhysicianIdIsInvalid(PATIENT_PRIMARY_PHYSICIAN_ID, INVALID, WARN, MQE0190),
  PatientPrimaryPhysicianIdIsMissing(PATIENT_PRIMARY_PHYSICIAN_ID, MISSING, ACCEPT, MQE0191),
  PatientPrimaryPhysicianIdIsUnrecognized(PATIENT_PRIMARY_PHYSICIAN_ID, UNRECOGNIZED, WARN, MQE0192),
  PatientPrimaryPhysicianNameIsMissing(PATIENT_PRIMARY_PHYSICIAN_NAME, MISSING, ACCEPT, MQE0193),
  PatientProtectionIndicatorIsDeprecated(PATIENT_PROTECTION_INDICATOR, DEPRECATED, WARN, MQE0194),
  PatientProtectionIndicatorIsIgnored(PATIENT_PROTECTION_INDICATOR, IGNORED, INFO, MQE0195),
  PatientProtectionIndicatorIsInvalid(PATIENT_PROTECTION_INDICATOR, INVALID, WARN, MQE0196),
  PatientProtectionIndicatorIsMissing(PATIENT_PROTECTION_INDICATOR, MISSING, ACCEPT, MQE0197),
  PatientProtectionIndicatorIsUnrecognized(PATIENT_PROTECTION_INDICATOR, UNRECOGNIZED, WARN, MQE0198),
  PatientProtectionIndicatorIsValuedAsNo(PATIENT_PROTECTION_INDICATOR, VALUED_AS_NO, ACCEPT, MQE0199),
  PatientProtectionIndicatorIsValuedAsYes(PATIENT_PROTECTION_INDICATOR, VALUED_AS_YES, WARN, MQE0200),
  PatientPublicityCodeIsDeprecated(PATIENT_PUBLICITY_CODE, DEPRECATED, WARN, MQE0201),
  PatientPublicityCodeIsIgnored(PATIENT_PUBLICITY_CODE, IGNORED, INFO, MQE0202),
  PatientPublicityCodeIsInvalid(PATIENT_PUBLICITY_CODE, INVALID, WARN, MQE0203),
  PatientPublicityCodeIsMissing(PATIENT_PUBLICITY_CODE, MISSING, ACCEPT, MQE0204),
  PatientPublicityCodeIsUnrecognized(PATIENT_PUBLICITY_CODE, UNRECOGNIZED, WARN, MQE0205),
  PatientRaceIsDeprecated(PATIENT_RACE, DEPRECATED, WARN, MQE0206),
  PatientRaceIsIgnored(PATIENT_RACE, IGNORED, INFO, MQE0207),
  PatientRaceIsInvalid(PATIENT_RACE, INVALID, WARN, MQE0208),
  PatientRaceIsMissing(PATIENT_RACE, MISSING, ACCEPT, MQE0209),
  PatientRaceIsUnrecognized(PATIENT_RACE, UNRECOGNIZED, WARN, MQE0210),
  PatientRegistryIdIsMissing(PATIENT_REGISTRY_ID, MISSING, ACCEPT, MQE0211),
  PatientRegistryIdIsUnrecognized(PATIENT_REGISTRY_ID, UNRECOGNIZED, ACCEPT, MQE0212),
  PatientRegistryStatusIsDeprecated(PATIENT_REGISTRY_STATUS, DEPRECATED, WARN, MQE0213),
  PatientRegistryStatusIsIgnored(PATIENT_REGISTRY_STATUS, IGNORED, INFO, MQE0214),
  PatientRegistryStatusIsInvalid(PATIENT_REGISTRY_STATUS, INVALID, WARN, MQE0215),
  PatientRegistryStatusIsMissing(PATIENT_REGISTRY_STATUS, MISSING, ACCEPT, MQE0216),
  PatientRegistryStatusIsUnrecognized(PATIENT_REGISTRY_STATUS, UNRECOGNIZED, WARN, MQE0217),
  PatientSsnIsInvalid(PATIENT_SSN, INVALID, WARN, MQE0218),
  PatientSsnIsMissing(PATIENT_SSN, MISSING, ACCEPT, MQE0219),
  PatientSubmitterIdIsMissing(PATIENT_SUBMITTER_ID, MISSING, ERROR, MQE0220),
  PatientSubmitterIdAuthorityIsMissing(PATIENT_SUBMITTER_ID_AUTHORITY, MISSING, WARN, MQE0393),
  PatientSubmitterIdTypeCodeIsMissing(PATIENT_SUBMITTER_ID_TYPE_CODE, MISSING, ERROR, MQE0394),
  PatientSubmitterIdTypeCodeIsDeprecated(PATIENT_SUBMITTER_ID_TYPE_CODE, DEPRECATED, WARN, MQE0512),
  PatientSubmitterIdTypeCodeIsInvalid(PATIENT_SUBMITTER_ID_TYPE_CODE, INVALID, ERROR, MQE0513),
  PatientSubmitterIdTypeCodeIsUnrecognized(PATIENT_SUBMITTER_ID_TYPE_CODE, UNRECOGNIZED, WARN, MQE0514),
  PatientSubmitterIdTypeCodeIsIgnored(PATIENT_SUBMITTER_ID_TYPE_CODE, IGNORED, INFO, MQE0515),
  PatientVfcEffectiveDateIsBeforeBirth(PATIENT_VFC_EFFECTIVE_DATE, BEFORE_BIRTH, ACCEPT, MQE0221),
  PatientVfcEffectiveDateIsInFuture(PATIENT_VFC_EFFECTIVE_DATE, IN_FUTURE, ACCEPT, MQE0222),
  PatientVfcEffectiveDateIsInvalid(PATIENT_VFC_EFFECTIVE_DATE, INVALID, ACCEPT, MQE0223),
  PatientVfcEffectiveDateIsMissing(PATIENT_VFC_EFFECTIVE_DATE, MISSING, ACCEPT, MQE0224),
  PatientVfcStatusIsDeprecated(PATIENT_VFC_STATUS, DEPRECATED, WARN, MQE0225),
  PatientVfcStatusIsIgnored(PATIENT_VFC_STATUS, IGNORED, INFO, MQE0226),
  PatientVfcStatusIsInvalid(PATIENT_VFC_STATUS, INVALID, WARN, MQE0227),
  PatientVfcStatusIsMissing(PATIENT_VFC_STATUS, MISSING, ACCEPT, MQE0228),
  PatientVfcStatusIsUnrecognized(PATIENT_VFC_STATUS, UNRECOGNIZED, WARN, MQE0229),
  PatientWicIdIsInvalid(PATIENT_WIC_ID, INVALID, ACCEPT, MQE0230),
  PatientWicIdIsMissing(PATIENT_WIC_ID, MISSING, ACCEPT, MQE0231),
  
  
  PatientCreationIsOnTime(PATIENT_SYSTEM_ENTRY_TIME, IS_ON_TIME, ACCEPT, MQE0575),
  PatientCreationIsLate(PATIENT_SYSTEM_ENTRY_TIME, IS_LATE, ACCEPT, MQE0576),
  PatientCreationIsVeryLate(PATIENT_SYSTEM_ENTRY_TIME, IS_VERY_LATE, ACCEPT, MQE0577),
  PatientCreationIsTooLate(PATIENT_SYSTEM_ENTRY_TIME, IS_TOO_LATE, ACCEPT, MQE0578),
  PatientSystemEntryDateIsMissing(PATIENT_SYSTEM_ENTRY_TIME, MISSING, ACCEPT, MQE0579),
  PatientSystemEntryDateIsInvalid(PATIENT_SYSTEM_ENTRY_TIME, INVALID, ACCEPT, MQE0580),
  PatientSystemEntryDateIsInTheFuture(PATIENT_SYSTEM_ENTRY_TIME, IN_FUTURE, ACCEPT, MQE0582),

  AdministeredVaccinationsCountIsLargerThanExpected(PATIENT_LEVEL, VACCINATION_COUNT_EXCEEDS_EXPECTATIONS, WARN, MQE0568),
  
  VaccinationSystemEntryDateIsMissing(VACCINATION_SYSTEM_ENTRY_TIME, MISSING, ACCEPT, MQE0573),
  VaccinationSystemEntryDateIsInTheFuture(VACCINATION_SYSTEM_ENTRY_TIME, IN_FUTURE, ACCEPT, MQE0581),
  VaccinationSystemEntryDateIsInvalid(VACCINATION_SYSTEM_ENTRY_TIME, INVALID, ACCEPT, MQE0574),
  
  VaccinationCreationIsOnTime(VACCINATION_SYSTEM_ENTRY_TIME, IS_ON_TIME, ACCEPT, MQE0569),
  VaccinationCreationIsLate(VACCINATION_SYSTEM_ENTRY_TIME, IS_LATE, ACCEPT, MQE0570),
  VaccinationCreationIsVeryLate(VACCINATION_SYSTEM_ENTRY_TIME, IS_VERY_LATE, ACCEPT, MQE0571),
  VaccinationCreationIsTooLate(VACCINATION_SYSTEM_ENTRY_TIME, IS_TOO_LATE, ACCEPT, MQE0572),
  
  VaccinationActionCodeIsDeprecated(VACCINATION_ACTION_CODE, DEPRECATED, WARN, MQE0232),
  VaccinationActionCodeIsIgnored(VACCINATION_ACTION_CODE, IGNORED, INFO, MQE0233),
  VaccinationActionCodeIsInvalid(VACCINATION_ACTION_CODE, INVALID, ERROR, MQE0234),
  VaccinationActionCodeIsMissing(VACCINATION_ACTION_CODE, MISSING, ERROR, MQE0235),
  VaccinationActionCodeIsUnrecognized(VACCINATION_ACTION_CODE, UNRECOGNIZED, WARN, MQE0236),
  VaccinationActionCodeIsValuedAsAdd(VACCINATION_ACTION_CODE, VALUED_AS_ADD, ACCEPT, MQE0237),
  VaccinationActionCodeIsValuedAsAddOrUpdate(VACCINATION_ACTION_CODE, VALUED_AS_ADD_OR_UPDATE, ACCEPT, MQE0238),
  VaccinationActionCodeIsValuedAsDelete(VACCINATION_ACTION_CODE, VALUED_AS_DELETE, ACCEPT, MQE0239),
  VaccinationActionCodeIsValuedAsUpdate(VACCINATION_ACTION_CODE, VALUED_AS_UPDATE, ACCEPT, MQE0240),

  VaccinationAdminDateIsAfterLotExpirationDate(VACCINATION_ADMIN_DATE, AFTER_LOT_EXPIRATION, WARN, MQE0251),
  VaccinationAdminDateIsAfterMessageSubmitted(VACCINATION_ADMIN_DATE, AFTER_MESSAGE_SUBMITTED, ERROR, MQE0252),
  VaccinationAdminDateIsAfterPatientDeathDate(VACCINATION_ADMIN_DATE, AFTER_PATIENT_DEATH_DATE, ERROR, MQE0253),
  VaccinationAdminDateIsAfterSystemEntryDate(VACCINATION_ADMIN_DATE, AFTER_SYSTEM_ENTRY_DATE, ERROR, MQE0254),
  VaccinationAdminDateIsBeforeBirth(VACCINATION_ADMIN_DATE, BEFORE_BIRTH, ERROR, MQE0255),
  VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(VACCINATION_ADMIN_DATE, BEFORE_OR_AFTER_EXPECTED_DATE_RANGE, WARN, MQE0256),
  VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(VACCINATION_ADMIN_DATE, BEFORE_OR_AFTER_LICENSED_DATE_RANGE, ERROR, MQE0257),
  VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(VACCINATION_ADMIN_DATE, BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, WARN, MQE0258),
  VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(VACCINATION_ADMIN_DATE, BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, ERROR, MQE0259),
  VaccinationAdminDateIsInvalid(VACCINATION_ADMIN_DATE, INVALID, ERROR, MQE0260),
  VaccinationAdminDateIsMissing(VACCINATION_ADMIN_DATE, MISSING, ERROR, MQE0261),
  VaccinationAdminDateIsOn15ThDayOfMonth(VACCINATION_ADMIN_DATE, ON_FIFTEENTH_DAY_OF_MONTH, ACCEPT, MQE0262),
  VaccinationAdminDateIsOnFirstDayOfMonth(VACCINATION_ADMIN_DATE, ON_FIRST_DAY_OF_MONTH, ACCEPT, MQE0263),
  VaccinationAdminDateIsOnLastDayOfMonth(VACCINATION_ADMIN_DATE, ON_LAST_DAY_OF_MONTH, ACCEPT, MQE0264),
  VaccinationAdminDateIsReportedLate(VACCINATION_ADMIN_DATE, REPORTED_LATE, ACCEPT, MQE0265),
  VaccinationAdminDateEndIsDifferentFromStartDate(VACCINATION_ADMIN_DATE_END, DIFF_FROM_START, WARN, MQE0266),
  VaccinationAdminDateEndIsMissing(VACCINATION_ADMIN_DATE_END, MISSING, ACCEPT, MQE0267),
  VaccinationAdminCodeIsForeign(VACCINATION_ADMIN_CODE, VALUED_AS_FOREIGN, ACCEPT, MQE0268),
  VaccinationHistoricalCodeIsForeign(VACCINATION_ADMIN_CODE, VALUED_AS_FOREIGN, ACCEPT, MQE0553),
  VaccinationAdministeredAmountIsInvalid(VACCINATION_ADMINISTERED_AMOUNT, INVALID, WARN, MQE0555),
  VaccinationAdministeredAmountIsMissing(VACCINATION_ADMINISTERED_AMOUNT, MISSING, ACCEPT, MQE0554),
  VaccinationAdministeredAmountIsValuedAsZero(VACCINATION_ADMINISTERED_AMOUNT, VALUED_AS_ZERO, ACCEPT, MQE0270),
  VaccinationAdministeredAmountIsValuedAsUnknown(VACCINATION_ADMINISTERED_AMOUNT, VALUED_AS_UNKNOWN, ACCEPT, MQE0271),
  VaccinationAdministeredUnitIsDeprecated(VACCINATION_ADMINISTERED_UNIT, DEPRECATED, WARN, MQE0447),
  VaccinationAdministeredUnitIsIgnored(VACCINATION_ADMINISTERED_UNIT, IGNORED, INFO, MQE0448),
  VaccinationAdministeredUnitIsInvalid(VACCINATION_ADMINISTERED_UNIT, INVALID, WARN, MQE0449),
  VaccinationAdministeredUnitIsMissing(VACCINATION_ADMINISTERED_UNIT, MISSING, ACCEPT, MQE0272),
  VaccinationAdministeredUnitIsUnrecognized(VACCINATION_ADMINISTERED_UNIT, UNRECOGNIZED, WARN, MQE0450),
  VaccinationBodyRouteIsDeprecated(VACCINATION_BODY_ROUTE, DEPRECATED, WARN, MQE0273),
  VaccinationBodyRouteIsIgnored(VACCINATION_BODY_ROUTE, IGNORED, INFO, MQE0274),
  VaccinationBodyRouteIsInvalid(VACCINATION_BODY_ROUTE, INVALID, WARN, MQE0275),
  VaccinationBodyRouteIsInvalidForVaccineIndicated(VACCINATION_BODY_ROUTE, INVALID_FOR_VACCINE, WARN, MQE0276),
  VaccinationBodyRouteIsMissing(VACCINATION_BODY_ROUTE, MISSING, ACCEPT, MQE0277),
  VaccinationBodyRouteIsUnrecognized(VACCINATION_BODY_ROUTE, UNRECOGNIZED, WARN, MQE0278),
  VaccinationBodySiteIsDeprecated(VACCINATION_BODY_SITE, DEPRECATED, WARN, MQE0279),
  VaccinationBodySiteIsIgnored(VACCINATION_BODY_SITE, IGNORED, INFO, MQE0280),
  VaccinationBodySiteIsInvalid(VACCINATION_BODY_SITE, INVALID, WARN, MQE0281),
  VaccinationBodySiteIsInvalidForVaccineIndicated(VACCINATION_BODY_SITE, INVALID_FOR_VACCINE, WARN, MQE0282),
  VaccinationBodySiteIsMissing(VACCINATION_BODY_SITE, MISSING, ACCEPT, MQE0283),
  VaccinationBodySiteIsUnrecognized(VACCINATION_BODY_SITE, UNRECOGNIZED, WARN, MQE0284),
  VaccinationCompletionStatusIsDeprecated(VACCINATION_COMPLETION_STATUS, DEPRECATED, WARN, MQE0285),
  VaccinationCompletionStatusIsIgnored(VACCINATION_COMPLETION_STATUS, IGNORED, INFO, MQE0286),
  VaccinationCompletionStatusIsInvalid(VACCINATION_COMPLETION_STATUS, INVALID, ACCEPT, MQE0287),
  VaccinationCompletionStatusIsMissing(VACCINATION_COMPLETION_STATUS, MISSING, ACCEPT, MQE0288),
  VaccinationCompletionStatusIsUnrecognized(VACCINATION_COMPLETION_STATUS, UNRECOGNIZED, ACCEPT, MQE0289),
  VaccinationCompletionStatusIsValuedAsCompleted(VACCINATION_COMPLETION_STATUS, VALUED_AS_COMPLETED, ACCEPT, MQE0290),
  VaccinationCompletionStatusIsValuedAsNotAdministered(VACCINATION_COMPLETION_STATUS, VALUED_AS_NOT_ADMINISTERED, INFO, MQE0291),
  VaccinationCompletionStatusIsValuedAsPartiallyAdministered(VACCINATION_COMPLETION_STATUS, VALUED_AS_PARTIALLY_ADMINISTERED, INFO, MQE0292),
  VaccinationCompletionStatusIsValuedAsRefused(VACCINATION_COMPLETION_STATUS, VALUED_AS_REFUSED, INFO, MQE0293),
  VaccinationConfidentialityCodeIsDeprecated(VACCINATION_CONFIDENTIALITY_CODE, DEPRECATED, WARN, MQE0294),
  VaccinationConfidentialityCodeIsIgnored(VACCINATION_CONFIDENTIALITY_CODE, IGNORED, INFO, MQE0295),
  VaccinationConfidentialityCodeIsInvalid(VACCINATION_CONFIDENTIALITY_CODE, INVALID, WARN, MQE0296),
  VaccinationConfidentialityCodeIsMissing(VACCINATION_CONFIDENTIALITY_CODE, MISSING, ACCEPT, MQE0297),
  VaccinationConfidentialityCodeIsUnrecognized(VACCINATION_CONFIDENTIALITY_CODE, UNRECOGNIZED, WARN, MQE0298),
  VaccinationConfidentialityCodeIsValuedAsRestricted(VACCINATION_CONFIDENTIALITY_CODE, VALUED_AS_RESTRICTED, WARN, MQE0299),

  VaccinationAdminCodeIsDeprecated(VACCINATION_ADMIN_CODE, DEPRECATED, WARN, MQE0241),
  VaccinationAdminCodeIsIgnored(VACCINATION_ADMIN_CODE, IGNORED, INFO, MQE0242),
  VaccinationAdminCodeIsInvalid(VACCINATION_ADMIN_CODE, INVALID, ERROR, MQE0243),
  VaccinationAdminCodeIsInvalidForDateAdministered(VACCINATION_ADMIN_CODE, INVALID_FOR_DATE_ADMINISTERED, ERROR, MQE0491),
  VaccinationAdminCodeIsMissing(VACCINATION_ADMIN_CODE, MISSING, ERROR, MQE0244),
  VaccinationAdminCodeIsNotSpecific(VACCINATION_ADMIN_CODE, NOT_SPECIFIC, WARN, MQE0245),
  VaccinationAdminCodeIsNotVaccine(VACCINATION_ADMIN_CODE, NOT_VACCINE, ACCEPT, MQE0246),
  VaccinationAdminCodeIsUnexpectedForDateAdministered(VACCINATION_ADMIN_CODE, UNEXPECTED_FOR_DATE_ADMINISTERED, WARN, MQE0490),
  VaccinationAdminCodeIsUnrecognized(VACCINATION_ADMIN_CODE, UNRECOGNIZED, ERROR, MQE0247),
  VaccinationAdminCodeIsNotUsable(VACCINATION_ADMIN_CODE, NOT_USABLE, ERROR, MQE0561),
  VaccinationAdminCodeIsValuedAsNotAdministered(VACCINATION_ADMIN_CODE, VALUED_AS_NOT_ADMINISTERED, ACCEPT, MQE0248),
  VaccinationAdminCodeIsValuedAsUnknown(VACCINATION_ADMIN_CODE, VALUED_AS_UNKNOWN, WARN, MQE0249),
  VaccinationAdminCodeTableIsMissing(VACCINATION_ADMIN_CODE_TABLE, MISSING, ERROR, MQE0483),
  VaccinationAdminCodeTableIsInvalid(VACCINATION_ADMIN_CODE_TABLE, INVALID, ERROR, MQE0484),
  VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(VACCINATION_ADMIN_CODE, MAY_BE_PREVIOUSLY_REPORTED, WARN, MQE0250),

  VaccinationCptCodeIsDeprecated(VACCINATION_CPT_CODE, DEPRECATED, WARN, MQE0300),
  VaccinationCptCodeIsIgnored(VACCINATION_CPT_CODE, IGNORED, INFO, MQE0301),
  VaccinationCptCodeIsInvalid(VACCINATION_CPT_CODE, INVALID, WARN, MQE0302),
  VaccinationCptCodeIsInvalidForDateAdministered(VACCINATION_CPT_CODE, INVALID_FOR_DATE_ADMINISTERED, WARN, MQE0489),
  VaccinationCptCodeIsMissing(VACCINATION_CPT_CODE, MISSING, ACCEPT, MQE0303),
  VaccinationCptCodeIsUnexpectedForDateAdministered(VACCINATION_CPT_CODE, UNEXPECTED_FOR_DATE_ADMINISTERED, ACCEPT, MQE0488),
  VaccinationCptCodeIsUnrecognized(VACCINATION_CPT_CODE, UNRECOGNIZED, WARN, MQE0304),

  VaccinationCvxCodeIsDeprecated(VACCINATION_CVX_CODE, DEPRECATED, WARN, MQE0305),
  VaccinationCvxCodeIsIgnored(VACCINATION_CVX_CODE, IGNORED, INFO, MQE0306),
  VaccinationCvxCodeIsInvalid(VACCINATION_CVX_CODE, INVALID, WARN, MQE0307),
  VaccinationCvxCodeIsInvalidForDateAdministered(VACCINATION_CVX_CODE, INVALID_FOR_DATE_ADMINISTERED, WARN, MQE0487),
  VaccinationCvxCodeIsMissing(VACCINATION_CVX_CODE, MISSING, ACCEPT, MQE0308),
  VaccinationCvxCodeIsUnexpectedForDateAdministered(VACCINATION_CVX_CODE, UNEXPECTED_FOR_DATE_ADMINISTERED, ACCEPT, MQE0486),
  VaccinationCvxCodeIsUnrecognized(VACCINATION_CVX_CODE, UNRECOGNIZED, WARN, MQE0309),
  VaccinationCvxCodeAndCptCodeAreInconsistent(VACCINATION_CVX_CODE_AND_CPT_CODE, ARE_INCONSISTENT, WARN, MQE0310),

  VaccinationNDCCodeIsUnrecognized(VACCINATION_NDC_CODE, UNRECOGNIZED, ERROR, MQE0559),
  VaccinationNDCCodeIsMissing(VACCINATION_NDC_CODE, MISSING, ACCEPT, MQE0560),

  VaccinationFacilityIdIsDeprecated(VACCINATION_FACILITY_ID, DEPRECATED, WARN, MQE0311),
  VaccinationFacilityIdIsIgnored(VACCINATION_FACILITY_ID, IGNORED, INFO, MQE0312),
  VaccinationFacilityIdIsInvalid(VACCINATION_FACILITY_ID, INVALID, ACCEPT, MQE0313),
  VaccinationFacilityIdIsMissing(VACCINATION_FACILITY_ID, MISSING, ACCEPT, MQE0314),
  VaccinationFacilityIdIsUnrecognized(VACCINATION_FACILITY_ID, UNRECOGNIZED, ACCEPT, MQE0315),
  VaccinationFacilityNameIsMissing(VACCINATION_FACILITY_NAME, MISSING, ACCEPT, MQE0316),

  VaccinationFillerOrderNumberIsDeprecated(VACCINATION_FILLER_ORDER_NUMBER, DEPRECATED, WARN, MQE0379),
  VaccinationFillerOrderNumberIsIgnored(VACCINATION_FILLER_ORDER_NUMBER, IGNORED, INFO, MQE0380),
  VaccinationFillerOrderNumberIsInvalid(VACCINATION_FILLER_ORDER_NUMBER, INVALID, WARN, MQE0381),
  VaccinationFillerOrderNumberIsMissing(VACCINATION_FILLER_ORDER_NUMBER, MISSING, ACCEPT, MQE0382),
  VaccinationFillerOrderNumberIsUnrecognized(VACCINATION_FILLER_ORDER_NUMBER, UNRECOGNIZED, WARN, MQE0383),

  VaccinationFinancialEligibilityCodeIsDeprecated(VACCINATION_FINANCIAL_ELIGIBILITY_CODE, DEPRECATED, WARN, MQE0465),
  VaccinationFinancialEligibilityCodeIsIgnored(VACCINATION_FINANCIAL_ELIGIBILITY_CODE, IGNORED, INFO, MQE0466),
  VaccinationFinancialEligibilityCodeIsInvalid(VACCINATION_FINANCIAL_ELIGIBILITY_CODE, INVALID, ACCEPT, MQE0467),
  VaccinationFinancialEligibilityCodeIsMissing(VACCINATION_FINANCIAL_ELIGIBILITY_CODE, MISSING, ACCEPT, MQE0468),
  VaccinationFinancialEligibilityCodeIsUnrecognized(VACCINATION_FINANCIAL_ELIGIBILITY_CODE, UNRECOGNIZED, ACCEPT, MQE0469),

  VaccinationFundingSourceCodeIsDeprecated(VACCINATION_FUNDING_SOURCE_CODE, DEPRECATED, WARN, MQE0583),
  VaccinationFundingSourceCodeIsIgnored(VACCINATION_FUNDING_SOURCE_CODE, IGNORED, INFO, MQE0584),
  VaccinationFundingSourceCodeIsInvalid(VACCINATION_FUNDING_SOURCE_CODE, INVALID, ACCEPT, MQE0585),
  VaccinationFundingSourceCodeIsMissing(VACCINATION_FUNDING_SOURCE_CODE, MISSING, ACCEPT, MQE0586),
  VaccinationFundingSourceCodeIsUnrecognized(VACCINATION_FUNDING_SOURCE_CODE, UNRECOGNIZED, ACCEPT, MQE0587),
  VaccinationFundingSourceCodeIsUnexpectedForFinancialEligibility(VACCINATION_FUNDING_SOURCE_CODE, UNEXPECTED_FOR_FINANCIAL_ELIGIBILITY, WARN, MQE0596),
  

  VaccinationGivenByIsDeprecated(VACCINATION_GIVEN_BY, DEPRECATED, WARN, MQE0317),
  VaccinationGivenByIsIgnored(VACCINATION_GIVEN_BY, IGNORED, INFO, MQE0318),
  VaccinationGivenByIsInvalid(VACCINATION_GIVEN_BY, INVALID, WARN, MQE0319),
  VaccinationGivenByIsMissing(VACCINATION_GIVEN_BY, MISSING, ACCEPT, MQE0320),
  VaccinationGivenByIsUnrecognized(VACCINATION_GIVEN_BY, UNRECOGNIZED, WARN, MQE0321),
  VaccinationIdIsMissing(VACCINATION_ID, MISSING, ACCEPT, MQE0322),
  VaccinationIdOfReceiverIsMissing(VACCINATION_ID_OF_RECEIVER, MISSING, ACCEPT, MQE0323),
  VaccinationIdOfReceiverIsUnrecognized(VACCINATION_ID_OF_RECEIVER, UNRECOGNIZED, WARN, MQE0324),
  VaccinationIdOfSenderIsMissing(VACCINATION_ID_OF_SENDER, MISSING, ACCEPT, MQE0325),
  VaccinationIdOfSenderIsUnrecognized(VACCINATION_ID_OF_SENDER, UNRECOGNIZED, WARN, MQE0326),

  VaccinationInformationSourceIsAdministeredButAppearsToHistorical(VACCINATION_INFORMATION_SOURCE, ADMINISTERED_BUT_APPEARS_TO_HISTORICAL, WARN, MQE0327),
  VaccinationInformationSourceIsDeprecated(VACCINATION_INFORMATION_SOURCE, DEPRECATED, WARN, MQE0328),
  VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(VACCINATION_INFORMATION_SOURCE, HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED, WARN, MQE0329),
  VaccinationInformationSourceIsIgnored(VACCINATION_INFORMATION_SOURCE, IGNORED, INFO, MQE0330),
  VaccinationInformationSourceIsInvalid(VACCINATION_INFORMATION_SOURCE, INVALID, ACCEPT, MQE0331),
  VaccinationInformationSourceIsMissing(VACCINATION_INFORMATION_SOURCE, MISSING, ACCEPT, MQE0332),
  VaccinationInformationSourceIsUnrecognized(VACCINATION_INFORMATION_SOURCE, UNRECOGNIZED, ACCEPT, MQE0333),
  VaccinationInformationSourceIsValuedAsAdministered(VACCINATION_INFORMATION_SOURCE, VALUED_AS_ADMINISTERED, ACCEPT, MQE0334),
  VaccinationInformationSourceIsValuedAsHistorical(VACCINATION_INFORMATION_SOURCE, VALUED_AS_HISTORICAL, ACCEPT, MQE0335),

  VaccinationVisCvxIsDeprecated(VACCINATION_VIS_CVX_CODE, DEPRECATED, INFO, MQE0601),
  VaccinationVisCvxIsIgnored(VACCINATION_VIS_CVX_CODE, IGNORED, INFO, MQE0602),
  VaccinationVisCvxIsInvalid(VACCINATION_VIS_CVX_CODE, INVALID, ACCEPT, MQE0603),
  VaccinationVisCvxIsUnrecognized(VACCINATION_VIS_CVX_CODE, UNRECOGNIZED, ACCEPT, MQE0604),
  VaccinationVisCvxIsMissing(VACCINATION_VIS_CVX_CODE, MISSING, ACCEPT, MQE0605),
  
  VaccinationVisDocumentTypeIsDeprecated(VACCINATION_VIS_DOCUMENT_TYPE, DEPRECATED, INFO, MQE0496),
  VaccinationVisDocumentTypeIsIgnored(VACCINATION_VIS_DOCUMENT_TYPE, IGNORED, INFO, MQE0497),
  VaccinationVisDocumentTypeIsIncorrect(VACCINATION_VIS_DOCUMENT_TYPE, INCORRECT, INFO, MQE0498),
  VaccinationVisDocumentTypeIsInvalid(VACCINATION_VIS_DOCUMENT_TYPE, INVALID, INFO, MQE0499),
  VaccinationVisDocumentTypeIsMissing(VACCINATION_VIS_DOCUMENT_TYPE, MISSING, INFO, MQE0500),
  VaccinationVisDocumentTypeIsUnrecognized(VACCINATION_VIS_DOCUMENT_TYPE, UNRECOGNIZED, INFO, MQE0501),
  VaccinationVisDocumentTypeIsOutOfDate(VACCINATION_VIS_DOCUMENT_TYPE, OUT_OF_DATE, INFO, MQE0502),
  VaccinationVisVersionDateIsInvalid(VACCINATION_VIS_VERSION_DATE, INVALID, INFO, MQE0503),
  VaccinationVisVersionDateIsMissing(VACCINATION_VIS_VERSION_DATE, MISSING, INFO, MQE0504),
  VaccinationVisVersionDateIsUnrecognized(VACCINATION_VIS_VERSION_DATE, UNRECOGNIZED, INFO, MQE0505),
  VaccinationVisVersionDateIsInFuture(VACCINATION_VIS_VERSION_DATE, IN_FUTURE, INFO, MQE0506),
  VaccinationVisDeliveryDateIsInvalid(VACCINATION_VIS_DELIVERY_DATE, INVALID, INFO, MQE0507),
  VaccinationVisDeliveryDateIsMissing(VACCINATION_VIS_DELIVERY_DATE, MISSING, INFO, MQE0508),
  VaccinationVisDeliveryDateIsNotAdminDate(VACCINATION_VIS_DELIVERY_DATE, NOT_SAME_AS_ADMIN_DATE, INFO, MQE0509),
  VaccinationVisDeliveryDateIsBeforeVersionDate(VACCINATION_VIS_DELIVERY_DATE, BEFORE_VERSION_DATE, INFO, MQE0510),
  VaccinationVisDeliveryDateIsAfterAdminDate(VACCINATION_VIS_DELIVERY_DATE, AFTER_ADMIN_DATE, INFO, MQE0511),

  //More that were missing.
  VaccinationVisPublishedDateIsMissing(VACCINATION_VIS_PUBLISHED_DATE, MISSING, INFO, MQE0534),
  VaccinationVisPublishedDateIsUnrecognized(VACCINATION_VIS_PUBLISHED_DATE, UNRECOGNIZED, INFO, MQE0535),
  VaccinationVisPublishedDateIsInFuture(VACCINATION_VIS_PUBLISHED_DATE, IN_FUTURE, INFO, MQE0536),
  VaccinationVisPresentedDateIsInvalid(VACCINATION_VIS_PRESENTED_DATE, INVALID, INFO, MQE0537),
  VaccinationVisPresentedDateIsMissing(VACCINATION_VIS_PRESENTED_DATE, MISSING, INFO, MQE0538),
  VaccinationVisPresentedDateIsNotAdminDate(VACCINATION_VIS_PRESENTED_DATE, NOT_SAME_AS_ADMIN_DATE, INFO, MQE0539),
  VaccinationVisPresentedDateIsBeforePublishedDate(VACCINATION_VIS_PRESENTED_DATE, BEFORE_PUBLISHED_DATE, INFO, MQE0540),
  VaccinationVisPresentedDateIsAfterAdminDate(VACCINATION_VIS_PRESENTED_DATE, AFTER_ADMIN_DATE, INFO, MQE0541),
  VaccinationVisIsMissing(VACCINATION_VIS, MISSING, INFO, MQE0542),
  VaccinationVisIsUnrecognized(VACCINATION_VIS, UNRECOGNIZED, INFO, MQE0543),
  VaccinationVisPublishedDateIsInvalid(VACCINATION_VIS_PUBLISHED_DATE, INVALID, INFO, MQE0544),

  VaccinationLotExpirationDateIsInvalid(VACCINATION_LOT_EXPIRATION_DATE, INVALID, WARN, MQE0336),
  VaccinationLotExpirationDateIsMissing(VACCINATION_LOT_EXPIRATION_DATE, MISSING, ACCEPT, MQE0337),
  VaccinationLotNumberFormatIsUnrecognized(VACCINATION_LOT_NUMBER, UNRECOGNIZED, WARN, MQE0590),
  VaccinationLotNumberHasMultiple(VACCINATION_LOT_NUMBER, MUTLIPLES, WARN, MQE0591),
  VaccinationLotNumberHasInvalidPrefixes(VACCINATION_LOT_NUMBER, INVALID_PREFIXES, WARN, MQE0592),
  VaccinationLotNumberHasInvalidSuffixes(VACCINATION_LOT_NUMBER, INVALID_SUFFIXES, WARN, MQE0593),
  VaccinationLotNumberHasInvalidInfixes(VACCINATION_LOT_NUMBER, INVALID_INFIXES, WARN, MQE0594),
  VaccinationLotNumberIsTooShort(VACCINATION_LOT_NUMBER, TOO_SHORT, WARN, MQE0595),
  VaccinationLotNumberIsInvalid(VACCINATION_LOT_NUMBER, INVALID, WARN, MQE0338),
  VaccinationLotNumberIsMissing(VACCINATION_LOT_NUMBER, MISSING, WARN, MQE0339),
  VaccinationManufacturerCodeIsDeprecated(VACCINATION_MANUFACTURER_CODE, DEPRECATED, WARN, MQE0340),
  VaccinationManufacturerCodeIsIgnored(VACCINATION_MANUFACTURER_CODE, IGNORED, INFO, MQE0341),
  VaccinationManufacturerCodeIsInvalid(VACCINATION_MANUFACTURER_CODE, INVALID, WARN, MQE0342),
  VaccinationManufacturerCodeIsInvalidForDateAdministered(VACCINATION_MANUFACTURER_CODE, INVALID_FOR_DATE_ADMINISTERED, WARN, MQE0495),
  VaccinationManufacturerCodeIsMissing(VACCINATION_MANUFACTURER_CODE, MISSING, WARN, MQE0343),
  VaccinationManufacturerCodeIsUnexpectedForDateAdministered(VACCINATION_MANUFACTURER_CODE, UNEXPECTED_FOR_DATE_ADMINISTERED, WARN, MQE0494),
  VaccinationManufacturerCodeIsUnrecognized(VACCINATION_MANUFACTURER_CODE, UNRECOGNIZED, WARN, MQE0344),
  VaccinationOrderControlCodeIsDeprecated(VACCINATION_ORDER_CONTROL_CODE, DEPRECATED, WARN, MQE0373),
  VaccinationOrderControlCodeIsIgnored(VACCINATION_ORDER_CONTROL_CODE, IGNORED, INFO, MQE0369),
  VaccinationOrderControlCodeIsInvalid(VACCINATION_ORDER_CONTROL_CODE, INVALID, WARN, MQE0370),
  VaccinationOrderControlCodeIsMissing(VACCINATION_ORDER_CONTROL_CODE, MISSING, ACCEPT, MQE0371),
  VaccinationOrderControlCodeIsUnrecognized(VACCINATION_ORDER_CONTROL_CODE, UNRECOGNIZED, WARN, MQE0372),
  VaccinationOrderFacilityIdIsDeprecated(VACCINATION_ORDER_FACILITY_ID, DEPRECATED, ACCEPT, MQE0442),
  VaccinationOrderFacilityIdIsIgnored(VACCINATION_ORDER_FACILITY_ID, IGNORED, INFO, MQE0443),
  VaccinationOrderFacilityIdIsInvalid(VACCINATION_ORDER_FACILITY_ID, INVALID, WARN, MQE0444),
  VaccinationOrderFacilityIdIsMissing(VACCINATION_ORDER_FACILITY_ID, MISSING, ACCEPT, MQE0445),
  VaccinationOrderFacilityIdIsUnrecognized(VACCINATION_ORDER_FACILITY_ID, UNRECOGNIZED, WARN, MQE0446),
  VaccinationOrderFacilityNameIsMissing(VACCINATION_ORDER_FACILITY_NAME, MISSING, ACCEPT, MQE0441),
  VaccinationOrderedByIsDeprecated(VACCINATION_ORDERED_BY, DEPRECATED, WARN, MQE0345),
  VaccinationOrderedByIsIgnored(VACCINATION_ORDERED_BY, IGNORED, INFO, MQE0346),
  VaccinationOrderedByIsInvalid(VACCINATION_ORDERED_BY, INVALID, WARN, MQE0347),
  VaccinationOrderedByIsMissing(VACCINATION_ORDERED_BY, MISSING, ACCEPT, MQE0348),
  VaccinationOrderedByIsUnrecognized(VACCINATION_ORDERED_BY, UNRECOGNIZED, WARN, MQE0349),
  VaccinationPlacerOrderNumberIsDeprecated(VACCINATION_PLACER_ORDER_NUMBER, DEPRECATED, WARN, MQE0384),
  VaccinationPlacerOrderNumberIsIgnored(VACCINATION_PLACER_ORDER_NUMBER, IGNORED, INFO, MQE0385),
  VaccinationPlacerOrderNumberIsInvalid(VACCINATION_PLACER_ORDER_NUMBER, INVALID, WARN, MQE0386),
  VaccinationPlacerOrderNumberIsMissing(VACCINATION_PLACER_ORDER_NUMBER, MISSING, ACCEPT, MQE0387),
  VaccinationPlacerOrderNumberIsUnrecognized(VACCINATION_PLACER_ORDER_NUMBER, UNRECOGNIZED, WARN, MQE0388),
  VaccinationProductIsDeprecated(VACCINATION_PRODUCT, DEPRECATED, WARN, MQE0350),
  VaccinationProductIsInvalid(VACCINATION_PRODUCT, INVALID, ACCEPT, MQE0351),
  VaccinationProductIsInvalidForDateAdministered(VACCINATION_PRODUCT, INVALID_FOR_DATE_ADMINISTERED, ACCEPT, MQE0493),
  VaccinationProductIsMissing(VACCINATION_PRODUCT, MISSING, ACCEPT, MQE0352),
  VaccinationProductIsUnexpectedForDateAdministered(VACCINATION_PRODUCT, UNEXPECTED_FOR_DATE_ADMINISTERED, ACCEPT, MQE0492),
  VaccinationProductIsUnrecognized(VACCINATION_PRODUCT, UNRECOGNIZED, WARN, MQE0353),
  VaccinationRecordedByIsDeprecated(VACCINATION_RECORDED_BY, DEPRECATED, WARN, MQE0354),
  VaccinationRecordedByIsIgnored(VACCINATION_RECORDED_BY, IGNORED, INFO, MQE0355),
  VaccinationRecordedByIsInvalid(VACCINATION_RECORDED_BY, INVALID, WARN, MQE0356),
  VaccinationRecordedByIsMissing(VACCINATION_RECORDED_BY, MISSING, ACCEPT, MQE0357),
  VaccinationRecordedByIsUnrecognized(VACCINATION_RECORDED_BY, UNRECOGNIZED, WARN, MQE0358),
  VaccinationRefusalReasonConflictsCompletionStatus(VACCINATION_REFUSAL_REASON, CONFLICTS_COMPLETION_STATUS, ACCEPT, MQE0359),
  VaccinationRefusalReasonIsDeprecated(VACCINATION_REFUSAL_REASON, DEPRECATED, WARN, MQE0360),
  VaccinationRefusalReasonIsIgnored(VACCINATION_REFUSAL_REASON, IGNORED, INFO, MQE0361),
  VaccinationRefusalReasonIsInvalid(VACCINATION_REFUSAL_REASON, INVALID, ACCEPT, MQE0362),
  VaccinationRefusalReasonIsMissing(VACCINATION_REFUSAL_REASON, MISSING, ACCEPT, MQE0363),
  VaccinationRefusalReasonIsUnrecognized(VACCINATION_REFUSAL_REASON, UNRECOGNIZED, ACCEPT, MQE0364),
  
  
  // Present test cases
  MessageAcceptAckTypeIsPresent(MESSAGE_ACCEPT_ACK_TYPE, PRESENT, ACCEPT, MQE0609),
  MessageAppAckTypeIsPresent(MESSAGE_APP_ACK_TYPE, PRESENT, ACCEPT, MQE0610),
  MessageMessageControlIdIsPresent(MESSAGE_CONTROL_ID, PRESENT, ACCEPT, MQE0611),
  MessageMessageDateIsPresent(MESSAGE_DATE, PRESENT, ACCEPT, MQE0612),
  MessageMessageDateTimezoneIsPresent(MESSAGE_DATE, PRESENT, ACCEPT, MQE0613),
  MessageMessageProfileIdIsPresent(MESSAGE_PROFILE_ID, PRESENT, ACCEPT, MQE0614),
  MessageMessageTriggerIsPresent(MESSAGE_TRIGGER, PRESENT, ACCEPT, MQE0615),
  MessageMessageTypeIsPresent(MESSAGE_TYPE, PRESENT, ACCEPT, MQE0616),
  MessageProcessingIdIsPresent(MESSAGE_PROCESSING_ID, PRESENT, ACCEPT, MQE0617),
  MessageReceivingApplicationIsPresent(MESSAGE_RECEIVING_APPLICATION, PRESENT, ACCEPT, MQE0618),
  MessageReceivingFacilityIsPresent(MESSAGE_RECEIVING_FACILITY, PRESENT, ACCEPT, MQE0619),
  MessageSendingApplicationIsPresent(MESSAGE_SENDING_APPLICATION, PRESENT, ACCEPT, MQE0620),
  MessageSendingFacilityIsPresent(MESSAGE_SENDING_FACILITY, PRESENT, ACCEPT, MQE0621),
  MessageSendingResponsibleOrganizationIsPresent(MESSAGE_SENDING_RESPONSIBLE_ORGANIZATION, PRESENT, ACCEPT, MQE0622),
  MessageVersionIsPresent(MESSAGE_VERSION, PRESENT, ACCEPT, MQE0623),
  NextOfKinAddressCityIsPresent(NEXT_OF_KIN_ADDRESS_CITY, PRESENT, ACCEPT, MQE0624),
  NextOfKinAddressCountryIsPresent(NEXT_OF_KIN_ADDRESS_COUNTRY, PRESENT, ACCEPT, MQE0625),
  NextOfKinAddressCountyIsPresent(NEXT_OF_KIN_ADDRESS_COUNTY, PRESENT, ACCEPT, MQE0626),
  NextOfKinAddressIsPresent(NEXT_OF_KIN_ADDRESS, PRESENT, ACCEPT, MQE0627),
  NextOfKinAddressStateIsPresent(NEXT_OF_KIN_ADDRESS_STATE, PRESENT, ACCEPT, MQE0628),
  NextOfKinAddressStreet2IsPresent(NEXT_OF_KIN_ADDRESS_STREET2, PRESENT, ACCEPT, MQE0629),
  NextOfKinAddressStreetIsPresent(NEXT_OF_KIN_ADDRESS_STREET, PRESENT, ACCEPT, MQE0630),
  NextOfKinAddressTypeIsPresent(NEXT_OF_KIN_ADDRESS_TYPE, PRESENT, ACCEPT, MQE0631),
  NextOfKinAddressZipIsPresent(NEXT_OF_KIN_ADDRESS_ZIP, PRESENT, ACCEPT, MQE0632),
  NextOfKinNameFirstIsPresent(NEXT_OF_KIN_NAME_FIRST, PRESENT, ACCEPT, MQE0633),
  NextOfKinNameIsPresent(NEXT_OF_KIN_NAME, PRESENT, ACCEPT, MQE0634),
  NextOfKinNameLastIsPresent(NEXT_OF_KIN_NAME_LAST, PRESENT, ACCEPT, MQE0635),
  NextOfKinPhoneNumberIsPresent(NEXT_OF_KIN_PHONE, PRESENT, ACCEPT, MQE0636),
  NextOfKinRelationshipIsPresent(NEXT_OF_KIN_RELATIONSHIP, PRESENT, ACCEPT, MQE0637),
  NextOfKinSsnIsPresent(NEXT_OF_KIN_SSN, PRESENT, ACCEPT, MQE0638),
  ObservationDateTimeOfObservationIsPresent(OBSERVATION_DATE_TIME_OF_OBSERVATION, PRESENT, ACCEPT, MQE0639),
  ObservationObservationIdentifierCodeIsPresent(OBSERVATION_IDENTIFIER_CODE, PRESENT, ACCEPT, MQE0640),
  ObservationObservationValueIsPresent(OBSERVATION_VALUE, PRESENT, ACCEPT, MQE0641),
  ObservationValueIsPresent(OBSERVATION_VALUE, PRESENT, ACCEPT, MQE0642),
  ObservationValueTypeIsPresent(OBSERVATION_VALUE_TYPE, PRESENT, ACCEPT, MQE0643),
  PatientAddressCityIsPresent(PATIENT_ADDRESS_CITY, PRESENT, ACCEPT, MQE0644),
  PatientAddressCountryIsPresent(PATIENT_ADDRESS_COUNTRY, PRESENT, ACCEPT, MQE0645),
  PatientAddressCountyIsPresent(PATIENT_ADDRESS_COUNTY, PRESENT, ACCEPT, MQE0646),
  PatientAddressIsPresent(PATIENT_ADDRESS, PRESENT, ACCEPT, MQE0647),
  PatientAddressStateIsPresent(PATIENT_ADDRESS_STATE, PRESENT, ACCEPT, MQE0648),
  PatientAddressStreet2IsPresent(PATIENT_ADDRESS_STREET2, PRESENT, ACCEPT, MQE0649),
  PatientAddressStreetIsPresent(PATIENT_ADDRESS_STREET, PRESENT, ACCEPT, MQE0650),
  PatientAddressTypeIsPresent(PATIENT_ADDRESS_TYPE, PRESENT, ACCEPT, MQE0651),
  PatientAddressZipIsPresent(PATIENT_ADDRESS_ZIP, PRESENT, ACCEPT, MQE0652),
  PatientAliasIsPresent(PATIENT_ALIAS, PRESENT, ACCEPT, MQE0653),
  PatientBirthDateIsPresent(PATIENT_BIRTH_DATE, PRESENT, ACCEPT, MQE0654),
  PatientBirthIndicatorIsPresent(PATIENT_BIRTH_INDICATOR, PRESENT, ACCEPT, MQE0655),
  PatientBirthOrderIsPresent(PATIENT_BIRTH_ORDER, PRESENT, ACCEPT, MQE0656),
  PatientBirthOrderIsMultipleAndMultipleBirthIndicatedIsPresent(PATIENT_BIRTH_ORDER, PRESENT, ACCEPT, MQE0657),
  PatientBirthPlaceIsPresent(PATIENT_BIRTH_PLACE, PRESENT, ACCEPT, MQE0658),
  PatientBirthRegistryIdIsPresent(PATIENT_BIRTH_REGISTRY_ID, PRESENT, ACCEPT, MQE0659),
  PatientClassIsPresent(PATIENT_CLASS, PRESENT, ACCEPT, MQE0660),
  PatientDeathDateIsPresent(PATIENT_DEATH_DATE, PRESENT, ACCEPT, MQE0661),
  PatientDeathIndicatorIsPresent(PATIENT_DEATH_INDICATOR, PRESENT, ACCEPT, MQE0662),
  PatientEmailIsPresent(PATIENT_EMAIL, PRESENT, ACCEPT, MQE0663),
  PatientEthnicityIsPresent(PATIENT_ETHNICITY, PRESENT, ACCEPT, MQE0664),
  PatientGenderIsPresent(PATIENT_GENDER, PRESENT, ACCEPT, MQE0665),
  PatientGuardianAddressCityIsPresent(PATIENT_GUARDIAN_ADDRESS_CITY, PRESENT, ACCEPT, MQE0666),
  PatientGuardianAddressCountryIsPresent(PATIENT_GUARDIAN_ADDRESS_COUNTRY, PRESENT, ACCEPT, MQE0667),
  PatientGuardianAddressCountyIsPresent(PATIENT_GUARDIAN_ADDRESS_COUNTY, PRESENT, ACCEPT, MQE0668),
  PatientGuardianAddressIsPresent(PATIENT_GUARDIAN_ADDRESS, PRESENT, ACCEPT, MQE0669),
  PatientGuardianAddressStateIsPresent(PATIENT_GUARDIAN_ADDRESS_STATE, PRESENT, ACCEPT, MQE0670),
  PatientGuardianAddressStreetIsPresent(PATIENT_GUARDIAN_ADDRESS_STREET, PRESENT, ACCEPT, MQE0671),
  PatientGuardianAddressTypeIsPresent(PATIENT_GUARDIAN_ADDRESS_TYPE, PRESENT, ACCEPT, MQE0672),
  PatientGuardianAddressZipIsPresent(PATIENT_GUARDIAN_ADDRESS_ZIP, PRESENT, ACCEPT, MQE0673),
  PatientGuardianNameFirstIsPresent(PATIENT_GUARDIAN_NAME_FIRST, PRESENT, ACCEPT, MQE0674),
  PatientGuardianNameIsPresent(PATIENT_GUARDIAN_NAME, PRESENT, ACCEPT, MQE0675),
  PatientGuardianNameLastIsPresent(PATIENT_GUARDIAN_NAME_LAST, PRESENT, ACCEPT, MQE0676),
  PatientGuardianPhoneIsPresent(PATIENT_GUARDIAN_PHONE, PRESENT, ACCEPT, MQE0677),
  PatientGuardianRelationshipIsPresent(PATIENT_GUARDIAN_RELATIONSHIP, PRESENT, ACCEPT, MQE0678),
  PatientGuardianResponsiblePartyIsPresent(PATIENT_GUARDIAN_RESPONSIBLE_PARTY, PRESENT, ACCEPT, MQE0679),
  PatientImmunizationRegistryStatusIsPresent(PATIENT_IMMUNIZATION_REGISTRY_STATUS, PRESENT, ACCEPT, MQE0680),
  PatientMedicaidNumberIsPresent(PATIENT_MEDICAID_NUMBER, PRESENT, ACCEPT, MQE0681),
  PatientMotherSMaidenNameIsPresent(PATIENT_MOTHERS_MAIDEN_NAME, PRESENT, ACCEPT, MQE0682),
  PatientNameFirstIsPresent(PATIENT_NAME_FIRST, PRESENT, ACCEPT, MQE0683),
  PatientNameLastIsPresent(PATIENT_NAME_LAST, PRESENT, ACCEPT, MQE0684),
  PatientNameMiddleIsPresent(PATIENT_NAME_MIDDLE, PRESENT, ACCEPT, MQE0685),
  PatientNameTypeCodeIsPresent(PATIENT_NAME_TYPE_CODE, PRESENT, ACCEPT, MQE0686),
  PatientObjectIsPresent(NONE, PRESENT, ACCEPT, MQE0687),
  PatientPhoneIsPresent(PATIENT_PHONE, PRESENT, ACCEPT, MQE0688),
  PatientPhoneTelEquipCodeIsPresent(PATIENT_PHONE_TEL_EQUIP_CODE, PRESENT, ACCEPT, MQE0689),
  PatientPhoneTelUseCodeIsPresent(PATIENT_PHONE_TEL_USE_CODE, PRESENT, ACCEPT, MQE0690),
  PatientPrimaryFacilityIdIsPresent(PATIENT_PRIMARY_FACILITY_ID, PRESENT, ACCEPT, MQE0691),
  PatientPrimaryFacilityNameIsPresent(PATIENT_PRIMARY_FACILITY_NAME, PRESENT, ACCEPT, MQE0692),
  PatientPrimaryLanguageIsPresent(PATIENT_PRIMARY_LANGUAGE, PRESENT, ACCEPT, MQE0693),
  PatientPrimaryPhysicianIdIsPresent(PATIENT_PRIMARY_PHYSICIAN_ID, PRESENT, ACCEPT, MQE0694),
  PatientPrimaryPhysicianNameIsPresent(PATIENT_PRIMARY_PHYSICIAN_NAME, PRESENT, ACCEPT, MQE0695),
  PatientProtectionIndicatorIsPresent(PATIENT_PROTECTION_INDICATOR, PRESENT, ACCEPT, MQE0696),
  PatientPublicityCodeIsPresent(PATIENT_PUBLICITY_CODE, PRESENT, ACCEPT, MQE0697),
  PatientRaceIsPresent(PATIENT_RACE, PRESENT, ACCEPT, MQE0698),
  PatientRegistryIdIsPresent(PATIENT_REGISTRY_ID, PRESENT, ACCEPT, MQE0699),
  PatientRegistryStatusIsPresent(PATIENT_REGISTRY_STATUS, PRESENT, ACCEPT, MQE0700),
  PatientSsnIsPresent(PATIENT_SSN, PRESENT, ACCEPT, MQE0701),
  PatientSubmitterIdAuthorityIsPresent(PATIENT_SUBMITTER_ID_AUTHORITY, PRESENT, ACCEPT, MQE0702),
  PatientSubmitterIdIsPresent(PATIENT_SUBMITTER_ID, PRESENT, ACCEPT, MQE0703),
  PatientSubmitterIdTypeCodeIsPresent(PATIENT_SUBMITTER_ID_TYPE_CODE, PRESENT, ACCEPT, MQE0704),
  PatientSystemEntryDateIsPresent(PATIENT_SYSTEM_ENTRY_TIME, PRESENT, ACCEPT, MQE0705),
  PatientVfcEffectiveDateIsPresent(PATIENT_VFC_EFFECTIVE_DATE, PRESENT, ACCEPT, MQE0706),
  PatientVfcStatusIsPresent(PATIENT_VFC_STATUS, PRESENT, ACCEPT, MQE0707),
  PatientWicIdIsPresent(PATIENT_WIC_ID, PRESENT, ACCEPT, MQE0708),
  VaccinationActionCodeIsPresent(VACCINATION_ACTION_CODE, PRESENT, ACCEPT, MQE0709),
  VaccinationAdminCodeIsPresent(VACCINATION_ADMIN_CODE, PRESENT, ACCEPT, MQE0710),
  VaccinationAdminCodeTableIsPresent(VACCINATION_ADMIN_CODE_TABLE, PRESENT, ACCEPT, MQE0711),
  VaccinationAdminDateEndIsPresent(VACCINATION_ADMIN_DATE_END, PRESENT, ACCEPT, MQE0712),
  VaccinationAdminDateIsPresent(VACCINATION_ADMIN_DATE, PRESENT, ACCEPT, MQE0713),
  VaccinationAdministeredAmountIsPresent(VACCINATION_ADMINISTERED_AMOUNT, PRESENT, ACCEPT, MQE0714),
  VaccinationAdministeredUnitIsPresent(VACCINATION_ADMINISTERED_UNIT, PRESENT, ACCEPT, MQE0715),
  VaccinationBodyRouteIsPresent(VACCINATION_BODY_ROUTE, PRESENT, ACCEPT, MQE0716),
  VaccinationBodySiteIsPresent(VACCINATION_BODY_SITE, PRESENT, ACCEPT, MQE0717),
  VaccinationCompletionStatusIsPresent(VACCINATION_COMPLETION_STATUS, PRESENT, ACCEPT, MQE0718),
  VaccinationConfidentialityCodeIsPresent(VACCINATION_CONFIDENTIALITY_CODE, PRESENT, ACCEPT, MQE0719),
  VaccinationCptCodeIsPresent(VACCINATION_CPT_CODE, PRESENT, ACCEPT, MQE0720),
  VaccinationCvxCodeIsPresent(VACCINATION_CVX_CODE, PRESENT, ACCEPT, MQE0721),
  VaccinationFacilityIdIsPresent(VACCINATION_FACILITY_ID, PRESENT, ACCEPT, MQE0722),
  VaccinationFacilityNameIsPresent(VACCINATION_FACILITY_NAME, PRESENT, ACCEPT, MQE0723),
  VaccinationFillerOrderNumberIsPresent(VACCINATION_FILLER_ORDER_NUMBER, PRESENT, ACCEPT, MQE0724),
  VaccinationFinancialEligibilityCodeIsPresent(VACCINATION_FINANCIAL_ELIGIBILITY_CODE, PRESENT, ACCEPT, MQE0725),
  VaccinationFundingSourceCodeIsPresent(VACCINATION_FUNDING_SOURCE_CODE, PRESENT, ACCEPT, MQE0726),
  VaccinationGivenByIsPresent(VACCINATION_GIVEN_BY, PRESENT, ACCEPT, MQE0727),
  VaccinationIdIsPresent(VACCINATION_ID, PRESENT, ACCEPT, MQE0728),
  VaccinationIdOfReceiverIsPresent(VACCINATION_ID_OF_RECEIVER, PRESENT, ACCEPT, MQE0729),
  VaccinationIdOfSenderIsPresent(VACCINATION_ID_OF_SENDER, PRESENT, ACCEPT, MQE0730),
  VaccinationInformationSourceIsPresent(VACCINATION_INFORMATION_SOURCE, PRESENT, ACCEPT, MQE0731),
  VaccinationLotExpirationDateIsPresent(VACCINATION_LOT_EXPIRATION_DATE, PRESENT, ACCEPT, MQE0732),
  VaccinationLotNumberIsPresent(VACCINATION_LOT_NUMBER, PRESENT, ACCEPT, MQE0733),
  VaccinationManufacturerCodeIsPresent(VACCINATION_MANUFACTURER_CODE, PRESENT, ACCEPT, MQE0734),
  VaccinationNDCCodeIsPresent(VACCINATION_NDC_CODE, PRESENT, ACCEPT, MQE0735),
  VaccinationOrderControlCodeIsPresent(VACCINATION_ORDER_CONTROL_CODE, PRESENT, ACCEPT, MQE0736),
  VaccinationOrderedByIsPresent(VACCINATION_ORDERED_BY, PRESENT, ACCEPT, MQE0737),
  VaccinationOrderFacilityIdIsPresent(VACCINATION_ORDER_FACILITY_ID, PRESENT, ACCEPT, MQE0738),
  VaccinationOrderFacilityNameIsPresent(VACCINATION_ORDER_FACILITY_NAME, PRESENT, ACCEPT, MQE0739),
  VaccinationPlacerOrderNumberIsPresent(VACCINATION_PLACER_ORDER_NUMBER, PRESENT, ACCEPT, MQE0740),
  VaccinationProductIsPresent(VACCINATION_PRODUCT, PRESENT, ACCEPT, MQE0741),
  VaccinationRecordedByIsPresent(VACCINATION_RECORDED_BY, PRESENT, ACCEPT, MQE0742),
  VaccinationRefusalReasonIsPresent(VACCINATION_REFUSAL_REASON, PRESENT, ACCEPT, MQE0743),
  VaccinationSystemEntryDateIsPresent(VACCINATION_SYSTEM_ENTRY_TIME, PRESENT, ACCEPT, MQE0744),
  VaccinationVisCvxIsPresent(VACCINATION_VIS_CVX_CODE, PRESENT, ACCEPT, MQE0745),
  VaccinationVisDeliveryDateIsPresent(VACCINATION_VIS_DELIVERY_DATE, PRESENT, ACCEPT, MQE0746),
  VaccinationVisDocumentTypeIsPresent(VACCINATION_VIS_DOCUMENT_TYPE, PRESENT, ACCEPT, MQE0747),
  VaccinationVisIsPresent(VACCINATION_VIS, PRESENT, ACCEPT, MQE0748),
  VaccinationVisPresentedDateIsPresent(VACCINATION_VIS_PRESENTED_DATE, PRESENT, ACCEPT, MQE0749),
  VaccinationVisPublishedDateIsPresent(VACCINATION_VIS_PUBLISHED_DATE, PRESENT, ACCEPT, MQE0750),
  VaccinationVisVersionDateIsPresent(VACCINATION_VIS_VERSION_DATE, PRESENT, ACCEPT, MQE0751),


  UnknownValidationIssue(CONFIGURATION, UNRECOGNIZED, WARN, MQE0000);
  //@formatter:on
  private static final Map<VxuField, Map<DetectionType, Detection>> fieldIssueMaps =
      new HashMap<>();

  private static final Map<MqeCode, Detection> errorCodeAttributeMap = new HashMap<>();

  private static final Map<MqeCode, SeverityLevel> defaultDetectionsMap = new HashMap<>();
  
  static {
    for (Detection detection : Detection.values()) {
      Map<DetectionType, Detection> map = fieldIssueMaps.get(detection.getTargetField());
      if (map == null) {
        map = new HashMap<>();
        fieldIssueMaps.put(detection.getTargetField(), map);
      }

      map.put(detection.getDetectionType(), detection);

      errorCodeAttributeMap.put(detection.mqeCode, detection);
      
      defaultDetectionsMap.put(detection.mqeCode, detection.severity);

    }
  }

  // public static final String CHANGE_PRIORITY_BLOCKED = "Blocked";
  // public static final String CHANGE_PRIORITY_CAN = "Can";
  // public static final String CHANGE_PRIORITY_MAY = "May";
  // public static final String CHANGE_PRIORITY_MUST = "Must";
  // public static final String CHANGE_PRIORITY_SHOULD = "Should";

  public final DetectionType detectionType;
  public final VxuField fieldRef;
  private       SeverityLevel severity;
  public final MqeCode mqeCode;

  Detection(VxuField fieldRef, DetectionType type, SeverityLevel severity, MqeCode mqeMqeCode) {
    this.detectionType = type;
    this.fieldRef = fieldRef;
    this.severity = severity;
    this.mqeCode = mqeMqeCode;
  }

  public ApplicationErrorCode getApplicationErrorCode() {
    return this.detectionType.getApplicationErrorCode();
  }

  public String getDisplayText() {
    return this.getTargetField().getObject().getDescription()
        + " "
        + fieldRef.getFieldDescription()
        + " "
        + detectionType.getWording();
  }

  public DetectionType getDetectionType() {
    return detectionType;
  }

  public VxuField getTargetField() {
    return fieldRef;
  }

  public VxuObject getTargetObject() {
    return this.fieldRef.getObject();
  }

  public static ValidationReport build(VxuField field, DetectionType type, String value, MetaFieldInfoData meta) {
    return get(field, type).build(value, meta);
  }

  public static ValidationReport build(VxuField field, DetectionType type, MetaFieldInfoData meta) {
    return get(field, type).build(meta);
  }

  public ValidationReport build(String value, MetaFieldInfoData meta) {
    ValidationReport found = build(meta);
    found.setValueReceived(value);
    return found;
  }

  public ValidationReport build(MetaFieldInfoData meta) {
    Hl7Location loc = null;
    if (meta != null) {
//      meta.getDetectionList().add(this);//I'm not super comfortable with this...  but I'm not sure how else to accomplish this.
      MetaFieldInfo mfi = meta.getMetaFieldInfo(this.fieldRef);
      if (mfi != null) {
        loc = mfi.getHl7Location();
      }
    }
    return build(loc);
  }

  private ValidationReport build(Hl7Location loc) {
    ValidationReport found = new ValidationReport();
    if (loc != null) {
      found.getHl7LocationList().add(loc);
    }
    found.setDetection(this);
    return found;
  }

  public static Detection getByMqeErrorCodeString(String codeString) {
    MqeCode code = MqeCode.getByCodeString(codeString);
    return errorCodeAttributeMap.get(code);
  }

  public SeverityLevel getSeverity() {
    return severity;
  }

  public String getMqeMqeCode() {
    return mqeCode.toString();
  }
  
  public MqeCode getMqeCodeObject() {
	  return mqeCode;
  }

  public AckERRCode getHl7ErrorCode() {
    return detectionType.getAckErrCode();
  }
  
  public void setSeverity(SeverityLevel severity) {
	  this.severity = severity;
  }

  /**
   * @param field vxu field
   * @param type detection type
   * @return Detection that matches these input parameters
   */
  public static Detection get(VxuField field, DetectionType type) {
    Map<DetectionType, Detection> fieldIssues = fieldIssueMaps.get(field);
    if (fieldIssues != null) {
      return fieldIssues.get(type);
    }
    return Detection.UnknownValidationIssue;
  }

  public static void resetDetectionSeverityToDefault() {
	  for (Detection detection : Detection.values()) {
		  detection.setSeverity(defaultDetectionsMap.get(detection.getMqeCodeObject()));
	  }
  }
}
