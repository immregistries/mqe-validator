package org.immregistries.dqa.validator.engine.rules.header;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.ValidationDetection;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageVersionIsValid extends ValidationRule<DqaMessageHeader> {

	private static final Logger logger = LoggerFactory
			.getLogger(MessageVersionIsValid.class);
	
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
		// PatientExists.class,
		};
	}

	public MessageVersionIsValid() {
		this.ruleDetections.addAll(Arrays.asList(Detection.MessageVersionIsMissing,
				Detection.MessageVersionIsUnrecognized));
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target,
			DqaMessageReceived mr) {

		List<ValidationDetection> issues = new ArrayList<ValidationDetection>();
		boolean passed = false;
		
		String version = target.getMessageVersion();
		
		if (this.common.isEmpty(version)) {
			issues.add(Detection.MessageVersionIsMissing.build(target));
		} else {
			//We want to evaluate the starting three characters...  2.5.1 should evaluate as 2.5, etc. 
			String evalVersion = version;
			if (evalVersion.length() > 3) {
				evalVersion = evalVersion.substring(0,3);
//				logger.info("eval version: " + evalVersion);
			}
			switch (evalVersion) {
				case "2.5":
				case "2.4":
				case "2.3":
					passed = true;
					break;
				default:
					issues.add(Detection.MessageVersionIsUnrecognized.build((version), target));
			}
		}

		return buildResults(issues, passed);
	}

}
