package org.immregistries.mqe.validator.engine.rules.nextofkin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NextOfKinGuardianAddressIsValidTester {

	  private NextOfKinGuardianAddressIsValid patientGuardianAddressIsValid = new NextOfKinGuardianAddressIsValid();

	  // Parts required for the test
	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqeNextOfKin nok = new MqeNextOfKin();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(NextOfKinGuardianAddressIsValidTester.class);
	  
	  /**
	   * Sets up the objects needed for the test. Patient guardian and patient need the same address.
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

	    nok.setAddress(addr);
	    resetPatientGuardian();

	    p.getPatientAddressList().add(new MqeAddress(addr));

	    mh.setMessageDate(new Date());
	    mr.setMessageHeader(mh);
	    mr.setPatient(p);
	  }
	  
	  /**
	   * Test the basic patientGuardianAddressIsValid with a valid address. (should be true)
	   */
	  @Test
	  public void testRule() {
	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    assertTrue(r.isRulePassed());
	  }
	  
	  /**
	   * Test with a null (missing) address.
	   */
	  @Test
	  public void testRuleNullAddress() {
	    nok.setAddress(null);
	    resetPatientGuardian();

	    p.getPatientAddressList().clear();

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Test with missing (null) street 1.
	   */
	  @Test
	  public void testRuleNullStreet() {
	    nok.getAddress().setStreet(null);
	    resetPatientGuardian();

	    MqeAddress paddr = new MqeAddress(nok.getAddress());
	    p.getPatientAddressList().clear();
	    p.getPatientAddressList().add(paddr);

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressStreetIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Test with missing (null) street 2.
	   */
	  @Test
	  public void testRuleNullStreet2() {
	    nok.getAddress().setStreet2(null);
	    resetPatientGuardian();

	    MqeAddress paddr = new MqeAddress(nok.getAddress());
	    p.getPatientAddressList().clear();
	    p.getPatientAddressList().add(paddr);

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	  }
	  
	  /**
	   * Test with missing city.
	   */
	  @Test
	  public void testRuleMissingCity() {
	    nok.getAddress().setCity(null);
	    resetPatientGuardian();

	    MqeAddress paddr = new MqeAddress(nok.getAddress());
	    p.getPatientAddressList().clear();
	    p.getPatientAddressList().add(paddr);

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertTrue(r.getValidationDetections().size() > 0);
	    assertEquals(Detection.PatientGuardianAddressCityIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Test with missing (null) state.
	   */
	  @Test
	  public void testRuleNullState() {
	    nok.getAddress().setStateCode(null);
	    resetPatientGuardian();

	    MqeAddress paddr = new MqeAddress(nok.getAddress());
	    p.getPatientAddressList().clear();
	    p.getPatientAddressList().add(paddr);

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressStateIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with missing zip.
	   */
	  @Test
	  public void testRuleMissingZip() {
	    nok.getAddress().setZip(null);
	    resetPatientGuardian();

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressZipIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with invalid zip.
	   */
	  @Test
	  public void testRuleInvalidZip() {
	    nok.getAddress().setZip("");
	    resetPatientGuardian();

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressZipIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with missing (null) country.
	   */
	  @Test
	  public void testRuleNullCountry() {
	    nok.getAddress().setCountryCode(null);
	    resetPatientGuardian();

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressCountryIsMissing,
	        r.getValidationDetections().get(0).getDetection());

	  }

	  /**
	   * Test with "bad address" address type.
	   */
	  @Test
	  public void testRuleBadAddressType() {
	    nok.getAddress().setTypeCode("BA");
	    resetPatientGuardian();

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressTypeIsValuedBadAddress,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with unrecognized address type.
	   */
	  @Test
	  public void testRuleUnrecognizedAddressType() {
	    nok.getAddress().setTypeCode("abc");
	    resetPatientGuardian();

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressTypeIsUnrecognized,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with missing (null) address type.
	   */
	  @Test
	  public void testRuleNullAddressType() {
	    nok.getAddress().setTypeCode(null);
	    resetPatientGuardian();

	    ValidationRuleResult r = patientGuardianAddressIsValid.executeRule(nok, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressTypeIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Set the patient guardian in the message.
	   */
	  private void resetPatientGuardian() {
	    List<MqeNextOfKin> noks = new ArrayList<>();
	    noks.add(nok);
	    mr.setNextOfKins(noks);
	  }
	
}
