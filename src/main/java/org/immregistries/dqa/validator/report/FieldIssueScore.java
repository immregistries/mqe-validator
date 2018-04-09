package org.immregistries.dqa.validator.report;

import org.immregistries.dqa.validator.detection.DetectionType;

public class FieldIssueScore {

  private int issueCount = 0;
  private double issuePercentDemerit;
  private int issueDemerit = 0;
  private DetectionType detectionType;


  public int getIssueCount() {
    return issueCount;
  }

  public void setIssueCount(int issueCount) {
    this.issueCount = issueCount;
  }

  public int getIssueDemerit() {
    return issueDemerit;
  }

  public void setIssueDemerit(int issueDemerit) {
    this.issueDemerit = issueDemerit;
  }

  public double getIssuePercentDemerit() {
    return issuePercentDemerit;
  }

  public void setIssuePercentDemerit(double issuePercentDemerit) {
    this.issuePercentDemerit = issuePercentDemerit;
  }

  public DetectionType getDetectionType() {
    return detectionType;
  }

  public void setDetectionType(DetectionType detectionType) {
    this.detectionType = detectionType;
  }

}
