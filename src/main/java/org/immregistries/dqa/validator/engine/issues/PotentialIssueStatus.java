package org.immregistries.dqa.validator.engine.issues;

import java.io.Serializable;

import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.validator.model.SubmitterProfile;

public class PotentialIssueStatus implements Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  private int potentialIssueStatusId = 0;
  private MessageAttribute issue = null;
  private SubmitterProfile profile = null;
  private SeverityLevel action = null;
  private int expectMin = 0;
  private int expectMax = 100;
  
  public PotentialIssueStatus()
  {
    // default
  }
  
  public PotentialIssueStatus(MessageAttribute potentialIssue, SubmitterProfile profile)
  {
    issue = potentialIssue;
    this.profile = profile;
    action = issue.getDefaultAction();
    expectMin = 0;
    expectMax = 100;
  }
  
  public PotentialIssueStatus(PotentialIssueStatus templatePis, SubmitterProfile profile)
  {
    this.issue = templatePis.issue;
    this.profile = profile;
    action = templatePis.action;
    expectMin = templatePis.expectMin;
    expectMax = templatePis.expectMax;
  }

  public int getPotentialIssueStatusId()
  {
    return potentialIssueStatusId;
  }

  public void setPotentialIssueStatusId(int potentialIssueStatusId)
  {
    this.potentialIssueStatusId = potentialIssueStatusId;
  }

  public MessageAttribute getIssue()
  {
    return issue;
  }

  public void setIssue(MessageAttribute issue)
  {
    this.issue = issue;
  }

  public SubmitterProfile getProfile()
  {
    return profile;
  }

  public void setProfile(SubmitterProfile profile)
  {
    this.profile = profile;
  }

  public SeverityLevel getAction()
  {
    return action;
  }

  public void setAction(SeverityLevel action)
  {
    this.action = action;
  }

  public int getExpectMin()
  {
    return expectMin;
  }

  public void setExpectMin(int expectMin)
  {
    this.expectMin = expectMin;
  }

  public int getExpectMax()
  {
    return expectMax;
  }

  public void setExpectMax(int expectMax)
  {
    this.expectMax = expectMax;
  }

}
