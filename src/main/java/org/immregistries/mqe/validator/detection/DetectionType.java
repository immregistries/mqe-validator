package org.immregistries.mqe.validator.detection;

import org.immregistries.mqe.hl7util.ApplicationErrorCode;
import org.immregistries.mqe.hl7util.builder.AckERRCode;
import org.immregistries.mqe.util.validation.MqeDetectionType;

public enum DetectionType implements MqeDetectionType {
  EXCEPTION("exception", "Unexpected application error occured. "),
  BEFORE_BIRTH("is before birth", "Date given is before patient birth date. "),
  DEPRECATED("is deprecated", "Deprecated code value. "),
  IGNORED("is ignored", "Ignored code value. "),
  IN_FUTURE("is in future", "Date given is in the future. "),
  INCOMPLETE("is incomplete", "Incomplete code value. "),
  INVALID("is invalid", "Coded value is recognized and is considered to be an invalid code, which means that there are no valid uses for this coded value. "),
  MISSING("is missing", "No value found, no data sent, nothing to analyze. "), 
  REPEATED("is repeated", "Sent more than once. "),
  UNRECOGNIZED("is unrecognized", "Coded value is not recognized as either valid or invalid because it is unknown to this system. "),
  UNEXPECTED("is unexpected"),
  PRESENT("is present", "Value found, something sent, may be valid or invalid"),
  
  // Vaccine Evaluation
  HEPB_1_ONLY("with only 1 valid HepB dose"),
  HEPB_3("with 3 valid HepB doses"),
  DTAP_4("with 4 valid DTaP doses"),
  PCV_4("with 4 valid PCV doses"),
  POLIO_3("with 3 valid IPV doses"),
  MMR_1("with 1 valid MMR dose"),
  VAR_1("with 1 valid Varicella doses"),
  HEPA_2("with 2 valid HepA doses"),
  HIB_2("with 2 valid Hib Doses"),
  HIB_3("with 3 valid Hib Doses"),
  HAS_INVALID_DOSES_1_OR_MORE("", "Patient has 1 or more invalid doses"),
  HAS_INVALID_DOSES_2_OR_MORE("", "Patient has 2 or more invalid doses"),
  HAS_INVALID_DOSES_3_OR_MORE("", "Patient has 3 or more invalid doses"),
  HAS_INVALID_DOSES_4_OR_MORE("", "Patient has 4 or more invalid doses"),
  HAS_INVALID_DOSES_5_OR_MORE("", "Patient has 5 or more invalid doses"),
  HAS_INVALID_DOSES_10_OR_MORE("", "Patient has 10 or more invalid doses"),


  
  // Vaccines Coverage
  SERIES_4_3_1_3_3_1_4("for series 4:3:1:3:3:1:4"),
  
  // Vaccine Forecast
  HIB("Hib"),
  PCV("PCV"),
  ROTAVIRUS("Rotavirus"),
  HEPB("Hep B"),
  
  //Valued As types:
  VALUED_AS("is valued as"),
  VALUED_AS_NO("is valued as no"),
  VALUED_AS_YES("is valued as yes"),
  VALUED_AS_ADD("is valued as add"),
  VALUED_AS_ADD_OR_UPDATE("is valued as add or update"),
  VALUED_AS_DELETE("is valued as delete"),
  VALUED_AS_UPDATE("is valued as update"),
  VALUED_AS_FOREIGN("is valued as foreign", "Field value is valued as foreign."),
  VALUED_AS_ZERO("is valued as zero"),
  VALUED_AS_UNKNOWN("is valued as unknown"),
  VALUED_AS_COMPLETED("is valued as completed"),
  VALUED_AS_REFUSED("is valued as refused"),
  VALUED_AS_RESTRICTED("is valued as restricted"),
  VALUED_AS_NOT_ADMINISTERED("is valued as not administered"),
  VALUED_AS_ADMINISTERED("is valued as administered"),
  VALUED_AS_PARTIALLY_ADMINISTERED("is valued as partially administered"),
  VALUED_AS_HISTORICAL("is valued as historical"),

  // These are the issue types that are not widely used...

