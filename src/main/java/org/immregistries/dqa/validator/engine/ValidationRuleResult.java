package org.immregistries.dqa.validator.engine;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;

public class ValidationRuleResult {
	private List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
	private boolean rulePassed = false;
	private List<Detection> possible = new ArrayList<>();
	private Class<? extends ValidationRule> ruleClass;

	public List<Detection> getPossible() {
		return possible;
	}

	public List<ValidationIssue> getIssues() {
		return issues;
	}
	
	public void setIssues(List<ValidationIssue> issues) {
		this.issues = issues;
	}
	
	public boolean isRulePassed() {
		return rulePassed;
	}
	
	public void setRulePassed(boolean rulePassed) {
		this.rulePassed = rulePassed;
	}
	
	public Class<? extends ValidationRule> getRuleClass() {
		return ruleClass;
	}
	
	public void setRuleClass(Class<? extends ValidationRule> ruleClass) {
		this.ruleClass = ruleClass;
	}
	
}
