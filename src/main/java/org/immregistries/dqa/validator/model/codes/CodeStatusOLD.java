/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.immregistries.dqa.validator.model.codes;

import java.io.Serializable;

public class CodeStatusOLD implements Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  private String codeStatus = "";
  private String codeLabel = "";
  
  public static final CodeStatusOLD VALID = new CodeStatusOLD("V", "Valid");
  public static final CodeStatusOLD INVALID = new CodeStatusOLD("I", "Invalid");
  public static final CodeStatusOLD UNRECOGNIZED = new CodeStatusOLD("U", "Unrecognized");
  public static final CodeStatusOLD DEPRECATED = new CodeStatusOLD("D", "Deprecated");
  public static final CodeStatusOLD IGNORED = new CodeStatusOLD("G", "Ignored");
  
  public static final String STATUS_VALID = "V";
  public static final String STATUS_INVALID = "I";
  public static final String STATUS_UNRECOGNIZED = "U";
  public static final String STATUS_DEPRECATED = "D";
  public static final String STATUS_IGNORED = "G";
  
  public boolean isValid()
  {
    return codeStatus.equals(STATUS_VALID);
  }
  
  public boolean isInvalid()
  {
    return codeStatus.equals(STATUS_INVALID);
  }
  
  public boolean isUnrecognized()
  {
    return codeStatus.equals(STATUS_UNRECOGNIZED);
  }
  
  public boolean isDeprecated()
  {
    return codeStatus.equals(STATUS_DEPRECATED);
  }
  
  public boolean isIgnored()
  {
    return codeStatus.equals(STATUS_IGNORED);
  }
  
  public CodeStatusOLD()
  {
    // default
  }
  
  public CodeStatusOLD(String codeValue, String codeLabel)
  {
    this.codeStatus = codeValue;
    this.codeLabel = codeLabel;
  }
  
  public String getCodeStatus()
  {
    return codeStatus;
  }
  public void setCodeStatus(String codeStatus)
  {
    this.codeStatus = codeStatus;
  }
  public String getCodeLabel()
  {
    return codeLabel;
  }
  public void setCodeLabel(String codeLabel)
  {
    this.codeLabel = codeLabel;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj instanceof CodeStatusOLD)
    {
      return ((CodeStatusOLD) obj).codeStatus.equals(this.codeStatus);
    }
    // TODO Auto-generated method stub
    return super.equals(obj);
  }
}
