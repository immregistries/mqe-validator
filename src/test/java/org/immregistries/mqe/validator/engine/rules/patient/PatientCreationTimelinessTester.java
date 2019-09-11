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

public class PatientCreationTimelinessTester {

	private PatientCreationTimeliness rule = new PatientCreationTimeliness();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	
	private static final Logger logger = LoggerFactory.getLogger(PatientCreationTimelinessTester.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	private static Date dob = new Date();
	private static Date w1 = new DateTime(dob).plusDays(30).toDate();
	private static Date w2 = new DateTime(dob).plusDays(40).toDate();
	private static Date w3 = new DateTime(dob).plusDays(60).toDate();
	private static Date w4 = new DateTime(dob).plusDays(100).toDate();
	
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
		p.setBirthDate(dob);
	}

	@Test
	public void testWindowOne() {
		p.setSystemEntryDate(w1);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.PatientCreationIsOnTime, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testWindowTwo() {
		p.setSystemEntryDate(w2);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.PatientCreationIsLate, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testWindowThree() {
		p.setSystemEntryDate(w3);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.PatientCreationIsVeryLate, r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testWindowFour() {
		p.setSystemEntryDate(w4);
		
		ValidationRuleResult r = rule.executeRule(p, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.PatientCreationIsTooLate, r.getValidationDetections().get(0).getDetection());
	}

}
