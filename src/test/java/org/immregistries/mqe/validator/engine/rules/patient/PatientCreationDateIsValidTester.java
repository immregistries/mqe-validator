package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientCreationDateIsValidTester {

	private PatientCreationDateIsValid rule = new PatientCreationDateIsValid();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();

	private static final Logger logger = LoggerFactory.getLogger(PatientCreationDateIsValidTester.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Sets up the objects with valid information before EACH TEST.
	 */
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
	}

	@Test
	public void testMissing() {
		p.setSystemEntryDate(null);
		p.setSystemEntryDateString(null);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.PatientSystemEntryDateIsMissing, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testInvalid() {
		p.setSystemEntryDateString("xxxxx");
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.PatientSystemEntryDateIsInvalid, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testFuture() {
		p.setSystemEntryDateString(format.format(new DateTime().plusDays(1).toDate()));
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.PatientSystemEntryDateIsInTheFuture, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testValid() {
		p.setSystemEntryDateString(format.format(new Date()));
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(0, r.getValidationDetections().size());
		assert(r.isRulePassed());
	}

}
