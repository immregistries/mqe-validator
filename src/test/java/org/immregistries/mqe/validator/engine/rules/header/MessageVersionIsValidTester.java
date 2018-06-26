package org.immregistries.mqe.validator.engine.rules.header;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageVersionIsValidTester {
  private MessageVersionIsValid rule = new MessageVersionIsValid();
  
  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  
  private static final Logger logger = LoggerFactory.getLogger(MessageVersionIsValidTester.class);

  @Test
  public void testRuleValid() {
    mh.setMessageVersion("2.5.1");
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }  
  
  @Test
  public void testRuleNull() {
    mh.setMessageVersion(null);
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
    assertEquals(Detection.MessageVersionIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
  
  
  @Test
  public void testRuleEmpty() {
    mh.setMessageVersion(" ");
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
    assertEquals(Detection.MessageVersionIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testRuleUnrecognized() {
    mh.setMessageVersion("abc");
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
    assertEquals(Detection.MessageVersionIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
}
