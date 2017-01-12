/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.model;

import java.io.Serializable;

public class CodeReceived implements Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  private long codeId = 0l;
  
  private CodeTable table = null;//Type of value???
  private String receivedValue = "";//Actual value from the message this may be mapped. 

  //We need a concept of mapping codes from the message to the DQACM codes. That will happen 
  //During the lookup. 
  
  private String codeValue = "";//The value out of the XML that we said is a match after mapping. 
  
  private String codeLabel = "";
  private CodeStatus codeStatus = new CodeStatus();

  public CodeReceived()
  {
    // default
  }
  
  public CodeReceived(SubmitterProfile profile, CodeTable table, String receivedValue)
  {
    this.table = table;
    this.receivedValue = receivedValue;
  }
  
  public CodeReceived(CodeReceived parent, SubmitterProfile profile, String codeLabel)
  {
    this.table = parent.table;
    this.receivedValue = parent.receivedValue;
    this.codeValue = parent.codeValue;
    this.codeStatus = parent.codeStatus;
    this.codeLabel = codeLabel;
  }
  
  public long getCodeId()
  {
    return codeId;
  }
  public void setCodeId(long codeId)
  {
    this.codeId = codeId;
  }
  public String getCodeLabel()
  {
    return codeLabel;
  }

  public void setCodeLabel(String codeLabel)
  {
    this.codeLabel = codeLabel;
  }
  public CodeTable getTable()
  {
    return table;
  }
  public void setTable(CodeTable table)
  {
    this.table = table;
  }
  public String getReceivedValue()
  {
    return receivedValue;
  }
  
  public void setReceivedValue(String receivedValue)
  {
    this.receivedValue = receivedValue.toUpperCase();
  }
  public String getCodeValue()
  {
    return codeValue;
  }
  public void setCodeValue(String codeValue)
  {
    this.codeValue = codeValue;
  }
  public CodeStatus getCodeStatus()
  {
    return codeStatus;
  }
  public void setCodeStatus(CodeStatus codeStatus)
  {
    this.codeStatus = codeStatus;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof CodeReceived)
    {
      CodeReceived compareTo = (CodeReceived) obj;
      if (getTable() != null & getReceivedValue() != null)
      {
        return getTable().equals(compareTo.getTable()) && getReceivedValue().equals(compareTo.getReceivedValue());        
      }
    }
    return super.equals(obj);
  }
  
  @Override
  public int hashCode()
  {
    if (getTable() != null & getReceivedValue() != null)
    {
      return (getTable().getTableId() + "." + getReceivedValue()).hashCode();
    }
    return super.hashCode();
  }

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "CodeReceived [codeId=" + codeId +", table=" + table + ", receivedValue=" + receivedValue
			+ ", codeValue=" + codeValue + ", codeLabel=" + codeLabel
			+ ", codeStatus=" + codeStatus + "]";
}
}
