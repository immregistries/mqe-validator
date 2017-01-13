/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.immregistries.dqa.validator.model.codes;

import java.io.Serializable;

public class VaccineCvxGroup implements Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  private VaccineCvx vaccineCvx = null;
  private VaccineGroup vaccineGroup = null;

  public VaccineCvx getVaccineCvx()
  {
    return vaccineCvx;
  }

  public VaccineGroup getVaccineGroup()
  {
    return vaccineGroup;
  }

  public void setVaccineCvx(VaccineCvx vaccineCvx)
  {
    this.vaccineCvx = vaccineCvx;
  }

  public void setVaccineGroup(VaccineGroup vaccineGroup)
  {
    this.vaccineGroup = vaccineGroup;
  }
}
