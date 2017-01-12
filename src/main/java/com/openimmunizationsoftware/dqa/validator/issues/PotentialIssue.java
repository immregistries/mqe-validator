/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.validator.issues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openimmunizationsoftware.dqa.model.CodeReceived;
import com.openimmunizationsoftware.dqa.model.CodeTable;
import com.openimmunizationsoftware.dqa.model.types.ToolTip;

public enum PotentialIssue {
	  GeneralAuthorizationException(IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_AUTHORIZATION,"", IssueAction.ERROR)
	, GeneralConfigurationException(IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_CONFIGURATION,"", IssueAction.ERROR)
	, GeneralParseException(		IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_PARSE,"", IssueAction.ERROR)
	, GeneralProcessingException(	IssueObject.GENERAL, IssueType.ISSUE_TYPE_EXCEPTION, IssueField.GENERAL_PROCESSING,"", IssueAction.ERROR)
	, Hl7SegmentIsUnrecognized(		IssueObject.HL7, IssueType.UNRECOGNIZED, IssueField.HL7_SEGMENT,"", IssueAction.ERROR)
	, Hl7SegmentIsInvalid(			IssueObject.HL7, IssueType.INVALID, IssueField.HL7_SEGMENT,"", IssueAction.ERROR)
	, Hl7SegmentsOutOfOrder(		IssueObject.HL7, IssueType.OUT_OF_ORDER, IssueField.HL7_SEGMENTS,"", IssueAction.ERROR)
	, Hl7MshAcceptAckTypeIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAcceptAckTypeIsIgnored(	IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAcceptAckTypeIsInvalid(	IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAcceptAckTypeIsMissing(	IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAcceptAckTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_ACCEPT_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAcceptAckTypeIsValuedAsAlways(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "always", IssueAction.ACCEPT)
	, Hl7MshAcceptAckTypeIsValuedAsNever(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "never", IssueAction.ACCEPT)
	, Hl7MshAcceptAckTypeIsValuedAsOnlyOnErrors(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_ACCEPT_ACK_TYPE, "only on errors", IssueAction.ACCEPT)
	, Hl7MshAltCharacterSetIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshAltCharacterSetIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshAltCharacterSetIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshAltCharacterSetIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshAltCharacterSetIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_ALT_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshAppAckTypeIsDeprecated(	IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAppAckTypeIsIgnored(	IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAppAckTypeIsInvalid(	IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAppAckTypeIsMissing(	IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAppAckTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_APP_ACK_TYPE,"", IssueAction.ERROR)
	, Hl7MshAppAckTypeIsValuedAsAlways(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "always", IssueAction.ACCEPT)
	, Hl7MshAppAckTypeIsValuedAsNever(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "never", IssueAction.ACCEPT)
	, Hl7MshAppAckTypeIsValuedAsOnlyOnErrors(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_APP_ACK_TYPE, "only on errors", IssueAction.ACCEPT)
	, Hl7MshCharacterSetIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshCharacterSetIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshCharacterSetIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshCharacterSetIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshCharacterSetIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_CHARACTER_SET,"", IssueAction.ERROR)
	, Hl7MshCountryCodeIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueAction.ERROR)
	, Hl7MshCountryCodeIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueAction.ERROR)
	, Hl7MshCountryCodeIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueAction.ERROR)
	, Hl7MshCountryCodeIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueAction.ERROR)
	, Hl7MshCountryCodeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_COUNTRY_CODE,"", IssueAction.ERROR)
	, Hl7MshEncodingCharacterIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_ENCODING_CHARACTER,"", IssueAction.ERROR)
	, Hl7MshEncodingCharacterIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_ENCODING_CHARACTER,"", IssueAction.ERROR)
	, Hl7MshEncodingCharacterIsNonStandard(IssueObject.HL7_MSH, IssueType.NON_STANDARD, IssueField.HL7_MSH_ENCODING_CHARACTER,"", IssueAction.ERROR)
	, Hl7MshMessageControlIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_CONTROL_ID,"", IssueAction.ERROR)
	, Hl7MshMessageDateIsInFuture(IssueObject.HL7_MSH, IssueType.IN_FUTURE, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueAction.ERROR)
	, Hl7MshMessageDateIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueAction.ERROR)
	, Hl7MshMessageDateIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueAction.ERROR)
	, Hl7MshMessageDateIsNotPrecise(IssueObject.HL7_MSH, IssueType.NOT_PRECISE, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueAction.ERROR)
	, Hl7MshMessageDateIsMissingTimezone(IssueObject.HL7_MSH, IssueType.MISSING_TIMEZONE, IssueField.HL7_MSH_MESSAGE_DATE,"", IssueAction.ERROR)
	, Hl7MshMessageProfileIdIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueAction.ERROR)
	, Hl7MshMessageProfileIdIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueAction.ERROR)
	, Hl7MshMessageProfileIdIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueAction.ERROR)
	, Hl7MshMessageProfileIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueAction.ERROR)
	, Hl7MshMessageProfileIdIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_PROFILE_ID,"", IssueAction.ERROR)
	, Hl7MshMessageStructureIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_STRUCTURE,"", IssueAction.ERROR)
	, Hl7MshMessageStructureIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_STRUCTURE,"", IssueAction.ERROR)
	, Hl7MshMessageTriggerIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", IssueAction.ERROR)
	, Hl7MshMessageTriggerIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", IssueAction.ERROR)
	, Hl7MshMessageTriggerIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_MESSAGE_TRIGGER,"", IssueAction.ERROR)
	, Hl7MshMessageTypeIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_MESSAGE_TYPE,"", IssueAction.ERROR)
	, Hl7MshMessageTypeIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_MESSAGE_TYPE,"", IssueAction.ERROR)
	, Hl7MshMessageTypeIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_MESSAGE_TYPE,"", IssueAction.ERROR)
	, Hl7MshProcessingIdIsDeprecated(IssueObject.HL7_MSH, IssueType.DEPRECATE, IssueField.HL7_MSH_PROCESSING_ID,"", IssueAction.ERROR)
	, Hl7MshProcessingIdIsIgnored(IssueObject.HL7_MSH, IssueType.IGNORED, IssueField.HL7_MSH_PROCESSING_ID,"", IssueAction.ERROR)
	, Hl7MshProcessingIdIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_PROCESSING_ID,"", IssueAction.ERROR)
	, Hl7MshProcessingIdIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_PROCESSING_ID,"", IssueAction.ERROR)
	, Hl7MshProcessingIdIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_PROCESSING_ID,"", IssueAction.ERROR)
	, Hl7MshProcessingIdIsUnsupported(IssueObject.HL7_MSH, IssueType.UNSUPPORTED, IssueField.HL7_MSH_PROCESSING_ID,"", IssueAction.ERROR)
	, Hl7MshProcessingIdIsValuedAsDebug(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "debug", IssueAction.ACCEPT)
	, Hl7MshProcessingIdIsValuedAsProduction(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "production", IssueAction.ACCEPT)
	, Hl7MshProcessingIdIsValuedAsTraining(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_PROCESSING_ID, "training", IssueAction.ACCEPT)
	, Hl7MshReceivingApplicationIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_RECEIVING_APPLICATION,"", IssueAction.ERROR)
	, Hl7MshReceivingApplicationIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_RECEIVING_APPLICATION,"", IssueAction.ERROR)
	, Hl7MshReceivingFacilityIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_RECEIVING_FACILITY,"", IssueAction.ERROR)
	, Hl7MshReceivingFacilityIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_RECEIVING_FACILITY,"", IssueAction.ERROR)
	, Hl7MshSegmentIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SEGMENT,"", IssueAction.ERROR)
	, Hl7MshSendingApplicationIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_SENDING_APPLICATION,"", IssueAction.ERROR)
	, Hl7MshSendingApplicationIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SENDING_APPLICATION,"", IssueAction.ERROR)
	, Hl7MshSendingFacilityIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_SENDING_FACILITY,"", IssueAction.ERROR)
	, Hl7MshSendingFacilityIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_SENDING_FACILITY,"", IssueAction.ERROR)
	, Hl7MshVersionIsMissing(IssueObject.HL7_MSH, IssueType.MISSING, IssueField.HL7_MSH_VERSION,"", IssueAction.ERROR)
	, Hl7MshVersionIsUnrecognized(IssueObject.HL7_MSH, IssueType.UNRECOGNIZED, IssueField.HL7_MSH_VERSION,"", IssueAction.ERROR)
	, Hl7MshVersionIsInvalid(IssueObject.HL7_MSH, IssueType.INVALID, IssueField.HL7_MSH_VERSION,"", IssueAction.ERROR)
	, Hl7MshVersionIsValuedAs2_3_1(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.3.1", IssueAction.ACCEPT)
	, Hl7MshVersionIsValuedAs2_4(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.4", IssueAction.ACCEPT)
	, Hl7MshVersionIsValuedAs2_5(IssueObject.HL7_MSH, IssueType.VALUED_AS, IssueField.HL7_MSH_VERSION, "2.5", IssueAction.ACCEPT)
	, Hl7Nk1SegmentIsMissing(IssueObject.HL7_NK1, IssueType.MISSING, IssueField.HL7_NK1_SEGMENT,"", IssueAction.ERROR)
	, Hl7Nk1SegmentIsRepeated(IssueObject.HL7_NK1, IssueType.REPEATED, IssueField.HL7_NK1_SEGMENT,"", IssueAction.ERROR)
	, Hl7Nk1SetIdIsMissing(IssueObject.HL7_NK1, IssueType.MISSING, IssueField.HL7_NK1_SET_ID,"", IssueAction.ERROR)
	, Hl7ObxSegmentIsMissing(IssueObject.HL7_OBX, IssueType.MISSING, IssueField.HL7_OBX_SEGMENT,"", IssueAction.ERROR)
	, Hl7OrcSegmentIsMissing(IssueObject.HL7_ORC, IssueType.MISSING, IssueField.HL7_ORC_SEGMENT,"", IssueAction.ERROR)
	, Hl7OrcSegmentIsRepeated(IssueObject.HL7_ORC, IssueType.REPEATED, IssueField.HL7_ORC_SEGMENT,"", IssueAction.ERROR)
	, Hl7Pd1SegmentIsMissing(IssueObject.HL7_PD1, IssueType.MISSING, IssueField.HL7_PD1_SEGMENT,"", IssueAction.ERROR)
	, Hl7PidSegmentIsMissing(IssueObject.HL7_PID, IssueType.MISSING, IssueField.HL7_PID_SEGMENT,"", IssueAction.ERROR)
	, Hl7PidSegmentIsRepeated(IssueObject.HL7_PID, IssueType.REPEATED, IssueField.HL7_PID_SEGMENT,"", IssueAction.ERROR)
	, Hl7Pv1SegmentIsMissing(IssueObject.HL7_PV1, IssueType.MISSING, IssueField.HL7_PV1_SEGMENT,"", IssueAction.ERROR)
	, Hl7Pv1SegmentIsRepeated(IssueObject.HL7_PV1, IssueType.REPEATED, IssueField.HL7_PV1_SEGMENT,"", IssueAction.ERROR)
	, Hl7RxaAdminSubIdCounterIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_ADMIN_SUB_ID_COUNTER,"", IssueAction.ERROR)
	, Hl7RxaGiveSubIdIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_GIVE_SUB_ID,"", IssueAction.ERROR)
	, Hl7RxaSegmentIsMissing(IssueObject.HL7_RXA, IssueType.MISSING, IssueField.HL7_RXA_SEGMENT,"", IssueAction.ERROR)
	, Hl7RxaSegmentIsRepeated(IssueObject.HL7_RXA, IssueType.REPEATED, IssueField.HL7_RXA_SEGMENT,"", IssueAction.ERROR)
	, Hl7RxrSegmentIsMissing(IssueObject.HL7_RXR, IssueType.MISSING, IssueField.HL7_RXR_SEGMENT,"", IssueAction.ERROR)
	, Hl7RxrSegmentIsRepeated(IssueObject.HL7_RXR, IssueType.REPEATED, IssueField.HL7_RXR_SEGMENT,"", IssueAction.ERROR)
	, NextOfKinAddressIsDifferentFromPatientAddress(IssueObject.NEXT_OF_KIN, IssueType.DIFFERENT_FROM_PATIENT_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS,"", IssueAction.ERROR)
	, NextOfKinAddressIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS,"", IssueAction.ERROR)
	, NextOfKinAddressCityIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueAction.ERROR)
	, NextOfKinAddressCityIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueAction.ERROR)
	, NextOfKinAddressCityIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueAction.ERROR)
	, NextOfKinAddressCityIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueAction.ERROR)
	, NextOfKinAddressCityIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueAction.ERROR)
	, NextOfKinAddressCityIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_ADDRESS_CITY,"", IssueAction.ERROR)
	, NextOfKinAddressCountryIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, NextOfKinAddressCountryIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, NextOfKinAddressCountryIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, NextOfKinAddressCountryIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, NextOfKinAddressCountryIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, NextOfKinAddressCountyIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, NextOfKinAddressCountyIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, NextOfKinAddressCountyIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, NextOfKinAddressCountyIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, NextOfKinAddressCountyIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, NextOfKinAddressStateIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueAction.ERROR)
	, NextOfKinAddressStateIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueAction.ERROR)
	, NextOfKinAddressStateIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueAction.ERROR)
	, NextOfKinAddressStateIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueAction.ERROR)
	, NextOfKinAddressStateIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_STATE,"", IssueAction.ERROR)
	, NextOfKinAddressStreetIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET,"", IssueAction.ERROR)
	, NextOfKinAddressStreet2IsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_STREET2,"", IssueAction.ERROR)
	, NextOfKinAddressTypeIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueAction.ERROR)
	, NextOfKinAddressTypeIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueAction.ERROR)
	, NextOfKinAddressTypeIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueAction.ERROR)
	, NextOfKinAddressTypeIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueAction.ERROR)
	, NextOfKinAddressTypeIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueAction.ERROR)
	, NextOfKinAddressTypeIsValuedBadAddress(IssueObject.NEXT_OF_KIN, IssueType.VALUED_BAD_ADDRESS, IssueField.NEXT_OF_KIN_ADDRESS_TYPE,"", IssueAction.ERROR)
	, NextOfKinAddressZipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", IssueAction.ERROR)
	, NextOfKinAddressZipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_ADDRESS_ZIP,"", IssueAction.ERROR)
	, NextOfKinNameIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME,"", IssueAction.ERROR)
	, NextOfKinNameFirstIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueAction.ERROR)
	, NextOfKinNameFirstIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueAction.ERROR)
	, NextOfKinNameFirstIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueAction.ERROR)
	, NextOfKinNameFirstIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueAction.ERROR)
	, NextOfKinNameFirstIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_FIRST,"", IssueAction.ERROR)
	, NextOfKinNameLastIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueAction.ERROR)
	, NextOfKinNameLastIsTooShort(IssueObject.NEXT_OF_KIN, IssueType.TOO_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueAction.ERROR)
	, NextOfKinNameLastIsUnexpectedlyShort(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTEDLY_SHORT, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueAction.ERROR)
	, NextOfKinNameLastIsUnexpectedlyLong(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECETDLY_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueAction.ERROR)
	, NextOfKinNameLastIsTooLong(IssueObject.NEXT_OF_KIN, IssueType.TOO_LONG, IssueField.NEXT_OF_KIN_NAME_LAST,"", IssueAction.ERROR)
	, NextOfKinPhoneNumberIsIncomplete(IssueObject.NEXT_OF_KIN, IssueType.INCOMPLETE, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", IssueAction.ERROR)
	, NextOfKinPhoneNumberIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", IssueAction.ERROR)
	, NextOfKinPhoneNumberIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_PHONE_NUMBER,"", IssueAction.ERROR)
	, NextOfKinRelationshipIsDeprecated(IssueObject.NEXT_OF_KIN, IssueType.DEPRECATE, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueAction.ERROR)
	, NextOfKinRelationshipIsIgnored(IssueObject.NEXT_OF_KIN, IssueType.IGNORED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueAction.ERROR)
	, NextOfKinRelationshipIsInvalid(IssueObject.NEXT_OF_KIN, IssueType.INVALID, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueAction.ERROR)
	, NextOfKinRelationshipIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueAction.ERROR)
	, NextOfKinRelationshipIsNotResponsibleParty(IssueObject.NEXT_OF_KIN, IssueType.NOT_RESPONSIBLE_PARTY, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueAction.ERROR)
	, NextOfKinRelationshipIsUnexpected(IssueObject.NEXT_OF_KIN, IssueType.UNEXPECTED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueAction.ERROR)
	, NextOfKinRelationshipIsUnrecognized(IssueObject.NEXT_OF_KIN, IssueType.UNRECOGNIZED, IssueField.NEXT_OF_KIN_RELATIONSHIP,"", IssueAction.ERROR)
	, NextOfKinSsnIsMissing(IssueObject.NEXT_OF_KIN, IssueType.MISSING, IssueField.NEXT_OF_KIN_SSN,"", IssueAction.ERROR)
	, ObservationValueTypeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_VALUE_TYPE,"", IssueAction.ERROR)
	, ObservationValueTypeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_VALUE_TYPE,"", IssueAction.ERROR)
	, ObservationValueTypeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_VALUE_TYPE,"", IssueAction.ERROR)
	, ObservationValueTypeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE_TYPE,"", IssueAction.ERROR)
	, ObservationValueTypeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_VALUE_TYPE,"", IssueAction.ERROR)
	, ObservationIdentifierCodeIsDeprecated(IssueObject.OBSERVATION, IssueType.DEPRECATE, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueAction.ERROR)
	, ObservationIdentifierCodeIsIgnored(IssueObject.OBSERVATION, IssueType.IGNORED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueAction.ERROR)
	, ObservationIdentifierCodeIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueAction.ERROR)
	, ObservationIdentifierCodeIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueAction.ERROR)
	, ObservationIdentifierCodeIsUnrecognized(IssueObject.OBSERVATION, IssueType.UNRECOGNIZED, IssueField.OBSERVATION_IDENTIFIER_CODE,"", IssueAction.ERROR)
	, ObservationValueIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_VALUE,"", IssueAction.ERROR)
	, ObservationDateTimeOfObservationIsMissing(IssueObject.OBSERVATION, IssueType.MISSING, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", IssueAction.ERROR)
	, ObservationDateTimeOfObservationIsInvalid(IssueObject.OBSERVATION, IssueType.INVALID, IssueField.OBSERVATION_DATE_TIME_OF_OBSERVATION,"", IssueAction.ERROR)
	, PatientObjectIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.NONE,"", IssueAction.ERROR)
	, PatientAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS,"", IssueAction.ERROR)
	, PatientAddressCityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_CITY,"", IssueAction.ERROR)
	, PatientAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_CITY,"", IssueAction.ERROR)
	, PatientAddressCityIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", IssueAction.ERROR)
	, PatientAddressCityIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_ADDRESS_CITY,"", IssueAction.ERROR)
	, PatientAddressCityIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_ADDRESS_CITY,"", IssueAction.ERROR)
	, PatientAddressCityIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_ADDRESS_CITY,"", IssueAction.ERROR)
	, PatientAddressCountryIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, PatientAddressCountryIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, PatientAddressCountryIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, PatientAddressCountryIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, PatientAddressCountryIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTRY,"", IssueAction.ERROR)
	, PatientAddressCountyIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, PatientAddressCountyIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, PatientAddressCountyIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, PatientAddressCountyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, PatientAddressCountyIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_COUNTY,"", IssueAction.ERROR)
	, PatientAddressStateIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_STATE,"", IssueAction.ERROR)
	, PatientAddressStateIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_STATE,"", IssueAction.ERROR)
	, PatientAddressStateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_STATE,"", IssueAction.ERROR)
	, PatientAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STATE,"", IssueAction.ERROR)
	, PatientAddressStateIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_STATE,"", IssueAction.ERROR)
	, PatientAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET,"", IssueAction.ERROR)
	, PatientAddressStreet2IsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_STREET2,"", IssueAction.ACCEPT)
	, PatientAddressTypeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_TYPE,"", IssueAction.ERROR)
	, PatientAddressTypeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ADDRESS_TYPE,"", IssueAction.ERROR)
	, PatientAddressTypeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ADDRESS_TYPE,"", IssueAction.ERROR)
	, PatientAddressTypeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_TYPE,"", IssueAction.ERROR)
	, PatientAddressTypeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ADDRESS_TYPE,"", IssueAction.ERROR)
	, PatientAddressTypeIsValuedBadAddress(IssueObject.PATIENT, IssueType.VALUED_BAD_ADDRESS, IssueField.PATIENT_ADDRESS_TYPE,"", IssueAction.ERROR)
	, PatientAddressZipIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ADDRESS_ZIP,"", IssueAction.ERROR)
	, PatientAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ADDRESS_ZIP,"", IssueAction.ERROR)
	, PatientAliasIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ALIAS,"", IssueAction.ERROR)
	, PatientBirthDateIsAfterSubmission(IssueObject.PATIENT, IssueType.AFTER_SUBMISSION, IssueField.PATIENT_BIRTH_DATE,"", IssueAction.ERROR)
	, PatientBirthDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_BIRTH_DATE,"", IssueAction.ERROR)
	, PatientBirthDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_DATE,"", IssueAction.ERROR)
	, PatientBirthDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_DATE,"", IssueAction.ERROR)
	, PatientBirthDateIsUnderage(IssueObject.PATIENT, IssueType.UNDERAGE, IssueField.PATIENT_BIRTH_DATE,"", IssueAction.ACCEPT)
	, PatientBirthDateIsVeryLongAgo(IssueObject.PATIENT, IssueType.VERY_LONG_AGO, IssueField.PATIENT_BIRTH_DATE,"", IssueAction.ERROR)
	, PatientBirthIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_INDICATOR,"", IssueAction.ERROR)
	, PatientBirthIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_INDICATOR,"", IssueAction.ERROR)
	, PatientBirthOrderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_ORDER,"", IssueAction.ERROR)
	, PatientBirthOrderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_ORDER,"", IssueAction.ERROR)
	, PatientBirthOrderIsMissingAndMultipleBirthIndicated(IssueObject.PATIENT, IssueType.MISSING_AND_MULTIPLE_BIRTH_INDICATED, IssueField.PATIENT_BIRTH_ORDER,"", IssueAction.ERROR)
	, PatientBirthPlaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_PLACE,"", IssueAction.ERROR)
	, PatientBirthPlaceIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", IssueAction.ERROR)
	, PatientBirthPlaceIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_BIRTH_PLACE,"", IssueAction.ERROR)
	, PatientBirthPlaceIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_BIRTH_PLACE,"", IssueAction.ERROR)
	, PatientBirthPlaceIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_BIRTH_PLACE,"", IssueAction.ERROR)
	, PatientBirthRegistryIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", IssueAction.ERROR)
	, PatientBirthRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_BIRTH_REGISTRY_ID,"", IssueAction.ERROR)
	, PatientClassIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_CLASS,"", IssueAction.ERROR)
	, PatientClassIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_CLASS,"", IssueAction.ERROR)
	, PatientClassIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_CLASS,"", IssueAction.ERROR)
	, PatientClassIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_CLASS,"", IssueAction.ERROR)
	, PatientClassIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_CLASS,"", IssueAction.ERROR)
	, PatientDeathDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_DEATH_DATE,"", IssueAction.ERROR)
	, PatientDeathDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_DEATH_DATE,"", IssueAction.ERROR)
	, PatientDeathDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_DEATH_DATE,"", IssueAction.ERROR)
	, PatientDeathDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_DATE,"", IssueAction.ERROR)
	, PatientDeathIndicatorIsInconsistent(IssueObject.PATIENT, IssueType.INCONSISTENT, IssueField.PATIENT_DEATH_INDICATOR,"", IssueAction.ERROR)
	, PatientDeathIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_DEATH_INDICATOR,"", IssueAction.ERROR)
	, PatientEthnicityIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_ETHNICITY,"", IssueAction.ERROR)
	, PatientEthnicityIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_ETHNICITY,"", IssueAction.ERROR)
	, PatientEthnicityIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_ETHNICITY,"", IssueAction.ERROR)
	, PatientEthnicityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_ETHNICITY,"", IssueAction.ERROR)
	, PatientEthnicityIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_ETHNICITY,"", IssueAction.ERROR)
	, PatientGenderIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_GENDER,"", IssueAction.ERROR)
	, PatientGenderIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_GENDER,"", IssueAction.ERROR)
	, PatientGenderIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_GENDER,"", IssueAction.ERROR)
	, PatientGenderIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GENDER,"", IssueAction.ERROR)
	, PatientGenderIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_GENDER,"", IssueAction.ERROR)
	, PatientGuardianAddressIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS,"", IssueAction.ERROR)
	, PatientGuardianAddressCityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_CITY,"", IssueAction.ERROR)
	, PatientGuardianAddressStateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STATE,"", IssueAction.ERROR)
	, PatientGuardianAddressStreetIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_STREET,"", IssueAction.ERROR)
	, PatientGuardianAddressZipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_ADDRESS_ZIP,"", IssueAction.ERROR)
	, PatientGuardianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME,"", IssueAction.ERROR)
	, PatientGuardianNameIsSameAsUnderagePatient(IssueObject.PATIENT, IssueType.SAME_AS_UNDERAGE_PATIENT, IssueField.PATIENT_GUARDIAN_NAME,"", IssueAction.ERROR)
	, PatientGuardianNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_GUARDIAN_NAME,"", IssueAction.ERROR)
	, PatientGuardianNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_FIRST,"", IssueAction.ERROR)
	, PatientGuardianNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_NAME_LAST,"", IssueAction.ERROR)
	, PatientGuardianResponsiblePartyIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RESPONSIBLE_PARTY,"", IssueAction.ERROR)
	, PatientGuardianPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_PHONE,"", IssueAction.ERROR)
	, PatientGuardianRelationshipIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_GUARDIAN_RELATIONSHIP,"", IssueAction.ERROR)
	, PatientImmunityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNITY_CODE,"", IssueAction.ERROR)
	, PatientImmunityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNITY_CODE,"", IssueAction.ERROR)
	, PatientImmunityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNITY_CODE,"", IssueAction.ERROR)
	, PatientImmunityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNITY_CODE,"", IssueAction.ERROR)
	, PatientImmunityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNITY_CODE,"", IssueAction.ERROR)
	, PatientImmunizationRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientImmunizationRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientImmunizationRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientImmunizationRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientImmunizationRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_IMMUNIZATION_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientMedicaidNumberIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MEDICAID_NUMBER,"", IssueAction.ERROR)
	, PatientMedicaidNumberIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MEDICAID_NUMBER,"", IssueAction.ERROR)
	, PatientMiddleNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MIDDLE_NAME,"", IssueAction.ERROR)
	, PatientMiddleNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MIDDLE_NAME,"", IssueAction.ERROR)
	, PatientMiddleNameMayBeInitial(IssueObject.PATIENT, IssueType.MAY_BE_AN_INITIAL, IssueField.PATIENT_MIDDLE_NAME,"", IssueAction.ERROR)
	, PatientMiddleNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", IssueAction.ERROR)
	, PatientMiddleNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MIDDLE_NAME,"", IssueAction.ERROR)
	, PatientMiddleNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MIDDLE_NAME,"", IssueAction.ERROR)
	, PatientMiddleNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MIDDLE_NAME,"", IssueAction.ERROR)
	, PatientMothersMaidenNameIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientMotherSMaidenNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientMotherSMaidenNameHasInvalidPrefixes(IssueObject.PATIENT, IssueType.INVALID_PREFIXES, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientMotherSMaidenNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientMotherSMaidenNameIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientMotherSMaidenNameIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientMotherSMaidenNameIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientMotherSMaidenNameIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_MOTHERS_MAIDEN_NAME,"", IssueAction.ERROR)
	, PatientNameMayBeTemporaryNewbornName(IssueObject.PATIENT, IssueType.MAY_BE_TEMPORARY_NEWBORN_NAME, IssueField.PATIENT_NAME,"", IssueAction.ERROR)
	, PatientNameMayBeTestName(IssueObject.PATIENT, IssueType.MAY_BE_TEST_NAME, IssueField.PATIENT_NAME,"", IssueAction.ERROR)
	, PatientNameHasJunkName(IssueObject.PATIENT, IssueType.HAS_JUNK_NAME, IssueField.PATIENT_NAME,"", IssueAction.ERROR)
	, PatientNameIsAKnownTestName(IssueObject.PATIENT, IssueType.KNOWN_TEST_NAME, IssueField.PATIENT_NAME,"", IssueAction.ERROR)
	, PatientNameFirstIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_FIRST,"", IssueAction.ERROR)
	, PatientNameFirstIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_FIRST,"", IssueAction.ERROR)
	, PatientNameFirstIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_FIRST,"", IssueAction.ERROR)
	, PatientNameFirstIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_FIRST,"", IssueAction.ERROR)
	, PatientNameFirstIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_FIRST,"", IssueAction.ERROR)
	, PatientNameFirstIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_FIRST,"", IssueAction.ERROR)
	, PatientNameFirstMayIncludeMiddleInitial(IssueObject.PATIENT, IssueType.MAY_INCLUDE_MIDDLE_INITIAL, IssueField.PATIENT_NAME_FIRST,"", IssueAction.ERROR)
	, PatientNameLastIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_LAST,"", IssueAction.ERROR)
	, PatientNameLastIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_LAST,"", IssueAction.ERROR)
	, PatientNameLastIsTooShort(IssueObject.PATIENT, IssueType.TOO_SHORT, IssueField.PATIENT_NAME_LAST,"", IssueAction.ERROR)
	, PatientNameLastIsUnexpectedlyShort(IssueObject.PATIENT, IssueType.UNEXPECTEDLY_SHORT, IssueField.PATIENT_NAME_LAST,"", IssueAction.ERROR)
	, PatientNameLastIsUnexpectedlyLong(IssueObject.PATIENT, IssueType.UNEXPECETDLY_LONG, IssueField.PATIENT_NAME_LAST,"", IssueAction.ERROR)
	, PatientNameLastIsTooLong(IssueObject.PATIENT, IssueType.TOO_LONG, IssueField.PATIENT_NAME_LAST,"", IssueAction.ERROR)
	, PatientNameTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueAction.ERROR)
	, PatientNameTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueAction.ERROR)
	, PatientNameTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueAction.ERROR)
	, PatientNameTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueAction.ERROR)
	, PatientNameTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueAction.ERROR)
	, PatientNameTypeCodeIsNotValuedLegal(IssueObject.PATIENT, IssueType.NOT_VALUED_LEGAL, IssueField.PATIENT_NAME_TYPE_CODE,"", IssueAction.ERROR)
	, PatientPhoneIsIncomplete(IssueObject.PATIENT, IssueType.INCOMPLETE, IssueField.PATIENT_PHONE,"", IssueAction.ERROR)
	, PatientPhoneIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE,"", IssueAction.ERROR)
	, PatientPhoneIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE,"", IssueAction.ERROR)
	, PatientPhoneTelUseCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelUseCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelUseCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelUseCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelUseCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_USE_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelEquipCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelEquipCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelEquipCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelEquipCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueAction.ERROR)
	, PatientPhoneTelEquipCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PHONE_TEL_EQUIP_CODE,"", IssueAction.ERROR)
	, PatientPrimaryFacilityIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueAction.ERROR)
	, PatientPrimaryFacilityIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueAction.ERROR)
	, PatientPrimaryFacilityIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueAction.ERROR)
	, PatientPrimaryFacilityIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueAction.ERROR)
	, PatientPrimaryFacilityIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_FACILITY_ID,"", IssueAction.ERROR)
	, PatientPrimaryFacilityNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_FACILITY_NAME,"", IssueAction.ERROR)
	, PatientPrimaryLanguageIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueAction.ERROR)
	, PatientPrimaryLanguageIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueAction.ERROR)
	, PatientPrimaryLanguageIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueAction.ERROR)
	, PatientPrimaryLanguageIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueAction.ERROR)
	, PatientPrimaryLanguageIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_LANGUAGE,"", IssueAction.ERROR)
	, PatientPrimaryPhysicianIdIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueAction.ERROR)
	, PatientPrimaryPhysicianIdIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueAction.ERROR)
	, PatientPrimaryPhysicianIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueAction.ERROR)
	, PatientPrimaryPhysicianIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueAction.ERROR)
	, PatientPrimaryPhysicianIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PRIMARY_PHYSICIAN_ID,"", IssueAction.ERROR)
	, PatientPrimaryPhysicianNameIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PRIMARY_PHYSICIAN_NAME,"", IssueAction.ERROR)
	, PatientProtectionIndicatorIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueAction.ERROR)
	, PatientProtectionIndicatorIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueAction.ERROR)
	, PatientProtectionIndicatorIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueAction.ERROR)
	, PatientProtectionIndicatorIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueAction.ERROR)
	, PatientProtectionIndicatorIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PROTECTION_INDICATOR,"", IssueAction.ERROR)
	, PatientProtectionIndicatorIsValuedAsNo(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "no", IssueAction.ACCEPT)
	, PatientProtectionIndicatorIsValuedAsYes(IssueObject.PATIENT, IssueType.VALUED_AS, IssueField.PATIENT_PROTECTION_INDICATOR, "yes", IssueAction.ACCEPT)
	, PatientPublicityCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_PUBLICITY_CODE,"", IssueAction.ERROR)
	, PatientPublicityCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_PUBLICITY_CODE,"", IssueAction.ERROR)
	, PatientPublicityCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_PUBLICITY_CODE,"", IssueAction.ERROR)
	, PatientPublicityCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_PUBLICITY_CODE,"", IssueAction.ERROR)
	, PatientPublicityCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_PUBLICITY_CODE,"", IssueAction.ERROR)
	, PatientRaceIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_RACE,"", IssueAction.ERROR)
	, PatientRaceIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_RACE,"", IssueAction.ERROR)
	, PatientRaceIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_RACE,"", IssueAction.ERROR)
	, PatientRaceIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_RACE,"", IssueAction.ERROR)
	, PatientRaceIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_RACE,"", IssueAction.ERROR)
	, PatientRegistryIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_ID,"", IssueAction.ERROR)
	, PatientRegistryIdIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_ID,"", IssueAction.ERROR)
	, PatientRegistryStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientRegistryStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientRegistryStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientRegistryStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientRegistryStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_REGISTRY_STATUS,"", IssueAction.ERROR)
	, PatientSsnIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SSN,"", IssueAction.SKIP)
	, PatientSsnIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SSN,"", IssueAction.SKIP)
	, PatientSubmitterIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID,"", IssueAction.ERROR)
	, PatientSubmitterIdAuthorityIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_AUTHORITY,"", IssueAction.ERROR)
	, PatientSubmitterIdTypeCodeIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueAction.ERROR)
	, PatientSubmitterIdTypeCodeIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueAction.ERROR)
	, PatientSubmitterIdTypeCodeIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueAction.ERROR)
	, PatientSubmitterIdTypeCodeIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueAction.ERROR)
	, PatientSubmitterIdTypeCodeIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_SUBMITTER_ID_TYPE_CODE,"", IssueAction.ERROR)
	, PatientSystemCreationDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueAction.ERROR)
	, PatientSystemCreationDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueAction.ERROR)
	, PatientSystemCreationDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueAction.ERROR)
	, PatientSystemCreationDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_SYSTEM_CREATION_DATE,"", IssueAction.ERROR)
	, PatientVfcEffectiveDateIsBeforeBirth(IssueObject.PATIENT, IssueType.BEFORE_BIRTH, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueAction.ERROR)
	, PatientVfcEffectiveDateIsInFuture(IssueObject.PATIENT, IssueType.IN_FUTURE, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueAction.ERROR)
	, PatientVfcEffectiveDateIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueAction.ERROR)
	, PatientVfcEffectiveDateIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_EFFECTIVE_DATE,"", IssueAction.ERROR)
	, PatientVfcStatusIsDeprecated(IssueObject.PATIENT, IssueType.DEPRECATE, IssueField.PATIENT_VFC_STATUS,"", IssueAction.ERROR)
	, PatientVfcStatusIsIgnored(IssueObject.PATIENT, IssueType.IGNORED, IssueField.PATIENT_VFC_STATUS,"", IssueAction.ERROR)
	, PatientVfcStatusIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_VFC_STATUS,"", IssueAction.ERROR)
	, PatientVfcStatusIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_VFC_STATUS,"", IssueAction.ERROR)
	, PatientVfcStatusIsUnrecognized(IssueObject.PATIENT, IssueType.UNRECOGNIZED, IssueField.PATIENT_VFC_STATUS,"", IssueAction.ERROR)
	, PatientWicIdIsInvalid(IssueObject.PATIENT, IssueType.INVALID, IssueField.PATIENT_WIC_ID,"", IssueAction.ERROR)
	, PatientWicIdIsMissing(IssueObject.PATIENT, IssueType.MISSING, IssueField.PATIENT_WIC_ID,"", IssueAction.ERROR)
	, VaccinationActionCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ACTION_CODE,"", IssueAction.ERROR)
	, VaccinationActionCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ACTION_CODE,"", IssueAction.ERROR)
	, VaccinationActionCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ACTION_CODE,"", IssueAction.ERROR)
	, VaccinationActionCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ACTION_CODE,"", IssueAction.ERROR)
	, VaccinationActionCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ACTION_CODE,"", IssueAction.ERROR)
	, VaccinationActionCodeIsValuedAsAdd(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add", IssueAction.ACCEPT)
	, VaccinationActionCodeIsValuedAsAddOrUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "add or update", IssueAction.ACCEPT)
	, VaccinationActionCodeIsValuedAsDelete(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "delete", IssueAction.ACCEPT)
	, VaccinationActionCodeIsValuedAsUpdate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ACTION_CODE, "update", IssueAction.ACCEPT)
	, VaccinationAdminCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsNotSpecific(IssueObject.VACCINATION, IssueType.NOT_SPECIFIC, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsNotVaccine(IssueObject.VACCINATION, IssueType.NOT_VACCINE, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "not administered", IssueAction.ACCEPT)
	, VaccinationAdminCodeIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMIN_CODE, "unknown", IssueAction.ACCEPT)
	, VaccinationAdminCodeMayBeVariationOfPreviouslyReportedCodes(IssueObject.VACCINATION, IssueType.MAY_BE_PREVIOUSLY_REPORTED, IssueField.VACCINATION_ADMIN_CODE,"", IssueAction.ERROR)
	, VaccinationAdminCodeTableIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", IssueAction.ERROR)
	, VaccinationAdminCodeTableIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_CODE_TABLE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsAfterLotExpirationDate(IssueObject.VACCINATION, IssueType.AFTER_LOT_EXPIRATION, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsAfterMessageSubmitted(IssueObject.VACCINATION, IssueType.AFTER_MESSAGE_SUBMITTED, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsAfterPatientDeathDate(IssueObject.VACCINATION, IssueType.AFTER_PATIENT_DEATH_DATE, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsAfterSystemEntryDate(IssueObject.VACCINATION, IssueType.AFTER_SYSTEM_ENTRY_DATE, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsBeforeBirth(IssueObject.VACCINATION, IssueType.BEFORE_BIRTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsBeforeOrAfterWhenValidForPatientAge(IssueObject.VACCINATION, IssueType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsOn15ThDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIFTEENTH_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsOnFirstDayOfMonth(IssueObject.VACCINATION, IssueType.ON_FIRST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsOnLastDayOfMonth(IssueObject.VACCINATION, IssueType.ON_LAST_DAY_OF_MONTH, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateIsReportedLate(IssueObject.VACCINATION, IssueType.REPORTED_LATE, IssueField.VACCINATION_ADMIN_DATE,"", IssueAction.ERROR)
	, VaccinationAdminDateEndIsDifferentFromStartDate(IssueObject.VACCINATION, IssueType.DIFF_FROM_START, IssueField.VACCINATION_ADMIN_DATE_END,"", IssueAction.ERROR)
	, VaccinationAdminDateEndIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMIN_DATE_END,"", IssueAction.ERROR)
	, VaccinationAdministeredAmountIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", IssueAction.ERROR)
	, VaccinationAdministeredAmountIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_AMOUNT,"", IssueAction.ERROR)
	, VaccinationAdministeredAmountIsValuedAsZero(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "zero", IssueAction.ACCEPT)
	, VaccinationAdministeredAmountIsValuedAsUnknown(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_ADMINISTERED_AMOUNT, "unknown", IssueAction.ACCEPT)
	, VaccinationAdministeredUnitIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueAction.ERROR)
	, VaccinationAdministeredUnitIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueAction.ERROR)
	, VaccinationAdministeredUnitIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueAction.ERROR)
	, VaccinationAdministeredUnitIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueAction.ERROR)
	, VaccinationAdministeredUnitIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ADMINISTERED_UNIT,"", IssueAction.ERROR)
	, VaccinationBodyRouteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_ROUTE,"", IssueAction.ERROR)
	, VaccinationBodyRouteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_ROUTE,"", IssueAction.ERROR)
	, VaccinationBodyRouteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_ROUTE,"", IssueAction.ERROR)
	, VaccinationBodyRouteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_ROUTE,"", IssueAction.ERROR)
	, VaccinationBodyRouteIsInvalidForBodySiteIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_BODY_SITE, IssueField.VACCINATION_BODY_ROUTE,"", IssueAction.ERROR)
	, VaccinationBodyRouteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_ROUTE,"", IssueAction.ERROR)
	, VaccinationBodyRouteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_ROUTE,"", IssueAction.ERROR)
	, VaccinationBodySiteIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_BODY_SITE,"", IssueAction.ERROR)
	, VaccinationBodySiteIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_BODY_SITE,"", IssueAction.ERROR)
	, VaccinationBodySiteIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_BODY_SITE,"", IssueAction.ERROR)
	, VaccinationBodySiteIsInvalidForVaccineIndicated(IssueObject.VACCINATION, IssueType.INVALID_FOR_VACCINE, IssueField.VACCINATION_BODY_SITE,"", IssueAction.ERROR)
	, VaccinationBodySiteIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_BODY_SITE,"", IssueAction.ERROR)
	, VaccinationBodySiteIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_BODY_SITE,"", IssueAction.ERROR)
	, VaccinationCompletionStatusIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueAction.ERROR)
	, VaccinationCompletionStatusIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueAction.ERROR)
	, VaccinationCompletionStatusIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueAction.ERROR)
	, VaccinationCompletionStatusIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueAction.ERROR)
	, VaccinationCompletionStatusIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_COMPLETION_STATUS,"", IssueAction.ERROR)
	, VaccinationCompletionStatusIsValuedAsCompleted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "completed", IssueAction.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsNotAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "not administered", IssueAction.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsPartiallyAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "partially administered", IssueAction.ACCEPT)
	, VaccinationCompletionStatusIsValuedAsRefused(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_COMPLETION_STATUS, "refused", IssueAction.ACCEPT)
	, VaccinationConfidentialityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueAction.ERROR)
	, VaccinationConfidentialityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueAction.ERROR)
	, VaccinationConfidentialityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueAction.ERROR)
	, VaccinationConfidentialityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueAction.ERROR)
	, VaccinationConfidentialityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CONFIDENTIALITY_CODE,"", IssueAction.ERROR)
	, VaccinationConfidentialityCodeIsValuedAsRestricted(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_CONFIDENTIALITY_CODE, "restricted", IssueAction.ACCEPT)
	, VaccinationCptCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationCptCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationCptCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationCptCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationCptCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationCptCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationCptCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationCvxCodeAndCptCodeAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_CVX_CODE_AND_CPT_CODE,"", IssueAction.ERROR)
	, VaccinationFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_NAME,"", IssueAction.ERROR)
	, VaccinationFacilityTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FACILITY_TYPE,"", IssueAction.ERROR)
	, VaccinationFacilityTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FACILITY_TYPE,"", IssueAction.ERROR)
	, VaccinationFacilityTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FACILITY_TYPE,"", IssueAction.ERROR)
	, VaccinationFacilityTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FACILITY_TYPE,"", IssueAction.ERROR)
	, VaccinationFacilityTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FACILITY_TYPE,"", IssueAction.ERROR)
	, VaccinationFacilityTypeIsValuedAsPublic(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "public", IssueAction.ACCEPT)
	, VaccinationFacilityTypeIsValuedAsPrivate(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_FACILITY_TYPE, "private", IssueAction.ACCEPT)
	, VaccinationFillerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationFillerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationFillerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationFillerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationFillerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FILLER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationFinancialEligibilityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueAction.ERROR)
	, VaccinationFinancialEligibilityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueAction.ERROR)
	, VaccinationFinancialEligibilityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueAction.ERROR)
	, VaccinationFinancialEligibilityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueAction.ERROR)
	, VaccinationFinancialEligibilityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_FINANCIAL_ELIGIBILITY_CODE,"", IssueAction.ERROR)
	, VaccinationGivenByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_GIVEN_BY,"", IssueAction.ERROR)
	, VaccinationGivenByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_GIVEN_BY,"", IssueAction.ERROR)
	, VaccinationGivenByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_GIVEN_BY,"", IssueAction.ERROR)
	, VaccinationGivenByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_GIVEN_BY,"", IssueAction.ERROR)
	, VaccinationGivenByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_GIVEN_BY,"", IssueAction.ERROR)
	, VaccinationIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID,"", IssueAction.ERROR)
	, VaccinationIdOfReceiverIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_RECEIVER,"", IssueAction.ERROR)
	, VaccinationIdOfReceiverIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_RECEIVER,"", IssueAction.ERROR)
	, VaccinationIdOfSenderIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ID_OF_SENDER,"", IssueAction.ERROR)
	, VaccinationIdOfSenderIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ID_OF_SENDER,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsAdministeredButAppearsToHistorical(IssueObject.VACCINATION, IssueType.ADMIN_BUT_APPEARS_HISTORICAL, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsHistoricalButAppearsToBeAdministered(IssueObject.VACCINATION, IssueType.HISTORICAL_BUT_APPEARS_ADMIN, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_INFORMATION_SOURCE,"", IssueAction.ERROR)
	, VaccinationInformationSourceIsValuedAsAdministered(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "administered", IssueAction.ACCEPT)
	, VaccinationInformationSourceIsValuedAsHistorical(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_INFORMATION_SOURCE, "historical", IssueAction.ACCEPT)
	, VaccinationVisIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS,"", IssueAction.ERROR)
	, VaccinationVisIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS,"", IssueAction.ERROR)
	, VaccinationVisIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS,"", IssueAction.ERROR)
	, VaccinationVisCvxCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationVisCvxCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationVisCvxCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationVisCvxCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationVisCvxCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_CVX_CODE,"", IssueAction.ERROR)
	, VaccinationVisDocumentTypeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueAction.ERROR)
	, VaccinationVisDocumentTypeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueAction.ERROR)
	, VaccinationVisDocumentTypeIsIncorrect(IssueObject.VACCINATION, IssueType.INCORRECT, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueAction.ERROR)
	, VaccinationVisDocumentTypeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueAction.ERROR)
	, VaccinationVisDocumentTypeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueAction.ERROR)
	, VaccinationVisDocumentTypeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueAction.ERROR)
	, VaccinationVisDocumentTypeIsOutOfDate(IssueObject.VACCINATION, IssueType.OUT_OF_DATE, IssueField.VACCINATION_VIS_DOCUMENT_TYPE,"", IssueAction.ERROR)
	, VaccinationVisPublishedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPublishedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPublishedDateIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPublishedDateIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_VIS_PUBLISHED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPresentedDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPresentedDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPresentedDateIsNotAdminDate(IssueObject.VACCINATION, IssueType.NOT_SAME_AS_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPresentedDateIsBeforePublishedDate(IssueObject.VACCINATION, IssueType.BEFORE_PUBLISHED_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueAction.ERROR)
	, VaccinationVisPresentedDateIsAfterAdminDate(IssueObject.VACCINATION, IssueType.AFTER_ADMIN_DATE, IssueField.VACCINATION_VIS_PRESENTED_DATE,"", IssueAction.ERROR)
	, VaccinationLotExpirationDateIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", IssueAction.ERROR)
	, VaccinationLotExpirationDateIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_EXPIRATION_DATE,"", IssueAction.ERROR)
	, VaccinationLotNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_LOT_NUMBER,"", IssueAction.ERROR)
	, VaccinationLotNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_LOT_NUMBER,"", IssueAction.ERROR)
	, VaccinationManufacturerCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueAction.ERROR)
	, VaccinationManufacturerCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueAction.ERROR)
	, VaccinationManufacturerCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueAction.ERROR)
	, VaccinationManufacturerCodeIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueAction.ERROR)
	, VaccinationManufacturerCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueAction.ERROR)
	, VaccinationManufacturerCodeIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueAction.ERROR)
	, VaccinationManufacturerCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_MANUFACTURER_CODE,"", IssueAction.ERROR)
	, VaccinationOrderControlCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueAction.ERROR)
	, VaccinationOrderControlCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueAction.ERROR)
	, VaccinationOrderControlCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueAction.ERROR)
	, VaccinationOrderControlCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueAction.ERROR)
	, VaccinationOrderControlCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_CONTROL_CODE,"", IssueAction.ERROR)
	, VaccinationOrderFacilityIdIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationOrderFacilityIdIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationOrderFacilityIdIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationOrderFacilityIdIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationOrderFacilityIdIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDER_FACILITY_ID,"", IssueAction.ERROR)
	, VaccinationOrderFacilityNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDER_FACILITY_NAME,"", IssueAction.ERROR)
	, VaccinationOrderedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_ORDERED_BY,"", IssueAction.ERROR)
	, VaccinationOrderedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_ORDERED_BY,"", IssueAction.ERROR)
	, VaccinationOrderedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_ORDERED_BY,"", IssueAction.ERROR)
	, VaccinationOrderedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_ORDERED_BY,"", IssueAction.ERROR)
	, VaccinationOrderedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_ORDERED_BY,"", IssueAction.ERROR)
	, VaccinationPlacerOrderNumberIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationPlacerOrderNumberIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationPlacerOrderNumberIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationPlacerOrderNumberIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationPlacerOrderNumberIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PLACER_ORDER_NUMBER,"", IssueAction.ERROR)
	, VaccinationProductIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_PRODUCT,"", IssueAction.ERROR)
	, VaccinationProductIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_PRODUCT,"", IssueAction.ERROR)
	, VaccinationProductIsInvalidForDateAdministered(IssueObject.VACCINATION, IssueType.INVALID_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", IssueAction.ERROR)
	, VaccinationProductIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_PRODUCT,"", IssueAction.ERROR)
	, VaccinationProductIsUnexpectedForDateAdministered(IssueObject.VACCINATION, IssueType.UNEXPECTED_FOR_DATE_ADMINISTERED, IssueField.VACCINATION_PRODUCT,"", IssueAction.ERROR)
	, VaccinationProductIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_PRODUCT,"", IssueAction.ERROR)
	, VaccinationRecordedByIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_RECORDED_BY,"", IssueAction.ERROR)
	, VaccinationRecordedByIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_RECORDED_BY,"", IssueAction.ERROR)
	, VaccinationRecordedByIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_RECORDED_BY,"", IssueAction.ERROR)
	, VaccinationRecordedByIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_RECORDED_BY,"", IssueAction.ERROR)
	, VaccinationRecordedByIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_RECORDED_BY,"", IssueAction.ERROR)
	, VaccinationRefusalReasonConflictsCompletionStatus(IssueObject.VACCINATION, IssueType.CONFLICTS_WITH_COMPLETION_STATUS, IssueField.VACCINATION_REFUSAL_REASON,"", IssueAction.ERROR)
	, VaccinationRefusalReasonIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_REFUSAL_REASON,"", IssueAction.ERROR)
	, VaccinationRefusalReasonIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_REFUSAL_REASON,"", IssueAction.ERROR)
	, VaccinationRefusalReasonIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_REFUSAL_REASON,"", IssueAction.ERROR)
	, VaccinationRefusalReasonIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_REFUSAL_REASON,"", IssueAction.ERROR)
	, VaccinationRefusalReasonIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_REFUSAL_REASON,"", IssueAction.ERROR)
	, VaccinationSystemEntryTimeIsInFuture(IssueObject.VACCINATION, IssueType.IN_FUTURE, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", IssueAction.ERROR)
	, VaccinationSystemEntryTimeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", IssueAction.ERROR)
	, VaccinationSystemEntryTimeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_SYSTEM_ENTRY_TIME,"", IssueAction.ERROR)
	, VaccinationTradeNameIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_TRADE_NAME,"", IssueAction.ERROR)
	, VaccinationTradeNameIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_TRADE_NAME,"", IssueAction.ERROR)
	, VaccinationTradeNameIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_TRADE_NAME,"", IssueAction.ERROR)
	, VaccinationTradeNameIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_TRADE_NAME,"", IssueAction.ERROR)
	, VaccinationTradeNameIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_TRADE_NAME,"", IssueAction.ERROR)
	, VaccinationTradeNameAndVaccineAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_VACCINE,"", IssueAction.ERROR)
	, VaccinationTradeNameAndManufacturerAreInconsistent(IssueObject.VACCINATION, IssueType.ARE_INCONSISTENT, IssueField.VACCINATION_TRADE_NAME_AND_MANUFACTURER,"", IssueAction.ERROR)
	, VaccinationValidityCodeIsInvalid(IssueObject.VACCINATION, IssueType.INVALID, IssueField.VACCINATION_VALIDITY_CODE,"", IssueAction.ERROR)
	, VaccinationValidityCodeIsDeprecated(IssueObject.VACCINATION, IssueType.DEPRECATE, IssueField.VACCINATION_VALIDITY_CODE,"", IssueAction.ERROR)
	, VaccinationValidityCodeIsIgnored(IssueObject.VACCINATION, IssueType.IGNORED, IssueField.VACCINATION_VALIDITY_CODE,"", IssueAction.ERROR)
	, VaccinationValidityCodeIsMissing(IssueObject.VACCINATION, IssueType.MISSING, IssueField.VACCINATION_VALIDITY_CODE,"", IssueAction.ERROR)
	, VaccinationValidityCodeIsUnrecognized(IssueObject.VACCINATION, IssueType.UNRECOGNIZED, IssueField.VACCINATION_VALIDITY_CODE,"", IssueAction.ERROR)
	, VaccinationValidityCodeIsValuedAsValid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "valid", IssueAction.ACCEPT)
	, VaccinationValidityCodeIsValuedAsInvalid(IssueObject.VACCINATION, IssueType.VALUED_AS, IssueField.VACCINATION_VALIDITY_CODE, "invalid", IssueAction.ACCEPT)
	, UnknownValidationIssue(IssueObject.GENERAL, IssueType.MISSING, IssueField.GENERAL_PROCESSING,"", IssueAction.ERROR)
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

	    private String changePriority = "";
	    private IssueAction defaultIssueAction = null;
	    private String fieldValue = "";
	    private String issueDescription = "";
	    private int issueId = 0;
	    private IssueType issueType;
	    private String reportDenominator;
	    private IssueObject targetObject;
	    private ToolTip toolTip = null;
	    private CodeTable table = null;
	    private String hl7Reference = null;
	    private String hl7ErrorCode = null;
	    private String appErrorCode = null;
	    private IssueField fieldRef;
	    private IssueAction defaultAction;
	    
    private PotentialIssue(IssueObject entity, IssueType type, IssueField fieldRef, String valuation, IssueAction ia) {
    	this.fieldValue = valuation;
    	this.targetObject = entity;
    	this.issueType = type;
    	this.fieldRef = fieldRef;
    	this.defaultAction = ia;
    }
	    
  public String getAppErrorCode()
  {
    return appErrorCode;
  }

  public void setAppErrorCode(String appErrorCode)
  {
    this.appErrorCode = appErrorCode;
  }

  public String getHl7ErrorCode()
  {
    return hl7ErrorCode;
  }

  public void setHl7ErrorCode(String hl7ErrorCode)
  {
    this.hl7ErrorCode = hl7ErrorCode;
  }

  public String getHl7Reference()
  {
    return hl7Reference;
  }

  public void setHl7Reference(String hl7Reference)
  {
    this.hl7Reference = hl7Reference;
  }

  public ToolTip getToolTip()
  {
    if (toolTip == null)
    {
      toolTip = new ToolTip(getDisplayText(), issueDescription);
    }
    return toolTip;
  }
  
  public CodeTable getTable()
  {
    return table;
  }
  public void setTable(CodeTable table)
  {
    this.table = table;
  }

  public String getChangePriority()
  {
    return changePriority;
  }

  public IssueAction getDefaultIssueAction()
  {
    return defaultIssueAction;
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

  public String getIssueDescription()
  {
    return issueDescription;
  }

  public int getIssueId()
  {
    return issueId;
  }

  public IssueType getIssueType()
  {
    return issueType;
  }
  

  public IssueType getIssueTypeEnum()
  {
    return issueType;
  }

  public String getReportDenominator()
  {
    return reportDenominator;
  }

  public IssueField getTargetField()
  {
    return fieldRef;
  }

  public IssueObject getTargetObject()
  {
    return targetObject;
  }

  public IssueFound build(String value) {
	  	IssueFound found = build();
	  	found.getCodeReceived().setCodeValue(value);
		return found;
  }
  
  public IssueFound build(CodeReceived cr) {
	  	IssueFound found = build();
	  	found.setCodeReceived(cr);
		return found;
}
  
  public IssueFound build() {
		IssueFound found = new IssueFound();
		found.setIssue(this);
		CodeReceived cr = new CodeReceived();
		found.setCodeReceived(cr);
		found.setIssueAction(this.defaultAction);//This needs to be equipped to be naunced. This will comes off a profile. 
		return found;
  }
  
  public static IssueFound buildIssue(IssueField field, IssueType type) {
	  PotentialIssue issue = get(field, type);
	  return issue.build();
  }
  
  public static IssueFound buildIssue(IssueField field, IssueType type, CodeReceived cr) {
	  PotentialIssue issue = get(field, type); 
	  return issue.build(cr);
  }
  
  public static IssueFound buildIssue(IssueField field, IssueType type, String value) {
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

}
