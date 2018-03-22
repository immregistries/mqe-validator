package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;

public class VaccinationConfidentialityCodeIsValid extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] { VaccinationAdministeredAmtIsValid.class };
	}
	
	public VaccinationConfidentialityCodeIsValid() {
		ruleDetections.add(Detection.VaccinationConfidentialityCodeIsValuedAsRestricted);
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_CONFIDENTIALITY_CODE));
	}

	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = false;

		String confCode = target.getConfidentialityCode();

		issues.addAll(codr.handleCode(confCode,VxuField.VACCINATION_CONFIDENTIALITY_CODE, target));
		passed = (issues.size() == 0);

		if ("R".equals(confCode) || "V".equals(confCode)) {
			issues.add(Detection.VaccinationConfidentialityCodeIsValuedAsRestricted.build(target));
		}

		return buildResults(issues, passed);

	}
}
