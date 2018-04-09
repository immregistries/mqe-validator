package org.immregistries.dqa.validator.report;

import org.immregistries.dqa.validator.detection.DetectionType;

public class ReportIssue {

  private String label;
  private DetectionType type;
  private int upperPercentBoundary = 100;
  private int lowerPercentBoundary = 0;
  private int multiplierPercent;// This is the idea of weight...

  // POJO
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public DetectionType getType() {
    return type;
  }

  public void setType(DetectionType type) {
    this.type = type;
  }

  public int getUpperPercentBoundary() {
    return upperPercentBoundary;
  }

  public void setUpperPercentBoundary(int upperPercentBoundary) {
    this.upperPercentBoundary = upperPercentBoundary;
  }

  public int getLowerPercentBoundary() {
    return lowerPercentBoundary;
  }

  public void setLowerPercentBoundary(int lowerPercentBoundary) {
    this.lowerPercentBoundary = lowerPercentBoundary;
  }

  public int getMultiplierPercent() {
    return multiplierPercent;
  }

  public void setMultiplierPercent(int multiplierPercent) {
    this.multiplierPercent = multiplierPercent;
  }

}
