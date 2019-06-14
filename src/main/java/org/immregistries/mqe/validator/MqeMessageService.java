package org.immregistries.mqe.validator;

import java.util.HashMap;
import java.util.List;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.MessageValidator;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.transform.MessageTransformer;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.parse.HL7MessageParser;

public enum MqeMessageService {
  INSTANCE;

  private MessageValidator validator = MessageValidator.INSTANCE;
  private MessageTransformer transformer = MessageTransformer.INSTANCE;
  private HL7MessageParser parser = HL7MessageParser.INSTANCE;

  // The purpose of this class is to be the main interface
  // into the MQE processes.
  //
  // THe results of this class will be:
  // 1. the object model that resulted from the HL7 Parser,
  // as well as the pre and post processing.
  // 2. The validation results.
  //
  // These two things will be contained in a single container office.
  //

  /**
   * Send in a raw HL7 Message text. This will engage the MQE Validator engine, and return a parsed
   * object model, as well as a list of identified validation results.
   */
  public MqeMessageServiceResponse processMessage(String messageText) {
	  return  processMessage(messageText, null);
  }
  
  public MqeMessageServiceResponse processMessage(String messageText, HashMap<String, String> detectionsOverride) {
	    MqeMessageReceived mr = this.extractMessageFromText(messageText);
	    return validateData(mr);
  }

  public MqeMessageReceived extractMessageFromText(String messageText) {
    MqeMessageReceived mr = parser.extractMessageFromText(messageText);
    return transformer.transform(mr);
  }


  public MqeMessageServiceResponse validateData(MqeMessageReceived mr) {
    List<ValidationRuleResult> validationResults = validator.validateMessage(mr);

    MqeMessageServiceResponse msr = new MqeMessageServiceResponse();
    msr.setMessageObjects(mr);
    msr.getValidationResults().addAll(validationResults);
    return msr;
  }
  
  public String getSendingFacility(String messageText) {
	  MqeMessageReceived mr = this.extractMessageFromText(messageText);
	  return mr.getMessageHeader().getSendingFacility();
  }
  
}
