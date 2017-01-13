/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.immregistries.dqa.validator.model.hl7types;

import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientIdNumber extends Id
{
  public PatientIdNumber()
  {
    super(CodesetType.PATIENT_ID);
  }
  
  public PatientIdNumber(Id id, int position) {
	  this();
	  this.setPositionId(position);
	  super.setNumber(id.getNumber());
	  super.setAssigningAuthorityCode(id.getAssigningAuthorityCode());
	  super.setTypeCode(id.getTypeCode());
  }
  
  private int idNumberId = 0;
  private DqaPatient patient = null;
  private int positionId = 0;
  private boolean skipped = false;
  
  public int getIdNumberId()
  {
    return idNumberId;
  }
  public void setIdNumberId(int idNumberId)
  {
    this.idNumberId = idNumberId;
  }
  public DqaPatient getPatient()
  {
    return patient;
  }
  public void setPatient(DqaPatient patient)
  {
    this.patient = patient;
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
  
}
