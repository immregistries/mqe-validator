package org.immregistries.dqa.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.engine.issues.ValidationIssue;
import org.immregistries.dqa.validator.model.DqaMessageHeader;
import org.immregistries.dqa.validator.model.DqaMessageReceived;

public class MessageVersionIs25 extends ValidationRule<DqaMessageHeader> {

	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
				MessageVersionIsValid.class,
		};
	}

	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target,
			DqaMessageReceived mr) {

		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		String version = target.getMessageVersion();
		
		boolean passed = version != null && version.startsWith("2.5");

		return buildResults(issues, passed);
	}

}
