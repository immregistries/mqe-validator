package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientIsUnderage extends ValidationRule<MqePatient> {

  private static final Logger logger = LoggerFactory.getLogger(PatientIsUnderage.class);

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class, PatientBirthDateIsValid.class};
  }

  public PatientIsUnderage() {
    this.addRuleDetection(Detection.PatientBirthDateIsUnderage);
    ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthDateIsUnderage);id.setImplementationDescription("Patient is under 18 years old.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = false;

    if (/* protect first */target != null && target.getBirthDate() != null
        && m.getMessageHeader() != null && m.getMessageHeader().getMessageDate() != null) {

      DateTime eighteenYearsBeforeToday = (new DateTime()).minusYears(18);
      DateTime birthDate = new DateTime(target.getBirthDate());

      boolean underage = birthDate.isAfter(eighteenYearsBeforeToday);
      logger.info("Eighteen years before submission: " + datr.toString(eighteenYearsBeforeToday));
      logger.info("patient birth date: " + datr.toString(birthDate));

      if (underage) {
        issues.add(Detection.PatientBirthDateIsUnderage.build(datr.toString(birthDate), target));
        passed = true;
      }
    }

    return buildResults(issues, passed);
  }
}
