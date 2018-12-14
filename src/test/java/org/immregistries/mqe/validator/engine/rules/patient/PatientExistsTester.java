package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientExistsTester {
	private PatientExists rule = new PatientExists();
	
	// Parts required for the test
	private MqeMessageHeader mh = new MqeMessageHeader();
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();

	private static final Logger logger = LoggerFactory.getLogger(PatientExistsTester.class);
	
	@Test
	public void testPresent() {
		List<MqePatient> noks = new ArrayList<>();
		noks.add(p);
		mr.setPatient(p);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertTrue(r.isRulePassed());
	}

	@Test
	public void testNotPresent() {
		mr.setPatient(new MqePatient());
		ValidationRuleResult r = rule.executeRule(null, mr);
		assertTrue(!r.isRulePassed());
	}
}
