package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.detection.ValidationReport;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Allison on 5/12/2017.
 */
public class PatientAddressIsValidTester {

  private PatientAddressIsValid rule = new PatientAddressIsValid();

  // Parts required for the test
  private MqeMessageHeader mh = new MqeMessageHeader();
  private MqeMessageReceived mr = new MqeMessageReceived();
  private MqePatient p = new MqePatient();

  private static final Logger logger = LoggerFactory.getLogger(PatientAddressIsValidTester.class);

  /**
   * Sets up the objects needed for the test. Next of kin and patient need the same address.
   */
  @Before
  public void setUpTheObjects() {
    MqeAddress addr = new MqeAddress();
    addr.setStreet("233 Cherokee Ln");
    addr.setStreet2("Apt 106");
    addr.setCity("Flint");
    addr.setStateCode("MI");
    addr.setZip("49501");
    addr.setCountryCode("USA");
    addr.setCountyParishCode("73");
    addr.setTypeCode("P");

    p.getPatientAddressList().add(addr);

    mh.setMessageDate(new Date());
    mr.setMessageHeader(mh);
    mr.setPatient(p);
  }

  /**
   * Test the basic rule with a valid address.
   * (should be true)
   */
  @Test
  public void testRule() {
    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.isRulePassed());
  }

  /**
   * Test with a null (missing) address.
   */
  @Test
  public void testRuleNullAddress() {
    p.getPatientAddressList().clear();
    p.getPatientAddressList().add(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    System.out.println(r.getValidationDetections().toString());
    assertEquals(1, r.getValidationDetections().size());
    assertEquals(Detection.PatientAddressIsMissing,
        r.getValidationDetections().get(0).getDetection());
  }

  /**
   * Test with missing (null) street 1.
   */
  @Test
  public void testRuleNullStreet() {
    p.getPatientAddress().setStreet(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressStreetIsMissing::equals));
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsMissing::equals));
  }

  /**
   * Test with missing (null) street 2.
   */
  @Test
  public void testRuleNullStreet2() {
    p.getPatientAddress().setStreet2(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());

    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsPresent::equals)
    );
  }

  /**
   * Test with missing city.
   */
  @Test
  public void testRuleMissingCity() {
    p.getPatientAddress().setCity(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r.getValidationDetections().size() > 0);
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressCityIsMissing::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsMissing::equals)
    );
  }

  /**
   * Test with missing (null) state.
   */
  @Test
  public void testRuleNullState() {
    p.getPatientAddress().setStateCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressStateIsMissing::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsMissing::equals)
    );
  }

  /**
   * Test with missing zip.
   */
  @Test
  public void testRuleMissingZip() {
    p.getPatientAddress().setZip(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressZipIsMissing::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsMissing::equals)
    );
  }

  /**
   * Test with invalid zip.
   */
  @Test
  public void testRuleInvalidZip() {
    p.getPatientAddress().setZip("abc");

    //we're not checking formats any more.  this shouldn't throw an error.

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsPresent::equals)
    );
  }

  /**
   * Test with missing (null) country.
   */
  @Test
  public void testRuleNullCountry() {
    p.getPatientAddress().setCountryCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressCountryIsMissing::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsPresent::equals)
    );
  }

  /**
   * Test with missing (null) county.
   */
  @Test
  public void testRuleNullCounty() {
    p.getPatientAddress().setCountyParishCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressCountyIsMissing::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsPresent::equals)
    );
  }

  /**
   * Test with "bad address" address type.
   */
  @Test
  public void testRuleBadAddressType() {
    p.getPatientAddress().setTypeCode("BA");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressTypeIsValuedBadAddress::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsPresent::equals)
    );
  }

  /**
   * Test with unrecognized address type.
   */
  @Test
  public void testRuleUnrecognizedAddressType() {
    p.getPatientAddress().setTypeCode("abc");

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressTypeIsUnrecognized::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsPresent::equals)
    );
  }

  /**
   * Test with missing (null) address type.
   */
  @Test
  public void testRuleNullAddressType() {
    p.getPatientAddress().setTypeCode(null);

    ValidationRuleResult r = rule.executeRule(p, mr);
    logger.info(r.getValidationDetections().toString());
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressTypeIsMissing::equals)
    );
    assertTrue(r
            .getValidationDetections()
            .stream()
            .map(ValidationReport::getDetection)
            .anyMatch(Detection.PatientAddressIsPresent::equals)
    );
  }
}
