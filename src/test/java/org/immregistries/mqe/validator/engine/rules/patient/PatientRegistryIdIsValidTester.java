package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientRegistryIdIsValidTester {

	  private PatientRegistryIdIsValid rule = new PatientRegistryIdIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientRegistryIdIsValidTester.class);
	  
	  /**
	   * Tests unrecognized patient registry id.
	   */
	  @Test
	  public void testRuleUnrecognizedPatientRegistryId() {
		p.setIdRegistryNumber("12345");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientRegistryIdIsUnrecognized,
	        r.getValidationDetections().get(0).getDetection());
	  }
	
}
