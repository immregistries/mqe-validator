package org.immregistries.dqa.validator.engine.rules.header;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;

import java.util.ArrayList;
import java.util.List;

public class MessageVersionIs25 extends ValidationRule<DqaMessageHeader> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
				MessageVersionIsValid.class
		};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target,
			DqaMessageReceived mr) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		String version = target.getMessageVersion();
		
		boolean passed = version != null && version.startsWith("2.5");

		return buildResults(issues, passed);
	}

}
