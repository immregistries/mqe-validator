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
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientImmunityCodeIsDeprecated);
      id.setHowToFix("Contact your software vendor and ask them to ensure that immunity information is encoded properly in the message. ");
      id.setWhyToFix("Patient immunities can change recommendation of next vaccinations due, so ideally patient immunities would be included "
          + "in a complete vaccination record. ");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientImmunityCodeIsUnrecognized);
      id.setImplementationDescription(
          "Code submitted is not recognized as either valid or invalid because it is unknown to this system. ");
      id.setHowToFix("Contact your software vendor and ask them to ensure that immunity information is encoded properly in the message. ");
      id.setWhyToFix("Patient immunities can change recommendation of next vaccinations due so ideally would be included "
          + "in a complete vaccination record. ");
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
