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
import org.immregistries.mqe.vxu.VxuField;
import org.immregistries.mqe.vxu.hl7.Observation;

/**
 * Created by Allison on 5/9/2017.
 */
public class ObservationValueTypeIsValid extends ValidationRule<MqeVaccination> {

  public ObservationValueTypeIsValid() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.ObservationValueTypeIsDeprecated);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.ObservationValueTypeIsInvalid);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.ObservationValueTypeIsMissing);
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.ObservationValueTypeIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeVaccination target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    for (Observation o : target.getObservations()) {
      issues.addAll(codr.handleCodeOrMissing(o.getValueTypeCode(), VxuField.OBSERVATION_VALUE_TYPE, target));
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }
}
