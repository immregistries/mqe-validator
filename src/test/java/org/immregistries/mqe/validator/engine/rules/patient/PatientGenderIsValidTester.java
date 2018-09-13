package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientGenderIsValidTester {
	
	private PatientGenderIsValid rule = new PatientGenderIsValid();

	// Parts required for the test
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();
	
	private static final Logger logger = LoggerFactory.getLogger(PatientAddressIsValidTester.class);
	
	  @Test
	  public void testPatientGenderIsNotRecognized() {
	    this.testGenderFor("Q",Detection.PatientGenderIsUnrecognized);
	    this.testGenderFor("!",Detection.PatientGenderIsUnrecognized);
	    this.testGenderFor("123",Detection.PatientGenderIsUnrecognized);
	  }

	  @Test
	  public void testPatientGenderIsMissing() {
	    this.testGenderFor("", Detection.PatientGenderIsMissing);
	    this.testGenderFor(null, Detection.PatientGenderIsMissing);
	    this.testGenderFor("         ", Detection.PatientGenderIsMissing);
	  }
	
	
	private void testGenderFor(String gender, Detection d) {
		p.setSexCode(gender);
		int expectedDetectionCount = 0;
		boolean passedExpected = true;

		if (d!=null) {
			passedExpected = false;
			expectedDetectionCount = 1;
		}
		ValidationRuleResult r = rule.executeRule(p, mr);
		if (!passedExpected) {
			assertFalse(r.isRulePassed());
		} else {
			assertTrue(r.isRulePassed());
		}
		assertEquals("Gender[" + p.getSexCode()+ "]", expectedDetectionCount,
				r.getValidationDetections().size());

		if (expectedDetectionCount == 1) {
			assertEquals("Expected detection for gender["+gender+"]", d,
					r.getValidationDetections().get(0).getDetection());
		}
	}
}
