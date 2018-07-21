package org.immregistries.mqe.validator.report;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents the core unit for the MQE VXU report.
 */
public class VxuFieldScore {

  private MqeReportFieldDefinition reportFieldDefinition;

  /**
   * This will contain the count of all the issues counted for this field.
   */
  private List<FieldIssueScore> issueScores = new ArrayList<>();
  /**
   * This represnts the score for the field.
   */
  private MqeScore fieldScore = new MqeScore();
  /**
   * this is the total count of fields expected.
   */
  private int expectedCount = 0;
  private int presentCount = 0;

  public MqeScore getFieldScore() {
    return fieldScore;
  }

  public void setFieldScore(MqeScore fieldScore) {
    this.fieldScore = fieldScore;
  }

  public Integer getExpectedCount() {
    return expectedCount;
  }

  public void setExpectedCount(Integer count) {
    this.expectedCount = count;
  }

  public MqeReportFieldDefinition getReportFieldDefinition() {
    return reportFieldDefinition;
  }

  public void setReportFieldDefinition(MqeReportFieldDefinition reportFieldDefinition) {
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
