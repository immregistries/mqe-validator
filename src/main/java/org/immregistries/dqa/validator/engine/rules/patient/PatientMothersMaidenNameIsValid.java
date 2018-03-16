package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.KnowNameList;
import org.immregistries.dqa.validator.engine.codes.KnownName.NameType;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientMothersMaidenNameIsValid extends ValidationRule<DqaPatient> {

	private KnowNameList listr = KnowNameList.INSTANCE;

	public PatientMothersMaidenNameIsValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.PatientMotherSMaidenNameIsMissing,
				Detection.PatientMothersMaidenNameIsInvalid,
				Detection.PatientMotherSMaidenNameHasJunkName,
				Detection.PatientMotherSMaidenNameHasInvalidPrefixes,
				Detection.PatientMotherSMaidenNameIsTooShort
		));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		String mmName = target.getMotherMaidenName();

		if (common.isEmpty(mmName)) {
			issues.add(Detection.PatientMotherSMaidenNameIsMissing.build());
			passed = false;
		} else {
			if (listr.lastNameOnlyMatch(NameType.INVALID_NAME, mmName)) {
				issues.add(Detection.PatientMothersMaidenNameIsInvalid
						.build());
				passed = false;
			} else if (listr.lastNameOnlyMatch(NameType.JUNK_NAME, mmName)) {
				issues.add(Detection.PatientMotherSMaidenNameHasJunkName
						.build());
				passed = false;
			} else if (listr.lastNameOnlyMatch(NameType.INVALID_PREFIXES, mmName)) {
				issues.add(Detection.PatientMotherSMaidenNameHasInvalidPrefixes
						.build());
				passed = false;
			}

			if (mmName.length() == 1) {
				issues.add(Detection.PatientMotherSMaidenNameIsTooShort
						.build());
				passed = false;
			}

		}

		return buildResults(issues, passed);
	}
}
