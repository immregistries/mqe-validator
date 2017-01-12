package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.codes.KnowNameList;
import com.openimmunizationsoftware.dqa.validator.codes.model.KnownName.NameType;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientMothersMaidenNameIsValid extends ValidationRule<Patient> {

	private KnowNameList listr = KnowNameList.INSTANCE;

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		String mmName = target.getMotherMaidenName();

		if (common.isEmpty(mmName)) {
			issues.add(PotentialIssue.PatientMotherSMaidenNameIsMissing.build());
			passed = false;
		} else {
			if (listr.lastNameOnlyMatch(NameType.INVALID_NAME, mmName)) {
				issues.add(PotentialIssue.PatientMothersMaidenNameIsInvalid
						.build());
				passed = false;
			} else if (listr.lastNameOnlyMatch(NameType.JUNK_NAME, mmName)) {
				issues.add(PotentialIssue.PatientMotherSMaidenNameHasJunkName
						.build());
				passed = false;
			} else if (listr.lastNameOnlyMatch(NameType.INVALID_PREFIXES, mmName)) {
				issues.add(PotentialIssue.PatientMotherSMaidenNameHasInvalidPrefixes
						.build());
				passed = false;
			}

			if (mmName.length() == 1) {
				issues.add(PotentialIssue.PatientMotherSMaidenNameIsTooShort
						.build());
				passed = false;
			}

		}

		return buildResults(issues, passed);
	}
}
