package org.immregistries.mqe.validator.engine.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.junit.Before;
import org.junit.Test;


public class PhoneValidatorTest {

  private PhoneValidator pd = PhoneValidator.INSTANCE;

  private String[] invalidAreaCodes = {"023", "111","112","113","121","131","A12", "5A5", "77A", "87A"};
  private String[] validAreaCodes = {"517","612","713","821","931","212", "525", "772", "872"};

  private String[] invalidLocalNumbers = {"1","11","123","1234","12345","123456", "5554848", "5551234", "87A", "565587A"};
  private String[] validLocalNumbers = {
       "2316516"
      ,"4981356"
      ,"498-1356"
      ,"498-0001"
      ,"498-0254"
  };

  private void areaCodeAssertion(String areaCode, boolean expectValid) {
    MqePhoneNumber pn = new MqePhoneNumber();
    pn.setCountryCode("+1");
    pn.setLocalNumber("9876543");
    pn.setAreaCode(areaCode);
    boolean isValid = pd.isValidPhone(pn);
    assertEquals(areaCode + " should be " + (expectValid ? " valid " : " invalid "), expectValid, isValid);
  }

  @Test
  public void assertGoodAreaCodes() {
    for (String ac : validAreaCodes) {
      areaCodeAssertion(ac, true);
    }
  }

  @Test
  public void assertBadAreaCodes() {
    for (String ac : invalidAreaCodes) {
      areaCodeAssertion(ac, false);
    }
  }

  private void localNumAssertion(String localNumber, boolean expectValid) {
    MqePhoneNumber pn = new MqePhoneNumber();
    pn.setCountryCode("+1");
    pn.setAreaCode("517");
    pn.setLocalNumber(localNumber);
    boolean isValid = pd.isValidPhone(pn);
    assertEquals(localNumber + " should be " + (expectValid ? " valid " : " invalid "), expectValid, isValid);
  }

  @Test
  public void assertGoodLocalNumber() {
    for (String ln : validLocalNumbers) {
      localNumAssertion(ln, true);
    }
  }

  @Test
  public void assertBadLocalNumber() {
    for (String ln : invalidLocalNumbers) {
      localNumAssertion(ln, false);
    }
  }

  @Test
  public void testBadPhoneNumbers() {
//    1. There shouldn't be dashes.  No dashes.
    MqePhoneNumber pn = new MqePhoneNumber();
    pn.setCountryCode("+1");
    String areaCode = "511";
    pn.setAreaCode(areaCode);
    pn.setLocalNumber("544-5555");
    boolean isValid = pd.isValidPhone(pn);
    assertFalse(isValid);

  }

  @Test
  public void testGoodPhoneFormats() {
    MqePhoneNumber pn = new MqePhoneNumber();
    pn.setCountryCode("+1");
    pn.setAreaCode("511");
    pn.setLocalNumber("5445555");

    assertFalse("Should be false since Area Code contains 11", pd.isValidPhone(pn));
    assertTrue(pd.isValidPhoneAccordingToGoogle(pn));
    pn.setAreaCode("");
    assertTrue("Should be valid without an area code", pd.isValidPhone(pn));
    assertTrue(pd.isValidPhoneAccordingToGoogle(pn));

    //Divergence:
    pn.setLocalNumber("");
    assertFalse("Should be invalid when theres NO value", pd.isValidPhone(pn));
    assertFalse(pd.isValidPhoneAccordingToGoogle(pn));
  }
}
