package org.immregistries.dqa.validator.model;

import java.util.Date;

import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.model.hl7types.CodedEntity;

public class VaccinationVIS
{
  private int visId = 0;
  private DqaVaccination vaccination = null;
  private int positionId = 0;
  private boolean skipped = false;
  private String document = "";//new CodedEntity(CodesetType.VACCINATION_VIS_DOC_TYPE);
  private String cvx = "";//new CodedEntity(CodesetType.VACCINATION_VIS_CVX_CODE);
  private Date publishedDate = null;
  private Date presentedDate = null;

  public String getCvxCode()
  {
    return cvx;
  }
  public void setCvxCode(String cvxCode)
  {
    cvx = cvxCode;
  }
  public String getCvx()
  {
    return cvx;
  }
  public int getVisId()
  {
    return visId;
  }
  public void setVisId(int visId)
  {
    this.visId = visId;
  }
  public DqaVaccination getVaccination()
  {
    return vaccination;
  }
  public void setVaccination(DqaVaccination vaccination)
  {
    this.vaccination = vaccination;
  }
  public int getPositionId()
  {
    return positionId;
  }
  public void setPositionId(int positionId)
  {
    this.positionId = positionId;
  }
  public boolean isSkipped()
  {
    return skipped;
  }
  public void setSkipped(boolean skipped)
  {
    this.skipped = skipped;
  }
  public String getDocumentCode()
  {
    return document;
  }
  public void setDocumentCode(String documentCode)
  {
    this.document = documentCode;
  }
  public Date getPublishedDate()
  {
    return publishedDate;
  }
  public void setPublishedDate(Date publishedDate)
  {
    this.publishedDate = publishedDate;
  }
  public Date getPresentedDate()
  {
    return presentedDate;
  }
  public void setPresentedDate(Date presentedDate)
  {
    this.presentedDate = presentedDate;
  }
  public String getDocument()
  {
    return document;
  }
}
