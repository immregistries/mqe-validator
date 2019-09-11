package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.PatientImmunity;
import org.immregistries.mqe.vxu.VxuField;

public class PatientImmunityIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {
        // PatientBirthDateIsValid.class
    };
  }

  public PatientImmunityIsValid() {
    this.addRuleDetections(codr.getDetectionsForField(VxuField.PATIENT_IMMUNITY_CODE));
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientImmunityCodeIsDeprecated);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");// TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientImmunityCodeIsUnrecognized);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");// TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    for (PatientImmunity imm : target.getPatientImmunityList()) {
      String immCode = imm.getCode();
      issues.addAll(codr.handleCode(immCode, VxuField.PATIENT_IMMUNITY_CODE, target));
    }

    passed = issues.isEmpty();

    return buildResults(issues, passed);
  }

}
