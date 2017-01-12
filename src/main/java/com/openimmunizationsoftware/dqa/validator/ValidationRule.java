package com.openimmunizationsoftware.dqa.validator;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.validator.codes.CodeRepository;
import com.openimmunizationsoftware.dqa.validator.common.CodeHandler;
import com.openimmunizationsoftware.dqa.validator.common.CommonRules;
import com.openimmunizationsoftware.dqa.validator.common.DateUtility;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

/**
 * A pass of a Validation Rule means that the concept expressed in the name is true.  
 * @author Josh
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public abstract class ValidationRule<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationRule.class);
	/**
	 * I wanted this to be a singleton 
	 * because I know I'll have tons of rule objects floating around
	 * and I wanted to limit the number of util objects.
	 */
	protected final ValidationUtility util = ValidationUtility.INSTANCE;
	protected final CommonRules common = CommonRules.INSTANCE;
	protected final CodeHandler codr = CodeHandler.INSTANCE;
	protected final DateUtility datr = DateUtility.INSTANCE;
	protected final CodeRepository repo = CodeRepository.INSTANCE;
	
	protected Class[] getDependencies() {
		return new Class[] {};
	}
	
	/**
	 * This is the primary method to call for this class from a validation driver. 
	 * @return
	 */
	public final ValidationRuleResult evaluate(T target, MessageReceived m) {
		try {
			 return executeRule(target, m);
		} catch (Exception e) {
			LOGGER.error("Error running rule - " + this.getClass() + " problem: " + e.getMessage(), e);
			IssueFound[] issues = new IssueFound[]{PotentialIssue.GeneralProcessingException.build(this.getClass().getName())};
			return buildResults(Arrays.asList(issues), false);
		}
	}
	
	/**
	 * This builds a results object and determines if issues 
	 * represent a failure or a pass. 
	 * @param issues
	 * @return
	 */
	protected ValidationRuleResult buildResults(List<IssueFound> issues) {
		return buildResults(issues, this.util.hasErrors(issues));
	}

	/**
	 * This builds a results object and determines if issues 
	 * represent a failure or a pass. 
	 * @param issues
	 * @return
	 */
	protected ValidationRuleResult buildResults(List<IssueFound> issues, boolean passed) {
		ValidationRuleResult result = new ValidationRuleResult();
		result.setRuleClass(this.getClass());
		result.setIssues(issues);
		result.setRulePassed(passed);
		return result;
	}
	
	/**
	 * This needs to be implemented for each rule.  The implementation should evaluate the rule, and 
	 * return a list of problems found during evaluation.  
	 */
	protected abstract ValidationRuleResult executeRule(T target, MessageReceived m);
	
	public boolean dependenciesAreMet(List<Class<? extends ValidationRule>> passedValidations) {
		Class[] dependencies = this.getDependencies();
		
		//If there are no dependencies, they are already met!
		if (dependencies == null || dependencies.length == 0) {
			return true;
		}
		
		//At this point there are dependencies.  If there aren't any passed, they're not met!
		if (passedValidations == null || passedValidations.size() <= 0) {
			return false;
		}

		//Now we have to make sure none are missing. 
		for (Class c : dependencies) {
			if (!passedValidations.contains(c)) {
				return false;
			}
			;
		}
		
//		At this point, none are missing! Dependencies are met!
		return true;
	}
}
