package org.immregistries.mqe.validator;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;

/**
 * This will contain the results of this process.
 *
 * @author Josh
 */
public class MqeMessageServiceResponse {

  private MqeMessageReceived messageObjects;
  private List<ValidationRuleResult> validationResults = new ArrayList<ValidationRuleResult>();

  public MqeMessageReceived getMessageObjects() {
    return messageObjects;
  }

  public void setMessageObjects(MqeMessageReceived messageObjects) {
    this.messageObjects = messageObjects;
  }

  public List<ValidationRuleResult> getValidationResults() {
    return validationResults;
  }

  public void setValidationResults(List<ValidationRuleResult> validationResults) {
    this.validationResults = validationResults;
  }

}
