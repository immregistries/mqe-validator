package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
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
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.NextOfKinRelationshipIsUnrecognized);
      id.setImplementationDescription("Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("The next-of-kin relationship is not set. Please contact your software vendor and request that the next-of-kin relationship always be set on every next-of-kin reported to the IIS. Only next-of-kin that have a relationship with the patient should be submitted to the IIS.   ");
      id.setWhyToFix("The relationship helps the IIS understand the reason why this person is linked to the patient and to presume the role they play in the care of the patient. The IIS may use this information for patient matching and to identify the person who should be contacted for reminder/recall activities. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinRelationshipIsMissing);
      id.setHowToFix("The next-of-kin relationship is not a recognized value. Please contact your software vendor and request that the next-of-kin relationship always be set properly on every next-of-kin reported to the IIS. ");
      id.setWhyToFix("The relationship helps the IIS understand the reason why this person is linked to the patient and to presume the role they play in the care of the patient. The IIS may use this information for patient matching and to identify the person who should be contacted for reminder/recall activities. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.NextOfKinRelationshipIsUnexpected);
      id.setImplementationDescription(
          "An underage patient is not expecting a next of kin that is a child, step child, or foster child.");
      id.setHowToFix("An underage patient (minor child) has a next-of-kin with a relationship that is not expected for a child. The relationship in some systems can be expressed backwards from the messaging standard. Please verify that the relationship is being entered in the correct direction in the user interface and if it is then contact your software vendor to request that they transmit this relationship in the correct direction during messaging. For example, if you record on the next-of-kin that the patient is a 'child' of the next-of-kin then this should be transmitted to the IIS as the next-of-kin having a 'guardian', 'mother', or 'father' relationship with the patient. ");
      id.setWhyToFix("The guardian/parent of the patient is used for patient matching and reminder/recall. Reversing the relationship can cause the IIS to not select this record for these purposes. Ensuring that the data is coded correctly according to the standard will ensure high data quality and less confusion. ");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.NextOfKinRelationshipIsNotResponsibleParty);
      id.setImplementationDescription(
          "The next of kin for an underage patient is expected to be a caregiver, father, grandparent, mother, parent, or guardian.");
      id.setHowToFix("Please ensure that the next-of-kin is properly indicated. ");
      id.setWhyToFix("Some IIS may not recognize this next-of-kin as the guardian/parent and will not record this.");
    }
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
