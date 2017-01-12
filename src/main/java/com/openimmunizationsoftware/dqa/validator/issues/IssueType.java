package com.openimmunizationsoftware.dqa.validator.issues;

public enum IssueType {
	 	 ISSUE_TYPE_EXCEPTION("exception")
	    ,BEFORE_BIRTH("is before birth")
	    ,DEPRECATE("is deprecated")
	    ,IGNORED("is ignored")
	    ,IN_FUTURE("is in future")
	    ,INCOMPLETE("is incomplete")
	    ,INVALID("is invalid")
	    ,MISSING("is missing")
	    ,REPEATED("is repeated")
	    ,UNRECOGNIZED("is unrecognized")
	    ,UNEXPECTED("is unexpected")
	    ,VALUED_AS("is valued as")
	    
	    //These are the issue types that are not widely used...
	    ,OUT_OF_ORDER("out of order")
	    ,NON_STANDARD("is non-standard")
	    ,NOT_PRECISE("is not precise")
	    ,MISSING_TIMEZONE("is missing timezone")
	    ,UNSUPPORTED("is unsupported")
	    ,TOO_SHORT("is too short")
	    ,TOO_LONG("is too long")
	    ,UNEXPECTEDLY_SHORT("is unexpectedly short")
	    ,UNEXPECETDLY_LONG("is unexpectedly long")
	    ,INCORRECT("is incorrect")
	    ,INCONSISTENT("is inconsistent")
	    ,OUT_OF_DATE("is out-of-date")
	    ,VERY_LONG_AGO("is very long ago")
	    ,AFTER_SUBMISSION("is after submission")
	    
	    
	    //These are the REALLY specific ones
	    ,UNDERAGE("is underage")
	    ,DIFFERENT_FROM_PATIENT_ADDRESS("is different from patient address")
	    ,MAY_BE_AN_INITIAL("may be initial")
	    ,MAY_INCLUDE_MIDDLE_INITIAL("may include middle initial")
	    ,HAS_JUNK_NAME("has junk name")
	    ,MAY_BE_TEST_NAME("may be test name")
	    ,KNOWN_TEST_NAME("is a known test name")
	    ,INVALID_PREFIXES("has invalid prefixes")
	    ,ARE_INCONSISTENT("are inconsistent")
	    ,CONFLICTS_WITH_COMPLETION_STATUS("conflicts completion status")
	    ,UNEXPECTED_FOR_DATE_ADMINISTERED("is unexpected for date administered")
	    ,INVALID_FOR_DATE_ADMINISTERED("is invalid for date administered")
	    ,AFTER_ADMIN_DATE("is after admin date")
	    ,NOT_SAME_AS_ADMIN_DATE("is not admin date")
	    ,BEFORE_PUBLISHED_DATE("is before published date")

	    
	    ,NOT_RESPONSIBLE_PARTY("is not responsible party")
	    
	    ,ADMIN_BUT_APPEARS_HISTORICAL("is administered but appears to historical")
	    ,HISTORICAL_BUT_APPEARS_ADMIN("is historical but appears to be administered")
	    ,INVALID_FOR_VACCINE("is invalid for vaccine indicated")
	    ,INVALID_FOR_BODY_SITE("is invalid for body site indicated")
	    
	    ,DIFF_FROM_START("is different from start date")
	    ,REPORTED_LATE("is reported late")
	    ,ON_LAST_DAY_OF_MONTH("is on last day of month")
	    ,ON_FIRST_DAY_OF_MONTH("is on first day of month")
	    ,ON_FIFTEENTH_DAY_OF_MONTH("is on 15th day of month")
	    ,BEFORE_OR_AFTER_VALID_DATE_FOR_AGE("is before or after when valid for patient age")
	    ,BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE("is before or after when expected for patient age")
	    ,BEFORE_OR_AFTER_LICENSED_DATE_RANGE("is before or after licensed vaccine range")
	    ,BEFORE_OR_AFTER_USAGE_DATE_RANGE("is before or after expected vaccine usage range")
	    
	    ,VALUED_BAD_ADDRESS("is valued bad address")
	    ,AFTER_SYSTEM_ENTRY_DATE("is after system entry date")
	    ,AFTER_PATIENT_DEATH_DATE("is after patient death date")
	    ,AFTER_MESSAGE_SUBMITTED("is after message submitted")
	    ,AFTER_LOT_EXPIRATION("is after lot expiration date")
	    
	    ,MAY_BE_PREVIOUSLY_REPORTED("may be variation of previously reported codes")
	    
	    
	    ,NOT_VACCINE("is not vaccine")
	    ,NOT_SPECIFIC("is not specific")
	    
	    ,NOT_VALUED_LEGAL("is not valued legal")
	    
	    ,MAY_BE_TEMPORARY_NEWBORN_NAME("may be temporary newborn name")
	    
	    
	    ,SAME_AS_UNDERAGE_PATIENT("is same as underage patient")
	    
	    ,MISSING_AND_MULTIPLE_BIRTH_INDICATED("is missing and multiple birth indicated")
	    
	    
	    
	    
	    
	    ;
	    
	    private String wording;
	    private IssueType (String wording) {
	    	this.wording = wording;
	    }
	    
	    public String getText() {
	    	return wording;
	    }
}
