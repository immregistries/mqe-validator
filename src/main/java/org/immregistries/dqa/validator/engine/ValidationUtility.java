package org.immregistries.dqa.validator.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.hl7util.SeverityLevel;
import org.immregistries.dqa.validator.engine.common.CommonRules;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.IssueType;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.slf4j.Logger;

@SuppressWarnings("rawtypes")
public enum ValidationUtility {
	INSTANCE;

	private static final CommonRules common  = CommonRules.INSTANCE;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ValidationUtility.class);
	
	public ValidationIssue createIssue(MessageAttribute attribute, String codedValue) {
		ValidationIssue found = new ValidationIssue();
		found.setMessageAttribute(attribute);
		found.setCodeReceived(codedValue);
		found.setSeverityLevel(SeverityLevel.ERROR);//This needs to be equipped to be naunced. This will comes off a profile. 
		return found;
	}

	/**
	 * This method determines if the rule has passed, based on if any of the issues are at the error level. 
	 * @param issues
	 * @return
	 */
	public boolean hasErrors(List<ValidationIssue> issues) {
		if (issues != null && issues.size() > 0) {
			for (ValidationIssue issue : issues) {
				if (issue.isError()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean addToListIfEmpty(String value, MessageAttribute issue, List<ValidationIssue> toList) {
		if (this.common.isEmpty(value)) {
			toList.add(issue.build(value));
			return true;
		}
		return false;
	}
	
	public boolean addToListIfEmpty(String value, IssueField field, List<ValidationIssue> toList) {
		MessageAttribute issue = MessageAttribute.get(field, IssueType.MISSING);
		return addToListIfEmpty(value, issue, toList);
	}
	
	public List<Class<? extends ValidationRule>> makeRuleList(Class<? extends ValidationRule>... classes) {
		return Arrays.asList(classes);
	};
	
	/**
	 * This builds a list of passed rule classes from a list of validation results. 
	 * @param list
	 * @return
	 */
	public List<Class> getPassedFromResults(List<ValidationRuleResult> list) {
		List<Class> passedValidations = new ArrayList<Class>();
		for (ValidationRuleResult v : list) {
			if (v.isRulePassed()) {
				passedValidations.add(v.getRuleClass());
			}
		}
		return passedValidations;
	}
	
	
	/**
	 * This builds a list of FAILED rule classes from a list of validation results. 
	 * @param list
	 * @return
	 */
	public List<Class> getFailedFromResults(List<ValidationRuleResult> list) {
		List<Class> failedValidations = new ArrayList<Class>();
		for (ValidationRuleResult v : list) {
			if (!v.isRulePassed()) {
				failedValidations.add(v.getRuleClass());
			}
		}
		return failedValidations;
	}
	
	public List<ValidationRulePair> buildRulePairs(List<ValidationRule> ruleList, Object target, DqaMessageReceived mr) {
		List<ValidationRulePair> vrpList = new ArrayList<ValidationRulePair>();
		for (ValidationRule vr : ruleList) {
			ValidationRulePair vp = new ValidationRulePair();
			vp.setMessage(mr);
			vp.setTarget(target);
			vp.setRule(vr);
			vrpList.add(vp);
		}
		return vrpList;
	}

}

