package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationAdminAfterBirthDateTester {

	private VaccinationAdminAfterBirthDate vaccinationAdminAfterBirthDate = new VaccinationAdminAfterBirthDate();

	// Parts required for the test
	private MqeMessageHeader mh = new MqeMessageHeader();
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory
	      .getLogger(VaccinationAdminAfterBirthDateTester.class);
	/**
	 * Sets up the objects needed for the test.
	 * Sets Birthdate as today
	 */
	@Before
	public void setUpTheObjects() {
		mh.setMessageDate(new Date());
		mr.setMessageHeader(mh);
		mr.getVaccinations().add(v);
		
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		mr.getPatient().setBirthDate(cal.getTime());
	}

	@Test
	public void isAdminAfterBirthDate() {
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, 1);
		v.setAdminDate(cal.getTime());

		// admin after - pass
		ValidationRuleResult r = vaccinationAdminAfterBirthDate.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(0, r.getValidationDetections().size());
		
		// admin before - fail
		cal = Calendar.getInstance(); 
		cal.add(Calendar.MONTH, -1);
		v.setAdminDate(cal.getTime());

		r = vaccinationAdminAfterBirthDate.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(1, r.getValidationDetections().size());
	}
	
	@Test
	public void isAdminOnBirthDate() {
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		v.setAdminDate(cal.getTime());

		ValidationRuleResult r = vaccinationAdminAfterBirthDate.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(0, r.getValidationDetections().size());
	}

}
