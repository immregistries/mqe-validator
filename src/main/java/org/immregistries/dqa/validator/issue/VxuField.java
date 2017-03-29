package org.immregistries.dqa.validator.issue;

import org.immregistries.dqa.codebase.client.reference.CodesetType;

public enum VxuField {
	/*
	 * What do you think about the idea of tagging some of these bad boys as "Coded"
	 */
	
	/*
	 * What do you think about tagging the HL7 Location for these bad boys. Pull them from the MessageAttribute class.  
	 */
	
			//general
			  AUTHORIZATION("authorization", null, null)
			, CONFIGURATION("configuration", null, null)
			, PARSE("parse", null, null)
			, PROCESSING("processing", null, null)
			
			//message header
			, MESSAGE_ACCEPT_ACK_TYPE("accept ack type", CodesetType.ACKNOWLEDGEMENT_TYPE, null)
			, MESSAGE_ALT_CHARACTER_SET("alt character set", null, null)
			, MESSAGE_APP_ACK_TYPE("app ack type", CodesetType.ACKNOWLEDGEMENT_TYPE, null)
			, MESSAGE_CHARACTER_SET("character set", null, null)
			, MESSAGE_COUNTRY_CODE("country code", null, null)
			, MESSAGE_ENCODING_CHARACTER("encoding character", null, null)
			, MESSAGE_CONTROL_ID("message control id", null, null)
			, MESSAGE_DATE("message date", null, null)
			, MESSAGE_PROFILE_ID("message profile id", null, null)
			, MESSAGE_TRIGGER("message trigger", null, null)
			, MESSAGE_TYPE("message type", null, null)
			, MESSAGE_PROCESSING_ID("processing id", null, null)
			, MESSAGE_RECEIVING_APPLICATION("receiving application", null, null)
			, MESSAGE_RECEIVING_FACILITY("receiving facility", null, null)
			, MESSAGE_SEGMENT("segment", null, null)
			, MESSAGE_SENDING_APPLICATION("sending application", null, null)
			, MESSAGE_SENDING_FACILITY("sending facility", null, null)
			, MESSAGE_VERSION("version", null, null)
			
			//Next of kin
			, NEXT_OF_KIN_ADDRESS("address", null, null)
			, NEXT_OF_KIN_ADDRESS_CITY("address city", null, null)
			, NEXT_OF_KIN_ADDRESS_COUNTRY("address country", null, null)
			, NEXT_OF_KIN_ADDRESS_COUNTY("address county", null, null)
			, NEXT_OF_KIN_ADDRESS_STATE("address state", null, null)
			, NEXT_OF_KIN_ADDRESS_STREET("address street", null, null)
			, NEXT_OF_KIN_ADDRESS_STREET2("address street2", null, null)
			, NEXT_OF_KIN_ADDRESS_TYPE("address type", null, null)
			, NEXT_OF_KIN_ADDRESS_ZIP("address zip", null, null)
			, NEXT_OF_KIN_NAME("name", null, null)
			, NEXT_OF_KIN_NAME_FIRST("name first", null, null)
			, NEXT_OF_KIN_NAME_LAST("name last", null, null)
			, NEXT_OF_KIN_PHONE_NUMBER("phone number", null, null)
			, NEXT_OF_KIN_RELATIONSHIP("relationship", null, null)
			, NEXT_OF_KIN_SSN("SSN", null, null)
			
			//observation
			, OBSERVATION_VALUE_TYPE("value type", CodesetType.HL7_VALUE_TYPE, null)
			, OBSERVATION_IDENTIFIER_CODE("identifier code", CodesetType.OBSERVATION_IDENTIFIER, null)
			, OBSERVATION_VALUE("value", null, null)
			, OBSERVATION_DATE_TIME_OF_OBSERVATION("date time of observation", null, null)
			
