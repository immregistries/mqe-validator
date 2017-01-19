package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.hl7model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.hl7model.ErrorLocation;

public class TransformationIssue implements Reportable{

  private SeverityLevel severity = null;
  private CodedWithExceptions hl7ErrorCode = null;
  private List<ErrorLocation> hl7LocationList = new ArrayList<>();
  private String reportedMessage = "";
  private String diagnosticMessage = "";
  private CodedWithExceptions applicationErrorCode = null;

  public void setSeverity(SeverityLevel severity)
  {
    this.severity = severity;
  }

  public void setHl7ErrorCode(CodedWithExceptions hl7ErrorCode)
  {
    this.hl7ErrorCode = hl7ErrorCode;
  }

  public void setReportedMessage(String reportedMessage)
  {
    this.reportedMessage = reportedMessage;
  }

  public void setDiagnosticMessage(String diagnosticMessage)
  {
    this.diagnosticMessage = diagnosticMessage;
  }

  public void setApplicationErrorCode(CodedWithExceptions applicationErrorCode)
  {
    this.applicationErrorCode = applicationErrorCode;
  }

  @Override
  public String getDiagnosticMessage()
  {
    return diagnosticMessage;
  }

  @Override
  public CodedWithExceptions getApplicationErrorCode()
  {
    return applicationErrorCode;
  }

  @Override
  public SeverityLevel getSeverity()
  {
    return severity;
  }

  @Override
  public CodedWithExceptions getHl7ErrorCode()
  {
    return hl7ErrorCode;
  }

  @Override
  public List<ErrorLocation> getHl7LocationList()
  {
    return hl7LocationList;
  }

  @Override
  public String getReportedMessage()
  {
    return reportedMessage;
  }

}