  OUT_OF_ORDER("out of order"),
  NON_STANDARD("is non-standard"),
  NOT_PRECISE("is not precise"),
  MISSING_TIMEZONE("is missing timezone", "Timezone was not included in the date field."),
  UNSUPPORTED("is unsupported"),
  TOO_SHORT("is too short", "Length of the value is shorter than it should be. "),
  TOO_LONG("is too long", "Length of the value is longer than it should be. "),
  UNEXPECTEDLY_SHORT("is unexpectedly short", "Length of the value is shorter than expected. "),
  UNEXPECETDLY_LONG("is unexpectedly long", "Length of the value is loger than expected."),
  INCORRECT("is incorrect", "The value is incorrect. " ),
  INCONSISTENT("is inconsistent", "Value is inconsistent with other values. "),
  OUT_OF_DATE("is out-of-date"),
  VERY_LONG_AGO("is very long ago", "Date is very long time in the past. "),
  AFTER_SUBMISSION("is after submission", "Date is after submission. "),
  UNEXPECTED_FORMAT("is an unexpected format", "Value format does not make sense. "),
  NOT_USABLE("is not usable")

  // These are the REALLY specific ones
  ,
  UNDERAGE("is underage"),
  DIFFERENT_FROM_PATIENT_ADDRESS("is different from patient address"),
  MAY_BE_AN_INITIAL("may be initial"),
  MAY_INCLUDE_MIDDLE_INITIAL("may include middle initial"),
  HAS_JUNK_NAME("has junk name"),
  MAY_BE_TEST_NAME("may be test name"),
  KNOWN_TEST_NAME("is a known test name"),
  INVALID_PREFIXES("has invalid prefixes"),
  INVALID_SUFFIXES("has invalid suffixes"),
  INVALID_INFIXES("has invalid infixes"),
  ARE_INCONSISTENT("are inconsistent"),
  CONFLICTS_COMPLETION_STATUS("conflicts completion status"),
  UNEXPECTED_FOR_DATE_ADMINISTERED("is unexpected for date administered"),
  UNEXPECTED_FOR_FINANCIAL_ELIGIBILITY("is unexpected for financial eligibility"),
  INVALID_FOR_DATE_ADMINISTERED("is invalid for date administered"),
  AFTER_ADMIN_DATE("is after admin date"),
  NOT_SAME_AS_ADMIN_DATE("is not admin date"),
  BEFORE_PUBLISHED_DATE("is before published date"),
  BEFORE_VERSION_DATE("is before version date"),
  NOT_RESPONSIBLE_PARTY("is not responsible party"),
  ADMINISTERED_BUT_APPEARS_TO_HISTORICAL("is administered but appears to historical"),
  HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED("is historical but appears to be administered"),
  INVALID_FOR_VACCINE("is invalid for vaccine indicated"),
  INVALID_FOR_BODY_SITE("is invalid for body site indicated"),
  DIFF_FROM_START("is different from start date"),
  REPORTED_LATE("is reported late"),
  ON_LAST_DAY_OF_MONTH("is on last day of month"),
  ON_FIRST_DAY_OF_MONTH("is on first day of month"),
  ON_FIFTEENTH_DAY_OF_MONTH("is on 15th day of month"),
  BEFORE_OR_AFTER_VALID_DATE_FOR_AGE("is before or after when valid for patient age"),
  BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE("is before or after when expected for patient age"),
  BEFORE_OR_AFTER_LICENSED_DATE_RANGE("is before or after licensed vaccine range","Date is outside of licensed vaccine date range."),
  BEFORE_OR_AFTER_EXPECTED_DATE_RANGE("is before or after expected vaccine usage range", "Date is outside of expected vaccine date range."),
  VALUED_BAD_ADDRESS("is valued bad address", "Address Type indicates the address is a bad address. "),
  AFTER_SYSTEM_ENTRY_DATE("is after system entry date"),
  AFTER_PATIENT_DEATH_DATE("is after patient death date"),
  AFTER_MESSAGE_SUBMITTED("is after message submitted"),
  AFTER_LOT_EXPIRATION("is after lot expiration date"),
  MAY_BE_PREVIOUSLY_REPORTED("may be variation of previously reported codes"),
  NOT_VACCINE("is not vaccine"),
  NOT_SPECIFIC("is not specific"),
  NOT_VALUED_LEGAL("is not valued legal"),
  MAY_BE_TEMPORARY_NEWBORN_NAME("may be temporary newborn name"),
  SAME_AS_UNDERAGE_PATIENT("is same as underage patient"),
  VACCINATION_COUNT_EXCEEDS_EXPECTATIONS("has more vaccinations than expected"),
  MISSING_AND_MULTIPLE_BIRTH_INDICATED("is missing and multiple birth indicated"),
  TWO_VACCINATION_EVENTS_BY_SIX_YEARS("has at least two vaccination events before six years of age"),
  ZERO("is zero"),
  LESS_THAN_FIFTEEN_DOSES_BY_24_MONTHS("is less than 15 doses by 24 months"),
  MUTLIPLES("has multiples"),
  
