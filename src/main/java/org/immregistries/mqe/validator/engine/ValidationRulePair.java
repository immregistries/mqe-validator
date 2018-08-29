package org.immregistries.mqe.validator.engine;


import org.immregistries.mqe.validator.domain.TargetType;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;

public class ValidationRulePair<T> {

  private ValidationRule<T> rule;
  private T target;
  private MqeMessageReceived message;

  public ValidationRuleResult evaluateRule() {
    
    ValidationRuleResult vrr = rule.evaluate(this.target, this.message);
	if(target instanceof MqeVaccination){
		vrr.setTargetType(TargetType.Vaccination);
		vrr.setTargetId(((MqeVaccination) target).getID());
	}
	
	return vrr;
  }

  /**
   * @return the rule
   */
  public ValidationRule<T> getRule() {
    return rule;
  }

  /**
   * @param rule the rule to set
   */
  public void setRule(ValidationRule<T> rule) {
    this.rule = rule;
  }

  /**
   * @return the target
   */
  public T getTarget() {
    return target;
  }

  /**
   * @param target the target to set
   */
  public void setTarget(T target) {
    this.target = target;
  }

  /**
   * @return the message
   */
  public MqeMessageReceived getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(MqeMessageReceived message) {
    this.message = message;
  }


}
