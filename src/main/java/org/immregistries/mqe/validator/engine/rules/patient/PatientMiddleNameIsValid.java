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

public class PatientMiddleNameIsValid extends ValidationRule<MqePatient> {

  @Override
  protected final Class[] getDependencies() {
    return new Class[] {PatientMiddleNameIsPresent.class};
  }

  public PatientMiddleNameIsValid() {
    this.addRuleDocumentation(Arrays.asList(Detection.PatientNameMiddleIsInvalid,
        Detection.PatientMiddleNameMayBeInitial));
    
    this.addImplementationMessage(Detection.PatientMiddleNameMayBeInitial, "Patient middle name is only 1 character and might be an initial.");
    this.addImplementationMessage(Detection.PatientNameMiddleIsInvalid, "Patient middle name must not be on the specified invalid name list ('UN','UK','UNK', 'UNKN', 'NONE') and is comprised of alphabetical characters except for '.' at the end.");
  }

  @Override
  protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
    List<ValidationReport> issues = new ArrayList<ValidationReport>();
    boolean passed = true;

    List<KnownName> invalidNames = KnowNameList.INSTANCE.getKnownNames(NameType.INVALID_NAME);

    String middleName = target.getNameMiddle();

    if (!this.common.isEmpty(middleName)) {
      for (KnownName invalidName : invalidNames) {
        if (invalidName.onlyNameMiddle()
            && middleName.equalsIgnoreCase(invalidName.getNameMiddle())) {

          issues.add(Detection.PatientNameMiddleIsInvalid.build(target));
          break;// this gets out of the for loop.
        }
      }

      if (middleName.length() == 1) {
        issues.add(Detection.PatientMiddleNameMayBeInitial.build(target));
      }

      if (middleName.endsWith(".")) {// why are we removing dots???
        String moddedMiddle = middleName.substring(0, middleName.length() - 1);

        if (!common.isValidNameChars(moddedMiddle)) {
          issues.add(Detection.PatientNameMiddleIsInvalid.build(target));
          passed = false;
        }
      }
    } else {
	    issues.add(Detection.PatientNameMiddleIsMissing.build(target));
	    passed = false;
    }
    
    return buildResults(issues, passed);
  }


}
