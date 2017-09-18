package org.immregistries.dqa.validator.issue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.ErrorLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationIssue implements Reportable
{
  
private static final Logger logger = LoggerFactory
		.getLogger(ValidationIssue.class);

  private Detection messageAttribute = null;//should this be a String?
  private int positionId = 0;//This says where in the ACK to put it. 
  private SeverityLevel severityLevel = null; //this is how bad it is. 
  private String valueReceived = null;//This is the related value. 
  
  public Detection getIssue()
  {
    return messageAttribute;
  }
  
  public String getMessage() {
	  return messageAttribute != null ? messageAttribute.getDisplayText() : "";
  }
  
  public void setMessageAttribute(Detection issue)
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
  public String getValueReceived()
  {
    return valueReceived;
  }
  public void setValueReceived(String valueReceived)
  {
    this.valueReceived = valueReceived;
  }
  public String getHl7Reference() {
	  return String.valueOf(this.messageAttribute.getHl7Locations());
  }

  public boolean isError()
  {
	  return SeverityLevel.ERROR.equals(this.severityLevel);
  }

@Override
public String toString() {
	return "IssueFound [issue=" + messageAttribute + ", positionId=" + positionId
			+ ", severity=" + getSeverity() + ", codeReceived=" + valueReceived
			+ "]";
}

@Override
public SeverityLevel getSeverity() {
	if (this.severityLevel == null) {
		return this.messageAttribute.getSeverity();
	}
	return this.severityLevel;
}

@Override
public CodedWithExceptions getHl7ErrorCode() {
	CodedWithExceptions cwe = new CodedWithExceptions();
	cwe.setIdentifier(this.messageAttribute.getHl7ErrorCode());
	return cwe;
}

@Override
public List<ErrorLocation> getHl7LocationList() {
	List<ErrorLocation> list = new ArrayList<ErrorLocation>();
	for (String loc : this.messageAttribute.getHl7Locations()) {
		logger.info("Adding : " + loc);
		ErrorLocation el = ErrorLocation.getErrorLocationFromString(loc);
		list.add(el);
	}
	return list;
}

@Override
public String getReportedMessage() {
	return this.messageAttribute.getDisplayText();
}

@Override
public String getDiagnosticMessage() {
	if (!StringUtils.isBlank(this.valueReceived)) {
		return "Value Received: [" + this.valueReceived + "]";
	}
	return null;
}

@Override
public CodedWithExceptions getApplicationErrorCode() {
	CodedWithExceptions cwe = new CodedWithExceptions();
	if (!this.messageAttribute.getApplicationErrorCode().equals("")){
	  cwe.setIdentifier(this.messageAttribute.getApplicationErrorCode().getId());
      cwe.setText(this.messageAttribute.getApplicationErrorCode().getText());
      cwe.setNameOfCodingSystem("HL70533");
      cwe.setAlternateIdentifier(this.messageAttribute.getDqaErrorCode());
      cwe.setAlternateText(this.messageAttribute.getDisplayText());
      cwe.setNameOfAlternateCodingSystem("L");
	}
	return cwe;
}

}
