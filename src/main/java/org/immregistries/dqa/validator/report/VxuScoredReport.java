package org.immregistries.dqa.validator.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.vxu.code.CodeReceived;
import org.joda.time.DateTime;

public class VxuScoredReport {
	/**
	 * This represents the score for the whole report, all the parts
	 * together. 
	 */
	private DateTime firstMessageReceived;
	private DateTime lastMessageReceived;
	private DqaScore reportScore = new DqaScore();
	
	/*
	 * These two fields are used to score the report. 
	 * and I need to decide if I calculate it up front (and populate the score)
	 * OR calculate it at the end. 
	 * 
	 * I'm leaning towards up front. There ought to be a calculator. 
	 * 
	 */
	//Patient and NK1 count once per message. 
	private int messageCount;
	
	//Need other identifying information. 
	private String reportlabel;
	
	
	//this represents the missing/quality issues. 
	private List<VxuCompletenessSectionScore> scoreGroups = new ArrayList<>();
	
	//This is the codes that were received...
	private Map<CodeReceived, Integer> codeCount = new HashMap<>();
	
	//This is the issues, and how many came through. 
	private Map<Detection, Integer> issueCounts = new HashMap<>();
	
	//this is the timeliness categories, and how many messages are represented. 
	private Map<TimelinessCategory, Integer> timelinessCount = new HashMap<>();
	
	
	private Map<VaccineGroup, Integer> vaccineGroupCounts = new HashMap<>();


	public DateTime getFirstMessageReceived() {
		return firstMessageReceived;
	}


	public void setFirstMessageReceived(DateTime firstMessageReceived) {
		this.firstMessageReceived = firstMessageReceived;
	}


	public DateTime getLastMessageReceived() {
		return lastMessageReceived;
	}


	public void setLastMessageReceived(DateTime lastMessageReceived) {
		this.lastMessageReceived = lastMessageReceived;
	}


	public int getMessageCount() {
		return messageCount;
	}


	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}


	public String getReportlabel() {
		return reportlabel;
	}


	public void setReportlabel(String reportlabel) {
		this.reportlabel = reportlabel;
	}


	public List<VxuCompletenessSectionScore> getScoreGroups() {
		return scoreGroups;
	}


	public void setScoreGroups(List<VxuCompletenessSectionScore> scoreGroups) {
		this.scoreGroups = scoreGroups;
	}


	public Map<CodeReceived, Integer> getCodeCount() {
		return codeCount;
	}


	public void setCodeCount(Map<CodeReceived, Integer> codeCount) {
		this.codeCount = codeCount;
	}


	public Map<Detection, Integer> getIssueCounts() {
		return issueCounts;
	}


	public void setIssueCounts(Map<Detection, Integer> issueCounts) {
		this.issueCounts = issueCounts;
	}


	public Map<TimelinessCategory, Integer> getTimelinessCount() {
		return timelinessCount;
	}


	public void setTimelinessCount(Map<TimelinessCategory, Integer> timelinessCount) {
		this.timelinessCount = timelinessCount;
	}


	public Map<VaccineGroup, Integer> getVaccineGroupCounts() {
		return vaccineGroupCounts;
	}


	public void setVaccineGroupCounts(Map<VaccineGroup, Integer> vaccineGroupCounts) {
		this.vaccineGroupCounts = vaccineGroupCounts;
	}

	public void appendScoreToReportScore(DqaScore score) {
		int potential = reportScore.getPotential();
		int scored = reportScore.getScored();
		potential += score.getPotential();
		scored += score.getScored();
		
		reportScore.setPotential(potential);
		reportScore.setScored(scored);
	}

	public DqaScore getReportScore() {
		return reportScore;
	}

	public void setReportScore(DqaScore score) {
		this.reportScore = score;
	}

}
