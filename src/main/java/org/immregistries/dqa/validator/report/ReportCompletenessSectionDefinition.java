package org.immregistries.dqa.validator.report;

import org.immregistries.dqa.validator.detection.MessageObject;

import java.util.ArrayList;
import java.util.List;

public class ReportCompletenessSectionDefinition {
	private String label;
	private List<DqaReportFieldDefinition> scores = new ArrayList<>();
	private MessageObject sectionObject;//(patient, vaccine...  this is the "Denominator") quality issues,
	
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
	public MessageObject getSectionObject() {
		return sectionObject;
	}
	public void setSectionObject(MessageObject sectionObject) {
		this.sectionObject = sectionObject;
	}
}
