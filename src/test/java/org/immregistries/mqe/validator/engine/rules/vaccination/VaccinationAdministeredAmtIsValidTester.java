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

public class VaccinationAdministeredAmtIsValidTester {

  private VaccinationAdministeredAmtIsValid rule = new VaccinationAdministeredAmtIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationAdministeredAmtIsValidTester.class);

  /**
   * Sets up the objects needed for the test.
   */
  @Before
  public void setUpTheObjects() {
    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.getVaccinations().add(v);
    v.setAdministered(true);
  }

  /**
   * Test the basic rule with a valid type code.
   * (should be true)
   */
  @Test
  public void testPass() {
    v.setAmount("1.0");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertTrue(r.isRulePassed());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleInvalidType() {
    // no amount or amount is 999
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdministeredAmountIsMissing,
            r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationAdministeredAmountIsValuedAsUnknown,
            r.getValidationDetections().get(1).getDetection());
    
    v.setAmount("999");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdministeredAmountIsMissing,
            r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationAdministeredAmountIsValuedAsUnknown,
            r.getValidationDetections().get(1).getDetection());
    
    // amount is 0
    v.setAmount("0");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdministeredAmountIsValuedAsZero,
            r.getValidationDetections().get(0).getDetection());
    
    // amount is invalid
    v.setAmount("abc");
    r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdministeredAmountIsInvalid,
            r.getValidationDetections().get(0).getDetection());
  }


}

