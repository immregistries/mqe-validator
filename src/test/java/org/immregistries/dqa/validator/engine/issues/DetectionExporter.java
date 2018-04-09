package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.ReportableSource;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.builder.HL7Util;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.Hl7Location;
import org.immregistries.dqa.validator.detection.Detection;

public class DetectionExporter {
  public static void main(String[] args) {
    for (final Detection detection : Detection.values()) {
      Reportable reportable = new ExporterReportable(detection);
      String errseg = HL7Util.makeERRSegment(reportable, true);
      System.out.print(errseg);
    }
  }

  private static class ExporterReportable implements Reportable {
    Detection detection;
    ExporterReportable(Detection d) {
      this.detection = d;
    }
    @Override
    public SeverityLevel getSeverity() {
      return SeverityLevel.INFO;
    }

    @Override
    public String getReportedMessage() {
      return detection.getDisplayText();
    }

    @Override
    public List<Hl7Location> getHl7LocationList() {
      List<Hl7Location> hl7LocationList = new ArrayList<>();
      Hl7Location el = new Hl7Location(detection.getTargetField().getHl7Locator());
      hl7LocationList.add(el);
      return hl7LocationList;
    }

    @Override
    public CodedWithExceptions getHl7ErrorCode() {
      CodedWithExceptions cwe = new CodedWithExceptions();
      cwe.setIdentifier(detection.getHl7ErrorCode().getIdentifier());
      return cwe;
    }

    @Override
    public String getDiagnosticMessage() {
      return null;
    }

    @Override
    public CodedWithExceptions getApplicationErrorCode() {
      CodedWithExceptions cwe = new CodedWithExceptions();
      cwe.setIdentifier(detection.getApplicationErrorCode().getId());
      cwe.setText(detection.getApplicationErrorCode().getText());
      cwe.setNameOfCodingSystem("HL70533");
      cwe.setAlternateIdentifier(detection.getDqaMqeCode());
      cwe.setAlternateText(detection.getDisplayText());
      cwe.setNameOfAlternateCodingSystem("L");
      return cwe;
    }

    @Override
    public ReportableSource getSource() {
      return ReportableSource.DQA;
    }
  };
}
