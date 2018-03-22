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
import org.immregistries.dqa.core.util.DateUtility;
import org.immregistries.dqa.hl7util.model.MetaFieldInfo;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.DetectionType;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.codes.CodeRepository;
import org.immregistries.dqa.vxu.MetaFieldInfoData;
import org.immregistries.dqa.vxu.VxuField;
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

  public List<Detection> getDetectionsForField(VxuField field) {
    List<Detection> fieldDetections = new ArrayList<>();
    for (DetectionType type : DetectionType.values()) {
      fieldDetections.add(Detection.get(field, type));
    }
    return fieldDetections;
  }


  public List<ValidationReport> handleCode(String value, VxuField field, MetaFieldInfoData meta) {
    List<ValidationReport> issues = new ArrayList<>();
    // LOGGER.info("value:" + value + " field: " + field);

    if (StringUtils.isBlank(value)) {
      issues.add(issueForField(field, DetectionType.MISSING, meta));
      return issues;
    }

    Code c = repo.getCodeFromValue(value, field.getCodesetType());
    return handleCode(c, field, value, meta);
  }

  public List<ValidationReport> handleCode(Code c, VxuField field, String value,
      MetaFieldInfoData meta) {
    List<ValidationReport> issues = new ArrayList<>();
    LOGGER.info("handleCode - Code " + value + " for field " + field + " found? " + (c != null));
    if (c != null) {
      CodeStatus status = c.getCodeStatus();
      String statusValue = status.getStatus();
      CodeStatusValue csVal = CodeStatusValue.getBy(statusValue);
      switch (csVal) {
        case VALID:
          break;
        case INVALID:
          issues.add(issueForField(field, DetectionType.INVALID, value, meta));
          break;
        case DEPRECATED:
          issues.add(issueForField(field, DetectionType.DEPRECATED, value, meta));
          break;
        case IGNORED:
          issues.add(issueForField(field, DetectionType.IGNORED, value, meta));
          break;
        case UNRECOGNIZED:
          issues.add(issueForField(field, DetectionType.UNRECOGNIZED, value, meta));
          break;
        default:
          issues.add(issueForField(field, DetectionType.UNRECOGNIZED, value, meta));
          break;
      }
    } else {
      issues.add(issueForField(field, DetectionType.UNRECOGNIZED, value, meta));
    }

    return issues;
  }

  public List<ValidationReport> handleUseDate(Code codedValue, String usedDateString,
      VxuField field, MetaFieldInfoData meta) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();

    if (codedValue == null || StringUtils.isEmpty(usedDateString)) {
      return issues;
    }

    UseDate useDate = codedValue.getUseDate();

    if (useDate != null) {

      String notExpectedAfterDateString = useDate.getNotExpectedAfter();
      String notExpectedBeforeDateString = useDate.getNotExpectedBefore();

      String notBeforeDateString = useDate.getNotBefore();
      String notAfterDateString = useDate.getNotAfter();
      LOGGER.debug("Expected Dates: notExpectedAfterDateString[" + notExpectedAfterDateString
          + "] notExpectedBeforeDateString[" + notExpectedBeforeDateString + "]");
      LOGGER.debug("Dont use Dates: notBeforeDateString[" + notBeforeDateString
          + "] notAfterDateString[" + notAfterDateString + "]");
      if (datr.isOutsideOfRange(usedDateString, notBeforeDateString, notAfterDateString)) {
        LOGGER.info("Adding issue for: " + field + " - "
            + DetectionType.BEFORE_OR_AFTER_USAGE_DATE_RANGE + " - " + issues);
        issues.add(issueForField(field, DetectionType.BEFORE_OR_AFTER_USAGE_DATE_RANGE,
            new MetaFieldInfoData() {

              @Override
              public MetaFieldInfo getMetaFieldInfo(VxuField vxuField) {
                return null;
              }
            }));
      } else if (datr.isOutsideOfRange(usedDateString, notExpectedBeforeDateString,
          notExpectedAfterDateString)) {
        LOGGER.info("Adding issue for: " + field + " - "
            + DetectionType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE + " - " + issues);
        issues.add(issueForField(field, DetectionType.BEFORE_OR_AFTER_LICENSED_DATE_RANGE, meta));
      } else {
        LOGGER.info("NO useDate issues for: " + field);
      }
    }
    return issues;
  }

  public List<ValidationReport> handleAgeDate(Code codedValue, Date birthDate, Date adminDate,
      VxuField field, MetaFieldInfoData meta) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();

    if (codedValue == null || birthDate == null || adminDate == null) {
      LOGGER.debug("One of the inputs is null.  returning no issues: code[" + codedValue
          + "] startDate[" + birthDate + "] endDate[" + adminDate + "]");
      return issues;
    }

    UseAge useAge = codedValue.getUseAge();

    if (useAge != null) {
      int ageInMonths = datr.monthsBetween(birthDate, adminDate);
      // LOGGER.info("age in months: " + ageInMonths);
      int notBeforeMonthByte = useAge.getNotBeforeMonth();
      int notNotAfterMonthByte = useAge.getNotAfterMonth();
      // LOGGER.info("notBeforeMonthByte["+notBeforeMonthByte+"] notNotAfterMonthByte["+notNotAfterMonthByte+"]");

      if (ageInMonths > notNotAfterMonthByte || ageInMonths < notBeforeMonthByte) {
        // LOGGER.info("Adding issue for: " + field + " - " +
        // IssueType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE + " - " + issues);
        ValidationReport vi =
            issueForField(field, DetectionType.BEFORE_OR_AFTER_EXPECTED_DATE_FOR_AGE, meta);
        LOGGER.info("validation issue: " + vi);
        issues.add(vi);
      }
    }
    return issues;
  }

  protected ValidationReport issueForField(VxuField field, DetectionType type,
      MetaFieldInfoData meta) {
    return issueForField(field, type, null, meta);
  }

  protected ValidationReport issueForField(VxuField field, DetectionType type,
      String receivedValue, MetaFieldInfoData meta) {

    Detection issue = Detection.get(field, type);

    if (issue != null) {
      return issue.build(receivedValue, meta);
    } else {
      LOGGER.warn("Checking for a condition that has no corresponding PotentialIssue. Field: "
          + field + " IssueType: " + DetectionType.BEFORE_OR_AFTER_VALID_DATE_FOR_AGE);
      return Detection.GeneralProcessingException.build(meta);
    }
  }
}
