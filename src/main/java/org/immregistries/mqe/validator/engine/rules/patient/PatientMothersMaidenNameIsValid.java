package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.codes.KnowNameList;
import org.immregistries.mqe.validator.engine.codes.KnownName.NameType;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientMothersMaidenNameIsValid extends ValidationRule<MqePatient> {

  private KnowNameList listr = KnowNameList.INSTANCE;

  public PatientMothersMaidenNameIsValid() {
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientMotherSMaidenNameIsMissing);
      // TODO Complete ImplementationDescription
      id.setImplementationDescription("");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientMothersMaidenNameIsInvalid);
      id.setImplementationDescription(
          "Patient maiden name must not be on the specified invalid name list ('X','UN','UK','UNK', 'UNKN', 'NONE').");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientMotherSMaidenNameHasJunkName);
      id.setImplementationDescription(
          "Patient maiden name must not be on the specified junk name list ('UNKNOWN','NONE','NO LAST NAM','NO LAST NAME', 'NONAME', 'NO NAME', 'EMPTY', 'MISSING').");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientMotherSMaidenNameHasInvalidPrefixes);
      id.setImplementationDescription(
          "Patient maiden name must not be on the specified invalid prefixes name list ('ZZ','XX').");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientMotherSMaidenNameIsTooShort);
      id.setImplementationDescription(
          "Patient maiden name must be more than 1 character in length.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;
    String mmName = target.getMotherMaidenName();

    if (this.common.isEmpty(mmName)) {
      issues.add(Detection.PatientMotherSMaidenNameIsMissing.build(target));
      passed = false;
    } else {
      if (listr.lastNameOnlyMatch(NameType.INVALID_NAME, mmName)) {
        issues.add(Detection.PatientMothersMaidenNameIsInvalid.build(target));
        passed = false;
      } else if (listr.lastNameOnlyMatch(NameType.JUNK_NAME, mmName)) {
        issues.add(Detection.PatientMotherSMaidenNameHasJunkName.build(target));
        passed = false;
      } else if (listr.lastNameOnlyMatch(NameType.INVALID_PREFIXES, mmName)) {
        issues.add(Detection.PatientMotherSMaidenNameHasInvalidPrefixes.build(target));
        passed = false;
      }

      if (mmName.length() == 1) {
        issues.add(Detection.PatientMotherSMaidenNameIsTooShort.build(target));
        passed = false;
      }

    }

    return buildResults(issues, passed);
  }
}
