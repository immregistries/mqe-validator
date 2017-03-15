package org.immregistries.dqa.validator.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinIsPresent;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsPresent;
import org.immregistries.dqa.validator.issue.IssueObject;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MessageResponseEvaluator {
	INSTANCE;
	private static final Logger logger = LoggerFactory.getLogger(MessageResponseEvaluator.class);
	
	public DqaMessageMetrics toMetrics(List<ValidationRuleResult> ruleResults) {
		DqaMessageMetrics metrics = new DqaMessageMetrics();
		Map<MessageAttribute, Integer> attributeCounts = makeCountMap(ruleResults);
		metrics.setAttributeCounts(attributeCounts);
		metrics.getObjectCounts().putAll(makeObjectCounts(ruleResults));
		logger.info(metrics.toString());
		return metrics;
	}
	
	protected Map<IssueObject, Integer>  makeObjectCounts(List<ValidationRuleResult> results) {
		Map<IssueObject, Integer> objCounts = new HashMap<IssueObject, Integer>();
		
		objCounts.put(IssueObject.PATIENT,  1);
		
		int vaccCount = 0;
		int nokCount = 0;
		
		for (ValidationRuleResult result : results) {
			
			if (result.getRuleClass().equals(VaccinationIsPresent.class)) {
				if (result.isRulePassed()) {
					vaccCount++;
				}
			}
			
			if (result.getRuleClass().equals(NextOfKinIsPresent.class)) {
				if (result.isRulePassed()) {
					nokCount++;
				}
			}
			
		}
		
		objCounts.put(IssueObject.VACCINATION,  vaccCount);
		objCounts.put(IssueObject.NEXT_OF_KIN,  nokCount);
		return objCounts;
	}
	
	protected Map<MessageAttribute, Integer> makeCountMap(List<ValidationRuleResult> results) {
		Map<MessageAttribute, Integer> map = new HashMap<>();
		for (ValidationRuleResult result : results) {
			List<ValidationIssue> issues = result.getIssues();
			for (ValidationIssue issue : issues) {
				MessageAttribute attr = issue.getIssue();
				Integer cnt = map.get(attr);
				if (cnt == null) {
					map.put(attr, 1);
				} else {
					map.put(attr,  cnt + 1);
				}
			}
		}
		
		return map;
	}
}
