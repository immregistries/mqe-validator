package org.immregistries.mqe.validator.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.immregistries.mqe.hl7util.SeverityLevel;
import org.immregistries.mqe.util.validation.MqeValidatedObject;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.common.CommonRules;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.slf4j.Logger;

@SuppressWarnings("rawtypes")
public enum ValidationUtility {
  INSTANCE;

  private static final CommonRules common = CommonRules.INSTANCE;
  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ValidationUtility.class);

  /**
   * This method determines if the rule has passed, based on if any of the issues are at the error
   * level.
   */
  public boolean hasErrors(List<ValidationReport> issues) {
    if (issues != null && issues.size() > 0) {
      for (ValidationReport issue : issues) {
        if (issue.isError()) {
          return false;
        }
      }
    }

    return true;
  }

  public List<Class<? extends ValidationRule>> makeRuleList(
      Class<? extends ValidationRule>... classes) {
    return Arrays.asList(classes);
  }

  ;

  /**
   * This builds a list of passed rule classes from a list of validation results.
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

  public List<ValidationRulePair> buildRulePairs(List<ValidationRule> ruleList, MqeValidatedObject target,
      MqeMessageReceived mr) {
	
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

private ValidationRule getFinalRule(ValidationRule vr, HashMap<String, String> detectionsOverride) {
	Iterator<Detection> itr = vr.getRuleDetections().iterator();
	while (itr.hasNext()) {
		Detection detection = itr.next();
		String mqeCode = detection.getMqeMqeCode();
		if (detectionsOverride != null) {
			if (detectionsOverride.containsKey(mqeCode)) {
				String severityLabel = detectionsOverride.get(mqeCode);
				SeverityLevel severity = SeverityLevel.findByLabel(severityLabel);
				detection.setSeverity(severity);
			}
		}
	}
	return vr;
}

}
