package org.immregistries.dqa.validator.detection;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.hl7util.ApplicationErrorCode;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.model.Hl7Location;
import org.immregistries.dqa.hl7util.model.MetaFieldInfo;
import org.immregistries.dqa.vxu.MetaFieldInfoData;
import org.immregistries.dqa.vxu.VxuField;

public enum Detection {
  // @formatter:off
  GeneralAuthorizationException(MessageObject.GENERAL, DetectionType.EXCEPTION, VxuField.AUTHORIZATION, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0002, ""),
  GeneralConfigurationException(MessageObject.GENERAL, DetectionType.EXCEPTION, VxuField.CONFIGURATION, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0003, ""),
  GeneralParseException(MessageObject.GENERAL, DetectionType.EXCEPTION, VxuField.PARSE, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0004, ""),
  GeneralProcessingException(MessageObject.GENERAL, DetectionType.EXCEPTION, VxuField.PROCESSING, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0005, ""),
  UnknownDetection(MessageObject.GENERAL, DetectionType.EXCEPTION, VxuField.PROCESSING, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0558, ""),

  //These might be validated from NIST validations.
  //Maybe we have an entry for Hl7StructureWarning, Hl7StructureError...
  //We need to figure this out...
  //DQA should not be looking at the ACK type...
  //accept ack type

  MessageAcceptAckTypeIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_ACCEPT_ACK_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0006, "MSH-15"),
  MessageAppAckTypeIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_APP_ACK_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0410, "MSH-16"),
  MessageMessageControlIdIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_CONTROL_ID, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0014, "MSH-10"),

  //This we validate against other stuff.
  MessageMessageDateIsInFuture(MessageObject.MESSAGE_HEADER, DetectionType.IN_FUTURE, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0015, "MSH-7"),
  MessageMessageDateIsInvalid(MessageObject.MESSAGE_HEADER, DetectionType.INVALID, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0016, "MSH-7"),
  MessageMessageDateIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0017, "MSH-7"),
  MessageMessageDateIsNotPrecise(MessageObject.MESSAGE_HEADER, DetectionType.NOT_PRECISE, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0526, "MSH-7"),
  MessageMessageDateIsMissingTimezone(MessageObject.MESSAGE_HEADER, DetectionType.MISSING_TIMEZONE, VxuField.MESSAGE_DATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0527, "MSH-7"),
  MessageMessageDateIsUnexpectedFormat(MessageObject.MESSAGE_HEADER, DetectionType.UNEXPECTED_FORMAT, VxuField.MESSAGE_DATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0531, "MSH-7"),

  //
  MessageMessageProfileIdIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_PROFILE_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0439, "MSH-21"),
  MessageMessageTriggerIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_TRIGGER, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0018, "MSH-9.2"),
  MessageMessageTypeIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_TYPE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0020, "MSH-9.1"),
  MessageProcessingIdIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_PROCESSING_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0023, "MSH-11"),
  MessageReceivingApplicationIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_RECEIVING_APPLICATION, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0030, "MSH-5"),
  MessageReceivingFacilityIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_RECEIVING_FACILITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0032, "MSH-6"),
  MessageSendingApplicationIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_SENDING_APPLICATION, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0035, "MSH-3"),
  MessageSendingFacilityIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_SENDING_FACILITY, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0037, "MSH-4"),
  MessageSendingResponsibleOrganizationIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_SENDING_RESPONSIBLE_ORGANIZATION, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0556, "MSH-22"),

  MessageVersionIsMissing(MessageObject.MESSAGE_HEADER, DetectionType.MISSING, VxuField.MESSAGE_VERSION, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0038, "MSH-12"),
  MessageVersionIsUnrecognized(MessageObject.MESSAGE_HEADER, DetectionType.UNRECOGNIZED, VxuField.MESSAGE_VERSION, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0039, "MSH-12"),
  MessageVersionIsInvalid(MessageObject.MESSAGE_HEADER, DetectionType.INVALID, VxuField.MESSAGE_VERSION, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0523, "MSH-12"),

  NextOfKinAddressIsDifferentFromPatientAddress(MessageObject.NEXT_OF_KIN, DetectionType.DIFFERENT_FROM_PATIENT_ADDRESS, VxuField.NEXT_OF_KIN_ADDRESS, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0056, "NK1-4"),
  NextOfKinAddressIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0057, "NK1-4"),
  NextOfKinAddressCityIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_CITY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0058, "NK1-4.3"),
  NextOfKinAddressCityIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0059, "NK1-4.3"),
  NextOfKinAddressCountryIsDeprecated(MessageObject.NEXT_OF_KIN, DetectionType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0060, "NK1-4.6"),
  NextOfKinAddressCountryIsIgnored(MessageObject.NEXT_OF_KIN, DetectionType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0061, "NK1-4.6"),
  NextOfKinAddressCountryIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0062, "NK1-4.6"),
  NextOfKinAddressCountryIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0063, "NK1-4.6"),
  NextOfKinAddressCountryIsUnrecognized(MessageObject.NEXT_OF_KIN, DetectionType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0064, "NK1-4.6"),
  NextOfKinAddressCountyIsDeprecated(MessageObject.NEXT_OF_KIN, DetectionType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0065, "NK1-4.9"),
  NextOfKinAddressCountyIsIgnored(MessageObject.NEXT_OF_KIN, DetectionType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0066, "NK1-4.9"),
  NextOfKinAddressCountyIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0067, "NK1-4.9"),
  NextOfKinAddressCountyIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0068, "NK1-4.9"),
  NextOfKinAddressCountyIsUnrecognized(MessageObject.NEXT_OF_KIN, DetectionType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0069, "NK1-4.9"),
  NextOfKinAddressStateIsDeprecated(MessageObject.NEXT_OF_KIN, DetectionType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0070, "NK1-4.4"),
  NextOfKinAddressStateIsIgnored(MessageObject.NEXT_OF_KIN, DetectionType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0071, "NK1-4.4"),
  NextOfKinAddressStateIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0072, "NK1-4.4"),
  NextOfKinAddressStateIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0073, "NK1-4.4"),
  NextOfKinAddressStateIsUnrecognized(MessageObject.NEXT_OF_KIN, DetectionType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0074, "NK1-4.4"),
  NextOfKinAddressStreetIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0075, "NK1-4.1"),
  NextOfKinAddressStreet2IsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_STREET2, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0076, "NK1-4.2"),
  NextOfKinAddressTypeIsDeprecated(MessageObject.NEXT_OF_KIN, DetectionType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0395, "NK1-4.7"),
  NextOfKinAddressTypeIsIgnored(MessageObject.NEXT_OF_KIN, DetectionType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0396, "NK1-4.7"),
  NextOfKinAddressTypeIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0397, "NK1-4.7"),
  NextOfKinAddressTypeIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0398, "NK1-4.7"),
  NextOfKinAddressTypeIsUnrecognized(MessageObject.NEXT_OF_KIN, DetectionType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0399, "NK1-4.7"),
  NextOfKinAddressTypeIsValuedBadAddress(MessageObject.NEXT_OF_KIN, DetectionType.VALUED_BAD_ADDRESS, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0522, "NK1-4.7"),
  NextOfKinAddressZipIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_ZIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0077, "NK1-4.5"),
  NextOfKinAddressZipIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0078, "NK1-4.5"),
  NextOfKinNameIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_NAME, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0079, "NK1-2"),
  NextOfKinNameFirstIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_NAME_FIRST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0080, "NK1-2.1"),
  NextOfKinNameLastIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_NAME_LAST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0081, "NK1-2.2"),
  NextOfKinPhoneNumberIsIncomplete(MessageObject.NEXT_OF_KIN, DetectionType.INCOMPLETE, VxuField.NEXT_OF_KIN_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0082, "NK1-5"),
  NextOfKinPhoneNumberIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0083, "NK1-5"),
  NextOfKinPhoneNumberIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_PHONE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0084, "NK1-5"),
  NextOfKinRelationshipIsDeprecated(MessageObject.NEXT_OF_KIN, DetectionType.DEPRECATED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0085, "NK1-3"),
  NextOfKinRelationshipIsIgnored(MessageObject.NEXT_OF_KIN, DetectionType.IGNORED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0086, "NK1-3"),
  NextOfKinRelationshipIsInvalid(MessageObject.NEXT_OF_KIN, DetectionType.INVALID, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0087, "NK1-3"),
  NextOfKinRelationshipIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0088, "NK1-3"),
  NextOfKinRelationshipIsNotResponsibleParty(MessageObject.NEXT_OF_KIN, DetectionType.NOT_RESPONSIBLE_PARTY, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0089, "NK1-3"),
  NextOfKinRelationshipIsUnexpected(MessageObject.NEXT_OF_KIN, DetectionType.UNEXPECTED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0485, "NK1-3"),
  NextOfKinRelationshipIsUnrecognized(MessageObject.NEXT_OF_KIN, DetectionType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0090, "NK1-3"),
  NextOfKinSsnIsMissing(MessageObject.NEXT_OF_KIN, DetectionType.MISSING, VxuField.NEXT_OF_KIN_SSN, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0091, "NK1-33"),
  ObservationValueIsMissing(MessageObject.OBSERVATION, DetectionType.MISSING, VxuField.OBSERVATION_VALUE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0532, "OBX"),
  ObservationValueTypeIsDeprecated(MessageObject.OBSERVATION, DetectionType.DEPRECATED, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0470, "OBX-2"),
  ObservationValueTypeIsIgnored(MessageObject.OBSERVATION, DetectionType.IGNORED, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0471, "OBX-2"),
  ObservationValueTypeIsInvalid(MessageObject.OBSERVATION, DetectionType.INVALID, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0472, "OBX-2"),
  ObservationValueTypeIsMissing(MessageObject.OBSERVATION, DetectionType.MISSING, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0473, "OBX-2"),
  ObservationValueTypeIsUnrecognized(MessageObject.OBSERVATION, DetectionType.UNRECOGNIZED, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0474, "OBX-2"),
  ObservationObservationIdentifierCodeIsDeprecated(MessageObject.OBSERVATION, DetectionType.DEPRECATED, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0475, "OBX-3"),
  ObservationObservationIdentifierCodeIsIgnored(MessageObject.OBSERVATION, DetectionType.IGNORED, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0476, "OBX-3"),
  ObservationObservationIdentifierCodeIsInvalid(MessageObject.OBSERVATION, DetectionType.INVALID, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0477, "OBX-3"),
  ObservationObservationIdentifierCodeIsMissing(MessageObject.OBSERVATION, DetectionType.MISSING, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0478, "OBX-3"),
  ObservationObservationIdentifierCodeIsUnrecognized(MessageObject.OBSERVATION, DetectionType.UNRECOGNIZED, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0479, "OBX-3"),
  ObservationObservationValueIsMissing(MessageObject.OBSERVATION, DetectionType.MISSING, VxuField.OBSERVATION_VALUE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0480, "OBX-5"),
  ObservationDateTimeOfObservationIsMissing(MessageObject.OBSERVATION, DetectionType.MISSING, VxuField.OBSERVATION_DATE_TIME_OF_OBSERVATION, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0481, "OBX-14"),
  ObservationDateTimeOfObservationIsInvalid(MessageObject.OBSERVATION, DetectionType.INVALID, VxuField.OBSERVATION_DATE_TIME_OF_OBSERVATION, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0482, "OBX-14"),
  PatientObjectIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.NONE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0545, "PID-11"),
  PatientAddressIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0092, "PID-11"),
  PatientAddressCityIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0093, "PID-11.3"),
  PatientAddressCityIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0094, "PID-11.3"),
  PatientAddressCountryIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0095, "PID-11.6"),
  PatientAddressCountryIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0096, "PID-11.6"),
  PatientAddressCountryIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0097, "PID-11.6"),
  PatientAddressCountryIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0098, "PID-11.6"),
  PatientAddressCountryIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0099, "PID-11.6"),
  PatientAddressCountyIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0100, "PID-11.9"),
  PatientAddressCountyIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0101, "PID-11.9"),
  PatientAddressCountyIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0102, "PID-11.9"),
  PatientAddressCountyIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0103, "PID-11.9"),
  PatientAddressCountyIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0104, "PID-11.9"),
  PatientAddressStateIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0105, "PID-11.4"),
  PatientAddressStateIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0106, "PID-11.4"),
  PatientAddressStateIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0107, "PID-11.4"),
  PatientAddressStateIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0108, "PID-11.4"),
  PatientAddressStateIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0109, "PID-11.4"),
  PatientAddressStreetIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0110, "PID-11.1"),
  PatientAddressStreet2IsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_STREET2, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0111, "PID-11.2"),
  PatientAddressTypeIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0451, "PID-11.7"),
  PatientAddressTypeIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0517, "PID-11.7"),
  PatientAddressTypeIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0518, "PID-11.7"),
  PatientAddressTypeIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0519, "PID-11.7"),
  PatientAddressTypeIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0520, "PID-11.7"),
  PatientAddressTypeIsValuedBadAddress(MessageObject.PATIENT, DetectionType.VALUED_BAD_ADDRESS, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0521, "PID-11.7"),
  PatientAddressZipIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_ADDRESS_ZIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0112, "PID-11.5"),
  PatientAddressZipIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0113, "PID-11.5"),
  PatientAliasIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ALIAS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0114, "PID-5"),
  PatientBirthDateIsAfterSubmission(MessageObject.PATIENT, DetectionType.AFTER_SUBMISSION, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0115, "PID-7"),
  PatientBirthDateIsInFuture(MessageObject.PATIENT, DetectionType.IN_FUTURE, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0116, "PID-7"),
  PatientBirthDateIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0117, "PID-7"),
  PatientBirthDateIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0118, "PID-7"),
  PatientBirthDateIsUnderage(MessageObject.PATIENT, DetectionType.UNDERAGE, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0119, "PID-7"),
  PatientBirthDateIsVeryLongAgo(MessageObject.PATIENT, DetectionType.VERY_LONG_AGO, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0120, "PID-7"),
  PatientBirthIndicatorIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_BIRTH_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0121, "PID-24"),
  PatientBirthIndicatorIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_BIRTH_INDICATOR, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0122, "PID-24"),
  PatientBirthOrderIsUnknown(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_BIRTH_ORDER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0557, "PID-25"),
  PatientBirthOrderIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_BIRTH_ORDER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0123, "PID-25"),
  PatientBirthOrderIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_BIRTH_ORDER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0124, "PID-25"),
  PatientBirthOrderIsMissingAndMultipleBirthIndicated(MessageObject.PATIENT, DetectionType.MISSING_AND_MULTIPLE_BIRTH_INDICATED, VxuField.PATIENT_BIRTH_ORDER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0125, "PID-25"),
  PatientBirthPlaceIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_BIRTH_PLACE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0126, "PID-23"),
  PatientBirthRegistryIdIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_BIRTH_REGISTRY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0127, "PID-3"),
  PatientBirthRegistryIdIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_BIRTH_REGISTRY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0128, "PID-3"),
  PatientClassIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_CLASS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0374, "PV1-2"),
  PatientClassIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_CLASS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0375, "PV1-2"),
  PatientClassIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_CLASS, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0376, "PV1-2"),
  PatientClassIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_CLASS, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0377, "PV1-2"),
  PatientClassIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_CLASS, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0378, "PV1-2"),
  PatientDeathDateIsBeforeBirth(MessageObject.PATIENT, DetectionType.BEFORE_BIRTH, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0129, "PID-29"),
  PatientDeathDateIsInFuture(MessageObject.PATIENT, DetectionType.IN_FUTURE, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0130, "PID-29"),
  PatientDeathDateIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0131, "PID-29"),
  PatientDeathDateIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0132, "PID-29"),
  PatientDeathIndicatorIsInconsistent(MessageObject.PATIENT, DetectionType.INCONSISTENT, VxuField.PATIENT_DEATH_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0133, "PID-30"),
  PatientDeathIndicatorIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_DEATH_INDICATOR, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0134, "PID-30"),
  PatientEthnicityIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0135, "PID-22"),
  PatientEthnicityIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0136, "PID-22"),
  PatientEthnicityIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0137, "PID-22"),
  PatientEthnicityIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0138, "PID-22"),
  PatientEthnicityIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0139, "PID-22"),
  PatientGenderIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_GENDER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0143, "PID-8"),
  PatientGenderIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_GENDER, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0144, "PID-8"),
  PatientGenderIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0145, "PID-8"),
  PatientGenderIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0146, "PID-8"),
  PatientGenderIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0147, "PID-8"),
  PatientGuardianAddressIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0148, "NK1-4"),
  PatientGuardianAddressCityIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0149, "NK1-4.3"),
  PatientGuardianAddressStateIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0150, "NK1-4.4"),
  PatientGuardianAddressStreetIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0151, "NK1-4.1"),
  PatientGuardianAddressZipIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0152, "NK1-4.5"),
  PatientGuardianNameIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_NAME, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0155, "NK1-2"),
  PatientGuardianNameIsSameAsUnderagePatient(MessageObject.PATIENT, DetectionType.SAME_AS_UNDERAGE_PATIENT, VxuField.PATIENT_GUARDIAN_NAME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0156, "NK1-2"),
  PatientGuardianNameFirstIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_NAME_FIRST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0153, "NK1-2.2"),
  PatientGuardianNameLastIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_NAME_LAST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0154, "NK1-2.1"),
  PatientGuardianResponsiblePartyIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0157, "NK1"),
  PatientGuardianPhoneIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_PHONE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0158, "NK1-5"),
  PatientGuardianRelationshipIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_GUARDIAN_RELATIONSHIP, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0159, "NK1-3"),
  PatientImmunizationRegistryStatusIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0160, "PD1-16"),
  PatientImmunizationRegistryStatusIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0161, "PD1-16"),
  PatientImmunizationRegistryStatusIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0162, "PD1-16"),
  PatientImmunizationRegistryStatusIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0163, "PD1-16"),
  PatientImmunizationRegistryStatusIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0164, "PD1-16"),
  PatientMedicaidNumberIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_MEDICAID_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0167, "PID-3"),
  PatientMedicaidNumberIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_MEDICAID_NUMBER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0168, "PID-3"),
  PatientMiddleNameIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0169, "PID-5.3"),
  PatientMiddleNameIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0546, "PID-5.3"),
  PatientMiddleNameMayBeInitial(MessageObject.PATIENT, DetectionType.MAY_BE_AN_INITIAL, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0170, "PID-5.3"),
  //Some of these weren't represented in the XLS file.
  PatientMotherSMaidenNameIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0171, "PID-6.1"),
  PatientMothersMaidenNameIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0547, "PID-6.1"),
  PatientMotherSMaidenNameHasJunkName(MessageObject.PATIENT, DetectionType.HAS_JUNK_NAME, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0548, "PID-6.1"),
  PatientMotherSMaidenNameHasInvalidPrefixes(MessageObject.PATIENT, DetectionType.INVALID_PREFIXES, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0549, "PID-6.1"),
  PatientMotherSMaidenNameIsTooShort(MessageObject.PATIENT, DetectionType.TOO_SHORT, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0550, "PID-6.1"),

  PatientNameMayBeTemporaryNewbornName(MessageObject.PATIENT, DetectionType.MAY_BE_TEMPORARY_NEWBORN_NAME, VxuField.PATIENT_NAME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0172, "PID-5"),
  PatientNameMayBeTestName(MessageObject.PATIENT, DetectionType.MAY_BE_TEST_NAME, VxuField.PATIENT_NAME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0551, "PID-5"),
  PatientNameHasJunkName(MessageObject.PATIENT, DetectionType.HAS_JUNK_NAME, VxuField.PATIENT_NAME, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0173, "PID-5"),
  PatientNameFirstIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_NAME_FIRST, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0140, "PID-5.2"),
  PatientNameFirstIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_NAME_FIRST, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0141, "PID-5.2"),
  PatientNameFirstMayIncludeMiddleInitial(MessageObject.PATIENT, DetectionType.MAY_INCLUDE_MIDDLE_INITIAL, VxuField.PATIENT_NAME_FIRST, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0142, "PID-5.2"),
  PatientNameLastIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_NAME_LAST, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0165, "PID-5.1"),
  PatientNameLastIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_NAME_LAST, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0166, "PID-5.1"),
  PatientNameMiddleIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ERROR, "", null, ErrorCode.MQE0528, ""),
  PatientNameMiddleIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ERROR, "", null, ErrorCode.MQE0529, ""),
  PatientNameTypeCodeIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0405, "PID-5.7"),
  PatientNameTypeCodeIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0406, "PID-5.7"),
  PatientNameTypeCodeIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0407, "PID-5.7"),
  PatientNameTypeCodeIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0408, "PID-5.7"),
  PatientNameTypeCodeIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0409, "PID-5.7"),
  PatientNameTypeCodeIsNotValuedLegal(MessageObject.PATIENT, DetectionType.NOT_VALUED_LEGAL, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0516, ""),
  PatientPhoneIsIncomplete(MessageObject.PATIENT, DetectionType.INCOMPLETE, VxuField.PATIENT_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0174, "PID-13"),
  PatientPhoneIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0175, "PID-13"),
  PatientPhoneIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PHONE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0176, "PID-13"),
  PatientPhoneTelUseCodeIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0453, "PID-13.2"),
  PatientPhoneTelUseCodeIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0454, "PID-13.2"),
  PatientPhoneTelUseCodeIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0455, "PID-13.2"),
  PatientPhoneTelUseCodeIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0456, "PID-13.2"),
  PatientPhoneTelUseCodeIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0457, "PID-13.2"),
  PatientPhoneTelEquipCodeIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0458, "PID-13.3"),
  PatientPhoneTelEquipCodeIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0459, "PID-13.3"),
  PatientPhoneTelEquipCodeIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0460, "PID-13.3"),
  PatientPhoneTelEquipCodeIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0461, "PID-13.3"),
  PatientPhoneTelEquipCodeIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0462, "PID-13.3"),
  PatientPrimaryFacilityIdIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0177, "PD1-3.3"),
  PatientPrimaryFacilityIdIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0178, "PD1-3.3"),
  PatientPrimaryFacilityIdIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0179, "PD1-3.3"),
  PatientPrimaryFacilityIdIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0180, "PD1-3.3"),
  PatientPrimaryFacilityIdIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0181, "PD1-3.3"),
  PatientPrimaryFacilityNameIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PRIMARY_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0182, "PD1-3.1"),
  PatientPrimaryLanguageIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0183, "PID-15"),
  PatientPrimaryLanguageIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0184, "PID-15"),
  PatientPrimaryLanguageIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0185, "PID-15"),
  PatientPrimaryLanguageIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0186, "PID-15"),
  PatientPrimaryLanguageIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0187, "PID-15"),
  PatientPrimaryPhysicianIdIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0188, "PD1-4.1"),
  PatientPrimaryPhysicianIdIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0189, "PD1-4.1"),
  PatientPrimaryPhysicianIdIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0190, "PD1-4.1"),
  PatientPrimaryPhysicianIdIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0191, "PD1-4.1"),
  PatientPrimaryPhysicianIdIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0192, "PD1-4.1"),
  PatientPrimaryPhysicianNameIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PRIMARY_PHYSICIAN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0193, "PD1-4.2"),
  PatientProtectionIndicatorIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0194, "PD1-12"),
  PatientProtectionIndicatorIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0195, "PD1-12"),
  PatientProtectionIndicatorIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0196, "PD1-12"),
  PatientProtectionIndicatorIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0197, "PD1-12"),
  PatientProtectionIndicatorIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0198, "PD1-12"),
  PatientProtectionIndicatorIsValuedAsNo(MessageObject.PATIENT, DetectionType.VALUED_AS, VxuField.PATIENT_PROTECTION_INDICATOR, "no", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0199, "PD1-12"),
  PatientProtectionIndicatorIsValuedAsYes(MessageObject.PATIENT, DetectionType.VALUED_AS, VxuField.PATIENT_PROTECTION_INDICATOR, "yes", SeverityLevel.WARN, "102", null, ErrorCode.MQE0200, "PD1-12"),
  PatientPublicityCodeIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0201, "PD1-11"),
  PatientPublicityCodeIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0202, "PD1-11"),
  PatientPublicityCodeIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0203, "PD1-11"),
  PatientPublicityCodeIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0204, "PD1-11"),
  PatientPublicityCodeIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0205, "PD1-11"),
  PatientRaceIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_RACE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0206, "PID-10"),
  PatientRaceIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_RACE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0207, "PID-10"),
  PatientRaceIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_RACE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0208, "PID-10"),
  PatientRaceIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_RACE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0209, "PID-10"),
  PatientRaceIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_RACE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0210, "PID-10"),
  PatientRegistryIdIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_REGISTRY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0211, "PID-3"),
  PatientRegistryIdIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_REGISTRY_ID, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0212, "PID-3"),
  PatientRegistryStatusIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0213, "PD1-16"),
  PatientRegistryStatusIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0214, "PD1-16"),
  PatientRegistryStatusIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0215, "PD1-16"),
  PatientRegistryStatusIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0216, "PD1-16"),
  PatientRegistryStatusIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0217, "PD1-16"),
  PatientSsnIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_SSN, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0218, "PID-3"),
  PatientSsnIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_SSN, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0219, "PID-3"),
  PatientSubmitterIdIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_SUBMITTER_ID, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0220, "PID-3"),
  PatientSubmitterIdAuthorityIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0393, "PID-3.4"),
  PatientSubmitterIdTypeCodeIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0394, "PID-3.5"),
  PatientSubmitterIdTypeCodeIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0512, ""),
  PatientSubmitterIdTypeCodeIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.ERROR, "", null, ErrorCode.MQE0513, ""),
  PatientSubmitterIdTypeCodeIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0514, ""),
  PatientSubmitterIdTypeCodeIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.INFO, "", null, ErrorCode.MQE0515, ""),
  PatientVfcEffectiveDateIsBeforeBirth(MessageObject.PATIENT, DetectionType.BEFORE_BIRTH, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0221, "PV1-20.2"),
  PatientVfcEffectiveDateIsInFuture(MessageObject.PATIENT, DetectionType.IN_FUTURE, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0222, "PV1-20.2"),
  PatientVfcEffectiveDateIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0223, "PV1-20.2"),
  PatientVfcEffectiveDateIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0224, "PV1-20.2"),
  PatientVfcStatusIsDeprecated(MessageObject.PATIENT, DetectionType.DEPRECATED, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0225, "PV1-20.1"),
  PatientVfcStatusIsIgnored(MessageObject.PATIENT, DetectionType.IGNORED, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0226, "PV1-20.1"),
  PatientVfcStatusIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0227, "PV1-20.1"),
  PatientVfcStatusIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0228, "PV1-20.1"),
  PatientVfcStatusIsUnrecognized(MessageObject.PATIENT, DetectionType.UNRECOGNIZED, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0229, "PV1-20.1"),
  PatientWicIdIsInvalid(MessageObject.PATIENT, DetectionType.INVALID, VxuField.PATIENT_WIC_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0230, "PID-3"),
  PatientWicIdIsMissing(MessageObject.PATIENT, DetectionType.MISSING, VxuField.PATIENT_WIC_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0231, "PID-3"),
  VaccinationActionCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0232, "RXA-21"),
  VaccinationActionCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0233, "RXA-21"),
  VaccinationActionCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0234, "RXA-21"),
  VaccinationActionCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0235, "RXA-21"),
  VaccinationActionCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0236, "RXA-21"),
  VaccinationActionCodeIsValuedAsAdd(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "add", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0237, "RXA-21"),
  VaccinationActionCodeIsValuedAsAddOrUpdate(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "add or update", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0238, "RXA-21"),
  VaccinationActionCodeIsValuedAsDelete(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "delete", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0239, "RXA-21"),
  VaccinationActionCodeIsValuedAsUpdate(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "update", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0240, "RXA-21"),
  VaccinationAdminCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0241, "RXA-5"),
  VaccinationAdminCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0242, "RXA-5"),
  VaccinationAdminCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0243, "RXA-5"),
  VaccinationAdminCodeIsInvalidForDateAdministered(MessageObject.VACCINATION, DetectionType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0491, "RXA-5"),
  VaccinationAdminCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0244, "RXA-5"),
  VaccinationAdminCodeIsNotSpecific(MessageObject.VACCINATION, DetectionType.NOT_SPECIFIC, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0245, "RXA-5"),
  VaccinationAdminCodeIsNotVaccine(MessageObject.VACCINATION, DetectionType.NOT_VACCINE, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0246, "RXA-5"),
  VaccinationAdminCodeIsUnexpectedForDateAdministered(MessageObject.VACCINATION, DetectionType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0490, "RXA-5"),
  VaccinationAdminCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "103", null, ErrorCode.MQE0247, "RXA-5"),
  VaccinationAdminCodeIsValuedAsNotAdministered(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "not administered", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0248, "RXA-5"),
  VaccinationAdminCodeIsValuedAsUnknown(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "unknown", SeverityLevel.WARN, "102", null, ErrorCode.MQE0249, "RXA-5"),
  VaccinationAdminCodeTableIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ADMIN_CODE_TABLE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0483, "RXA-5"),
  VaccinationAdminCodeTableIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ADMIN_CODE_TABLE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0484, "RXA-5"),
  VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(MessageObject.VACCINATION, DetectionType.MAY_BE_PREVIOUSLY_REPORTED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0250, "RXA-5"),
  VaccinationAdminDateIsAfterLotExpirationDate(MessageObject.VACCINATION, DetectionType.AFTER_LOT_EXPIRATION, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0251, "RXA-3"),
  VaccinationAdminDateIsAfterMessageSubmitted(MessageObject.VACCINATION, DetectionType.AFTER_MESSAGE_SUBMITTED, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0252, "RXA-3"),
  VaccinationAdminDateIsAfterPatientDeathDate(MessageObject.VACCINATION, DetectionType.AFTER_PATIENT_DEATH_DATE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0253, "RXA-3"),
  VaccinationAdminDateIsAfterSystemEntryDate(MessageObject.VACCINATION, DetectionType.AFTER_SYSTEM_ENTRY_DATE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0254, "RXA-3"),
  VaccinationAdminDateIsBeforeBirth(MessageObject.VACCINATION, DetectionType.BEFORE_BIRTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0255, "RXA-3"),
  VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(MessageObject.VACCINATION, DetectionType.BEFORE_OR_AFTER_EXPECTED_DATE_RANGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0256, "RXA-3"),
  VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(MessageObject.VACCINATION, DetectionType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0257, "RXA-3"),
  VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(MessageObject.VACCINATION, DetectionType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0258, "RXA-3"),
  VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(MessageObject.VACCINATION, DetectionType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0259, "RXA-3"),
  VaccinationAdminDateIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0260, "RXA-3"),
  VaccinationAdminDateIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0261, "RXA-3"),
  VaccinationAdminDateIsOn15ThDayOfMonth(MessageObject.VACCINATION, DetectionType.ON_FIFTEENTH_DAY_OF_MONTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0262, "RXA-3"),
  VaccinationAdminDateIsOnFirstDayOfMonth(MessageObject.VACCINATION, DetectionType.ON_FIRST_DAY_OF_MONTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0263, "RXA-3"),
  VaccinationAdminDateIsOnLastDayOfMonth(MessageObject.VACCINATION, DetectionType.ON_LAST_DAY_OF_MONTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0264, "RXA-3"),
  VaccinationAdminDateIsReportedLate(MessageObject.VACCINATION, DetectionType.REPORTED_LATE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0265, "RXA-3"),
  VaccinationAdminDateEndIsDifferentFromStartDate(MessageObject.VACCINATION, DetectionType.DIFF_FROM_START, VxuField.VACCINATION_ADMIN_DATE_END, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0266, "RXA-3"),
  VaccinationAdminDateEndIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ADMIN_DATE_END, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0267, "RXA-3"),
  VaccinationAdministeredCodeIsForiegn(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0268, ""),
  VaccinationHistoricalCodeIsForeign(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0553, ""),
  VaccinationAdministeredAmountIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0555, "RXA-6"),
  VaccinationAdministeredAmountIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0554, "RXA-6"),
  VaccinationAdministeredAmountIsValuedAsZero(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "zero", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0270, "RXA-6"),
  VaccinationAdministeredAmountIsValuedAsUnknown(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "unknown", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0271, "RXA-6"),
  VaccinationAdministeredUnitIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0447, "RXA-7"),
  VaccinationAdministeredUnitIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0448, "RXA-7"),
  VaccinationAdministeredUnitIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0449, "RXA-7"),
  VaccinationAdministeredUnitIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0272, "RXA-7"),
  VaccinationAdministeredUnitIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0450, "RXA-7"),
  VaccinationBodyRouteIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0273, "RXR-1"),
  VaccinationBodyRouteIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0274, "RXR-1"),
  VaccinationBodyRouteIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0275, "RXR-1"),
  VaccinationBodyRouteIsInvalidForVaccineIndicated(MessageObject.VACCINATION, DetectionType.INVALID_FOR_VACCINE, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0276, "RXR-1"),
  VaccinationBodyRouteIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0277, "RXR-1"),
  VaccinationBodyRouteIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0278, "RXR-1"),
  VaccinationBodySiteIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0279, "RXR-2"),
  VaccinationBodySiteIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0280, "RXR-2"),
  VaccinationBodySiteIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0281, "RXR-2"),
  VaccinationBodySiteIsInvalidForVaccineIndicated(MessageObject.VACCINATION, DetectionType.INVALID_FOR_VACCINE, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0282, "RXR-2"),
  VaccinationBodySiteIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0283, "RXR-2"),
  VaccinationBodySiteIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0284, "RXR-2"),
  VaccinationCompletionStatusIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0285, "RXA-20"),
  VaccinationCompletionStatusIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0286, "RXA-20"),
  VaccinationCompletionStatusIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0287, "RXA-20"),
  VaccinationCompletionStatusIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0288, "RXA-20"),
  VaccinationCompletionStatusIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0289, "RXA-20"),
  VaccinationCompletionStatusIsValuedAsCompleted(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "completed", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0290, "RXA-20"),
  VaccinationCompletionStatusIsValuedAsNotAdministered(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "not administered", SeverityLevel.INFO, "102", null, ErrorCode.MQE0291, "RXA-20"),
  VaccinationCompletionStatusIsValuedAsPartiallyAdministered(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "partially administered", SeverityLevel.INFO, "102", null, ErrorCode.MQE0292, "RXA-20"),
  VaccinationCompletionStatusIsValuedAsRefused(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "refused", SeverityLevel.INFO, "102", null, ErrorCode.MQE0293, "RXA-20"),
  VaccinationConfidentialityCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0294, "ORC-28"),
  VaccinationConfidentialityCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0295, "ORC-28"),
  VaccinationConfidentialityCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0296, "ORC-28"),
  VaccinationConfidentialityCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0297, "ORC-28"),
  VaccinationConfidentialityCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0298, "ORC-28"),
  VaccinationConfidentialityCodeIsValuedAsRestricted(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "restricted", SeverityLevel.WARN, "102", null, ErrorCode.MQE0299, "ORC-28"),
  VaccinationCptCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0300, "RXA-5"),
  VaccinationCptCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0301, "RXA-5"),
  VaccinationCptCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0302, "RXA-5"),
  VaccinationCptCodeIsInvalidForDateAdministered(MessageObject.VACCINATION, DetectionType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0489, "RXA-5"),
  VaccinationCptCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0303, "RXA-5"),
  VaccinationCptCodeIsUnexpectedForDateAdministered(MessageObject.VACCINATION, DetectionType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0488, "RXA-5"),
  VaccinationCptCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0304, "RXA-5"),
  VaccinationCvxCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0305, "RXA-5"),
  VaccinationCvxCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0306, "RXA-5"),
  VaccinationCvxCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0307, "RXA-5"),
  VaccinationCvxCodeIsInvalidForDateAdministered(MessageObject.VACCINATION, DetectionType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CVX_CODE, "",SeverityLevel.WARN, "102", null, ErrorCode.MQE0487, "RXA-5"),
  VaccinationCvxCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING,VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0308,"RXA-5"),
  VaccinationCvxCodeIsUnexpectedForDateAdministered(MessageObject.VACCINATION, DetectionType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0486, "RXA-5"),
  VaccinationCvxCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0309, "RXA-5"),
  VaccinationCvxCodeAndCptCodeAreInconsistent(MessageObject.VACCINATION, DetectionType.ARE_INCONSISTENT, VxuField.VACCINATION_CVX_CODE_AND_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0310, "RXA-5"),
  VaccinationNDCCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_NDC_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0559, "RXA-5"),
  VaccinationNDCCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_NDC_CODE, "", SeverityLevel.INFO, "103", null, ErrorCode.MQE0560, "RXA-5"),
  VaccinationFacilityIdIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0311, "RXA-11.4"),
  VaccinationFacilityIdIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0312, "RXA-11.4"),
  VaccinationFacilityIdIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0313, "RXA-11.4"),
  VaccinationFacilityIdIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0314, "RXA-11.4"),
  VaccinationFacilityIdIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0315, "RXA-11.4"),
  VaccinationFacilityNameIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0316, "RXA-11.4"),
  VaccinationFillerOrderNumberIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0379, "ORC-3"),
  VaccinationFillerOrderNumberIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0380, "ORC-3"),
  VaccinationFillerOrderNumberIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0381, "ORC-3"),
  VaccinationFillerOrderNumberIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0382, "ORC-3"),
  VaccinationFillerOrderNumberIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0383, "ORC-3"),
  VaccinationFinancialEligibilityCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0465, "OBX-5"),
  VaccinationFinancialEligibilityCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0466, "OBX-5"),
  VaccinationFinancialEligibilityCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0467, "OBX-5"),
  VaccinationFinancialEligibilityCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0468, "OBX-5"),
  VaccinationFinancialEligibilityCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0469, "OBX-5"),
  VaccinationGivenByIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0317, "RXA-10"),
  VaccinationGivenByIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0318, "RXA-10"),
  VaccinationGivenByIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0319, "RXA-10"),
  VaccinationGivenByIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0320, "RXA-10"),
  VaccinationGivenByIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0321, "RXA-10"),
  VaccinationIdIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0322, "ORC-3"),
  VaccinationIdOfReceiverIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ID_OF_RECEIVER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0323, "ORC-2"),
  VaccinationIdOfReceiverIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ID_OF_RECEIVER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0324, "ORC-2"),
  VaccinationIdOfSenderIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ID_OF_SENDER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0325, "ORC-3"),
  VaccinationIdOfSenderIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ID_OF_SENDER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0326, "ORC-3"),
  VaccinationInformationSourceIsAdministeredButAppearsToHistorical(MessageObject.VACCINATION, DetectionType.ADMINISTERED_BUT_APPEARS_TO_HISTORICAL, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0327, "RXA-9"),
  VaccinationInformationSourceIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0328, "RXA-9"),
  VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(MessageObject.VACCINATION, DetectionType.HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0329, "RXA-9"),
  VaccinationInformationSourceIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0330, "RXA-9"),
  VaccinationInformationSourceIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0331, "RXA-9"),
  VaccinationInformationSourceIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0332, "RXA-9"),
  VaccinationInformationSourceIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0333, "RXA-9"),
  VaccinationInformationSourceIsValuedAsAdministered(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_INFORMATION_SOURCE, "administered", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0334, "RXA-9"),
  VaccinationInformationSourceIsValuedAsHistorical(MessageObject.VACCINATION, DetectionType.VALUED_AS, VxuField.VACCINATION_INFORMATION_SOURCE, "historical", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0335, "RXA-9"),
  VaccinationVisDocumentTypeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0496, ""),
  VaccinationVisDocumentTypeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.INFO, "", null, ErrorCode.MQE0497, ""),
  VaccinationVisDocumentTypeIsIncorrect(MessageObject.VACCINATION, DetectionType.INCORRECT, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0498, ""),
  VaccinationVisDocumentTypeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0499, ""),
  VaccinationVisDocumentTypeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0500, ""),
  VaccinationVisDocumentTypeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0501, ""),
  VaccinationVisDocumentTypeIsOutOfDate(MessageObject.VACCINATION, DetectionType.OUT_OF_DATE, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0502, ""),
  VaccinationVisVersionDateIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0503, ""),
  VaccinationVisVersionDateIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0504, ""),
  VaccinationVisVersionDateIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0505, ""),
  VaccinationVisVersionDateIsInFuture(MessageObject.VACCINATION, DetectionType.IN_FUTURE, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0506, ""),
  VaccinationVisDeliveryDateIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0507, ""),
  VaccinationVisDeliveryDateIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0508, ""),
  VaccinationVisDeliveryDateIsNotAdminDate(MessageObject.VACCINATION, DetectionType.NOT_SAME_AS_ADMIN_DATE, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0509, ""),
  VaccinationVisDeliveryDateIsBeforeVersionDate(MessageObject.VACCINATION, DetectionType.BEFORE_VERSION_DATE, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0510, ""),
  VaccinationVisDeliveryDateIsAfterAdminDate(MessageObject.VACCINATION, DetectionType.AFTER_ADMIN_DATE, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0511, ""),

  //More that were missing.
  VaccinationVisPublishedDateIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_VIS_PUBLISHED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0534, ""),
  VaccinationVisPublishedDateIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_VIS_PUBLISHED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0535, ""),
  VaccinationVisPublishedDateIsInFuture(MessageObject.VACCINATION, DetectionType.IN_FUTURE, VxuField.VACCINATION_VIS_PUBLISHED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0536, ""),
  VaccinationVisPresentedDateIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_VIS_PRESENTED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0537, ""),
  VaccinationVisPresentedDateIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_VIS_PRESENTED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0538, ""),
  VaccinationVisPresentedDateIsNotAdminDate(MessageObject.VACCINATION, DetectionType.NOT_SAME_AS_ADMIN_DATE, VxuField.VACCINATION_VIS_PRESENTED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0539, ""),
  VaccinationVisPresentedDateIsBeforePublishedDate(MessageObject.VACCINATION, DetectionType.BEFORE_PUBLISHED_DATE, VxuField.VACCINATION_VIS_PRESENTED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0540, ""),
  VaccinationVisPresentedDateIsAfterAdminDate(MessageObject.VACCINATION, DetectionType.AFTER_ADMIN_DATE, VxuField.VACCINATION_VIS_PRESENTED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0541, ""),
  VaccinationVisIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_VIS, "", SeverityLevel.INFO, "100", null, ErrorCode.MQE0542, ""),
  VaccinationVisIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_VIS, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0543, ""),
  VaccinationVisPublishedDateIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_VIS_PUBLISHED_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0544, ""),

  VaccinationLotExpirationDateIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_LOT_EXPIRATION_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0336, "RXA-16"),
  VaccinationLotExpirationDateIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_LOT_EXPIRATION_DATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0337, "RXA-16"),
  VaccinationLotNumberIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_LOT_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0338, "RXA-15"),
  VaccinationLotNumberIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_LOT_NUMBER, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0339, "RXA-15"),
  VaccinationManufacturerCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0340, "RXA-17"),
  VaccinationManufacturerCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0341, "RXA-17"),
  VaccinationManufacturerCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0342, "RXA-17"),
  VaccinationManufacturerCodeIsInvalidForDateAdministered(MessageObject.VACCINATION, DetectionType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0495, "RXA-17"),
  VaccinationManufacturerCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0343, "RXA-17"),
  VaccinationManufacturerCodeIsUnexpectedForDateAdministered(MessageObject.VACCINATION, DetectionType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0494, "RXA-17"),
  VaccinationManufacturerCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0344, "RXA-17"),
  VaccinationOrderControlCodeIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0373, "ORC-1"),
  VaccinationOrderControlCodeIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0369, "ORC-1"),
  VaccinationOrderControlCodeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0370, "ORC-1"),
  VaccinationOrderControlCodeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0371, "ORC-1"),
  VaccinationOrderControlCodeIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0372, "ORC-1"),
  VaccinationOrderFacilityIdIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0442, "ORC-21"),
  VaccinationOrderFacilityIdIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0443, "ORC-21"),
  VaccinationOrderFacilityIdIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0444, "ORC-21"),
  VaccinationOrderFacilityIdIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0445, "ORC-21"),
  VaccinationOrderFacilityIdIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0446, "ORC-21"),
  VaccinationOrderFacilityNameIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ORDER_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0441, "ORC-21"),
  VaccinationOrderedByIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0345, "XCN-12"),
  VaccinationOrderedByIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0346, "XCN-12"),
  VaccinationOrderedByIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0347, "XCN-12"),
  VaccinationOrderedByIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0348, "XCN-12"),
  VaccinationOrderedByIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0349, "XCN-12"),
  VaccinationPlacerOrderNumberIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0384, "ORC-2"),
  VaccinationPlacerOrderNumberIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0385, "ORC-2"),
  VaccinationPlacerOrderNumberIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0386, "ORC-2"),
  VaccinationPlacerOrderNumberIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0387, "ORC-2"),
  VaccinationPlacerOrderNumberIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0388, "ORC-2"),
  VaccinationProductIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0350, "RXA-5"),
  VaccinationProductIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0351, "RXA-5"),
  VaccinationProductIsInvalidForDateAdministered(MessageObject.VACCINATION, DetectionType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0493, "RXA-5"),
  VaccinationProductIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0352, "RXA-5"),
  VaccinationProductIsUnexpectedForDateAdministered(MessageObject.VACCINATION, DetectionType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0492, "RXA-5"),
  VaccinationProductIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0353, "RXA-5"),
  VaccinationRecordedByIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0354, "ORC-10"),
  VaccinationRecordedByIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0355, "ORC-10"),
  VaccinationRecordedByIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0356, "ORC-10"),
  VaccinationRecordedByIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0357, "ORC-10"),
  VaccinationRecordedByIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0358, "ORC-10"),
  VaccinationRefusalReasonConflictsCompletionStatus(MessageObject.VACCINATION, DetectionType.CONFLICTS_COMPLETION_STATUS, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0359, "RXA-18"),
  VaccinationRefusalReasonIsDeprecated(MessageObject.VACCINATION, DetectionType.DEPRECATED, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0360, "RXA-18"),
  VaccinationRefusalReasonIsIgnored(MessageObject.VACCINATION, DetectionType.IGNORED, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0361, "RXA-18"),
  VaccinationRefusalReasonIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0362, "RXA-18"),
  VaccinationRefusalReasonIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0363, "RXA-18"),
  VaccinationRefusalReasonIsUnrecognized(MessageObject.VACCINATION, DetectionType.UNRECOGNIZED, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0364, "RXA-18"),
  VaccinationSystemEntryTimeIsInFuture(MessageObject.VACCINATION, DetectionType.IN_FUTURE, VxuField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0365, "RXA-22"),
  VaccinationSystemEntryTimeIsInvalid(MessageObject.VACCINATION, DetectionType.INVALID, VxuField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0366, "RXA-22"),
  VaccinationSystemEntryTimeIsMissing(MessageObject.VACCINATION, DetectionType.MISSING, VxuField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0367, "RXA-22"),
  UnknownValidationIssue(MessageObject.GENERAL, DetectionType.UNRECOGNIZED, VxuField.CONFIGURATION, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0000, "");
  //@formatter:on
  private static final Map<VxuField, Map<DetectionType, Detection>> fieldIssueMaps =
      new HashMap<>();

  private static final Map<ErrorCode, Detection> errorCodeAttributeMap = new HashMap<>();

  static {
    for (Detection detection : Detection.values()) {
      Map<DetectionType, Detection> map = fieldIssueMaps.get(detection.getTargetField());
      if (map == null) {
        map = new HashMap<>();
        fieldIssueMaps.put(detection.getTargetField(), map);
      }

      map.put(detection.getDetectionType(), detection);

      errorCodeAttributeMap.put(detection.dqaErrorCode, detection);
    }
  }

  /**
   * This replaces the whole PotentialIssues.java class and all the mapping that was done there.
   *
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

  // public static final String CHANGE_PRIORITY_BLOCKED = "Blocked";
  // public static final String CHANGE_PRIORITY_CAN = "Can";
  // public static final String CHANGE_PRIORITY_MAY = "May";
  // public static final String CHANGE_PRIORITY_MUST = "Must";
  // public static final String CHANGE_PRIORITY_SHOULD = "Should";

  private String fieldValue;
  private DetectionType detectionType;
  private MessageObject targetObject;
  private VxuField fieldRef;
  private SeverityLevel severity;
  private String[] hl7Locations;
  private ErrorCode dqaErrorCode;
  private String hl7ErrorCode;
  private ApplicationErrorCode applicationErrorCode;

  public ApplicationErrorCode getApplicationErrorCode() {
    if (applicationErrorCode == null) {
      switch (detectionType) {
        case ADMINISTERED_BUT_APPEARS_TO_HISTORICAL:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case AFTER_ADMIN_DATE:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case AFTER_LOT_EXPIRATION:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case AFTER_MESSAGE_SUBMITTED:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case AFTER_PATIENT_DEATH_DATE:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case AFTER_SUBMISSION:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case AFTER_SYSTEM_ENTRY_DATE:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case ARE_INCONSISTENT:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case BEFORE_BIRTH:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case BEFORE_OR_AFTER_LICENSED_DATE_RANGE:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case BEFORE_OR_AFTER_EXPECTED_DATE_RANGE:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case BEFORE_OR_AFTER_VALID_DATE_FOR_AGE:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case BEFORE_PUBLISHED_DATE:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case BEFORE_VERSION_DATE:
          return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
        case CONFLICTS_COMPLETION_STATUS:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case DEPRECATED:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case DIFFERENT_FROM_PATIENT_ADDRESS:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case DIFF_FROM_START:
          return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
        case EXCEPTION:
          break;
        case HAS_JUNK_NAME:
          break;
        case HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED:
          break;
        case IGNORED:
          break;
        case INCOMPLETE:
          break;
        case INCONSISTENT:
          break;
        case INCORRECT:
          break;
        case INVALID:
          break;
        case INVALID_FOR_BODY_SITE:
          break;
        case INVALID_FOR_DATE_ADMINISTERED:
          break;
        case INVALID_FOR_VACCINE:
          break;
        case INVALID_PREFIXES:
          break;
        case IN_FUTURE:
          break;
        case KNOWN_TEST_NAME:
          break;
        case MAY_BE_AN_INITIAL:
          break;
        case MAY_BE_PREVIOUSLY_REPORTED:
          break;
        case MAY_BE_TEMPORARY_NEWBORN_NAME:
          break;
        case MAY_BE_TEST_NAME:
          break;
        case MAY_INCLUDE_MIDDLE_INITIAL:
          break;
        case MISSING:
          break;
        case MISSING_AND_MULTIPLE_BIRTH_INDICATED:
          break;
        case MISSING_TIMEZONE:
          break;
        case NON_STANDARD:
          break;
        case NOT_PRECISE:
          break;
        case NOT_RESPONSIBLE_PARTY:
          break;
        case NOT_SAME_AS_ADMIN_DATE:
          break;
        case NOT_SPECIFIC:
          break;
        case NOT_VACCINE:
          break;
        case NOT_VALUED_LEGAL:
          break;
        case ON_FIFTEENTH_DAY_OF_MONTH:
          break;
        case ON_FIRST_DAY_OF_MONTH:
          break;
        case ON_LAST_DAY_OF_MONTH:
          break;
        case OUT_OF_DATE:
          break;
        case OUT_OF_ORDER:
          break;
        case REPEATED:
          break;
        case REPORTED_LATE:
          break;
        case SAME_AS_UNDERAGE_PATIENT:
          break;
        case TOO_LONG:
          break;
        case TOO_SHORT:
          break;
        case UNDERAGE:
          break;
        case UNEXPECETDLY_LONG:
          break;
        case UNEXPECTED:
          break;
        case UNEXPECTEDLY_SHORT:
          break;
        case UNEXPECTED_FORMAT:
          break;
        case UNEXPECTED_FOR_DATE_ADMINISTERED:
          break;
        case UNRECOGNIZED:
          break;
        case UNSUPPORTED:
          break;
        case VALUED_AS:
          break;
        case VALUED_BAD_ADDRESS:
          break;
        case VERY_LONG_AGO:
          break;
      }
      return ApplicationErrorCode.INVALID_VALUE;
    }
    return applicationErrorCode;
  }

  Detection(MessageObject entity, DetectionType type, VxuField fieldRef, String valuation,
      SeverityLevel severity, String hl7ErrCode, ApplicationErrorCode applicationErrorCode,
      ErrorCode dqaErrorCode, String hl7Location) {

    this.fieldValue = valuation;
    this.targetObject = entity;
    this.detectionType = type;
    this.fieldRef = fieldRef;
    this.severity = severity;

    this.hl7Locations = new String[] {hl7Location};
    this.dqaErrorCode = dqaErrorCode;
    this.applicationErrorCode = applicationErrorCode;
    this.hl7ErrorCode = hl7ErrCode;

  }

  public String getDisplayText() {
    StringBuilder displayText = new StringBuilder();

    displayText.append(targetObject.getDescription());
    displayText.append(" ");
    displayText.append(fieldRef.getFieldDescription());
    displayText.append(" ");
    displayText.append(detectionType.getText());

    if (!StringUtils.isBlank(fieldValue)) {
      displayText.append(" ").append(fieldValue);
    }

    return displayText.toString();
  }

  public DetectionType getDetectionType() {
    return detectionType;
  }

  public VxuField getTargetField() {
    return fieldRef;
  }

  public MessageObject getTargetObject() {
    return targetObject;
  }

  public ValidationReport build(String value, MetaFieldInfoData meta) {
    ValidationReport found = build(meta);
    found.setValueReceived(value);
    return found;
  }

  public ValidationReport build(MetaFieldInfoData meta) {
    Hl7Location loc = null;
    if (meta != null) {
      MetaFieldInfo mfi = meta.getMetaFieldInfo(this.fieldRef);
      if (mfi != null) {
        loc = mfi.getHl7Location();
      }
    }
    return build(loc);
  }

  public ValidationReport build(Hl7Location loc) {
    ValidationReport found = new ValidationReport();
    if (loc != null) {
      found.getHl7LocationList().add(loc);
    }
    found.setDetection(this);
    return found;
  }

  public static Detection getByDqaErrorCodeString(String codeString) {
    ErrorCode code = ErrorCode.getByCodeString(codeString);
    return errorCodeAttributeMap.get(code);
  }

  public SeverityLevel getSeverity() {
    return severity;
  }

  public String[] getHl7Locations() {
    return hl7Locations;
  }

  public String getDqaErrorCode() {
    return dqaErrorCode.toString();
  }

  public String getHl7ErrorCode() {
    return hl7ErrorCode;
  }

}
