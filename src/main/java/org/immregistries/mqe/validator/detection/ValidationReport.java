package org.immregistries.mqe.validator.detection;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.hl7util.Reportable;
import org.immregistries.mqe.hl7util.ReportableSource;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.hl7util.model.CodedWithExceptions;
import org.immregistries.mqe.hl7util.model.Hl7Location;
import org.immregistries.mqe.vxu.MetaFieldInfoData;
import org.immregistries.mqe.vxu.VxuField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationReport implements Reportable {

  private static final Logger logger = LoggerFactory.getLogger(ValidationReport.class);

  private Detection detection = null;// should this be a String?
  private String additionalMessage;
  private int positionId = 0;// This says where in the ACK to put it.
  private SeverityLevel severityLevel = null; // this is how bad it is.
  private String valueReceived = null;// This is the related value.
  private List<Hl7Location> hl7LocationList = new ArrayList<>();

  public ValidationReport() {
  }

  public ValidationReport(VxuField field, DetectionType type, MetaFieldInfoData meta) {
    this.detection = Detection.get(field, type);
    if (detection != null) {
      this.severityLevel = this.detection.getSeverity();
    }
    if (meta != null && meta.getMetaFieldInfo(field) != null) {
      this.hl7LocationList.add(meta.getMetaFieldInfo(field).getHl7Location());
    }
  }

  public ValidationReport(String value, VxuField field, DetectionType type,
      MetaFieldInfoData meta) {
    this.detection = Detection.get(field, type);
    this.severityLevel = this.detection.getSeverity();
    this.hl7LocationList.add(meta.getMetaFieldInfo(field).getHl7Location());
    this.valueReceived = value;
  }

  public Detection getDetection() {
    return detection;
  }

  public String getMessage() {
    return detection != null ? detection.getDisplayText() : "";
  }

  public void setDetection(Detection issue) {
    this.detection = issue;
  }

  public int getPositionId() {
    return positionId;
  }

  public void setPositionId(int positionId) {
    this.positionId = positionId;
  }

  public void setSeverityLevel(SeverityLevel level) {
    this.severityLevel = level;
  }

  public String getValueReceived() {
    return valueReceived;
  }

  public void setValueReceived(String valueReceived) {
    this.valueReceived = valueReceived;
  }

  public boolean isError() {
    return (this.severityLevel != null && SeverityLevel.ERROR.equals(this.severityLevel))
        || this.severityLevel == null && this.detection.getSeverity() == SeverityLevel.ERROR;
  }


  @Override
  public ReportableSource getSource() {
    return ReportableSource.MQE;
  }

  @Override
  public String toString() {
    return "IssueFound [issue=" + detection + ", positionId=" + positionId + ", severity="
        + getSeverity() + ", codeReceived=" + valueReceived + "]";
  }

  @Override
  public SeverityLevel getSeverity() {
    if (this.severityLevel == null && this.detection != null) {
      return this.detection.getSeverity();
    }
    return this.severityLevel;
  }

  @Override
  public CodedWithExceptions getHl7ErrorCode() {
    CodedWithExceptions cwe = new CodedWithExceptions();
    cwe.setIdentifier(this.detection.getHl7ErrorCode().getIdentifier());
    return cwe;
  }

  @Override
  public List<Hl7Location> getHl7LocationList() {
    return this.hl7LocationList;
  }

  public void setAdditionalMessage(String message) {
    this.additionalMessage = message;
  }

  @Override
  public String getReportedMessage() {
    return this.detection.getDisplayText() + " " + (this.additionalMessage != null ? this.additionalMessage : "");
  }

  @Override
  public String getDiagnosticMessage() {
    if (!StringUtils.isBlank(this.valueReceived)) {
      return "Value Received: [" + this.valueReceived + "]";
    }
    return null;
  }

  @Override
  public CodedWithExceptions getApplicationErrorCode() {
    CodedWithExceptions cwe = new CodedWithExceptions();
    if (!this.detection.getApplicationErrorCode().equals("")) {
      cwe.setIdentifier(this.detection.getApplicationErrorCode().getId());
      cwe.setText(this.detection.getApplicationErrorCode().getText());
      cwe.setNameOfCodingSystem("HL70533");
      cwe.setAlternateIdentifier(this.detection.getMqeMqeCode());
      cwe.setAlternateText(this.detection.getDisplayText());
      cwe.setNameOfAlternateCodingSystem("L");
    }
    return cwe;
  }

}
