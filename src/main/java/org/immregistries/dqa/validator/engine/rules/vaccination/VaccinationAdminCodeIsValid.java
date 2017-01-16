package org.immregistries.dqa.validator.engine.rules.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.codebase.client.reference.CvxSpecialValues;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.MessageAttribute;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageReceived;
import org.immregistries.dqa.validator.model.DqaVaccination;

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

		String cvxCode = target.getAdminCvxCode();
		
		Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
		
		if (vaccineCode != null) {
			String conceptTypeString = vaccineCode.getConceptType();
			CvxConceptType concept = CvxConceptType.getBy(conceptTypeString);

			CvxSpecialValues cvxSpecial = CvxSpecialValues.getBy(cvxCode);
			
			if (CvxConceptType.UNSPECIFIED == concept) {
				if (target.isAdministered()) {
					issues.add(MessageAttribute.VaccinationAdminCodeIsNotSpecific.build(cvxCode));
				}
			} else if (CvxSpecialValues.NO_VACCINE_ADMINISTERED == cvxSpecial) {
				issues.add(MessageAttribute.VaccinationAdminCodeIsValuedAsNotAdministered
						.build(cvxCode));
			} else if (CvxSpecialValues.UNKNOWN == cvxSpecial) {
				issues.add(MessageAttribute.VaccinationAdminCodeIsValuedAsUnknown.build(cvxCode));
			} else if (CvxConceptType.NON_VACCINE == concept) {
				issues.add(MessageAttribute.VaccinationAdminCodeIsNotVaccine.build(cvxCode));
			}

			passed = (issues.size() == 0);
		}

		return buildResults(issues, passed);
	}
}
