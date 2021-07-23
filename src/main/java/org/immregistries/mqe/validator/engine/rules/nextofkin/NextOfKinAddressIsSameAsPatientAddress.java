package org.immregistries.mqe.validator.engine.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.validator.engine.rules.patient.PatientExists;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.NextOfKin)
public class NextOfKinAddressIsSameAsPatientAddress extends ValidationRule<MqeNextOfKin> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class, NextOfKinAddressIsValid.class};
  }

  public NextOfKinAddressIsSameAsPatientAddress() {
    ImplementationDetail id =
        this.addRuleDetection(Detection.NextOfKinAddressIsDifferentFromPatientAddress);
    id.setImplementationDescription(
        "The City/State/Street/Street2 are different between Next Of Kin address and Patient address.");
  }


  @Override
  protected ValidationRuleResult executeRule(MqeNextOfKin target, MqeMessageReceived message) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    MqeAddress p = message.getPatient().getPatientAddress();
    MqeAddress n = target.getAddress();

    if ((p != null && n != null) && !p.equals(n)) {
      issues
          .add(Detection.NextOfKinAddressIsDifferentFromPatientAddress.build(n.toString(), target));
      passed = false;
    }

    return buildResults(issues, passed);
  }
}
