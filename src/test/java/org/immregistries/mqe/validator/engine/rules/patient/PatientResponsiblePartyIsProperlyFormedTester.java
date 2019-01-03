package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeAddress;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqeNextOfKin;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientResponsiblePartyIsProperlyFormedTester {

	  private PatientResponsiblePartyIsProperlyFormed rule = new PatientResponsiblePartyIsProperlyFormed();

	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqeNextOfKin nok = new MqeNextOfKin();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientResponsiblePartyIsProperlyFormedTester.class);
	  
	  /**
	   * Tests missing patient guardian state (address).
	   */
	  @Test
	  public void testMissingPatientResponsiblePartyAddressState() {
		nok.setNameFirst("Jane");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
	    MqeAddress addr = new MqeAddress();
	    addr.setStreet("233 Cherokee Ln");
	    addr.setStreet2("Apt 106");
	    addr.setCity("Flint");
	    addr.setStateCode("");
	    addr.setZip("49501");
	    addr.setCountryCode("USA");
	    addr.setCountyParishCode("73");
	    addr.setTypeCode("P");

	    nok.setAddress(addr);
		
		p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressStateIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient guardian city (address).
	   */
	  @Test
	  public void testMissingPatientResponsiblePartyAddressCity() {
		nok.setNameFirst("Jane");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
	    MqeAddress addr = new MqeAddress();
	    addr.setStreet("233 Cherokee Ln");
	    addr.setStreet2("Apt 106");
	    addr.setCity("");
	    addr.setStateCode("MI");
	    addr.setZip("49501");
	    addr.setCountryCode("USA");
	    addr.setCountyParishCode("73");
	    addr.setTypeCode("P");

	    nok.setAddress(addr);
		
		p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressCityIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient guardian zip (address).
	   */
	  @Test
	  public void testMissingPatientResponsiblePartyAddressZip() {
		nok.setNameFirst("Jane");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
	    MqeAddress addr = new MqeAddress();
	    addr.setStreet("233 Cherokee Ln");
	    addr.setStreet2("Apt 106");
	    addr.setCity("Flint");
	    addr.setStateCode("MI");
	    addr.setZip("");
	    addr.setCountryCode("USA");
	    addr.setCountyParishCode("73");
	    addr.setTypeCode("P");

	    nok.setAddress(addr);
		
		p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianAddressZipIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient guardian first name.
	   */
	  @Test
	  public void testMissingPatientResponsiblePartyFirstName() {
		nok.setNameFirst("");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
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
		
		p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianNameFirstIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient guardian last name.
	   */
	  @Test
	  public void testMissingPatientResponsiblePartyLastName() {
		nok.setNameFirst("Jane");
		nok.setNameLast("");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
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
		
		p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianNameLastIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests patient guardian name same as underage patient name.
	   */
	  @Test
	  public void testPatientResponsiblePartyNameSameAsUnderagePatient() {
		nok.setNameFirst("Jane");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
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

		p.setNameFirst("Jane");
		p.setNameLast("Doe");
		
		p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianNameIsSameAsUnderagePatient,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient guardian phone.
	   */
	  @Test
	  public void testMissingPatientResponsiblePartyPhone() {
		nok.setNameFirst("Jane");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber(""));
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
	    
	    p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianPhoneIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests missing patient guardian relationship.
	   */
	  @Test
	  public void testMissingPatientResponsiblePartyRelationship() {
		nok.setNameFirst("Jane");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
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
	    
	    p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientGuardianRelationshipIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Tests valid patient guardian.
	   */
	  @Test
	  public void testValidPatientResponsibleParty() {
		nok.setNameFirst("Jane");
		nok.setNameLast("Doe");
		nok.setRelationshipCode("MTH");
		nok.setPhoneNumber(new MqePhoneNumber("8109573567"));
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

		p.setNameFirst("John");
		p.setNameLast("Doe");
		
		p.setResponsibleParty(nok);
		
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }
}
