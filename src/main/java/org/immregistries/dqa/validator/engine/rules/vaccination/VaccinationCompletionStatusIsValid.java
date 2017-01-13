package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationCompletionStatusIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		
	    // If vaccination is not actually administered then this is a waiver. Need
	    // to check that now, here to see if we need to enforce a value in RXA-9 to
	    // indicate that the vaccination is historical or administered.
	    // By default we assume that the vaccination was completed.
		
		String completion = target.getCompletion();
		
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
