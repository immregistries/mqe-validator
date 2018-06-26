package org.immregistries.mqe.validator.report;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.vxu.VxuField;

public class MqeReportFieldDefinition {

  private VxuField field;
  private String scoreLabel;
  private Requirement requirement;
  private Integer weight;// The points this field gets assigned on the report.
  private List<ReportIssue> issues = new ArrayList<>();
  private boolean checkForPresent = true;

  public VxuField getField() {
    return field;
  }

  public void setField(VxuField field) {
    this.field = field;
  }

  public String getLabel() {
    return scoreLabel;
  }

  public void setLabel(String scoreLabel) {
    this.scoreLabel = scoreLabel;
  }

  public List<ReportIssue> getIssues() {
    return issues;
  }

  public void setIssues(List<ReportIssue> issues) {
    this.issues = issues;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public boolean isCheckForPresent() {
    return checkForPresent;
  }

  public void setCheckForPresent(boolean checkForPresent) {
    this.checkForPresent = checkForPresent;
  }

  public Requirement getRequirement() {
    return requirement;
  }

  public void setRequirement(Requirement requirement) {
    this.requirement = requirement;
  }

  public String getHl7Field() {
    return this.field.getHl7Locator();
  }
}
