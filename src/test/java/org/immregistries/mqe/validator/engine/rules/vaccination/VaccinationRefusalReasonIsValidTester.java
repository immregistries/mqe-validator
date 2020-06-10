package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationRefusalReasonIsValidTester {

  private VaccinationRefusalReasonIsValid rule = new VaccinationRefusalReasonIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationRefusalReasonIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
  }

  @Test
  public void testNoRefusalReason() {
	v.setCompletionCode("RE");

	v.setRefusalCode(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationRefusalReasonIsMissing,
        r.getValidationDetections().get(0).getDetection());
    
    v.setRefusalCode(" ");
    r = rule.executeRule(v, mr);
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationRefusalReasonIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testRefusalReasonUnrecognized() {
	v.setCompletionCode("RE");
	
	v.setRefusalCode("ABC");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationRefusalReasonIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testRefusalReasonValid() {
	v.setRefusalCode("00");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("This is pass", true, r.isRulePassed());
    
    v.setRefusalCode("02");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("This is pass", true, r.isRulePassed());
    
	v.setCompletionCode("RE");
	v.setRefusalCode("00");
	r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("This is pass", true, r.isRulePassed());
  }
  
  @Test 
  public void testConflictingCompletionStatus() {
	v.setCompletionCode("CP");
	v.setRefusalCode("00");
	ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationRefusalReasonConflictsCompletionStatus,
        r.getValidationDetections().get(0).getDetection());	  
  }
}

