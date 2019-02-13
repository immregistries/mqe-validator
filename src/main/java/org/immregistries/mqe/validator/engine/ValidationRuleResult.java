package org.immregistries.mqe.validator.engine;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.domain.TargetType;

public class ValidationRuleResult {

  private List<ValidationReport> issues = new ArrayList<ValidationReport>();
  private boolean rulePassed = false;
  private List<Detection> possible = new ArrayList<>();
  private Class<? extends ValidationRule> ruleClass;
  private String targetId;
  private TargetType targetType;
  
  public List<Detection> getPossible() {
    return possible;
  }

  public List<ValidationReport> getValidationDetections() {
    return issues;
  }

  public void setValidationDetections(List<ValidationReport> issues) {
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

	public List<ValidationReport> getIssues() {
		return issues;
	}
	
	public void setIssues(List<ValidationReport> issues) {
		this.issues = issues;
	}
	
	public String getTargetId() {
		return targetId;
	}
	
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	public TargetType getTargetType() {
		return targetType;
	}
	
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	
	public void setPossible(List<Detection> possible) {
		this.possible = possible;
	}
  
   public boolean issuesContainsDetection(Detection detection) {
	   for (ValidationReport issue : this.issues) {
		   if (issue.getDetection().equals(detection)) {
			   return true;
		   }
	   }
	   return false;
   }

}
