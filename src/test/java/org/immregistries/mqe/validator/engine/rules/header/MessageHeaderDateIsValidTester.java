package org.immregistries.mqe.validator.engine.rules.header;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.DateFormatter;
import org.immregistries.mqe.core.util.DateUtility;
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

public class MessageHeaderDateIsValidTester {
  private MessageHeaderDateIsValid rule = new MessageHeaderDateIsValid();
  
  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private SimpleDateFormat formatTZ = new SimpleDateFormat("yyyyMMddHHmmssZ");
  private SimpleDateFormat formatNoTZ = new SimpleDateFormat("yyyyMMddHHmmss");
  
  private static final Logger logger = LoggerFactory.getLogger(MessageHeaderDateIsValidTester.class);

  
  @Test
  public void testRule() {
    String today = formatTZ.format(new Date());
    mh.setMessageDateString(today);
    mh.setMessageDate(new Date());
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }
  
  
  @Test
  public void testRuleDateMissing() {
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
    assertEquals(Detection.MessageMessageDateIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testRuleDateFuture() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    Date futureDate = calendar.getTime();
    String futureDateStr = formatTZ.format(futureDate);
    mh.setMessageDateString(futureDateStr);
    
    mh.setMessageDate(futureDate);
    mr.setReceivedDate(new Date());
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
    assertEquals(Detection.MessageMessageDateIsInFuture,
        r.getValidationDetections().get(0).getDetection());
  }
  
  @Test
  public void testRuleDateMissingTZ() {
    String today = formatNoTZ.format(new Date());
    mh.setMessageDateString(today);
    mh.setMessageDate(new Date());
    ValidationRuleResult r = rule.executeRule(mh, mr);
    logger.info(r.getValidationDetections().toString());
    assertFalse(r.isRulePassed());
    assertEquals(Detection.MessageMessageDateIsMissingTimezone,
        r.getValidationDetections().get(0).getDetection());
  }
}
