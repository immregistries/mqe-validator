package org.immregistries.dqa.validator.report;

import org.immregistries.dqa.validator.issue.MessageAttribute;


/**
 * The intention is to generalize 
 * the interface for the various types of things we report. 
 * @author Josh Hull
 */
public interface Measureable {
	MessageAttribute getAttribute();
	
	//If I bring in MessageAttribute...  I have to bring in other things...
	//1. IssueObject
	//2. IssueField
	//3. IssueType

}

/*
 * Message Attribute maybe should be the global set... 
 * 	Perhaps we should ask them to map their validations to the message attribute. 
 *  The integration process could involve adding new MessageAttributes if they don't have them. 
 * 
 * 
 * 
 * 
 * */
