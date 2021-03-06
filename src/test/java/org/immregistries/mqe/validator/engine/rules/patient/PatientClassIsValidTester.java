package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Date;
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
public class PatientClassIsValidTester {

  private PatientClassIsValid rule = new PatientClassIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();

  private static final Logger logger = LoggerFactory.getLogger(PatientClassIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    p.setPatientClassCode("R"); // R = recurring

    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.setPatient(p);
  }

  /**
   * Test the basic rule, with a valid class code
   * (should be true)
   */
  @Test
  public void testRule() {
    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  /**
   * Test with null class code
   * (should be false)
   */
  @Test
  public void testRuleNullCode() {
    p.setPatientClassCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
  }

  /**
   * Test with empty class code
   * (should be false)
   */
  @Test
  public void testRuleEmptyCode() {
    p.setPatientClassCode("");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
  }

  /**
   * Test with unrecognized class code
   * (should be false)
   */
  @Test
  public void testRuleUnrecognizedCode() {
    p.setPatientClassCode("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
  }
}
