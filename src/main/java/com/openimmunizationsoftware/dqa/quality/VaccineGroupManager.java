/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.quality;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openimmunizationsoftware.dqa.model.codes.VaccineCvx;
import com.openimmunizationsoftware.dqa.model.codes.VaccineGroup;

public class VaccineGroupManager
{
  private static VaccineGroupManager singleton = new VaccineGroupManager();

  public static VaccineGroupManager getVaccineGroupManager()
  {
    if (singleton == null)
    {
      singleton = new VaccineGroupManager();
    }
    return singleton;
  }

  private Map<String, VaccineGroup> vaccineGroups = new HashMap<String, VaccineGroup>();
  private List<VaccineCvxGroup> vaccineCvxGroups = null;
  
  public List<VaccineCvxGroup> getVaccineCvxGroups(VaccineCvx vaccineCvx)
  {
    List<VaccineCvxGroup> list = new ArrayList<VaccineCvxGroup>();
    for (VaccineCvxGroup vaccineCvxGroup : vaccineCvxGroups)
    {
      if (vaccineCvxGroup.getVaccineCvx().equals(vaccineCvx))
      {
        list.add(vaccineCvxGroup);
      }
    }
    return list;
  }
  
  public VaccineGroup getVaccineGroup(String groupCode)
  {
    return vaccineGroups.get(groupCode);
  }

  public VaccineGroupManager() {
  }
}
