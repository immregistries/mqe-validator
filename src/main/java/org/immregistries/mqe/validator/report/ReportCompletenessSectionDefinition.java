package org.immregistries.mqe.validator.report;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.vxu.VxuObject;

public class ReportCompletenessSectionDefinition {

  private String label;
  private List<MqeReportFieldDefinition> scores = new ArrayList<>();
  private VxuObject sectionObject;// (patient, vaccine... this is the "Denominator") quality
                                      // issues,

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<MqeReportFieldDefinition> getReportFields() {
    return scores;
  }

  public void setReportFields(List<MqeReportFieldDefinition> scores) {
    this.scores = scores;
  }

  public VxuObject getSectionObject() {
    return sectionObject;
  }

  public void setSectionObject(VxuObject sectionObject) {
    this.sectionObject = sectionObject;
  }
}
