package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.detection.DetectionType;
import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientSubmitterIsValid extends ValidationRule<DqaPatient> {

	public PatientSubmitterIsValid() {
		ruleDetections.addAll(
				Arrays.asList(
						Detection.get(VxuField.PATIENT_SUBMITTER_ID, DetectionType.MISSING),
						Detection.get(VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, DetectionType.MISSING),
						Detection.get(VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, DetectionType.MISSING)
				)
		);
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived m) {
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		String submitterNumStr = target.getIdSubmitterNumber();
		String assignAuthCodeStr = target.getIdSubmitterAssigningAuthorityCode();
		String submitterTypeCdStr = target.getIdSubmitterTypeCode();
		
		if (this.common.isEmpty(submitterNumStr)) {
			
			issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID, DetectionType.MISSING).build(target));
			passed = false;
			
			if (this.common.isEmpty(assignAuthCodeStr)) {
				issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_AUTHORITY, DetectionType.MISSING).build(target));
			}
			
			if (this.common.isEmpty(submitterTypeCdStr)) {
				issues.add(Detection.get(VxuField.PATIENT_SUBMITTER_ID_TYPE_CODE, DetectionType.MISSING).build(target));
			}
	    }
		
		return buildResults(issues, passed);
	}
	
}
