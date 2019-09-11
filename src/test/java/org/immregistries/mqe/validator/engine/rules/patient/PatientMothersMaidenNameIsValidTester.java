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

public class PatientMothersMaidenNameIsValidTester {

	  private PatientMothersMaidenNameIsValid rule = new PatientMothersMaidenNameIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientMothersMaidenNameIsValidTester.class);
	  
	  /**
	   * Tests empty patient mother's maiden name (length = 1).
	   */
	  @Test
	  public void testRuleMissingPatientMothersMaidenName() {
		p.setMotherMaidenName("");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMotherSMaidenNameIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient mother's maiden name.
	   */
	  @Test
	  public void testRuleInvalidPatientMothersMaidenName2() {
		  p.setMotherMaidenName("NONE");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMothersMaidenNameIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient mother's maiden name (has junk).
	   */
	  @Test
	  public void testRuleInvalidPatientMothersMaidenName3() {
	    p.setMotherMaidenName("NO LASTNAME");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMotherSMaidenNameHasJunkName,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient mother's maiden name (invalid prefix).
	   */
	  @Test
	  public void testRuleInvalidPatientMothersMaidenName4() {
	    p.setMotherMaidenName("ZZ");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMotherSMaidenNameHasInvalidPrefixes,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests invalid patient mother's maiden name (length = 1).
	   */
	  @Test
	  public void testRuleInvalidPatientMothersMaidenName5() {
	    p.setMotherMaidenName("a");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientMotherSMaidenNameIsTooShort,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests valid patient mother's maiden name.
	   */
	  @Test
	  public void testRuleValidPatientMothersMaidenName() {
	    p.setMotherMaidenName("george");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
	  
	  /**
	   * Tests valid patient mother's maiden name.
	   */
	  @Test
	  public void testRuleValidPatientMothersMaidenName2() {
	    p.setMotherMaidenName("jerry");
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
	
}
