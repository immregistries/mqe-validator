/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package com.openimmunizationsoftware.dqa.model;

import java.io.Serializable;
import java.util.Date;

import com.openimmunizationsoftware.dqa.model.types.CodedEntity;

public class Observation implements Skippable, Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  private CodedEntity observationIdentifier = new CodedEntity(CodeTable.Type.OBSERVATION_IDENTIFIER);
  private String observationValue = "";
  private Date observationValueDate = null;
  private String observationValueDateString;
  private String observationSubId = "";
  private boolean skipped = false;
  private CodedEntity valueType = new CodedEntity(CodeTable.Type.HL7_VALUE_TYPE);
  
  public Date getObservationValueDate()
  {
    return observationValueDate;
  }

  public void setObservationValueDate(Date observationValueDate)
  {
    this.observationValueDate = observationValueDate;
  }

  public String getObservationSubId()
  {
    return observationSubId;
  }

  public void setObservationSubId(String observationSubId)
  {
    this.observationSubId = observationSubId;
  }

  public CodedEntity getObservationIdentifier()
  {
    return observationIdentifier;
  }
  
  public String getObservationIdentifierCode()
  {
    return this.observationIdentifier.getCode();
  }
  
  public String getObservationValue()
  {
    return observationValue;
  }
  
  public CodedEntity getValueType()
  {
    return valueType;
  }

  public String getValueTypeCode()
  {
    return valueType.getCode();
  }
  public boolean isSkipped()
  {
    return skipped;
  }
  public void setObservationIdentifierCode(String observationIdentifierCode)
  {
    this.observationIdentifier.setCode(observationIdentifierCode);
  }
  public void setObservationValue(String observationValue)
  {
    this.observationValue = observationValue;
  }
  public void setSkipped(boolean skipped)
  {
    this.skipped = skipped;
  }
  public void setValueType(CodedEntity valueType)
  {
    this.valueType = valueType;
  }

  public void setValueTypeCode(String valueTypeCode)
  {
    this.valueType.setCode(valueTypeCode);
  }

/**
 * @return the observationValueDateString
 */
public String getObservationValueDateString() {
	return observationValueDateString;
}

/**
 * @param observationValueDateString the observationValueDateString to set
 */
public void setObservationValueDateString(String observationValueDateString) {
	this.observationValueDateString = observationValueDateString;
}

   
}
