package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
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
    this.addRuleDetections(
        Arrays.asList(Detection.PatientNameFirstIsMissing, Detection.PatientNameFirstIsInvalid,
            Detection.PatientNameFirstMayIncludeMiddleInitial, Detection.PatientNameLastIsMissing,
            Detection.PatientNameLastIsInvalid, Detection.PatientNameMiddleIsMissing,
            Detection.PatientNameMiddleIsInvalid, Detection.PatientNameMayBeTemporaryNewbornName,
            Detection.PatientNameMayBeTestName, Detection.PatientNameHasJunkName));
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientNameFirstIsInvalid);
      id.setImplementationDescription(
          "Patient first name must not be on the specified invalid name list ('X','U','UN','UK','UNK', 'UNKN', 'NONE').");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientNameLastIsInvalid);
      id.setImplementationDescription(
          "Patient last name must not be on the specified invalid name list ('X','U','UN','UK','UNK', 'UNKN', 'NONE').");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientNameMiddleIsInvalid);
      id.setImplementationDescription(
          "Patient middle name must not be on the specified invalid name list ('UN','UK','UNK', 'UNKN', 'NONE').");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientNameFirstMayIncludeMiddleInitial);
      id.setImplementationDescription(
          "Patient has first name but missing middle name. The first name has a space followed by a single character.");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id =
          this.addRuleDetection(Detection.PatientNameMayBeTemporaryNewbornName);
      id.setImplementationDescription(
          "Patient name must not be on the specified temporary newborn name list (BABY BOY, BABY GIRL, BABY (first name), NEWBORN (first name), BOY BABY, GIRL BABY)");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientNameMayBeTestName);
      id.setImplementationDescription(
          "Patient name must not be on the specified test name list (MICKY MOUSE, DONALD DUCK, TEST PATIENT, TEST(first or last name),  PATIENT(first or last name), BENJAMIN S PETERSON");
      // TODO Complete HowToFix
      id.setHowToFix("");
      // TODO Complete WhyToFix
      id.setWhyToFix("");
    }
    {
      ImplementationDetail id = this.addRuleDetection(Detection.PatientNameHasJunkName);
      id.setImplementationDescription(
          "Patient names must not be on the specified junk name list (first names: B1, G1, G2, UNNAMED, UNKNOWN, NONE, NOFIRSTNAME, NO FIRST NAME, NO FIRSTNAME, NONAME, NO NAME, EMPTY, MISSING, BABY, BABY BOY, BABY GIRL, GIRL, BOY, A BOY, A GIRL, ABABYGIRL, B BOY, B GIRL, BABY OY, BABY BAY, BABY BO, BABY BOY, BABY BOY 2, BABY BOY A, BABY BOY B, BABY BOY #1, BABY BOY 2, BABY BOY1, BABY GIRL 1, BABY GIRL B, BABY GIRL #1, BABY GIRL 1, BABY GIRL A, BABY GIRL B, BABY GIRL ONE, BABY GIRL1, BABY GRIL, BABY M, BABY SISTER, BABY-GIRL, BABYBOY, BABYBOY-1, BABYBOY-2, BABYBOYA, BABYGIR, BABYGIRL, BABYGIRL-A, BABYGIRL-B, BB, BBABYGIRL, BG, BOY #1, BOY #2, BOY 1, BOY 2, BOY 3, BOY A, BOY B, BOY ONE, BOY TWO, BOY+, C BOY, GIRL # 2, GIRL #2, GIRL (L), GIRL A, GIRL B, GIRL TWIN 2, GIRL#1, GIRL#2, TEST GIRL, TWIN BOY, TWIN GIRL A, B2, NEWBORN, TWIN GIRL, BABU GIRL TWIN, BABY BOY TWIN, BABY BOY 1, BBOY, BABY GIRL, BABY GIRL TWO, BABY 1, BABYGIRL A, BABYBOY 2, BBTWO, BBONE, BGONE, BGTWO, B-G, BG2, BG1, MALE, FEMALE) (middle names: UNKNOWN, NONE, NOMIDDLENAME, NO MIDDLE NAME, NO MIDDLENAME, NONAME, NO NAME, EMPTY, MISSING) (last names: UNKNOWN, NONE, NOLASTNAME, NO LAST NAME, NO LASTNAME, NONAME, NO NAME, EMPTY, MISSING)");
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

    // Middle Name Issues:
    if (this.common.isEmpty(middle)) {
      issues.add(Detection.PatientNameMiddleIsMissing.build(middle, target));
      passed = false;
    } else {
      if (listr.middleNameOnlyMatch(NameType.INVALID_NAME, middle)) {
        issues.add(Detection.PatientNameMiddleIsInvalid.build(middle, target));
        passed = false;
      }

      if (!common.isValidNameChars(middle)) {
        issues.add(Detection.PatientNameMiddleIsInvalid.build(middle, target));
        passed = false;
      }
    }

    if (listr.matches(NameType.UNNAMED_NEWBORN, first, last, middle)) {
      issues.add(Detection.PatientNameMayBeTemporaryNewbornName
          .build("first[" + first + "] middle[" + middle + "] last[" + last, target));
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
