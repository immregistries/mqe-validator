package org.immregistries.mqe.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.hl7.Observation;

/**
 * Currently only checks if the date is present or missing. Created by Allison on 5/9/2017.
 */
public class ObservationDateIsValid extends ValidationRule<MqeVaccination> {

  public ObservationDateIsValid() {
    this.addRuleDetection(Detection.ObservationDateTimeOfObservationIsMissing);
    this.addRuleDetection(Detection.ObservationDateTimeOfObservationIsInvalid);
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.ObservationDateTimeOfObservationIsMissing);
      id.setImplementationDescription("Observation Date/Time of Observation is not indicated");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.ObservationDateTimeOfObservationIsPresent);
      id.setImplementationDescription("Observation Date/Time of Observation is indicated");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.ObservationDateTimeOfObservationIsInvalid);
      id.setImplementationDescription("Observation date cannot be translated to a date.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    for (Observation o : target.getObservations()) {
      String observationDateString = o.getObservationDateString();
      if (this.common.isEmpty(observationDateString)) {
        issues.add(Detection.ObservationDateTimeOfObservationIsMissing
            .build((observationDateString), target));
      } else {
        issues.add(Detection.ObservationDateTimeOfObservationIsPresent
            .build((observationDateString), target));
        if (!this.common.isValidDate(observationDateString)) {
          issues.add(Detection.ObservationDateTimeOfObservationIsInvalid
              .build((observationDateString), target));
        }
      }
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }
}
