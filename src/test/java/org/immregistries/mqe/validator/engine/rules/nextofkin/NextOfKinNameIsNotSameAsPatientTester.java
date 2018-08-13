package org.immregistries.mqe.validator.engine.rules.nextofkin;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextOfKinNameIsNotSameAsPatientTester {
	private NextOfKinNameIsNotSameAsPatient rule = new NextOfKinNameIsNotSameAsPatient();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqeNextOfKin nok = new MqeNextOfKin();
	private MqePatient p = new MqePatient();

	private static final Logger logger = LoggerFactory.getLogger(NextOfKinAddressIsValidTester.class);

	@Before
	public void setUpTheObjects() {
		nok.setNameFirst("John");
		nok.setNameLast("Doe");
		nok.setNameMiddle("M");
		nok.setNameSuffix("S");
		nok.setRelationshipCode("MTH");

		p.setNameFirst("John");
		p.setNameLast("Doe");
		p.setNameMiddle("M");
		p.setNameSuffix("S");
		
		List<MqeNextOfKin> noks = new ArrayList<>();
		noks.add(nok);
		mr.setNextOfKins(noks);
		mr.setPatient(p);
	}

	@Test
	public void testSameAndNk1Responsible() {
		ValidationRuleResult r = rule.executeRule(nok, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.PatientGuardianNameIsSameAsUnderagePatient, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testSameAndNk1NotResponsible() {
		nok.setRelationshipCode("BRO");
		ValidationRuleResult r = rule.executeRule(nok, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(0, r.getValidationDetections().size());
	}

	@Test
	public void testNotSameMissingPart() {
		nok.setNameSuffix(null);
		ValidationRuleResult r = rule.executeRule(nok, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(0, r.getValidationDetections().size());
	}
	
	@Test
	public void testNotSameDifferentPart() {
		nok.setNameFirst("Jane");
		ValidationRuleResult r = rule.executeRule(nok, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(0, r.getValidationDetections().size());
	}

	
}
