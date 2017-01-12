package com.openimmunizationsoftware.dqa.validator.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import com.openimmunizationsoftware.dqa.model.MessageReceived;
import com.openimmunizationsoftware.dqa.model.Vaccination;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCpt;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvx;
import com.openimmunizationsoftware.dqa.model.codes.VaccineCvxGroup;
import com.openimmunizationsoftware.dqa.validator.ValidationRule;
import com.openimmunizationsoftware.dqa.validator.ValidationRuleResult;
import com.openimmunizationsoftware.dqa.validator.issues.IssueFound;
import com.openimmunizationsoftware.dqa.validator.issues.PotentialIssue;

public class VaccinationCodeGroupsMatch extends ValidationRule<Vaccination> {

	@Override
	protected ValidationRuleResult executeRule(Vaccination target,
			MessageReceived m) {

		List<IssueFound> issues = new ArrayList<IssueFound>();
		boolean passed = true;

		VaccineCvx vaccineCvx = target.getVaccineCvx();
		VaccineCpt vaccineCpt = repo.getCptForCode(target.getAdminCptCode());
		
		
	    if (vaccineCpt != null && vaccineCpt.getCvx() != null && vaccineCvx != null)
	    {
	      if (!checkGroupMatch(vaccineCvx, vaccineCpt))
	      {
	        issues.add(PotentialIssue.VaccinationCvxCodeAndCptCodeAreInconsistent.build());
	      }
	    }
	    
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
	
	  private boolean checkGroupMatch(VaccineCvx vaccineCvx, VaccineCpt vaccineCpt)
	  {
	    if (vaccineCvx.equals(vaccineCpt.getCvx()))
	    {
	      return true;
	    }
	    
	    if (vaccineCvx == null || vaccineCpt == null) {
	    	return false;
	    }
	    
	    String cptCvxCode = vaccineCpt.getCvx().getCvxCode();
	    
	    List<VaccineCvxGroup> cvxGroups = repo.getVaccineCvxGroups(vaccineCvx.getCvxCode());
	    List<VaccineCvxGroup> cptGroups = repo.getVaccineCvxGroups(cptCvxCode);
	    
	    // CPT doesn't map to CVX, so need to check if it's in the same family
	    //Has to have at least one matching. 
	    for (VaccineCvxGroup cvxGroup : cvxGroups)
	    {
	      for (VaccineCvxGroup cptGroup : cptGroups)
	      {
	        if (cvxGroup.getVaccineGroup().equals(cptGroup.getVaccineGroup()))
	        {
	          return true;
	        }
	      }
	    }
	    
	    
	    return false;
	  }
	  
}
