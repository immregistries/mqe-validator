package org.immregistries.dqa.validator.report;

import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.ReportableSource;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.Hl7Location;
import org.immregistries.dqa.validator.detection.Detection;

import java.util.ArrayList;

/**
 * The intention is to generalize
 * the interface for the various types of things we report.
 * @author Josh Hull
 */
public class ScoreReportable implements Reportable {

	private final SeverityLevel severity;
	private final String reportedMessage;
	private final ArrayList<Hl7Location> hl7LocationList;
	private final CodedWithExceptions hl7ErrorCode;
	private final CodedWithExceptions applicationErrorCode;
	private int reportedCount;

  public ScoreReportable(Detection d, int count) {
  	this.reportedCount = count;
  	this.severity = d.getSeverity();
  	this.reportedMessage = d.getDisplayText();
  	this.hl7LocationList = new ArrayList<Hl7Location>();
	  for (String loc : d.getHl7Locations()) {
		  Hl7Location el = new Hl7Location(loc);
		  hl7LocationList.add(el);
	  }
	  CodedWithExceptions cwe = new CodedWithExceptions();
	  cwe.setIdentifier(d.getHl7ErrorCode());
	  this.hl7ErrorCode = cwe;

	  CodedWithExceptions aec = new CodedWithExceptions();
	  aec.setIdentifier(d.getApplicationErrorCode().getId());
	  aec.setText(d.getApplicationErrorCode().getText());
	  aec.setNameOfCodingSystem("HL70533");
	  aec.setAlternateIdentifier(d.getDqaErrorCode());
	  aec.setAlternateText(d.getDisplayText());
	  aec.setNameOfAlternateCodingSystem("L");
	  this.applicationErrorCode = aec;
  }

	@Override public SeverityLevel getSeverity() {
		return severity;
	}

	@Override public String getReportedMessage() {
		return reportedMessage;
	}

	@Override public String getDiagnosticMessage() {
		return "";
	}

	@Override public ArrayList<Hl7Location> getHl7LocationList() {
		return hl7LocationList;
	}

	@Override public CodedWithExceptions getHl7ErrorCode() {
		return hl7ErrorCode;
	}

	@Override public CodedWithExceptions getApplicationErrorCode() {
		return applicationErrorCode;
	}

	@Override
	public ReportableSource getSource() {
		return ReportableSource.DQA;
	}

	public int getCount() {
		return reportedCount;
	}
}
