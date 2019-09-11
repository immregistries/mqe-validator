package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.patient.PatientNameIsValid;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;

public class NextOfKinNameIsNotSameAsPatient extends ValidationRule<MqeNextOfKin> {

  @Override
  protected final Class[] getDependencies() {

    return new Class[] {PatientNameIsValid.class, NextOfKinNameIsValid.class};
  }

  public NextOfKinNameIsNotSameAsPatient() {
    this.addRuleDetection(Detection.PatientGuardianNameIsSameAsUnderagePatient);
    ImplementationDetail id =
        this.addRuleDetection(Detection.PatientGuardianNameIsSameAsUnderagePatient);
    id.setImplementationDescription(
        "Next of Kin is a patient guardian (caregiver, father, grandparent, mother, parent, or guardian) and has same last name, first name, middle name, and suffix as the patient.");
    // TODO Complete HowToFix
    id.setHowToFix("");
    // TODO Complete WhyToFix
    id.setWhyToFix("");
  }

  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    MqePatient patient = m.getPatient();

    if (target.isResponsibleRelationship() && areEqual(target.getNameLast(), patient.getNameLast())
        && areEqual(target.getNameFirst(), patient.getNameFirst())
        && areEqual(target.getNameMiddle(), patient.getNameMiddle())
        && areEqual(target.getNameSuffix(), patient.getNameSuffix())) {

      issues.add(Detection.PatientGuardianNameIsSameAsUnderagePatient.build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }

  /**
   * Just a little hack.
   */
  protected boolean areEqual(String one, String two) {
    return one != null && one.equals(two);
  }

}
