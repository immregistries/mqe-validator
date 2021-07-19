package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.immregistries.mqe.validator.address.SmartyStreetResponse;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientResponsiblePartyIsProperlyFormed extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientHasResponsibleParty.class};
  }

  public PatientResponsiblePartyIsProperlyFormed() {
    this.addRuleDetection(Detection.PatientGuardianAddressStateIsMissing);
    this.addRuleDetection(Detection.PatientGuardianAddressStateIsPresent);
    this.addRuleDetection(Detection.PatientGuardianAddressCityIsMissing);
    this.addRuleDetection(Detection.PatientGuardianAddressCityIsPresent);
    this.addRuleDetection(Detection.PatientGuardianAddressZipIsMissing);
    this.addRuleDetection(Detection.PatientGuardianAddressZipIsPresent);
    this.addRuleDetection(Detection.PatientGuardianNameFirstIsMissing);
    this.addRuleDetection(Detection.PatientGuardianNameFirstIsPresent);
    this.addRuleDetection(Detection.PatientGuardianNameLastIsMissing);
    this.addRuleDetection(Detection.PatientGuardianNameLastIsPresent);

    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientGuardianNameIsSameAsUnderagePatient);
      id.setImplementationDescription(
          "Patient first and last name match the guardian first and last name.");
    }
    this.addRuleDetection(Detection.PatientGuardianPhoneIsMissing);
    this.addRuleDetection(Detection.PatientGuardianPhoneIsPresent);


    if (props.isAddressCleanserEnabled()) {
      this.addRuleDetection(Detection.PatientGuardianAddressIsInvalid);
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived mr) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    if (target.getResponsibleParty() != null) {
      MqeNextOfKin guardian = target.getResponsibleParty();

      String tFirst = guardian.getNameFirst();
      String tLast = guardian.getNameLast();
      String pFirst = target.getNameFirst();
      String pLast = target.getNameLast();

      if (props.isAddressCleanserEnabled()) {
        if (guardian.getAddress() != null && !guardian.getAddress().isClean()) {
          ValidationReport r = Detection.PatientGuardianAddressIsInvalid.build(target);
          List<SmartyStreetResponse> rList =
              SmartyStreetResponse.codesFromDpv(guardian.getAddress().getCleansingResultCode());
          if (rList.size() > 0) {
            StringBuilder b = new StringBuilder(":");
            for (SmartyStreetResponse rz : rList) {
              b.append(" ").append(rz.title);
            }
            r.setAdditionalMessage(b.toString());
          }
          issues.add(r);
        }
      }
      if (guardian.getAddress() != null) {
        issues.add(Detection.PatientGuardianAddressIsPresent.build(target));
        if (this.common.isEmpty(guardian.getAddress().getStateCode())) {
          issues.add(Detection.PatientGuardianAddressStateIsMissing.build(target));
        } else {
          issues.add(Detection.PatientGuardianAddressStateIsPresent.build(target));
        }
        if (this.common.isEmpty(guardian.getAddress().getCity())) {
          issues.add(Detection.PatientGuardianAddressCityIsMissing.build(target));
        } else {
          issues.add(Detection.PatientGuardianAddressCityIsPresent.build(target));
        }
        if (this.common.isEmpty(guardian.getAddress().getZip())) {
          issues.add(Detection.PatientGuardianAddressZipIsMissing.build(target));
        } else {
          issues.add(Detection.PatientGuardianAddressZipIsPresent.build(target));
        }
      } else {
        issues.add(Detection.PatientGuardianAddressIsMissing.build(target));
      }

      if (this.common.isEmpty(tFirst)) {
        issues.add(Detection.PatientGuardianNameFirstIsMissing.build(target));
      } else {
        issues.add(Detection.PatientGuardianNameFirstIsPresent.build(target));
      }
      if (this.common.isEmpty(tLast)) {
        issues.add(Detection.PatientGuardianNameLastIsMissing.build(target));
      } else {
        issues.add(Detection.PatientGuardianNameLastIsPresent.build(target));
      }

      if (StringUtils.isNotBlank(pFirst) && StringUtils.isNotBlank(pLast) && pFirst.equals(tFirst)
          && pLast.equals(tLast)) {
        issues.add(Detection.PatientGuardianNameIsSameAsUnderagePatient.build(target));
      }

      if (this.common.isEmpty(guardian.getPhoneNumber())) {
        issues.add(Detection.PatientGuardianPhoneIsMissing.build(target));
      } else {
        issues.add(Detection.PatientGuardianPhoneIsPresent.build(target)); 
      }

      passed = verifyNoIssuesExceptPresent(issues);

    }

    return buildResults(issues, passed);
  }

}
