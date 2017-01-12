package com.openimmunizationsoftware.dqa.validator.rules.nextofkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class NextOfKinNameIsValid extends ValidationRule<NextOfKin> {

	@Override
	protected ValidationRuleResult executeRule(NextOfKin target, MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		String first = target.getNameFirst();
		String last = target.getNameLast();
		
		if (StringUtils.isEmpty(first)) {
			issues.add(PotentialIssue.NextOfKinNameLastIsMissing.build(first));
		}
		
		if (StringUtils.isEmpty(last)) {
			issues.add(PotentialIssue.NextOfKinNameFirstIsMissing.build(last));
		}
		 
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
	}

}
