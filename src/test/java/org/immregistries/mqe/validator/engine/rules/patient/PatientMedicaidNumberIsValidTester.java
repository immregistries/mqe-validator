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

public class PatientMedicaidNumberIsValidTester {
	
	  private PatientMedicaidNumberIsValid rule = new PatientMedicaidNumberIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientMedicaidNumberIsValidTester.class);
	  
	  /**
	   * Tests empty patient medicaid number.
	   */
	  @Test
	  public void testRuleEmptyPatientMedicaidNumber() {
		p.setIdMedicaidNumber(null);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMedicaidNumberIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient medicaid number (common number).
	   */
	  @Test
	  public void testRuleInvalidPatientMedicaidNumber() {
		p.setIdMedicaidNumber("123456789");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMedicaidNumberIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient medicaid number (common number).
	   */
	  @Test
	  public void testRuleInvalidPatientMedicaidNumber2() {
		p.setIdMedicaidNumber("987654321");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMedicaidNumberIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient medicaid number (same 6 characters in a row).
	   */
	  @Test
	  public void testRuleInvalidPatientMedicaidNumber3() {
		p.setIdMedicaidNumber("77777712");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMedicaidNumberIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient medicaid number (invalid length).
	   */
	  @Test
	  public void testRuleInvalidPatientMedicaidNumber4() {
		p.setIdMedicaidNumber("1234567");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMedicaidNumberIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests valid patient medicaid number.
	   */
	  @Test
	  public void testRuleValidPatientMedicaidNumber() {
		  p.setIdMedicaidNumber("457891027");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
	  
	  /**
	   * Tests valid patient medicaid number.
	   */
	  @Test
	  public void testRuleValidPatientMedicaidNumber2() {
		  p.setIdMedicaidNumber("324567841");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
}
