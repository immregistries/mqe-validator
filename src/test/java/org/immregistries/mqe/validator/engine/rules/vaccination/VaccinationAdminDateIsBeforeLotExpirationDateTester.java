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

public class VaccinationAdminDateIsBeforeLotExpirationDateTester {

	private VaccinationAdminDateIsBeforeLotExpirationDate vaccinationAdminDateIsBeforeLotExpirationDate = new VaccinationAdminDateIsBeforeLotExpirationDate();

	// Parts required for the test
	private MqeMessageHeader mh = new MqeMessageHeader();
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqeVaccination v = new MqeVaccination();

	private static final Logger logger = LoggerFactory.getLogger(VaccinationAdminDateIsBeforeLotExpirationDateTester.class);

	/**
	 * Sets up the objects needed for the test.
	 */
	@Before
	public void setUpTheObjects() {
		mh.setMessageDate(new Date());
		mr.setMessageHeader(mh);
		mr.getVaccinations().add(v);
	}

	/**
	 * Test the vaccinationCvxIsValid with a null type code.
	 */
	@Test
	public void testPass() {
		Calendar cal = Calendar.getInstance(); 
		v.setAdminDate(cal.getTime());
		cal.add(Calendar.MONTH, 1);
		v.setExpirationDate(cal.getTime());
		

		ValidationRuleResult r = vaccinationAdminDateIsBeforeLotExpirationDate.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(0, r.getValidationDetections().size());
	}
	
	@Test
	public void testFail() {
		Calendar cal = Calendar.getInstance(); 
		v.setExpirationDate(cal.getTime());
		cal.add(Calendar.MONTH, 1);
		v.setAdminDate(cal.getTime());
		
		ValidationRuleResult r = vaccinationAdminDateIsBeforeLotExpirationDate.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.VaccinationAdminDateIsAfterLotExpirationDate, r.getValidationDetections().get(0).getDetection());
	}

}
