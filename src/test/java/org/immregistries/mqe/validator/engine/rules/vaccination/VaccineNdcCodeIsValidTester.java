package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;

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

public class VaccineNdcCodeIsValidTester {

  private VaccinationNdcIsValid rule = new VaccinationNdcIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationNdcIsValid.class);

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
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleNullType() {
    v.setAdminNdcCode(null);
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationNDCCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleBlankType() {
    v.setAdminNdcCode("");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationNDCCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleBlankSpacesType() {
    v.setAdminNdcCode("          ");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationNDCCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleValidValue() {
    v.setAdminNdcCode("49281-0545-03");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleValidValueTen() {
    v.setAdminNdcCode("49281-545-03");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleValidValueNoDash() {
    v.setAdminNdcCode("49281054503");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleInvalidValue1() {
    v.setAdminNdcCode("1");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationNDCCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the rule with a null type code.
   */
  @Test
  public void testRuleInvalidValue2() {
    v.setAdminNdcCode("99999999999999999999999999999999999999999999999999999999999999999999");
    ValidationRuleResult r = rule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationNDCCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
}

