package org.immregistries.dqa.validator.engine.rules.vaccination;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.engine.ValidationRuleResult;
import org.immregistries.dqa.vxu.DqaMessageHeader;
import org.immregistries.dqa.vxu.DqaMessageReceived;
import org.immregistries.dqa.vxu.DqaVaccination;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaccineCvxCodeIsValidTester {

  private VaccinationCvxIsValid vaccinationCvxIsValid = new VaccinationCvxIsValid();

  // Parts required for the test
  private DqaMessageHeader mh = new DqaMessageHeader();
  private DqaMessageReceived mr = new DqaMessageReceived();
  private DqaVaccination v = new DqaVaccination();

  private static final Logger logger = LoggerFactory.getLogger(VaccineProductIsValidTester.class);

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
   * Test the vaccinationCvxIsValid with a null type code.
   */
  @Test
  public void testRuleNullType() {
    v.setAdminCvxCode(null);
    ValidationRuleResult r = vaccinationCvxIsValid.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCvxCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccinationCvxIsValid with a null type code.
   */
  @Test
  public void testRuleBlankType() {
    v.setAdminCvxCode("");
    ValidationRuleResult r = vaccinationCvxIsValid.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCvxCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccinationCvxIsValid with a null type code.
   */
  @Test
  public void testRuleBlankSpacesType() {
    v.setAdminCvxCode("          ");
    ValidationRuleResult r = vaccinationCvxIsValid.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCvxCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccinationCvxIsValid with a null type code.
   */
  @Test
  public void testRuleValidValue() {
    v.setAdminCvxCode("141");
    ValidationRuleResult r = vaccinationCvxIsValid.executeRule(v, mr);
    logger.warn("Detections: " + r.getValidationDetections().toString());
    assertEquals(0, r.getValidationDetections().size());
  }

  /**
   * Test the vaccinationCvxIsValid with a null type code.
   */
  @Test
  public void testRuleInvalidValue1() {
    v.setAdminCvxCode("1");
    ValidationRuleResult r = vaccinationCvxIsValid.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCvxCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test the vaccinationCvxIsValid with a null type code.
   */
  @Test
  public void testRuleInvalidValue2() {
    v.setAdminCvxCode("99999999999999999999999999999999999999999999999999999999999999999999");
    ValidationRuleResult r = vaccinationCvxIsValid.executeRule(v, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.VaccinationCvxCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }
}

