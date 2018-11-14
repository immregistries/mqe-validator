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

public class PatientMiddleNameIsValidTester {

	  private PatientMiddleNameIsValid rule = new PatientMiddleNameIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientMiddleNameIsValidTester.class);
	  
	  /**
	   * Tests invalid patient middle name (length = 1).
	   */
	  @Test
	  public void testRuleInvalidPatientMiddleName() {
		p.setNameMiddle("a");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMiddleNameMayBeInitial,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient middle name (ends with a ".").
	   */
	  @Test
	  public void testRuleInvalidPatientMiddleName2() {
		p.setNameMiddle("55.");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameMiddleIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient middle name (on the known name list).
	   */
	  @Test
	  public void testRuleInvalidPatientMiddleName3() {
		p.setNameMiddle("NONE");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameMiddleIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests valid patient middle name.
	   */
	  @Test
	  public void testRuleValidPatientMiddleName() {
		p.setNameMiddle("george");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
	
}
