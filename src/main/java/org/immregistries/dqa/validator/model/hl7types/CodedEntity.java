/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.immregistries.dqa.validator.model.hl7types;

import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.model.codes.CodeReceived;

public class CodedEntity
{
  private String code = "";
  private String text = "";
  private String table = "";
  private String altCode = "";
  private String altText = "";
  private String altTable = "";
  private CodesetType tableType = null;
  //This just needs to hold the code manager data???  That we get from the XML.
  private CodeReceived codeReceived = null; 

  public void setValuesFrom(CodedEntity e) {
	  this.code = e.code;
	  this.text = e.text;
	  this.table = e.table;
	  this.altCode = e.altCode;
	  this.altText = e.altText;
	  this.altTable = e.altTable;
	  this.codeReceived = e.codeReceived;
	  //don't copy the table type.
	  //this.tableType
  }

  public boolean isEmpty()
  {
    return code == null || code.equals("");
  }

  public boolean isValid()
  {
    return codeReceived != null && codeReceived.getCodeStatus().isValid();
  }

  public boolean isInvalid()
  {
    return codeReceived != null && codeReceived.getCodeStatus().isInvalid();
  }

  public boolean isUnrecognized()
  {
    return codeReceived != null && codeReceived.getCodeStatus().isUnrecognized();
  }

  public boolean isDeprecated()
  {
    return codeReceived != null && codeReceived.getCodeStatus().isDeprecated();
  }

  public boolean isIgnored()
  {
    return codeReceived != null && codeReceived.getCodeStatus().isIgnored();
  }

  public CodedEntity(CodesetType tableType) {
    this.tableType = tableType;
  }

  public CodeReceived getCodeReceived()
  {
    return codeReceived;
  }

  public void setCodeReceived(CodeReceived codeReceived)
  {
    this.codeReceived = codeReceived;
  }

  public CodesetType getTableType()
  {
    return tableType;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = nullSafe(code);
  }

  public String getText()
  {
    return text;
  }

  public void setText(String text)
  {
    this.text = nullSafe(text);
  }

  public String getTable()
  {
    return table;
  }

  public void setTable(String table)
  {
    this.table = nullSafe(table);
  }

  public String getAltCode()
  {
    return altCode;
  }

  public void setAltCode(String altCode)
  {
    this.altCode = altCode;
  }

  public String getAltText()
  {
    return altText;
  }

  public void setAltText(String altText)
  {
    this.altText = nullSafe(altText);
  }

  public String getAltTable()
  {
    return altTable;
  }

  public void setAltTable(String altTable)
  {
    this.altTable = nullSafe(altTable);
  }
  
  private static String nullSafe(String s)
  {
    if (s == null)
    {
      return "";
    }
    else
    {
      return s;
    }
  }

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "CodedEntity [code=" + code + ", text=" + text + ", table=" + table
			+ ", altCode=" + altCode + ", altText=" + altText + ", altTable="
			+ altTable + ", tableType=" + tableType + ", codeReceived="
			+ codeReceived + "]";
}
}
