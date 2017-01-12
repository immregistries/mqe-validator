package com.openimmunizationsoftware.dqa.validator.rules.respparty;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.NextOfKin;
import com.openimmunizationsoftware.dqa.model.Patient;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;
import com.openimmunizationsoftware.dqa.validator.rules.patient.PatientExists;

public class ResponsiblePartyExists extends ValidationRule<Patient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class};
	}
	
	
	@Override
	protected ValidationRuleResult executeRule(Patient target, MessageReceived mr) {
		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;
		
		NextOfKin respParty = target.getResponsibleParty();
		
		if (respParty  == null) {
			issues.add(this.util.createIssue(PotentialIssue.PatientGuardianResponsiblePartyIsMissing, ""));
			passed = false;
		}
		
		return buildResults(issues, passed);
	}
}
