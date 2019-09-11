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

public class VaccinationAdminCodeIsValidTester {

  private VaccinationAdminCodeIsValid vaccAdminCodeIsValidRule = new VaccinationAdminCodeIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqeVaccination v = new MqeVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccinationAdminCodeIsValid.class);

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
   * Test the vaccAdminCodeIsValidRule with a null type code.
   */
  @Test
  public void testRuleNullType() {
    v.setAdminNdcCode(null);
    v.setAdminCvxCode(null);
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a blank type code.
   */
  @Test
  public void testRuleBlankType() {
    v.setAdminNdcCode("");
    v.setAdminCvxCode("");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a blank type code.
   */
  @Test
  public void testRuleSpacesType() {
    v.setAdminNdcCode("          ");
    v.setAdminCvxCode("          ");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Shouldn't pass", false, r.isRulePassed());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a valid type code.
   */
  @Test
  public void testRuleValidValueCVX() {
    v.setAdminCvxCode("141");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a valid type code.
   */
  @Test
  public void testRuleForeignValueCVX() {
    v.setAdminCvxCode("173");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(false, r.isRulePassed());
    assertEquals(Detection.VaccinationAdminCodeIsForeign,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a valid type code.
   */
  @Test
  public void testRuleUnspecifiedValueCVX() {
    v.setAdminCvxCode("26");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(false, r.isRulePassed());
    assertEquals(Detection.VaccinationAdminCodeIsNotSpecific,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a valid type code.
   */
  @Test
  public void testRule998ValueCVX() {
    v.setAdminCvxCode("998");
    v.setAdministered(false);
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(false, r.isRulePassed());
    assertEquals(Detection.VaccinationAdminCodeIsValuedAsNotAdministered,
        r.getValidationDetections().get(0).getDetection());
  }


  /**
   * Test the vaccAdminCodeIsValidRule with a valid type code.
   */
  @Test
  public void testRule998AdmindValueCVX() {
    v.setAdminCvxCode("998");
    v.setAdministered(true);
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    assertEquals(false, r.isRulePassed());
    assertEquals(Detection.VaccinationAdminCodeIsNotVaccine,
        r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationAdminCodeIsValuedAsNotAdministered,
        r.getValidationDetections().get(1).getDetection());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a valid type code.
   */
  @Test
  public void testRule999AdmindValueCVX() {
    v.setAdminCvxCode("999");
    v.setAdministered(true);
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    assertEquals(false, r.isRulePassed());
    assertEquals(Detection.VaccinationAdminCodeIsNotVaccine,
        r.getValidationDetections().get(0).getDetection());
    assertEquals(Detection.VaccinationAdminCodeIsValuedAsUnknown,
        r.getValidationDetections().get(1).getDetection());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a valid type code.
   */
  @Test
  public void testRule999ValueCVX() {
    v.setAdminCvxCode("999");
    v.setAdministered(false);
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(false, r.isRulePassed());
    assertEquals(Detection.VaccinationAdminCodeIsValuedAsUnknown,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a null type code.
   */
  @Test
  public void testRuleValidValue() {
    v.setAdminNdcCode("49281-0545-03");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a null type code.
   */
  @Test
  public void testRuleValidValueTen() {
    v.setAdminNdcCode("49281-545-03");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a null type code.
   */
  @Test
  public void testRuleValidValueNoDash() {
    v.setAdminNdcCode("49281054503");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
    assertEquals("Should pass", true, r.isRulePassed());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a null type code.
   */
  @Test
  public void testRuleInvalidValue1() {
    v.setAdminNdcCode("1");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdminCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccAdminCodeIsValidRule with a null type code.
   */
  @Test
  public void testRuleInvalidValue2() {
    v.setAdminNdcCode("99999999999999999999999999999999999999999999999999999999999999999999");
    ValidationRuleResult r = vaccAdminCodeIsValidRule.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationAdminCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

}

