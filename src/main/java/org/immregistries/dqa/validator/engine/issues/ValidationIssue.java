/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.immregistries.dqa.validator.engine.issues;

import org.immregistries.dqa.validator.model.codes.CodeReceived;

public class ValidationIssue implements Reportable
{
  private PotentialIssue issue = null;//should this be a String?
  private int positionId = 0;//This says where in the ACK to put it. 
  private IssueLevel issueLevel = null; //this is how bad it is. 
  private CodeReceived codeReceived = null;//This is the related value. 
  private String hl7Reference;//This is where it is from in the HL7 message. 
  
  public PotentialIssue getIssue()
  {
    return issue;
  }
  public void setIssue(PotentialIssue issue)
  {
    this.issue = issue;
  }
  public int getPositionId()
  {
    return positionId;
  }
  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }
  public IssueLevel getIssueAction()
  {
    return issueLevel;
  }
  public void setIssueAction(IssueLevel issueAction)
  {
    this.issueLevel = issueAction;
  }
  public CodeReceived getCodeReceived()
  {
    return codeReceived;
  }
  public void setCodeReceived(CodeReceived codeReceived)
  {
    this.codeReceived = codeReceived;
  }
  public String getHl7Reference() {
	  return hl7Reference;
  }
  public void setHl7Reference(String hl7Reference) {
	  this.hl7Reference = hl7Reference;
  }
  
  public boolean isError()
  {
	  return IssueLevel.ERROR.equals(this.issueLevel);
  }

@Override
public String toString() {
	return "IssueFound [issue=" + issue + ", positionId=" + positionId
			+ ", issueAction=" + issueLevel + ", codeReceived=" + codeReceived
			+ "]";
}
public IssueLevel getIssueLevel() {
	return this.issueLevel;
}

}
