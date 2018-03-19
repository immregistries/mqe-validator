package org.immregistries.dqa.validator.transform;

import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.ReportableSource;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.Hl7Location;

import java.util.List;

public class TransformationIssue implements Reportable{

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
    return ReportableSource.DQA;
  }

}
