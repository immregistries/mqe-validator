package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.validator.engine.rules.patient.PatientIsUnderage;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.TargetType;
import org.immregistries.mqe.vxu.code.NokRelationship;

@ValidationRuleEntry(TargetType.NextOfKin)
public class NextOfKinRelationshipIsValidForUnderagedPatient extends ValidationRule<MqeNextOfKin> {

  @Override
  protected final Class[] getDependencies() {
    // TODO: needs to be tested from the frontend once PatientIsUnderage is working
    return new Class[] {PatientIsUnderage.class};
  }

  public NextOfKinRelationshipIsValidForUnderagedPatient() {
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.NextOfKinRelationshipIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinRelationshipIsMissing);
      id.setImplementationDescription("Next of Kin Relationship is not indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinRelationshipIsPresent);
      id.setImplementationDescription("Next of Kin Relationship is indicated");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinRelationshipIsUnexpected);
      id.setImplementationDescription(
          "An underage patient is not expecting a next of kin that is a child, step child, or foster child.");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.NextOfKinRelationshipIsNotResponsibleParty);
      id.setImplementationDescription(
          "The next of kin for an underage patient is expected to be a caregiver, father, grandparent, mother, parent, or guardian.");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    String relationship = target.getRelationshipCode();

    if (StringUtils.isNotBlank(relationship)) {
      issues.add(Detection.NextOfKinRelationshipIsPresent.build((relationship), target));
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
          issues.add(
              Detection.NextOfKinRelationshipIsNotResponsibleParty.build((relationship), target));
        }
      }
    } else {
      issues.add(Detection.NextOfKinRelationshipIsMissing.build((relationship), target));
    }

    passed = issues.size() == 0;

    return buildResults(issues, passed);
  }
}
