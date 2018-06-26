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
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Allison on 5/4/2017.
 */
public class NextOfKinNameIsValidTester {

  private NextOfKinNameIsValid rule = new NextOfKinNameIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeNextOfKin nok = new MqeNextOfKin();

  private static final Logger logger = LoggerFactory.getLogger(NextOfKinNameIsValidTester.class);

  /**
   * Sets up the objects needed for the test. Next of kin and patient need the same address.
   */
  @Before
  public void setUpTheObjects() {
    nok.setNameFirst("Lois");
    nok.setNameLast("Lane");
    setNextOfKin();

    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
  }

  /**
   * Test the basic rule with a valid first and last name. (should be true)
   */
  @Test
  public void testRule() {
    ValidationRuleResult r = rule.executeRule(nok, mr);
    assertTrue(r.isRulePassed());
  }

  /**
   * Test with a missing first name. (should be false)
   */
  @Test
  public void testRuleNoFirstName() {
    nok.setNameFirst(null);
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinNameFirstIsMissing, r.getValidationDetections().get(0)
        .getDetection());
  }

  /**
   * Test with a missing last name. (should be false)
   */
  @Test
  public void testRuleNoLastName() {
    nok.setNameLast(null);
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinNameLastIsMissing, r.getValidationDetections().get(0)
        .getDetection());
  }

  /**
   * Test with both parts of the name missing. (should be false)
   */
  @Test
  public void testRuleNoName() {
    nok.setNameFirst(null);
    nok.setNameLast(null);
    setNextOfKin();

    ValidationRuleResult r = rule.executeRule(nok, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.NextOfKinNameIsMissing,
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
