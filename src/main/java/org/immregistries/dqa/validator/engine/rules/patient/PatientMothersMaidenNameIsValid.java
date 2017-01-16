package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.KnowNameList;
import org.immregistries.dqa.validator.engine.codes.model.KnownName.NameType;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaPatient;

public class PatientMothersMaidenNameIsValid extends ValidationRule<DqaPatient> {

	private KnowNameList listr = KnowNameList.INSTANCE;

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		String mmName = target.getMotherMaidenName();

		if (common.isEmpty(mmName)) {
			issues.add(MessageAttribute.PatientMotherSMaidenNameIsMissing.build());
			passed = false;
		} else {
			if (listr.lastNameOnlyMatch(NameType.INVALID_NAME, mmName)) {
				issues.add(MessageAttribute.PatientMothersMaidenNameIsInvalid
						.build());
				passed = false;
			} else if (listr.lastNameOnlyMatch(NameType.JUNK_NAME, mmName)) {
				issues.add(MessageAttribute.PatientMotherSMaidenNameHasJunkName
						.build());
				passed = false;
			} else if (listr.lastNameOnlyMatch(NameType.INVALID_PREFIXES, mmName)) {
				issues.add(MessageAttribute.PatientMotherSMaidenNameHasInvalidPrefixes
						.build());
				passed = false;
			}

			if (mmName.length() == 1) {
				issues.add(MessageAttribute.PatientMotherSMaidenNameIsTooShort
						.build());
				passed = false;
			}

		}

		return buildResults(issues, passed);
	}
}
