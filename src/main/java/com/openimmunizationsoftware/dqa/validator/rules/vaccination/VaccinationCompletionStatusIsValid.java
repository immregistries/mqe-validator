package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.types.CodedEntity;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueField;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationCompletionStatusIsValid extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target, MessageReceived m) {
		
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		
	    // If vaccination is not actually administered then this is a waiver. Need
	    // to check that now, here to see if we need to enforce a value in RXA-9 to
	    // indicate that the vaccination is historical or administered.
	    // By default we assume that the vaccination was completed.
		
		CodedEntity completion = target.getCompletion();
		
		issues.addAll(this.codr.handleCode(completion, IssueField.VACCINATION_COMPLETION_STATUS));
		if (issues.size() > 0) {
			passed = false;
		}
		
	    if (target.isCompletionCompleted())
	    {
	      issues.add(PotentialIssue.VaccinationCompletionStatusIsValuedAsCompleted.build(target.getCompletionCode()));
	    } else if (target.isCompletionRefused())
	    {
	      issues.add(PotentialIssue.VaccinationCompletionStatusIsValuedAsRefused.build(target.getCompletionCode()));
	    } else if (target.isCompletionNotAdministered())
	    {
	      issues.add(PotentialIssue.VaccinationCompletionStatusIsValuedAsNotAdministered.build(target.getCompletionCode()));
	    } else if (target.isCompletionPartiallyAdministered())
	    {
	      issues.add(PotentialIssue.VaccinationCompletionStatusIsValuedAsPartiallyAdministered.build(target.getCompletionCode()));
	    }
		
		return buildResults(issues, passed);
		
	}
}
