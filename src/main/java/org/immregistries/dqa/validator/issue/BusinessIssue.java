package org.immregistries.dqa.validator.issue;

import java.util.List;

import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.builder.AckERRCode;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.ErrorLocation;

public class BusinessIssue implements Reportable {

	private SeverityLevel severity;//expects I/W/E/A according to the IssueLevel.java class.
	private CodedWithExceptions hl7ErrorCode = new CodedWithExceptions();//This is 0 for success, and there's a whole table for errors. General code
	private List<ErrorLocation> hl7LocationList;
	private String reportedMessage; // ERR-8
	private String diagnosticMessage; // ERR-7
	private CodedWithExceptions applicationErrorCode; // Detailed code of the issue, CDC code & DQA Code


	@Override public SeverityLevel getSeverity() {
		return severity;
	}

	public void setSeverity(SeverityLevel severity) {
		this.severity = severity;
	}

	@Override public CodedWithExceptions getHl7ErrorCode() {
		return hl7ErrorCode;
	}

	public void setHl7ErrorCode(CodedWithExceptions hl7ErrorCode) {
		this.hl7ErrorCode = hl7ErrorCode;
	}

	public CodedWithExceptions makeCweFrom(AckERRCode ackCode) {
		CodedWithExceptions cwe = new CodedWithExceptions();
		cwe.setIdentifier(ackCode.identifier);
		cwe.setText(ackCode.text);
		cwe.setNameOfCodingSystem(AckERRCode.TABLE);
		return cwe;
	}

	public void setHl7ErrorCodeFrom(AckERRCode ackCode) {
		if (ackCode != null) {
			this.hl7ErrorCode = this.makeCweFrom(ackCode);
		}
	}

	@Override public List<ErrorLocation> getHl7LocationList() {
		return hl7LocationList;
	}

	public void setHl7LocationList(List<ErrorLocation> hl7LocationList) {
		this.hl7LocationList = hl7LocationList;
	}

	@Override public String getReportedMessage() {
		return reportedMessage;
	}

	public void setReportedMessage(String reportedMessage) {
		this.reportedMessage = reportedMessage;
	}

	@Override public String getDiagnosticMessage() {
		return diagnosticMessage;
	}

	public void setDiagnosticMessage(String diagnosticMessage) {
		this.diagnosticMessage = diagnosticMessage;
	}

	@Override public CodedWithExceptions getApplicationErrorCode() {
		return applicationErrorCode;
	}

	public void setApplicationErrorCode(CodedWithExceptions applicationErrorCode) {
		this.applicationErrorCode = applicationErrorCode;
	}

}
