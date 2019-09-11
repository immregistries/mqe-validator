package org.immregistries.mqe.validator.engine.rules.nextofkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Allison on 5/4/2017.
 */
public class NextOfKinPhoneIsValidTester {

  private NextOfKinPhoneIsValid rule = new NextOfKinPhoneIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeNextOfKin nok = new MqeNextOfKin();

  private static final Logger logger = LoggerFactory.getLogger(NextOfKinPhoneIsValidTester.class);

  /**
   * Sets up the objects needed for the test. Next of kin and patient need the same address.
   */
  @Before
  public void setUpTheObjects() {
    MqePhoneNumber phone = new MqePhoneNumber("810", "9573567");
    nok.setPhone(phone);
    setNextOfKin();

    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
  }

  /**
   * Test the basic rule with a valid phone number.
   * (should be true)
   */
  @Test
  public void testRule() {
    ValidationRuleResult r = rule.executeRule(nok, mr);
    assertTrue(r.isRulePassed());
  }

  /**
   * Test with missing area code.
   * (should be false)
   */
  @Test
  public void testRuleNoAreaCode() {
    nok.getPhone().setAreaCode(null);
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinPhoneNumberIsIncomplete,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with missing local number (the 7-digit part).
   */
  @Test
  public void testRuleNoLocalNumber() {
    nok.getPhone().setLocalNumber(null);
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals("should get one issue", 1, r.getValidationDetections().size());
    assertEquals("Should get phone number missing", Detection.NextOfKinPhoneNumberIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with missing phone number overall.
   */
  @Test
  public void testRuleNullPhone() {
    nok.setPhone(null);
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinPhoneNumberIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with invalid phone number (bad format)
   */
  @Test
  public void testRuleBadFormat() {
    nok.getPhone().setAreaCode("1234");
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinPhoneNumberIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with invalid phone number (bad area code)
   */
  @Test
  public void testRuleBadAreaCode() {
    nok.getPhone().setAreaCode("000");
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinPhoneNumberIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with invalid phone number (bad local number)
   */
  @Test
  public void testRuleBadLocalNumber() {
    nok.getPhone().setLocalNumber("abc");
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinPhoneNumberIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Set the next-of-kin in the message.
   */
  private void setNextOfKin() {
    List<MqeNextOfKin> noks = new ArrayList<>();
    noks.add(nok);
    mr.setNextOfKins(noks);
  }
}
