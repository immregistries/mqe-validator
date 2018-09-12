package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.immregistries.mqe.validator.TestMessageGenerator;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.MessageValidator;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.parse.HL7MessageParser;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationCompletionStatusIsValidTester {

	private VaccinationCompletionStatusIsValid rule = new VaccinationCompletionStatusIsValid();

	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	private MqeVaccination v = new MqeVaccination();
	
	private static final Logger logger = LoggerFactory.getLogger(VaccinationCompletionStatusIsValidTester.class);

	/**
	 * Sets up the objects with valid information before EACH TEST.
	 */
	@Before
	public void setUpTheObjects() {
		mr.setPatient(p);
		mr.getVaccinations().add(v);
		v.setCompletionCode("CP");
	}

	@Test
	public void testInvalid() {
		v.setCompletionCode(null);
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(!r.isRulePassed());
		assertEquals(Detection.VaccinationCompletionStatusIsMissing, r.getValidationDetections().get(0).getDetection());
}

	@Test
	public void testValid() {
		v.setCompletionCode("AA");
		ValidationRuleResult r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCompletionStatusIsUnrecognized, r.getValidationDetections().get(0).getDetection());
		
		v.setCompletionCode("CP");
		r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCompletionStatusIsValuedAsCompleted, r.getValidationDetections().get(0).getDetection());
		
		v.setCompletionCode(null);
		r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCompletionStatusIsValuedAsNotAdministered, r.getValidationDetections().get(0).getDetection());
		
		v.setCompletionCode(null);
		r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCompletionStatusIsValuedAsPartiallyAdministered, r.getValidationDetections().get(0).getDetection());

		v.setCompletionCode(null);
		r = rule.executeRule(v, mr);
		assertEquals(1, r.getValidationDetections().size());
		assert(r.isRulePassed());
		assertEquals(Detection.VaccinationCompletionStatusIsValuedAsRefused, r.getValidationDetections().get(0).getDetection());

	}

}
