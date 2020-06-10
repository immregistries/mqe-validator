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

public class VaccinationCreationTimelinessTester {

	private VaccinationCreationTimeliness rule = new VaccinationCreationTimeliness();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	private MqeVaccination vax = new MqeVaccination();
	
	private static final Logger logger = LoggerFactory.getLogger(VaccinationCreationTimelinessTester.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	private static Date admin = new Date();
	private static Date w1 = new DateTime(admin).plusDays(3).toDate();
	private static Date w2 = new DateTime(admin).plusDays(10).toDate();
	private static Date w3 = new DateTime(admin).plusDays(20).toDate();
	private static Date w4 = new DateTime(admin).plusDays(40).toDate();
	
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
		mr.getVaccinations().add(vax);
		vax.setAdminDate(admin);
	}

	@Test
	public void testWindowOne() {
		vax.setSystemEntryDate(w1);
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCreationIsOnTime, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testWindowTwo() {
		vax.setSystemEntryDate(w2);
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCreationIsLate, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testWindowThree() {
		vax.setSystemEntryDate(w3);
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCreationIsVeryLate, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testWindowFour() {
		vax.setSystemEntryDate(w4);
		
		ValidationRuleResult r = rule.executeRule(vax, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCreationIsTooLate, r.getValidationDetections().get(0).getDetection());
	}

}
