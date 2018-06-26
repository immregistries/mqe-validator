package org.immregistries.mqe.validator.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.immregistries.mqe.vxu.VxuObject;

public class VxuCompletenessSectionScore {

  private String label;
  private List<VxuFieldScore> scores = new ArrayList<>();
  private int objectCount;// like patient count or vaccine count.
  private VxuObject sectionObject;
  private Map<Requirement, MqeScore> requirementScores = new HashMap<>();
  /**
   * this represents the score calculated for this whole group.
   */
  private MqeScore sectionScore = new MqeScore();


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

  public VxuObject getSectionObject() {
    return sectionObject;
  }

  public void setSectionObject(VxuObject sectionObject) {
    this.sectionObject = sectionObject;
  }

  public Map<Requirement, MqeScore> getRequirementScores() {
    return requirementScores;
  }

  public void setRequirementScores(Map<Requirement, MqeScore> requirementScores) {
    this.requirementScores = requirementScores;
  }

  public void appendScoresToSectionScore(MqeScore score) {
    int potential = sectionScore.getPotential();
    int scored = sectionScore.getScored();
    potential += score.getPotential();
    scored += score.getScored();

    sectionScore.setPotential(potential);
    sectionScore.setScored(scored);
  }

  public MqeScore getSectionScore() {
    return sectionScore;
  }

  public void setSectionScore(MqeScore sectionScore) {
    this.sectionScore = sectionScore;
  }

}
