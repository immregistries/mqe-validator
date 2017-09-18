package org.immregistries.dqa.validator;

import java.util.List;

import org.immregistries.dqa.validator.engine.MessageValidator;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.parse.HL7MessageParser;
import org.immregistries.dqa.vxu.transform.MessageTransformer;

public enum DqaMessageService {
	INSTANCE;
	
	private MessageValidator validator = MessageValidator.INSTANCE;
	private MessageTransformer transformer = MessageTransformer.INSTANCE;
	private HL7MessageParser parser = HL7MessageParser.INSTANCE;
	
	//The purpose of this class is to be the main interface
	//into the DQA processes. 
	//
	//THe results of this class will be: 
	//1. the object model that resulted from the HL7 Parser, 
	//   as well as the pre and post processing. 
	//2. The validation results. 
	//
	//These two things will be contained in a single container office. 
	//
	/**
	 * Send in a raw HL7 Message text. This will engage the DQA Validator engine, and return 
	 * a parsed object model, as well as a list of identified validation results. 
	 * @param messageText
	 * @return
	 */
	public DqaMessageServiceResponse processMessage(String messageText) {
		DqaMessageReceived mr = parser.extractMessageFromText(messageText);
		
		transformer.transform(mr);
		
		DqaMessageServiceResponse msr = validateData(mr);
		
		return msr;
	}

	public DqaMessageServiceResponse validateData(DqaMessageReceived mr) {
		List<ValidationRuleResult> validationResults = validator.validateMessage(mr);
		
		DqaMessageServiceResponse msr = new DqaMessageServiceResponse();
		msr.setMessageObjects(mr);
		msr.getValidationResults().addAll(validationResults);
		return msr;
	}
}
