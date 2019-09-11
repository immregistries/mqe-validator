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

public class PatientNameTypeIsValidTester {

	  private PatientNameTypeIsValid rule = new PatientNameTypeIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientNameTypeIsValidTester.class);
	  
	  /**
	   * Tests missing patient name type code.
	   */
	  @Test
	  public void testRuleMissingPatientNameTypeCode() {
		p.setNameTypeCode("");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameTypeCodeIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	
	  /**
	   * Tests valid patient name type code.
	   */
	  @Test
	  public void testRuleValidPatientNameTypeCode() {
		p.setNameTypeCode("P");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
}
