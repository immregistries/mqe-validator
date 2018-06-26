package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class VaccineProductIsValidTester {

  private VaccinationProductIsValid rule = new VaccinationProductIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccineNdcCodeIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
  }

  /**
   * Test the basic rule with a valid type code.
   * (should be true)
   */
  @Test
  public void testRule() {
    v.setProduct("KINRIX");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleNullType() {
    v.setProduct(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals("Should be one issue when value is invalid"
        , 1, r.getValidationDetections().size());
    assertEquals("Should be Unrecognized", Detection.VaccinationProductIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleInvalidValue() {
    v.setProduct("KINRIXOS");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals("Should be one issue when value is invalid"
        , 1, r.getValidationDetections().size());
    assertEquals("Should be Unrecognized", Detection.VaccinationProductIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
}

