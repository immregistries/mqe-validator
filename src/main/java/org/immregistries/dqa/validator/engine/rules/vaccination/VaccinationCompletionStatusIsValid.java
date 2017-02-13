package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.IssueField;
import org.immregistries.dqa.validator.issue.MessageAttribute;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

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
	      issues.add(MessageAttribute.VaccinationCompletionStatusIsValuedAsCompleted.build(target.getCompletionCode()));
	    } else if (target.isCompletionRefused())
	    {
	      issues.add(MessageAttribute.VaccinationCompletionStatusIsValuedAsRefused.build(target.getCompletionCode()));
	    } else if (target.isCompletionNotAdministered())
	    {
	      issues.add(MessageAttribute.VaccinationCompletionStatusIsValuedAsNotAdministered.build(target.getCompletionCode()));
	    } else if (target.isCompletionPartiallyAdministered())
	    {
	      issues.add(MessageAttribute.VaccinationCompletionStatusIsValuedAsPartiallyAdministered.build(target.getCompletionCode()));
	    }
		
		return buildResults(issues, passed);
		
	}
}
