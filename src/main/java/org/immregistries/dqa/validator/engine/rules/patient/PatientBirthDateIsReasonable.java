package org.immregistries.dqa.validator.engine.rules.patient;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaPatient;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class PatientBirthDateIsReasonable extends ValidationRule<DqaPatient> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {PatientExists.class, PatientBirthDateIsValid.class};
	}
	
	public PatientBirthDateIsReasonable(){
		this.ruleDetections.add(Detection.PatientBirthDateIsVeryLongAgo);
	}

	@Override
	protected ValidationRuleResult executeRule(DqaPatient target, DqaMessageReceived message) {
		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		String birthDateString = target.getBirthDateString();
		DateTime birthDate = this.common.parseDateTimeFrom(birthDateString);

		//This is not an error condition...  the birthdate can still be valid.  it's just wierd. 
		int age = this.datr.getAge(birthDate);
		if (age > 120) {
			issues.add(Detection.PatientBirthDateIsVeryLongAgo.build((birthDateString), target));
		}
			
		return buildResults(issues, passed);
	}
}
