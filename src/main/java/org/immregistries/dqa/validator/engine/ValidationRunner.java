package org.immregistries.dqa.validator.engine;

import java.util.ArrayList;
import java.util.List;

public enum ValidationRunner {
	INSTANCE;
	
	private static final ValidationUtility util = ValidationUtility.INSTANCE;
	
	protected List<ValidationRuleResult> processValidationRules(List<ValidationRulePair> rulesLeftToRun, List<Class> passedPreviously) {
		
	     List<ValidationRulePair> eligible = new ArrayList<ValidationRulePair>();
	     List<Class> passed = new ArrayList<Class>();
	     List<ValidationRuleResult> results = new ArrayList<ValidationRuleResult>();
	     
	     if (passedPreviously != null && passedPreviously.size() > 0) {
	    	 passed.addAll(passedPreviously);
	     }

	     for (ValidationRulePair r : rulesLeftToRun) {
	          if (r.getRule().dependenciesAreMet(passed)) {
	               eligible.add(r);
	          }
	     }

	     if (eligible.size() > 0) {

	          List<ValidationRuleResult> ruleResults = processRules(eligible);
	          //Add all the results from processing the rules. 
	          results.addAll(ruleResults);
	          //Add the classes that passed to our list of passed rules. 
	          passed.addAll(util.getPassedFromResults(ruleResults));

	          //Then make a list of the rules that are left to process: 
	          List<ValidationRulePair> rulesLeftToProcess = new ArrayList<ValidationRulePair>();
	          //First add the rules coming into the method: 
	          rulesLeftToProcess.addAll(rulesLeftToRun);
	          //Then remove the ones that were run: 
	          rulesLeftToProcess.removeAll(eligible);
	          
	          //The process the rest. 
	          List<ValidationRuleResult> theRest = processValidationRules(rulesLeftToProcess, passed);
	          //And add the results to the list. 
	          results.addAll(theRest);
	     }

	     return results;
	}

	protected List<ValidationRuleResult> processRules(List<ValidationRulePair> eligible) {
		List<ValidationRuleResult> results = new ArrayList<ValidationRuleResult>();
		for (ValidationRulePair rp : eligible) {
			ValidationRuleResult vrr = rp.evaluateRule();
			results.add(vrr);
		}
		return results;
	}
}
