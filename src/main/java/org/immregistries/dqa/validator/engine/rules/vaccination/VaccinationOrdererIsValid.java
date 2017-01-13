package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.IssueField;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;
import org.immregistries.dqa.validator.model.hl7types.Id;

public class VaccinationOrdererIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationIsAdministered.class };
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination vaccination, DqaMessageReceived m) {
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
		//Orderer
		Id orderedBy = vaccination.getOrderedBy();
		String ordererNum = null;
		if (orderedBy != null) {
			ordererNum = orderedBy.getNumber();
		}
		
		issues.addAll(codr.handleCode(ordererNum,  IssueField.VACCINATION_ORDERED_BY));
		
		//Recorder
		Id enteredBy = vaccination.getEnteredBy();
		String recorderNum = null;
		if (enteredBy != null) {
			recorderNum = enteredBy.getNumber();
		}
		
		issues.addAll(codr.handleCode(recorderNum, IssueField.VACCINATION_RECORDED_BY));
	    
		// mark passed if there's no issues.
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}

}
