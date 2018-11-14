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

public class PatientNameIsValidTester {

	  private PatientNameIsValid rule = new PatientNameIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientNameIsValidTester.class);
	  
	  /**
	   * Tests missing patient first name.
	   */
	  @Test
	  public void testRuleMissingPatientFirstName() {
		p.setNameFirst("");
		p.setNameLast("costanza");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameFirstIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient first name (on known list).
	   */
	  @Test
	  public void testRuleInvalidPatientFirstName() {
		p.setNameFirst("NO NAME");
		p.setNameLast("costanza");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameHasJunkName,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient first name (invalid characters).
	   */
	  @Test
	  public void testRuleInvalidPatientFirstName2() {
		p.setNameFirst("7");
		p.setNameLast("costanza");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameFirstIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient first name (invalid characters).
	   */
	  @Test
	  public void testRuleInvalidPatientFirstName3() {
		p.setNameFirst("UK");
		p.setNameLast("costanza");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameFirstIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient first name (first name may contain middle initial).
	   */
	  @Test
	  public void testRuleInvalidPatientFirstName4() {
		p.setNameFirst("george l");
		p.setNameMiddle("");
		p.setNameLast("costanza");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameFirstMayIncludeMiddleInitial,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient first name (test patient name).
	   */
	  @Test
	  public void testRuleInvalidPatientFirstName5() {
		p.setNameFirst("MICKY");
		p.setNameLast("MOUSE");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameMayBeTestName,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient first name (temporary newborn name).
	   */
	  @Test
	  public void testRuleInvalidPatientFirstName6() {
		p.setNameFirst("BABY");
		p.setNameLast("GIRL");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(2, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameMayBeTemporaryNewbornName,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient last name.
	   */
	  @Test
	  public void testRuleMissingPatientLastName() {
		p.setNameFirst("george");
		p.setNameLast("");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameLastIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient last name.
	   */
	  @Test
	  public void testRuleInvalidPatientLastName() {
		p.setNameFirst("george");
		p.setNameLast("NONE");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(2, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameLastIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient last name (invalid characters).
	   */
	  @Test
	  public void testRuleInvalidPatientLastName2() {
		p.setNameFirst("george");
		p.setNameLast("7");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientNameLastIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests valid patient first name.
	   */
	  @Test
	  public void testRuleValidPatientFirstName() {
		p.setNameFirst("george");
		p.setNameLast("costanza");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
	
}
