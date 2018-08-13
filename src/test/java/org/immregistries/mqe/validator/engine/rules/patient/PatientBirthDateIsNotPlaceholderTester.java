package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientBirthDateIsNotPlaceholderTester {
	private PatientBirthDateIsValid rule = new PatientBirthDateIsValid();

	// Parts required for the test
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	private static final Logger logger = LoggerFactory.getLogger(PatientAddressIsValidTester.class);

	@Test
	public void testPatientBirthDateIs15th() {
		Calendar cal = Calendar.getInstance();
		// Making sure we are not in the future
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 15);

		p.setBirthDateString(dateFormat.format(cal.getTime()));
		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(r.isRulePassed());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.PatientBirthDateIsOn15ThDayOfMonth, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testPatientBirthDateIsFirstDay() {
		Calendar cal = Calendar.getInstance();
		// Making sure we are not in the future
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		p.setBirthDateString(dateFormat.format(cal.getTime()));

		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(r.isRulePassed());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.PatientBirthDateIsOnFirstDayOfMonth, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testPatientBirthDateIsLastDay() {
		Calendar cal = Calendar.getInstance();
		// Making sure we are not in the future
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));

		p.setBirthDateString(dateFormat.format(cal.getTime()));
		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(r.isRulePassed());
		assertEquals(1, r.getValidationDetections().size());
		assertEquals(Detection.PatientBirthDateIsOnLastDayOfMonth, r.getValidationDetections().get(0).getDetection());
	}

	@Test
	public void testPatientBirthDateIsNotAPlaceholder() {
		Calendar cal = Calendar.getInstance();
		// Making sure we are not in the future
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		
		p.setBirthDateString(dateFormat.format(cal.getTime()));
		ValidationRuleResult r = rule.executeRule(p, mr);
		logger.info(r.getValidationDetections().toString());
		assertTrue(r.isRulePassed());
		assertEquals(0, r.getValidationDetections().size());
	}

}
