package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PotentialIssue {
	  GeneralAuthorizationException(  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_AUTHORIZATION,"", IssueLevel.ERROR)
	, GeneralConfigurationException(  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_CONFIGURATION,"", IssueLevel.ERROR)
	, GeneralParseException(		  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_PARSE,"", IssueLevel.ERROR)
	, GeneralProcessingException(	  IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_PROCESSING,"", IssueLevel.ERROR)
	, Hl7SegmentIsUnrecognized(		  IssueObject.HL7, IssueType.UNRECOGNIZED, IssueField.HL7_SEGMENT,"", IssueLevel.ERROR)
	, Hl7SegmentIsInvalid(			  IssueObject.HL7, IssueType.INVALID, IssueField.HL7_SEGMENT,"", IssueLevel.ERROR)
	, Hl7SegmentsOutOfOrder(		  IssueObject.HL7, IssueType.OUT_OF_ORDER, IssueField.HL7_SEGMENTS,"", IssueLevel.ERROR)
	, Hl7MshAcceptAckTypeIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAcceptAckTypeIsIgnored(	  IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAcceptAckTypeIsInvalid(	  IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAcceptAckTypeIsMissing(	  IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAcceptAckTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAcceptAckTypeIsValuedAsAlways(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "always", IssueLevel.ACCEPT)
	, Hl7MshAcceptAckTypeIsValuedAsNever(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "never", IssueLevel.ACCEPT)
	, Hl7MshAcceptAckTypeIsValuedAsOnlyOnErrors(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "only on errors", IssueLevel.ACCEPT)
	, Hl7MshAltCharacterSetIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshAltCharacterSetIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshAltCharacterSetIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshAltCharacterSetIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshAltCharacterSetIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshAppAckTypeIsDeprecated(	IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAppAckTypeIsIgnored(	IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAppAckTypeIsInvalid(	IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAppAckTypeIsMissing(	IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAppAckTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueLevel.ERROR)
	, Hl7MshAppAckTypeIsValuedAsAlways(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "always", IssueLevel.ACCEPT)
	, Hl7MshAppAckTypeIsValuedAsNever(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "never", IssueLevel.ACCEPT)
	, Hl7MshAppAckTypeIsValuedAsOnlyOnErrors(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "only on errors", IssueLevel.ACCEPT)
	, Hl7MshCharacterSetIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshCharacterSetIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshCharacterSetIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshCharacterSetIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshCharacterSetIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_CHARACTER_SET,"", IssueLevel.ERROR)
	, Hl7MshCountryCodeIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueLevel.ERROR)
	, Hl7MshCountryCodeIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueLevel.ERROR)
	, Hl7MshCountryCodeIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueLevel.ERROR)
	, Hl7MshCountryCodeIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueLevel.ERROR)
	, Hl7MshCountryCodeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueLevel.ERROR)
	, Hl7MshEncodingCharacterIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ENCODING_CHARACTER,"", IssueLevel.ERROR)
	, Hl7MshEncodingCharacterIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ENCODING_CHARACTER,"", IssueLevel.ERROR)
	, Hl7MshEncodingCharacterIsNonStandard(IssueObject.HL7_MSH, IssueType.NON_STANDARD, IssueField.HL7_MSH_ENCODING_CHARACTER,"", IssueLevel.ERROR)
	, Hl7MshMessageControlIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_CONTROL_ID,"", IssueLevel.ERROR)
	, Hl7MshMessageDateIsInFuture(IssueObject.HL7_MSH, IssueType.IN_FUTURE, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueLevel.ERROR)
	, Hl7MshMessageDateIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueLevel.ERROR)
	, Hl7MshMessageDateIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueLevel.ERROR)
	, Hl7MshMessageDateIsNotPrecise(IssueObject.HL7_MSH, IssueType.NOT_PRECISE, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueLevel.ERROR)
	, Hl7MshMessageDateIsMissingTimezone(IssueObject.HL7_MSH, IssueType.MISSING_TIMEZONE, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueLevel.ERROR)
	, Hl7MshMessageProfileIdIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueLevel.ERROR)
	, Hl7MshMessageProfileIdIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueLevel.ERROR)
	, Hl7MshMessageProfileIdIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueLevel.ERROR)
	, Hl7MshMessageProfileIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueLevel.ERROR)
	, Hl7MshMessageProfileIdIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueLevel.ERROR)
	, Hl7MshMessageStructureIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_STRUCTURE,"", IssueLevel.ERROR)
	, Hl7MshMessageStructureIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_STRUCTURE,"", IssueLevel.ERROR)
	, Hl7MshMessageTriggerIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", IssueLevel.ERROR)
	, Hl7MshMessageTriggerIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", IssueLevel.ERROR)
	, Hl7MshMessageTriggerIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", IssueLevel.ERROR)
	, Hl7MshMessageTypeIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_TYPE,"", IssueLevel.ERROR)
	, Hl7MshMessageTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_TYPE,"", IssueLevel.ERROR)
	, Hl7MshMessageTypeIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_MESSAGE_TYPE,"", IssueLevel.ERROR)
	, Hl7MshProcessingIdIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_PROCESSING_ID,"", IssueLevel.ERROR)
	, Hl7MshProcessingIdIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_PROCESSING_ID,"", IssueLevel.ERROR)
	, Hl7MshProcessingIdIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_PROCESSING_ID,"", IssueLevel.ERROR)
	, Hl7MshProcessingIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_PROCESSING_ID,"", IssueLevel.ERROR)
	, Hl7MshProcessingIdIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_PROCESSING_ID,"", IssueLevel.ERROR)
	, Hl7MshProcessingIdIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_PROCESSING_ID,"", IssueLevel.ERROR)
	, Hl7MshProcessingIdIsValuedAsDebug(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "debug", IssueLevel.ACCEPT)
	, Hl7MshProcessingIdIsValuedAsProduction(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "production", IssueLevel.ACCEPT)
	, Hl7MshProcessingIdIsValuedAsTraining(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "training", IssueLevel.ACCEPT)
	, Hl7MshReceivingApplicationIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_RECEIVING_APPLICATION,"", IssueLevel.ERROR)
	, Hl7MshReceivingApplicationIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_RECEIVING_APPLICATION,"", IssueLevel.ERROR)
	, Hl7MshReceivingFacilityIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_RECEIVING_FACILITY,"", IssueLevel.ERROR)
	, Hl7MshReceivingFacilityIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_RECEIVING_FACILITY,"", IssueLevel.ERROR)
	, Hl7MshSegmentIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SEGMENT,"", IssueLevel.ERROR)
	, Hl7MshSendingApplicationIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_SENDING_APPLICATION,"", IssueLevel.ERROR)
	, Hl7MshSendingApplicationIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SENDING_APPLICATION,"", IssueLevel.ERROR)
	, Hl7MshSendingFacilityIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_SENDING_FACILITY,"", IssueLevel.ERROR)
	, Hl7MshSendingFacilityIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SENDING_FACILITY,"", IssueLevel.ERROR)
	, Hl7MshVersionIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_VERSION,"", IssueLevel.ERROR)
	, Hl7MshVersionIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_VERSION,"", IssueLevel.ERROR)
	, Hl7MshVersionIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_VERSION,"", IssueLevel.ERROR)
	, Hl7MshVersionIsValuedAs2_3_1(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.3.1", IssueLevel.ACCEPT)
	, Hl7MshVersionIsValuedAs2_4(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.4", IssueLevel.ACCEPT)
	, Hl7MshVersionIsValuedAs2_5(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.5", IssueLevel.ACCEPT)
	, Hl7Nk1SegmentIsMissing(IssueObject.HL7_NK1, IssueType.MISSING, IssueField.HL7_NK1_SEGMENT,"", IssueLevel.ERROR)
	, Hl7Nk1SegmentIsRepeated(IssueObject.HL7_NK1, IssueType.REPEATED, IssueField.HL7_NK1_SEGMENT,"", IssueLevel.ERROR)
	, Hl7Nk1SetIdIsMissing(IssueObject.HL7_NK1, IssueType.MISSING, IssueField.HL7_NK1_SET_ID,"", IssueLevel.ERROR)
	, Hl7ObxSegmentIsMissing(IssueObject.HL7_OBX, IssueType.MISSING, IssueField.HL7_OBX_SEGMENT,"", IssueLevel.ERROR)
	, Hl7OrcSegmentIsMissing(IssueObject.HL7_ORC, IssueType.MISSING, IssueField.HL7_ORC_SEGMENT,"", IssueLevel.ERROR)
	, Hl7OrcSegmentIsRepeated(IssueObject.HL7_ORC, IssueType.REPEATED, IssueField.HL7_ORC_SEGMENT,"", IssueLevel.ERROR)
	, Hl7Pd1SegmentIsMissing(IssueObject.HL7_PD1, IssueType.MISSING, IssueField.HL7_PD1_SEGMENT,"", IssueLevel.ERROR)
	, Hl7PidSegmentIsMissing(IssueObject.HL7_PID, IssueType.MISSING, IssueField.HL7_PID_SEGMENT,"", IssueLevel.ERROR)
	, Hl7PidSegmentIsRepeated(IssueObject.HL7_PID, IssueType.REPEATED, IssueField.HL7_PID_SEGMENT,"", IssueLevel.ERROR)
	, Hl7Pv1SegmentIsMissing(IssueObject.HL7_PV1, IssueType.MISSING, IssueField.HL7_PV1_SEGMENT,"", IssueLevel.ERROR)
	, Hl7Pv1SegmentIsRepeated(IssueObject.HL7_PV1, IssueType.REPEATED, IssueField.HL7_PV1_SEGMENT,"", IssueLevel.ERROR)
	, Hl7RxaAdminSubIdCounterIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_ADMIN_SUB_ID_COUNTER,"", IssueLevel.ERROR)
	, Hl7RxaGiveSubIdIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_GIVE_SUB_ID,"", IssueLevel.ERROR)
	, Hl7RxaSegmentIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_SEGMENT,"", IssueLevel.ERROR)
	, Hl7RxaSegmentIsRepeated(IssueObject.HL7_RXA, IssueType.REPEATED, IssueField.HL7_RXA_SEGMENT,"", IssueLevel.ERROR)
	, Hl7RxrSegmentIsMissing(IssueObject.HL7_RXR, IssueType.MISSING, IssueField.HL7_RXR_SEGMENT,"", IssueLevel.ERROR)
	, Hl7RxrSegmentIsRepeated(IssueObject.HL7_RXR, IssueType.REPEATED, IssueField.HL7_RXR_SEGMENT,"", IssueLevel.ERROR)
	, NextOfKinAddressIsDifferentFromPatientAddress(IssueObject.NEXT_OF_KIN, IssueType.DIFFERENT_FROM_PATIENT_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS,"", IssueLevel.ERROR)
	, NextOfKinAddressIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS,"", IssueLevel.ERROR)
	, NextOfKinAddressCityIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueLevel.ERROR)
	, NextOfKinAddressCityIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueLevel.ERROR)
	, NextOfKinAddressCityIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueLevel.ERROR)
	, NextOfKinAddressCityIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueLevel.ERROR)
	, NextOfKinAddressCityIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueLevel.ERROR)
	, NextOfKinAddressCityIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountryIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountryIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountryIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountryIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountryIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountyIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountyIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountyIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountyIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, NextOfKinAddressCountyIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, NextOfKinAddressStateIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueLevel.ERROR)
	, NextOfKinAddressStateIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueLevel.ERROR)
	, NextOfKinAddressStateIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueLevel.ERROR)
	, NextOfKinAddressStateIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueLevel.ERROR)
	, NextOfKinAddressStateIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueLevel.ERROR)
	, NextOfKinAddressStreetIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET,"", IssueLevel.ERROR)
	, NextOfKinAddressStreet2IsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET2,"", IssueLevel.ERROR)
	, NextOfKinAddressTypeIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, NextOfKinAddressTypeIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, NextOfKinAddressTypeIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, NextOfKinAddressTypeIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, NextOfKinAddressTypeIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, NextOfKinAddressTypeIsValuedBadAddress(IssueObject.NEXT_OF_KIN, IssueType.VALUED_BAD_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, NextOfKinAddressZipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", IssueLevel.ERROR)
	, NextOfKinAddressZipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", IssueLevel.ERROR)
	, NextOfKinNameIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME,"", IssueLevel.ERROR)
	, NextOfKinNameFirstIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueLevel.ERROR)
	, NextOfKinNameFirstIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueLevel.ERROR)
	, NextOfKinNameFirstIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueLevel.ERROR)
	, NextOfKinNameFirstIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueLevel.ERROR)
	, NextOfKinNameFirstIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueLevel.ERROR)
	, NextOfKinNameLastIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueLevel.ERROR)
	, NextOfKinNameLastIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueLevel.ERROR)
	, NextOfKinNameLastIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueLevel.ERROR)
	, NextOfKinNameLastIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueLevel.ERROR)
	, NextOfKinNameLastIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueLevel.ERROR)
	, NextOfKinPhoneNumberIsIncomplete(IssueObject.NEXT_OF_KIN, IssueType.INCOMPLETE, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", IssueLevel.ERROR)
	, NextOfKinPhoneNumberIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", IssueLevel.ERROR)
	, NextOfKinPhoneNumberIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", IssueLevel.ERROR)
	, NextOfKinRelationshipIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueLevel.ERROR)
	, NextOfKinRelationshipIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueLevel.ERROR)
	, NextOfKinRelationshipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueLevel.ERROR)
	, NextOfKinRelationshipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueLevel.ERROR)
	, NextOfKinRelationshipIsNotResponsibleParty(IssueObject.NEXT_OF_KIN, IssueType.NOT_RESPONSIBLE_PARTY, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueLevel.ERROR)
	, NextOfKinRelationshipIsUnexpected(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueLevel.ERROR)
	, NextOfKinRelationshipIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueLevel.ERROR)
	, NextOfKinSsnIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_SSN,"", IssueLevel.ERROR)
	, ObservationValueTypeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_VALUE_TYPE,"", IssueLevel.ERROR)
	, ObservationValueTypeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_VALUE_TYPE,"", IssueLevel.ERROR)
	, ObservationValueTypeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_VALUE_TYPE,"", IssueLevel.ERROR)
	, ObservationValueTypeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE_TYPE,"", IssueLevel.ERROR)
	, ObservationValueTypeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_VALUE_TYPE,"", IssueLevel.ERROR)
	, ObservationIdentifierCodeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueLevel.ERROR)
	, ObservationIdentifierCodeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueLevel.ERROR)
	, ObservationIdentifierCodeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueLevel.ERROR)
	, ObservationIdentifierCodeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueLevel.ERROR)
	, ObservationIdentifierCodeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueLevel.ERROR)
	, ObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE,"", IssueLevel.ERROR)
	, ObservationDateTimeOfObservationIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", IssueLevel.ERROR)
	, ObservationDateTimeOfObservationIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", IssueLevel.ERROR)
	, PatientObjectIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.NONE,"", IssueLevel.ERROR)
	, PatientAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS,"", IssueLevel.ERROR)
	, PatientAddressCityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_CITY,"", IssueLevel.ERROR)
	, PatientAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_CITY,"", IssueLevel.ERROR)
	, PatientAddressCityIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", IssueLevel.ERROR)
	, PatientAddressCityIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", IssueLevel.ERROR)
	, PatientAddressCityIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_ADDRESS_CITY,"", IssueLevel.ERROR)
	, PatientAddressCityIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_ADDRESS_CITY,"", IssueLevel.ERROR)
	, PatientAddressCountryIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, PatientAddressCountryIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, PatientAddressCountryIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, PatientAddressCountryIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, PatientAddressCountryIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueLevel.ERROR)
	, PatientAddressCountyIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, PatientAddressCountyIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, PatientAddressCountyIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, PatientAddressCountyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, PatientAddressCountyIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueLevel.ERROR)
	, PatientAddressStateIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_STATE,"", IssueLevel.ERROR)
	, PatientAddressStateIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_STATE,"", IssueLevel.ERROR)
	, PatientAddressStateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_STATE,"", IssueLevel.ERROR)
	, PatientAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STATE,"", IssueLevel.ERROR)
	, PatientAddressStateIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_STATE,"", IssueLevel.ERROR)
	, PatientAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET,"", IssueLevel.ERROR)
	, PatientAddressStreet2IsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET2,"", IssueLevel.ACCEPT)
	, PatientAddressTypeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, PatientAddressTypeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, PatientAddressTypeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, PatientAddressTypeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, PatientAddressTypeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, PatientAddressTypeIsValuedBadAddress(IssueObject.PATIENT, IssueType.VALUED_BAD_ADDRESS, IssueField.PATIENT_ADDRESS_TYPE,"", IssueLevel.ERROR)
	, PatientAddressZipIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_ZIP,"", IssueLevel.ERROR)
	, PatientAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_ZIP,"", IssueLevel.ERROR)
	, PatientAliasIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ALIAS,"", IssueLevel.ERROR)
	, PatientBirthDateIsAfterSubmission(IssueObject.PATIENT, IssueType.AFTER_SUBMISSION, IssueField.PATIENT_BIRTH_DATE,"", IssueLevel.ERROR)
	, PatientBirthDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_BIRTH_DATE,"", IssueLevel.ERROR)
	, PatientBirthDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_DATE,"", IssueLevel.ERROR)
	, PatientBirthDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_DATE,"", IssueLevel.ERROR)
	, PatientBirthDateIsUnderage(IssueObject.PATIENT, IssueType.UNDERAGE, IssueField.PATIENT_BIRTH_DATE,"", IssueLevel.ACCEPT)
	, PatientBirthDateIsVeryLongAgo(IssueObject.PATIENT, IssueType.VERY_LONG_AGO, IssueField.PATIENT_BIRTH_DATE,"", IssueLevel.ERROR)
	, PatientBirthIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_INDICATOR,"", IssueLevel.ERROR)
	, PatientBirthIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_INDICATOR,"", IssueLevel.ERROR)
	, PatientBirthOrderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_ORDER,"", IssueLevel.ERROR)
	, PatientBirthOrderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_ORDER,"", IssueLevel.ERROR)
	, PatientBirthOrderIsMissingAndMultipleBirthIndicated(IssueObject.PATIENT, IssueType.MISSING_AND_MULTIPLE_BIRTH_INDICATED, IssueField.PATIENT_BIRTH_ORDER,"", IssueLevel.ERROR)
	, PatientBirthPlaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_PLACE,"", IssueLevel.ERROR)
	, PatientBirthPlaceIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", IssueLevel.ERROR)
	, PatientBirthPlaceIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", IssueLevel.ERROR)
	, PatientBirthPlaceIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_BIRTH_PLACE,"", IssueLevel.ERROR)
	, PatientBirthPlaceIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_BIRTH_PLACE,"", IssueLevel.ERROR)
	, PatientBirthRegistryIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", IssueLevel.ERROR)
	, PatientBirthRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", IssueLevel.ERROR)
	, PatientClassIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_CLASS,"", IssueLevel.ERROR)
	, PatientClassIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_CLASS,"", IssueLevel.ERROR)
	, PatientClassIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_CLASS,"", IssueLevel.ERROR)
	, PatientClassIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_CLASS,"", IssueLevel.ERROR)
	, PatientClassIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_CLASS,"", IssueLevel.ERROR)
	, PatientDeathDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_DEATH_DATE,"", IssueLevel.ERROR)
	, PatientDeathDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_DEATH_DATE,"", IssueLevel.ERROR)
	, PatientDeathDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_DEATH_DATE,"", IssueLevel.ERROR)
	, PatientDeathDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_DATE,"", IssueLevel.ERROR)
	, PatientDeathIndicatorIsInconsistent(IssueObject.PATIENT, IssueType.INCONSISTENT, IssueField.PATIENT_DEATH_INDICATOR,"", IssueLevel.ERROR)
	, PatientDeathIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_INDICATOR,"", IssueLevel.ERROR)
	, PatientEthnicityIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ETHNICITY,"", IssueLevel.ERROR)
	, PatientEthnicityIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ETHNICITY,"", IssueLevel.ERROR)
	, PatientEthnicityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ETHNICITY,"", IssueLevel.ERROR)
	, PatientEthnicityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ETHNICITY,"", IssueLevel.ERROR)
	, PatientEthnicityIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ETHNICITY,"", IssueLevel.ERROR)
	, PatientGenderIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_GENDER,"", IssueLevel.ERROR)
	, PatientGenderIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_GENDER,"", IssueLevel.ERROR)
	, PatientGenderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_GENDER,"", IssueLevel.ERROR)
	, PatientGenderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GENDER,"", IssueLevel.ERROR)
	, PatientGenderIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_GENDER,"", IssueLevel.ERROR)
	, PatientGuardianAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS,"", IssueLevel.ERROR)
	, PatientGuardianAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_CITY,"", IssueLevel.ERROR)
	, PatientGuardianAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STATE,"", IssueLevel.ERROR)
	, PatientGuardianAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STREET,"", IssueLevel.ERROR)
	, PatientGuardianAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_ZIP,"", IssueLevel.ERROR)
	, PatientGuardianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME,"", IssueLevel.ERROR)
	, PatientGuardianNameIsSameAsUnderagePatient(IssueObject.PATIENT, IssueType.SAME_AS_UNDERAGE_PATIENT, IssueField.PATIENT_GUARDIAN_NAME,"", IssueLevel.ERROR)
	, PatientGuardianNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_GUARDIAN_NAME,"", IssueLevel.ERROR)
	, PatientGuardianNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientGuardianNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_LAST,"", IssueLevel.ERROR)
	, PatientGuardianResponsiblePartyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY,"", IssueLevel.ERROR)
	, PatientGuardianPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_PHONE,"", IssueLevel.ERROR)
	, PatientGuardianRelationshipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RELATIONSHIP,"", IssueLevel.ERROR)
	, PatientImmunityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNITY_CODE,"", IssueLevel.ERROR)
	, PatientImmunityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNITY_CODE,"", IssueLevel.ERROR)
	, PatientImmunityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNITY_CODE,"", IssueLevel.ERROR)
	, PatientImmunityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNITY_CODE,"", IssueLevel.ERROR)
	, PatientImmunityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNITY_CODE,"", IssueLevel.ERROR)
	, PatientImmunizationRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientImmunizationRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientImmunizationRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientImmunizationRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientImmunizationRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientMedicaidNumberIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MEDICAID_NUMBER,"", IssueLevel.ERROR)
	, PatientMedicaidNumberIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MEDICAID_NUMBER,"", IssueLevel.ERROR)
	, PatientMiddleNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MIDDLE_NAME,"", IssueLevel.ERROR)
	, PatientMiddleNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MIDDLE_NAME,"", IssueLevel.ERROR)
	, PatientMiddleNameMayBeInitial(IssueObject.PATIENT, IssueType.MAY_BE_AN_INITIAL, IssueField.PATIENT_MIDDLE_NAME,"", IssueLevel.ERROR)
	, PatientMiddleNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", IssueLevel.ERROR)
	, PatientMiddleNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", IssueLevel.ERROR)
	, PatientMiddleNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MIDDLE_NAME,"", IssueLevel.ERROR)
	, PatientMiddleNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MIDDLE_NAME,"", IssueLevel.ERROR)
	, PatientMothersMaidenNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientMotherSMaidenNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientMotherSMaidenNameHasInvalidPrefixes(IssueObject.PATIENT, IssueType.INVALID_PREFIXES, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientMotherSMaidenNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientMotherSMaidenNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientMotherSMaidenNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientMotherSMaidenNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientMotherSMaidenNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueLevel.ERROR)
	, PatientNameMayBeTemporaryNewbornName(IssueObject.PATIENT, IssueType.MAY_BE_TEMPORARY_NEWBORN_NAME, IssueField.PATIENT_NAME,"", IssueLevel.ERROR)
	, PatientNameMayBeTestName(IssueObject.PATIENT, IssueType.MAY_BE_TEST_NAME, IssueField.PATIENT_NAME,"", IssueLevel.ERROR)
	, PatientNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_NAME,"", IssueLevel.ERROR)
	, PatientNameIsAKnownTestName(IssueObject.PATIENT, IssueType.KNOWN_TEST_NAME, IssueField.PATIENT_NAME,"", IssueLevel.ERROR)
	, PatientNameFirstIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientNameFirstIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientNameFirstIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientNameFirstIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientNameFirstIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientNameFirstMayIncludeMiddleInitial(IssueObject.PATIENT, IssueType.MAY_INCLUDE_MIDDLE_INITIAL, IssueField.PATIENT_NAME_FIRST,"", IssueLevel.ERROR)
	, PatientNameLastIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_LAST,"", IssueLevel.ERROR)
	, PatientNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_LAST,"", IssueLevel.ERROR)
	, PatientNameLastIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_LAST,"", IssueLevel.ERROR)
	, PatientNameLastIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_LAST,"", IssueLevel.ERROR)
	, PatientNameLastIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_LAST,"", IssueLevel.ERROR)
	, PatientNameLastIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_LAST,"", IssueLevel.ERROR)
	, PatientNameTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientNameTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientNameTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientNameTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientNameTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientNameTypeCodeIsNotValuedLegal(IssueObject.PATIENT, IssueType.NOT_VALUED_LEGAL, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientPhoneIsIncomplete(IssueObject.PATIENT, IssueType.INCOMPLETE, IssueField.PATIENT_PHONE,"", IssueLevel.ERROR)
	, PatientPhoneIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE,"", IssueLevel.ERROR)
	, PatientPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE,"", IssueLevel.ERROR)
	, PatientPhoneTelUseCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelUseCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelUseCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelUseCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelUseCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelEquipCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelEquipCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelEquipCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelEquipCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueLevel.ERROR)
	, PatientPhoneTelEquipCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueLevel.ERROR)
	, PatientPrimaryFacilityIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueLevel.ERROR)
	, PatientPrimaryFacilityIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueLevel.ERROR)
	, PatientPrimaryFacilityIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueLevel.ERROR)
	, PatientPrimaryFacilityIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueLevel.ERROR)
	, PatientPrimaryFacilityIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueLevel.ERROR)
	, PatientPrimaryFacilityNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_NAME,"", IssueLevel.ERROR)
	, PatientPrimaryLanguageIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueLevel.ERROR)
	, PatientPrimaryLanguageIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueLevel.ERROR)
	, PatientPrimaryLanguageIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueLevel.ERROR)
	, PatientPrimaryLanguageIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueLevel.ERROR)
	, PatientPrimaryLanguageIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueLevel.ERROR)
	, PatientPrimaryPhysicianIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueLevel.ERROR)
	, PatientPrimaryPhysicianIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueLevel.ERROR)
	, PatientPrimaryPhysicianIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueLevel.ERROR)
	, PatientPrimaryPhysicianIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueLevel.ERROR)
	, PatientPrimaryPhysicianIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueLevel.ERROR)
	, PatientPrimaryPhysicianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_NAME,"", IssueLevel.ERROR)
	, PatientProtectionIndicatorIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueLevel.ERROR)
	, PatientProtectionIndicatorIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueLevel.ERROR)
	, PatientProtectionIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueLevel.ERROR)
	, PatientProtectionIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueLevel.ERROR)
	, PatientProtectionIndicatorIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueLevel.ERROR)
	, PatientProtectionIndicatorIsValuedAsNo(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "no", IssueLevel.ACCEPT)
	, PatientProtectionIndicatorIsValuedAsYes(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "yes", IssueLevel.ACCEPT)
	, PatientPublicityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PUBLICITY_CODE,"", IssueLevel.ERROR)
	, PatientPublicityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PUBLICITY_CODE,"", IssueLevel.ERROR)
	, PatientPublicityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PUBLICITY_CODE,"", IssueLevel.ERROR)
	, PatientPublicityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PUBLICITY_CODE,"", IssueLevel.ERROR)
	, PatientPublicityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PUBLICITY_CODE,"", IssueLevel.ERROR)
	, PatientRaceIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_RACE,"", IssueLevel.ERROR)
	, PatientRaceIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_RACE,"", IssueLevel.ERROR)
	, PatientRaceIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_RACE,"", IssueLevel.ERROR)
	, PatientRaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_RACE,"", IssueLevel.ERROR)
	, PatientRaceIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_RACE,"", IssueLevel.ERROR)
	, PatientRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_ID,"", IssueLevel.ERROR)
	, PatientRegistryIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_ID,"", IssueLevel.ERROR)
	, PatientRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_STATUS,"", IssueLevel.ERROR)
	, PatientSsnIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SSN,"", IssueLevel.ACCEPT)
	, PatientSsnIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SSN,"", IssueLevel.ACCEPT)
	, PatientSubmitterIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID,"", IssueLevel.ERROR)
	, PatientSubmitterIdAuthorityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_AUTHORITY,"", IssueLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientSubmitterIdTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueLevel.ERROR)
	, PatientSystemCreationDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueLevel.ERROR)
	, PatientSystemCreationDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueLevel.ERROR)
	, PatientSystemCreationDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueLevel.ERROR)
	, PatientSystemCreationDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueLevel.ERROR)
	, PatientVfcEffectiveDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueLevel.ERROR)
	, PatientVfcEffectiveDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueLevel.ERROR)
	, PatientVfcEffectiveDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueLevel.ERROR)
	, PatientVfcEffectiveDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueLevel.ERROR)
	, PatientVfcStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_VFC_STATUS,"", IssueLevel.ERROR)
	, PatientVfcStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_VFC_STATUS,"", IssueLevel.ERROR)
	, PatientVfcStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_STATUS,"", IssueLevel.ERROR)
	, PatientVfcStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_STATUS,"", IssueLevel.ERROR)
	, PatientVfcStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_VFC_STATUS,"", IssueLevel.ERROR)
	, PatientWicIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_WIC_ID,"", IssueLevel.ERROR)
	, PatientWicIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_WIC_ID,"", IssueLevel.ERROR)
	, VaccinationActionCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ACTION_CODE,"", IssueLevel.ERROR)
	, VaccinationActionCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ACTION_CODE,"", IssueLevel.ERROR)
	, VaccinationActionCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ACTION_CODE,"", IssueLevel.ERROR)
	, VaccinationActionCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ACTION_CODE,"", IssueLevel.ERROR)
	, VaccinationActionCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ACTION_CODE,"", IssueLevel.ERROR)
	, VaccinationActionCodeIsValuedAsAdd(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add", IssueLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsAddOrUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add or update", IssueLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsDelete(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "delete", IssueLevel.ACCEPT)
	, VaccinationActionCodeIsValuedAsUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "update", IssueLevel.ACCEPT)
	, VaccinationAdministeredCodeIsForiegn(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", IssueLevel.ERROR)
	, VaccinationHistoricalCodeIsForeign(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "foreign", IssueLevel.WARN)
	, VaccinationAdminCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsNotSpecific(IssueObject.VACCINATION, IssueType.NOT_SPECIFIC, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsNotVaccine(IssueObject.VACCINATION, IssueType.NOT_VACCINE, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "not administered", IssueLevel.ACCEPT)
	, VaccinationAdminCodeIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "unknown", IssueLevel.ACCEPT)
	, VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(IssueObject.VACCINATION, IssueType.MAY_BE_PREVIOUSLY_REPORTED, IssueField.VACCINATION_ADMIN_CODE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeTableIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", IssueLevel.ERROR)
	, VaccinationAdminCodeTableIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsAfterLotExpirationDate(IssueObject.VACCINATION, IssueType.AFTER_LOT_EXPIRATION, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsAfterMessageSubmitted(IssueObject.VACCINATION, IssueType.AFTER_MESSAGE_SUBMITTED, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsAfterPatientDeathDate(IssueObject.VACCINATION, IssueType.AFTER_PATIENT_DEATH_DATE, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsAfterSystemEntryDate(IssueObject.VACCINATION, IssueType.AFTER_SYSTEM_ENTRY_DATE, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsBeforeBirth(IssueObject.VACCINATION, IssueType.BEFORE_BIRTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsOn15ThDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIFTEENTH_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsOnFirstDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIRST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsOnLastDayOfMonth(IssueObject.VACCINATION, IssueType.ON_LAST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateIsReportedLate(IssueObject.VACCINATION, IssueType.REPORTED_LATE, IssueField.VACCINATION_ADMIN_DATE,"", IssueLevel.ERROR)
	, VaccinationAdminDateEndIsDifferentFromStartDate(IssueObject.VACCINATION, IssueType.DIFF_FROM_START, IssueField.VACCINATION_ADMIN_DATE_END,"", IssueLevel.ERROR)
	, VaccinationAdminDateEndIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE_END,"", IssueLevel.ERROR)
	, VaccinationAdministeredAmountIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", IssueLevel.ERROR)
	, VaccinationAdministeredAmountIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", IssueLevel.ERROR)
	, VaccinationAdministeredAmountIsValuedAsZero(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "zero", IssueLevel.ACCEPT)
	, VaccinationAdministeredAmountIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "unknown", IssueLevel.ACCEPT)
	, VaccinationAdministeredUnitIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueLevel.ERROR)
	, VaccinationAdministeredUnitIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueLevel.ERROR)
	, VaccinationAdministeredUnitIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueLevel.ERROR)
	, VaccinationAdministeredUnitIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueLevel.ERROR)
	, VaccinationAdministeredUnitIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueLevel.ERROR)
	, VaccinationBodyRouteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_ROUTE,"", IssueLevel.ERROR)
	, VaccinationBodyRouteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_ROUTE,"", IssueLevel.ERROR)
	, VaccinationBodyRouteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_ROUTE,"", IssueLevel.ERROR)
	, VaccinationBodyRouteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_ROUTE,"", IssueLevel.ERROR)
	, VaccinationBodyRouteIsInvalidForBodySiteIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_BODY_SITE, IssueField.VACCINATION_BODY_ROUTE,"", IssueLevel.ERROR)
	, VaccinationBodyRouteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_ROUTE,"", IssueLevel.ERROR)
	, VaccinationBodyRouteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_ROUTE,"", IssueLevel.ERROR)
	, VaccinationBodySiteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_SITE,"", IssueLevel.ERROR)
	, VaccinationBodySiteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_SITE,"", IssueLevel.ERROR)
	, VaccinationBodySiteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_SITE,"", IssueLevel.ERROR)
	, VaccinationBodySiteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_SITE,"", IssueLevel.ERROR)
	, VaccinationBodySiteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_SITE,"", IssueLevel.ERROR)
	, VaccinationBodySiteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_SITE,"", IssueLevel.ERROR)
	, VaccinationCompletionStatusIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueLevel.ERROR)
	, VaccinationCompletionStatusIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueLevel.ERROR)
	, VaccinationCompletionStatusIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueLevel.ERROR)
	, VaccinationCompletionStatusIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueLevel.ERROR)
	, VaccinationCompletionStatusIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueLevel.ERROR)
	, VaccinationCompletionStatusIsValuedAsCompleted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "completed", IssueLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "not administered", IssueLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsPartiallyAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "partially administered", IssueLevel.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsRefused(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "refused", IssueLevel.ACCEPT)
	, VaccinationConfidentialityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueLevel.ERROR)
	, VaccinationConfidentialityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueLevel.ERROR)
	, VaccinationConfidentialityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueLevel.ERROR)
	, VaccinationConfidentialityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueLevel.ERROR)
	, VaccinationConfidentialityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueLevel.ERROR)
	, VaccinationConfidentialityCodeIsValuedAsRestricted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "restricted", IssueLevel.ACCEPT)
	, VaccinationCptCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationCptCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationCptCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationCptCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationCptCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationCptCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationCptCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationCvxCodeAndCptCodeAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_CVX_CODE_AND_CPT_CODE,"", IssueLevel.ERROR)
	, VaccinationFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_NAME,"", IssueLevel.ERROR)
	, VaccinationFacilityTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_TYPE,"", IssueLevel.ERROR)
	, VaccinationFacilityTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_TYPE,"", IssueLevel.ERROR)
	, VaccinationFacilityTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_TYPE,"", IssueLevel.ERROR)
	, VaccinationFacilityTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_TYPE,"", IssueLevel.ERROR)
	, VaccinationFacilityTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_TYPE,"", IssueLevel.ERROR)
	, VaccinationFacilityTypeIsValuedAsPublic(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "public", IssueLevel.ACCEPT)
	, VaccinationFacilityTypeIsValuedAsPrivate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "private", IssueLevel.ACCEPT)
	, VaccinationFillerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationFillerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationFillerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationFillerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationFillerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueLevel.ERROR)
	, VaccinationFinancialEligibilityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueLevel.ERROR)
	, VaccinationGivenByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_GIVEN_BY,"", IssueLevel.ERROR)
	, VaccinationGivenByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_GIVEN_BY,"", IssueLevel.ERROR)
	, VaccinationGivenByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_GIVEN_BY,"", IssueLevel.ERROR)
	, VaccinationGivenByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_GIVEN_BY,"", IssueLevel.ERROR)
	, VaccinationGivenByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_GIVEN_BY,"", IssueLevel.ERROR)
	, VaccinationIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID,"", IssueLevel.ERROR)
	, VaccinationIdOfReceiverIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_RECEIVER,"", IssueLevel.ERROR)
	, VaccinationIdOfReceiverIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_RECEIVER,"", IssueLevel.ERROR)
	, VaccinationIdOfSenderIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_SENDER,"", IssueLevel.ERROR)
	, VaccinationIdOfSenderIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_SENDER,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsAdministeredButAppearsToHistorical(IssueObject.VACCINATION, IssueType.ADMIN_BUT_APPEARS_HISTORICAL, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(IssueObject.VACCINATION, IssueType.HISTORICAL_BUT_APPEARS_ADMIN, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueLevel.ERROR)
	, VaccinationInformationSourceIsValuedAsAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "administered", IssueLevel.ACCEPT)
	, VaccinationInformationSourceIsValuedAsHistorical(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "historical", IssueLevel.ACCEPT)
	, VaccinationVisIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS,"", IssueLevel.ERROR)
	, VaccinationVisIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS,"", IssueLevel.ERROR)
	, VaccinationVisIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS,"", IssueLevel.ERROR)
	, VaccinationVisCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationVisCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationVisCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationVisCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationVisCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueLevel.ERROR)
	, VaccinationVisDocumentTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueLevel.ERROR)
	, VaccinationVisDocumentTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueLevel.ERROR)
	, VaccinationVisDocumentTypeIsIncorrect(IssueObject.VACCINATION, IssueType.INCORRECT, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueLevel.ERROR)
	, VaccinationVisDocumentTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueLevel.ERROR)
	, VaccinationVisDocumentTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueLevel.ERROR)
	, VaccinationVisDocumentTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueLevel.ERROR)
	, VaccinationVisDocumentTypeIsOutOfDate(IssueObject.VACCINATION, IssueType.OUT_OF_DATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueLevel.ERROR)
	, VaccinationVisPublishedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPublishedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPublishedDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPublishedDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPresentedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPresentedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPresentedDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPresentedDateIsBeforePublishedDate(IssueObject.VACCINATION, IssueType.BEFORE_PUBLISHED_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueLevel.ERROR)
	, VaccinationVisPresentedDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueLevel.ERROR)
	, VaccinationLotExpirationDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", IssueLevel.ERROR)
	, VaccinationLotExpirationDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", IssueLevel.ERROR)
	, VaccinationLotNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_NUMBER,"", IssueLevel.ERROR)
	, VaccinationLotNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_NUMBER,"", IssueLevel.ERROR)
	, VaccinationManufacturerCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueLevel.ERROR)
	, VaccinationManufacturerCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueLevel.ERROR)
	, VaccinationManufacturerCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueLevel.ERROR)
	, VaccinationManufacturerCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueLevel.ERROR)
	, VaccinationManufacturerCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueLevel.ERROR)
	, VaccinationManufacturerCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueLevel.ERROR)
	, VaccinationManufacturerCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueLevel.ERROR)
	, VaccinationOrderControlCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueLevel.ERROR)
	, VaccinationOrderControlCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueLevel.ERROR)
	, VaccinationOrderControlCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueLevel.ERROR)
	, VaccinationOrderControlCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueLevel.ERROR)
	, VaccinationOrderControlCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueLevel.ERROR)
	, VaccinationOrderFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationOrderFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationOrderFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationOrderFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationOrderFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueLevel.ERROR)
	, VaccinationOrderFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_NAME,"", IssueLevel.ERROR)
	, VaccinationOrderedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDERED_BY,"", IssueLevel.ERROR)
	, VaccinationOrderedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDERED_BY,"", IssueLevel.ERROR)
	, VaccinationOrderedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDERED_BY,"", IssueLevel.ERROR)
	, VaccinationOrderedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDERED_BY,"", IssueLevel.ERROR)
	, VaccinationOrderedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDERED_BY,"", IssueLevel.ERROR)
	, VaccinationPlacerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationPlacerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationPlacerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationPlacerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationPlacerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueLevel.ERROR)
	, VaccinationProductIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PRODUCT,"", IssueLevel.ERROR)
	, VaccinationProductIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PRODUCT,"", IssueLevel.ERROR)
	, VaccinationProductIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", IssueLevel.ERROR)
	, VaccinationProductIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PRODUCT,"", IssueLevel.ERROR)
	, VaccinationProductIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", IssueLevel.ERROR)
	, VaccinationProductIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PRODUCT,"", IssueLevel.ERROR)
	, VaccinationRecordedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_RECORDED_BY,"", IssueLevel.ERROR)
	, VaccinationRecordedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_RECORDED_BY,"", IssueLevel.ERROR)
	, VaccinationRecordedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_RECORDED_BY,"", IssueLevel.ERROR)
	, VaccinationRecordedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_RECORDED_BY,"", IssueLevel.ERROR)
	, VaccinationRecordedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_RECORDED_BY,"", IssueLevel.ERROR)
	, VaccinationRefusalReasonConflictsCompletionStatus(IssueObject.VACCINATION, IssueType.CONFLICTS_WITH_COMPLETION_STATUS, IssueField.VACCINATION_REFUSAL_REASON,"", IssueLevel.ERROR)
	, VaccinationRefusalReasonIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_REFUSAL_REASON,"", IssueLevel.ERROR)
	, VaccinationRefusalReasonIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_REFUSAL_REASON,"", IssueLevel.ERROR)
	, VaccinationRefusalReasonIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_REFUSAL_REASON,"", IssueLevel.ERROR)
	, VaccinationRefusalReasonIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_REFUSAL_REASON,"", IssueLevel.ERROR)
	, VaccinationRefusalReasonIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_REFUSAL_REASON,"", IssueLevel.ERROR)
	, VaccinationSystemEntryTimeIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", IssueLevel.ERROR)
	, VaccinationSystemEntryTimeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", IssueLevel.ERROR)
	, VaccinationSystemEntryTimeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", IssueLevel.ERROR)
	, VaccinationTradeNameIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_TRADE_NAME,"", IssueLevel.ERROR)
	, VaccinationTradeNameIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_TRADE_NAME,"", IssueLevel.ERROR)
	, VaccinationTradeNameIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_TRADE_NAME,"", IssueLevel.ERROR)
	, VaccinationTradeNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_TRADE_NAME,"", IssueLevel.ERROR)
	, VaccinationTradeNameIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_TRADE_NAME,"", IssueLevel.ERROR)
	, VaccinationTradeNameAndVaccineAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_VACCINE,"", IssueLevel.ERROR)
	, VaccinationTradeNameAndManufacturerAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_MANUFACTURER,"", IssueLevel.ERROR)
	, VaccinationValidityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VALIDITY_CODE,"", IssueLevel.ERROR)
	, VaccinationValidityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VALIDITY_CODE,"", IssueLevel.ERROR)
	, VaccinationValidityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VALIDITY_CODE,"", IssueLevel.ERROR)
	, VaccinationValidityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VALIDITY_CODE,"", IssueLevel.ERROR)
	, VaccinationValidityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VALIDITY_CODE,"", IssueLevel.ERROR)
	, VaccinationValidityCodeIsValuedAsValid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "valid", IssueLevel.ACCEPT)
	, VaccinationValidityCodeIsValuedAsInvalid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "invalid", IssueLevel.ACCEPT)
	, UnknownValidationIssue(IssueObject.GENERAL, IssueType.MISSING, IssueField.GENERAL_PROCESSING,"", IssueLevel.ERROR)
