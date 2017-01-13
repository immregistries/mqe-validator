package org.immregistries.dqa.validator.engine;

import org.immregistries.dqa.validator.model.DqaMessageReceived;

public class ValidationRulePair<T> {
	
	private ValidationRule<T> rule;
	private T target;
	private DqaMessageReceived message;
	
	public ValidationRuleResult evaluateRule() {
		return rule.evaluate(this.target, this.message);
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
	public DqaMessageReceived getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(DqaMessageReceived message) {
		this.message = message;
	}
	
	
	
	
}
