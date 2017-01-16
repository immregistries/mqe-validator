package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

public class VaccinationAdminDateIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		String dateString = target.getAdminDateString();

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		if (this.common.isEmpty(dateString)) {
			issues.add(MessageAttribute.VaccinationAdminDateIsMissing.build(dateString));
			passed = false;
			
			return buildResults(issues, passed);
		}

		if (!this.common.isValidDate(dateString)) {
			issues.add(MessageAttribute.VaccinationAdminDateIsInvalid.build(dateString));
			passed = false;
			return buildResults(issues, passed);
		};

		
		//After this, we have a date. 

		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int lastDayofMonth = cal.getMaximum(Calendar.DAY_OF_MONTH);

		if (dayOfMonth == 1) {
			issues.add(MessageAttribute.VaccinationAdminDateIsOnFirstDayOfMonth.build(dateString));
			
		} else if (dayOfMonth == 15) {
			issues.add(MessageAttribute.VaccinationAdminDateIsOn15ThDayOfMonth.build(dateString));
			
		} else if (dayOfMonth == lastDayofMonth) {
			issues.add(MessageAttribute.VaccinationAdminDateIsOnLastDayOfMonth.build(dateString));
		}
		
		
	      if (target.isAdministered())
		      {
		        if (target.getExpirationDate() != null)
		        {
		          if (datr.isAfterDate(target.getAdminDate(), target.getExpirationDate()))
		          {
		            issues.add(MessageAttribute.VaccinationAdminDateIsAfterLotExpirationDate.build());
		          }
		        }
		      }
		      
	      	if (datr.isAfterDate(target.getAdminDate(), m.getReceivedDate()))
		      {
		        issues.add(MessageAttribute.VaccinationAdminDateIsAfterMessageSubmitted.build());
		      }
		      
		      if (m.getPatient().getDeathDate() != null)
		      {
		        if (datr.isAfterDate(target.getAdminDate(), m.getPatient().getDeathDate()))
		        {
		          issues.add(MessageAttribute.VaccinationAdminDateIsAfterPatientDeathDate.build());
		        }
		      }
		      
		      if (m.getPatient().getBirthDate() != null)
		      {
		        if (datr.isBeforeDate(target.getAdminDate(), m.getPatient().getBirthDate()))
		        {
		          issues.add(MessageAttribute.VaccinationAdminDateIsBeforeBirth.build());
		        }
		      }
		      
		      if (target.getSystemEntryDate() != null)
		      {

		        if (datr.isAfterDate(target.getAdminDate(), target.getSystemEntryDate()))
		        {
		          issues.add(MessageAttribute.VaccinationAdminDateIsAfterSystemEntryDate.build());
		        }
		      }
		      
		      if (target.getAdminDateEnd() == null) {
		    	  issues.add(MessageAttribute.VaccinationAdminDateEndIsMissing.build());
		      } else {
		        if (!target.getAdminDateEnd().equals(target.getAdminDate()))
		        {
		          issues.add(MessageAttribute.VaccinationAdminDateEndIsDifferentFromStartDate.build());
		        }
		      }
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);

	}
}
