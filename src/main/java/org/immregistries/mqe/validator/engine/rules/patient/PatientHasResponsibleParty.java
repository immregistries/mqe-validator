package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.rules.ValidationRuleEntry;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.TargetType;

@ValidationRuleEntry(TargetType.Patient)
public class PatientHasResponsibleParty extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientExists.class};
  }

  public PatientHasResponsibleParty() {
    ImplementationDetail id =
        this.addRuleDetection(Detection.PatientGuardianResponsiblePartyIsMissing);
    id.setImplementationDescription(
        "Responsible party missing and/or responsible party relationship code missing.");
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
