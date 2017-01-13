/*
 * Copyright 2013 by Dandelion Software & Research, Inc (DSR)
 * 
 * This application was written for immunization information system (IIS) community and has
 * been released by DSR under an Apache 2 License with the hope that this software will be used
 * to improve Public Health.  
 */
package org.immregistries.dqa.validator.model;

import java.io.Serializable;
import java.util.Date;

import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.model.hl7types.CodedEntity;

public class DqaMessageHeader implements Serializable
{
  private static final long serialVersionUID = 1l;
  
  public static String PROCESSING_ID_DEBUGGING = "D";
  public static String PROCESSING_ID_PRODUCTION = "P";
  public static String PROCESSING_ID_TRAINING = "T";
  public static String VERSION_ID_2_3_1 = "2.3.1";
  public static String VERSION_ID_2_4 = "2.4";

  private int headerId = 0;
  private String ackTypeAccept = "";//new CodedEntity(CodesetType.ACKNOWLEDGEMENT_TYPE);
  private String ackTypeApplication = "";//new CodedEntity(CodesetType.ACKNOWLEDGEMENT_TYPE);
  private String characterSet = "";
  private String characterSetAlt = "";
  private String country = "";
  private String messageControl = "";
  private Date messageDate = null;
  private String messageDateString;
  private String messageProfile = "";
  private String messageStructure = "";
  private String messageTrigger = "";
  private String messageType = "";
  private String processingStatus = "";//new CodedEntity(CodesetType.MESSAGE_PROCESSING_ID);
  private String receivingApplication = "";
  private String receivingFacility = "";
  private String sendingApplication = "";
  private String sendingFacility = "";
  private String messageVersion = "";


  public int getHeaderId()
  {
    return headerId;
  }

  public void setHeaderId(int headerId)
  {
    this.headerId = headerId;
  }

  public String getAckTypeAccept()
  {
    return ackTypeAccept;
  }

  public String getAckTypeAcceptCode()
  {
    return ackTypeAccept;
  }

  public String getAckTypeApplication()
  {
    return ackTypeApplication;
  }

  public String getAckTypeApplicationCode()
  {
    return ackTypeApplication;
  }

  public String getCharacterSet()
  {
    return characterSet;
  }

  public String getCharacterSetAlt()
  {
    return characterSetAlt;
  }

  public String getCharacterSetCode()
  {
    return characterSet;
  }

  public String getCharacterSetAltCode()
  {
    return characterSetAlt;
  }

  public String getCountry()
  {
    return country;
  }

  public String getCountryCode()
  {
    return country;
  }

  public String getMessageControl()
  {
    return messageControl;
  }

  public Date getMessageDate()
  {
    return messageDate;
  }

  public String getMessageProfile()
  {
    return messageProfile;
  }

  public String getMessageStructure()
  {
    return messageStructure;
  }

  public String getMessageTrigger()
  {
    return messageTrigger;
  }

  public String getMessageType()
  {
    return messageType;
  }

  public String getProcessingStatus()
  {
    return processingStatus;
  }

  public String getProcessingStatusCode()
  {
    return processingStatus;
  }
  
  public void setProcessingStatusCode(String code)
  {
    processingStatus = code;
  }

  public String getReceivingApplication()
  {
    return receivingApplication;
  }

  public String getReceivingFacility()
  {
    return receivingFacility;
  }

  public String getSendingApplication()
  {
    return sendingApplication;
  }

  public String getSendingFacility()
  {
    return sendingFacility;
  }

  public String getMessageVersion()
  {
    return messageVersion;
  }

  public boolean isProcessingStatusDebugging()
  {
    return PROCESSING_ID_DEBUGGING.equals(processingStatus);
  }

  public boolean isProcessingStatusProduction()
  {
    return PROCESSING_ID_PRODUCTION.equals(processingStatus);
  }

  public boolean isProcessingStatusTraining()
  {
    return PROCESSING_ID_DEBUGGING.equals(processingStatus);
  }

  public void setAckTypeAcceptCode(String ackTypeAccept)
  {
    this.ackTypeAccept = ackTypeAccept;
  }

  public void setAckTypeApplicationCode(String ackTypeApplication)
  {
    this.ackTypeApplication = ackTypeApplication;
  }

  public void setCharacterSetCode(String characterSet)
  {
    this.characterSet = characterSet;
  }

  public void setCharacterSetAltCode(String characterSetAlt)
  {
    this.characterSetAlt = characterSetAlt;
  }

  public void setCountryCode(String countryCode)
  {
    this.country = countryCode;
  }

  public void setMessageControl(String messageControl)
  {
    this.messageControl = messageControl;
  }

  public void setMessageDate(Date messageDate)
  {
    this.messageDate = messageDate;
  }

  public void setMessageProfile(String messageProfileId)
  {
    this.messageProfile = messageProfileId;
  }

  public void setMessageStructure(String messageStructure)
  {
    this.messageStructure = messageStructure;
  }

  public void setMessageTrigger(String messageTrigger)
  {
    this.messageTrigger = messageTrigger;
  }

  public void setMessageType(String messageType)
  {
    this.messageType = messageType;
  }

  public void setProcessingIdCode(String processingId)
  {
    this.processingStatus = processingId;
  }

  public void setReceivingApplication(String receivingApplication)
  {
    this.receivingApplication = receivingApplication;
  }

  public void setReceivingFacility(String receivingFacility)
  {
    this.receivingFacility = receivingFacility;
  }

  public void setSendingApplication(String sendingApplication)
  {
    this.sendingApplication = sendingApplication;
  }

  public void setSendingFacility(String sendingFacility)
  {
    this.sendingFacility = sendingFacility;
  }

  public void setMessageVersion(String versionId)
  {
    this.messageVersion = versionId;
  }

public void setMessageDateString(String sentDate) {
	this.messageDateString = sentDate;
	
}

public String getMessageDateString() {
	return this.messageDateString;
}

}
