package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Allison on 5/9/2017.
 */
public class PatientMultipleBirthsValidTester {

  private PatientMultipleBirthsValid rule = new PatientMultipleBirthsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();

  private static final Logger logger = LoggerFactory
      .getLogger(PatientMultipleBirthsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    p.setBirthMultipleIndicator("N");

    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.setPatient(p);
  }

  /**
   * Test the basic rule, with a valid multiple birth indicator (N indicator + no birth order)
   * (should be true)
   */
  @Test
  public void testRule() {
    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  /**
   * Test the basic rule, with a valid multiple birth indicator (Y indicator + birth order)
   * (should be true)
   */
  @Test
  public void testRuleIfMultiple() {
    p.setBirthMultipleIndicator("Y");
    p.setBirthOrder("2");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  /**
   * Test without multiple birth (N indicator) BUT with the birth order set to 1
   * (should be true)
   */
  @Test
  public void testRuleIfFirstChild() {
    p.setBirthOrder("1");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  /**
   * Test with birth order but multiple birth indicator = N
   * (should be false)
   */
  @Test
  public void testRuleIfNoIndicator() {
    p.setBirthOrder("2");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientBirthOrderIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with multiple birth indicator but no order
   * (should be false)
   */
  @Test
  public void testRuleIfNoOrder() {
    p.setBirthMultipleIndicator("Y");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    // should be both PatientBirthOrderIsMissing and PatientBirthOrderIsMissingAndMultipleBirthIndicated
  }

  /**
   * Test with birth order but null multiple birth indicator
   * (should be false)
   */
  @Test
  public void testRuleIfNullIndicator() {
    p.setBirthMultipleIndicator(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientBirthIndicatorIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with birth order but empty multiple birth indicator
   * (should be false)
   */
  @Test
  public void testRuleIfEmptyIndicator() {
    p.setBirthMultipleIndicator("");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientBirthIndicatorIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with invalid multiple birth indicator
   * (should be false)
   */
  @Test
  public void testRuleIfInvalidIndicator() {
    p.setBirthMultipleIndicator("abc");
    p.setBirthOrder("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientBirthIndicatorIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with invalid birth order number
   * (should be false)
   */
  @Test
  public void testRuleIfInvalidOrder() {
    p.setBirthMultipleIndicator("Y");
    p.setBirthOrder("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    assertEquals("should have one issue", 1, r.getValidationDetections().size());
    assertEquals("the issue should be this one: ", Detection.PatientBirthOrderIsUnknown,
        r.getValidationDetections().get(0).getDetection());
  }
}
