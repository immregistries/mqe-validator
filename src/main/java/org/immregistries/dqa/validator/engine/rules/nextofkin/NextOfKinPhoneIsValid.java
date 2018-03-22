package org.immregistries.dqa.validator.engine.rules.nextofkin;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.common.PhoneValidator;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaNextOfKin;
import org.immregistries.dqa.vxu.VxuField;

import java.util.ArrayList;
import java.util.List;

public class NextOfKinPhoneIsValid extends ValidationRule<DqaNextOfKin> {

	private PhoneValidator phoneValidator = PhoneValidator.INSTANCE;
	
	public NextOfKinPhoneIsValid() {
		this.ruleDetections.addAll(this.codr.getDetectionsForField(VxuField.NEXT_OF_KIN_PHONE));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaNextOfKin target, DqaMessageReceived m) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = true;
		
		issues.addAll(
				phoneValidator.validatePhone(target.getPhone(), VxuField.NEXT_OF_KIN_PHONE, target)
		);
		
		passed = (issues.size() == 0);
		
		return buildResults(issues, passed);
	}

}
