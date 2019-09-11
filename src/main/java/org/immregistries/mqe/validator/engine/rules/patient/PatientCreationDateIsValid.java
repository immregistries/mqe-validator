package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.joda.time.DateTime;

public class PatientCreationDateIsValid extends ValidationRule<MqePatient> {

	
	
	public PatientCreationDateIsValid() {
		super();
		this.addRuleDetections(Arrays.asList(
				Detection.PatientSystemEntryDateIsInvalid,
				Detection.PatientSystemEntryDateIsMissing,
				Detection.PatientSystemEntryDateIsInTheFuture
		));
		ImplementationDetail id = this.addRuleDetection(Detection.PatientSystemEntryDateIsInvalid);id.setImplementationDescription("Patient system entry date cannot be translated to a date.");
	}

	@Override
	protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
		List<ValidationReport> issues = new ArrayList<>();
		boolean passed = false;
		
		if(target.getSystemEntryDateString() == null || target.getSystemEntryDateString().isEmpty()){
			issues.add(Detection.PatientSystemEntryDateIsMissing.build(target));
		}
		else {
			if(this.common.isValidDate(target.getSystemEntryDateString())){
				 DateTime systemEntryDate = this.common.parseDateTimeFrom(target.getSystemEntryDateString());
				 if(systemEntryDate.isAfterNow()){
						issues.add(Detection.PatientSystemEntryDateIsInTheFuture.build(target.getSystemEntryDateString(),target));
				 }
				 else {
					 if(target.getSystemEntryDate() == null){
						 target.setSystemEntryDate(systemEntryDate.toDate());
					 }
					 passed = true;
				 }
			}
			else {
				issues.add(Detection.PatientSystemEntryDateIsInvalid.build(target.getSystemEntryDateString(),target));
			}
		}
		
		return buildResults(issues, passed);
	}

}