package org.immregistries.mqe.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccinationLotNumberIsValidTester {

  private VaccinationAdministeredLotNumberIsValid vaccAdminLotRule =
      new VaccinationAdministeredLotNumberIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccineProductIsValidTester.class);

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
   * Test that a good lot number passes
   */
  @Test
  public void testBasicPass() {
    v.setLotNumber("A12345");
    v.setManufacturerCode("PMC");
    ValidationRuleResult r = vaccAdminLotRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
  }

  /**
   * Test that a bad lot number fails
   */
  @Test
  public void testBasicFail() {
    v.setLotNumber("!!!");
    v.setManufacturerCode("PMC");
    ValidationRuleResult r = vaccAdminLotRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }

  /**
   * Test that a bad format does not pass
   */
  @Test
  public void testLotFormat() {
    v.setLotNumber("A1A1A1A1A1A1");
    v.setManufacturerCode("PMC");
    ValidationRuleResult r = vaccAdminLotRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }

  @Test
  public void testSuffix() {
    v.setLotNumber("A12345-V");
    ValidationRuleResult r = vaccAdminLotRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }

  @Test
  public void testPreffix() {
    v.setLotNumber("PMCA12345");
    ValidationRuleResult r = vaccAdminLotRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }

  @Test
  public void testInfix() {
    v.setLotNumber("A12345ICE35677");
    ValidationRuleResult r = vaccAdminLotRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }
  
  @Test
  public void testInvalid() {
    v.setLotNumber("A12345!");
    ValidationRuleResult r = vaccAdminLotRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }
}

