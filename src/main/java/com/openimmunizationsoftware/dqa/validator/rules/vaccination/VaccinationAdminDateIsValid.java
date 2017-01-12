package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationAdminDateIsValid extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		String dateString = target.getAdminDateString();

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		if (this.common.isEmpty(dateString)) {
			issues.add(PotentialIssue.VaccinationAdminDateIsMissing.build(dateString));
			passed = false;
			
			return buildResults(issues, passed);
		}

		if (!this.common.isValidDate(dateString)) {
			issues.add(PotentialIssue.VaccinationAdminDateIsInvalid.build(dateString));
			passed = false;
			return buildResults(issues, passed);
		};

		
		//After this, we have a date. 

		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int lastDayofMonth = cal.getMaximum(Calendar.DAY_OF_MONTH);

		if (dayOfMonth == 1) {
			issues.add(PotentialIssue.VaccinationAdminDateIsOnFirstDayOfMonth.build(dateString));
			
		} else if (dayOfMonth == 15) {
			issues.add(PotentialIssue.VaccinationAdminDateIsOn15ThDayOfMonth.build(dateString));
			
		} else if (dayOfMonth == lastDayofMonth) {
			issues.add(PotentialIssue.VaccinationAdminDateIsOnLastDayOfMonth.build(dateString));
		}
		
		
	      if (target.isAdministered())
		      {
		        if (target.getExpirationDate() != null)
		        {
		          if (datr.isAfterDate(target.getAdminDate(), target.getExpirationDate()))
		          {
		            issues.add(PotentialIssue.VaccinationAdminDateIsAfterLotExpirationDate.build());
		          }
		        }
		      }
		      
	      	if (datr.isAfterDate(target.getAdminDate(), m.getReceivedDate()))
		      {
		        issues.add(PotentialIssue.VaccinationAdminDateIsAfterMessageSubmitted.build());
		      }
		      
		      if (m.getPatient().getDeathDate() != null)
		      {
		        if (datr.isAfterDate(target.getAdminDate(), m.getPatient().getDeathDate()))
		        {
		          issues.add(PotentialIssue.VaccinationAdminDateIsAfterPatientDeathDate.build());
		        }
		      }
		      
		      if (m.getPatient().getBirthDate() != null)
		      {
		        if (datr.isBeforeDate(target.getAdminDate(), m.getPatient().getBirthDate()))
		        {
		          issues.add(PotentialIssue.VaccinationAdminDateIsBeforeBirth.build());
		        }
		      }
		      
		      if (target.getSystemEntryDate() != null)
		      {

		        if (datr.isAfterDate(target.getAdminDate(), target.getSystemEntryDate()))
		        {
		          issues.add(PotentialIssue.VaccinationAdminDateIsAfterSystemEntryDate.build());
		        }
		      }
		      
		      if (target.getAdminDateEnd() == null) {
		    	  issues.add(PotentialIssue.VaccinationAdminDateEndIsMissing.build());
		      } else {
		        if (!target.getAdminDateEnd().equals(target.getAdminDate()))
		        {
		          issues.add(PotentialIssue.VaccinationAdminDateEndIsDifferentFromStartDate.build());
		        }
		      }
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);

	}
}
