package org.immregistries.mqe.validator.report;

import java.util.ArrayList;
import java.util.List;

public class ReportDefinition {

  private String label;
  private List<ReportCompletenessSectionDefinition> qualitySections = new ArrayList<>();

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public List<ReportCompletenessSectionDefinition> getQualitySections() {
    return qualitySections;
  }

  public void setQualitySections(List<ReportCompletenessSectionDefinition> qualitySections) {
    this.qualitySections = qualitySections;
  }
}
