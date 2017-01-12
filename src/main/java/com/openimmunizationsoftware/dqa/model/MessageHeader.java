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

public class MessageHeader implements Serializable
{
  
  private static final long serialVersionUID = 1l;
  
  public static String PROCESSING_ID_DEBUGGING = "D";
  public static String PROCESSING_ID_PRODUCTION = "P";
  public static String PROCESSING_ID_TRAINING = "T";

  public static String VERSION_ID_2_3_1 = "2.3.1";
  public static String VERSION_ID_2_4 = "2.4";

  private MessageReceived messageReceived = null;
  private int headerId = 0;
  private CodedEntity ackTypeAccept = new CodedEntity(CodeTable.Type.ACKNOWLEDGEMENT_TYPE);
  private CodedEntity ackTypeApplication = new CodedEntity(CodeTable.Type.ACKNOWLEDGEMENT_TYPE);
  private CodedEntity characterSet = new CodedEntity(CodeTable.Type.CHARACTER_SET);
  private CodedEntity characterSetAlt = new CodedEntity(CodeTable.Type.CHARACTER_SET);
  private CodedEntity country = new CodedEntity(CodeTable.Type.ADDRESS_COUNTRY);
  private String messageControl = "";
  private Date messageDate = null;
  private String messageDateString;
  private String messageProfile = "";
  private String messageStructure = "";
  private String messageTrigger = "";
  private String messageType = "";
  private CodedEntity processingStatus = new CodedEntity(CodeTable.Type.MESSAGE_PROCESSING_ID);
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

  public CodedEntity getAckTypeAccept()
  {
    return ackTypeAccept;
  }

  public String getAckTypeAcceptCode()
  {
    return ackTypeAccept.getCode();
  }

  public CodedEntity getAckTypeApplication()
  {
    return ackTypeApplication;
  }

  public String getAckTypeApplicationCode()
  {
    return ackTypeApplication.getCode();
  }

  public CodedEntity getCharacterSet()
  {
    return characterSet;
  }

  public CodedEntity getCharacterSetAlt()
  {
    return characterSetAlt;
  }

  public String getCharacterSetCode()
  {
    return characterSet.getCode();
  }

  public String getCharacterSetAltCode()
  {
    return characterSetAlt.getCode();
  }

  public CodedEntity getCountry()
  {
    return country;
  }

  public String getCountryCode()
  {
    return country.getCode();
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

  public CodedEntity getProcessingStatus()
  {
    return processingStatus;
  }

  public String getProcessingStatusCode()
  {
    return processingStatus.getCode();
  }
  
  public void setProcessingStatusCode(String code)
  {
    processingStatus.setCode(code);
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
    return processingStatus.getCode().equals(PROCESSING_ID_DEBUGGING);
  }

  public boolean isProcessingStatusProduction()
  {
    return processingStatus.getCode().equals(PROCESSING_ID_PRODUCTION);
  }

  public boolean isProcessingStatusTraining()
  {
    return processingStatus.getCode().equals(PROCESSING_ID_DEBUGGING);
  }

  public void setAckTypeAcceptCode(String ackTypeAccept)
  {
    this.ackTypeAccept.setCode(ackTypeAccept);
  }

  public void setAckTypeApplicationCode(String ackTypeApplication)
  {
    this.ackTypeApplication.setCode(ackTypeApplication);
  }

  public void setCharacterSetCode(String characterSet)
  {
    this.characterSet.setCode(characterSet);
  }

  public void setCharacterSetAltCode(String characterSetAlt)
  {
    this.characterSetAlt.setCode(characterSetAlt);
  }

  public void setCountryCode(String countryCode)
  {
    this.country.setCode(countryCode);
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
    this.processingStatus.setCode(processingId);
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

  public MessageReceived getMessageReceived()
  {
    return messageReceived;
  }

  public void setMessageReceived(MessageReceived messageReceived)
  {
    this.messageReceived = messageReceived;
  }

public void setMessageDateString(String sentDate) {
	this.messageDateString = sentDate;
	
}

public String getMessageDateString() {
	return this.messageDateString;
}

}
