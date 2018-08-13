package org.immregistries.mqe.validator.engine.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.immregistries.mqe.vxu.MqePhoneNumber;
import org.junit.Before;
import org.junit.Test;


public class PhoneValidatorTest {

  PhoneValidator pd = PhoneValidator.INSTANCE;
  private MqePhoneNumber pn;

  @Before
  public void buildNewCode() {
    this.pn = new MqePhoneNumber();
    pn.setCountryCode("+1");
    pn.setAreaCode("511");
    pn.setLocalNumber("5445555");
  }

  @Test
  public void testGoodPhoneFormats() {
    assertFalse("Should be false since Area Code contains 11", pd.isValidPhone(this.pn));
    assertTrue(pd.isValidPhoneAccordingToGoogle(this.pn));

    pn.setAreaCode("");
    assertTrue("Should be valid without an area code", pd.isValidPhone(this.pn));
    assertTrue(pd.isValidPhoneAccordingToGoogle(this.pn));

    //Divergence:
    pn.setLocalNumber("");
    assertFalse("Should be invalid when theres NO value", pd.isValidPhone(this.pn));
    assertFalse(pd.isValidPhoneAccordingToGoogle(this.pn));
  }
}
