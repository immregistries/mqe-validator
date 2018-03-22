package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.KnowNameList;
import org.immregistries.dqa.validator.engine.codes.KnownName;
import org.immregistries.dqa.validator.engine.codes.KnownName.NameType;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientMiddleNameIsValid extends ValidationRule<DqaPatient> {
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientMiddleNameIsPresent.class};
	}

	public PatientMiddleNameIsValid() {
		ruleDetections.addAll(
				Arrays.asList(
						Detection.PatientMiddleNameIsInvalid,
						Detection.PatientMiddleNameMayBeInitial,
						Detection.PatientMiddleNameIsInvalid
				)
		);
	}

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;

		List<KnownName> invalidNames = KnowNameList.INSTANCE.getKnownNames(NameType.INVALID_NAME);

		String middleName = target.getNameMiddle();

    if (!this.common.isEmpty(middleName)) {
        for (KnownName invalidName : invalidNames) {
            if (invalidName.onlyNameMiddle() && middleName.equalsIgnoreCase(invalidName.getNameMiddle())) {

                issues.add(Detection.PatientMiddleNameIsInvalid.build(target));
                break;// this gets out of the for loop.
            }
        }

        if (middleName.length() == 1) {
            issues.add(Detection.PatientMiddleNameMayBeInitial.build(target));
        }

        if (middleName.endsWith(".")) {// why are we removing dots???
            String moddedMiddle = middleName.substring(0, middleName.length() - 1);

            if (!common.isValidNameChars(moddedMiddle)) {
                issues.add(Detection.PatientMiddleNameIsInvalid.build(target));
                passed = false;
            }
        }
    }
		return buildResults(issues, passed);
	}



}
