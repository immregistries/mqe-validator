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

public class PatientBirthPlaceIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class,};
  }

  public PatientBirthPlaceIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientBirthPlaceIsMissing);
      id.setHowToFix("Please verify that the patient birth place is indicated on the patient medical record. If it is then contact your software vendor and request that the birth place be properly encoded in the message. ");
      id.setWhyToFix("The patien birth place may be used for patient matching and verification. Identifying unnamed children can be especially challanging and a birth place can help locate correct record matches. ");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived mr) {

    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String place = target.getBirthPlace();

    if (this.common.isEmpty(place)) {
      issues.add(Detection.PatientBirthPlaceIsMissing.build(target));
    }

    return buildResults(issues, passed);
  }


}
