package org.immregistries.mqe.validator.engine.rules.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRule;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;

public class PatientEmailIsPresent extends ValidationRule<MqePatient> {

	public PatientEmailIsPresent() {
		this.addRuleDocumentation(Detection.PatientEmailIsMissing);
		this.addRuleDocumentation(Detection.PatientEmailIsInvalid);
		this.addImplementationMessage(Detection.PatientEmailIsInvalid, "Email address is invalid and must contain letters, numbers, '@' and '.'");
	}

	@Override
	protected ValidationRuleResult executeRule(MqePatient target, MqeMessageReceived m) {
	    List<ValidationReport> issues = new ArrayList<ValidationReport>();
	    boolean passed = false;

	    String patientEmail = target.getEmail();

	    if (this.common.isEmpty(patientEmail)) {
	    	issues.add(Detection.PatientEmailIsMissing.build(target));
	    }
	    else {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
                     
			Pattern pat = Pattern.compile(emailRegex);
			if (!pat.matcher(patientEmail).matches()) {
				issues.add(Detection.PatientEmailIsInvalid.build(target));
			}
	    }

	    passed = (issues.size() == 0);
	    return buildResults(issues, passed);
	}
	
}
