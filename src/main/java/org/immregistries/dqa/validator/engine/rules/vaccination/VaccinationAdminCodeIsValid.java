package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.codebase.client.reference.CvxSpecialValues;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

public class VaccinationAdminCodeIsValid extends ValidationRule<DqaVaccination> {


	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationCvxIsValid.class };
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		
		boolean passed = true;

//		String cptCode =
		String cvxCode = target.getAdminCvxCode();
		String ndcCode = target.getAdminNdc();

		Code vaccineCvxCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
		if (vaccineCvxCode == null) {
        vaccineCvxCode = this.repo.getFirstRelatedCodeForCodeIn(CodesetType.VACCINATION_NDC_CODE, ndcCode, CodesetType.VACCINATION_CVX_CODE);
    }


		if (vaccineCvxCode != null) {
			String conceptTypeString = vaccineCvxCode.getConceptType();
			CvxConceptType concept = CvxConceptType.getBy(conceptTypeString);

			CvxSpecialValues cvxSpecial = CvxSpecialValues.getBy(cvxCode);
			
			if (CvxConceptType.UNSPECIFIED == concept) {
				if (target.isAdministered()) {
					issues.add(Detection.VaccinationAdminCodeIsNotSpecific.build(cvxCode));
				}
			} else if (CvxSpecialValues.NO_VACCINE_ADMINISTERED == cvxSpecial) {
				issues.add(Detection.VaccinationAdminCodeIsValuedAsNotAdministered.build(cvxCode));
			} else if (CvxSpecialValues.UNKNOWN == cvxSpecial) {
				issues.add(Detection.VaccinationAdminCodeIsValuedAsUnknown.build(cvxCode));
			} else if (CvxConceptType.NON_VACCINE == concept) {
				issues.add(Detection.VaccinationAdminCodeIsNotVaccine.build(cvxCode));
			}

			passed = (issues.size() == 0);
		}

		return buildResults(issues, passed);
	}
}
