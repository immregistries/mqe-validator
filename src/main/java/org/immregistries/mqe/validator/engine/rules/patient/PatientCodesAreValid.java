package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
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
    this.addRuleDetections(codr.getDetectionsForField(VxuField.PATIENT_PRIMARY_LANGUAGE));
    this.addRuleDetections(codr.getDetectionsForField(VxuField.PATIENT_PUBLICITY_CODE));
    this.addRuleDetections(codr.getDetectionsForField(VxuField.PATIENT_RACE));
    this.addRuleDetections(codr.getDetectionsForField(VxuField.PATIENT_VFC_STATUS));
    this.addRuleDetections(Arrays.asList(Detection.PatientPrimaryFacilityIdIsMissing,
        Detection.PatientPrimaryFacilityNameIsMissing));

    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPrimaryLanguageIsMissing);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientPrimaryLanguageIsUnrecognized);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPublicityCodeIsMissing);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPublicityCodeIsUnrecognized);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientPublicityCodeIsInvalid);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsMissing);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsDeprecated);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }

    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsUnrecognized);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientRaceIsInvalid);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsDeprecated);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsInvalid);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsMissing);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsUnrecognized);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientVfcStatusIsInvalid);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }



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
    issues.addAll(this.codr.handleCodeOrMissing(target.getPublicity(),
        VxuField.PATIENT_PUBLICITY_CODE, target));

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
