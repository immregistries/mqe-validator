package org.immregistries.dqa.validator.report;

import java.util.ArrayList;
import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.ReportableSource;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.Hl7Location;
import org.immregistries.dqa.validator.detection.Detection;

/**
 * The intention is to generalize the interface for the various types of things we report.
 *
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
    this.hl7LocationList = new ArrayList<>();
    CodedWithExceptions cwe = new CodedWithExceptions();
    this.hl7ErrorCode = cwe;
    CodedWithExceptions aec = new CodedWithExceptions();
    this.applicationErrorCode = aec;

    if (d != null) {
      this.severity = d.getSeverity();
      this.reportedMessage = d.getDisplayText();
      Hl7Location el = new Hl7Location(d.getTargetField().getHl7Locator());
      aec.setIdentifier(d.getApplicationErrorCode().getId());
      aec.setText(d.getApplicationErrorCode().getText());
      aec.setNameOfCodingSystem("HL70533");
      aec.setAlternateIdentifier(d.getDqaMqeCode());
      aec.setAlternateText(d.getDisplayText());
      aec.setNameOfAlternateCodingSystem("L");
      hl7LocationList.add(el);
      cwe.setIdentifier(d.getHl7ErrorCode().getIdentifier());
    } else {
      this.severity = SeverityLevel.INFO;
      this.reportedMessage = "Undetected";
    }
  }

  @Override
  public SeverityLevel getSeverity() {
    return severity;
  }

  @Override
  public String getReportedMessage() {
    return reportedMessage;
  }

  @Override
  public String getDiagnosticMessage() {
    return "";
  }

  @Override
  public ArrayList<Hl7Location> getHl7LocationList() {
    return hl7LocationList;
  }

  @Override
  public CodedWithExceptions getHl7ErrorCode() {
    return hl7ErrorCode;
  }

  @Override
  public CodedWithExceptions getApplicationErrorCode() {
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
