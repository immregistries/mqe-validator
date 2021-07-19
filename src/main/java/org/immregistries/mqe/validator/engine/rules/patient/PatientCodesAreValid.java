package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

/**
 * Covers several cases. Looks like a catchall for codes that don't have a separate rule
 */
public class PatientCodesAreValid extends ValidationRule<MqePatient> {

  public PatientCodesAreValid() {
    this.addRuleDetection(Detection.PatientPrimaryLanguageIsMissing);
    this.addRuleDetection(Detection.PatientPrimaryLanguageIsPresent);
    this.addRuleDetection(Detection.PatientPrimaryLanguageIsUnrecognized);
    this.addRuleDetection(Detection.PatientPublicityCodeIsMissing);
    this.addRuleDetection(Detection.PatientPublicityCodeIsPresent);
    this.addRuleDetection(Detection.PatientPublicityCodeIsUnrecognized);
    this.addRuleDetection(Detection.PatientPublicityCodeIsInvalid);
    this.addRuleDetection(Detection.PatientRaceIsMissing);
    this.addRuleDetection(Detection.PatientRaceIsPresent);
    this.addRuleDetection(Detection.PatientRaceIsDeprecated);
    this.addRuleDetection(Detection.PatientRaceIsUnrecognized);
    this.addRuleDetection(Detection.PatientRaceIsInvalid);
    this.addRuleDetection(Detection.PatientVfcStatusIsDeprecated);
    this.addRuleDetection(Detection.PatientVfcStatusIsInvalid);
    this.addRuleDetection(Detection.PatientVfcStatusIsMissing);
    this.addRuleDetection(Detection.PatientVfcStatusIsPresent);
    this.addRuleDetection(Detection.PatientVfcStatusIsUnrecognized);
    this.addRuleDetection(Detection.PatientPrimaryFacilityIdIsMissing);
    this.addRuleDetection(Detection.PatientPrimaryFacilityIdIsPresent);
    this.addRuleDetection(Detection.PatientPrimaryFacilityNameIsMissing);
    this.addRuleDetection(Detection.PatientPrimaryFacilityNameIsPresent);
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    if (target == null) {
      return buildResults(issues, false);
    }

    // facility
    String facilityId = target.getFacilityIdNumber();
    String facilityName = target.getFacilityName();

    if (this.common.isEmpty(facilityId)) {
      issues.add(Detection.PatientPrimaryFacilityIdIsMissing.build((facilityId), target));
    } else {
      issues.add(Detection.PatientPrimaryFacilityIdIsPresent.build((facilityId), target));
    }
    

    if (this.common.isEmpty(facilityName)) {
      issues.add(Detection.PatientPrimaryFacilityNameIsMissing.build((facilityName), target));
    } else {
      issues.add(Detection.PatientPrimaryFacilityNameIsPresent.build((facilityName), target));      
    }

    // language
    issues.addAll(this.codr.handleCodeOrMissing(target.getPrimaryLanguage(),
        VxuField.PATIENT_PRIMARY_LANGUAGE, target));

    // physician
    // TODO: primary physician isn't in the list of validation rules at all...
    // issues.addAll(this.codr.handleCode(target.getPhysician().getFormattedNumber(),
    // VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, target));

    // publicity code
    issues.addAll(this.codr.handleCodeOrMissing(target.getPublicity(),
        VxuField.PATIENT_PUBLICITY_CODE, target));

    // race
    issues.addAll(this.codr.handleCodeOrMissing(target.getRace(), VxuField.PATIENT_RACE, target));

    // VFC/financial eligibility status
    issues.addAll(this.codr.handleCodeOrMissing(target.getFinancialEligibility(),
        VxuField.PATIENT_VFC_STATUS, target));

    // mark passed if there's no issues
    passed = verifyPassed(issues);
    return buildResults(issues, passed);
  }

}
