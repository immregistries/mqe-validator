package com.openimmunizationsoftware.dqa.validator;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;

public class ValidationRuleResult {
	private List<IssueFound> issues = new ArrayList<IssueFound>();
	private boolean rulePassed = false;
	private Class<? extends ValidationRule> ruleClass;
	
	public List<IssueFound> getIssues() {
		return issues;
	}
	
	public void setIssues(List<IssueFound> issues) {
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
