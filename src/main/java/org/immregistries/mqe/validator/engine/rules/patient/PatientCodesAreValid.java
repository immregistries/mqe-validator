package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.VxuField;

/**
 * Covers several cases. Replaces patient ethnicity, gender, and name type validation classes.
 */
public class PatientCodesAreValid extends ValidationRule<MqePatient> {

  public PatientCodesAreValid() {
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.PATIENT_ETHNICITY));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.PATIENT_GENDER));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.PATIENT_NAME_TYPE_CODE));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.PATIENT_PRIMARY_LANGUAGE));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.PATIENT_PUBLICITY_CODE));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.PATIENT_RACE));
    this.addRuleDocumentation(codr.getDetectionsForField(VxuField.PATIENT_VFC_STATUS));
    this.addRuleDocumentation(Arrays.asList(Detection.PatientPrimaryFacilityIdIsMissing,
            Detection.PatientPrimaryFacilityNameIsMissing));
    
    this.addImplementationMessage(Detection.PatientNameTypeCodeIsNotValuedLegal, "Patient Name Type is not 'L' for legal.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<>();
    boolean passed;

    if (target == null) {
      return buildResults(issues, false);
    }

    issues.addAll(this.codr.handleCodeOrMissing(target.getEthnicity(), VxuField.PATIENT_ETHNICITY, target));
    issues.addAll(this.codr.handleCodeOrMissing(target.getSex(), VxuField.PATIENT_GENDER, target));

    // name type
    issues.addAll(this.codr.handleCodeOrMissing(target.getName().getType(), VxuField.PATIENT_NAME_TYPE_CODE,
        target));

    // name code is supposed to be L for legal
    if (!"L".equals(target.getNameTypeCode())) {
      issues.add(Detection.PatientNameTypeCodeIsNotValuedLegal.build(target));
    }

    // facility
    String facilityId = target.getFacilityIdNumber();
    String facilityName = target.getFacilityName();

    if (this.common.isEmpty(facilityId)) {
      issues.add(Detection.PatientPrimaryFacilityIdIsMissing.build((facilityId), target));
    }

    if (this.common.isEmpty(facilityName)) {
      issues.add(Detection.PatientPrimaryFacilityNameIsMissing.build((facilityName), target));
    }

    // language
    issues.addAll(this.codr.handleCodeOrMissing(target.getPrimaryLanguage(),
        VxuField.PATIENT_PRIMARY_LANGUAGE, target));

    // physician
    // TODO: primary physician isn't in the list of validation rules at all...
    // issues.addAll(this.codr.handleCode(target.getPhysician().getFormattedNumber(),
    // VxuField.PATIENT_PRIMARY_PHYSICIAN_ID, target));

    // publicity code
    issues.addAll(this.codr.handleCodeOrMissing(target.getPublicity(), VxuField.PATIENT_PUBLICITY_CODE,
        target));

    // race
    issues.addAll(this.codr.handleCodeOrMissing(target.getRace(), VxuField.PATIENT_RACE, target));

    // VFC/financial eligibility status
    issues.addAll(this.codr.handleCodeOrMissing(target.getFinancialEligibility(),
        VxuField.PATIENT_VFC_STATUS, target));

    // mark passed if there's no issues
    passed = (issues.size() == 0);
    return buildResults(issues, passed);
  }

}
