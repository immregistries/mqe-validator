package org.immregistries.dqa.validator.engine.common;

import static org.junit.Assert.*;

import org.immregistries.dqa.vxu.DqaPhoneNumber;
import org.junit.Before;
import org.junit.Test;


public class PhoneValidatorTest {

	PhoneValidator pd = PhoneValidator.INSTANCE;
	private DqaPhoneNumber pn;
	
	@Before
	public void buildNewCode() {
		this.pn = new DqaPhoneNumber();
		pn.setCountryCode("+1");
		pn.setAreaCode("517");
		pn.setLocalNumber("5555555");
	}
	
	@Test
	public void testGoodPhoneFormats() {
		assertTrue(pd.isValidPhone(this.pn));
		assertTrue(pd.isValidPhoneAccordingToGoogle(this.pn));
		
		pn.setAreaCode("");
		assertTrue("Should be valid without an area code",pd.isValidPhone(this.pn));
		assertTrue(pd.isValidPhoneAccordingToGoogle(this.pn));
		
		//Divergence:
		pn.setLocalNumber("");
		assertFalse("Should be invalid when theres NO value",pd.isValidPhone(this.pn));
		assertFalse(pd.isValidPhoneAccordingToGoogle(this.pn));
	}
}