  IS_ON_TIME("is on time"),
  IS_LATE("is late"),
  IS_VERY_LATE("is very late"),
  IS_TOO_LATE("is too late");
	
  public final String wording;
  public final String description;

  DetectionType(String wording) {
    this.wording = wording;
    this.description = null;
  }
  
  DetectionType(String wording, String description) {
    this.wording = wording;
    this.description = description;
  }

  public String getWording() {
    return wording;
  }

  public String getDescription() {
    return description;
  }

  public AckERRCode getAckErrCode() {
    switch (this) {
      case UNRECOGNIZED:
        return AckERRCode.CODE_103_TABLE_VALUE_NOT_FOUND;
      case INVALID:
        return AckERRCode.CODE_102_DATA_TYPE_ERROR;
      case MISSING:
        return AckERRCode.CODE_101_REQUIRED_FIELD_MISSING;
      case EXCEPTION:
        return AckERRCode.CODE_207_APPLICATION_INTERNAL_ERROR;
      default:
        return AckERRCode.CODE_0_MESSAGE_ACCEPTED;
    }
  }

  public ApplicationErrorCode  getApplicationErrorCode() {
    switch (this) {
      case ADMINISTERED_BUT_APPEARS_TO_HISTORICAL:
      case ARE_INCONSISTENT:
      case CONFLICTS_COMPLETION_STATUS:
      case DEPRECATED:
      case DIFFERENT_FROM_PATIENT_ADDRESS:
      case DIFF_FROM_START:
      case BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE:
      case BEFORE_OR_AFTER_LICENSED_DATE_RANGE:
      case BEFORE_OR_AFTER_EXPECTED_DATE_RANGE:
      case BEFORE_OR_AFTER_VALID_DATE_FOR_AGE:
        return ApplicationErrorCode.ILLOGICAL_VALUE_ERROR;
      case AFTER_SYSTEM_ENTRY_DATE:
      case AFTER_ADMIN_DATE:
      case AFTER_LOT_EXPIRATION:
      case AFTER_MESSAGE_SUBMITTED:
      case AFTER_PATIENT_DEATH_DATE:
      case AFTER_SUBMISSION:
      case BEFORE_BIRTH:
      case BEFORE_PUBLISHED_DATE:
      case BEFORE_VERSION_DATE:
        return ApplicationErrorCode.ILLOGICAL_DATE_ERROR;
      case EXCEPTION:
      case HAS_JUNK_NAME:
      case HISTORICAL_BUT_APPEARS_TO_BE_ADMINISTERED:
      case IGNORED:
      case INCOMPLETE:
      case INCONSISTENT:
      case INCORRECT:
      case INVALID:
      case INVALID_FOR_BODY_SITE:
      case INVALID_FOR_DATE_ADMINISTERED:
      case INVALID_FOR_VACCINE:
      case INVALID_PREFIXES:
      case IN_FUTURE:
      case KNOWN_TEST_NAME:
      case MAY_BE_AN_INITIAL:
      case MAY_BE_PREVIOUSLY_REPORTED:
      case MAY_BE_TEMPORARY_NEWBORN_NAME:
      case MAY_BE_TEST_NAME:
      case MAY_INCLUDE_MIDDLE_INITIAL:
      case MISSING:
      case MISSING_AND_MULTIPLE_BIRTH_INDICATED:
      case MISSING_TIMEZONE:
      case NON_STANDARD:
      case NOT_PRECISE:
      case NOT_RESPONSIBLE_PARTY:
      case NOT_SAME_AS_ADMIN_DATE:
      case NOT_SPECIFIC:
      case NOT_VACCINE:
      case NOT_VALUED_LEGAL:
      case ON_FIFTEENTH_DAY_OF_MONTH:
      case ON_FIRST_DAY_OF_MONTH:
      case ON_LAST_DAY_OF_MONTH:
      case OUT_OF_DATE:
      case OUT_OF_ORDER:
      case REPEATED:
      case REPORTED_LATE:
      case SAME_AS_UNDERAGE_PATIENT:
      case TOO_LONG:
      case TOO_SHORT:
      case UNDERAGE:
      case UNEXPECETDLY_LONG:
      case UNEXPECTED:
      case UNEXPECTEDLY_SHORT:
      case UNEXPECTED_FORMAT:
      case UNEXPECTED_FOR_DATE_ADMINISTERED:
      case UNRECOGNIZED:
      case UNSUPPORTED:
      case VALUED_AS:
      case VALUED_BAD_ADDRESS:
      case VERY_LONG_AGO:
      default:
        return ApplicationErrorCode.INVALID_VALUE;
    }
  }
}
