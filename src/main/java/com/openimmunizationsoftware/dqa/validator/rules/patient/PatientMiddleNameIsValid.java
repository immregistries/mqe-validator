package com.openimmunizationsoftware.dqa.validator.rules.patient;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.codes.KnowNameList;
import com.openimmunizationsoftware.dqa.validator.codes.model.KnownName;
import com.openimmunizationsoftware.dqa.validator.codes.model.KnownName.NameType;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class PatientMiddleNameIsValid extends ValidationRule<Patient> {

	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived m) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		List<KnownName> invalidNames = KnowNameList.INSTANCE.getKnownNames(NameType.INVALID_NAME);
		
		String middleName = target.getNameMiddle();
		  
		if (common.isEmpty(middleName)) {
			issues.add(PotentialIssue.PatientMiddleNameIsMissing.build());
			passed = false;
		} else {
			for (KnownName invalidName : invalidNames) {
				if (invalidName.onlyNameMiddle()
						&& middleName.equalsIgnoreCase(invalidName.getNameMiddle())) {
					
					issues.add(PotentialIssue.PatientMiddleNameIsInvalid.build());
					break;// this gets out of the for loop.
				}
			}

			if (middleName.length() == 1) {
				issues.add(PotentialIssue.PatientMiddleNameMayBeInitial.build());
			}

			if (middleName.endsWith(".")) {// why are we removing dots???
				String moddedMiddle = middleName.substring(0, middleName.length() - 1);
	
				if (!common.isValidNameChars(moddedMiddle)) {
					issues.add(PotentialIssue.PatientMiddleNameIsInvalid.build());
					passed = false;
				}
			}
		} 
		return buildResults(issues, passed);
	}
	
	

}
