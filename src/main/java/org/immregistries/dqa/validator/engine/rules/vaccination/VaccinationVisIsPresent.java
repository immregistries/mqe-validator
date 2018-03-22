package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VaccinationVIS;

import java.util.ArrayList;
import java.util.List;

public class VaccinationVisIsPresent extends
		ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
				
		};
	}

	public VaccinationVisIsPresent() {
		ruleDetections.add(Detection.VaccinationVisIsMissing);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target,
			DqaMessageReceived m) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = false;

		if (target.isAdministered()) {
		  VaccinationVIS vis = target.getVaccinationVis();
		if (vis == null ||(this.common.isEmpty(vis.getDocumentCode()) && (this.common.isEmpty(vis.getCvxCode()) && vis.getPublishedDate() == null))) {
				issues.add(Detection.VaccinationVisIsMissing.build(target));
		} else {
		  passed = true;
			}
		}
		
		return buildResults(issues, passed);
	}
}
