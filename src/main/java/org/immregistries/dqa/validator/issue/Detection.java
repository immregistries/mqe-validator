package org.immregistries.dqa.validator.issue;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.hl7util.ApplicationErrorCode;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.model.Hl7Location;
import org.immregistries.dqa.hl7util.model.MetaFieldInfo;
import org.immregistries.dqa.vxu.MetaFieldInfoData;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Detection {
	GeneralAuthorizationException(IssueObject.GENERAL, IssueType.EXCEPTION, VxuField.AUTHORIZATION, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0002, ""),
	GeneralConfigurationException(IssueObject.GENERAL, IssueType.EXCEPTION, VxuField.CONFIGURATION, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0003, ""),
	GeneralParseException(IssueObject.GENERAL, IssueType.EXCEPTION, VxuField.PARSE, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0004, ""),
	GeneralProcessingException(IssueObject.GENERAL, IssueType.EXCEPTION, VxuField.PROCESSING, "", SeverityLevel.ACCEPT, "207", null, ErrorCode.MQE0005, ""),

	//These might be validated from NIST validations. 
	//Maybe we have an entry for Hl7StructureWarning, Hl7StructureError...
	//We need to figure this out...
	//DQA should not be looking at the ACK type...
	//accept ack type
	
	MessageAcceptAckTypeIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_ACCEPT_ACK_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0006, "MSH-15"),
	MessageAppAckTypeIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_APP_ACK_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0410, "MSH-16"),
	MessageMessageControlIdIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_CONTROL_ID, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0014, "MSH-10"),
	
	//This we validate against other stuff. 
	MessageMessageDateIsInFuture(IssueObject.MESSAGE_HEADER, IssueType.IN_FUTURE, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0015, "MSH-7"),
	MessageMessageDateIsInvalid(IssueObject.MESSAGE_HEADER, IssueType.INVALID, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0016, "MSH-7"),
	MessageMessageDateIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0017, "MSH-7"),
	MessageMessageDateIsNotPrecise(IssueObject.MESSAGE_HEADER, IssueType.NOT_PRECISE, VxuField.MESSAGE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0526, "MSH-7"),
	MessageMessageDateIsMissingTimezone(IssueObject.MESSAGE_HEADER, IssueType.MISSING_TIMEZONE, VxuField.MESSAGE_DATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0527, "MSH-7"),
	MessageMessageDateIsUnexpectedFormat(IssueObject.MESSAGE_HEADER, IssueType.UNEXPECTED_FORMAT, VxuField.MESSAGE_DATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0531, "MSH-7"),
	
	//
	MessageMessageProfileIdIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_PROFILE_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0439, "MSH-21"),
	MessageMessageTriggerIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_TRIGGER, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0018, "MSH-9.2"),
	MessageMessageTypeIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_TYPE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0020, "MSH-9.1"),
	MessageProcessingIdIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_PROCESSING_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0023, "MSH-11"),
	MessageReceivingApplicationIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_RECEIVING_APPLICATION, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0030, "MSH-5"),
	MessageReceivingFacilityIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_RECEIVING_FACILITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0032, "MSH-6"),
	MessageSendingApplicationIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_SENDING_APPLICATION, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0035, "MSH-3"),
	MessageSendingFacilityIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_SENDING_FACILITY, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0037, "MSH-4"),
	MessageSendingResponsibleOrganizationIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_SENDING_RESPONSIBLE_ORGANIZATION, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0556, "MSH-22"),
	
	MessageVersionIsMissing(IssueObject.MESSAGE_HEADER, IssueType.MISSING, VxuField.MESSAGE_VERSION, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0038, "MSH-12"),
	MessageVersionIsUnrecognized(IssueObject.MESSAGE_HEADER, IssueType.UNRECOGNIZED, VxuField.MESSAGE_VERSION, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0039, "MSH-12"),
	MessageVersionIsInvalid(IssueObject.MESSAGE_HEADER, IssueType.INVALID, VxuField.MESSAGE_VERSION, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0523, "MSH-12"),
	
	
	NextOfKinAddressIsDifferentFromPatientAddress(IssueObject.NEXT_OF_KIN, IssueType.DIFFERENT_FROM_PATIENT_ADDRESS, VxuField.NEXT_OF_KIN_ADDRESS, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0056, "NK1-4"),
	NextOfKinAddressIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0057, "NK1-4"),
	NextOfKinAddressCityIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_CITY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0058, "NK1-4.3"),
	NextOfKinAddressCityIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0059, "NK1-4.3"),
	NextOfKinAddressCountryIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0060, "NK1-4.6"),
	NextOfKinAddressCountryIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0061, "NK1-4.6"),
	NextOfKinAddressCountryIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0062, "NK1-4.6"),
	NextOfKinAddressCountryIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0063, "NK1-4.6"),
	NextOfKinAddressCountryIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0064, "NK1-4.6"),
	NextOfKinAddressCountyIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0065, "NK1-4.9"),
	NextOfKinAddressCountyIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0066, "NK1-4.9"),
	NextOfKinAddressCountyIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0067, "NK1-4.9"),
	NextOfKinAddressCountyIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0068, "NK1-4.9"),
	NextOfKinAddressCountyIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0069, "NK1-4.9"),
	NextOfKinAddressStateIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0070, "NK1-4.4"),
	NextOfKinAddressStateIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0071, "NK1-4.4"),
	NextOfKinAddressStateIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0072, "NK1-4.4"),
	NextOfKinAddressStateIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0073, "NK1-4.4"),
	NextOfKinAddressStateIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_STATE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0074, "NK1-4.4"),
	NextOfKinAddressStreetIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0075, "NK1-4.1"),
	NextOfKinAddressStreet2IsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_STREET2, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0076, "NK1-4.2"),
	NextOfKinAddressTypeIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0395, "NK1-4.7"),
	NextOfKinAddressTypeIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0396, "NK1-4.7"),
	NextOfKinAddressTypeIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0397, "NK1-4.7"),
	NextOfKinAddressTypeIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0398, "NK1-4.7"),
	NextOfKinAddressTypeIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0399, "NK1-4.7"),
	NextOfKinAddressTypeIsValuedBadAddress(IssueObject.NEXT_OF_KIN, IssueType.VALUED_BAD_ADDRESS, VxuField.NEXT_OF_KIN_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0522, "NK1-4.7"),
	NextOfKinAddressZipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_ADDRESS_ZIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0077, "NK1-4.5"),
	NextOfKinAddressZipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0078, "NK1-4.5"),
	NextOfKinNameIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_NAME, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0079, "NK1-2"),
	NextOfKinNameFirstIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_NAME_FIRST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0080, "NK1-2.1"),
	NextOfKinNameLastIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_NAME_LAST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0081, "NK1-2.2"),
	NextOfKinPhoneNumberIsIncomplete(IssueObject.NEXT_OF_KIN, IssueType.INCOMPLETE, VxuField.NEXT_OF_KIN_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0082, "NK1-5"),
	NextOfKinPhoneNumberIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0083, "NK1-5"),
	NextOfKinPhoneNumberIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_PHONE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0084, "NK1-5"),
	NextOfKinRelationshipIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0085, "NK1-3"),
	NextOfKinRelationshipIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0086, "NK1-3"),
	NextOfKinRelationshipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0087, "NK1-3"),
	NextOfKinRelationshipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0088, "NK1-3"),
	NextOfKinRelationshipIsNotResponsibleParty(IssueObject.NEXT_OF_KIN, IssueType.NOT_RESPONSIBLE_PARTY, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0089, "NK1-3"),
	NextOfKinRelationshipIsUnexpected(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0485, "NK1-3"),
	NextOfKinRelationshipIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, VxuField.NEXT_OF_KIN_RELATIONSHIP, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0090, "NK1-3"),
	NextOfKinSsnIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, VxuField.NEXT_OF_KIN_SSN, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0091, "NK1-33"),
	ObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, VxuField.OBSERVATION_VALUE,"", SeverityLevel.WARN, "102", null, ErrorCode.MQE0532, "OBX"),
	ObservationValueTypeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATED, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0470, "OBX-2"),
	ObservationValueTypeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0471, "OBX-2"),
	ObservationValueTypeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0472, "OBX-2"),
	ObservationValueTypeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0473, "OBX-2"),
	ObservationValueTypeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, VxuField.OBSERVATION_VALUE_TYPE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0474, "OBX-2"),
	ObservationObservationIdentifierCodeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATED, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0475, "OBX-3"),
	ObservationObservationIdentifierCodeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0476, "OBX-3"),
	ObservationObservationIdentifierCodeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0477, "OBX-3"),
	ObservationObservationIdentifierCodeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0478, "OBX-3"),
	ObservationObservationIdentifierCodeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, VxuField.OBSERVATION_IDENTIFIER_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0479, "OBX-3"),
	ObservationObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, VxuField.OBSERVATION_VALUE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0480, "OBX-5"),
	ObservationDateTimeOfObservationIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, VxuField.OBSERVATION_DATE_TIME_OF_OBSERVATION, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0481, "OBX-14"),
	ObservationDateTimeOfObservationIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, VxuField.OBSERVATION_DATE_TIME_OF_OBSERVATION, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0482, "OBX-14"),
	PatientObjectIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.NONE,"", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0545, "PID-11"),
	PatientAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0092, "PID-11"),
	PatientAddressCityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0093, "PID-11.3"),
	PatientAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0094, "PID-11.3"),
	PatientAddressCountryIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0095, "PID-11.6"),
	PatientAddressCountryIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0096, "PID-11.6"),
	PatientAddressCountryIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0097, "PID-11.6"),
	PatientAddressCountryIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0098, "PID-11.6"),
	PatientAddressCountryIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_COUNTRY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0099, "PID-11.6"),
	PatientAddressCountyIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0100, "PID-11.9"),
	PatientAddressCountyIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0101, "PID-11.9"),
	PatientAddressCountyIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0102, "PID-11.9"),
	PatientAddressCountyIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0103, "PID-11.9"),
	PatientAddressCountyIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_COUNTY, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0104, "PID-11.9"),
	PatientAddressStateIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0105, "PID-11.4"),
	PatientAddressStateIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0106, "PID-11.4"),
	PatientAddressStateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0107, "PID-11.4"),
	PatientAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0108, "PID-11.4"),
	PatientAddressStateIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0109, "PID-11.4"),
	PatientAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0110, "PID-11.1"),
	PatientAddressStreet2IsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_STREET2, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0111, "PID-11.2"),
	PatientAddressTypeIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0451, "PID-11.7"),
	PatientAddressTypeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0517, "PID-11.7"),
	PatientAddressTypeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0518, "PID-11.7"),
	PatientAddressTypeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0519, "PID-11.7"),
	PatientAddressTypeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0520, "PID-11.7"),
	PatientAddressTypeIsValuedBadAddress(IssueObject.PATIENT, IssueType.VALUED_BAD_ADDRESS, VxuField.PATIENT_ADDRESS_TYPE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0521, "PID-11.7"),
	PatientAddressZipIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_ADDRESS_ZIP, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0112, "PID-11.5"),
	PatientAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0113, "PID-11.5"),
	PatientAliasIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ALIAS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0114, "PID-5"),
	PatientBirthDateIsAfterSubmission(IssueObject.PATIENT, IssueType.AFTER_SUBMISSION, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0115, "PID-7"),
	PatientBirthDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0116, "PID-7"),
	PatientBirthDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0117, "PID-7"),
	PatientBirthDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0118, "PID-7"),
	PatientBirthDateIsUnderage(IssueObject.PATIENT, IssueType.UNDERAGE, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0119, "PID-7"),
	PatientBirthDateIsVeryLongAgo(IssueObject.PATIENT, IssueType.VERY_LONG_AGO, VxuField.PATIENT_BIRTH_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0120, "PID-7"),
	PatientBirthIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_BIRTH_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0121, "PID-24"),
	PatientBirthIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_BIRTH_INDICATOR, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0122, "PID-24"),
	PatientBirthOrderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_BIRTH_ORDER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0123, "PID-25"),
	PatientBirthOrderIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_BIRTH_ORDER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0124, "PID-25"),
	PatientBirthOrderIsMissingAndMultipleBirthIndicated(IssueObject.PATIENT, IssueType.MISSING_AND_MULTIPLE_BIRTH_INDICATED, VxuField.PATIENT_BIRTH_ORDER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0125, "PID-25"),
	PatientBirthPlaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_BIRTH_PLACE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0126, "PID-23"),
	PatientBirthRegistryIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_BIRTH_REGISTRY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0127, "PID-3"),
	PatientBirthRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_BIRTH_REGISTRY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0128, "PID-3"),
	PatientClassIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_CLASS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0374, "PV1-2"),
	PatientClassIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_CLASS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0375, "PV1-2"),
	PatientClassIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_CLASS, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0376, "PV1-2"),
	PatientClassIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_CLASS, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0377, "PV1-2"),
	PatientClassIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_CLASS, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0378, "PV1-2"),
	PatientDeathDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0129, "PID-29"),
	PatientDeathDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0130, "PID-29"),
	PatientDeathDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0131, "PID-29"),
	PatientDeathDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_DEATH_DATE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0132, "PID-29"),
	PatientDeathIndicatorIsInconsistent(IssueObject.PATIENT, IssueType.INCONSISTENT, VxuField.PATIENT_DEATH_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0133, "PID-30"),
	PatientDeathIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_DEATH_INDICATOR, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0134, "PID-30"),
	PatientEthnicityIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0135, "PID-22"),
	PatientEthnicityIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0136, "PID-22"),
	PatientEthnicityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0137, "PID-22"),
	PatientEthnicityIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0138, "PID-22"),
	PatientEthnicityIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_ETHNICITY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0139, "PID-22"),
	PatientGenderIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_GENDER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0143, "PID-8"),
	PatientGenderIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_GENDER, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0144, "PID-8"),
	PatientGenderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0145, "PID-8"),
	PatientGenderIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0146, "PID-8"),
	PatientGenderIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_GENDER, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0147, "PID-8"),
	PatientGuardianAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0148, "NK1-4"),
	PatientGuardianAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_CITY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0149, "NK1-4.3"),
	PatientGuardianAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_STATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0150, "NK1-4.4"),
	PatientGuardianAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_STREET, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0151, "NK1-4.1"),
	PatientGuardianAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_ADDRESS_ZIP, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0152, "NK1-4.5"),
	PatientGuardianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_NAME, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0155, "NK1-2"),
	PatientGuardianNameIsSameAsUnderagePatient(IssueObject.PATIENT, IssueType.SAME_AS_UNDERAGE_PATIENT, VxuField.PATIENT_GUARDIAN_NAME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0156, "NK1-2"),
	PatientGuardianNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_NAME_FIRST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0153, "NK1-2.2"),
	PatientGuardianNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_NAME_LAST, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0154, "NK1-2.1"),
	PatientGuardianResponsiblePartyIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0157, "NK1"),
	PatientGuardianPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_PHONE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0158, "NK1-5"),
	PatientGuardianRelationshipIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_GUARDIAN_RELATIONSHIP, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0159, "NK1-3"),
	PatientImmunizationRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0160, "PD1-16"),
	PatientImmunizationRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0161, "PD1-16"),
	PatientImmunizationRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0162, "PD1-16"),
	PatientImmunizationRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0163, "PD1-16"),
	PatientImmunizationRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_IMMUNIZATION_REGISTRY_STATUS, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0164, "PD1-16"),
	PatientMedicaidNumberIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_MEDICAID_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0167, "PID-3"),
	PatientMedicaidNumberIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_MEDICAID_NUMBER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0168, "PID-3"),
	PatientMiddleNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0169, "PID-5.3"),
	PatientMiddleNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_NAME_MIDDLE,"", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0546, "PID-5.3"),
	PatientMiddleNameMayBeInitial(IssueObject.PATIENT, IssueType.MAY_BE_AN_INITIAL, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0170, "PID-5.3"),
	//Some of these weren't represented in the XLS file. 
	PatientMotherSMaidenNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0171, "PID-6.1"),
	PatientMothersMaidenNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_MOTHERS_MAIDEN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0547, "PID-6.1"), 
	PatientMotherSMaidenNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, VxuField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0548, "PID-6.1"),
	PatientMotherSMaidenNameHasInvalidPrefixes(IssueObject.PATIENT, IssueType.INVALID_PREFIXES, VxuField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0549, "PID-6.1"),
	PatientMotherSMaidenNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, VxuField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0550, "PID-6.1"),
	
	PatientNameMayBeTemporaryNewbornName(IssueObject.PATIENT, IssueType.MAY_BE_TEMPORARY_NEWBORN_NAME, VxuField.PATIENT_NAME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0172, "PID-5"),
	PatientNameMayBeTestName(IssueObject.PATIENT, IssueType.MAY_BE_TEST_NAME, VxuField.PATIENT_NAME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0551, "PID-5"),
	PatientNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, VxuField.PATIENT_NAME,"", SeverityLevel.WARN, "102", null, ErrorCode.MQE0173, "PID-5"), 
	PatientNameFirstIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_NAME_FIRST, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0140, "PID-5.2"),
	PatientNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_NAME_FIRST, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0141, "PID-5.2"),
	PatientNameFirstMayIncludeMiddleInitial(IssueObject.PATIENT, IssueType.MAY_INCLUDE_MIDDLE_INITIAL, VxuField.PATIENT_NAME_FIRST, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0142, "PID-5.2"),
	PatientNameLastIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_NAME_LAST, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0165, "PID-5.1"),
	PatientNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_NAME_LAST, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0166, "PID-5.1"),
	PatientNameMiddleIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ERROR, "", null, ErrorCode.MQE0528, ""),
	PatientNameMiddleIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_NAME_MIDDLE, "", SeverityLevel.ERROR, "", null, ErrorCode.MQE0529, ""),
	PatientNameTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0405, "PID-5.7"),
	PatientNameTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0406, "PID-5.7"),
	PatientNameTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0407, "PID-5.7"),
	PatientNameTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0408, "PID-5.7"),
	PatientNameTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0409, "PID-5.7"),
	PatientNameTypeCodeIsNotValuedLegal(IssueObject.PATIENT, IssueType.NOT_VALUED_LEGAL, VxuField.PATIENT_NAME_TYPE_CODE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0516, ""),
	PatientPhoneIsIncomplete(IssueObject.PATIENT, IssueType.INCOMPLETE, VxuField.PATIENT_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0174, "PID-13"),
	PatientPhoneIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PHONE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0175, "PID-13"),
	PatientPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PHONE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0176, "PID-13"),
	PatientPhoneTelUseCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0453, "PID-13.2"),
	PatientPhoneTelUseCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0454, "PID-13.2"),
	PatientPhoneTelUseCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0455, "PID-13.2"),
	PatientPhoneTelUseCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0456, "PID-13.2"),
	PatientPhoneTelUseCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_PHONE_TEL_USE_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0457, "PID-13.2"),
	PatientPhoneTelEquipCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0458, "PID-13.3"),
	PatientPhoneTelEquipCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0459, "PID-13.3"),
	PatientPhoneTelEquipCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0460, "PID-13.3"),
	PatientPhoneTelEquipCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0461, "PID-13.3"),
	PatientPhoneTelEquipCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_PHONE_TEL_EQUIP_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0462, "PID-13.3"),
	PatientPrimaryFacilityIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0177, "PD1-3.3"),
	PatientPrimaryFacilityIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0178, "PD1-3.3"),
	PatientPrimaryFacilityIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0179, "PD1-3.3"),
	PatientPrimaryFacilityIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0180, "PD1-3.3"),
	PatientPrimaryFacilityIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_PRIMARY_FACILITY_ID, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0181, "PD1-3.3"),
	PatientPrimaryFacilityNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PRIMARY_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0182, "PD1-3.1"),
	PatientPrimaryLanguageIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0183, "PID-15"),
	PatientPrimaryLanguageIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0184, "PID-15"),
	PatientPrimaryLanguageIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0185, "PID-15"),
	PatientPrimaryLanguageIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0186, "PID-15"),
	PatientPrimaryLanguageIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_PRIMARY_LANGUAGE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0187, "PID-15"),
	PatientPrimaryPhysicianIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0188, "PD1-4.1"),
	PatientPrimaryPhysicianIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0189, "PD1-4.1"),
	PatientPrimaryPhysicianIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0190, "PD1-4.1"),
	PatientPrimaryPhysicianIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0191, "PD1-4.1"),
	PatientPrimaryPhysicianIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0192, "PD1-4.1"),
	PatientPrimaryPhysicianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PRIMARY_PHYSICIAN_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0193, "PD1-4.2"),
	PatientProtectionIndicatorIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0194, "PD1-12"),
	PatientProtectionIndicatorIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0195, "PD1-12"),
	PatientProtectionIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0196, "PD1-12"),
	PatientProtectionIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0197, "PD1-12"),
	PatientProtectionIndicatorIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_PROTECTION_INDICATOR, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0198, "PD1-12"),
	PatientProtectionIndicatorIsValuedAsNo(IssueObject.PATIENT, IssueType.VALUED_AS, VxuField.PATIENT_PROTECTION_INDICATOR, "no", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0199, "PD1-12"),
	PatientProtectionIndicatorIsValuedAsYes(IssueObject.PATIENT, IssueType.VALUED_AS, VxuField.PATIENT_PROTECTION_INDICATOR, "yes", SeverityLevel.WARN, "102", null, ErrorCode.MQE0200, "PD1-12"),
	PatientPublicityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0201, "PD1-11"),
	PatientPublicityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0202, "PD1-11"),
	PatientPublicityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0203, "PD1-11"),
	PatientPublicityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0204, "PD1-11"),
	PatientPublicityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_PUBLICITY_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0205, "PD1-11"),
	PatientRaceIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_RACE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0206, "PID-10"),
	PatientRaceIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_RACE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0207, "PID-10"),
	PatientRaceIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_RACE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0208, "PID-10"),
	PatientRaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_RACE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0209, "PID-10"),
	PatientRaceIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_RACE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0210, "PID-10"),
	PatientRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_REGISTRY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0211, "PID-3"),
	PatientRegistryIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_REGISTRY_ID, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0212, "PID-3"),
	PatientRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0213, "PD1-16"),
	PatientRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0214, "PD1-16"),
	PatientRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0215, "PD1-16"),
	PatientRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0216, "PD1-16"),
	PatientRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_REGISTRY_STATUS, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0217, "PD1-16"),
	PatientSsnIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_SSN, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0218, "PID-3"),
	PatientSsnIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_SSN, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0219, "PID-3"),
	PatientSubmitterIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_SUBMITTER_ID, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0220, "PID-3"),
	PatientSubmitterIdAuthorityIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0393, "PID-3.4"),
	PatientSubmitterIdTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0394, "PID-3.5"),
	PatientSubmitterIdTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0512, ""),
	PatientSubmitterIdTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.ERROR, "", null, ErrorCode.MQE0513, ""),
	PatientSubmitterIdTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0514, ""),
	PatientSubmitterIdTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, "", SeverityLevel.INFO, "", null, ErrorCode.MQE0515, ""),
	PatientSystemCreationDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_SYSTEM_CREATION_DATE, "", SeverityLevel.ACCEPT, "", null, ErrorCode.MQE0530, "?-?"),
	PatientSystemCreationDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, VxuField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR, "", null, ErrorCode.MQE0552, ""), 
	PatientSystemCreationDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, VxuField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR, "", null, ErrorCode.MQE0533, ""), 
	PatientVfcEffectiveDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0221, "PV1-20.2"),
	PatientVfcEffectiveDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0222, "PV1-20.2"),
	PatientVfcEffectiveDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0223, "PV1-20.2"),
	PatientVfcEffectiveDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_VFC_EFFECTIVE_DATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0224, "PV1-20.2"),
	PatientVfcStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATED, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0225, "PV1-20.1"),
	PatientVfcStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0226, "PV1-20.1"),
	PatientVfcStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0227, "PV1-20.1"),
	PatientVfcStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0228, "PV1-20.1"),
	PatientVfcStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, VxuField.PATIENT_VFC_STATUS, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0229, "PV1-20.1"),
	PatientWicIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, VxuField.PATIENT_WIC_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0230, "PID-3"),
	PatientWicIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, VxuField.PATIENT_WIC_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0231, "PID-3"),
	VaccinationActionCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0232, "RXA-21"),
	VaccinationActionCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0233, "RXA-21"),
	VaccinationActionCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0234, "RXA-21"),
	VaccinationActionCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0235, "RXA-21"),
	VaccinationActionCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ACTION_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0236, "RXA-21"),
	VaccinationActionCodeIsValuedAsAdd(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "add", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0237, "RXA-21"),
	VaccinationActionCodeIsValuedAsAddOrUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "add or update", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0238, "RXA-21"),
	VaccinationActionCodeIsValuedAsDelete(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "delete", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0239, "RXA-21"),
	VaccinationActionCodeIsValuedAsUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ACTION_CODE, "update", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0240, "RXA-21"),
	VaccinationAdminCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0241, "RXA-5"),
	VaccinationAdminCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0242, "RXA-5"),
	VaccinationAdminCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0243, "RXA-5"),
	VaccinationAdminCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0491, "RXA-5"),
	VaccinationAdminCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0244, "RXA-5"),
	VaccinationAdminCodeIsNotSpecific(IssueObject.VACCINATION, IssueType.NOT_SPECIFIC, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0245, "RXA-5"),
	VaccinationAdminCodeIsNotVaccine(IssueObject.VACCINATION, IssueType.NOT_VACCINE, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0246, "RXA-5"),
	VaccinationAdminCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0490, "RXA-5"),
	VaccinationAdminCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.ERROR, "103", null, ErrorCode.MQE0247, "RXA-5"),
	VaccinationAdminCodeIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "not administered", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0248, "RXA-5"),
	VaccinationAdminCodeIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "unknown", SeverityLevel.WARN, "102", null, ErrorCode.MQE0249, "RXA-5"),
	VaccinationAdminCodeTableIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ADMIN_CODE_TABLE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0483, "RXA-5"),
	VaccinationAdminCodeTableIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ADMIN_CODE_TABLE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0484, "RXA-5"),
	VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(IssueObject.VACCINATION, IssueType.MAY_BE_PREVIOUSLY_REPORTED, VxuField.VACCINATION_ADMIN_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0250, "RXA-5"),
	VaccinationAdminDateIsAfterLotExpirationDate(IssueObject.VACCINATION, IssueType.AFTER_LOT_EXPIRATION, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0251, "RXA-3"),
	VaccinationAdminDateIsAfterMessageSubmitted(IssueObject.VACCINATION, IssueType.AFTER_MESSAGE_SUBMITTED, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0252, "RXA-3"),
	VaccinationAdminDateIsAfterPatientDeathDate(IssueObject.VACCINATION, IssueType.AFTER_PATIENT_DEATH_DATE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0253, "RXA-3"),
	VaccinationAdminDateIsAfterSystemEntryDate(IssueObject.VACCINATION, IssueType.AFTER_SYSTEM_ENTRY_DATE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0254, "RXA-3"),
	VaccinationAdminDateIsBeforeBirth(IssueObject.VACCINATION, IssueType.BEFORE_BIRTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0255, "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0256, "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0257, "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0258, "RXA-3"),
	VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0259, "RXA-3"),
	VaccinationAdminDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "102", null, ErrorCode.MQE0260, "RXA-3"),
	VaccinationAdminDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0261, "RXA-3"),
	VaccinationAdminDateIsOn15ThDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIFTEENTH_DAY_OF_MONTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0262, "RXA-3"),
	VaccinationAdminDateIsOnFirstDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIRST_DAY_OF_MONTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0263, "RXA-3"),
	VaccinationAdminDateIsOnLastDayOfMonth(IssueObject.VACCINATION, IssueType.ON_LAST_DAY_OF_MONTH, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0264, "RXA-3"),
	VaccinationAdminDateIsReportedLate(IssueObject.VACCINATION, IssueType.REPORTED_LATE, VxuField.VACCINATION_ADMIN_DATE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0265, "RXA-3"),
	VaccinationAdminDateEndIsDifferentFromStartDate(IssueObject.VACCINATION, IssueType.DIFF_FROM_START, VxuField.VACCINATION_ADMIN_DATE_END, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0266, "RXA-3"),
	VaccinationAdminDateEndIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ADMIN_DATE_END, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0267, "RXA-3"),
	VaccinationAdministeredCodeIsForiegn(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0268, ""),
	VaccinationHistoricalCodeIsForeign(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ACCEPT,  "102", null, ErrorCode.MQE0553, ""),
	VaccinationAdministeredAmountIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0555, "RXA-6"),
	VaccinationAdministeredAmountIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0554, "RXA-6"),
	VaccinationAdministeredAmountIsValuedAsZero(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "zero", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0270, "RXA-6"),
	VaccinationAdministeredAmountIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_ADMINISTERED_AMOUNT, "unknown", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0271, "RXA-6"),
	VaccinationAdministeredUnitIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0447, "RXA-7"),
	VaccinationAdministeredUnitIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0448, "RXA-7"),
	VaccinationAdministeredUnitIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0449, "RXA-7"),
	VaccinationAdministeredUnitIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0272, "RXA-7"),
	VaccinationAdministeredUnitIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ADMINISTERED_UNIT, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0450, "RXA-7"),
	VaccinationBodyRouteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0273, "RXR-1"),
	VaccinationBodyRouteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0274, "RXR-1"),
	VaccinationBodyRouteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0275, "RXR-1"),
	VaccinationBodyRouteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0276, "RXR-1"),
	VaccinationBodyRouteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0277, "RXR-1"),
	VaccinationBodyRouteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_BODY_ROUTE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0278, "RXR-1"),
	VaccinationBodySiteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0279, "RXR-2"),
	VaccinationBodySiteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0280, "RXR-2"),
	VaccinationBodySiteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0281, "RXR-2"),
	VaccinationBodySiteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0282, "RXR-2"),
	VaccinationBodySiteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0283, "RXR-2"),
	VaccinationBodySiteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_BODY_SITE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0284, "RXR-2"),
	VaccinationCompletionStatusIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0285, "RXA-20"),
	VaccinationCompletionStatusIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0286, "RXA-20"),
	VaccinationCompletionStatusIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0287, "RXA-20"),
	VaccinationCompletionStatusIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0288, "RXA-20"),
	VaccinationCompletionStatusIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_COMPLETION_STATUS, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0289, "RXA-20"),
	VaccinationCompletionStatusIsValuedAsCompleted(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "completed", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0290, "RXA-20"),
	VaccinationCompletionStatusIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "not administered", SeverityLevel.INFO, "102", null, ErrorCode.MQE0291, "RXA-20"),
	VaccinationCompletionStatusIsValuedAsPartiallyAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "partially administered", SeverityLevel.INFO, "102", null, ErrorCode.MQE0292, "RXA-20"),
	VaccinationCompletionStatusIsValuedAsRefused(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_COMPLETION_STATUS, "refused", SeverityLevel.INFO, "102", null, ErrorCode.MQE0293, "RXA-20"),
	VaccinationConfidentialityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0294, "ORC-28"),
	VaccinationConfidentialityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0295, "ORC-28"),
	VaccinationConfidentialityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0296, "ORC-28"),
	VaccinationConfidentialityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0297, "ORC-28"),
	VaccinationConfidentialityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0298, "ORC-28"),
	VaccinationConfidentialityCodeIsValuedAsRestricted(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_CONFIDENTIALITY_CODE, "restricted", SeverityLevel.WARN, "102", null, ErrorCode.MQE0299, "ORC-28"),
	VaccinationCptCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0300, "RXA-5"),
	VaccinationCptCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0301, "RXA-5"),
	VaccinationCptCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0302, "RXA-5"),
	VaccinationCptCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0489, "RXA-5"),
	VaccinationCptCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0303, "RXA-5"),
	VaccinationCptCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0488, "RXA-5"),
	VaccinationCptCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_CPT_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0304, "RXA-5"),
	VaccinationCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0305, "RXA-5"),
	VaccinationCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0306, "RXA-5"),
	VaccinationCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0307, "RXA-5"),
	VaccinationCvxCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0487, "RXA-5"),
	VaccinationCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0308, "RXA-5"),
	VaccinationCvxCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0486, "RXA-5"),
	VaccinationCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_CVX_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0309, "RXA-5"),
	VaccinationCvxCodeAndCptCodeAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, VxuField.VACCINATION_CVX_CODE_AND_CPT_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0310, "RXA-5"),
	VaccinationFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0311, "RXA-11.4"),
	VaccinationFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0312, "RXA-11.4"),
	VaccinationFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0313, "RXA-11.4"),
	VaccinationFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0314, "RXA-11.4"),
	VaccinationFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_FACILITY_ID, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0315, "RXA-11.4"),
	VaccinationFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0316, "RXA-11.4"),
	VaccinationFillerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0379, "ORC-3"),
	VaccinationFillerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0380, "ORC-3"),
	VaccinationFillerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0381, "ORC-3"),
	VaccinationFillerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0382, "ORC-3"),
	VaccinationFillerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_FILLER_ORDER_NUMBER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0383, "ORC-3"),
	VaccinationFinancialEligibilityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0465, "OBX-5"),
	VaccinationFinancialEligibilityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0466, "OBX-5"),
	VaccinationFinancialEligibilityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0467, "OBX-5"),
	VaccinationFinancialEligibilityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0468, "OBX-5"),
	VaccinationFinancialEligibilityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0469, "OBX-5"),
	VaccinationGivenByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0317, "RXA-10"),
	VaccinationGivenByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0318, "RXA-10"),
	VaccinationGivenByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0319, "RXA-10"),
	VaccinationGivenByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0320, "RXA-10"),
	VaccinationGivenByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_GIVEN_BY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0321, "RXA-10"),
	VaccinationIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0322, "ORC-3"),
	VaccinationIdOfReceiverIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ID_OF_RECEIVER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0323, "ORC-2"),
	VaccinationIdOfReceiverIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ID_OF_RECEIVER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0324, "ORC-2"),
	VaccinationIdOfSenderIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ID_OF_SENDER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0325, "ORC-3"),
	VaccinationIdOfSenderIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ID_OF_SENDER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0326, "ORC-3"),
	VaccinationInformationSourceIsAdministeredButAppearsToHistorical(IssueObject.VACCINATION, IssueType.ADMINISTERED_BUT_APPEARS_TO_HISTORICAL, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0327, "RXA-9"),
	VaccinationInformationSourceIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0328, "RXA-9"),
	VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(IssueObject.VACCINATION, IssueType.HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0329, "RXA-9"),
	VaccinationInformationSourceIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0330, "RXA-9"),
	VaccinationInformationSourceIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0331, "RXA-9"),
	VaccinationInformationSourceIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0332, "RXA-9"),
	VaccinationInformationSourceIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_INFORMATION_SOURCE, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0333, "RXA-9"),
	VaccinationInformationSourceIsValuedAsAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_INFORMATION_SOURCE, "administered", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0334, "RXA-9"),
	VaccinationInformationSourceIsValuedAsHistorical(IssueObject.VACCINATION, IssueType.VALUED_AS, VxuField.VACCINATION_INFORMATION_SOURCE, "historical", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0335, "RXA-9"),
	VaccinationVisDocumentTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0496, ""),
	VaccinationVisDocumentTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.INFO, "", null, ErrorCode.MQE0497, ""),
	VaccinationVisDocumentTypeIsIncorrect(IssueObject.VACCINATION, IssueType.INCORRECT, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0498, ""),
	VaccinationVisDocumentTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0499, ""),
	VaccinationVisDocumentTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0500, ""),
	VaccinationVisDocumentTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0501, ""),
	VaccinationVisDocumentTypeIsOutOfDate(IssueObject.VACCINATION, IssueType.OUT_OF_DATE, VxuField.VACCINATION_VIS_DOCUMENT_TYPE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0502, ""),
	VaccinationVisVersionDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0503, ""),
	VaccinationVisVersionDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0504, ""),
	VaccinationVisVersionDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0505, ""),
	VaccinationVisVersionDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, VxuField.VACCINATION_VIS_VERSION_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0506, ""),
	VaccinationVisDeliveryDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0507, ""),
	VaccinationVisDeliveryDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0508, ""),
	VaccinationVisDeliveryDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0509, ""),
	VaccinationVisDeliveryDateIsBeforeVersionDate(IssueObject.VACCINATION, IssueType.BEFORE_VERSION_DATE, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0510, ""),
	VaccinationVisDeliveryDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, VxuField.VACCINATION_VIS_DELIVERY_DATE, "", SeverityLevel.WARN, "", null, ErrorCode.MQE0511, ""),
	
	//More that were missing. 
	VaccinationVisPublishedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0534, ""),
	VaccinationVisPublishedDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0535, ""),
	VaccinationVisPublishedDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, VxuField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0536, ""),
	VaccinationVisPresentedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0537, ""),
	VaccinationVisPresentedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0538, ""),
	VaccinationVisPresentedDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, VxuField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0539, ""),
	VaccinationVisPresentedDateIsBeforePublishedDate(IssueObject.VACCINATION, IssueType.BEFORE_PUBLISHED_DATE, VxuField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0540, ""),
	VaccinationVisPresentedDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, VxuField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0541, ""),
	VaccinationVisIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_VIS,"", SeverityLevel.INFO, "100", null, ErrorCode.MQE0542, ""),
	VaccinationVisIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_VIS,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0543, ""),
	VaccinationVisPublishedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.WARN, "", null, ErrorCode.MQE0544, ""),
	
	VaccinationLotExpirationDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_LOT_EXPIRATION_DATE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0336, "RXA-16"),
	VaccinationLotExpirationDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_LOT_EXPIRATION_DATE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0337, "RXA-16"),
	VaccinationLotNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_LOT_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0338, "RXA-15"),
	VaccinationLotNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_LOT_NUMBER, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0339, "RXA-15"),
	VaccinationManufacturerCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0340, "RXA-17"),
	VaccinationManufacturerCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0341, "RXA-17"),
	VaccinationManufacturerCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0342, "RXA-17"),
	VaccinationManufacturerCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0495, "RXA-17"),
	VaccinationManufacturerCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "101", null, ErrorCode.MQE0343, "RXA-17"),
	VaccinationManufacturerCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0494, "RXA-17"),
	VaccinationManufacturerCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_MANUFACTURER_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0344, "RXA-17"),
	VaccinationOrderControlCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0373, "ORC-1"),
	VaccinationOrderControlCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0369, "ORC-1"),
	VaccinationOrderControlCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0370, "ORC-1"),
	VaccinationOrderControlCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0371, "ORC-1"),
	VaccinationOrderControlCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ORDER_CONTROL_CODE, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0372, "ORC-1"),
	VaccinationOrderFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0442, "ORC-21"),
	VaccinationOrderFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0443, "ORC-21"),
	VaccinationOrderFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0444, "ORC-21"),
	VaccinationOrderFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0445, "ORC-21"),
	VaccinationOrderFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ORDER_FACILITY_ID, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0446, "ORC-21"),
	VaccinationOrderFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ORDER_FACILITY_NAME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0441, "ORC-21"),
	VaccinationOrderedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0345, "XCN-12"),
	VaccinationOrderedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0346, "XCN-12"),
	VaccinationOrderedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0347, "XCN-12"),
	VaccinationOrderedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0348, "XCN-12"),
	VaccinationOrderedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_ORDERED_BY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0349, "XCN-12"),
	VaccinationPlacerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0384, "ORC-2"),
	VaccinationPlacerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0385, "ORC-2"),
	VaccinationPlacerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0386, "ORC-2"),
	VaccinationPlacerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0387, "ORC-2"),
	VaccinationPlacerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_PLACER_ORDER_NUMBER, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0388, "ORC-2"),
	VaccinationProductIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0350, "RXA-5"),
	VaccinationProductIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0351, "RXA-5"),
	VaccinationProductIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0493, "RXA-5"),
	VaccinationProductIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0352, "RXA-5"),
	VaccinationProductIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0492, "RXA-5"),
	VaccinationProductIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_PRODUCT, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0353, "RXA-5"),
	VaccinationRecordedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0354, "ORC-10"),
	VaccinationRecordedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0355, "ORC-10"),
	VaccinationRecordedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0356, "ORC-10"),
	VaccinationRecordedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0357, "ORC-10"),
	VaccinationRecordedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_RECORDED_BY, "", SeverityLevel.WARN, "103", null, ErrorCode.MQE0358, "ORC-10"),
	VaccinationRefusalReasonConflictsCompletionStatus(IssueObject.VACCINATION, IssueType.CONFLICTS_COMPLETION_STATUS, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0359, "RXA-18"),
	VaccinationRefusalReasonIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATED, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.WARN, "102", null, ErrorCode.MQE0360, "RXA-18"),
	VaccinationRefusalReasonIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.INFO, "102", null, ErrorCode.MQE0361, "RXA-18"),
	VaccinationRefusalReasonIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0362, "RXA-18"),
	VaccinationRefusalReasonIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0363, "RXA-18"),
	VaccinationRefusalReasonIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, VxuField.VACCINATION_REFUSAL_REASON, "", SeverityLevel.ACCEPT, "103", null, ErrorCode.MQE0364, "RXA-18"),
	VaccinationSystemEntryTimeIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, VxuField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0365, "RXA-22"),
	VaccinationSystemEntryTimeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, VxuField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "102", null, ErrorCode.MQE0366, "RXA-22"),
	VaccinationSystemEntryTimeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, VxuField.VACCINATION_SYSTEM_ENTRY_TIME, "", SeverityLevel.ACCEPT, "101", null, ErrorCode.MQE0367, "RXA-22"),
	UnknownValidationIssue(IssueObject.GENERAL, IssueType.UNRECOGNIZED, VxuField.CONFIGURATION, "", SeverityLevel.ERROR, "101", null, ErrorCode.MQE0000, "")
