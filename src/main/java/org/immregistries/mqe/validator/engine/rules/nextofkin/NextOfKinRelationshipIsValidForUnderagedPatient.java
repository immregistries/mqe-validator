package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.code.NokRelationship;

public class NextOfKinRelationshipIsValidForUnderagedPatient extends ValidationRule<MqeNextOfKin> {

  @Override
  protected final Class[] getDependencies() {
    // TODO: needs to be tested from the frontend once PatientIsUnderage is working
    return new Class[] {PatientIsUnderage.class};
  }

  public NextOfKinRelationshipIsValidForUnderagedPatient() {
    this.addRuleDocumentation(Arrays.asList(Detection.NextOfKinRelationshipIsUnexpected,
        Detection.NextOfKinRelationshipIsUnrecognized,
        Detection.NextOfKinRelationshipIsNotResponsibleParty,
        Detection.NextOfKinRelationshipIsMissing));
    this.addImplementationMessage(Detection.NextOfKinRelationshipIsUnexpected, "An underage patient is not expecting a next of kin that is a child, step child, or foster child.");
    this.addImplementationMessage(Detection.NextOfKinRelationshipIsNotResponsibleParty, "The next of kin for an underage patient is expected to be a caregiver, father, grandparent, mother, parent, or guardian.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    String relationship = target.getRelationshipCode();

    if (StringUtils.isNotBlank(relationship)) {
      if (!target.isResponsibleRelationship()) {
        if (target.isChildRelationship()) {
          // In most situations, an underage patient would not have a child, so this is unexpected
          // (and
          // probably indicates the relationship was entered backwards). However, it's not
          // impossible, so
          // it's just a warning.
          issues.add(Detection.NextOfKinRelationshipIsUnexpected.build((relationship), target));
        } else if (NokRelationship.get(relationship) == NokRelationship.UNKNOWN) {
          issues.add(Detection.NextOfKinRelationshipIsUnrecognized.build((relationship), target));
        } else {
          issues.add(Detection.NextOfKinRelationshipIsNotResponsibleParty.build((relationship),
              target));
        }
      }
    } else {
      issues.add(Detection.NextOfKinRelationshipIsMissing.build((relationship), target));
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }
}
