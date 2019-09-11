package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationCptIsValidTester {

	private VaccinationCptIsValid rule = new VaccinationCptIsValid();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	private MqeVaccination v = new MqeVaccination();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	private static final Logger logger = LoggerFactory.getLogger(VaccinationCptIsValidTester.class);

	/**
	 * Sets up the objects with valid information before EACH TEST.
	 */
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
		mr.getVaccinations().add(v);
	}

	@Test
	public void testInvalid() {
		v.setAdminCptCode("112234555");
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.VaccinationCptCodeIsUnrecognized, r.getValidationDetections().get(0).getDetection());
	}
	

	@Test
	public void testValid() {
		v.setAdminCptCode("90665");
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(0, r.getValidationDetections().size());
		assert(r.isRulePassed());
	}
	
	@Test
	public void testValidDate() {
		v.setAdminCptCode("90665");
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, 2000);
		v.setAdminDateString(dateFormat.format(cal.getTime()));
		v.setAdminDate(cal.getTime());
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(0, r.getValidationDetections().size());
		assert(r.isRulePassed());
	}
	
	@Test
	public void testInvalidDate() {
		v.setAdminCptCode("90705");
		
//        <not-before>19630101</not-before>
//        <not-expected-after>20100831</not-expected-after>
//        <not-after>20100831</not-after>
		
		// invalid expected
		Calendar cal = Calendar.getInstance(); 
		v.setAdminDateString(dateFormat.format(cal.getTime()));
		v.setAdminDate(cal.getTime());
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertTrue(r.issuesContainsDetection(Detection.VaccinationCptCodeIsUnexpectedForDateAdministered));
		
		// invalid license
		cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, 1960);
		v.setAdminDateString(dateFormat.format(cal.getTime()));
		v.setAdminDate(cal.getTime());
		r = rule.executeRule(v, mr);
		assertTrue(r.issuesContainsDetection(Detection.VaccinationCptCodeIsInvalidForDateAdministered));
		
		cal = Calendar.getInstance(); 
		v.setAdminDateString(dateFormat.format(cal.getTime()));
		v.setAdminDate(cal.getTime());
		r = rule.executeRule(v, mr);
		assertTrue(r.issuesContainsDetection(Detection.VaccinationCptCodeIsInvalidForDateAdministered));
	}

}
