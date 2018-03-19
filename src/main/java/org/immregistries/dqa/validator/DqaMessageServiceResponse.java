package org.immregistries.dqa.validator;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;

import java.util.ArrayList;
import java.util.List;

/**
 * This will contain the results of this process.
 * @author Josh
 *
 */
public class DqaMessageServiceResponse {

	private DqaMessageReceived messageObjects;
	private List<ValidationRuleResult> validationResults = new ArrayList<ValidationRuleResult>();

	public DqaMessageReceived getMessageObjects() {
		return messageObjects;
	}

	public void setMessageObjects(DqaMessageReceived messageObjects) {
		this.messageObjects = messageObjects;
	}

	public List<ValidationRuleResult> getValidationResults() {
		return validationResults;
	}
	
	public void setValidationResults(List<ValidationRuleResult> validationResults) {
		this.validationResults = validationResults;
	}

}
