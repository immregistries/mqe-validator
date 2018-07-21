package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.validator.engine.codes.KnowNameList;
import org.immregistries.mqe.validator.engine.codes.KnownName;
import org.immregistries.mqe.validator.engine.codes.KnownName.NameType;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientNameIsValid extends ValidationRule<MqePatient> {

  private KnowNameList listr = KnowNameList.INSTANCE;

  public PatientNameIsValid() {
    ruleDetections.addAll(Arrays.asList(Detection.PatientNameFirstIsMissing,
        Detection.PatientNameFirstIsInvalid, Detection.PatientNameFirstMayIncludeMiddleInitial,
        Detection.PatientNameLastIsMissing, Detection.PatientNameLastIsInvalid,
        Detection.PatientNameMayBeTemporaryNewbornName, Detection.PatientNameMayBeTestName,
        Detection.PatientNameHasJunkName));
  }


  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    String first = target.getNameFirst();
    String last = target.getNameLast();
    String middle = target.getNameMiddle();

    List<KnownName> invalidNames = KnowNameList.INSTANCE.getKnownNames(NameType.INVALID_NAME);

    // First Name Issues:
    if (this.common.isEmpty(first)) {
      issues.add(Detection.PatientNameFirstIsMissing.build(first, target));
      passed = false;
    } else {
      if (listr.firstNameOnlyMatch(NameType.INVALID_NAME, first)) {
        issues.add(Detection.PatientNameFirstIsInvalid.build(first, target));
        passed = false;
      }

      if (!common.isValidNameChars(first)) {
        issues.add(Detection.PatientNameFirstIsInvalid.build(first, target));
        passed = false;
      }

      if (first.length() > 3 && this.common.isEmpty(target.getNameMiddle())) {
        int pos = first.lastIndexOf(' ');
        if (pos > -1 && pos == first.length() - 2) {
          issues.add(Detection.PatientNameFirstMayIncludeMiddleInitial.build(first, target));
        }
      }
    }

    // Last Name Issues:
    if (this.common.isEmpty(last)) {
      issues.add(Detection.PatientNameLastIsMissing.build(last, target));
      passed = false;
    } else {
      if (listr.lastNameOnlyMatch(NameType.INVALID_NAME, last)) {
        issues.add(Detection.PatientNameLastIsInvalid.build(target));
        passed = false;
      }

      if (!common.isValidNameChars(last)) {
        issues.add(Detection.PatientNameLastIsInvalid.build(target));
      }

    }

    if (listr.matches(NameType.UNNAMED_NEWBORN, first, last, middle)) {
      issues.add(Detection.PatientNameMayBeTemporaryNewbornName.build("first[" + first
          + "] middle[" + middle + "] last[" + last, target));
    }

    if (listr.matches(NameType.TEST_PATIENT, first, last, middle)) {
      issues.add(Detection.PatientNameMayBeTestName.build(target));
    }

    if (listr.matches(NameType.JUNK_NAME, first, last, middle)) {
      issues.add(Detection.PatientNameHasJunkName.build(target));
    }

    // ALL OF THIS SHOULD BE IN THE EXTRACT/TRANSFORM LAYER:
    // specialNameHandling1(patient.getName());
    // specialNameHandling2(patient.getName());
    // specialNameHandling3(patient.getName());
    // specialNameHandling5(patient.getName());
    // specialNameHandling6(patient.getName());
    // specialNameHandling7(patient.getName());

    return buildResults(issues, passed);
  }

}
