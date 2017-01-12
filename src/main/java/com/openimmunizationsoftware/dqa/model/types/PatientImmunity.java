/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.model.types;

import java.io.Serializable;

import com.openimmunizationsoftware.dqa.model.CodeTable.Type;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.model.Skippable;

public class PatientImmunity  implements Serializable
{
  private int immunityId = 0;
  private CodedEntity immunity = new CodedEntity(Type.EVIDENCE_OF_IMMUNITY);
  
  public String getImmunityCode()
  {
    return immunity.getCode();
  }
  
  public void setImmunityCode(String immunityCode)
  {
    immunity.setCode(immunityCode);
  }
  
  public CodedEntity getImmunity()
  {
    return immunity;
  }
  public int getImmunityId()
  {
    return immunityId;
  }
  public void setImmunityId(int immunityId)
  {
    this.immunityId = immunityId;
  }

}
