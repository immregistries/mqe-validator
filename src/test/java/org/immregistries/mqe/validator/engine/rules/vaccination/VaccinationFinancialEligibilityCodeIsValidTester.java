package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.immregistries.mqe.vxu.hl7.Observation;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Allison on 5/9/2017.
 */
public class VaccinationFinancialEligibilityCodeIsValidTester {

  private VaccinationFinancialEligibilityCodeIsValid rule = new VaccinationFinancialEligibilityCodeIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory
      .getLogger(VaccinationFinancialEligibilityCodeIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    v.setFinancialEligibilityCode("V23");
    v.setAdministered(true);
  }

  /**
   * Test the basic rule with a valid type code.
   * (should be true)
   */
  @Test
  public void testRule() {
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  @Test
  public void testRuleEligibilityInvalid() {
	v.setFinancialEligibilityCode("V00");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationFinancialEligibilityCodeIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the rule with an unrecognized type code.
   */
  @Test
  public void testRuleEligibilityUnrecognized() {
	v.setFinancialEligibilityCode("ABDC");

    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationFinancialEligibilityCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testRuleEligibilityMissing() {
	v.setFinancialEligibilityCode(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationFinancialEligibilityCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
    
	v.setFinancialEligibilityCode("  ");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationFinancialEligibilityCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

}