;

	  private static final Map<IssueField, Map<IssueType, PotentialIssue>> fieldIssueMaps = new HashMap<IssueField, Map<IssueType, PotentialIssue>>();
	  
	  private static final Map<IssueField, List<PotentialIssue>> objectIssueListMap = new HashMap<IssueField, List<PotentialIssue>>();
	  
	  private static final Map<String, PotentialIssue> displayTextMap = new HashMap<String, PotentialIssue>();
	  static {
		  for (PotentialIssue issue : PotentialIssue.values()) {
			  Map<IssueType, PotentialIssue> map = fieldIssueMaps.get(issue.getTargetField());
			  if (map == null) {
				  map = new HashMap<IssueType, PotentialIssue>();
				  fieldIssueMaps.put(issue.getTargetField(), map);
			  }
			  
			  map.put(issue.getIssueType(), issue);
			  
			  List<PotentialIssue> objectIssues = objectIssueListMap.get(issue.getTargetObject());
			  if (objectIssues == null) {
				  objectIssues = new ArrayList<PotentialIssue>();
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
	  public static List<PotentialIssue> getIssuesFor(IssueObject object) {
		  return objectIssueListMap.get(object);
	  }
	  
	  /**
	   * This replaces the whole PotentialIssues.java class
	   * and all the mapping that was done there. 
	   * @param field
	   * @param type
	   * @return
	   */
	  public static PotentialIssue get(IssueField field, IssueType type) {
		  Map<IssueType, PotentialIssue> fieldIssues = fieldIssueMaps.get(field);
		  if (fieldIssues != null) {
			  return fieldIssues.get(type);
		  }
		  return PotentialIssue.UnknownValidationIssue;
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
	    private IssueLevel defaultAction;
	    
    private PotentialIssue(IssueObject entity, IssueType type, IssueField fieldRef, String valuation, IssueLevel ia) {
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
		return found;
  }
  
  public ValidationIssue build() {
		ValidationIssue found = new ValidationIssue();
		found.setIssue(this);
		//This needs to be equipped to be naunced. Need to make an option to override this in a profile. 
		found.setIssueAction(this.getDefaultAction());
		return found;
  }
  
  public static ValidationIssue buildIssue(IssueField field, IssueType type) {
	  PotentialIssue issue = get(field, type);
	  return issue.build();
  }
  
  public static ValidationIssue buildIssue(IssueField field, IssueType type, String value) {
	  PotentialIssue issue = get(field, type);
	  return issue.build(value);
  }

  public static PotentialIssue getPotentialIssueByDisplayText(String num, IssueType missing) {

		return getPotentialIssueByDisplayText(num + missing.getText());
	}
  
  public static PotentialIssue getPotentialIssueByDisplayText(String txt) {
		PotentialIssue issue = displayTextMap.get(txt);
		if (issue == null) {
			return UnknownValidationIssue;
		} 
		return issue;
	}

public IssueLevel getDefaultAction() {
	return defaultAction;
}

}
