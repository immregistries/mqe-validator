package org.immregistries.dqa.validator.engine.rules.header;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.validator.engine.ValidationRule;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.validator.issue.ValidationIssue;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHeaderCodesAreValid extends ValidationRule<DqaMessageHeader> {
	private static final Logger logger = LoggerFactory
			.getLogger(MessageHeaderCodesAreValid.class);
	
	@Override
	protected final Class[] getDependencies() {
		return new Class[] {
//			PatientExists.class, 
		};
	}
	
	@Override
	protected ValidationRuleResult executeRule(DqaMessageHeader target, DqaMessageReceived mr) {
		
		List<ValidationIssue> issues = new ArrayList<ValidationIssue>();
		boolean passed = true;
		
//		String ackTypeAccept = target.getAckTypeAcceptCode();
//		logger.info("getAckTypeAcceptCode:"+ackTypeAccept);
//		issues.addAll(codr.handleCode(ackTypeAccept, IssueField.MESSAGE_ACCEPT_ACK_TYPE));
//		
//		String ackTypeApp = target.getAckTypeApplicationCode();
//		logger.info("getAckTypeApplicationCode:"+ackTypeApp);
//		issues.addAll(codr.handleCode(ackTypeApp, IssueField.MESSAGE_APP_ACK_TYPE));
		
		
		return buildResults(issues, passed);
	}


}
