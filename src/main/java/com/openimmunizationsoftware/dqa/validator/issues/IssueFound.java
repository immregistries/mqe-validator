/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.validator.issues;

import java.io.Serializable;

import com.openimmunizationsoftware.dqa.model.CodeReceived;

public class IssueFound implements Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  public PotentialIssue issue = null;
  public int positionId = 0;
  public IssueAction issueAction = null;
  public CodeReceived codeReceived = null;
  
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
  public IssueAction getIssueAction()
  {
    return issueAction;
  }
  public void setIssueAction(IssueAction issueAction)
  {
    this.issueAction = issueAction;
  }
  public CodeReceived getCodeReceived()
  {
    return codeReceived;
  }
  public void setCodeReceived(CodeReceived codeReceived)
  {
    this.codeReceived = codeReceived;
  }
  
  public boolean isError()
  {
    return this.issueAction != null && this.issueAction.equals(IssueAction.ERROR);
  }

  public boolean isWarn()
  {
    return this.issueAction != null && this.issueAction.equals(IssueAction.WARN);
  }
  
  public boolean isAccept()
  {
    return this.issueAction != null && this.issueAction.equals(IssueAction.ACCEPT);
  }
  
  public boolean isSkip()
  {
    return this.issueAction != null && this.issueAction.equals(IssueAction.SKIP);
  }
  
  public String getDisplayText()
  {
    StringBuilder sb = new StringBuilder(issue.getDisplayText());
    
    if (codeReceived != null)
    {
      sb.append(", " + codeReceived.getTable().getTableLabel() + " = '" + codeReceived.getReceivedValue() +  "'");
      if (codeReceived.getCodeStatus().isDeprecated() && !codeReceived.getCodeValue().equals(codeReceived.getReceivedValue()))
      {
        sb.append(", use '" + codeReceived.getCodeValue() +  "' instead");        
      }
    }
    if (positionId > 1)
    {
      sb.append(", in repeat #" + positionId + "");
    }

    return sb.toString();
  }
@Override
public String toString() {
	return "IssueFound [issue=" + issue + ", positionId=" + positionId
			+ ", issueAction=" + issueAction + ", codeReceived=" + codeReceived
			+ "]";
}

}
