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

public class PatientBirthDateIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientBirthDateIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsInvalid);
      id.setImplementationDescription("Patient Birth date cannot be translated to a date.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String birthDateString = target.getBirthDateString();

    if (this.common.isEmpty(birthDateString)) {
      issues.add(Detection.PatientBirthDateIsMissing.build((birthDateString), target));
      passed = false;
    } else if (!this.common.isValidDate(birthDateString)) {
      issues.add(Detection.PatientBirthDateIsInvalid.build((birthDateString), target));
      passed = false;
    }

    return buildResults(issues, passed);
  }

}
