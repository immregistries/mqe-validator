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

public class PatientProtectionIndicatorIsValidTester {

	  private PatientProtectionIndicatorIsValid rule = new PatientProtectionIndicatorIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientProtectionIndicatorIsValidTester.class);
	  
	  /**
	   * Tests missing patient protection indicator.
	   */
	  @Test
	  public void testRuleMissingPatientProtectionIndicator() {
		p.setProtectionCode("");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientProtectionIndicatorIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests "yes" patient protection indicator.
	   */
	  @Test
	  public void testRuleYesPatientProtectionIndicator() {
		p.setProtectionCode("Y");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientProtectionIndicatorIsValuedAsYes,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests "no" patient protection indicator.
	   */
	  @Test
	  public void testRuleNoPatientProtectionIndicator() {
		p.setProtectionCode("N");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientProtectionIndicatorIsValuedAsNo,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests unrecognized patient protection indicator.
	   */
	  @Test
	  public void testRuleUnrecognizedPatientProtectionIndicator() {
		p.setProtectionCode("P");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientProtectionIndicatorIsUnrecognized,
		        r.getValidationDetections().get(0).getDetection());
	  }
	
}
