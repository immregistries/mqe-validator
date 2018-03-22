package org.immregistries.dqa.validator.engine.rules.vaccination;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VaccinationInformationSourceIsValid extends ValidationRule<DqaVaccination> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
			VaccinationIsAdministeredOrHistorical.class
		};
	}
	
	public VaccinationInformationSourceIsValid() {
		ruleDetections.addAll(Arrays.asList(
				Detection.VaccinationInformationSourceIsMissing,
				Detection.VaccinationInformationSourceIsValuedAsAdministered,
				Detection.VaccinationInformationSourceIsValuedAsHistorical
		));
		
		ruleDetections.addAll(codr.getDetectionsForField(VxuField.VACCINATION_INFORMATION_SOURCE));
	}
	
	/*
	 * This is the money: 
	 */
	
	@Override
	protected ValidationRuleResult executeRule(DqaVaccination target, DqaMessageReceived m) {
		
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		String sourceCd = target.getInformationSourceCode();
		
		if (this.common.isEmpty(sourceCd)) {
			issues.add(Detection.VaccinationInformationSourceIsMissing.build(target));
			passed = false;
		} else {
			
	        issues.addAll(this.codr.handleCode(target.getInformationSource(), VxuField.VACCINATION_INFORMATION_SOURCE, target));
	        
	        if (issues.size() > 0) {
	        	passed = false;
	        }
	        
			switch (sourceCd) {
				case DqaVaccination.INFO_SOURCE_ADMIN:
					issues.add(Detection.VaccinationInformationSourceIsValuedAsAdministered.build((sourceCd), target));
					break;
				case DqaVaccination.INFO_SOURCE_HIST:
					issues.add(Detection.VaccinationInformationSourceIsValuedAsHistorical.build((sourceCd), target));
					break;
			}
	      }
		
		return buildResults(issues, passed);
	}
}
