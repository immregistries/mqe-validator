package org.immregistries.dqa.validator.engine;

import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;

import java.util.ArrayList;
import java.util.List;

public class ValidationRuleResult {
	private List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
	private boolean rulePassed = false;
	private List<Detection> possible = new ArrayList<>();
	private Class<? extends ValidationRule> ruleClass;

	public List<Detection> getPossible() {
		return possible;
	}

	public List<ValidationDetection> getValidationDetections() {
		return issues;
	}
	
	public void setIssues(List<ValidationDetection> issues) {
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
