package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.hl7model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.hl7model.ErrorLocation;

public class ValidationIssue implements Reportable
{
  private MessageAttribute messageAttribute = null;//should this be a String?
  private int positionId = 0;//This says where in the ACK to put it. 
  private SeverityLevel severityLevel = null; //this is how bad it is. 
  private String valueReceived = null;//This is the related value. 
  private String hl7Reference;//This is where it is from in the HL7 message. 
  
  public MessageAttribute getIssue()
  {
    return messageAttribute;
  }
  
  public String getMessage() {
	  return messageAttribute != null ? messageAttribute.getDisplayText() : "";
  }
  
  public void setMessageAttribute(MessageAttribute issue)
  {
    this.messageAttribute = issue;
  }
  public int getPositionId()
  {
    return positionId;
  }
  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }
  
  public void setSeverityLevel(SeverityLevel level)
  {
    this.severityLevel = level;
  }
  public String getCodeReceived()
  {
    return valueReceived;
  }
  public void setCodeReceived(String codeReceived)
  {
    this.valueReceived = codeReceived;
  }
  public String getHl7Reference() {
	  return hl7Reference;
  }
  public void setHl7Reference(String hl7Reference) {
	  this.hl7Reference = hl7Reference;
  }
  
  public boolean isError()
  {
	  return SeverityLevel.ERROR.equals(this.severityLevel);
  }

@Override
public String toString() {
	return "IssueFound [issue=" + messageAttribute + ", positionId=" + positionId
			+ ", issueAction=" + severityLevel + ", codeReceived=" + valueReceived
			+ "]";
}
public SeverityLevel getSeverityLevel() {
	return this.severityLevel;
}

@Override
public String getDiagnosticMessage()
{
  // TODO: Not implemented yet
  return null;
}

@Override
public CodedWithExceptions getApplicationErrorCode()
{
  // TODO: Not implemented yet
  return null;
}

@Override
public SeverityLevel getSeverity()
{
  // TODO: Not implemented yet
  return null;
}

@Override
public CodedWithExceptions getHl7ErrorCode()
{
  // TODO: Not implemented yet
  return null;
}

@Override
public List<ErrorLocation> getHl7LocationList()
{
  // TODO: Not implemented yet
  return null;
}

@Override
public String getReportedMessage()
{
  // TODO: Not implemented yet
  return null;
}


}
