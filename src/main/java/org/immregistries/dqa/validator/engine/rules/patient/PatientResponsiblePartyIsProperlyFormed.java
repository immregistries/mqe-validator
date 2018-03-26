package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationReport;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientResponsiblePartyIsProperlyFormed extends ValidationRule<DqaPatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[]{PatientHasResponsibleParty.class};
  }

  public PatientResponsiblePartyIsProperlyFormed() {
    ruleDetections.addAll(Arrays.asList(
        Detection.PatientGuardianAddressStateIsMissing,
        Detection.PatientGuardianAddressCityIsMissing,
        Detection.PatientGuardianAddressZipIsMissing,
        Detection.PatientGuardianNameFirstIsMissing,
        Detection.PatientGuardianNameLastIsMissing,
        Detection.PatientGuardianNameIsMissing,
        Detection.PatientGuardianNameIsSameAsUnderagePatient,
        Detection.PatientGuardianPhoneIsMissing,
        Detection.PatientGuardianRelationshipIsMissing,
        Detection.PatientGuardianResponsiblePartyIsMissing));
  }

  @Override
  protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived mr) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target.getResponsibleParty() != null) {
      DqaNextOfKin guardian = target.getResponsibleParty();

      String tFirst = guardian.getNameFirst();
      String tLast = guardian.getNameLast();
      String pFirst = target.getNameFirst();
      String pLast = target.getNameLast();

      if (this.common.isEmpty(guardian.getAddress().getStateCode())) {
        issues.add(Detection.PatientGuardianAddressStateIsMissing.build(target));
      }
      if (this.common.isEmpty(guardian.getAddress().getCity())) {
        issues.add(Detection.PatientGuardianAddressCityIsMissing.build(target));
      }
      if (this.common.isEmpty(guardian.getAddress().getZip())) {
        issues.add(Detection.PatientGuardianAddressZipIsMissing.build(target));
      }
      if (this.common.isEmpty(tFirst)) {
        issues.add(Detection.PatientGuardianNameFirstIsMissing.build(target));
      }
      if (this.common.isEmpty(tLast)) {
        issues.add(Detection.PatientGuardianNameLastIsMissing.build(target));
      }

      if (this.common.isEmpty(tFirst) || this.common.isEmpty(tLast)) {
        issues.add(Detection.PatientGuardianNameIsMissing.build(target));
      }

      if (StringUtils.isNotBlank(pFirst) && StringUtils.isNotBlank(pLast) && pFirst.equals(tFirst)
          && pLast.equals(tLast)) {
        issues.add(Detection.PatientGuardianNameIsSameAsUnderagePatient.build(target));
      }

      if (this.common.isEmpty(guardian.getPhoneNumber())) {
        issues.add(Detection.PatientGuardianPhoneIsMissing.build(target));
      }
      if (this.common.isEmpty(guardian.getRelationshipCode())) {//This shouldn't be possible...
        issues.add(Detection.PatientGuardianRelationshipIsMissing.build(target));
      }

      passed = (issues.size() == 0);

    }

    return buildResults(issues, passed);
  }

}
