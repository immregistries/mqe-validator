package com.openimmunizationsoftware.dqa.parser;

import java.io.Serializable;

import com.openimmunizationsoftware.dqa.quality.Software;

public class EvaluationActual implements Serializable
{
  private int evaluationActualId = 0;
  private Software software = null;
  private String vaccineCvx = "";
  private String seriesUsedCode = "";
  private String seriesUsedText = "";
  private String reasonCode = "";
  private String reasonText = "";
  private String doseNumber = "";
  private String doseValid = "";

  public int getEvaluationActualId() {
    return evaluationActualId;
  }

  public void setEvaluationActualId(int evaluationActualId) {
    this.evaluationActualId = evaluationActualId;
  }

  public Software getSoftware() {
    return software;
  }

  public void setSoftware(Software software) {
    this.software = software;
  }

  public String getVaccineCvx() {
    return vaccineCvx;
  }

  public void setVaccineCvx(String vaccineCvx) {
    this.vaccineCvx = vaccineCvx;
  }

  public String getSeriesUsedCode() {
    return seriesUsedCode;
  }

  public void setSeriesUsedCode(String seriesUsedCode) {
    this.seriesUsedCode = seriesUsedCode;
  }

  public String getSeriesUsedText() {
    return seriesUsedText;
  }

  public void setSeriesUsedText(String seriesUsedText) {
    this.seriesUsedText = seriesUsedText;
  }

  public String getReasonCode() {
    return reasonCode;
  }

  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }

  public String getReasonText() {
    return reasonText;
  }

  public void setReasonText(String reasonText) {
    this.reasonText = reasonText;
  }

  public String getDoseNumber() {
    return doseNumber;
  }

  public void setDoseNumber(String doseNumber) {
    this.doseNumber = doseNumber;
  }

  public String getDoseValid() {
    return doseValid;
  }

  public void setDoseValid(String doseValid) {
    this.doseValid = doseValid;
  }

}
