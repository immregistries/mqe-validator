package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
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

public class VaccinationAdminDateValidForPatientAgeTester {

	private VaccinationAdminDateIsValidForPatientAge vaccinationAdminDateIsValidForPatientAge = new VaccinationAdminDateIsValidForPatientAge();

	// Parts required for the test
	private MqeMessageHeader mh = new MqeMessageHeader();
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqeVaccination v = new MqeVaccination();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

  private static final Logger logger = LoggerFactory.getLogger(VaccinationAdminDateValidForPatientAgeTester.class);
	/**
	 * Sets up the objects needed for the test.
	 * Sets Birthdate as today
	 */
	@Before
	public void setUpTheObjects() {
		mh.setMessageDate(new Date());
		mr.setMessageHeader(mh);
		mr.getVaccinations().add(v);
	}

	@Test
	public void testNotValidAdminDateForAge() {
		Calendar adminCal = Calendar.getInstance(); 
		v.setAdminDateString(dateFormat.format(adminCal.getTime()));
		v.setAdminCvxCode("28");
		
		Calendar bdayCal = Calendar.getInstance(); 
		adminCal.add(Calendar.DAY_OF_MONTH, -5);
		mr.getPatient().setBirthDateString(dateFormat.format(bdayCal.getTime()));

		// patient birthday 5 days ago - administered today
		ValidationRuleResult r = vaccinationAdminDateIsValidForPatientAge.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge,
		        r.getValidationDetections().get(0).getDetection());
		
		bdayCal = Calendar.getInstance(); 
		adminCal.add(Calendar.MONTH, -86);
		mr.getPatient().setBirthDateString(dateFormat.format(bdayCal.getTime()));

		// patient birthday 86 months - administered today
		r = vaccinationAdminDateIsValidForPatientAge.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.VaccinationAdminDateIsBeforeOrAfterWhenExpectedForPatientAge,
		        r.getValidationDetections().get(0).getDetection());
	}
	
	@Test
	public void testValidAdminDateForAge() {
		Calendar cal = Calendar.getInstance(); 
		v.setAdminDateString(dateFormat.format(cal.getTime()));
		v.setAdminCvxCode("28");
		
		Calendar bdayCal = Calendar.getInstance(); 
		bdayCal.add(Calendar.MONTH, -2);
		mr.getPatient().setBirthDateString(dateFormat.format(bdayCal.getTime()));

		// patient birthday 2 months ago - administered today
		ValidationRuleResult r = vaccinationAdminDateIsValidForPatientAge.executeRule(v, mr);
		logger.info(r.getValidationDetections().toString());
		assertEquals(0, r.getValidationDetections().size());
	}

}
