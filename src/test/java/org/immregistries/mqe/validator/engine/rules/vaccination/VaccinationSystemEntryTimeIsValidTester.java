package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationSystemEntryTimeIsValidTester {

	private VaccinationSystemEntryTimeIsValid rule = new VaccinationSystemEntryTimeIsValid();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqeVaccination v = new MqeVaccination();
	
	private static final Logger logger = LoggerFactory.getLogger(VaccinationSystemEntryTimeIsValidTester.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Sets up the objects with valid information before EACH TEST.
	 */
	@Before
	public void setUpTheObjects() {
		mr.getVaccinations().add(v);
		
		DateTime receivedDate = new DateTime();
		mr.setReceivedDate(receivedDate.toDate());
	}

	@Test
	public void testMissing() {
		v.setSystemEntryDate(null);
		v.setSystemEntryDateString(null);
		
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationSystemEntryTimeIsMissing, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testInvalid() {
		v.setSystemEntryDateString("xxxxx");
		
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationSystemEntryTimeIsInvalid, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testFuture() {
		DateTime entryDate = new DateTime().plusDays(1);
		
		v.setSystemEntryDate(entryDate.toDate());
		v.setSystemEntryDateString(format.format(entryDate.toDate()));
		
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationSystemEntryTimeIsInFuture, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testValid() {
		DateTime entryDate = new DateTime();
		
		v.setSystemEntryDate(entryDate.toDate());
		v.setSystemEntryDateString(format.format(entryDate.toDate()));
		
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(0, r.getValidationDetections().size());
		assert(r.isRulePassed());
	}

}