			//patient
			, PATIENT_ADDRESS("address", null, null)
			, PATIENT_ADDRESS_CITY("address city", null, null)
			, PATIENT_ADDRESS_COUNTRY("address country", null, null)
			, PATIENT_ADDRESS_COUNTY("address county", null, null)
			, PATIENT_ADDRESS_STATE("address state", null, null)
			, PATIENT_ADDRESS_STREET("address street", null, null)
			, PATIENT_ADDRESS_STREET2("address street2", null, null)
			, PATIENT_ADDRESS_TYPE("address type", null, null)
			, PATIENT_ADDRESS_ZIP("address zip", null, null)
			, PATIENT_ALIAS("alias", null, null)
			, PATIENT_BIRTH_DATE("birth date", null, null)
			, PATIENT_BIRTH_INDICATOR("birth indicator", null, null)
			, PATIENT_BIRTH_ORDER("birth order", CodesetType.BIRTH_ORDER, null)
			, PATIENT_BIRTH_PLACE("birth place", null, null)
			, PATIENT_BIRTH_REGISTRY_ID("birth registry id", null, null)
			, PATIENT_CLASS("class", null, null)
			, PATIENT_DEATH_DATE("death date", null, null)
			, PATIENT_DEATH_INDICATOR("death indicator", null, null)
			, PATIENT_ETHNICITY("ethnicity", CodesetType.PATIENT_ETHNICITY, null)
			, PATIENT_GENDER("gender", CodesetType.PATIENT_SEX, null)
			, PATIENT_GUARDIAN_ADDRESS("guardian address", null, null)
			, PATIENT_GUARDIAN_ADDRESS_CITY("guardian address city", null, null)
			, PATIENT_GUARDIAN_ADDRESS_STATE("guardian address state", null, null)
			, PATIENT_GUARDIAN_ADDRESS_STREET("guardian address street", null, null)
			, PATIENT_GUARDIAN_ADDRESS_ZIP("guardian address zip", null, null)
			, PATIENT_GUARDIAN_NAME("guardian name", null, null)
			, PATIENT_GUARDIAN_NAME_FIRST("guardian name first", null, null)
			, PATIENT_GUARDIAN_NAME_LAST("guardian name last", null, null)
			, PATIENT_GUARDIAN_RESPONSIBLE_PARTY("guardian responsible party", null, null)
			, PATIENT_GUARDIAN_PHONE("guardian phone", null, null)
			, PATIENT_GUARDIAN_RELATIONSHIP("guardian relationship", null, null)
			, PATIENT_IMMUNITY_CODE("immunity code", CodesetType.EVIDENCE_OF_IMMUNITY, null)
			, PATIENT_IMMUNIZATION_REGISTRY_STATUS("immunization registry status", null, null)
			, PATIENT_MEDICAID_NUMBER("Medicaid number", null, null)
			, PATIENT_MIDDLE_NAME("middle name", null, "PID-5.3")
			, PATIENT_MOTHERS_MAIDEN_NAME("mother's maiden name", null, "PID-6")
			, PATIENT_NAME("name", null, null)
			, PATIENT_NAME_FIRST("name first", null, null)
			, PATIENT_NAME_LAST("name last", null, null)
			, PATIENT_NAME_TYPE_CODE("name type code", CodesetType.PERSON_NAME_TYPE, null)
			, PATIENT_PHONE("phone", null, null)
			, PATIENT_PHONE_TEL_USE_CODE("phone tel use code", CodesetType.TELECOMMUNICATION_USE, null)
			, PATIENT_PHONE_TEL_EQUIP_CODE("phone tel equip code", CodesetType.TELECOMMUNICATION_EQUIPMENT, null)
			, PATIENT_PRIMARY_FACILITY_ID("primary facility id", null, null)
			, PATIENT_PRIMARY_FACILITY_NAME("primary facility name", null, null)
			, PATIENT_PRIMARY_LANGUAGE("primary language", CodesetType.PERSON_LANGUAGE, null)
			, PATIENT_PRIMARY_PHYSICIAN_ID("primary physician id", CodesetType.PHYSICIAN_NUMBER, null)
			, PATIENT_PRIMARY_PHYSICIAN_NAME("primary physician name", null, null)
			, PATIENT_PROTECTION_INDICATOR("protection indicator", CodesetType.PATIENT_PROTECTION, null)
			, PATIENT_PUBLICITY_CODE("publicity code", CodesetType.PATIENT_PUBLICITY, null)
			, PATIENT_RACE("race", CodesetType.PATIENT_RACE, null)
			, PATIENT_REGISTRY_ID("registry id", null, null)
			, PATIENT_REGISTRY_STATUS("registry status", null, null)
			, PATIENT_SSN("SSN", null, null)
			, PATIENT_SUBMITTER_ID("submitter id", null, null)
			, PATIENT_SUBMITTER_ID_AUTHORITY("submitter id authority", null, null)
			, PATIENT_SUBMITTER_ID_TYPE_CODE("submitter id type code", null, null)
			, PATIENT_SYSTEM_CREATION_DATE("system creation date", null, null)
			, PATIENT_VFC_EFFECTIVE_DATE("VFC effective date", null, null)
			, PATIENT_VFC_STATUS("VFC status", CodesetType.FINANCIAL_STATUS_CODE, null)
			, PATIENT_WIC_ID("WIC id", null, null)
			