;

	  private static final Map<VxuField, Map<IssueType, Detection>> fieldIssueMaps = new HashMap<VxuField, Map<IssueType, Detection>>();
	  
	  private static final Map<VxuField, List<Detection>> objectIssueListMap = new HashMap<VxuField, List<Detection>>();
	  
	  private static final Map<ErrorCode, Detection> errorCodeAttributeMap = new HashMap<>();
	  
	  private static final Map<String, Detection> displayTextMap = new HashMap<String, Detection>();
	  static {
		  for (Detection issue : Detection.values()) {
			  Map<IssueType, Detection> map = fieldIssueMaps.get(issue.getTargetField());
			  if (map == null) {
				  map = new HashMap<IssueType, Detection>();
				  fieldIssueMaps.put(issue.getTargetField(), map);
			  }
			  
			  map.put(issue.getIssueType(), issue);
			  
			  List<Detection> objectIssues = objectIssueListMap.get(issue.getTargetObject());
			  if (objectIssues == null) {
				  objectIssues = new ArrayList<Detection>();
			  }
			  objectIssues.add(issue);
			  
			  displayTextMap.put(issue.getDisplayText() + issue.getIssueType().getText(), issue);
			  
			  errorCodeAttributeMap.put(issue.dqaErrorCode,  issue);
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
	  public static List<Detection> getIssuesFor(IssueObject object) {
		  return objectIssueListMap.get(object);
	  }
	  
	  /**
	   * This replaces the whole PotentialIssues.java class
	   * and all the mapping that was done there. 
	   * @param field
	   * @param type
	   * @return
	   */
	  public static Detection get(VxuField field, IssueType type) {
		  Map<IssueType, Detection> fieldIssues = fieldIssueMaps.get(field);
		  if (fieldIssues != null) {
			  return fieldIssues.get(type);
		  }
		  return Detection.UnknownValidationIssue;
	  }
	  
	    public static final String CHANGE_PRIORITY_BLOCKED = "Blocked";
	    public static final String CHANGE_PRIORITY_CAN = "Can";
	    public static final String CHANGE_PRIORITY_MAY = "May";
	    public static final String CHANGE_PRIORITY_MUST = "Must";
	    public static final String CHANGE_PRIORITY_SHOULD = "Should";

	    private String fieldValue = "";
	    private IssueType issueType;
	    private IssueObject targetObject;
	    private VxuField fieldRef;
	    private SeverityLevel severity;
	    private String[] hl7Locations;
	    private ErrorCode dqaErrorCode;
	    private String hl7ErrorCode;
	    private ApplicationErrorCode applicationErrorCode;
	    
	    
	public ApplicationErrorCode getApplicationErrorCode() {
	  if (applicationErrorCode == null)
	  {
	    switch (issueType)
	    {
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
        case BEFORE_OR_AFTER_USAGE_DATE_RANGE:
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
	

  private Detection(IssueObject entity, IssueType type,
			VxuField fieldRef, String valuation, SeverityLevel severity,
			String hl7ErrCode, ApplicationErrorCode applicationErrorCode, ErrorCode dqaErrorCode, String hl7Location) {
	
		this.fieldValue = valuation;
		this.targetObject = entity;
		this.issueType = type;
		this.fieldRef = fieldRef;
		this.severity = severity;
		
		this.hl7Locations = new String[] {hl7Location};
		this.dqaErrorCode = dqaErrorCode;
        this.applicationErrorCode = applicationErrorCode;
		this.hl7ErrorCode = hl7ErrCode;
		
	}
	    

  public String getDisplayText()
  {
    StringBuilder displayText = new StringBuilder();

    displayText.append(targetObject.getDescription());
    displayText.append(" ");
    displayText.append(fieldRef.getFieldDescription());
    displayText.append(" ");
    displayText.append(issueType.getText());
    
    if (!StringUtils.isBlank(fieldValue)) {
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

  public VxuField getTargetField()
  {
    return fieldRef;
  }

  public IssueObject getTargetObject()
  {
    return targetObject;
  }

	public ValidationIssue build(String value, MetaFieldInfoData meta) {
		ValidationIssue found = build(meta);
		found.setValueReceived(value);
		return found;
	}

  public ValidationIssue build(MetaFieldInfoData meta) {
	  Hl7Location loc = null;
	  if (meta != null) {
			MetaFieldInfo mfi = meta.getMetaFieldInfo(this.fieldRef);
			if (mfi != null) {
				loc = mfi.getHl7Location();
			}
		}
		return build(loc);
  }

	public ValidationIssue build(Hl7Location loc) {
		ValidationIssue found = new ValidationIssue();
		if (loc != null) {
			found.getHl7LocationList().add(loc);
		}
		found.setDetection(this);
		return found;
	}


//
//  public static ValidationIssue buildIssue(VxuField field, IssueType type, MetaFieldInfoData meta) {
//	  Detection issue = get(field, type);
//	  return issue.build(meta);
//  }
//
//  public static ValidationIssue buildIssue(VxuField field, IssueType type, String value, MetaFieldInfoData meta) {
//	  Detection issue = get(field, type);
//	  return issue.build(value, meta);
//  }
//
  public static Detection getByDqaErrorCode(ErrorCode code) {
	  return errorCodeAttributeMap.get(code);
  }
  
  public static Detection getByDqaErrorCodeString(String codeString) {
	  ErrorCode code =  ErrorCode.getByCodeString(codeString);
	  return errorCodeAttributeMap.get(code);
  }

  public static Detection getPotentialIssueByDisplayText(String num, IssueType missing) {

		return getPotentialIssueByDisplayText(num + missing.getText());
	}
  
  public static Detection getPotentialIssueByDisplayText(String txt) {
		Detection issue = displayTextMap.get(txt);
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
	return dqaErrorCode.toString();
}

public String getHl7ErrorCode() {
	return hl7ErrorCode;
}


}
