package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientBirthPlaceIsValidTester {
	
	  private PatientBirthPlaceIsValid rule = new PatientBirthPlaceIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientBirthPlaceIsValidTester.class);
	  
	  /**
	   * Tests empty patient birth place.
	   */
	  @Test
	  public void testRuleEmptyPatientBirthPlace() {
		p.setBirthPlace(null);
		

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientBirthPlaceIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests patient birth place exists.
	   * (should be true)
	   */
	  @Test
	  public void testRulePatientBirthPlace() {
		p.setBirthPlace("CALIFORNIA");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertTrue(r.isRulePassed());
	  }
}
