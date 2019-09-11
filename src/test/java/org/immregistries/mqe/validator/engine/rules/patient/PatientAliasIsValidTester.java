package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertFalse;
import java.util.Date;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientAliasIsValidTester {

  private PatientAliasIsPresent rule = new PatientAliasIsPresent();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();

  private static final Logger logger = LoggerFactory.getLogger(PatientAliasIsValidTester.class);

  /**
   * Sets up the objects needed for the test. Next of kin and patient need the same address.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.setPatient(p);
  }

  /**
   * Test the basic rule with a valid address.
   * (should be true)
   */
  @Test
  public void testRuleEmptyName() {
    p.setAliasFirst("");
    ValidationRuleResult rr = rule.executeRule(p, mr);
    logger.info("Detections: "+ rr.getValidationDetections().toString());
    assertFalse(rr.isRulePassed());

    p.setAliasLast("");
    rr = rule.executeRule(p, mr);
    logger.info("Detections: "+ rr.getValidationDetections().toString());
    assertFalse(rr.isRulePassed());
  }

  /**
   * Test the basic rule with a valid address.
   * (should be true)
   */
  @Test
  public void testRuleGoodName() {
    p.setAliasFirst("George");
    ValidationRuleResult rr = rule.executeRule(p, mr);
    logger.info("Detections: "+ rr.getValidationDetections().toString());
    assertFalse(rr.isRulePassed());

    p.setAliasLast("Castanza");
    rr = rule.executeRule(p, mr);
    logger.info("Detections: "+ rr.getValidationDetections().toString());
    assertFalse(rr.isRulePassed());

    p.setAliasFirst("George");
    p.setAliasLast(null);
    rr = rule.executeRule(p, mr);
    logger.info("Detections: "+ rr.getValidationDetections().toString());
    assertFalse(rr.isRulePassed());

    p.setAliasLast("Castanza");
    p.setAliasFirst(null);
    rr = rule.executeRule(p, mr);
    logger.info("Detections: "+ rr.getValidationDetections().toString());
    assertFalse(rr.isRulePassed());
  }
}
