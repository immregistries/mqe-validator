package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.codebase.client.reference.CvxSpecialValues;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinationAdminCodeIsValid extends ValidationRule<DqaVaccination> {


	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationCvxIsValid.class };
	}
	
	public VaccinationAdminCodeIsValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.VaccinationAdminCodeIsNotSpecific,
				Detection.VaccinationAdminCodeIsValuedAsNotAdministered,
				Detection.VaccinationAdminCodeIsValuedAsUnknown,
				Detection.VaccinationAdminCodeIsNotVaccine
		));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		
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
					issues.add(Detection.VaccinationAdminCodeIsNotSpecific.build((cvxCode), target));
				}
			} else if (CvxSpecialValues.NO_VACCINE_ADMINISTERED == cvxSpecial) {
				issues.add(Detection.VaccinationAdminCodeIsValuedAsNotAdministered.build((cvxCode), target));
			} else if (CvxSpecialValues.UNKNOWN == cvxSpecial) {
				issues.add(Detection.VaccinationAdminCodeIsValuedAsUnknown.build((cvxCode), target));
			} else if (CvxConceptType.NON_VACCINE == concept) {
				issues.add(Detection.VaccinationAdminCodeIsNotVaccine.build((cvxCode), target));
			}

			passed = (issues.size() == 0);
		}

		return buildResults(issues, passed);
	}
}
