package org.immregistries.mqe.validator.engine.rules.nextofkin;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextOfKinIsPresentTester {
	private NextOfKinIsPresent rule = new NextOfKinIsPresent();

	// Parts required for the test
	private MqeMessageHeader mh = new MqeMessageHeader();
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqeNextOfKin nok = new MqeNextOfKin();
	private MqePatient p = new MqePatient();

	private static final Logger logger = LoggerFactory.getLogger(NextOfKinIsPresentTester.class);

	@Test
	public void testPresent() {
		List<MqeNextOfKin> noks = new ArrayList<>();
		noks.add(nok);
		mr.setNextOfKins(noks);
		
		ValidationRuleResult r = rule.executeRule(nok, mr);
		assertTrue(r.isRulePassed());
	}

	@Test
	public void testNotPresent() {
		mr.setNextOfKins(new ArrayList<MqeNextOfKin>());
		ValidationRuleResult r = rule.executeRule(null, mr);
		assertTrue(!r.isRulePassed());
	}
}
