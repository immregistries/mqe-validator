package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientHasResponsibleParty extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientHasResponsibleParty() {
    this.addRuleDetection(Detection.PatientGuardianResponsiblePartyIsMissing);
    ImplementationDetail id =
        this.addRuleDetection(Detection.PatientGuardianResponsiblePartyIsMissing);
    id.setImplementationDescription(
        "Responsible party missing and/or responsible party relationship code missing.");
    id.setHowToFix("The patient responsible party which is a parent or guardian was not found in the message for a minor-aged patient. "
        + "Please ensure that the guardian/parent is recorded for this patient or "
        + "ask your software vendor to include the guardian/parent in all messages when available. ");
    id.setWhyToFix("Patient matching uses the guardian information to improve patient matching and identify the person that should be "
        + "contacted during reminder/recall activities. ");
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived mr) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target.getResponsibleParty() == null
        || StringUtils.isBlank(target.getResponsibleParty().getRelationshipCode())) {
      issues.add(Detection.PatientGuardianResponsiblePartyIsMissing.build(target));
      passed = false;
    }

    return buildResults(issues, passed);
  }
}
