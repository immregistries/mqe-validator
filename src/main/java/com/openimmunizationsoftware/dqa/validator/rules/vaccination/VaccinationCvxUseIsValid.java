package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvx;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationCvxUseIsValid extends ValidationRule<Vaccination> {


	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationCvxIsValid.class };
	}
	
	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		VaccineCvx vaccineCvx = target.getVaccineCvx();
		String cvxCd = target.getAdminCvxCode();
		String conceptType = vaccineCvx.getConceptType();

		   if (vaccineCvx != null)
		    {
		      if (VaccineCvx.CONCEPT_TYPE_UNSPECIFIED.equals(conceptType))
		      {
		        if (target.isAdministered())
		        {
		          issues.add(PotentialIssue.VaccinationAdminCodeIsNotSpecific.build(cvxCd));
		        }
		      } else if (VaccineCvx.NO_VACCINE_ADMINISTERED.equals(cvxCd))
		      {
		        issues.add(PotentialIssue.VaccinationAdminCodeIsValuedAsNotAdministered.build(cvxCd));
		      } else if (VaccineCvx.UNKNOWN.equals(cvxCd))
		      {
		        issues.add(PotentialIssue.VaccinationAdminCodeIsValuedAsUnknown.build(cvxCd));
		      } else if (VaccineCvx.CONCEPT_TYPE_NON_VACCINE.equals(cvxCd))
		      {
		        issues.add(PotentialIssue.VaccinationAdminCodeIsNotVaccine.build(cvxCd));
		      }
		      if (target.getAdminDate() != null)
		      {
		        if (datr.isAfterDate(vaccineCvx.getValidStartDate(), target.getAdminDate()) || datr.isAfterDate(target.getAdminDate(), vaccineCvx.getValidEndDate()))
		        {
		          if (VaccineCvx.CONCEPT_TYPE_FOREIGN_VACCINE.equals(conceptType)
		              || VaccineCvx.CONCEPT_TYPE_UNSPECIFIED.equals(conceptType))
		          {
		            if (target.isAdministered())
		            {
		              issues.add(PotentialIssue.VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange.build(cvxCd));
		              issues.add(PotentialIssue.VaccinationAdminCodeIsInvalidForDateAdministered.build(cvxCd));
		            }
		          } else
		          {
		            issues.add(PotentialIssue.VaccinationAdminDateIsBeforeOrAfterLicensedVaccineRange.build(cvxCd));
		            issues.add(PotentialIssue.VaccinationAdminCodeIsInvalidForDateAdministered.build(cvxCd));
		          }
		        } else if (datr.isAfterDate(vaccineCvx.getUseStartDate(), target.getAdminDate())
		            || datr.isAfterDate(target.getAdminDate(), vaccineCvx.getUseEndDate()))
		        {

		          if (VaccineCvx.CONCEPT_TYPE_FOREIGN_VACCINE.equals(conceptType)
		              || VaccineCvx.CONCEPT_TYPE_UNSPECIFIED.equals(conceptType))
		          {
		            if (target.isAdministered())
		            {
		              issues.add(PotentialIssue.VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange.build(cvxCd));
		              issues.add(PotentialIssue.VaccinationAdminCodeIsUnexpectedForDateAdministered.build(cvxCd));
		            }
		          } else
		          {
		            issues.add(PotentialIssue.VaccinationAdminDateIsBeforeOrAfterExpectedVaccineUsageRange.build(cvxCd));
		            issues.add(PotentialIssue.VaccinationAdminCodeIsUnexpectedForDateAdministered.build(cvxCd));
		          }
		        }
		        if (m.getPatient().getBirthDate() != null)
		        {
		          int monthsBetween = this.datr.monthsBetween(m.getPatient().getBirthDate(), target.getAdminDate());
		          if (monthsBetween < vaccineCvx.getUseMonthStart() || monthsBetween > vaccineCvx.getUseMonthEnd())
		          {
		            issues.add(PotentialIssue.VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge.build(cvxCd));
		          }
		        }
		      }
		      passed = (issues.size() == 0);
		    } else {
//		    	vaccine cvx is null...  so it either wasn't parsed into the object... or not sent in. either way, it needs an issue raised. 
//		    	
		    }

		return buildResults(issues, passed);
	}
}
