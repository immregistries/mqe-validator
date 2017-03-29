package org.immregistries.dqa.validator.report;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.issue.IssueObject;

public class ReportCompletenessSectionDefinition {
	private String label;
	private List<DqaReportFieldDefinition> scores = new ArrayList<>();
	private IssueObject sectionObject;//(patient, vaccine...  this is the "Denominator") quality issues,
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<DqaReportFieldDefinition> getReportFields() {
		return scores;
	}
	public void setReportFields(List<DqaReportFieldDefinition> scores) {
		this.scores = scores;
	}
	public IssueObject getSectionObject() {
		return sectionObject;
	}
	public void setSectionObject(IssueObject sectionObject) {
		this.sectionObject = sectionObject;
	}
}
