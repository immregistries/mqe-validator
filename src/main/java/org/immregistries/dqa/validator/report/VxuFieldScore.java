package org.immregistries.dqa.validator.report;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents the core unit for the DQA VXU report.
 */
public class VxuFieldScore {

  private DqaReportFieldDefinition reportFieldDefinition;

  /**
   * This will contain the count of all the issues counted for this field.
   */
  private List<FieldIssueScore> issueScores = new ArrayList<>();
  /**
   * This represnts the score for the field.
   */
  private DqaScore fieldScore = new DqaScore();
  /**
   * this is the total count of fields expected.
   */
  private int expectedCount = 0;
  private int presentCount = 0;

  public DqaScore getFieldScore() {
    return fieldScore;
  }

  public void setFieldScore(DqaScore fieldScore) {
    this.fieldScore = fieldScore;
  }

  public Integer getExpectedCount() {
    return expectedCount;
  }

  public void setExpectedCount(Integer count) {
    this.expectedCount = count;
  }

  public DqaReportFieldDefinition getReportFieldDefinition() {
    return reportFieldDefinition;
  }

  public void setReportFieldDefinition(DqaReportFieldDefinition reportFieldDefinition) {
    this.reportFieldDefinition = reportFieldDefinition;
  }

  public int getPresentCount() {
    return presentCount;
  }

  public void setPresentCount(int presentCount) {
    this.presentCount = presentCount;
  }

  public List<FieldIssueScore> getIssueScores() {
    return issueScores;
  }

  public void setIssueScores(List<FieldIssueScore> issueScores) {
    this.issueScores = issueScores;
  }

}
