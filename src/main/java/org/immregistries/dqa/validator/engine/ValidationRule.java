package org.immregistries.dqa.validator.engine;

import org.immregistries.dqa.core.util.DateUtility;
import org.immregistries.dqa.validator.engine.codes.CodeRepository;
import org.immregistries.dqa.validator.engine.common.CodeHandler;
import org.immregistries.dqa.validator.engine.common.CommonRules;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * A pass of a Validation Rule means that the concept expressed in the name is true.  
 * @author Josh
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public abstract class ValidationRule<T> {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ValidationRule.class);
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
	public final ValidationRuleResult evaluate(T target, DqaMessageReceived m) {
		try {
			 return executeRule(target, m);
		} catch (Exception e) {
			LOGGER.error("Error running rule - " + this.getClass() + " problem: " + e.getMessage(), e);
			ValidationIssue[] issues = new ValidationIssue[]{Detection.GeneralProcessingException.build(this.getClass().getName())};
			return buildResults(Arrays.asList(issues), false);
		}
	}
	
	/**
	 * This builds a results object and determines if issues 
	 * represent a failure or a pass. 
	 * @param issues
	 * @return
	 */
	protected ValidationRuleResult buildResults(List<ValidationIssue> issues) {
		return buildResults(issues, this.util.hasErrors(issues));
	}

	/**
	 * This builds a results object and determines if issues 
	 * represent a failure or a pass. 
	 * @param issues
	 * @return
	 */
	protected ValidationRuleResult buildResults(List<ValidationIssue> issues, boolean passed) {
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
	protected abstract ValidationRuleResult executeRule(T target, DqaMessageReceived m);
	
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
