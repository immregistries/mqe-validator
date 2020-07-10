package org.immregistries.mqe.validator.report;

import java.util.ArrayList;
import org.immregistries.mqe.hl7util.Reportable;
import org.immregistries.mqe.hl7util.ReportableSource;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.hl7util.model.CodedWithExceptions;
import org.immregistries.mqe.hl7util.model.Hl7Location;
import org.immregistries.mqe.util.validation.MqeDetection;
import org.immregistries.mqe.validator.detection.Detection;

/**
 * The intention is to generalize the interface for the various types of things we report.
 *
 * @author Josh Hull
 */
public class ScoreReportable implements Reportable {

  private SeverityLevel severity;
  private final String reportedMessage;
  private final ArrayList<Hl7Location> hl7LocationList;
  private final CodedWithExceptions hl7ErrorCode;
  private final CodedWithExceptions applicationErrorCode;
  private final String mqeCode;

  /* Support for extra reporting features */
  private String exampleMessage;
  private int count;

  private String howToFix;
  private String whyToFix;

  public ScoreReportable(String severity, String mqeCode, String exampleMessage, int count) {
    this(Detection.getByMqeErrorCodeString(mqeCode), count);
    this.severity = SeverityLevel.valueOf(severity);
    this.exampleMessage = exampleMessage;
  }

  public ScoreReportable(MqeDetection d, int count) {
    this.count = count;
    this.hl7LocationList = new ArrayList<>();
    CodedWithExceptions cwe = new CodedWithExceptions();
    this.hl7ErrorCode = cwe;
    CodedWithExceptions aec = new CodedWithExceptions();
    this.applicationErrorCode = aec;

    if (d != null) {
      this.severity = d.getSeverity();
      this.reportedMessage = d.getDisplayText();
      this.mqeCode = d.getMqeMqeCode();
      Hl7Location el = new Hl7Location(d.getTargetField().getHl7Locator());
      aec.setIdentifier(d.getApplicationErrorCode().getId());
      aec.setText(d.getApplicationErrorCode().getText());
      aec.setNameOfCodingSystem("HL70533");
      aec.setAlternateIdentifier(d.getMqeMqeCode());
      aec.setAlternateText(d.getDisplayText());
      aec.setNameOfAlternateCodingSystem("L");
      hl7LocationList.add(el);
      cwe.setIdentifier(d.getHl7ErrorCode().getIdentifier());
    } else {
      this.severity = SeverityLevel.INFO;
      this.reportedMessage = "Undetected";
      this.mqeCode = null;
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
    return ReportableSource.MQE;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) { this.count = count; }
  
  public void setSeverity(SeverityLevel severity) {
	  this.severity = severity;
  }

  public String getExampleMessage() {
    return exampleMessage;
  }

  public void setExampleMessage(String exampleMessage) {
    this.exampleMessage = exampleMessage;
  }

  public String getMqeCode() {
	return mqeCode;
}

  public String getHowToFix() {
    return howToFix;
  }

  public void setHowToFix(String howToFix) {
    this.howToFix = howToFix;
  }

  public String getWhyToFix() {
    return whyToFix;
  }

  public void setWhyToFix(String whyToFix) {
    this.whyToFix = whyToFix;
  }
}
