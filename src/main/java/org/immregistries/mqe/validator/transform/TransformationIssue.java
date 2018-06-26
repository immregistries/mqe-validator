package org.immregistries.mqe.validator.transform;

import java.util.List;
import org.immregistries.mqe.hl7util.Reportable;
import org.immregistries.mqe.hl7util.ReportableSource;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.hl7util.model.CodedWithExceptions;
import org.immregistries.mqe.hl7util.model.Hl7Location;

public class TransformationIssue implements Reportable {

  @Override
  public SeverityLevel getSeverity() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CodedWithExceptions getHl7ErrorCode() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Hl7Location> getHl7LocationList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getReportedMessage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getDiagnosticMessage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CodedWithExceptions getApplicationErrorCode() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ReportableSource getSource() {
    return ReportableSource.MQE;
  }

}
