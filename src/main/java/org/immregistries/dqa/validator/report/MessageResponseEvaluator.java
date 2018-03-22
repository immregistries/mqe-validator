package org.immregistries.dqa.validator.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.MessageObject;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.rules.nextofkin.NextOfKinIsPresent;
import org.immregistries.dqa.validator.engine.rules.vaccination.VaccinationIsPresent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MessageResponseEvaluator {
  INSTANCE;
  private static final Logger logger = LoggerFactory.getLogger(MessageResponseEvaluator.class);

  public DqaMessageMetrics toMetrics(List<ValidationRuleResult> ruleResults) {
    DqaMessageMetrics metrics = new DqaMessageMetrics();
    Map<Detection, Integer> attributeCounts = makeCountMap(ruleResults);
    metrics.setAttributeCounts(attributeCounts);
    metrics.getObjectCounts().putAll(makeObjectCounts(ruleResults));
    logger.info(metrics.toString());
    return metrics;
  }

  protected Map<MessageObject, Integer> makeObjectCounts(List<ValidationRuleResult> results) {
    Map<MessageObject, Integer> objCounts = new HashMap<MessageObject, Integer>();

    objCounts.put(MessageObject.PATIENT, 1);
    objCounts.put(MessageObject.MESSAGE_HEADER, 1);

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

    objCounts.put(MessageObject.VACCINATION, vaccCount);
    objCounts.put(MessageObject.NEXT_OF_KIN, nokCount);
    return objCounts;
  }

  protected Map<Detection, Integer> makeCountMap(List<ValidationRuleResult> results) {
    Map<Detection, Integer> map = new HashMap<>();
    for (ValidationRuleResult result : results) {
      List<ValidationReport> issues = result.getValidationDetections();
      for (ValidationReport issue : issues) {
        Detection attr = issue.getDetection();
        Integer cnt = map.get(attr);
        if (cnt == null) {
          map.put(attr, 1);
        } else {
          map.put(attr, cnt + 1);
        }
      }
    }

    return map;
  }
}
