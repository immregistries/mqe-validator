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

public class PatientSubmitterIsValidTester {

	  private PatientSubmitterIsValid rule = new PatientSubmitterIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientSubmitterIsValidTester.class);
	  
	  /**
	   * Tests missing patient submitter id.
	   */
	  @Test
	  public void testRuleMissingPatientSubmitterId() {
		p.setIdSubmitterNumber("");
		p.setIdSubmitterAssigningAuthorityCode("P");
		p.setIdSubmitterTypeCode("P");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientSubmitterIdIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient submitter authority code.
	   */
	  @Test
	  public void testRuleMissingPatientSubmitterAuthorityCode() {
		p.setIdSubmitterNumber("");
		p.setIdSubmitterAssigningAuthorityCode("");
		p.setIdSubmitterTypeCode("P");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(2, r.getValidationDetections().size());
	    assertEquals(Detection.PatientSubmitterIdAuthorityIsMissing,
	        r.getValidationDetections().get(1).getDetection());
	  }
	  
	  /**
	   * Tests missing patient submitter type code.
	   */
	  @Test
	  public void testRuleMissingPatientSubmitterTypeCode() {
		p.setIdSubmitterNumber("");
		p.setIdSubmitterAssigningAuthorityCode("P");
		p.setIdSubmitterTypeCode("");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(2, r.getValidationDetections().size());
	    assertEquals(Detection.PatientSubmitterIdTypeCodeIsMissing,
	        r.getValidationDetections().get(1).getDetection());
	  }
	  
	  /**
	   * Tests valid patient submitter.
	   */
	  @Test
	  public void testRuleValidPatientSubmitter() {
		p.setIdSubmitterNumber("12345");
		p.setIdSubmitterAssigningAuthorityCode("P");
		p.setIdSubmitterTypeCode("P");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
}
