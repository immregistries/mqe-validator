package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.hl7.Observation;

/**
 * Currently only checks if the date is present or missing. Created by Allison on 5/9/2017.
 */
public class ObservationDateIsValid extends ValidationRule<DqaVaccination> {

  public ObservationDateIsValid() {
    ruleDetections.add(Detection.ObservationDateTimeOfObservationIsMissing);
  }

  @Override
  protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    for (Observation o : target.getObservations()) {
      String observationDateString = o.getObservationDateString();
      if (this.common.isEmpty(observationDateString)) {
        issues.add(Detection.ObservationDateTimeOfObservationIsMissing.build(
            (observationDateString), target));
      }
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }
}