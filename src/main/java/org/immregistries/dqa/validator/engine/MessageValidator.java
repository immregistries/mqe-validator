package org.immregistries.dqa.validator.engine;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.model.DqaMessageReceived;

public enum MessageValidator {
	INSTANCE;
	
	private final ValidationRunner runner = ValidationRunner.INSTANCE;
	private final ValidationUtility util = ValidationUtility.INSTANCE;
	private final RulePairBuilder builder = RulePairBuilder.INSTANCE;
	/**
	 * The driving method for executing the whole set of validations for an entire message. 
	 * 
	 * @param m A Set of objects that represents a vaccination update message. 
	 * @return a list of validation results. 
	 */
	public List<ValidationRuleResult> validateMessage(DqaMessageReceived m) {
		
		//first validate the high order elements of the message: 
		List<ValidationRuleResult> headerAndPatientResults = validateHighOrderElements(m);
		
		//Generate a list of passed classes from the results: 
		List<Class> headerAndPatientPassed = util.getPassedFromResults(headerAndPatientResults);
		
		//Then validate the list items.  The resons they are treated separately is desribed elsewhere.  
		List<ValidationRuleResult> listEntityResults = validateListItems(m, headerAndPatientPassed);
		
		//Then add them all together.
		List<ValidationRuleResult> validationResults = new ArrayList<ValidationRuleResult>();
		validationResults.addAll(headerAndPatientResults);
		validationResults.addAll(listEntityResults);
		
		return validationResults;
	}
	
	protected List<ValidationRuleResult> validateHighOrderElements(DqaMessageReceived m) {
		List<ValidationRulePair> headerAndPatientRules = builder.buildHeaderAndPatientRuleList(m);
		List<ValidationRuleResult> headerAndPatientResults = runner.processValidationRules(headerAndPatientRules, new ArrayList<Class>());
	
		return headerAndPatientResults;
	}
	
	protected List<ValidationRuleResult> validateListItems(DqaMessageReceived m, List<Class> mainPassed) {
		List<List<ValidationRulePair>> listEntityRuleLists = builder.buildListItemRuleLists(m);
		
		List<ValidationRuleResult> listRuleResults = new ArrayList<ValidationRuleResult>();
		
		for (List<ValidationRulePair> rules : listEntityRuleLists) {
			List<ValidationRuleResult> results = runner.processValidationRules(rules, mainPassed);
			listRuleResults.addAll(results);
		}
		
		return listRuleResults;
	}
	
}
