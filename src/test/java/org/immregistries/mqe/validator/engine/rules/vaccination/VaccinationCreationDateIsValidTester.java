package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationCreationDateIsValidTester {

	private VaccinationCreationDateIsValid rule = new VaccinationCreationDateIsValid();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	private MqeVaccination vax = new MqeVaccination();
	
	private static final Logger logger = LoggerFactory.getLogger(VaccinationCreationDateIsValidTester.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Sets up the objects with valid information before EACH TEST.
	 */
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
		mr.getVaccinations().add(vax);
	}

	@Test
	public void testMissing() {
		vax.setSystemEntryDate(null);
		vax.setSystemEntryDateString(null);
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationSystemEntryDateIsMissing, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testInvalid() {
		vax.setSystemEntryDateString("xxxxx");
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationSystemEntryDateIsInvalid, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testFuture() {
		vax.setSystemEntryDateString(format.format(new DateTime().plusDays(1).toDate()));
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationSystemEntryDateIsInTheFuture, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testValid() {
		vax.setSystemEntryDateString(format.format(new Date()));
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(0, r.getValidationDetections().size());
		assert(r.isRulePassed());
	}

}
