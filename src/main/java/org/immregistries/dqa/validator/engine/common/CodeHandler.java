package org.immregistries.dqa.validator.engine.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.generated.CodeStatus;
import org.immregistries.dqa.codebase.client.generated.UseAge;
import org.immregistries.dqa.codebase.client.generated.UseDate;
import org.immregistries.dqa.codebase.client.reference.CodeStatusValue;
import org.immregistries.dqa.validator.engine.codes.CodeRepository;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.IssueType;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum CodeHandler {
		INSTANCE;

	/**
	 * This codemap object will get all the information we know about a code.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeHandler.class);
	protected final DateUtility datr = DateUtility.INSTANCE;
	protected final CodeRepository repo = CodeRepository.INSTANCE;

	public List<ValidationIssue> handleCode(String value, IssueField field) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		//LOGGER.info("value:" + value + " field: " + field);

		if (StringUtils.isBlank(value)) {
			issueToList(field, IssueType.MISSING, issues);
			return issues;
		}

		Code c = repo.getCodeFromValue(value, field.getCodesetType());
		if (c!=null) {
			CodeStatus status = c.getCodeStatus();
			String statusValue = status.getStatus();
			CodeStatusValue csVal = CodeStatusValue.getBy(statusValue);
			switch (csVal) {
				case VALID:
					break;
				case INVALID:
					issueToList(field, IssueType.INVALID, issues);
					break;
				case DEPRECATED:
					issueToList(field, IssueType.DEPRECATED, issues);
					break;
				case IGNORED:
					issueToList(field, IssueType.IGNORED, issues);
					break;
				case UNRECOGNIZED:
					issueToList(field, IssueType.UNRECOGNIZED, issues);
					break;
				default:
					issueToList(field, IssueType.UNRECOGNIZED, issues);
					break;
			}
		} else {
			issueToList(field, IssueType.UNRECOGNIZED, issues);
		}

		return issues;
	}

	public List<ValidationIssue> handleUseDate(Code codedValue, String usedDateString, IssueField field) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();

		if (codedValue == null || StringUtils.isEmpty(usedDateString)) {
			return issues;
		}

		UseDate useDate = codedValue.getUseDate();

		if (useDate != null) {

			String notExpectedAfterDateString = useDate.getNotExpectedAfter();
			String notExpectedBeforeDateString = useDate.getNotExpectedBefore();

			String notBeforeDateString = useDate.getNotBefore();
			String notAfterDateString = useDate.getNotAfter();
			LOGGER.debug("Expected Dates: notExpectedAfterDateString["+notExpectedAfterDateString+"] notExpectedBeforeDateString[" + notExpectedBeforeDateString + "]");
			LOGGER.debug("Dont use Dates: notBeforeDateString["+notBeforeDateString+"] notAfterDateString[" + notAfterDateString + "]");
			if (datr.isOutsideOfRange(usedDateString, notBeforeDateString, notAfterDateString)) {
				LOGGER.info("Adding issue for: " + field + " - " + IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE + " - " + issues);
				issueToList(field, IssueType.BEFORE_OR_AFTER_USAGE_DATE_RANGE, issues);
			} else if (datr.isOutsideOfRange(usedDateString, notExpectedBeforeDateString, notExpectedAfterDateString)) {
				LOGGER.info("Adding issue for: " + field + " - " + IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE + " - " + issues);
				issueToList(field, IssueType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, issues);
			}  else {
				LOGGER.info("NO useDate issues for: " + field);
			}
		}
		return issues;
	}

	public List<ValidationIssue> handleAgeDate(Code codedValue, Date birthDate, Date referenceDate, IssueField field) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();

		if (codedValue == null || birthDate == null || referenceDate == null) {
			LOGGER.debug("One of the inputs is null.  returning no issues: code[" + codedValue+"] birthDate["+birthDate+"] refDate["+referenceDate+"]");
			return issues;
		}

		UseAge useAge = codedValue.getUseAge();

		if (useAge != null) {
			int ageInMonths = datr.monthsBetween(birthDate, referenceDate);
			LOGGER.debug("age in months: " + ageInMonths);
			int notBeforeMonthByte = useAge.getNotBeforeMonth();
			int notNotAfterMonthByte = useAge.getNotAfterMonth();
			LOGGER.debug("notBeforeMonthByte["+notBeforeMonthByte+"] notNotAfterMonthByte["+notNotAfterMonthByte+"]");
			if (ageInMonths > notNotAfterMonthByte || ageInMonths < notBeforeMonthByte) {
				LOGGER.info("Adding issue for: " + field + " - " + IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE + " - " + issues);
				issueToList(field, IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, issues);
			}
		}
		return issues;
	}

	protected void issueToList(IssueField field, IssueType type, List<ValidationIssue> issues) {
		if (issues == null) {
			throw new IllegalArgumentException("Empty list sent in for issueToList.  This should not happen.  IssueField: " + field + " Issue Type: " + type);
		}

		MessageAttribute issue = MessageAttribute.get(field, type);
		if (issue != null) {
			issues.add(issue.build());
		} else {
			LOGGER.warn("Checking for a condition that has no corresponding PotentialIssue. Field: " + field + " IssueType: "+ IssueType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE);
		}
	}
}