			//vaccination
			, VACCINATION_ACTION_CODE("action code", CodesetType.VACCINATION_ACTION_CODE, null)
			, VACCINATION_ADMIN_CODE("admin code", CodesetType.VACCINATION_CVX_CODE, null)
			, VACCINATION_ADMIN_CODE_TABLE("admin code table", null, null)
			, VACCINATION_ADMIN_DATE("admin date", null, null)
			, VACCINATION_ADMIN_DATE_END("admin date end", null, null)
			, VACCINATION_ADMINISTERED_AMOUNT("administered amount", null, null)
			, VACCINATION_ADMINISTERED_UNIT("administered unit", CodesetType.ADMINISTRATION_UNIT, null)
			, VACCINATION_BODY_ROUTE("body route", CodesetType.BODY_ROUTE, null)
			, VACCINATION_BODY_SITE("body site", CodesetType.BODY_SITE, null)
			, VACCINATION_COMPLETION_STATUS("completion status", CodesetType.VACCINATION_COMPLETION, null)
			, VACCINATION_CONFIDENTIALITY_CODE("confidentiality code", CodesetType.VACCINATION_CONFIDENTIALITY, null)
			, VACCINATION_CPT_CODE("CPT code", CodesetType.VACCINATION_CPT_CODE, null)
			, VACCINATION_CVX_CODE("CVX code", CodesetType.VACCINATION_CVX_CODE, null)
			, VACCINATION_CVX_CODE_AND_CPT_CODE("CVX code and CPT code", null, null)
			, VACCINATION_FACILITY_ID("facility id", null, null)
			, VACCINATION_FACILITY_NAME("facility name", null, null)
			, VACCINATION_FACILITY_TYPE("facility type", null, null)
			, VACCINATION_FILLER_ORDER_NUMBER("filler order number", null, null)
			, VACCINATION_FINANCIAL_ELIGIBILITY_CODE("financial eligibility code", CodesetType.FINANCIAL_STATUS_CODE, null)
			, VACCINATION_GIVEN_BY("given by", null, null)
			, VACCINATION_ID("id", null, null)
			, VACCINATION_ID_OF_RECEIVER("id of receiver", null, null)
			, VACCINATION_ID_OF_SENDER("id of sender", null, null)
			, VACCINATION_INFORMATION_SOURCE("information source", CodesetType.VACCINATION_INFORMATION_SOURCE, null)
			, VACCINATION_VIS("VIS", null, null)
			, VACCINATION_VIS_VERSION_DATE("VIS Version Date", null, null)
			, VACCINATION_VIS_DELIVERY_DATE("VIS Delivery Date", null, null)
			, VACCINATION_VIS_CVX_CODE("VIS CVX Code", CodesetType.VACCINATION_VIS_CVX_CODE, null)
			, VACCINATION_VIS_DOCUMENT_TYPE("VIS document type", null, null)
			, VACCINATION_VIS_PUBLISHED_DATE("VIS published date", null, null)
			, VACCINATION_VIS_PRESENTED_DATE("VIS presented date", null, null)
			, VACCINATION_LOT_EXPIRATION_DATE("lot expiration date", null, null)
			, VACCINATION_LOT_NUMBER("lot number", null, null)
			, VACCINATION_MANUFACTURER_CODE("manufacturer code", CodesetType.VACCINATION_MANUFACTURER_CODE, null)
			, VACCINATION_ORDER_CONTROL_CODE("order control code", null, null)
			, VACCINATION_ORDER_FACILITY_ID("order facility id", null, null)
			, VACCINATION_ORDER_FACILITY_NAME("order facility name", null, null)
			, VACCINATION_ORDERED_BY("ordered by", null, null)
			, VACCINATION_PLACER_ORDER_NUMBER("placer order number", null, null)
			, VACCINATION_PRODUCT("product", CodesetType.VACCINE_PRODUCT, null)
			, VACCINATION_RECORDED_BY("recorded by", null, null)
			, VACCINATION_REFUSAL_REASON("refusal reason", CodesetType.VACCINATION_REFUSAL, null)
			, VACCINATION_SYSTEM_ENTRY_TIME("system entry time", null, null)
			, VACCINATION_TRADE_NAME("trade name", null, null)
			, VACCINATION_TRADE_NAME_AND_VACCINE("trade name and vaccine", null, null)
			, VACCINATION_TRADE_NAME_AND_MANUFACTURER("trade name and manufacturer", null, null)
			, VACCINATION_VALIDITY_CODE("validity code", null, null)
			, NONE("object", null, null)
			;
			  
		private final String fieldDescription;
		private final CodesetType valueType;
		private final String hl7Field;
		
		private VxuField(String fieldDesc, CodesetType typeOfValue, String hl7Field) {
			this.fieldDescription = fieldDesc;
			this.valueType = typeOfValue;
			this.hl7Field = hl7Field;
		}
		
		public String getFieldDescription() {
			return fieldDescription;
		}

		public CodesetType getCodesetType() {
			return valueType;
		}

		public String getHl7Field() {
			return hl7Field;
		}

}
