package org.immregistries.dqa.validator.report;

import org.immregistries.dqa.validator.detection.MessageObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VxuCompletenessSectionScore {
	private String label;
	private List<VxuFieldScore>  scores = new ArrayList<>();
	private int objectCount;//like patient count or vaccine count.
	private MessageObject sectionObject;
	private Map<Requirement, DqaScore> requirementScores = new HashMap<>();
	/**
	 * this represents the score calculated for this whole group. 
	 */
	private DqaScore sectionScore = new DqaScore();
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<VxuFieldScore> getScores() {
		return scores;
	}

	public void setScores(List<VxuFieldScore> scores) {
		this.scores = scores;
	}

	public void setObjectCount(Integer count) {
		this.objectCount = count;
	}
	
	public Integer getObjectCount() {
		return this.objectCount;
	}

	public MessageObject getSectionObject() {
		return sectionObject;
	}

	public void setSectionObject(MessageObject sectionObject) {
		this.sectionObject = sectionObject;
	}

	public Map<Requirement, DqaScore> getRequirementScores() {
		return requirementScores;
	}

	public void setRequirementScores(Map<Requirement, DqaScore> requirementScores) {
		this.requirementScores = requirementScores;
	}
	
	public void appendScoresToSectionScore(DqaScore score) {
		int potential = sectionScore.getPotential();
		int scored = sectionScore.getScored();
		potential += score.getPotential();
		scored += score.getScored();
		
		sectionScore.setPotential(potential);
		sectionScore.setScored(scored);
	}

	public DqaScore getSectionScore() {
		return sectionScore;
	}

	public void setSectionScore(DqaScore sectionScore) {
		this.sectionScore = sectionScore;
	}

}
