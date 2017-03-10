package org.immregistries.dqa.validator.engine.issues;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.hl7util.Reportable;
import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.hl7util.builder.HL7Util;
import org.immregistries.dqa.hl7util.model.CodedWithExceptions;
import org.immregistries.dqa.hl7util.model.ErrorLocation;
import org.immregistries.dqa.validator.issue.MessageAttribute;

public class MessageAttributeExporter {
  public static void main(String[] args) {
    for (final MessageAttribute ma : MessageAttribute.values()) {
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
          cwe.setIdentifier(ma.getDqaErrorCode());
          return cwe;
        }
      };
      HL7Util.makeERRSegment(errSegment, reportable, true);
      System.out.print(errSegment);
    }
  }
}
