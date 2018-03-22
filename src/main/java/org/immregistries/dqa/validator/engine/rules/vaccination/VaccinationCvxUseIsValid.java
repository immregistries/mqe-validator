package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.codebase.client.generated.Code;
import org.immregistries.dqa.codebase.client.reference.CodesetType;
import org.immregistries.dqa.codebase.client.reference.CvxConceptType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class VaccinationCvxUseIsValid extends ValidationRule<DqaVaccination> {

	private static final Logger logger = LoggerFactory
		.getLogger(VaccinationCvxUseIsValid.class);

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationCvxIsValid.class };
	}
	
	public VaccinationCvxUseIsValid() {
		
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;

		String cvxCode = target.getAdminCvxCode();
		
		Code vaccineCode = this.repo.getCodeFromValue(cvxCode, CodesetType.VACCINATION_CVX_CODE);
		
		String CvxConceptTypeString = vaccineCode.getConceptType();
		CvxConceptType concept = CvxConceptType.getBy(CvxConceptTypeString);

		if (vaccineCode != null && target.getAdminDate() != null) {
			if (CvxConceptType.FOREIGN_VACCINE 	== concept
			 || CvxConceptType.UNSPECIFIED 		== concept
			 && !target.isAdministered()) {
				logger.info("Not evaluating date because the concept type indicates an UNSPECIFIED or FOREIGN_VACCINE, and it's not administered");
			} else {
				codr.handleUseDate(vaccineCode, target.getAdminDateString(),
						VxuField.VACCINATION_ADMIN_DATE, target);
			}

			passed = (issues.size() == 0);
		}
		   
		return buildResults(issues, passed);
	}
}
