package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.codes.KnowNameList;
import com.openimmunizationsoftware.dqa.validator.codes.model.KnownName;
import com.openimmunizationsoftware.dqa.validator.codes.model.KnownName.NameType;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientNameIsValid extends ValidationRule<Patient> {

	private KnowNameList listr = KnowNameList.INSTANCE;
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String first = target.getNameFirst();
		String last = target.getNameLast();
		String middle = target.getNameMiddle();

		List<KnownName> invalidNames = KnowNameList.INSTANCE.getKnownNames(NameType.INVALID_NAME);
		
//		First Name Issues:
		if (common.isEmpty(first)) {
			issues.add(PotentialIssue.PatientNameFirstIsMissing.build(first));
			passed = false;
		} else {
			if (listr.firstNameOnlyMatch(NameType.INVALID_NAME, first)) {
				issues.add(PotentialIssue.PatientNameFirstIsInvalid.build(first));
				passed = false;
			}

			if (!common.isValidNameChars(first)) {
				issues.add(PotentialIssue.PatientNameFirstIsInvalid
						.build(first));
				passed = false;
			}

			if (first.length() > 3
					&& StringUtils.isEmpty(target.getNameMiddle())) {
				int pos = first.lastIndexOf(' ');
				if (pos > -1 && pos == first.length() - 2) {
					issues.add(PotentialIssue.PatientNameFirstMayIncludeMiddleInitial
							.build(first));
				}
			}
		}
		
//		Last Name Issues:
		if (common.isEmpty(last)) {
			issues.add(PotentialIssue.PatientNameLastIsMissing.build(last));
			passed = false;
		} else {
			if (listr.lastNameOnlyMatch(NameType.INVALID_NAME, last)) {
					issues.add(PotentialIssue.PatientNameLastIsInvalid.build());
					passed = false;
			}
			
			if (!common.isValidNameChars(last)) {
				issues.add(PotentialIssue.PatientNameLastIsInvalid.build());
			}
				
		}
		
		if (listr.matches(NameType.UNNAMED_NEWBORN, first, last, middle)) {
			issues.add(PotentialIssue.PatientNameMayBeTemporaryNewbornName.build("first[" + first + "] middle[" + middle + "] last[" + last));
		}

		if (listr.matches(NameType.TEST_PATIENT, first, last, middle)) {
			issues.add(PotentialIssue.PatientNameMayBeTestName.build());
	    }

		if (listr.matches(NameType.JUNK_NAME, first, last, middle)) {
			issues.add(PotentialIssue.PatientNameHasJunkName.build());
	    }
		
//		 ALL OF THIS SHOULD BE IN THE EXTRACT/TRANSFORM LAYER:
//		    specialNameHandling1(patient.getName());
//		    specialNameHandling2(patient.getName());
//		    specialNameHandling3(patient.getName());
//		    specialNameHandling5(patient.getName());
//		    specialNameHandling6(patient.getName());
//		    specialNameHandling7(patient.getName());
		 
		 
		return buildResults(issues, passed);
	}
	
}
