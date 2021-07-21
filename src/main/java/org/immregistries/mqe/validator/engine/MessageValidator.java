package org.immregistries.mqe.validator.engine;

import java.util.*;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.MqeCode;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntityLists;
import org.immregistries.mqe.vxu.MqeMessageReceived;

public enum MessageValidator {
  INSTANCE;

  private final ValidationRunner runner = ValidationRunner.INSTANCE;
  private final ValidationUtility util = ValidationUtility.INSTANCE;
  private final RulePairBuilder builder = RulePairBuilder.INSTANCE;

  public void configure(Set<MqeCode> codes) {
    builder.setActiveDetections(codes);
  }

  /**
   * The driving method for executing the whole set of validations for an entire message.
   *
   * @param m A Set of objects that represents a vaccination update message.
   * @return a list of validation results.
   */
  public List<ValidationRuleResult> validateMessage(MqeMessageReceived m) {
    // first validate the high order elements of the message:
    List<ValidationRuleResult> headerResults = validateMessageHeader(m);
    // Generate a list of passed classes from the results:
    List<Class> headerPassed = util.getPassedFromResults(headerResults);

    List<Class> allPassed = new ArrayList<>(headerPassed);
    // first validate the high order elements of the message:
    List<ValidationRuleResult> patientResults = validatePatient(m, headerPassed);
    // Generate a list of passed classes from the results:
    List<Class> patientPassed = util.getPassedFromResults(patientResults);
    allPassed.addAll(patientPassed);

    // Then validate the list items. The reasons they are treated separately is described elsewhere.
    List<ValidationRuleResult> listEntityResults = validateListItems(m, allPassed);

    // Then add them all together.
    List<ValidationRuleResult> validationResults = new ArrayList<ValidationRuleResult>();
    validationResults.addAll(headerResults);
    validationResults.addAll(patientResults);
    validationResults.addAll(listEntityResults);

    return validationResults;
  }

  protected List<ValidationRuleResult> validateMessageHeader(MqeMessageReceived m) {
    List<ValidationRulePair> headerRules = builder.buildMessageHeaderRulePairs(m.getMessageHeader(), m);
    return runner.processValidationRules(headerRules, new ArrayList<Class>());
  }

  protected List<ValidationRuleResult> validatePatient(MqeMessageReceived m, List<Class> passedPreviously) {
    List<ValidationRulePair> headerRules = builder.buildPatientRulePairs(m.getPatient(), m);
    return runner.processValidationRules(headerRules, passedPreviously);
  }

  protected List<ValidationRuleResult> validateListItems(MqeMessageReceived m,
      List<Class> mainPassed) {
    List<List<ValidationRulePair>> listEntityRuleLists = builder.buildListItemRuleLists(m);

    List<ValidationRuleResult> listRuleResults = new ArrayList<>();

    for (List<ValidationRulePair> rules : listEntityRuleLists) {
      List<ValidationRuleResult> results = runner.processValidationRules(rules, mainPassed);
      listRuleResults.addAll(results);
    }

    return listRuleResults;
  }
  
	protected List<ValidationRuleResult> validatePatient(MqeMessageReceived m) {
		List<ValidationRulePair> patientRules = builder.buildPatientRulePairs(m.getPatient(), m);
    return runner.processValidationRules(patientRules, new ArrayList<>());
	}
  
	public List<ValidationRuleResult> validateMessageNIST(MqeMessageReceived m) {
		
		//first validate the high order elements of the message: 
		List<ValidationRuleResult> headerAndPatientResults = validatePatient(m);
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

    public static Set<Detection> activeDetections() {
        Set<Detection> active = new HashSet();
        Iterator var1 = ValidationRuleEntityLists.PATIENT_RULES.getRules().iterator();

        ValidationRule r;
        while(var1.hasNext()) {
            r = (ValidationRule)var1.next();
            active.addAll(r.getRuleDetections());
        }

        var1 = ValidationRuleEntityLists.NEXT_OF_KIN_RULES.getRules().iterator();

        while(var1.hasNext()) {
            r = (ValidationRule)var1.next();
            active.addAll(r.getRuleDetections());
        }

        var1 = ValidationRuleEntityLists.VACCINATION_RULES.getRules().iterator();

        while(var1.hasNext()) {
            r = (ValidationRule)var1.next();
            active.addAll(r.getRuleDetections());
        }

        //Filter out detections that aren't set as "active" in the configuration file (that we make up)

        return active;
    }

}
