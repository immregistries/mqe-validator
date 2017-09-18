package org.immregistries.dqa.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.codes.KnowNameList;
import org.immregistries.dqa.validator.engine.codes.KnownName;
import org.immregistries.dqa.validator.engine.codes.KnownName.NameType;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;

public class PatientNameIsValid extends ValidationRule<DqaPatient> {

	private KnowNameList listr = KnowNameList.INSTANCE;
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		String first = target.getNameFirst();
		String last = target.getNameLast();
		String middle = target.getNameMiddle();

		List<KnownName> invalidNames = KnowNameList.INSTANCE.getKnownNames(NameType.INVALID_NAME);
		
//		First Name Issues:
		if (common.isEmpty(first)) {
			issues.add(Detection.PatientNameFirstIsMissing.build(first));
			passed = false;
		} else {
			if (listr.firstNameOnlyMatch(NameType.INVALID_NAME, first)) {
				issues.add(Detection.PatientNameFirstIsInvalid.build(first));
				passed = false;
			}

			if (!common.isValidNameChars(first)) {
				issues.add(Detection.PatientNameFirstIsInvalid
						.build(first));
				passed = false;
			}

			if (first.length() > 3
					&& StringUtils.isEmpty(target.getNameMiddle())) {
				int pos = first.lastIndexOf(' ');
				if (pos > -1 && pos == first.length() - 2) {
					issues.add(Detection.PatientNameFirstMayIncludeMiddleInitial
							.build(first));
				}
			}
		}
		
//		Last Name Issues:
		if (common.isEmpty(last)) {
			issues.add(Detection.PatientNameLastIsMissing.build(last));
			passed = false;
		} else {
			if (listr.lastNameOnlyMatch(NameType.INVALID_NAME, last)) {
					issues.add(Detection.PatientNameLastIsInvalid.build());
					passed = false;
			}
			
			if (!common.isValidNameChars(last)) {
				issues.add(Detection.PatientNameLastIsInvalid.build());
			}
				
		}
		
		if (listr.matches(NameType.UNNAMED_NEWBORN, first, last, middle)) {
			issues.add(Detection.PatientNameMayBeTemporaryNewbornName.build("first[" + first + "] middle[" + middle + "] last[" + last));
		}

		if (listr.matches(NameType.TEST_PATIENT, first, last, middle)) {
			issues.add(Detection.PatientNameMayBeTestName.build());
	    }

		if (listr.matches(NameType.JUNK_NAME, first, last, middle)) {
			issues.add(Detection.PatientNameHasJunkName.build());
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
