package org.immregistries.mqe.validator.detection;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.hl7util.Reportable;
import org.immregistries.mqe.hl7util.ReportableSource;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.hl7util.builder.AckERRCode;
import org.immregistries.mqe.hl7util.model.CodedWithExceptions;
import org.immregistries.mqe.hl7util.model.Hl7Location;

public class ProcessReport implements Reportable {

  private SeverityLevel severity;// expects I/W/E/A according to the IssueLevel.java class.
  private CodedWithExceptions hl7ErrorCode = new CodedWithExceptions();// This is 0 for success, and
  private List<Hl7Location> hl7LocationList = new ArrayList<>();
  private String reportedMessage; // ERR-8
  private String diagnosticMessage; // ERR-7
  private CodedWithExceptions applicationErrorCode; // Detailed code of the issue, CDC code & MQE

  @Override
  public SeverityLevel getSeverity() {
    return severity;
  }

  public void setSeverity(SeverityLevel severity) {
    this.severity = severity;
  }

  @Override
  public CodedWithExceptions getHl7ErrorCode() {
    return hl7ErrorCode;
  }

  public void setHl7ErrorCode(CodedWithExceptions hl7ErrorCode) {
    this.hl7ErrorCode = hl7ErrorCode;
  }

  public void setHl7ErrorCodeFrom(AckERRCode ackCode) {
    if (ackCode != null) {
      this.hl7ErrorCode = ackCode.getCWE();
    }
  }

  @Override
  public List<Hl7Location> getHl7LocationList() {
    return hl7LocationList;
  }

  public void setHl7LocationList(List<Hl7Location> hl7LocationList) {
    this.hl7LocationList = hl7LocationList;
  }

  @Override
  public String getReportedMessage() {
    return reportedMessage;
  }

  public void setReportedMessage(String reportedMessage) {
    this.reportedMessage = reportedMessage;
  }

  @Override
  public String getDiagnosticMessage() {
    return diagnosticMessage;
  }

  public void setDiagnosticMessage(String diagnosticMessage) {
    this.diagnosticMessage = diagnosticMessage;
  }

  @Override
  public CodedWithExceptions getApplicationErrorCode() {
    return applicationErrorCode;
  }

  public void setApplicationErrorCode(CodedWithExceptions applicationErrorCode) {
    this.applicationErrorCode = applicationErrorCode;
  }

  @Override
  public ReportableSource getSource() {
    return ReportableSource.MQE;
  }

}
