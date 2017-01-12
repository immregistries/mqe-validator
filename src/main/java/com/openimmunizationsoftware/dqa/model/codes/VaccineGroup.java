/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.model.codes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.types.ToolTip;

public class VaccineGroup implements Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  private String groupCode = "";
  private int groupId = 0;
  private String groupLabel = "";
  private ToolTip toolTip = null;
  private List<VaccineCvx> vaccineCvxList = new ArrayList<VaccineCvx>();

  public String getGroupCode()
  {
    return groupCode;
  }

  public int getGroupId()
  {
    return groupId;
  }

  public String getGroupLabel()
  {
    return groupLabel;
  }

  public ToolTip getToolTip()
  {
    if (toolTip == null)
    {
      toolTip = new ToolTip(groupLabel, "");
    }
    return toolTip;
  }

  public List<VaccineCvx> getVaccineCvxList()
  {
    return vaccineCvxList;
  }

  public void setGroupCode(String groupCode)
  {
    this.groupCode = groupCode;
  }

  public void setGroupId(int groupId)
  {
    this.groupId = groupId;
  }

  public void setGroupLabel(String groupLabel)
  {
    this.groupLabel = groupLabel;
  }

  public void setToolTip(ToolTip toolTip)
  {
    this.toolTip = toolTip;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof VaccineGroup)
    {
      return ((VaccineGroup) obj).getGroupId() == groupId;
    }
    return super.equals(obj);
  }

  @Override
  public int hashCode()
  {
    return getGroupId();
  }
}
