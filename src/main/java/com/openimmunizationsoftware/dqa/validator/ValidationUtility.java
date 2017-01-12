package com.openimmunizationsoftware.dqa.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.openimmunizationsoftware.dqa.model.CodeReceived;
import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.validator.common.CommonRules;
import com.openimmunizationsoftware.dqa.validator.issues.IssueAction;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.IssueType;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

@SuppressWarnings("rawtypes")
public enum ValidationUtility {
	INSTANCE;

	private static final CommonRules common  = CommonRules.INSTANCE;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ValidationUtility.class);
	
	public IssueFound createIssue(PotentialIssue issue, String codedValue) {
		IssueFound found = new IssueFound();
		found.setIssue(issue);
		CodeReceived cr = new CodeReceived();
		cr.setCodeValue(codedValue);
		found.setCodeReceived(cr);
		found.setIssueAction(IssueAction.ERROR);//This needs to be equipped to be naunced. This will comes off a profile. 
		return found;
	}

	/**
	 * This method determines if the rule has passed, based on if any of the issues are at the error level. 
	 * @param issues
	 * @return
	 */
	public boolean hasErrors(List<IssueFound> issues) {
		if (issues != null && issues.size() > 0) {
			for (IssueFound issue : issues) {
				if (issue.isError()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean addToListIfEmpty(String value, PotentialIssue issue, List<IssueFound> toList) {
		if (this.common.isEmpty(value)) {
			toList.add(issue.build(value));
			return true;
		}
		return false;
	}
	
	public boolean addToListIfEmpty(String value, IssueField field, List<IssueFound> toList) {
		PotentialIssue issue = PotentialIssue.get(field, IssueType.MISSING);
		return addToListIfEmpty(value, issue, toList);
	}
	
	
//	public List<IssueFound> makeIssueList(IssueFound... issues) {
//		List<IssueFound> issuesList = new ArrayList<IssueFound>();
//		for (IssueFound issue : issues) {
//			if (issue != null) {
//				issuesList.add(issue);
//			}
//		}
//		return issuesList;
//	}

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
	
	public List<ValidationRulePair> buildRulePairs(List<ValidationRule> ruleList, Object target, MessageReceived mr) {
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

