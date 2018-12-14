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

public class PatientSsnIsValidTester {

	  private PatientSsnIsValid rule = new PatientSsnIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientSsnIsValidTester.class);
	
	  /**
	   * Tests missing patient social security number.
	   */
	  @Test
	  public void testRuleMissingPatientMedicaidNumber() {
		p.setIdSsnNumber("");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientSsnIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient social security number.
	   */
	  @Test
	  public void testRuleInvalidPatientMedicaidNumber() {
		p.setIdSsnNumber("123456789");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientSsnIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient social security number.
	   */
	  @Test
	  public void testRuleInvalidPatientMedicaidNumber2() {
		p.setIdSsnNumber("abc");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientSsnIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests valid patient social security number.
	   */
	  @Test
	  public void testRulValidPatientMedicaidNumber() {
		p.setIdSsnNumber("485795127");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
}
