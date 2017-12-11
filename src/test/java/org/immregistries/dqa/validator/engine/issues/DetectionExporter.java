package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.ReportableSource;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.builder.HL7Util;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.ErrorLocation;
import org.immregistries.dqa.validator.issue.Detection;

public class DetectionExporter {
  public static void main(String[] args) {
    for (final Detection ma : Detection.values()) {
      StringBuilder errSegment = new StringBuilder();
      Reportable reportable = new Reportable() {
        @Override
        public SeverityLevel getSeverity() {
          return SeverityLevel.INFO;
        }

        @Override
        public String getReportedMessage() {
          return ma.getDisplayText();
        }

        @Override
        public List<ErrorLocation> getHl7LocationList() {
          List<ErrorLocation> errorLocationList = new ArrayList<>();
          for (String loc : ma.getHl7Locations()) {
            ErrorLocation el = new ErrorLocation(loc);
            errorLocationList.add(el);
          }
          return errorLocationList;
        }

        @Override
        public CodedWithExceptions getHl7ErrorCode() {
          CodedWithExceptions cwe = new CodedWithExceptions();
          cwe.setIdentifier(ma.getHl7ErrorCode());
          return cwe;
        }

        @Override
        public String getDiagnosticMessage() {
          return null;
        }

        @Override
        public CodedWithExceptions getApplicationErrorCode() {
          CodedWithExceptions cwe = new CodedWithExceptions();
          cwe.setIdentifier(ma.getApplicationErrorCode().getId());
          cwe.setText(ma.getApplicationErrorCode().getText());
          cwe.setNameOfCodingSystem("HL70533");
          cwe.setAlternateIdentifier(ma.getDqaErrorCode());
          cwe.setAlternateText(ma.getDisplayText());
          cwe.setNameOfAlternateCodingSystem("L");
          return cwe;
        }

        @Override
        public ReportableSource getSource() {
          return ReportableSource.DQA;
        }
      };
      HL7Util.makeERRSegment(errSegment, reportable, true);
      System.out.print(errSegment);
    }
  }
}
