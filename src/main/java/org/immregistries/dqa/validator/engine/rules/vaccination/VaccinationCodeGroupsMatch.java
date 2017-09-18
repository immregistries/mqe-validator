package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationCodeGroupsMatch extends ValidationRule<DqaVaccination> {

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;

		String vaccineCpt = target.getAdminCptCode();
		
		String vaccineCvx = target.getAdminCvxCode();
		
//		Code cptCode = repo.getCodeFromValue(target.getAdminCptCode(), CodesetType.VACCINATION_CPT_CODE);
		
		Code cptCvxCode = repo.getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_CPT_CODE, vaccineCpt, CodesetType.VACCINATION_CVX_CODE);
		
		
	    if (cptCvxCode != null && cptCvxCode.getValue() != null && vaccineCvx != null)
	    {
	      if (!checkGroupMatch(vaccineCvx, cptCvxCode.getValue()))
	      {
	        issues.add(Detection.VaccinationCvxCodeAndCptCodeAreInconsistent.build());
	      }
	    }
	    
		passed = (issues.size() == 0);

		return buildResults(issues, passed);
	}
	
	private boolean checkGroupMatch(String cvxCode, String cptCvxCode) {
		if (cvxCode == null || cptCvxCode == null) {
			return false;
		}

		if (cvxCode.equals(cptCvxCode)) {
			return true;
		}

		List<Code> cvxVaccineGroups = repo.getRelatedCodesForCodeIn(
				CodesetType.VACCINATION_CVX_CODE, cvxCode,
				CodesetType.VACCINE_GROUP);
		
		List<Code> cptVaccineGroups = repo.getRelatedCodesForCodeIn(
				CodesetType.VACCINATION_CPT_CODE, cptCvxCode,
				CodesetType.VACCINE_GROUP);

		for (Code cvxGroup : cvxVaccineGroups) {
			for (Code cptGroup : cptVaccineGroups) {
				if (repo.codeEquals(cvxGroup,  cptGroup)) {
					return true;
				}
			}
		}
		
		return false;
		// CPT doesn't map to CVX, so need to check if it's in the same family
		// Has to have at least one matching.
	}
	  
}
