package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.PotentialIssue;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;
import org.immregistries.dqa.validator.model.codes.VaccineCpt;
import org.immregistries.dqa.validator.model.codes.VaccineCvx;

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
	        issues.add(PotentialIssue.VaccinationCvxCodeAndCptCodeAreInconsistent.build());
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
