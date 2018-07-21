package org.immregistries.mqe.validator.engine.rules.header;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageHeaderDateIsExpectedFormatTester {
  private MessageHeaderDateIsExpectedFormat rule = new MessageHeaderDateIsExpectedFormat();
  
  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  
  private static final Logger logger = LoggerFactory.getLogger(MessageHeaderDateIsExpectedFormatTester.class);

  @Test
  public void testRule() {
    String dateTime = "20180514105605-0400";
    mh.setMessageDateString(dateTime);
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }
  
  
  @Test
  public void testRuleDateWrongFormat() {
    String dateTime = "11-15-2013";
    mh.setMessageDateString(dateTime);

    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
    assertEquals(Detection.MessageMessageDateIsUnexpectedFormat,
        r.getValidationDetections().get(0).getDetection());
  }
}
