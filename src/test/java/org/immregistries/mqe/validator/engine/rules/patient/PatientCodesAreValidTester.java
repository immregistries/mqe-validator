package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests a whole bunch of individual codes used for the patient.
 * Ethnicity
 * Sex/Gender
 * Name type
 * Facility ID
 * Facility name
 * Primary language
 * Publicity
 * Race
 * VFC/Financial eligibility
 * Created by Allison on 5/10/2017.
 */
public class PatientCodesAreValidTester {

  private PatientCodesAreValid rule = new PatientCodesAreValid();

  // TODO: deprecated/invalid values come up as "unrecognized" instead

  /*
   * Parts required for the test:
   */
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();

  private static final Logger logger = LoggerFactory.getLogger(PatientCodesAreValidTester.class);

  /**
   * Sets up the objects with valid codes.
   */
  @Before
  public void setUpTheObjects() {
    p.setEthnicity("2186-5");               // "not Hispanic or Latino"
    p.setSexCode("M");                      // "male"
    p.setNameTypeCode("L");                 // "legal name"
    p.setFacilityIdNumber("123");           // probably not a real facility ID...
    p.setFacilityName("abc");               // DEFINITELY not a real facility name
    p.setPrimaryLanguageCode("eng");        // "English"
    p.setPublicityCode("01");               // "No reminder/recall"
    p.setRaceCode("1002-5");                // "American Indian or Alaska Native"
    p.setFinancialEligibilityCode("MIA14"); // VFC status; "Medicaid-Non VFC"

    mh.setMessageDate(new Date());          // today's date.
    mr.setMessageHeader(mh);
    mr.setPatient(p);
  }

  /**
   * Tests the basic rule with valid data.
   * (should be true)
   */
  @Test
  public void testRule() {
    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  // PATIENT_ETHNICITY

  /**
   * Tests a null ethnicity code.
   */
  @Test
  public void testRuleNullEthnicity() {
    p.setEthnicity(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientEthnicityIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Tests an unrecognized ethnicity code.
   */
  @Test
  public void testRuleUnrecognizedEthnicity() {
    p.setEthnicity("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientEthnicityIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Tests a deprecated ethnicity code.
   */
  @Test
  public void testRuleDeprecatedEthnicity() {
    p.setEthnicity("H");    // "Hispanic"

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientEthnicityIsDeprecated,
        r.getValidationDetections().get(0).getDetection());
  }

  // PATIENT_GENDER

  /**
   * Tests an unrecognized sex/gender code.
   */
  @Test
  public void testRuleUnrecognizedSexCode() {
    p.setSexCode("Q");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientGenderIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Tests a null sex/gender code.
   */
  @Test
  public void testRuleNullSexCode() {
    p.setSexCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientGenderIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  // PATIENT_NAME_TYPE_CODE

  /**
   * Test with the type code set to something other than "legal".
   */
  @Test
  public void testRuleNameTypeNotLegal() {
    p.setNameTypeCode("D"); // D means "display name"

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientNameTypeCodeIsNotValuedLegal,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with the type code null.
   */
  @Test
  public void testRuleNullNameType() {
    p.setNameTypeCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    // PatientNameTypeCodeIsMissing and PatientNameTypeCodeIsNotValuedLegal
  }

  /**
   * Test with the type code empty.
   */
  @Test
  public void testRuleEmptyNameType() {
    p.setNameTypeCode("");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    // PatientNameTypeCodeIsMissing and PatientNameTypeCodeIsNotValuedLegal
  }

  /**
   * Test with the type code set to an unrecognized value.
   */
  @Test
  public void testRuleUnrecognizedNameType() {
    p.setNameTypeCode("Q");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(2, r.getValidationDetections().size());
    // PatientNameTypeCodeIsUnrecognized and PatientNameTypeCodeIsNotValuedLegal
  }

  // PATIENT_PRIMARY_FACILITY_ID and PATIENT_PRIMARY_FACILITY_NAME

  /**
   * Test with null facility ID.
   * (should be false)
   */
  @Test
  public void testRuleNullFacilityId() {
    p.setFacilityIdNumber(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientPrimaryFacilityIdIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with null facility name.
   * (should be false)
   */
  @Test
  public void testRuleNullFacilityName() {
    p.setFacilityName(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientPrimaryFacilityNameIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  // PATIENT_PRIMARY_LANGUAGE

  /**
   * Test with deprecated language code.
   */
  @Test
  public void testRuleDeprecatedLanguage() {
    p.setPrimaryLanguage("Ar"); // "Arabic"; current code is "ara"

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientPrimaryLanguageIsDeprecated,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with unrecognized language code.
   */
  @Test
  public void testRuleUnrecognizedLanguage() {
    p.setPrimaryLanguage("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientPrimaryLanguageIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with missing language code.
   */
  @Test
  public void testRuleNullLanguage() {
    p.setPrimaryLanguage(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientPrimaryLanguageIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  // PATIENT_PUBLICITY_CODE

  /**
   * Test with unrecognized publicity code.
   */
  @Test
  public void testRuleUnrecognizedPublicity() {
    p.setPublicityCode("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientPublicityCodeIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with null publicity code.
   */
  @Test
  public void testRuleNullPublicity() {
    p.setPublicityCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientPublicityCodeIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  // PATIENT_RACE

  /**
   * Test a deprecated race code.
   */
  @Test
  public void testRuleDeprecatedRace() {
    p.setRaceCode("I"); // "American Indian or Alaska Native"; the current one is "1002-5"

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientRaceIsDeprecated,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test a null race code.
   */
  @Test
  public void testRuleNullRace() {
    p.setRaceCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientRaceIsMissing, r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test an unrecognized race code.
   */
  @Test
  public void testRuleUnrecognizedRace() {
    p.setRaceCode("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientRaceIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  // PATIENT_VFC_STATUS

  /**
   * Test an invalid eligibility code.
   * (should be false)
   */
  @Test
  public void testRuleInvalidEligibility() {
    p.setFinancialEligibilityCode("V00");   // "VFC eligibility unknown"

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientVfcStatusIsInvalid,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test an unrecognized eligibility code.
   */
  @Test
  public void testRuleUnrecognizedEligibility() {
    p.setFinancialEligibilityCode("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientVfcStatusIsUnrecognized,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test a null eligibility code.
   */
  @Test
  public void testRuleNullEligibility() {
    p.setFinancialEligibilityCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientVfcStatusIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }
}
