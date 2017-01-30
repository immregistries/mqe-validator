package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.immregistries.dqa.hl7util.SeverityLevel;

public enum MessageAttribute {
	  GeneralAuthorizationException(  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_AUTHORIZATION,"", SeverityLevel.ERROR)
	, GeneralConfigurationException(  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_CONFIGURATION,"", SeverityLevel.ERROR)
	, GeneralParseException(		  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_PARSE,"", SeverityLevel.ERROR)
	, GeneralProcessingException(	  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_PROCESSING,"", SeverityLevel.ERROR)
	, Hl7SegmentIsUnrecognized(		  IssueObject.HL7, IssueType.UNRECOGNIZED, IssueField.HL7_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7SegmentIsInvalid(			  IssueObject.HL7, IssueType.INVALID, IssueField.HL7_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7SegmentsOutOfOrder(		  IssueObject.HL7, IssueType.OUT_OF_ORDER, IssueField.HL7_SEGMENTS,"", SeverityLevel.ERROR)
	, Hl7MshAcceptAckTypeIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAcceptAckTypeIsIgnored(	  IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAcceptAckTypeIsInvalid(	  IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAcceptAckTypeIsMissing(	  IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAcceptAckTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAcceptAckTypeIsValuedAsAlways(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "always", SeverityLevel.ACCEPT)
	, Hl7MshAcceptAckTypeIsValuedAsNever(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "never", SeverityLevel.ACCEPT)
	, Hl7MshAcceptAckTypeIsValuedAsOnlyOnErrors(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "only on errors", SeverityLevel.ACCEPT)
	, Hl7MshAltCharacterSetIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshAltCharacterSetIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshAltCharacterSetIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshAltCharacterSetIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshAltCharacterSetIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshAppAckTypeIsDeprecated(	IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_APP_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAppAckTypeIsIgnored(	IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_APP_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAppAckTypeIsInvalid(	IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_APP_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAppAckTypeIsMissing(	IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_APP_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAppAckTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_APP_ACK_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshAppAckTypeIsValuedAsAlways(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "always", SeverityLevel.ACCEPT)
	, Hl7MshAppAckTypeIsValuedAsNever(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "never", SeverityLevel.ACCEPT)
	, Hl7MshAppAckTypeIsValuedAsOnlyOnErrors(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "only on errors", SeverityLevel.ACCEPT)
	, Hl7MshCharacterSetIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshCharacterSetIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshCharacterSetIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshCharacterSetIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshCharacterSetIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_CHARACTER_SET,"", SeverityLevel.ERROR)
	, Hl7MshCountryCodeIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_COUNTRY_CODE,"", SeverityLevel.ERROR)
	, Hl7MshCountryCodeIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_COUNTRY_CODE,"", SeverityLevel.ERROR)
	, Hl7MshCountryCodeIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_COUNTRY_CODE,"", SeverityLevel.ERROR)
	, Hl7MshCountryCodeIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_COUNTRY_CODE,"", SeverityLevel.ERROR)
	, Hl7MshCountryCodeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_COUNTRY_CODE,"", SeverityLevel.ERROR)
	, Hl7MshEncodingCharacterIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ENCODING_CHARACTER,"", SeverityLevel.ERROR)
	, Hl7MshEncodingCharacterIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ENCODING_CHARACTER,"", SeverityLevel.ERROR)
	, Hl7MshEncodingCharacterIsNonStandard(IssueObject.HL7_MSH, IssueType.NON_STANDARD, IssueField.HL7_MSH_ENCODING_CHARACTER,"", SeverityLevel.ERROR)
	, Hl7MshMessageControlIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_CONTROL_ID,"", SeverityLevel.ERROR)
	, Hl7MshMessageDateIsInFuture(IssueObject.HL7_MSH, IssueType.IN_FUTURE, IssueField.HL7_MSH_MESSAGE_DATE,"", SeverityLevel.ERROR)
	, Hl7MshMessageDateIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_MESSAGE_DATE,"", SeverityLevel.ERROR)
	, Hl7MshMessageDateIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_DATE,"", SeverityLevel.ERROR)
	, Hl7MshMessageDateIsNotPrecise(IssueObject.HL7_MSH, IssueType.NOT_PRECISE, IssueField.HL7_MSH_MESSAGE_DATE,"", SeverityLevel.ERROR)
	, Hl7MshMessageDateIsMissingTimezone(IssueObject.HL7_MSH, IssueType.MISSING_TIMEZONE, IssueField.HL7_MSH_MESSAGE_DATE,"", SeverityLevel.ERROR)
	, Hl7MshMessageProfileIdIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", SeverityLevel.ERROR)
	, Hl7MshMessageProfileIdIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", SeverityLevel.ERROR)
	, Hl7MshMessageProfileIdIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", SeverityLevel.ERROR)
	, Hl7MshMessageProfileIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", SeverityLevel.ERROR)
	, Hl7MshMessageProfileIdIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", SeverityLevel.ERROR)
	, Hl7MshMessageStructureIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_STRUCTURE,"", SeverityLevel.ERROR)
	, Hl7MshMessageStructureIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_STRUCTURE,"", SeverityLevel.ERROR)
	, Hl7MshMessageTriggerIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", SeverityLevel.ERROR)
	, Hl7MshMessageTriggerIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", SeverityLevel.ERROR)
	, Hl7MshMessageTriggerIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", SeverityLevel.ERROR)
	, Hl7MshMessageTypeIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshMessageTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshMessageTypeIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_MESSAGE_TYPE,"", SeverityLevel.ERROR)
	, Hl7MshProcessingIdIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_PROCESSING_ID,"", SeverityLevel.ERROR)
	, Hl7MshProcessingIdIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_PROCESSING_ID,"", SeverityLevel.ERROR)
	, Hl7MshProcessingIdIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_PROCESSING_ID,"", SeverityLevel.ERROR)
	, Hl7MshProcessingIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_PROCESSING_ID,"", SeverityLevel.ERROR)
	, Hl7MshProcessingIdIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_PROCESSING_ID,"", SeverityLevel.ERROR)
	, Hl7MshProcessingIdIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_PROCESSING_ID,"", SeverityLevel.ERROR)
	, Hl7MshProcessingIdIsValuedAsDebug(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "debug", SeverityLevel.ACCEPT)
	, Hl7MshProcessingIdIsValuedAsProduction(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "production", SeverityLevel.ACCEPT)
	, Hl7MshProcessingIdIsValuedAsTraining(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "training", SeverityLevel.ACCEPT)
	, Hl7MshReceivingApplicationIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_RECEIVING_APPLICATION,"", SeverityLevel.ERROR)
	, Hl7MshReceivingApplicationIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_RECEIVING_APPLICATION,"", SeverityLevel.ERROR)
	, Hl7MshReceivingFacilityIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_RECEIVING_FACILITY,"", SeverityLevel.ERROR)
	, Hl7MshReceivingFacilityIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_RECEIVING_FACILITY,"", SeverityLevel.ERROR)
	, Hl7MshSegmentIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7MshSendingApplicationIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_SENDING_APPLICATION,"", SeverityLevel.ERROR)
	, Hl7MshSendingApplicationIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SENDING_APPLICATION,"", SeverityLevel.ERROR)
	, Hl7MshSendingFacilityIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_SENDING_FACILITY,"", SeverityLevel.ERROR)
	, Hl7MshSendingFacilityIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SENDING_FACILITY,"", SeverityLevel.ERROR)
	, Hl7MshVersionIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_VERSION,"", SeverityLevel.ERROR)
	, Hl7MshVersionIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_VERSION,"", SeverityLevel.ERROR)
	, Hl7MshVersionIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_VERSION,"", SeverityLevel.ERROR)
	, Hl7MshVersionIsValuedAs2_3_1(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.3.1", SeverityLevel.ACCEPT)
	, Hl7MshVersionIsValuedAs2_4(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.4", SeverityLevel.ACCEPT)
	, Hl7MshVersionIsValuedAs2_5(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.5", SeverityLevel.ACCEPT)
	, Hl7Nk1SegmentIsMissing(IssueObject.HL7_NK1, IssueType.MISSING, IssueField.HL7_NK1_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7Nk1SegmentIsRepeated(IssueObject.HL7_NK1, IssueType.REPEATED, IssueField.HL7_NK1_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7Nk1SetIdIsMissing(IssueObject.HL7_NK1, IssueType.MISSING, IssueField.HL7_NK1_SET_ID,"", SeverityLevel.ERROR)
	, Hl7ObxSegmentIsMissing(IssueObject.HL7_OBX, IssueType.MISSING, IssueField.HL7_OBX_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7OrcSegmentIsMissing(IssueObject.HL7_ORC, IssueType.MISSING, IssueField.HL7_ORC_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7OrcSegmentIsRepeated(IssueObject.HL7_ORC, IssueType.REPEATED, IssueField.HL7_ORC_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7Pd1SegmentIsMissing(IssueObject.HL7_PD1, IssueType.MISSING, IssueField.HL7_PD1_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7PidSegmentIsMissing(IssueObject.HL7_PID, IssueType.MISSING, IssueField.HL7_PID_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7PidSegmentIsRepeated(IssueObject.HL7_PID, IssueType.REPEATED, IssueField.HL7_PID_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7Pv1SegmentIsMissing(IssueObject.HL7_PV1, IssueType.MISSING, IssueField.HL7_PV1_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7Pv1SegmentIsRepeated(IssueObject.HL7_PV1, IssueType.REPEATED, IssueField.HL7_PV1_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7RxaAdminSubIdCounterIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_ADMIN_SUB_ID_COUNTER,"", SeverityLevel.ERROR)
	, Hl7RxaGiveSubIdIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_GIVE_SUB_ID,"", SeverityLevel.ERROR)
	, Hl7RxaSegmentIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7RxaSegmentIsRepeated(IssueObject.HL7_RXA, IssueType.REPEATED, IssueField.HL7_RXA_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7RxrSegmentIsMissing(IssueObject.HL7_RXR, IssueType.MISSING, IssueField.HL7_RXR_SEGMENT,"", SeverityLevel.ERROR)
	, Hl7RxrSegmentIsRepeated(IssueObject.HL7_RXR, IssueType.REPEATED, IssueField.HL7_RXR_SEGMENT,"", SeverityLevel.ERROR)
	, NextOfKinAddressIsDifferentFromPatientAddress(IssueObject.NEXT_OF_KIN, IssueType.DIFFERENT_FROM_PATIENT_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS,"", SeverityLevel.ERROR)
	, NextOfKinAddressIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS,"", SeverityLevel.ERROR)
	, NextOfKinAddressCityIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCityIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCityIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCityIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCityIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCityIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountryIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountryIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountryIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountryIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountryIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountyIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountyIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountyIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountyIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, NextOfKinAddressCountyIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, NextOfKinAddressStateIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, NextOfKinAddressStateIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, NextOfKinAddressStateIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, NextOfKinAddressStateIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, NextOfKinAddressStateIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, NextOfKinAddressStreetIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET,"", SeverityLevel.ERROR)
	, NextOfKinAddressStreet2IsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET2,"", SeverityLevel.ERROR)
	, NextOfKinAddressTypeIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, NextOfKinAddressTypeIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, NextOfKinAddressTypeIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, NextOfKinAddressTypeIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, NextOfKinAddressTypeIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, NextOfKinAddressTypeIsValuedBadAddress(IssueObject.NEXT_OF_KIN, IssueType.VALUED_BAD_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, NextOfKinAddressZipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", SeverityLevel.ERROR)
	, NextOfKinAddressZipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", SeverityLevel.ERROR)
	, NextOfKinNameIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME,"", SeverityLevel.ERROR)
	, NextOfKinNameFirstIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ERROR)
	, NextOfKinNameFirstIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ERROR)
	, NextOfKinNameFirstIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ERROR)
	, NextOfKinNameFirstIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ERROR)
	, NextOfKinNameFirstIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", SeverityLevel.ERROR)
	, NextOfKinNameLastIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ERROR)
	, NextOfKinNameLastIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ERROR)
	, NextOfKinNameLastIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ERROR)
	, NextOfKinNameLastIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ERROR)
	, NextOfKinNameLastIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", SeverityLevel.ERROR)
	, NextOfKinPhoneNumberIsIncomplete(IssueObject.NEXT_OF_KIN, IssueType.INCOMPLETE, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", SeverityLevel.ERROR)
	, NextOfKinPhoneNumberIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", SeverityLevel.ERROR)
	, NextOfKinPhoneNumberIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", SeverityLevel.ERROR)
	, NextOfKinRelationshipIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, NextOfKinRelationshipIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, NextOfKinRelationshipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, NextOfKinRelationshipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, NextOfKinRelationshipIsNotResponsibleParty(IssueObject.NEXT_OF_KIN, IssueType.NOT_RESPONSIBLE_PARTY, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, NextOfKinRelationshipIsUnexpected(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, NextOfKinRelationshipIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, NextOfKinSsnIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_SSN,"", SeverityLevel.ERROR)
	, ObservationValueTypeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ERROR)
	, ObservationValueTypeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ERROR)
	, ObservationValueTypeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ERROR)
	, ObservationValueTypeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ERROR)
	, ObservationValueTypeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_VALUE_TYPE,"", SeverityLevel.ERROR)
	, ObservationIdentifierCodeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ERROR)
	, ObservationIdentifierCodeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ERROR)
	, ObservationIdentifierCodeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ERROR)
	, ObservationIdentifierCodeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ERROR)
	, ObservationIdentifierCodeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", SeverityLevel.ERROR)
	, ObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE,"", SeverityLevel.ERROR)
	, ObservationDateTimeOfObservationIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", SeverityLevel.ERROR)
	, ObservationDateTimeOfObservationIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", SeverityLevel.ERROR)
	, PatientObjectIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.NONE,"", SeverityLevel.ERROR)
	, PatientAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS,"", SeverityLevel.ERROR)
	, PatientAddressCityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, PatientAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, PatientAddressCityIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, PatientAddressCityIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, PatientAddressCityIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, PatientAddressCityIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, PatientAddressCountryIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, PatientAddressCountryIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, PatientAddressCountryIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, PatientAddressCountryIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, PatientAddressCountryIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTRY,"", SeverityLevel.ERROR)
	, PatientAddressCountyIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, PatientAddressCountyIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, PatientAddressCountyIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, PatientAddressCountyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, PatientAddressCountyIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTY,"", SeverityLevel.ERROR)
	, PatientAddressStateIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, PatientAddressStateIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, PatientAddressStateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, PatientAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, PatientAddressStateIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, PatientAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET,"", SeverityLevel.ERROR)
	, PatientAddressStreet2IsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET2,"", SeverityLevel.ACCEPT)
	, PatientAddressTypeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, PatientAddressTypeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, PatientAddressTypeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, PatientAddressTypeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, PatientAddressTypeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, PatientAddressTypeIsValuedBadAddress(IssueObject.PATIENT, IssueType.VALUED_BAD_ADDRESS, IssueField.PATIENT_ADDRESS_TYPE,"", SeverityLevel.ERROR)
	, PatientAddressZipIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_ZIP,"", SeverityLevel.ERROR)
	, PatientAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_ZIP,"", SeverityLevel.ERROR)
	, PatientAliasIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ALIAS,"", SeverityLevel.ERROR)
	, PatientBirthDateIsAfterSubmission(IssueObject.PATIENT, IssueType.AFTER_SUBMISSION, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ERROR)
	, PatientBirthDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ERROR)
	, PatientBirthDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ERROR)
	, PatientBirthDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ERROR)
	, PatientBirthDateIsUnderage(IssueObject.PATIENT, IssueType.UNDERAGE, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ACCEPT)
	, PatientBirthDateIsVeryLongAgo(IssueObject.PATIENT, IssueType.VERY_LONG_AGO, IssueField.PATIENT_BIRTH_DATE,"", SeverityLevel.ERROR)
	, PatientBirthIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_INDICATOR,"", SeverityLevel.ERROR)
	, PatientBirthIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_INDICATOR,"", SeverityLevel.ERROR)
	, PatientBirthOrderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_ORDER,"", SeverityLevel.ERROR)
	, PatientBirthOrderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_ORDER,"", SeverityLevel.ERROR)
	, PatientBirthOrderIsMissingAndMultipleBirthIndicated(IssueObject.PATIENT, IssueType.MISSING_AND_MULTIPLE_BIRTH_INDICATED, IssueField.PATIENT_BIRTH_ORDER,"", SeverityLevel.ERROR)
	, PatientBirthPlaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ERROR)
	, PatientBirthPlaceIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ERROR)
	, PatientBirthPlaceIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ERROR)
	, PatientBirthPlaceIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ERROR)
	, PatientBirthPlaceIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_BIRTH_PLACE,"", SeverityLevel.ERROR)
	, PatientBirthRegistryIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", SeverityLevel.ERROR)
	, PatientBirthRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", SeverityLevel.ERROR)
	, PatientClassIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_CLASS,"", SeverityLevel.ERROR)
	, PatientClassIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_CLASS,"", SeverityLevel.ERROR)
	, PatientClassIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_CLASS,"", SeverityLevel.ERROR)
	, PatientClassIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_CLASS,"", SeverityLevel.ERROR)
	, PatientClassIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_CLASS,"", SeverityLevel.ERROR)
	, PatientDeathDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ERROR)
	, PatientDeathDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ERROR)
	, PatientDeathDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ERROR)
	, PatientDeathDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_DATE,"", SeverityLevel.ERROR)
	, PatientDeathIndicatorIsInconsistent(IssueObject.PATIENT, IssueType.INCONSISTENT, IssueField.PATIENT_DEATH_INDICATOR,"", SeverityLevel.ERROR)
	, PatientDeathIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_INDICATOR,"", SeverityLevel.ERROR)
	, PatientEthnicityIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ERROR)
	, PatientEthnicityIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ERROR)
	, PatientEthnicityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ERROR)
	, PatientEthnicityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ERROR)
	, PatientEthnicityIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ETHNICITY,"", SeverityLevel.ERROR)
	, PatientGenderIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_GENDER,"", SeverityLevel.ERROR)
	, PatientGenderIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_GENDER,"", SeverityLevel.ERROR)
	, PatientGenderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_GENDER,"", SeverityLevel.ERROR)
	, PatientGenderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GENDER,"", SeverityLevel.ERROR)
	, PatientGenderIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_GENDER,"", SeverityLevel.ERROR)
	, PatientGuardianAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS,"", SeverityLevel.ERROR)
	, PatientGuardianAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_CITY,"", SeverityLevel.ERROR)
	, PatientGuardianAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STATE,"", SeverityLevel.ERROR)
	, PatientGuardianAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STREET,"", SeverityLevel.ERROR)
	, PatientGuardianAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_ZIP,"", SeverityLevel.ERROR)
	, PatientGuardianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME,"", SeverityLevel.ERROR)
	, PatientGuardianNameIsSameAsUnderagePatient(IssueObject.PATIENT, IssueType.SAME_AS_UNDERAGE_PATIENT, IssueField.PATIENT_GUARDIAN_NAME,"", SeverityLevel.ERROR)
	, PatientGuardianNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_GUARDIAN_NAME,"", SeverityLevel.ERROR)
	, PatientGuardianNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientGuardianNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_LAST,"", SeverityLevel.ERROR)
	, PatientGuardianResponsiblePartyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY,"", SeverityLevel.ERROR)
	, PatientGuardianPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_PHONE,"", SeverityLevel.ERROR)
	, PatientGuardianRelationshipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RELATIONSHIP,"", SeverityLevel.ERROR)
	, PatientImmunityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ERROR)
	, PatientImmunityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ERROR)
	, PatientImmunityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ERROR)
	, PatientImmunityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ERROR)
	, PatientImmunityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNITY_CODE,"", SeverityLevel.ERROR)
	, PatientImmunizationRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientImmunizationRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientImmunizationRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientImmunizationRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientImmunizationRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientMedicaidNumberIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MEDICAID_NUMBER,"", SeverityLevel.ERROR)
	, PatientMedicaidNumberIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MEDICAID_NUMBER,"", SeverityLevel.ERROR)
	, PatientMiddleNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ERROR)
	, PatientMiddleNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ERROR)
	, PatientMiddleNameMayBeInitial(IssueObject.PATIENT, IssueType.MAY_BE_AN_INITIAL, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ERROR)
	, PatientMiddleNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ERROR)
	, PatientMiddleNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ERROR)
	, PatientMiddleNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ERROR)
	, PatientMiddleNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MIDDLE_NAME,"", SeverityLevel.ERROR)
	, PatientMothersMaidenNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientMotherSMaidenNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientMotherSMaidenNameHasInvalidPrefixes(IssueObject.PATIENT, IssueType.INVALID_PREFIXES, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientMotherSMaidenNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientMotherSMaidenNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientMotherSMaidenNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientMotherSMaidenNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientMotherSMaidenNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", SeverityLevel.ERROR)
	, PatientNameMayBeTemporaryNewbornName(IssueObject.PATIENT, IssueType.MAY_BE_TEMPORARY_NEWBORN_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ERROR)
	, PatientNameMayBeTestName(IssueObject.PATIENT, IssueType.MAY_BE_TEST_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ERROR)
	, PatientNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ERROR)
	, PatientNameIsAKnownTestName(IssueObject.PATIENT, IssueType.KNOWN_TEST_NAME, IssueField.PATIENT_NAME,"", SeverityLevel.ERROR)
	, PatientNameFirstIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientNameFirstIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientNameFirstIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientNameFirstIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientNameFirstIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientNameFirstMayIncludeMiddleInitial(IssueObject.PATIENT, IssueType.MAY_INCLUDE_MIDDLE_INITIAL, IssueField.PATIENT_NAME_FIRST,"", SeverityLevel.ERROR)
	, PatientNameLastIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ERROR)
	, PatientNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ERROR)
	, PatientNameLastIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ERROR)
	, PatientNameLastIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ERROR)
	, PatientNameLastIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ERROR)
	, PatientNameLastIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_LAST,"", SeverityLevel.ERROR)
	, PatientNameTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientNameTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientNameTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientNameTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientNameTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientNameTypeCodeIsNotValuedLegal(IssueObject.PATIENT, IssueType.NOT_VALUED_LEGAL, IssueField.PATIENT_NAME_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneIsIncomplete(IssueObject.PATIENT, IssueType.INCOMPLETE, IssueField.PATIENT_PHONE,"", SeverityLevel.ERROR)
	, PatientPhoneIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE,"", SeverityLevel.ERROR)
	, PatientPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE,"", SeverityLevel.ERROR)
	, PatientPhoneTelUseCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelUseCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelUseCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelUseCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelUseCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelEquipCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelEquipCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelEquipCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelEquipCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ERROR)
	, PatientPhoneTelEquipCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", SeverityLevel.ERROR)
	, PatientPrimaryFacilityIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryFacilityIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryFacilityIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryFacilityIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryFacilityIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryFacilityNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_NAME,"", SeverityLevel.ERROR)
	, PatientPrimaryLanguageIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ERROR)
	, PatientPrimaryLanguageIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ERROR)
	, PatientPrimaryLanguageIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ERROR)
	, PatientPrimaryLanguageIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ERROR)
	, PatientPrimaryLanguageIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", SeverityLevel.ERROR)
	, PatientPrimaryPhysicianIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryPhysicianIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryPhysicianIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryPhysicianIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryPhysicianIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", SeverityLevel.ERROR)
	, PatientPrimaryPhysicianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_NAME,"", SeverityLevel.ERROR)
	, PatientProtectionIndicatorIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ERROR)
	, PatientProtectionIndicatorIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ERROR)
	, PatientProtectionIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ERROR)
	, PatientProtectionIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ERROR)
	, PatientProtectionIndicatorIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PROTECTION_INDICATOR,"", SeverityLevel.ERROR)
	, PatientProtectionIndicatorIsValuedAsNo(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "no", SeverityLevel.ACCEPT)
	, PatientProtectionIndicatorIsValuedAsYes(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "yes", SeverityLevel.ACCEPT)
	, PatientPublicityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ERROR)
	, PatientPublicityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ERROR)
	, PatientPublicityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ERROR)
	, PatientPublicityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ERROR)
	, PatientPublicityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PUBLICITY_CODE,"", SeverityLevel.ERROR)
	, PatientRaceIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_RACE,"", SeverityLevel.ERROR)
	, PatientRaceIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_RACE,"", SeverityLevel.ERROR)
	, PatientRaceIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_RACE,"", SeverityLevel.ERROR)
	, PatientRaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_RACE,"", SeverityLevel.ERROR)
	, PatientRaceIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_RACE,"", SeverityLevel.ERROR)
	, PatientRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_ID,"", SeverityLevel.ERROR)
	, PatientRegistryIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_ID,"", SeverityLevel.ERROR)
	, PatientRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_STATUS,"", SeverityLevel.ERROR)
	, PatientSsnIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SSN,"", SeverityLevel.ACCEPT)
	, PatientSsnIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SSN,"", SeverityLevel.ACCEPT)
	, PatientSubmitterIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID,"", SeverityLevel.ERROR)
	, PatientSubmitterIdAuthorityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_AUTHORITY,"", SeverityLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", SeverityLevel.ERROR)
	, PatientSystemCreationDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR)
	, PatientSystemCreationDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR)
	, PatientSystemCreationDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR)
	, PatientSystemCreationDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", SeverityLevel.ERROR)
	, PatientVfcEffectiveDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ERROR)
	, PatientVfcEffectiveDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ERROR)
	, PatientVfcEffectiveDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ERROR)
	, PatientVfcEffectiveDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", SeverityLevel.ERROR)
	, PatientVfcStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ERROR)
	, PatientVfcStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ERROR)
	, PatientVfcStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ERROR)
	, PatientVfcStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ERROR)
	, PatientVfcStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_VFC_STATUS,"", SeverityLevel.ERROR)
	, PatientWicIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_WIC_ID,"", SeverityLevel.ERROR)
	, PatientWicIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_WIC_ID,"", SeverityLevel.ERROR)
	, VaccinationActionCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ERROR)
	, VaccinationActionCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ERROR)
	, VaccinationActionCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ERROR)
	, VaccinationActionCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ERROR)
	, VaccinationActionCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ACTION_CODE,"", SeverityLevel.ERROR)
	, VaccinationActionCodeIsValuedAsAdd(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsAddOrUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add or update", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsDelete(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "delete", SeverityLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "update", SeverityLevel.ACCEPT)
	, VaccinationAdministeredCodeIsForiegn(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.ERROR)
	, VaccinationHistoricalCodeIsForeign(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", SeverityLevel.WARN)
	, VaccinationAdminCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsNotSpecific(IssueObject.VACCINATION, IssueType.NOT_SPECIFIC, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsNotVaccine(IssueObject.VACCINATION, IssueType.NOT_VACCINE, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "not administered", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "unknown", SeverityLevel.ACCEPT)
	, VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(IssueObject.VACCINATION, IssueType.MAY_BE_PREVIOUSLY_REPORTED, IssueField.VACCINATION_ADMIN_CODE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeTableIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", SeverityLevel.ERROR)
	, VaccinationAdminCodeTableIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsAfterLotExpirationDate(IssueObject.VACCINATION, IssueType.AFTER_LOT_EXPIRATION, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsAfterMessageSubmitted(IssueObject.VACCINATION, IssueType.AFTER_MESSAGE_SUBMITTED, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsAfterPatientDeathDate(IssueObject.VACCINATION, IssueType.AFTER_PATIENT_DEATH_DATE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsAfterSystemEntryDate(IssueObject.VACCINATION, IssueType.AFTER_SYSTEM_ENTRY_DATE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsBeforeBirth(IssueObject.VACCINATION, IssueType.BEFORE_BIRTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsOn15ThDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIFTEENTH_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsOnFirstDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIRST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsOnLastDayOfMonth(IssueObject.VACCINATION, IssueType.ON_LAST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateIsReportedLate(IssueObject.VACCINATION, IssueType.REPORTED_LATE, IssueField.VACCINATION_ADMIN_DATE,"", SeverityLevel.ERROR)
	, VaccinationAdminDateEndIsDifferentFromStartDate(IssueObject.VACCINATION, IssueType.DIFF_FROM_START, IssueField.VACCINATION_ADMIN_DATE_END,"", SeverityLevel.ERROR)
	, VaccinationAdminDateEndIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE_END,"", SeverityLevel.ERROR)
	, VaccinationAdministeredAmountIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", SeverityLevel.ERROR)
	, VaccinationAdministeredAmountIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", SeverityLevel.ERROR)
	, VaccinationAdministeredAmountIsValuedAsZero(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "zero", SeverityLevel.ACCEPT)
	, VaccinationAdministeredAmountIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "unknown", SeverityLevel.ACCEPT)
	, VaccinationAdministeredUnitIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ERROR)
	, VaccinationAdministeredUnitIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ERROR)
	, VaccinationAdministeredUnitIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ERROR)
	, VaccinationAdministeredUnitIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ERROR)
	, VaccinationAdministeredUnitIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", SeverityLevel.ERROR)
	, VaccinationBodyRouteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ERROR)
	, VaccinationBodyRouteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ERROR)
	, VaccinationBodyRouteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ERROR)
	, VaccinationBodyRouteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ERROR)
	, VaccinationBodyRouteIsInvalidForBodySiteIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_BODY_SITE, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ERROR)
	, VaccinationBodyRouteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ERROR)
	, VaccinationBodyRouteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_ROUTE,"", SeverityLevel.ERROR)
	, VaccinationBodySiteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ERROR)
	, VaccinationBodySiteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ERROR)
	, VaccinationBodySiteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ERROR)
	, VaccinationBodySiteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ERROR)
	, VaccinationBodySiteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ERROR)
	, VaccinationBodySiteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_SITE,"", SeverityLevel.ERROR)
	, VaccinationCompletionStatusIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ERROR)
	, VaccinationCompletionStatusIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ERROR)
	, VaccinationCompletionStatusIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ERROR)
	, VaccinationCompletionStatusIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ERROR)
	, VaccinationCompletionStatusIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_COMPLETION_STATUS,"", SeverityLevel.ERROR)
	, VaccinationCompletionStatusIsValuedAsCompleted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "completed", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "not administered", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsPartiallyAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "partially administered", SeverityLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsRefused(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "refused", SeverityLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationConfidentialityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationConfidentialityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationConfidentialityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationConfidentialityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationConfidentialityCodeIsValuedAsRestricted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "restricted", SeverityLevel.ACCEPT)
	, VaccinationCptCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationCptCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationCptCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationCptCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationCptCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationCptCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationCptCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationCvxCodeAndCptCodeAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_CVX_CODE_AND_CPT_CODE,"", SeverityLevel.ERROR)
	, VaccinationFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_NAME,"", SeverityLevel.ERROR)
	, VaccinationFacilityTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ERROR)
	, VaccinationFacilityTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ERROR)
	, VaccinationFacilityTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ERROR)
	, VaccinationFacilityTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ERROR)
	, VaccinationFacilityTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_TYPE,"", SeverityLevel.ERROR)
	, VaccinationFacilityTypeIsValuedAsPublic(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "public", SeverityLevel.ACCEPT)
	, VaccinationFacilityTypeIsValuedAsPrivate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "private", SeverityLevel.ACCEPT)
	, VaccinationFillerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationFillerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationFillerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationFillerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationFillerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationGivenByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ERROR)
	, VaccinationGivenByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ERROR)
	, VaccinationGivenByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ERROR)
	, VaccinationGivenByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ERROR)
	, VaccinationGivenByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_GIVEN_BY,"", SeverityLevel.ERROR)
	, VaccinationIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID,"", SeverityLevel.ERROR)
	, VaccinationIdOfReceiverIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_RECEIVER,"", SeverityLevel.ERROR)
	, VaccinationIdOfReceiverIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_RECEIVER,"", SeverityLevel.ERROR)
	, VaccinationIdOfSenderIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_SENDER,"", SeverityLevel.ERROR)
	, VaccinationIdOfSenderIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_SENDER,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsAdministeredButAppearsToHistorical(IssueObject.VACCINATION, IssueType.ADMIN_BUT_APPEARS_HISTORICAL, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(IssueObject.VACCINATION, IssueType.HISTORICAL_BUT_APPEARS_ADMIN, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_INFORMATION_SOURCE,"", SeverityLevel.ERROR)
	, VaccinationInformationSourceIsValuedAsAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "administered", SeverityLevel.ACCEPT)
	, VaccinationInformationSourceIsValuedAsHistorical(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "historical", SeverityLevel.ACCEPT)
	, VaccinationVisIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS,"", SeverityLevel.ERROR)
	, VaccinationVisIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS,"", SeverityLevel.ERROR)
	, VaccinationVisIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS,"", SeverityLevel.ERROR)
	, VaccinationVisCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationVisCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationVisCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationVisCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationVisCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_CVX_CODE,"", SeverityLevel.ERROR)
	, VaccinationVisDocumentTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ERROR)
	, VaccinationVisDocumentTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ERROR)
	, VaccinationVisDocumentTypeIsIncorrect(IssueObject.VACCINATION, IssueType.INCORRECT, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ERROR)
	, VaccinationVisDocumentTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ERROR)
	, VaccinationVisDocumentTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ERROR)
	, VaccinationVisDocumentTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ERROR)
	, VaccinationVisDocumentTypeIsOutOfDate(IssueObject.VACCINATION, IssueType.OUT_OF_DATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", SeverityLevel.ERROR)
	, VaccinationVisPublishedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPublishedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPublishedDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPublishedDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPresentedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPresentedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPresentedDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPresentedDateIsBeforePublishedDate(IssueObject.VACCINATION, IssueType.BEFORE_PUBLISHED_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ERROR)
	, VaccinationVisPresentedDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", SeverityLevel.ERROR)
	, VaccinationLotExpirationDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", SeverityLevel.ERROR)
	, VaccinationLotExpirationDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", SeverityLevel.ERROR)
	, VaccinationLotNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationLotNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationManufacturerCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ERROR)
	, VaccinationManufacturerCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ERROR)
	, VaccinationManufacturerCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ERROR)
	, VaccinationManufacturerCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ERROR)
	, VaccinationManufacturerCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ERROR)
	, VaccinationManufacturerCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ERROR)
	, VaccinationManufacturerCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_MANUFACTURER_CODE,"", SeverityLevel.ERROR)
	, VaccinationOrderControlCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ERROR)
	, VaccinationOrderControlCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ERROR)
	, VaccinationOrderControlCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ERROR)
	, VaccinationOrderControlCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ERROR)
	, VaccinationOrderControlCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", SeverityLevel.ERROR)
	, VaccinationOrderFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationOrderFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationOrderFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationOrderFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationOrderFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", SeverityLevel.ERROR)
	, VaccinationOrderFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_NAME,"", SeverityLevel.ERROR)
	, VaccinationOrderedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ERROR)
	, VaccinationOrderedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ERROR)
	, VaccinationOrderedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ERROR)
	, VaccinationOrderedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ERROR)
	, VaccinationOrderedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDERED_BY,"", SeverityLevel.ERROR)
	, VaccinationPlacerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationPlacerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationPlacerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationPlacerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationPlacerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", SeverityLevel.ERROR)
	, VaccinationProductIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ERROR)
	, VaccinationProductIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ERROR)
	, VaccinationProductIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ERROR)
	, VaccinationProductIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ERROR)
	, VaccinationProductIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ERROR)
	, VaccinationProductIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PRODUCT,"", SeverityLevel.ERROR)
	, VaccinationRecordedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ERROR)
	, VaccinationRecordedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ERROR)
	, VaccinationRecordedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ERROR)
	, VaccinationRecordedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ERROR)
	, VaccinationRecordedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_RECORDED_BY,"", SeverityLevel.ERROR)
	, VaccinationRefusalReasonConflictsCompletionStatus(IssueObject.VACCINATION, IssueType.CONFLICTS_WITH_COMPLETION_STATUS, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ERROR)
	, VaccinationRefusalReasonIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ERROR)
	, VaccinationRefusalReasonIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ERROR)
	, VaccinationRefusalReasonIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ERROR)
	, VaccinationRefusalReasonIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ERROR)
	, VaccinationRefusalReasonIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_REFUSAL_REASON,"", SeverityLevel.ERROR)
	, VaccinationSystemEntryTimeIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", SeverityLevel.ERROR)
	, VaccinationSystemEntryTimeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", SeverityLevel.ERROR)
	, VaccinationSystemEntryTimeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", SeverityLevel.ERROR)
	, VaccinationTradeNameIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ERROR)
	, VaccinationTradeNameIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ERROR)
	, VaccinationTradeNameIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ERROR)
	, VaccinationTradeNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ERROR)
	, VaccinationTradeNameIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_TRADE_NAME,"", SeverityLevel.ERROR)
	, VaccinationTradeNameAndVaccineAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_VACCINE,"", SeverityLevel.ERROR)
	, VaccinationTradeNameAndManufacturerAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_MANUFACTURER,"", SeverityLevel.ERROR)
	, VaccinationValidityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationValidityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationValidityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationValidityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationValidityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VALIDITY_CODE,"", SeverityLevel.ERROR)
	, VaccinationValidityCodeIsValuedAsValid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "valid", SeverityLevel.ACCEPT)
	, VaccinationValidityCodeIsValuedAsInvalid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "invalid", SeverityLevel.ACCEPT)
	, UnknownValidationIssue(IssueObject.GENERAL, IssueType.MISSING, IssueField.GENERAL_PROCESSING,"", SeverityLevel.ERROR)
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
