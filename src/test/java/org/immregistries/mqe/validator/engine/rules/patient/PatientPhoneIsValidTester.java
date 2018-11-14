package org.immregistries.mqe.validator.engine.rules.patient;

import static org.junit.Assert.assertEquals;

import org.immregistries.mqe.validator.detection.Detection;
import org.immregistries.mqe.validator.engine.ValidationRuleResult;
import org.immregistries.mqe.vxu.MqeMessageHeader;
import org.immregistries.mqe.vxu.MqeMessageReceived;
import org.immregistries.mqe.vxu.MqePatient;
import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientPhoneIsValidTester {

	  private PatientPhoneIsValid rule = new PatientPhoneIsValid();

	  private MqeMessageHeader mh = new MqeMessageHeader();
	  private MqeMessageReceived mr = new MqeMessageReceived();
	  private MqePatient p = new MqePatient();

	  private static final Logger logger = LoggerFactory.getLogger(PatientPhoneIsValidTester.class);

	  /**
	   * Test the basic rule with a valid phone number.
	   * (should be true)
	   */
	  @Test
	  public void testRule() {
		MqePhoneNumber phone = new MqePhoneNumber("810", "9573567");
		phone.setTelEquipCode("CP");
		phone.setTelUseCode("PRN");
		p.setPhone(phone);
	    ValidationRuleResult r = rule.executeRule(p, mr);
	    assertEquals(0, r.getValidationDetections().size());
	    assertEquals(true, r.isRulePassed());
	  }

	  /**
	   * Test with missing area code.
	   * (should be false)
	   */
	  @Test
	  public void testRuleNoAreaCode() {
	    p.getPhone().setAreaCode(null);
	    p.getPhone().setNumber("9573567");
		p.getPhone().setTelEquipCode("CP");
		p.getPhone().setTelUseCode("PRN");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneIsIncomplete,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with missing local number (the 7-digit part).
	   */
	  @Test
	  public void testRuleNoLocalNumber() {
	    p.getPhone().setLocalNumber(null);

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals("should get one issue", 1, r.getValidationDetections().size());
	    assertEquals("Should get phone number missing", Detection.PatientPhoneIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with missing phone number overall.
	   */
	  @Test
	  public void testRuleNullPhone() {
	    p.setPhone(null);

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with invalid phone number (bad format)
	   */
	  @Test
	  public void testRuleBadFormat() {
	    p.getPhone().setAreaCode("1234");
	    p.getPhone().setLocalNumber("abc");
		p.getPhone().setTelEquipCode("CP");
		p.getPhone().setTelUseCode("PRN");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with invalid phone number (bad area code)
	   */
	  @Test
	  public void testRuleBadAreaCode() {
	    p.getPhone().setAreaCode("000");
	    p.getPhone().setLocalNumber("9573567");
		p.getPhone().setTelEquipCode("CP");
		p.getPhone().setTelUseCode("PRN");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }

	  /**
	   * Test with invalid phone number (bad local number)
	   */
	  @Test
	  public void testRuleBadLocalNumber() {
	    p.getPhone().setLocalNumber("abc");
	    p.getPhone().setAreaCode("810");
		p.getPhone().setTelEquipCode("CP");
		p.getPhone().setTelUseCode("PRN");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneIsInvalid,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Test with unrecognized Tel Equip Code
	   */
	  @Test
	  public void testRuleUnrecognizedTelEquipCode() {
	    p.getPhone().setLocalNumber("9573567");
	    p.getPhone().setAreaCode("810");
		p.getPhone().setTelEquipCode("PX");
		p.getPhone().setTelUseCode("PRN");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneTelEquipCodeIsUnrecognized,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Test with missing Tel Equip Code
	   */
	  @Test
	  public void testRuleMissingTelEquipCode() {
	    p.getPhone().setLocalNumber("9573567");
	    p.getPhone().setAreaCode("810");
		p.getPhone().setTelEquipCode("");
		p.getPhone().setTelUseCode("PRN");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneTelEquipCodeIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Test with unrecognized Tel Use Code
	   */
	  @Test
	  public void testRuleUnrecognizedTelUseCode() {
	    p.getPhone().setLocalNumber("9573567");
	    p.getPhone().setAreaCode("810");
		p.getPhone().setTelEquipCode("CP");
		p.getPhone().setTelUseCode("PRX");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneTelUseCodeIsUnrecognized,
	        r.getValidationDetections().get(0).getDetection());
	  }
	  
	  /**
	   * Test with missing Tel Use Code
	   */
	  @Test
	  public void testRuleMissingTelUseCode() {
	    p.getPhone().setLocalNumber("9573567");
	    p.getPhone().setAreaCode("810");
		p.getPhone().setTelEquipCode("CP");
		p.getPhone().setTelUseCode("");

	    ValidationRuleResult r = rule.executeRule(p, mr);
	    logger.info(r.getValidationDetections().toString());
	    assertEquals(1, r.getValidationDetections().size());
	    assertEquals(Detection.PatientPhoneTelUseCodeIsMissing,
	        r.getValidationDetections().get(0).getDetection());
	  }
	
}
