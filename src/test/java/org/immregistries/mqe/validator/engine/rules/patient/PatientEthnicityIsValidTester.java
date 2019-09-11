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

public class PatientEthnicityIsValidTester {

	private PatientEthnicityIsValid rule = new PatientEthnicityIsValid();

	// Parts required for the test
	private MqeMessageReceived mr = new MqeMessageReceived();
	private MqePatient p = new MqePatient();

	private static final Logger logger = LoggerFactory.getLogger(PatientAddressIsValidTester.class);
	
	  @Test
	  public void testPatientEthnicityIsNotRecognized() {
	    this.testEthnicityFor("!",Detection.PatientEthnicityIsUnrecognized);
	    this.testEthnicityFor("123",Detection.PatientEthnicityIsUnrecognized);
	    this.testEthnicityFor("1234567890",Detection.PatientEthnicityIsUnrecognized);
	  }

	  @Test
	  public void testPatientEthnicityIsMissing() {
	    this.testEthnicityFor("", Detection.PatientEthnicityIsMissing);
	    this.testEthnicityFor(null, Detection.PatientEthnicityIsMissing);
	    this.testEthnicityFor("         ", Detection.PatientEthnicityIsMissing);
	  }
	  
	  @Test
	  public void testPatientEthnicityIsDeprecated() {
	    this.testEthnicityFor("H", Detection.PatientEthnicityIsDeprecated);
	  }

	private void testEthnicityFor(String ethnicity, Detection d) {
		p.setEthnicity(ethnicity);
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
		assertEquals("Ethnicity[" + p.getEthnicity()+ "]", expectedDetectionCount,
				r.getValidationDetections().size());

		if (expectedDetectionCount == 1) {
			assertEquals("Expected detection for ethnicity["+ethnicity+"]", d,
					r.getValidationDetections().get(0).getDetection());
		}
	}
}
